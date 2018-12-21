package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HotelRestrictDTO implements Serializable {
    private static final long serialVersionUID = -615698518534885231L;

    private Integer ratePlanId;

    private Integer bookDays;

    private String bookTime;

    private Integer occupancyOfDays;

    private Integer numberOfBooking;

    private Integer cancelType = 1;

    private Integer cancelDays;

    private String cancelTime;

    private Integer lastConfirmDays;

    private String lastConfirmHours;
}
