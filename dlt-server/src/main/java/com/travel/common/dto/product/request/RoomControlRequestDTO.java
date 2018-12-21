package com.travel.common.dto.product.request;

import com.travel.common.dto.GenericQueryDTO;

import java.util.List;

/**
 *   2018/1/26.
 */
public class RoomControlRequestDTO extends GenericQueryDTO {

    /**
     * 酒店ID
     */
    private Long hotelId;

    private Long pricePlanId;

    /**
     * 包房类型（采购类型），多选
     */
    private List<String> quotaTypeList;

    private String quotaType;

    /**
     * 供应商编码
     */
    private String  supplyCode;

    private String beginDate;

    private String endDate;

    /**
     * 价格计划有效性
     */
    private Integer isActive;
    private String isActiveName;

    private String hotelName;
    private String supplyName;

   private String cityName;
   private String cityCode;
   
   private Integer cityType;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

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

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public List<String> getQuotaTypeList() {
        return quotaTypeList;
    }

    public void setQuotaTypeList(List<String> quotaTypeList) {
        this.quotaTypeList = quotaTypeList;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId) {
        this.pricePlanId = pricePlanId;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public String getIsActiveName() {
        return isActiveName;
    }

    public void setIsActiveName(String isActiveName) {
        this.isActiveName = isActiveName;
    }
}
