package com.fangcang.finance.bill.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_fin_agent_bill_order_item")
public class AgentBillOrderItemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bill_order_id")
    private Integer billOrderId;

    @Column(name = "bill_id")
    private Integer billId;

    /**
     * 明细类型
     */
    @Column(name = "item_type")
    private Integer itemType;

    /**
     * 明细名称
     */
    @Column(name = "item_name")
    private String itemName;

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
     * 晚数
     */
    @Column(name = "night_num")
    private Integer nightNum;

    /**
     * 间数
     */
    @Column(name = "room_num")
    private BigDecimal roomNum;

    /**
     * 币种
     */
    private String currency;

    /**
     * 单价
     */
    private BigDecimal price;

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
     * @return bill_order_id
     */
    public Integer getBillOrderId() {
        return billOrderId;
    }

    /**
     * @param billOrderId
     */
    public void setBillOrderId(Integer billOrderId) {
        this.billOrderId = billOrderId;
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
     * 获取明细类型
     *
     * @return item_type - 明细类型
     */
    public Integer getItemType() {
        return itemType;
    }

    /**
     * 设置明细类型
     *
     * @param itemType 明细类型
     */
    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    /**
     * 获取明细名称
     *
     * @return item_name - 明细名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置明细名称
     *
     * @param itemName 明细名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
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
     * 获取晚数
     *
     * @return night_num - 晚数
     */
    public Integer getNightNum() {
        return nightNum;
    }

    /**
     * 设置晚数
     *
     * @param nightNum 晚数
     */
    public void setNightNum(Integer nightNum) {
        this.nightNum = nightNum;
    }

    /**
     * 获取间数
     *
     * @return room_num - 间数
     */
    public BigDecimal getRoomNum() {
        return roomNum;
    }

    /**
     * 设置间数
     *
     * @param roomNum 间数
     */
    public void setRoomNum(BigDecimal roomNum) {
        this.roomNum = roomNum;
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
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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