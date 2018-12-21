package com.travel.hotel.product.entity.po;

import com.travel.common.dto.GenericQueryDTO;

import java.math.BigDecimal;
import java.util.Date;

public class AdditionalChargePO extends GenericQueryDTO {
    private Long chargeId;

    private String chargeType;

    private String chargeName;

    private String chargeDescrip;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    private Long hotelId;

    private String baseCurrency;

    private BigDecimal basePrice;

    private BigDecimal saleBPrice;

    private BigDecimal saleCPrice;

    private String saleBCurrency;

    private String saleCCurrency;

    private String supplyCode;

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName == null ? null : chargeName.trim();
    }

    public String getChargeDescrip() {
        return chargeDescrip;
    }

    public void setChargeDescrip(String chargeDescrip) {
        this.chargeDescrip = chargeDescrip == null ? null : chargeDescrip.trim();
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

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency == null ? null : baseCurrency.trim();
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getSaleBPrice() {
        return saleBPrice;
    }

    public void setSaleBPrice(BigDecimal saleBPrice) {
        this.saleBPrice = saleBPrice;
    }

    public BigDecimal getSaleCPrice() {
        return saleCPrice;
    }

    public void setSaleCPrice(BigDecimal saleCPrice) {
        this.saleCPrice = saleCPrice;
    }

    public String getSaleBCurrency() {
        return saleBCurrency;
    }

    public void setSaleBCurrency(String saleBCurrency) {
        this.saleBCurrency = saleBCurrency == null ? null : saleBCurrency.trim();
    }

    public String getSaleCCurrency() {
        return saleCCurrency;
    }

    public void setSaleCCurrency(String saleCCurrency) {
        this.saleCCurrency = saleCCurrency == null ? null : saleCCurrency.trim();
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode == null ? null : supplyCode.trim();
    }
}