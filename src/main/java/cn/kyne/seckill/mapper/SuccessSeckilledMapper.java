package cn.kyne.seckill.mapper;

import cn.kyne.seckill.entity.SuccessSeckilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangkun01 on 2017/9/4.
 */
public interface SuccessSeckilledMapper {

    /**
     * 插入购买明细,可以过滤重复秒杀
     * @param seckillId
     * @param userPhone
     * @return
     */
    int saveSuccessSeckilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据ID查询SuccessSeckilled并携带秒杀商品对象实体
     * @param seckillId
     * @return
     */
    SuccessSeckilled getByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
