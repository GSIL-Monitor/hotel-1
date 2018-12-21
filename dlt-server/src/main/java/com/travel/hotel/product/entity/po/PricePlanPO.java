package com.travel.hotel.product.entity.po;

import com.travel.common.dto.GenericQueryDTO;

import java.util.Date;
import java.util.List;

public class PricePlanPO extends GenericQueryDTO {
    private Long priceplanId;

    private String priceplanName;

    private String supplyCode;

    private Long hotelId;

    private Integer payMethod;

    private Integer breakfast;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    private Long roomTypeId;

    private Long accountId;

    /**
     * 采购类型
     */
    private String quotaType;

    private Integer isActive;

    private String saleBCurrency;
    private String saleCCurrency;
    private String baseCurrency;

    /**
     * 城市
     */
    private String cityCode;
    
    private Integer cityType;


    /**
     * 包房类型（采购类型），多选
     */
    private List<String> quotaTypeList;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCityType() {
		return cityType;
	}

	public void setCityType(Integer cityType) {
		this.cityType = cityType;
	}

	public String getSaleBCurrency() {
        return saleBCurrency;
    }

    public void setSaleBCurrency(String saleBCurrency) {
        this.saleBCurrency = saleBCurrency;
    }

    public String getSaleCCurrency() {
        return saleCCurrency;
    }

    public void setSaleCCurrency(String saleCCurrency) {
        this.saleCCurrency = saleCCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public Long getPriceplanId() {
        return priceplanId;
    }

    public void setPriceplanId(Long priceplanId) {
        this.priceplanId = priceplanId;
    }

    public String getPriceplanName() {
        return priceplanName;
    }

    public void setPriceplanName(String priceplanName) {
        this.priceplanName = priceplanName == null ? null : priceplanName.trim();
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode == null ? null : supplyCode.trim();
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<String> getQuotaTypeList() {
        return quotaTypeList;
    }

    public void setQuotaTypeList(List<String> quotaTypeList) {
        this.quotaTypeList = quotaTypeList;
    }

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }
}