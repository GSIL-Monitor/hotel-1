package com.fangcang.order.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "o_supply_order")
public class SupplyOrderDO {
    /**
     * 供货单信息
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 供货单编码
     */
    @Column(name = "supply_order_code")
    private String supplyOrderCode;

    /**
     * 供货单总价
     */
    @Column(name = "supply_sum")
    private BigDecimal supplySum;

    /**
     * 底价币种
     */
    @Column(name = "base_currency")
    private String baseCurrency;

    /**
     * 供应商编码
     */
    @Column(name = "supply_code")
    private String supplyCode;

    /**
     * 供应商名称
     */
    @Column(name = "supply_name")
    private String supplyName;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    @Column(name = "balance_method")
    private Byte balanceMethod;

    /**
     * 供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    @Column(name = "supply_status")
    private Byte supplyStatus;

    /**
     * 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    /**
     * 产品供应方式， 1商家自签，2系统直连
     */
    @Column(name = "supply_way")
    private Byte supplyWay;

    /**
     * 应付金额
     */
    @Column(name = "payable_amount")
    private BigDecimal payableAmount;

    /**
     * 酒店id
     */
    @Column(name = "hotel_id")
    private Integer hotelId;

    /**
     * 酒店名称
     */
    @Column(name = "hotel_name")
    private String hotelName;

    /**
     * 供应商确认号码
     */
    @Column(name = "confirm_no")
    private String confirmNo;

    /**
     * 确认人名称
     */
    @Column(name = "confirm_name")
    private String confirmName;

    /**
     * 拒绝原因：1变价，2满房，3，其他自定义
     */
    @Column(name = "refuse_reason")
    private String refuseReason;

    /**
     * 业务经理
     */
    @Column(name = "merchant_bm")
    private String merchantBm;

    /**
     * 产品经理
     */
    @Column(name = "merchant_pm")
    private String merchantPm;

    /**
     * 运营员
     */
    @Column(name = "merchant_op")
    private String merchantOp;

    /**
     * 总售价
     */
    @Column(name = "sale_price_sum")
    private BigDecimal salePriceSum;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 产品币种转商家本币汇率
     */
    private BigDecimal rate;

    /**
     * 第一个产品的入住日期
     */
    @Column(name = "checkin_date")
    private Date checkinDate;

    /**
     * 第一个产品的离店日期
     */
    @Column(name = "checkout_date")
    private Date checkoutDate;

    /**
     * 间数，团房取第一个产品间数
     */
    @Column(name = "room_num")
    private Integer roomNum;

    /**
     * 房型名称，团房取第一个房型名称
     */
    @Column(name = "room_type_name")
    private String roomTypeName;

    /**
     * 价格计划名称，团房取第一个产品名称
     */
    @Column(name = "rateplan_name")
    private String rateplanName;

    /**
     * 异常金额
     */
    @Column(name = "exception_amount")
    private BigDecimal exceptionAmount;

    /**
     * 获取供货单信息
     *
     * @return id - 供货单信息
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置供货单信息
     *
     * @param id 供货单信息
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取供货单编码
     *
     * @return supply_order_code - 供货单编码
     */
    public String getSupplyOrderCode() {
        return supplyOrderCode;
    }

    /**
     * 设置供货单编码
     *
     * @param supplyOrderCode 供货单编码
     */
    public void setSupplyOrderCode(String supplyOrderCode) {
        this.supplyOrderCode = supplyOrderCode;
    }

    /**
     * 获取供货单总价
     *
     * @return supply_sum - 供货单总价
     */
    public BigDecimal getSupplySum() {
        return supplySum;
    }

    /**
     * 设置供货单总价
     *
     * @param supplySum 供货单总价
     */
    public void setSupplySum(BigDecimal supplySum) {
        this.supplySum = supplySum;
    }

    /**
     * 获取底价币种
     *
     * @return base_currency - 底价币种
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * 设置底价币种
     *
     * @param baseCurrency 底价币种
     */
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    /**
     * 获取供应商编码
     *
     * @return supply_code - 供应商编码
     */
    public String getSupplyCode() {
        return supplyCode;
    }

    /**
     * 设置供应商编码
     *
     * @param supplyCode 供应商编码
     */
    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    /**
     * 获取供应商名称
     *
     * @return supply_name - 供应商名称
     */
    public String getSupplyName() {
        return supplyName;
    }

    /**
     * 设置供应商名称
     *
     * @param supplyName 供应商名称
     */
    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    /**
     * 获取结算方式：1月结 2半月结 3周结 4单结 5日结
     *
     * @return balance_method - 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    public Byte getBalanceMethod() {
        return balanceMethod;
    }

    /**
     * 设置结算方式：1月结 2半月结 3周结 4单结 5日结
     *
     * @param balanceMethod 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    public void setBalanceMethod(Byte balanceMethod) {
        this.balanceMethod = balanceMethod;
    }

    /**
     * 获取供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     *
     * @return supply_status - 供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    public Byte getSupplyStatus() {
        return supplyStatus;
    }

    /**
     * 设置供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     *
     * @param supplyStatus 供货单状态，1：未发单，2：已发单待确认，3：已确认，4：不确认
     */
    public void setSupplyStatus(Byte supplyStatus) {
        this.supplyStatus = supplyStatus;
    }

    /**
     * 获取支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     *
     * @return pay_status - 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     *
     * @param payStatus 支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账，5：已退款
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取产品供应方式， 1商家自签，2系统直连
     *
     * @return supply_way - 产品供应方式， 1商家自签，2系统直连
     */
    public Byte getSupplyWay() {
        return supplyWay;
    }

    /**
     * 设置产品供应方式， 1商家自签，2系统直连
     *
     * @param supplyWay 产品供应方式， 1商家自签，2系统直连
     */
    public void setSupplyWay(Byte supplyWay) {
        this.supplyWay = supplyWay;
    }

    /**
     * 获取应付金额
     *
     * @return payable_amount - 应付金额
     */
    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    /**
     * 设置应付金额
     *
     * @param payableAmount 应付金额
     */
    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    /**
     * 获取酒店id
     *
     * @return hotel_id - 酒店id
     */
    public Integer getHotelId() {
        return hotelId;
    }

    /**
     * 设置酒店id
     *
     * @param hotelId 酒店id
     */
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * 获取酒店名称
     *
     * @return hotel_name - 酒店名称
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * 设置酒店名称
     *
     * @param hotelName 酒店名称
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * 获取供应商确认号码
     *
     * @return confirm_no - 供应商确认号码
     */
    public String getConfirmNo() {
        return confirmNo;
    }

    /**
     * 设置供应商确认号码
     *
     * @param confirmNo 供应商确认号码
     */
    public void setConfirmNo(String confirmNo) {
        this.confirmNo = confirmNo;
    }

    /**
     * 获取确认人名称
     *
     * @return confirm_name - 确认人名称
     */
    public String getConfirmName() {
        return confirmName;
    }

    /**
     * 设置确认人名称
     *
     * @param confirmName 确认人名称
     */
    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

    /**
     * 获取拒绝原因：1变价，2满房，3，其他自定义
     *
     * @return refuse_reason - 拒绝原因：1变价，2满房，3，其他自定义
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * 设置拒绝原因：1变价，2满房，3，其他自定义
     *
     * @param refuseReason 拒绝原因：1变价，2满房，3，其他自定义
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    /**
     * 获取业务经理
     *
     * @return merchant_bm - 业务经理
     */
    public String getMerchantBm() {
        return merchantBm;
    }

    /**
     * 设置业务经理
     *
     * @param merchantBm 业务经理
     */
    public void setMerchantBm(String merchantBm) {
        this.merchantBm = merchantBm;
    }

    /**
     * 获取产品经理
     *
     * @return merchant_pm - 产品经理
     */
    public String getMerchantPm() {
        return merchantPm;
    }

    /**
     * 设置产品经理
     *
     * @param merchantPm 产品经理
     */
    public void setMerchantPm(String merchantPm) {
        this.merchantPm = merchantPm;
    }

    /**
     * 获取总售价
     *
     * @return sale_price_sum - 总售价
     */
    public BigDecimal getSalePriceSum() {
        return salePriceSum;
    }

    /**
     * 设置总售价
     *
     * @param salePriceSum 总售价
     */
    public void setSalePriceSum(BigDecimal salePriceSum) {
        this.salePriceSum = salePriceSum;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取产品币种转商家本币汇率
     *
     * @return rate - 产品币种转商家本币汇率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置产品币种转商家本币汇率
     *
     * @param rate 产品币种转商家本币汇率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取第一个产品的入住日期
     *
     * @return checkin_date - 第一个产品的入住日期
     */
    public Date getCheckinDate() {
        return checkinDate;
    }

    /**
     * 设置第一个产品的入住日期
     *
     * @param checkinDate 第一个产品的入住日期
     */
    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * 获取第一个产品的离店日期
     *
     * @return checkout_date - 第一个产品的离店日期
     */
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * 设置第一个产品的离店日期
     *
     * @param checkoutDate 第一个产品的离店日期
     */
    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * 获取间数，团房取第一个产品间数
     *
     * @return room_num - 间数，团房取第一个产品间数
     */
    public Integer getRoomNum() {
        return roomNum;
    }

    /**
     * 设置间数，团房取第一个产品间数
     *
     * @param roomNum 间数，团房取第一个产品间数
     */
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * 获取房型名称，团房取第一个房型名称
     *
     * @return room_type_name - 房型名称，团房取第一个房型名称
     */
    public String getRoomTypeName() {
        return roomTypeName;
    }

    /**
     * 设置房型名称，团房取第一个房型名称
     *
     * @param roomTypeName 房型名称，团房取第一个房型名称
     */
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    /**
     * 获取价格计划名称，团房取第一个产品名称
     *
     * @return rateplan_name - 价格计划名称，团房取第一个产品名称
     */
    public String getRateplanName() {
        return rateplanName;
    }

    /**
     * 设置价格计划名称，团房取第一个产品名称
     *
     * @param rateplanName 价格计划名称，团房取第一个产品名称
     */
    public void setRateplanName(String rateplanName) {
        this.rateplanName = rateplanName;
    }

    public BigDecimal getExceptionAmount() {
        return exceptionAmount;
    }

    public void setExceptionAmount(BigDecimal exceptionAmount) {
        this.exceptionAmount = exceptionAmount;
    }

    public String getMerchantOp() {
        return merchantOp;
    }

    public void setMerchantOp(String merchantOp) {
        this.merchantOp = merchantOp;
    }
}