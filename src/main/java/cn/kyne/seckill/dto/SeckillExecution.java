package cn.kyne.seckill.dto;

import cn.kyne.seckill.entity.SuccessSeckilled;
import cn.kyne.seckill.enums.SeckillStatenum;

import java.io.Serializable;

/**
 * 封装秒杀执行后的结果
 * Created by kyne on 2017/9/5.
 */
public class SeckillExecution {

    private long seckillId;
    private int state;
    private String stateInfo;
    private SuccessSeckilled successSeckilled;

    public SeckillExecution(long seckillId, SeckillStatenum seckillStatenum, SuccessSeckilled successSeckilled) {
        this.seckillId = seckillId;
        this.state = seckillStatenum.getState();
        this.stateInfo = seckillStatenum.getStateInfo();
        this.successSeckilled = successSeckilled;
    }

    public SeckillExecution(long seckillId, SeckillStatenum seckillStatenum) {
        this.seckillId = seckillId;
        this.state = seckillStatenum.getState();
        this.stateInfo = seckillStatenum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSeckilled getSuccessSeckilled() {
        return successSeckilled;
    }

    public void setSuccessSeckilled(SuccessSeckilled successSeckilled) {
        this.successSeckilled = successSeckilled;
    }
}
