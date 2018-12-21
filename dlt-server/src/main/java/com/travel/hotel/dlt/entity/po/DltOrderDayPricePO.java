package com.travel.hotel.dlt.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DltOrderDayPricePO {
    private Long id;

    private String dltOrderId;

    private Date effectDate;

    private Integer mealType;

    private String breakfast;

    private Integer breakfastNum;

    private String currency;

    private Long price;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    @Override
    public String toString() {
        return "DltOrderDayPricePO{" +
                "effectDate=" + effectDate +
                ", mealType=" + mealType +
                ", breakfast='" + breakfast + '\'' +
                ", breakfastNum=" + breakfastNum +
                ", currency='" + currency + '\'' +
                ", price=" + price +
                '}';
    }
}