package com.travel.common.dto.product.request;

import java.io.Serializable;

/**
 *   2018/4/18.
 */
public class BatchPriceRequestDTO implements Serializable{

    private String pricePlanId;

    private String hotelId;

    private String roomTypeId;

    private String pricePlanName;

    private String sameSalePrice;

    private String beginDate;

    private String endDate;

    private String basePriceWeekday;

    private String basePriceWeekend;

    private String baseCurrency;

    private String toBPriceWeekday;

    private String toBPriceWeekend;

    private String toCPriceWeekday;

    private String toCPriceWeekend;

    public String getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(String pricePlanId) {
        this.pricePlanId = pricePlanId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public String getSameSalePrice() {
        return sameSalePrice;
    }

    public void setSameSalePrice(String sameSalePrice) {
        this.sameSalePrice = sameSalePrice;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBasePriceWeekday() {
        return basePriceWeekday;
    }

    public void setBasePriceWeekday(String basePriceWeekday) {
        this.basePriceWeekday = basePriceWeekday;
    }

    public String getBasePriceWeekend() {
        return basePriceWeekend;
    }

    public void setBasePriceWeekend(String basePriceWeekend) {
        this.basePriceWeekend = basePriceWeekend;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getToBPriceWeekday() {
        return toBPriceWeekday;
    }

    public void setToBPriceWeekday(String toBPriceWeekday) {
        this.toBPriceWeekday = toBPriceWeekday;
    }

    public String getToBPriceWeekend() {
        return toBPriceWeekend;
    }

    public void setToBPriceWeekend(String toBPriceWeekend) {
        this.toBPriceWeekend = toBPriceWeekend;
    }

    public String getToCPriceWeekday() {
        return toCPriceWeekday;
    }

    public void setToCPriceWeekday(String toCPriceWeekday) {
        this.toCPriceWeekday = toCPriceWeekday;
    }

    public String getToCPriceWeekend() {
        return toCPriceWeekend;
    }

    public void setToCPriceWeekend(String toCPriceWeekend) {
        this.toCPriceWeekend = toCPriceWeekend;
    }
}
