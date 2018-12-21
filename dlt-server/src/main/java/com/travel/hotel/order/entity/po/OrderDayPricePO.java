package com.travel.hotel.order.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDayPricePO {
    private Long dayPriceId;

    private Long opId;

    private String orderCode;

    private Long priceplanId;

    private String priceplanName;

    private Date saleDate;

    private String baseCurrency;

    private BigDecimal basePrice;

    private String saleBCurrency;

    private BigDecimal saleBPrice;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private Integer orderBillStatus;

    private String orderBillUser;

    private String saleCCurrency;

    private BigDecimal saleCPrice;

    private BigDecimal baseRate;

    private BigDecimal saleChannelRate;

    private BigDecimal saleCRate;
    
    private BigDecimal actualReceived;
    
    private BigDecimal actualPaied;

    private String voucherCode;

    private Integer rooms;

    private Integer supplyBillStatus;

    private String supplyBillUser;

    private String breakfastNum;
    
    private BigDecimal agentCommission;
    
    private BigDecimal supplyCommission;

    public Long getDayPriceId() {
        return dayPriceId;
    }

    public void setDayPriceId(Long dayPriceId) {
        this.dayPriceId = dayPriceId;
    }

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
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

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
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

    public String getSaleBCurrency() {
        return saleBCurrency;
    }

    public void setSaleBCurrency(String saleBCurrency) {
        this.saleBCurrency = saleBCurrency == null ? null : saleBCurrency.trim();
    }

    public BigDecimal getSaleBPrice() {
        return saleBPrice;
    }

    public void setSaleBPrice(BigDecimal saleBPrice) {
        this.saleBPrice = saleBPrice;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getOrderBillStatus() {
        return orderBillStatus;
    }

    public void setOrderBillStatus(Integer orderBillStatus) {
        this.orderBillStatus = orderBillStatus;
    }

    public String getOrderBillUser() {
        return orderBillUser;
    }

    public void setOrderBillUser(String orderBillUser) {
        this.orderBillUser = orderBillUser == null ? null : orderBillUser.trim();
    }

    public String getSaleCCurrency() {
        return saleCCurrency;
    }

    public void setSaleCCurrency(String saleCCurrency) {
        this.saleCCurrency = saleCCurrency == null ? null : saleCCurrency.trim();
    }

    public BigDecimal getSaleCPrice() {
        return saleCPrice;
    }

    public void setSaleCPrice(BigDecimal saleCPrice) {
        this.saleCPrice = saleCPrice;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public BigDecimal getSaleChannelRate() {
        return saleChannelRate;
    }

    public void setSaleChannelRate(BigDecimal saleChannelRate) {
        this.saleChannelRate = saleChannelRate;
    }

    public BigDecimal getSaleCRate() {
        return saleCRate;
    }

    public void setSaleCRate(BigDecimal saleCRate) {
        this.saleCRate = saleCRate;
    }

    public BigDecimal getActualReceived() {
		return actualReceived;
	}

	public void setActualReceived(BigDecimal actualReceived) {
		this.actualReceived = actualReceived;
	}

	public BigDecimal getActualPaied() {
		return actualPaied;
	}

	public void setActualPaied(BigDecimal actualPaied) {
		this.actualPaied = actualPaied;
	}

	public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode == null ? null : voucherCode.trim();
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getSupplyBillStatus() {
        return supplyBillStatus;
    }

    public void setSupplyBillStatus(Integer supplyBillStatus) {
        this.supplyBillStatus = supplyBillStatus;
    }

    public String getSupplyBillUser() {
        return supplyBillUser;
    }

    public void setSupplyBillUser(String supplyBillUser) {
        this.supplyBillUser = supplyBillUser == null ? null : supplyBillUser.trim();
    }

    public String getBreakfastNum() {
        return breakfastNum;
    }

    public void setBreakfastNum(String breakfastNum) {
        this.breakfastNum = breakfastNum == null ? null : breakfastNum.trim();
    }

	public BigDecimal getAgentCommission() {
		return agentCommission;
	}

	public void setAgentCommission(BigDecimal agentCommission) {
		this.agentCommission = agentCommission;
	}

	public BigDecimal getSupplyCommission() {
		return supplyCommission;
	}

	public void setSupplyCommission(BigDecimal supplyCommission) {
		this.supplyCommission = supplyCommission;
	}
}