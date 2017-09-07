package cn.kyne.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀成功记录实体类
 * Created by zhangkun01 on 2017/9/4.
 */
public class SuccessSeckilled {

    private long seckillId;//秒杀商品ID
    private long userPhone;//用户手机号
    private short state;//状态 -1:无效 0:成功 1:已付款 2:已发货
    private Date createTime;//创建时间

    //多对一
    private Seckill seckill;

    @Override
    public String toString() {
        return "SuccessSeckilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
}
