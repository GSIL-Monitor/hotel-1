package com.travel.hotel.product.entity;

import com.travel.hotel.product.entity.po.PricePlanPO;

/**
 *   2018/1/16.
 */
public class PricePlanDTO extends PricePlanPO{

    private String hotelName;
    private String supplyName;
    private String roomTypeName;

    /**
     * 是否允许预留配额
     */
    private Integer preHoldQuota;
    private String preHoldQuotaName;

    /**
     * 采购类型的名称
     */
    private String quotaTypeName;

    private String isActiveName;

    public String getIsActiveName() {
        return isActiveName;
    }

    public void setIsActiveName(String isActiveName) {
        this.isActiveName = isActiveName;
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

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getQuotaTypeName() {
        return quotaTypeName;
    }

    public void setQuotaTypeName(String quotaTypeName) {
        this.quotaTypeName = quotaTypeName;
    }

    public Integer getPreHoldQuota() {
        return preHoldQuota;
    }

    public void setPreHoldQuota(Integer preHoldQuota) {
        this.preHoldQuota = preHoldQuota;
    }

    public String getPreHoldQuotaName() {
        return preHoldQuotaName;
    }

    public void setPreHoldQuotaName(String preHoldQuotaName) {
        this.preHoldQuotaName = preHoldQuotaName;
    }
}
