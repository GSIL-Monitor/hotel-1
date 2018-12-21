package com.fangcang.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "o_supply_product")
public class SupplyProductDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供货单ID
     */
    @Column(name = "supply_order_id")
    private Integer supplyOrderId;

    /**
     * 房型id
     */
    @Column(name = "room_type_id")
    private Integer roomTypeId;

    /**
     * 房型名称
     */
    @Column(name = "room_type_name")
    private String roomTypeName;

    /**
     * 价格计划id
     */
    @Column(name = "rateplan_id")
    private Integer rateplanId;

    /**
     * 价格计划名称
     */
    @Column(name = "rateplan_name")
    private String rateplanName;

    /**
     * 采购类型(1合约房,2配额房,3包房一,4包房二)
     */
    @Column(name = "quota_type")
    private Byte quotaType;

    /**
     * 床型，0:单床，1：大床，2：双床，3：三床，4：四床，多个床型用逗号隔开
     */
    private String bedtype;

    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    @Column(name = "breakfast_type")
    private Byte breakfastType;

    /**
     * 入住日期
     */
    @Column(name = "checkin_date")
    private Date checkinDate;

    /**
     * 离店日期
     */
    @Column(name = "checkout_date")
    private Date checkoutDate;

    /**
     * 间数
     */
    @Column(name = "room_num")
    private Integer roomNum;

    /**
     * 总售价
     */
    @Column(name = "sale_price_sum")
    private BigDecimal salePriceSum;

    /**
     * 总底价
     */
    @Column(name = "base_price_sum")
    private BigDecimal basePriceSum;

    @Column(name = "quota_account_id")
    private Integer quotaAccountId;

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
     * 获取供货单ID
     *
     * @return supply_order_id - 供货单ID
     */
    public Integer getSupplyOrderId() {
        return supplyOrderId;
    }

    /**
     * 设置供货单ID
     *
     * @param supplyOrderId 供货单ID
     */
    public void setSupplyOrderId(Integer supplyOrderId) {
        this.supplyOrderId = supplyOrderId;
    }

    /**
     * 获取房型id
     *
     * @return room_type_id - 房型id
     */
    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * 设置房型id
     *
     * @param roomTypeId 房型id
     */
    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
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
     * 获取价格计划id
     *
     * @return rateplan_id - 价格计划id
     */
    public Integer getRateplanId() {
        return rateplanId;
    }

    /**
     * 设置价格计划id
     *
     * @param rateplanId 价格计划id
     */
    public void setRateplanId(Integer rateplanId) {
        this.rateplanId = rateplanId;
    }

    /**
     * 获取价格计划名称
     *
     * @return rateplan_name - 价格计划名称
     */
    public String getRateplanName() {
        return rateplanName;
    }

    /**
     * 设置价格计划名称
     *
     * @param rateplanName 价格计划名称
     */
    public void setRateplanName(String rateplanName) {
        this.rateplanName = rateplanName;
    }

    /**
     * 获取采购类型(1合约房,2配额房,3包房一,4包房二)
     *
     * @return quota_type - 采购类型(1合约房,2配额房,3包房一,4包房二)
     */
    public Byte getQuotaType() {
        return quotaType;
    }

    /**
     * 设置采购类型(1合约房,2配额房,3包房一,4包房二)
     *
     * @param quotaType 采购类型(1合约房,2配额房,3包房一,4包房二)
     */
    public void setQuotaType(Byte quotaType) {
        this.quotaType = quotaType;
    }

    /**
     * 获取床型，0:单床，1：大床，2：双床，3：三床，4：四床，多个床型用逗号隔开
     *
     * @return bedtype - 床型，0:单床，1：大床，2：双床，3：三床，4：四床，多个床型用逗号隔开
     */
    public String getBedtype() {
        return bedtype;
    }

    /**
     * 设置床型，0:单床，1：大床，2：双床，3：三床，4：四床，多个床型用逗号隔开
     *
     * @param bedtype 床型，0:单床，1：大床，2：双床，3：三床，4：四床，多个床型用逗号隔开
     */
    public void setBedtype(String bedtype) {
        this.bedtype = bedtype;
    }

    /**
     * 获取早餐类型，1，无早  2：单早，3：双早，4：人头早
     *
     * @return breakfast_type - 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    public Byte getBreakfastType() {
        return breakfastType;
    }

    /**
     * 设置早餐类型，1，无早  2：单早，3：双早，4：人头早
     *
     * @param breakfastType 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    public void setBreakfastType(Byte breakfastType) {
        this.breakfastType = breakfastType;
    }

    /**
     * 获取入住日期
     *
     * @return checkin_date - 入住日期
     */
    public Date getCheckinDate() {
        return checkinDate;
    }

    /**
     * 设置入住日期
     *
     * @param checkinDate 入住日期
     */
    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * 获取离店日期
     *
     * @return checkout_date - 离店日期
     */
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * 设置离店日期
     *
     * @param checkoutDate 离店日期
     */
    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * 获取间数
     *
     * @return room_num - 间数
     */
    public Integer getRoomNum() {
        return roomNum;
    }

    /**
     * 设置间数
     *
     * @param roomNum 间数
     */
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
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
     * 获取总底价
     *
     * @return base_price_sum - 总底价
     */
    public BigDecimal getBasePriceSum() {
        return basePriceSum;
    }

    /**
     * 设置总底价
     *
     * @param basePriceSum 总底价
     */
    public void setBasePriceSum(BigDecimal basePriceSum) {
        this.basePriceSum = basePriceSum;
    }

    /**
     * @return quota_account_id
     */
    public Integer getQuotaAccountId() {
        return quotaAccountId;
    }

    /**
     * @param quotaAccountId
     */
    public void setQuotaAccountId(Integer quotaAccountId) {
        this.quotaAccountId = quotaAccountId;
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