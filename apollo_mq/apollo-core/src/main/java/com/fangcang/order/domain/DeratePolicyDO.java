package com.fangcang.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "o_derate_policy")
public class DeratePolicyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 供货单id
     */
    @Column(name = "supply_order_id")
    private Integer supplyOrderId;

    /**
     * 减免政策名称
     */
    private String name;

    /**
     * 总售价
     */
    @Column(name = "sale_price_sum")
    private BigDecimal salePriceSum;

    /**
     * 总底价
     */
    @Column(name = "base_price_sum")
    private BigDecimal basePriceSum;

    /**
     * 总间夜数
     */
    @Column(name = "room_num_sum")
    private BigDecimal roomNumSum;

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
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取供货单id
     *
     * @return supply_order_id - 供货单id
     */
    public Integer getSupplyOrderId() {
        return supplyOrderId;
    }

    /**
     * 设置供货单id
     *
     * @param supplyOrderId 供货单id
     */
    public void setSupplyOrderId(Integer supplyOrderId) {
        this.supplyOrderId = supplyOrderId;
    }

    /**
     * 获取减免政策名称
     *
     * @return name - 减免政策名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置减免政策名称
     *
     * @param name 减免政策名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取总售价
     *
     * @return sale_price_sum - 总售价
     */
    public BigDecimal getSalePriceSum() {
        return salePriceSum;
    }

    /**
     * 设置总售价
     *
     * @param salePriceSum 总售价
     */
    public void setSalePriceSum(BigDecimal salePriceSum) {
        this.salePriceSum = salePriceSum;
    }

    /**
     * 获取总底价
     *
     * @return base_price_sum - 总底价
     */
    public BigDecimal getBasePriceSum() {
        return basePriceSum;
    }

    /**
     * 设置总底价
     *
     * @param basePriceSum 总底价
     */
    public void setBasePriceSum(BigDecimal basePriceSum) {
        this.basePriceSum = basePriceSum;
    }

    /**
     * 获取总间夜数
     *
     * @return room_num_sum - 总间夜数
     */
    public BigDecimal getRoomNumSum() {
        return roomNumSum;
    }

    /**
     * 设置总间夜数
     *
     * @param roomNumSum 总间夜数
     */
    public void setRoomNumSum(BigDecimal roomNumSum) {
        this.roomNumSum = roomNumSum;
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