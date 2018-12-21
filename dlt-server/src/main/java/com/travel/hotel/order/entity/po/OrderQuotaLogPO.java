package com.travel.hotel.order.entity.po;

import java.util.Date;

public class OrderQuotaLogPO {
    private Long id;

    private Date saleDate;

    private Integer quotaNum;

    private String orderCode;

    private Date createDate;

    private Date modifyDate;

    private String opType;

    private String priceplanId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getQuotaNum() {
        return quotaNum;
    }

    public void setQuotaNum(Integer quotaNum) {
        this.quotaNum = quotaNum;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public String getPriceplanId() {
        return priceplanId;
    }

    public void setPriceplanId(String priceplanId) {
        this.priceplanId = priceplanId == null ? null : priceplanId.trim();
    }
}