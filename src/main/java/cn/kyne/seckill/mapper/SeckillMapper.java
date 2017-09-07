package cn.kyne.seckill.mapper;

import cn.kyne.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

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
    List<Seckill> listAll(@Param("offset") int offset,@Param("limit") int limit);

    /**
     * 调用存储过程执行秒杀
     * @param params
     */
    void seckillByProcedure(Map<String,Object> params);

}
