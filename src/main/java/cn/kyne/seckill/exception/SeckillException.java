package cn.kyne.seckill.exception;

/**
 * 秒杀相关业务异常
 * 声明式事务只会在发生运行时异常时才会回滚
 * Created by zhangkun01 on 2017/9/5.
 */
public class SeckillException extends RuntimeException {

    private static final long serialVersionUID = -1634637068950560274L;

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
