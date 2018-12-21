package com.travel.hotel.dlt.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DltOrderCancelRulesPO {
    private Long id;

    private String dltOrderId;

    private Integer deductType;

    private Date lastCancelTime;

    private Long value;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    @Override
    public String toString() {
        return "DltOrderCancelRulesPO{" +
                "dltOrderId='" + dltOrderId + '\'' +
                ", deductType=" + deductType +
                ", lastCancelTime=" + lastCancelTime +
                ", value=" + value +
                '}';
    }
}