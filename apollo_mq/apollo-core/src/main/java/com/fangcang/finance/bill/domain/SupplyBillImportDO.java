package com.fangcang.finance.bill.domain;

import com.fangcang.common.util.excel.ExcelColumn;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_fin_supply_bill_import")
public class SupplyBillImportDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 导入批次号
     */
    @Column(name = "import_no")
    private Integer importNo;

    /**
     * 账单id
     */
    @Column(name = "bill_id")
    private Integer billId;

    /**
     * 确认号
     */
    @Column(name = "confirm_no")
    @ExcelColumn(name="确认号",orderNum = "5")
    private String confirmNo;

    /**
     * 酒店名称
     */
    @Column(name = "hotel_name")
    @ExcelColumn(name="酒店名称",orderNum = "0")
    private String hotelName;

    /**
     * 房型名称
     */
    @Column(name = "room_type_name")
    private String roomTypeName;

    /**
     * 价格计划
     */
    @Column(name = "rateplan_name")
    private String rateplanName;

    /**
     * 入住日期
     */
    @Column(name = "checkin_date")
    @ExcelColumn(name="入住日期",orderNum = "1")
    private String checkinDate;

    /**
     * 离店日期
     */
    @Column(name = "checkout_date")
    @ExcelColumn(name="离店日期",orderNum = "2")
    private String checkoutDate;

    /**
     * 入住人
     */
    @Column(name = "guest")
    @ExcelColumn(name="入住人",orderNum = "3")
    private String guest;

    /**
     * 订单金额
     */
    @Column(name = "order_sum")
    @ExcelColumn(name="订单金额",orderNum = "4")
    private BigDecimal orderSum;

    @Column(name = "order_code")
    private String orderCode;

    /**
     * 是否匹配（1已匹配、2未匹配）
     */
    @Column(name = "is_match")
    private Integer isMatch;

    @Column(name = "fail_reason")
    private String failReason;

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
     * 获取导入批次号
     *
     * @return import_no - 导入批次号
     */
    public Integer getImportNo() {
        return importNo;
    }

    /**
     * 设置导入批次号
     *
     * @param importNo 导入批次号
     */
    public void setImportNo(Integer importNo) {
        this.importNo = importNo;
    }

    /**
     * 获取账单id
     *
     * @return bill_id - 账单id
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * 设置账单id
     *
     * @param billId 账单id
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getConfirmNo() {
        return confirmNo;
    }

    public void setConfirmNo(String confirmNo) {
        this.confirmNo = confirmNo;
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

    public String getRateplanName() {
        return rateplanName;
    }

    public void setRateplanName(String rateplanName) {
        this.rateplanName = rateplanName;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    /**
     * 获取订单金额
     *
     * @return order_sum - 订单金额
     */
    public BigDecimal getOrderSum() {
        return orderSum;
    }

    /**
     * 设置订单金额
     *
     * @param orderSum 订单金额
     */
    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    /**
     * @return order_code
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * @param orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取是否匹配（1已匹配、2未匹配）
     *
     * @return is_match - 是否匹配（1已匹配、2未匹配）
     */
    public Integer getIsMatch() {
        return isMatch;
    }

    /**
     * 设置是否匹配（1已匹配、2未匹配）
     *
     * @param isMatch 是否匹配（1已匹配、2未匹配）
     */
    public void setIsMatch(Integer isMatch) {
        this.isMatch = isMatch;
    }

    /**
     * @return fail_reason
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * @param failReason
     */
    public void setFailReason(String failReason) {
        this.failReason = failReason;
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