package cn.kyne.seckill.dao;

import cn.kyne.seckill.entity.Seckill;

/**
 * 操作redis的dao接口
 * Created by kyne on 2017/9/7.
 */
public interface RedisDao {
    /**
     * 从redis缓存中获取seckill对象
     *
     * @param seckillId
     * @return
     */
    Seckill getSeckill(long seckillId);

    /**
     * 将seckill存入redis缓存
     *
     * @param seckill
     * @return
     */
    String setSeckill(Seckill seckill);

}
