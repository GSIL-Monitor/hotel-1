package com.travel.common.dto.product.request;

import com.travel.common.dto.ModifyDTO;

import java.math.BigDecimal;

/**
 *   2018/1/23.
 */
public class PriceQuotaRestrictRequestDTO extends ModifyDTO {

    private Long hotelId;
    private String hotelName;
    private Long roomTypeId;
    private String roomTypeName;
    private Long pricePlanId;
    private String pricePlanName;

    private String beginDate;
    private String endDate;

    /**
     * 配额账号ID：如果为空，表示要新建配额账号，如果不为空则表示直接使用该配额账号的房态数据
     */
    private Long accountId;

    private String  breakfastNum;

    /**
     * 平日底价
     */
    private BigDecimal basePriceWeekday;

    /**
     * 周末底价
     */
    private BigDecimal basePriceWeekend;

    /**
     * 底价币种
     */
    private String baseCurrency;

    /**
     * 直客周末价
     */
    private BigDecimal toCPriceWeekend;

    /**
     * 直客平日价
     */
    private BigDecimal toCPriceWeekday;

    /**
     * 直客价币种
     */
    private String toCCurrency;

    /**
     * 同行平日价
     */
    private BigDecimal toBPriceWeekday;
    /**
     * 同行周末价
     */
    private BigDecimal toBPriceWeekend;

    /**
     * 同行报价币种
     */
    private String toBCurrency;

    /**
     * 所有渠道售价是否相同
     * true-相同
     * false-不同
     */
    private Boolean sameSalePrice;

    /**
     * B2B渠道平日价
     */
    private BigDecimal B2BPriceWeekday;

    /**
     *B2B渠道周末价
     */
    private  BigDecimal B2BPriceWeekend;

    /**
     *  携程平日价
     */
    private BigDecimal ctripPriceWeekday;

    /**
     *携程周末价
     */
    private BigDecimal ctripPriceWeekend;

    /**
     *淘宝平日价
     */
    private BigDecimal taobaoPriceWeekday;

    /**
     *淘宝周末价
     */
    private  BigDecimal taobaoPriceWeekend;

    /**
     * 房态
     */
    private String roomState;

    /**
     * 调整什么配额
     * setQuotaNum：调整在售配额
     * setDetQuotaNum 调整扣留配额
     *
     * 调整在售配额（增加、减少），总配额需要变更（增加、减少）。
     * 调整扣留配额（增加、减少），在售配额需要变更（减少、增加）
     * （下单时）调整已用配额（增加、减少），在售配额需要变更（减少，增加）
     */
    @Deprecated
    private String setQuotaType;

    /**
     * 配额调整类型：增加，减少，设置为
     */
    @Deprecated
    private String quotaNumType;
    /**
     * 在售配额调整数量
     */
    private Integer quotaNum;

    /**
     *扣留配额调整类型
     */
    @Deprecated
    private String holdQuotaNumType;
    /***
     * 扣留配额调整数量
     */
    private  Integer holdQuotaNum;

    /**
     * 提前预订天数
     */
    private Integer bookDays;

    /**
     * 提前预订时间
     */
    private Integer bookTime;

    /**
     * 预订间数
     */
    private Integer bookRooms;

    /**
     * 连住天数
     */
    private Integer occupancyDays;

    /**
     * 批量
     */
    private Boolean batchSetPrice = true;
    private Boolean batchSetQuota = true;
    private Boolean batchSetRestrict = true;

    public Boolean getBatchSetPrice() {
        return batchSetPrice;
    }

    public void setBatchSetPrice(Boolean batchSetPrice) {
        this.batchSetPrice = batchSetPrice;
    }

    public Boolean getBatchSetQuota() {
        return batchSetQuota;
    }

    public void setBatchSetQuota(Boolean batchSetQuota) {
        this.batchSetQuota = batchSetQuota;
    }

    public Boolean getBatchSetRestrict() {
        return batchSetRestrict;
    }

    public void setBatchSetRestrict(Boolean batchSetRestrict) {
        this.batchSetRestrict = batchSetRestrict;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId) {
        this.pricePlanId = pricePlanId;
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

    public BigDecimal getBasePriceWeekday() {
        return basePriceWeekday;
    }

    public void setBasePriceWeekday(BigDecimal basePriceWeekday) {
        this.basePriceWeekday = basePriceWeekday;
    }

    public BigDecimal getBasePriceWeekend() {
        return basePriceWeekend;
    }

    public void setBasePriceWeekend(BigDecimal basePriceWeekend) {
        this.basePriceWeekend = basePriceWeekend;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public BigDecimal getToCPriceWeekend() {
        return toCPriceWeekend;
    }

    public void setToCPriceWeekend(BigDecimal toCPriceWeekend) {
        this.toCPriceWeekend = toCPriceWeekend;
    }

    public BigDecimal getToCPriceWeekday() {
        return toCPriceWeekday;
    }

    public void setToCPriceWeekday(BigDecimal toCPriceWeekday) {
        this.toCPriceWeekday = toCPriceWeekday;
    }

    public String getToCCurrency() {
        return toCCurrency;
    }

    public void setToCCurrency(String toCCurrency) {
        this.toCCurrency = toCCurrency;
    }

    public BigDecimal getToBPriceWeekday() {
        return toBPriceWeekday;
    }

    public void setToBPriceWeekday(BigDecimal toBPriceWeekday) {
        this.toBPriceWeekday = toBPriceWeekday;
    }

    public BigDecimal getToBPriceWeekend() {
        return toBPriceWeekend;
    }

    public void setToBPriceWeekend(BigDecimal toBPriceWeekend) {
        this.toBPriceWeekend = toBPriceWeekend;
    }

    public String getToBCurrency() {
        return toBCurrency;
    }

    public void setToBCurrency(String toBCurrency) {
        this.toBCurrency = toBCurrency;
    }

    public BigDecimal getB2BPriceWeekday() {
        return B2BPriceWeekday;
    }

    public void setB2BPriceWeekday(BigDecimal b2BPriceWeekday) {
        B2BPriceWeekday = b2BPriceWeekday;
    }

    public BigDecimal getB2BPriceWeekend() {
        return B2BPriceWeekend;
    }

    public void setB2BPriceWeekend(BigDecimal b2BPriceWeekend) {
        B2BPriceWeekend = b2BPriceWeekend;
    }

    public BigDecimal getCtripPriceWeekday() {
        return ctripPriceWeekday;
    }

    public void setCtripPriceWeekday(BigDecimal ctripPriceWeekday) {
        this.ctripPriceWeekday = ctripPriceWeekday;
    }

    public BigDecimal getCtripPriceWeekend() {
        return ctripPriceWeekend;
    }

    public void setCtripPriceWeekend(BigDecimal ctripPriceWeekend) {
        this.ctripPriceWeekend = ctripPriceWeekend;
    }

    public BigDecimal getTaobaoPriceWeekday() {
        return taobaoPriceWeekday;
    }

    public void setTaobaoPriceWeekday(BigDecimal taobaoPriceWeekday) {
        this.taobaoPriceWeekday = taobaoPriceWeekday;
    }

    public BigDecimal getTaobaoPriceWeekend() {
        return taobaoPriceWeekend;
    }

    public void setTaobaoPriceWeekend(BigDecimal taobaoPriceWeekend) {
        this.taobaoPriceWeekend = taobaoPriceWeekend;
    }

    public String getRoomState() {
        return roomState;
    }

    public void setRoomState(String roomState) {
        this.roomState = roomState;
    }

    public String getQuotaNumType() {
        return quotaNumType;
    }

    public void setQuotaNumType(String quotaNumType) {
        this.quotaNumType = quotaNumType;
    }

    public Integer getQuotaNum() {
        return quotaNum;
    }

    public void setQuotaNum(Integer quotaNum) {
        this.quotaNum = quotaNum;
    }

    public String getHoldQuotaNumType() {
        return holdQuotaNumType;
    }

    public void setHoldQuotaNumType(String holdQuotaNumType) {
        this.holdQuotaNumType = holdQuotaNumType;
    }

    public Integer getHoldQuotaNum() {
        return holdQuotaNum;
    }

    public void setHoldQuotaNum(Integer holdQuotaNum) {
        this.holdQuotaNum = holdQuotaNum;
    }

    public Integer getBookDays() {
        return bookDays;
    }

    public void setBookDays(Integer bookDays) {
        this.bookDays = bookDays;
    }

    public Integer getBookTime() {
        return bookTime;
    }

    public void setBookTime(Integer bookTime) {
        this.bookTime = bookTime;
    }

    public Integer getBookRooms() {
        return bookRooms;
    }

    public void setBookRooms(Integer bookRooms) {
        this.bookRooms = bookRooms;
    }

    public Integer getOccupancyDays() {
        return occupancyDays;
    }

    public void setOccupancyDays(Integer occupancyDays) {
        this.occupancyDays = occupancyDays;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Boolean getSameSalePrice() {
        return sameSalePrice;
    }

    public void setSameSalePrice(Boolean sameSalePrice) {
        this.sameSalePrice = sameSalePrice;
    }

    public String getBreakfastNum() {
        return breakfastNum;
    }

    public void setBreakfastNum(String breakfastNum) {
        this.breakfastNum = breakfastNum;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSetQuotaType() {
        return setQuotaType;
    }

    public void setSetQuotaType(String setQuotaType) {
        this.setQuotaType = setQuotaType;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }
}
