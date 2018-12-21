package com.travel.hotel.dlt.request.dto;

import lombok.Data;

@Data
public class SaleRule {
    //ctrip、qunar、elong
    private String channelType;
    private Boolean isSetAutoChange;
    private Boolean autoChange;
    private Integer formula;
    private Integer modifyValue;
    private String baseChannel;

    //ctrip、qunar
    private Boolean isSetCancel;
    private Boolean canCancel;
    private Integer checkInDay;
    private String checkInTime;
    private Integer deductType;
    private Integer deductProportion;
    private Integer checkInDay2;
    private String checkInTime2;
    private Integer deductType2;
    private Integer deductProportion2;
    private Boolean isSetLastBookTime;
    private Integer lastBookDay;
    private String lastBookTime;
    private Boolean isSetLastConfirmTime;
    private Integer lastConfirmDay;
    private String lastConfirmTime;

    //qunar
    private Boolean isSetSaleTime;
    private String startTime;
    private String endTime;
    private Integer endDateType;
    private Boolean isSetLimit;
    private Integer limitType;
    private Integer advanceDay;
    private String advanceTime;
    private Integer advanceType;
    private Boolean isSetLast;
    private Integer minLastDay;
    private Integer maxLastDay;
    private Boolean isSetBookRoomNum;
    private Integer minBookRoomNum;
    private Integer maxBookRoomNum;
}
