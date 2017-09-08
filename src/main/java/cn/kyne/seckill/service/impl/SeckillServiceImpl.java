package cn.kyne.seckill.service.impl;

import cn.kyne.seckill.constant.Constants;
import cn.kyne.seckill.dao.RedisDao;
import cn.kyne.seckill.dto.Exposer;
import cn.kyne.seckill.dto.SeckillExecution;
import cn.kyne.seckill.entity.Seckill;
import cn.kyne.seckill.entity.SuccessSeckilled;
import cn.kyne.seckill.constant.SeckillStatenum;
import cn.kyne.seckill.exception.RepeatKillException;
import cn.kyne.seckill.exception.SeckillCloseException;
import cn.kyne.seckill.exception.SeckillException;
import cn.kyne.seckill.mapper.SeckillMapper;
import cn.kyne.seckill.mapper.SuccessSeckilledMapper;
import cn.kyne.seckill.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kyne on 2017/9/5.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SeckillMapper seckillMapper;
    @Resource
    private SuccessSeckilledMapper successSeckilledMapper;
    @Resource
    private RedisDao redisDao;

    public List<Seckill> listSeckills() {
        return seckillMapper.listAll(0, 4);
    }

    public Seckill getSeckill(long seckillId) {
        return seckillMapper.getById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        //缓存优化 从redis缓存中获取
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill == null){
            //缓存中没有,访问数据库
            seckill = seckillMapper.getById(seckillId);
            if (seckill == null) {
                //无此秒杀
                return new Exposer(false, seckillId);
            }else{
                //放入缓存
                redisDao.setSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date curTime = new Date();
        if (curTime.getTime() < startTime.getTime() || curTime.getTime() > endTime.getTime()) {
            //秒杀未开启或秒杀已结束
            return new Exposer(false, seckillId, curTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //符合秒杀时间
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     * 此方法不是只返回dto而是抛出异常,
     * 是因为只有运行时异常时,声明式事务才会回滚
     * Created By kyne on 22:37 2017/9/7.
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑
        Date killTime = new Date();
        try {
            //记录秒杀记录
            int saveRow = successSeckilledMapper.saveSuccessSeckilled(seckillId, userPhone);
            if (saveRow <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                //减库存
                int updateRow = seckillMapper.reduceNumber(seckillId, killTime);
                if (updateRow <= 0) {
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //秒杀成功
                    SuccessSeckilled successSeckilled = successSeckilledMapper.getByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatenum.SUCCESS, successSeckilled);
                }
            }
        } catch (SeckillCloseException | RepeatKillException sc) {
            throw sc;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error: " + e.getMessage());
        }
    }

    @Override
    public SeckillExecution excuteSeckillProcedure(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException {
        if(md5 == null || !md5.equals(getMD5(seckillId))){
            return new SeckillExecution(seckillId,SeckillStatenum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String,Object> params = new HashMap<>();
        params.put("seckillId",seckillId);
        params.put("userPhone",userPhone);
        params.put("killTime",killTime);
        params.put("result",null);
        try {
            seckillMapper.seckillByProcedure(params);
            //获取result,没有赋值为-2
            int result = MapUtils.getInteger(params,"result",-2);
            if(result == 1){
                SuccessSeckilled successSeckilled = successSeckilledMapper.getByIdWithSeckill(seckillId,userPhone);
                return new SeckillExecution(seckillId,SeckillStatenum.SUCCESS,successSeckilled);
            }else {
                return new SeckillExecution(seckillId,SeckillStatenum.stateOf(result));
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new SeckillExecution(seckillId,SeckillStatenum.INNER_ERROR);
        }
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + Constants.SLAT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
