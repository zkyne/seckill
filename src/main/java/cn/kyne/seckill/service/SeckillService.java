package cn.kyne.seckill.service;

import cn.kyne.seckill.dto.Exposer;
import cn.kyne.seckill.dto.SeckillExecution;
import cn.kyne.seckill.entity.Seckill;
import cn.kyne.seckill.exception.RepeatKillException;
import cn.kyne.seckill.exception.SeckillCloseException;
import cn.kyne.seckill.exception.SeckillException;

import java.util.List;

/**
 * 秒杀业务接口,接口设计应站在'使用者'角度设计接口:
 * 三个方面:方法定义粒度,参数,返回类型
 * Created by zhangkun01 on 2017/9/5.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> listSeckills();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getSeckill(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口url
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillCloseException
     * @throws RepeatKillException
     * @throws SeckillException
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException,RepeatKillException,SeckillException;



}
