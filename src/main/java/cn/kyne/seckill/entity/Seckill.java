package cn.kyne.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品实体类
 * Created by zhangkun01 on 2017/9/4.
 */
public class Seckill {

    private long seckillId;//秒杀商品ID
    private String name;//秒杀商品名称
    private String description;//秒杀商品描述
    private double originalPrice;//秒杀商品原价
    private double seckillPrice;//秒杀价格
    private String detailImg;//秒杀商品详情图片
    private String image;//秒杀商品图片
    private int number;//秒杀商品库存量
    private Date startTime;//秒杀开始时间
    private Date endTime;//秒杀结束时间
    private Date createTime;//创建时间

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", originalPrice=" + originalPrice +
                ", seckillPrice=" + seckillPrice +
                ", detailImg='" + detailImg + '\'' +
                ", image='" + image + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public String[] getImage() {
        return image.split(",");
    }

    public void setImage(String image) {
        this.image = image;
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
