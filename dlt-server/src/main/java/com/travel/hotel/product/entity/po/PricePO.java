package com.travel.hotel.product.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class PricePO {
    private Long priceId;

    private Long priceplanId;

    private Date saleDate;

    /**
     * 底价币种
     */
    private String baseCurrency;

    /**
     * 底价
     */
    private BigDecimal basePrice;

    /**
     * 早餐
     */
    private String breakfastNum;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    /**
     * B2B售价
     */
    private BigDecimal saleBPrice;

    /**
     * 渠道币种（B2B,OTA渠道都是这个币种）
     */
    private String saleChannelCurrency;

    /**
     * 直客价
     */
    private BigDecimal saleCPrice;

    /**
     * 直客价币种
     */
    private String saleCCurrency;

    /**
     * 淘宝售价
     */
    private BigDecimal tmPrice;

    /**
     * 携程售价
     */
    private BigDecimal ctripPrice;

    /**
     * 京东售价
     */
    private BigDecimal jdPrice;

    /**
     * 新美大售价
     */
    private BigDecimal xmdPrice;

    /**
     * 途牛售价
     */
    private BigDecimal tuniuPrice;
    /**
     * 同程售价
     */
    private BigDecimal tongchengPrice;

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Long getPriceplanId() {
        return priceplanId;
    }

    public void setPriceplanId(Long priceplanId) {
        this.priceplanId = priceplanId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency == null ? null : baseCurrency.trim();
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getBreakfastNum() {
        return breakfastNum;
    }

    public void setBreakfastNum(String breakfastNum) {
        this.breakfastNum = breakfastNum;
    }

    public BigDecimal getSaleBPrice() {
        return saleBPrice;
    }

    public void setSaleBPrice(BigDecimal saleBPrice) {
        this.saleBPrice = saleBPrice;
    }

    public String getSaleChannelCurrency() {
        return saleChannelCurrency;
    }

    public void setSaleChannelCurrency(String saleChannelCurrency) {
        this.saleChannelCurrency = saleChannelCurrency == null ? null : saleChannelCurrency.trim();
    }

    public BigDecimal getSaleCPrice() {
        return saleCPrice;
    }

    public void setSaleCPrice(BigDecimal saleCPrice) {
        this.saleCPrice = saleCPrice;
    }

    public String getSaleCCurrency() {
        return saleCCurrency;
    }

    public void setSaleCCurrency(String saleCCurrency) {
        this.saleCCurrency = saleCCurrency == null ? null : saleCCurrency.trim();
    }

    public BigDecimal getTmPrice() {
        return tmPrice;
    }

    public void setTmPrice(BigDecimal tmPrice) {
        this.tmPrice = tmPrice;
    }

    public BigDecimal getCtripPrice() {
        return ctripPrice;
    }

    public void setCtripPrice(BigDecimal ctripPrice) {
        this.ctripPrice = ctripPrice;
    }

    public BigDecimal getJdPrice() {
        return jdPrice;
    }

    public void setJdPrice(BigDecimal jdPrice) {
        this.jdPrice = jdPrice;
    }

    public BigDecimal getXmdPrice() {
        return xmdPrice;
    }

    public void setXmdPrice(BigDecimal xmdPrice) {
        this.xmdPrice = xmdPrice;
    }

    public BigDecimal getTuniuPrice() {
        return tuniuPrice;
    }

    public void setTuniuPrice(BigDecimal tuniuPrice) {
        this.tuniuPrice = tuniuPrice;
    }

    public BigDecimal getTongchengPrice() {
        return tongchengPrice;
    }

    public void setTongchengPrice(BigDecimal tongchengPrice) {
        this.tongchengPrice = tongchengPrice;
    }
}