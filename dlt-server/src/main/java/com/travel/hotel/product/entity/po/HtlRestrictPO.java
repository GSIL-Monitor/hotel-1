package com.travel.hotel.product.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class HtlRestrictPO {
    private Long restrictId;

    private Long priceplanId;

    private Date saleDate;

    private Integer bookDays;

    private Integer occupancyType;

    private Integer occupancyDays;

    private Integer cancelType = 1;

    private Integer cancelDays;

    private String cancelTime;

    private String cancelRemark;

    private Integer bookRooms;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    private Integer bookTime;
}