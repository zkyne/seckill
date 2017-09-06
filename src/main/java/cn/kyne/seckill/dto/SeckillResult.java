package cn.kyne.seckill.dto;

import java.io.Serializable;

/**
 * 封装json结果,所有ajax返回类型
 * Created by zhangkun01 on 2017/9/6.
 */
public class SeckillResult<T> implements Serializable {

    private static final long serialVersionUID = -6069858217643331488L;

    private boolean success;
    private T data;
    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
