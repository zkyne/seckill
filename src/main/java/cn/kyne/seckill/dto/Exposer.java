package cn.kyne.seckill.dto;

import java.io.Serializable;

/**
 * 暴露秒杀地址DTO
 * Created by zhangkun01 on 2017/9/5.
 */
public class Exposer{

    private boolean exposed;//是否开启秒杀
    private String md5;
    private long seckillId;
    private long curTime;
    private long startTime;
    private long endTime;

    public Exposer() {

    }

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, long curTime, long startTime, long endTime) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.curTime = curTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getCurTime() {
        return curTime;
    }

    public void setCurTime(long curTime) {
        this.curTime = curTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
