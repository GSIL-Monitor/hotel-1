package com.travel.hotel.product.entity.po;

import java.util.Date;

public class DatePO {
    private Long dateid;

    private Date dateDate;

    private String dateStr;

    public Long getDateid() {
        return dateid;
    }

    public void setDateid(Long dateid) {
        this.dateid = dateid;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr == null ? null : dateStr.trim();
    }
}