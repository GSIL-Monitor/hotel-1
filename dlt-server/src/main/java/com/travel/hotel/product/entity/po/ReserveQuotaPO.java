package com.travel.hotel.product.entity.po;

import com.travel.common.dto.GenericQueryDTO;

import java.util.Date;

public class ReserveQuotaPO extends GenericQueryDTO{
    private Long reserveId;

    private Long quotaAccountId;

    private String agentName;

    private Long priceplanId;

    private String pricePlanName;

    private Date saleDate;

    private String saleDateStr;

    private String distributeCode;

    /**
     * 本次要新增的预留配额
     * 页面做修改的时候的：此值有可能为负数
     * 即：原来预留是2，现在改为1，那么传入的此值为-1.表示要增加-1的意思。
     */
    private Integer reserveQuotaNum;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    /**
     * 预留截止时间
     */
    private Date endTime;

    /**
     * 本次更新，需要增加多少
     */
    private Integer amount;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getQuotaAccountId() {
        return quotaAccountId;
    }

    public void setQuotaAccountId(Long quotaAccountId) {
        this.quotaAccountId = quotaAccountId;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public String getSaleDateStr() {
        return saleDateStr;
    }

    public void setSaleDateStr(String saleDateStr) {
        this.saleDateStr = saleDateStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }



    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
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

    public String getDistributeCode() {
        return distributeCode;
    }

    public void setDistributeCode(String distributeCode) {
        this.distributeCode = distributeCode == null ? null : distributeCode.trim();
    }

    public Integer getReserveQuotaNum() {
        return reserveQuotaNum;
    }

    public void setReserveQuotaNum(Integer reserveQuotaNum) {
        this.reserveQuotaNum = reserveQuotaNum;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}