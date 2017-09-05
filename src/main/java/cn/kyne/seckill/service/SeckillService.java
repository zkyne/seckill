package cn.kyne.seckill.service;

import cn.kyne.seckill.entity.Seckill;

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

}
