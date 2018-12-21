package com.fangcang.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "o_derate_policy_price")
public class DeratePolicyPriceDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 减免政策id
     */
    @Column(name = "derate_policy_id")
    private Integer deratePolicyId;

    /**
     * 减免日期
     */
    @Column(name = "sale_date")
    private Date saleDate;

    /**
     * 减免间数
     */
    @Column(name = "room_num")
    private BigDecimal roomNum;

    /**
     * 售价
     */
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    /**
     * 底价
     */
    @Column(name = "base_price")
    private BigDecimal basePrice;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取减免政策id
     *
     * @return derate_policy_id - 减免政策id
     */
    public Integer getDeratePolicyId() {
        return deratePolicyId;
    }

    /**
     * 设置减免政策id
     *
     * @param deratePolicyId 减免政策id
     */
    public void setDeratePolicyId(Integer deratePolicyId) {
        this.deratePolicyId = deratePolicyId;
    }

    /**
     * 获取减免日期
     *
     * @return sale_date - 减免日期
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * 设置减免日期
     *
     * @param saleDate 减免日期
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * 获取减免间数
     *
     * @return room_num - 减免间数
     */
    public BigDecimal getRoomNum() {
        return roomNum;
    }

    /**
     * 设置减免间数
     *
     * @param roomNum 减免间数
     */
    public void setRoomNum(BigDecimal roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * 获取售价
     *
     * @return sale_price - 售价
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * 设置售价
     *
     * @param salePrice 售价
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * 获取底价
     *
     * @return base_price - 底价
     */
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    /**
     * 设置底价
     *
     * @param basePrice 底价
     */
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}