package cn.kyne.seckill.service.impl;

import cn.kyne.seckill.dto.Exposer;
import cn.kyne.seckill.dto.SeckillExecution;
import cn.kyne.seckill.entity.Seckill;
import cn.kyne.seckill.entity.SuccessSeckilled;
import cn.kyne.seckill.enums.SeckillStatenum;
import cn.kyne.seckill.exception.RepeatKillException;
import cn.kyne.seckill.exception.SeckillCloseException;
import cn.kyne.seckill.exception.SeckillException;
import cn.kyne.seckill.mapper.SeckillMapper;
import cn.kyne.seckill.mapper.SuccessSeckilledMapper;
import cn.kyne.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by kyne on 2017/9/5.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String slat = "#@$!#@!FDSAT$#%DSFASDAd~sfd";

    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private SuccessSeckilledMapper successSeckilledMapper;

    public List<Seckill> listSeckills() {
        return seckillMapper.listAll(0, 4);
    }

    public Seckill getSeckill(long seckillId) {
        return seckillMapper.getById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillMapper.getById(seckillId);
        if (seckill == null) {
            //无此秒杀
            return new Exposer(false, seckillId);
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

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑
        Date curTime = new Date();
        try {
            //减库存
            int updateRow = seckillMapper.reduceNumber(seckillId, curTime);
            if (updateRow <= 0) {
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录秒杀记录
                int saveRow = successSeckilledMapper.saveSuccessSeckilled(seckillId, userPhone);
                if (saveRow <= 0) {
                    throw new RepeatKillException("seckill repeated");
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

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
