# seckill
系统采用springMVC+spring+mybatis实现简单高并发秒杀系统

参考:慕课网java高并发秒杀系列课程

1)系统采用通过mysql数据库行级锁的方式来应对高并发
2)采用redis缓存机制来暂存秒杀商品信息
3)使用存储过程可以更加高效
其他秒杀解决方案:
