package com.travel.hotel.product.entity.po;

import java.util.Date;

public class QuotaPO {
    private Long quotaId;

    private Long accountId;

    private Date saleDate;

    private String quotaState;

    private Integer quotaNum;

    /**
     * 扣留配额
     */
    private Integer detQuotaNum;

    /**
     * 预留配额
     */
    private Integer reserveQuotaNum;

    private Integer allQuotaNum;

    private Integer usedQuotaNum;

    @Deprecated
    private Integer overdraft;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    public Long getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getQuotaState() {
        return quotaState;
    }

    public void setQuotaState(String quotaState) {
        this.quotaState = quotaState == null ? null : quotaState.trim();
    }

    public Integer getQuotaNum() {
        return quotaNum;
    }

    public void setQuotaNum(Integer quotaNum) {
        this.quotaNum = quotaNum;
    }

    public Integer getDetQuotaNum() {
        return detQuotaNum;
    }

    public void setDetQuotaNum(Integer detQuotaNum) {
        this.detQuotaNum = detQuotaNum;
    }

    public Integer getReserveQuotaNum() {
        return reserveQuotaNum;
    }

    public void setReserveQuotaNum(Integer reserveQuotaNum) {
        this.reserveQuotaNum = reserveQuotaNum;
    }

    public Integer getAllQuotaNum() {
        return allQuotaNum;
    }

    public void setAllQuotaNum(Integer allQuotaNum) {
        this.allQuotaNum = allQuotaNum;
    }

    public Integer getUsedQuotaNum() {
        return usedQuotaNum;
    }

    public void setUsedQuotaNum(Integer usedQuotaNum) {
        this.usedQuotaNum = usedQuotaNum;
    }

    public Integer getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Integer overdraft) {
        this.overdraft = overdraft;
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
}