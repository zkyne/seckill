# seckill
系统采用springMVC+spring+mybatis实现简单高并发秒杀系统

开发环境:mysql+redis+maven+IDEA+git+jdk1.7

参考:慕课网java高并发秒杀系列课程

1)系统采用通过mysql数据库行级锁的方式来应对高并发

2)采用redis缓存机制来暂存秒杀商品信息

3)使用存储过程可以更加高效

4)前端使用bootstrap,jquery,cookie插件countdown计时插件等
