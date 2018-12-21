package com.travel.hotel.product.entity.po;

import java.math.BigDecimal;
import java.util.Date;

import com.travel.common.dto.GenericQueryDTO;

public class HotelPO extends GenericQueryDTO{
    private Long hotelId;

    private String hotelName;

    private String address;

    private String tel;

    private String cityCode;

    private String cityName;

    private String email;

    private String introduction;

    private Integer isactive;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    private String country;

    private String province;

    private String star;

    private String supplyCode;

    private  String supplyName;

    /**
     * 能否预留配额
     */
    private Integer preHoldQuota;

    /**
     * 返佣扣税税率
     */
    private BigDecimal rebateTax;

    /**
     * 返佣给客户时，扣客户税的税率
     */
    private BigDecimal customerRebateTax;

    private Long relationId;


    private String contacts;
    /**
     * 供应商传真号码
     */
    private String fax;

    /**
     * 供应商结算方式
     */
    private String settlement;
    
    private BigDecimal bedSalePrice;

    private String bedSaleCurrency;

    private BigDecimal breakfastSalePrice;

    private String breakfastSaleCurrency;

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public BigDecimal getCustomerRebateTax() {
        return customerRebateTax;
    }

    public void setCustomerRebateTax(BigDecimal customerRebateTax) {
        this.customerRebateTax = customerRebateTax;
    }

    public BigDecimal getRebateTax() {
        return rebateTax;
    }

    public void setRebateTax(BigDecimal rebateTax) {
        this.rebateTax = rebateTax;
    }

    public Integer getPreHoldQuota() {
        return preHoldQuota;
    }

    public void setPreHoldQuota(Integer preHoldQuota) {
        this.preHoldQuota = preHoldQuota;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star == null ? null : star.trim();
    }

	public BigDecimal getBedSalePrice() {
		return bedSalePrice;
	}

	public void setBedSalePrice(BigDecimal bedSalePrice) {
		this.bedSalePrice = bedSalePrice;
	}

	public String getBedSaleCurrency() {
		return bedSaleCurrency;
	}

	public void setBedSaleCurrency(String bedSaleCurrency) {
		this.bedSaleCurrency = bedSaleCurrency;
	}

	public BigDecimal getBreakfastSalePrice() {
		return breakfastSalePrice;
	}

	public void setBreakfastSalePrice(BigDecimal breakfastSalePrice) {
		this.breakfastSalePrice = breakfastSalePrice;
	}

	public String getBreakfastSaleCurrency() {
		return breakfastSaleCurrency;
	}

	public void setBreakfastSaleCurrency(String breakfastSaleCurrency) {
		this.breakfastSaleCurrency = breakfastSaleCurrency;
	}

}