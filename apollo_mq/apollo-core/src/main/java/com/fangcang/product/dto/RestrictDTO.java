package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RestrictDTO implements Serializable{
    private Integer id;

    private Long ratePlanId;

    private Integer advanceBookingDays;

    private Integer advanceBookingHours;

    private Integer occupancyOfDays;

    private Integer numberOfBooking;

    /**
     * 1-已经预定不可取消；2-提前取消
     */
    private Integer cancelType;

    private Integer cancelDays;

    private String cancelHours;

    private Integer lastConfirmDays;

    private Integer lastConfirmHours;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyDate;
}