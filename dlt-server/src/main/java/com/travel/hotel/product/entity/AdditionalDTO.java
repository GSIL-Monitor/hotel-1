package com.travel.hotel.product.entity;

import com.travel.hotel.product.entity.po.AdditionalChargePO;

/**
 *   2018/2/2.
 */
public class AdditionalDTO extends AdditionalChargePO {

    private String hotelName;

    private String chargeTypeName;

    private String baseCurrencyName;

    private String saleCCurrencyName;

    private String saleBCurrencyName;

    public String getBaseCurrencyName() {
        return baseCurrencyName;
    }

    public void setBaseCurrencyName(String baseCurrencyName) {
        this.baseCurrencyName = baseCurrencyName;
    }

    public String getSaleCCurrencyName() {
        return saleCCurrencyName;
    }

    public void setSaleCCurrencyName(String saleCCurrencyName) {
        this.saleCCurrencyName = saleCCurrencyName;
    }

    public String getSaleBCurrencyName() {
        return saleBCurrencyName;
    }

    public void setSaleBCurrencyName(String saleBCurrencyName) {
        this.saleBCurrencyName = saleBCurrencyName;
    }

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
}
