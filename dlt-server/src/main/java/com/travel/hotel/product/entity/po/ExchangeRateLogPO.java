package com.travel.hotel.product.entity.po;

import com.travel.common.dto.GenericQueryDTO;

import java.math.BigDecimal;
import java.util.Date;

public class ExchangeRateLogPO extends GenericQueryDTO {
    private Long logId;

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal rate;

    private String creator;

    private Date createDate;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency == null ? null : sourceCurrency.trim();
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency == null ? null : targetCurrency.trim();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
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
}