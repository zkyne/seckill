package cn.kyne.seckill.exception;

/**
 * 重复秒杀异常
 * Created by zhangkun01 on 2017/9/5.
 */
public class RepeatKillException extends SeckillException {


    private static final long serialVersionUID = 6387800003932574590L;

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
