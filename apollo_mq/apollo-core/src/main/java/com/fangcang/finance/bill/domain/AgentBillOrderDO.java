package com.fangcang.finance.bill.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_fin_agent_bill_order")
public class AgentBillOrderDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 酒店名称
     */
    @Column(name = "hotel_name")
    private String hotelName;

    /**
     * 房型名称
     */
    @Column(name = "room_type_name")
    private String roomTypeName;

    /**
     * 价格计划名称
     */
    @Column(name = "rate_plan_name")
    private String ratePlanName;

    /**
     * 入住人
     */
    private String guest;

    /**
     * 入住日期
     */
    @Column(name = "check_in_date")
    private Date checkInDate;

    /**
     * 离店日期
     */
    @Column(name = "check_out_date")
    private Date checkOutDate;

    /**
     * 间夜
     */
    @Column(name = "room_night")
    private Integer roomNight;

    /**
     * 币种
     */
    private String currency;

    /**
     * 客户单号
     */
    @Column(name = "customer_order_code")
    private String customerOrderCode;

    /**
     * 应收
     */
    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

    /**
     * 实收
     */
    @Column(name = "paidin_amount")
    private BigDecimal paidinAmount;

    /**
     * 本次要收
     */
    @Column(name = "curr_paidin_amount")
    private String currPaidinAmount;

    /**
     * 备注
     */
    private String note;

    /**
     * 订单创建时间
     */
    @Column(name = "order_create_date")
    private Date orderCreateDate;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    @Column(name = "balance_method")
    private Integer balanceMethod;

    /**
     * 订单明细数量
     */
    @Column(name = "order_item_count")
    private Integer orderItemCount;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return bill_id
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * @param billId
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单号
     *
     * @return order_code - 订单号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单号
     *
     * @param orderCode 订单号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
     * 获取房型名称
     *
     * @return room_type_name - 房型名称
     */
    public String getRoomTypeName() {
        return roomTypeName;
    }

    /**
     * 设置房型名称
     *
     * @param roomTypeName 房型名称
     */
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    /**
     * 获取价格计划名称
     *
     * @return rate_plan_name - 价格计划名称
     */
    public String getRatePlanName() {
        return ratePlanName;
    }

    /**
     * 设置价格计划名称
     *
     * @param ratePlanName 价格计划名称
     */
    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    /**
     * 获取入住人
     *
     * @return guest - 入住人
     */
    public String getGuest() {
        return guest;
    }

    /**
     * 设置入住人
     *
     * @param guest 入住人
     */
    public void setGuest(String guest) {
        this.guest = guest;
    }

    /**
     * 获取入住日期
     *
     * @return check_in_date - 入住日期
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * 设置入住日期
     *
     * @param checkInDate 入住日期
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * 获取离店日期
     *
     * @return check_out_date - 离店日期
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * 设置离店日期
     *
     * @param checkOutDate 离店日期
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * 获取间夜
     *
     * @return room_night - 间夜
     */
    public Integer getRoomNight() {
        return roomNight;
    }

    /**
     * 设置间夜
     *
     * @param roomNight 间夜
     */
    public void setRoomNight(Integer roomNight) {
        this.roomNight = roomNight;
    }

    /**
     * 获取币种
     *
     * @return currency - 币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币种
     *
     * @param currency 币种
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取客户单号
     *
     * @return customer_order_code - 客户单号
     */
    public String getCustomerOrderCode() {
        return customerOrderCode;
    }

    /**
     * 设置客户单号
     *
     * @param customerOrderCode 客户单号
     */
    public void setCustomerOrderCode(String customerOrderCode) {
        this.customerOrderCode = customerOrderCode;
    }

    /**
     * 获取应收
     *
     * @return receivable_amount - 应收
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * 设置应收
     *
     * @param receivableAmount 应收
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    /**
     * 获取实收
     *
     * @return paidin_amount - 实收
     */
    public BigDecimal getPaidinAmount() {
        return paidinAmount;
    }

    /**
     * 设置实收
     *
     * @param paidinAmount 实收
     */
    public void setPaidinAmount(BigDecimal paidinAmount) {
        this.paidinAmount = paidinAmount;
    }

    /**
     * 获取本次要收
     *
     * @return curr_paidin_amount - 本次要收
     */
    public String getCurrPaidinAmount() {
        return currPaidinAmount;
    }

    /**
     * 设置本次要收
     *
     * @param currPaidinAmount 本次要收
     */
    public void setCurrPaidinAmount(String currPaidinAmount) {
        this.currPaidinAmount = currPaidinAmount;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取订单创建时间
     *
     * @return order_create_date - 订单创建时间
     */
    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    /**
     * 设置订单创建时间
     *
     * @param orderCreateDate 订单创建时间
     */
    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Integer getBalanceMethod() {
        return balanceMethod;
    }

    public void setBalanceMethod(Integer balanceMethod) {
        this.balanceMethod = balanceMethod;
    }

    /**
     * 获取订单明细数量
     *
     * @return order_item_count - 订单明细数量
     */
    public Integer getOrderItemCount() {
        return orderItemCount;
    }

    /**
     * 设置订单明细数量
     *
     * @param orderItemCount 订单明细数量
     */
    public void setOrderItemCount(Integer orderItemCount) {
        this.orderItemCount = orderItemCount;
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
}