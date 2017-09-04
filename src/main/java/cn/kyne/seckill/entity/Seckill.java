package cn.kyne.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangkun01 on 2017/9/4.
 */
public class Seckill implements Serializable {

    private static final long serialVersionUID = 4321261486935488197L;

    private long seckillId;//秒杀商品ID
    private String name;//秒杀商品名称
    private int number;//秒杀商品库存量
    private Date startTime;//秒杀开始时间
    private Date endTime;//秒杀结束时间
    private Date createTime;//创建时间

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
