package cn.kyne.seckill.exception;

/**
 * 秒杀系统关闭异常
 * Created by zhangkun01 on 2017/9/5.
 */
public class SeckillCloseException extends SeckillException {

    private static final long serialVersionUID = -6838563177718557125L;

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
