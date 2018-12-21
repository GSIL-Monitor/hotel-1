package com.fangcang.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "o_supply_product_price")
public class SupplyProductPriceDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供货单id
     */
    @Column(name = "supply_product_id")
    private Integer supplyProductId;

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
     * 底价
     */
    @Column(name = "base_price")
    private BigDecimal basePrice;

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
     * 获取供货单id
     *
     * @return supply_product_id - 供货单id
     */
    public Integer getSupplyProductId() {
        return supplyProductId;
    }

    /**
     * 设置供货单id
     *
     * @param supplyProductId 供货单id
     */
    public void setSupplyProductId(Integer supplyProductId) {
        this.supplyProductId = supplyProductId;
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
}