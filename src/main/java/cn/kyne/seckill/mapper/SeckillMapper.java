package cn.kyne.seckill.mapper;

import cn.kyne.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangkun01 on 2017/9/4.
 */
public interface SeckillMapper {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(long seckillId, Date killTime);

    /**
     * 根据ID查询秒杀商品对象
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> listAll(int offset, int limit);

}
