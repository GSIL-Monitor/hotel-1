package com.travel.common.dto.product.response;

import java.util.Date;

/**
 *   2018/3/14.
 */
public class DailyQuotaDTO {

    private Date saleDate;

    //可售配额
    private Integer onsaleQuotaNum;

    //已售配额
    private Integer usedQuotaNum;

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getOnsaleQuotaNum() {
        return onsaleQuotaNum;
    }

    public void setOnsaleQuotaNum(Integer onsaleQuotaNum) {
        this.onsaleQuotaNum = onsaleQuotaNum;
    }

    public Integer getUsedQuotaNum() {
        return usedQuotaNum;
    }

    public void setUsedQuotaNum(Integer usedQuotaNum) {
        this.usedQuotaNum = usedQuotaNum;
    }
}
