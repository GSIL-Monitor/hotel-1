package com.fangcang.finance.invoice.domain;

import com.fangcang.common.util.excel.ExcelColumn;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_fin_supply_invoice_item")
public class SupplyInvoiceItemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "invoice_id")
    private Integer invoiceId;

    /**
     * 订单号
     */
    @Column(name = "business_code")
    @ExcelColumn(name="供货单号",orderNum = "0")
    private String businessCode;

    /**
     * 酒店名称
     */
    @Column(name = "hotel_name")
    @ExcelColumn(name="酒店名称",orderNum = "1")
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
    @ExcelColumn(name="入住人",orderNum = "4")
    private String guest;

    /**
     * 入住日期
     */
    @Column(name = "check_in_date")
    @ExcelColumn(name="入住日期",orderNum = "2")
    private Date checkInDate;

    /**
     * 离店日期
     */
    @Column(name = "check_out_date")
    @ExcelColumn(name="离店日期",orderNum = "3")
    private Date checkOutDate;

    /**
     * 间夜
     */
    @Column(name = "room_night")
    private Integer roomNight;

    /**
     * 订单创建时间
     */
    @Column(name = "order_create_date")
    private Date orderCreateDate;

    /**
     * 币种
     */
    private String currency;

    /**
     * 应收
     */
    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

    /**
     * 开票金额
     */
    @Column(name = "invoice_amount")
    private BigDecimal invoiceAmount;

    /**
     * 账单编码
     */
    @Column(name = "bill_code")
    private String billCode;

    /**
     * 账单名称
     */
    @Column(name = "bill_name")
    private String billName;

    /**
     * 开始日期
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 结束日期
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 账单创建时间
     */
    @Column(name = "bill_create_date")
    private Date billCreateDate;

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
     * @return invoice_id
     */
    public Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * @param invoiceId
     */
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
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
     * 获取开票金额
     *
     * @return invoice_amount - 开票金额
     */
    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     * 设置开票金额
     *
     * @param invoiceAmount 开票金额
     */
    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    /**
     * 获取账单编码
     *
     * @return bill_code - 账单编码
     */
    public String getBillCode() {
        return billCode;
    }

    /**
     * 设置账单编码
     *
     * @param billCode 账单编码
     */
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    /**
     * 获取账单名称
     *
     * @return bill_name - 账单名称
     */
    public String getBillName() {
        return billName;
    }

    /**
     * 设置账单名称
     *
     * @param billName 账单名称
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }

    /**
     * 获取开始日期
     *
     * @return begin_date - 开始日期
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置开始日期
     *
     * @param beginDate 开始日期
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束日期
     *
     * @return end_date - 结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束日期
     *
     * @param endDate 结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取账单创建时间
     *
     * @return bill_create_date - 账单创建时间
     */
    public Date getBillCreateDate() {
        return billCreateDate;
    }

    /**
     * 设置账单创建时间
     *
     * @param billCreateDate 账单创建时间
     */
    public void setBillCreateDate(Date billCreateDate) {
        this.billCreateDate = billCreateDate;
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