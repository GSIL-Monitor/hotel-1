package com.travel.common.dto;

import java.util.Date;

/**
 *   2018/1/23.
 */
public class DateInfoDTO {

    /**
     * YYYY-MM-DD
     */
    private Date saleDate;

    /**
     * 周一、周二、 周三
     */
    private String weekDay;

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
