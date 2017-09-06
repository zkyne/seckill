-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
USE seckill;
-- 创建商品秒杀库存表
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品秒杀ID',
  `name` VARCHAR(120) NOT  NULL COMMENT '秒杀商品名称',
  `description` VARCHAR(300) NOT NULL COMMENT '秒杀商品描述',
  `original_price` DOUBLE(6,2) NOT NULL COMMENT '秒杀商品原价',
  `seckill_price` DOUBLE(6,2) NOT NULL COMMENT '秒杀价格',
  `detail_img` VARCHAR(200) NOT NULL COMMENT '秒杀商品详情图片',
  `image` VARCHAR(600) NOT NULL COMMENT '秒杀商品图片',
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
  seckill(name,description,original_price,seckill_price,detail_img,image,number,start_time,end_time)
VALUES
  ('9.9元秒杀《城南旧事》','城南旧事（名师导读，随书赠送《名著考点与创新试题精华》）',36.00,9.90,'99999990001712592_1_o.jpg','25125042-1_u_1.jpg,25125042-2_u_1.jpg,25125042-3_u_1.jpg,25125042-4_u_1.jpg,25125042-5_u_1.jpg',99,'2017-09-05 00:00:00','2017-09-06 00:00:00'),
  ('9.9元秒杀《好孩子不是管出来的》','好孩子不是管出来的：不骄纵、不惩罚的自然养育',28.00,9.90,'99999990000587406_1_o.jpg','24043653-1_u_6.jpg,24043653-2_u_6.jpg,24043653-3_u_6.jpg,24043653-4_u_6.jpg,24043653-5_u_6.jpg',80,'2017-09-05 00:00:00','2017-09-06 00:00:00'),
  ('56.9元秒杀韩版潮流女针织衫','【厂家直销！！超值秒杀】 秋季新款韩版宽松大码毛针织衫女士贴布毛衣女套头百搭时尚打底衫',699.00,56.90,'162350024770435_1_o.jpg','1354995795-1_u_1.jpg,1354995795-2_u_1.jpg,1354995795-3_u_1.jpg,1354995795-4_u_1.jpg,1354995795-5_u_1.jpg,',90,'2017-09-05 00:00:00','2017-09-06 00:00:00'),
  ('9.9元秒杀《朝花夕拾》','朝花夕拾（名师导读，随书赠送《名著考点与创新试题精华》）',39.80,9.90,'99999990001712582_1_o.jpg','25125041-1_u_3.jpg,25125041-2_u_3.jpg,25125041-3_u_3.jpg,25125041-4_u_3.jpg,25125041-5_u_3.jpg,',66,'2017-09-05 00:00:00','2017-09-06 00:00:00');

-- 创建秒杀成功明细表
CREATE TABLE success_seckilled(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT DEFAULT -1 COMMENT '状态:-1:无效 0:秒杀成功 1:已付款 2:已发货',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),/*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '秒杀成功明细表';
