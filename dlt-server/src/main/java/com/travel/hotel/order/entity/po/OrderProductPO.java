package com.travel.hotel.order.entity.po;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderProductPO {
    private Long opid;

    private Long hotelId;

    private String hotelName;

    private Long roomTypeId;

    private String roomTypeName;

    private Long priceplanId;

    private String priceplanName;

    private Date checkinDate;

    private Date checkoutDate;

    private Integer isactive;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private String orderCode;

    private String productType;

    private List<OrderDayPricePO> orderDayPricePOList;
}