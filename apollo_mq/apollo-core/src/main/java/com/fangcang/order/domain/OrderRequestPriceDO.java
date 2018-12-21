package com.fangcang.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "o_order_request_price")
public class OrderRequestPriceDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单申请id
     */
    @Column(name = "order_request_id")
    private Integer orderRequestId;

    /**
     * 日期
     */
    @Column(name = "sale_date")
    private Date saleDate;

    /**
     * 售价
     */
    @Column(name = "sale_price")
    private BigDecimal salePrice;

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
     * 获取订单申请id
     *
     * @return order_request_id - 订单申请id
     */
    public Integer getOrderRequestId() {
        return orderRequestId;
    }

    /**
     * 设置订单申请id
     *
     * @param orderRequestId 订单申请id
     */
    public void setOrderRequestId(Integer orderRequestId) {
        this.orderRequestId = orderRequestId;
    }

    /**
     * 获取日期
     *
     * @return sale_date - 日期
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * 设置日期
     *
     * @param saleDate 日期
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
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
}