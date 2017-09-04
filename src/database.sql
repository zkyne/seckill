-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
USE seckill;
-- 创建商品秒杀库存表
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品秒杀ID',
  `name` VARCHAR(120) NOT  NULL COMMENT '秒杀商品名称',
  `number` INT NOT NULL COMMENT '库存量',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_create_time(create_time),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀商品库存表';

-- 初始化数据
INSERT INTO
  seckill(name,number,start_time,end_time)
VALUES
  ('200元秒杀iphone6s',100,'2017-09-05 00:00:00','2017-09-06 00:00:00'),
  ('500元秒杀ipad',50,'2017-09-05 00:00:00','2017-09-06 00:00:00'),
  ('100元秒杀小米',200,'2017-09-05 00:00:00','2017-09-06 00:00:00'),
  ('200元秒杀红米note',150,'2017-09-05 00:00:00','2017-09-06 00:00:00');

-- 创建秒杀成功明细表
CREATE TABLE success_seckilled(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT DEFAULT -1 COMMENT '状态:-1:无效 0:秒杀成功 1:已付款 2:已发货',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),/*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '秒杀成功明细表';
