package com.travel.common.dto.product.response;

import com.travel.common.dto.GenericQueryDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   2018/1/26.
 */
public class RoomControlResponseDTO extends GenericQueryDTO {
    private String hotelName;
    private Long hotelId;
    /**
     * 该酒店是否允许预留配额
     * 1-允许
     * 0-不允许
     */
    private Integer preHoldQuota;

    private String roomTypeName;
    private Long roomTypeId;

    private Long pricePlanId;
    private String pricePlanName;

    /**
     * 配额账号ID
     */
    private Long accouontId;
    /**
     * 配额主键
     */
    private Long quotaId;
    /**
     * 价格主键
     */
    private Long priceId;

    private Date saleDate;
    private String saleDateStr;

    /**
     * 包房类型
     */
    private String quotaType;
    /**
     * 包房类型名称
     */
    private String quotaTypeName;

    private Integer isActive;
    private String isActiveName;

    private BigDecimal basePrice;
    private String baseCurrency;

    private BigDecimal saleBPrice;
    private String saleBCurrency;

    /**
     * 携程价、淘宝价是否在第一屏展示出来
     */
    private BigDecimal saleCtripPrice;
    private String saleCtripCurrency;
    private BigDecimal saleTaobaoPrice;
    private String saleTaobaoCurrency;

    private BigDecimal saleCPrice;
    private String saleCCurrency;

    /**
     * 在售配额
     */
    private Integer quotaNum;
    /**
     * 已用配额
     */
    private Integer usedQuotaNum;
    /**
     * 总配额
     */
    private Integer allQuotaNum;

    /**
     * 扣留配额
     */
    private Integer detQuotaNum;

    /**
     * （分销商）保留配额
     */
    private Integer reserveQuotaNum;

    /**
     * 分销商保留时长（单位分钟）
     */
    private Integer reserveMinute;

    /**
     * 昨天之前的数据不能修改。以此来判断
     * 1-可以改
     * 0-不可以改
     */
    private int canEdit = 1;

    public int getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(int canEdit) {
        this.canEdit = canEdit;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getPreHoldQuota() {
        return preHoldQuota;
    }

    public void setPreHoldQuota(Integer preHoldQuota) {
        this.preHoldQuota = preHoldQuota;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public BigDecimal getSaleBPrice() {
        return saleBPrice;
    }

    public void setSaleBPrice(BigDecimal saleBPrice) {
        this.saleBPrice = saleBPrice;
    }

    public String getSaleBCurrency() {
        return saleBCurrency;
    }

    public void setSaleBCurrency(String saleBCurrency) {
        this.saleBCurrency = saleBCurrency;
    }

    public BigDecimal getSaleCtripPrice() {
        return saleCtripPrice;
    }

    public void setSaleCtripPrice(BigDecimal saleCtripPrice) {
        this.saleCtripPrice = saleCtripPrice;
    }

    public String getSaleCtripCurrency() {
        return saleCtripCurrency;
    }

    public void setSaleCtripCurrency(String saleCtripCurrency) {
        this.saleCtripCurrency = saleCtripCurrency;
    }

    public BigDecimal getSaleTaobaoPrice() {
        return saleTaobaoPrice;
    }

    public void setSaleTaobaoPrice(BigDecimal saleTaobaoPrice) {
        this.saleTaobaoPrice = saleTaobaoPrice;
    }

    public String getSaleTaobaoCurrency() {
        return saleTaobaoCurrency;
    }

    public void setSaleTaobaoCurrency(String saleTaobaoCurrency) {
        this.saleTaobaoCurrency = saleTaobaoCurrency;
    }

    public BigDecimal getSaleCPrice() {
        return saleCPrice;
    }

    public void setSaleCPrice(BigDecimal saleCPrice) {
        this.saleCPrice = saleCPrice;
    }

    public String getSaleCCurrency() {
        return saleCCurrency;
    }

    public void setSaleCCurrency(String saleCCurrency) {
        this.saleCCurrency = saleCCurrency;
    }

    public Integer getQuotaNum() {
        return quotaNum;
    }

    public void setQuotaNum(Integer quotaNum) {
        this.quotaNum = quotaNum;
    }

    public Integer getUsedQuotaNum() {
        return usedQuotaNum;
    }

    public void setUsedQuotaNum(Integer usedQuotaNum) {
        this.usedQuotaNum = usedQuotaNum;
    }

    public Integer getAllQuotaNum() {
        return allQuotaNum;
    }

    public void setAllQuotaNum(Integer allQuotaNum) {
        this.allQuotaNum = allQuotaNum;
    }

    public Integer getDetQuotaNum() {
        return detQuotaNum;
    }

    public void setDetQuotaNum(Integer detQuotaNum) {
        this.detQuotaNum = detQuotaNum;
    }

    public Integer getReserveQuotaNum() {
        return reserveQuotaNum;
    }

    public void setReserveQuotaNum(Integer reserveQuotaNum) {
        this.reserveQuotaNum = reserveQuotaNum;
    }

    public Integer getReserveMinute() {
        return reserveMinute;
    }

    public void setReserveMinute(Integer reserveMinute) {
        this.reserveMinute = reserveMinute;
    }

    public Long getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId) {
        this.pricePlanId = pricePlanId;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public Long getAccouontId() {
        return accouontId;
    }

    public void setAccouontId(Long accouontId) {
        this.accouontId = accouontId;
    }

    public Long getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public String getQuotaTypeName() {
        return quotaTypeName;
    }

    public void setQuotaTypeName(String quotaTypeName) {
        this.quotaTypeName = quotaTypeName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getIsActiveName() {
        return isActiveName;
    }

    public void setIsActiveName(String isActiveName) {
        this.isActiveName = isActiveName;
    }

    public String getSaleDateStr() {
        return saleDateStr;
    }

    public void setSaleDateStr(String saleDateStr) {
        this.saleDateStr = saleDateStr;
    }
}
