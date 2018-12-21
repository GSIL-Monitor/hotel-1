package com.travel.hotel.product.entity;

import com.travel.hotel.product.entity.po.AdditionalChargePO;

/**
 *   2018/1/26.
 */
public class ChargeDTO extends AdditionalChargePO {

    private String chargeTypeName;

    private String hotelName;

    private String supplyName;

    public String getChargeTypeName() {
        return chargeTypeName;
    }

    public void setChargeTypeName(String chargeTypeName) {
        this.chargeTypeName = chargeTypeName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }
}
