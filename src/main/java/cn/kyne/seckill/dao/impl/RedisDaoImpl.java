package cn.kyne.seckill.dao.impl;

import cn.kyne.seckill.constant.Constants;
import cn.kyne.seckill.dao.RedisDao;
import cn.kyne.seckill.entity.Seckill;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 操作缓存的实现类
 * Created by kyne on 2017/9/7.
 */
@Repository
public class RedisDaoImpl implements RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    @Override
    public Seckill getSeckill(long seckillId) {
        //从redis获取seckill
        try {
            try (Jedis jedis = jedisPool.getResource()) {//jdk1.7支持的自动资源释放
                String key = Constants.REDIS_KEY + seckillId;
                //自定义序列化对象实体,采用protostuff技术
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    //从缓存获取到
                    Seckill seckill = schema.newMessage();
                    ProtobufIOUtil.mergeFrom(bytes, seckill, schema);
                    //seckill被反序列化
                    return seckill;
                }
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public String setSeckill(Seckill seckill) {
        try {
            try(Jedis jedis = jedisPool.getResource()){
                String key = Constants.REDIS_KEY + seckill.getSeckillId();
                byte[] bytes = ProtobufIOUtil.toByteArray(seckill,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //设置超时缓存
                int timeOut = 60 * 60;//设置超时时间为1小时
                return jedis.setex(key.getBytes(), timeOut, bytes);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
