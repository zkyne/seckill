-- 秒杀存储过程设计
-- 切换换行符
DELIMITER $$
-- 定义存储过程
-- 参数:in 表示入参 out 输出参数
-- row_count();返回上一条修改类型sql(delete/insert/update)的影响行数
CREATE PROCEDURE `seckill`.`execute_seckill`
  (IN v_seckill_id BIGINT, IN v_phone BIGINT, IN v_kill_time TIMESTAMP, OUT r_result INT)
  BEGIN
    DECLARE effect_row INT DEFAULT 0;
    START TRANSACTION;
    INSERT IGNORE INTO success_seckilled
    (seckill_id, user_phone, state, create_time)
    VALUES (v_seckill_id, v_phone, 0, v_kill_time);
    SELECT row_count()
    INTO effect_row;
    IF (effect_row = 0)
    THEN
      ROLLBACK;
      SET r_result = -1;
    ELSEIF (effect_row < 0)
      THEN
        ROLLBACK;
        SET r_result = -2;
    ELSE
      UPDATE seckill
      SET number = number - 1
      WHERE v_kill_time < seckill.end_time
            AND v_kill_time > seckill.start_time
            AND number > 0;
      SELECT row_count()
      INTO effect_row;
      IF (effect_row = 0)
      THEN
        ROLLBACK;
        SET r_result = 0;
      ELSEIF (effect_row < 0)
        THEN
          ROLLBACK;
          SET r_result = -2;
      ELSE
        COMMIT;
        SET r_result = 1;
      END IF;
    END IF;
  END $$
-- 换行符切换回来
DELIMITER ;



