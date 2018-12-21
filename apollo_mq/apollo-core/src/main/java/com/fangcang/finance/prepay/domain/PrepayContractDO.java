package com.fangcang.finance.prepay.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_prepay_contract")
public class PrepayContractDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构id
     */
    @Column(name = "supply_id")
    private Integer supplyId;

    /**
     * 合同有效开始日期
     */
    @Column(name = "valid_begin_date")
    private Date validBeginDate;

    /**
     * 合同有效结束日期
     */
    @Column(name = "valid_end_date")
    private Date validEndDate;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    /**
     * 目标间夜总数
     */
    @Column(name = "target_room_night_sum")
    private Integer targetRoomNightSum;

    /**
     * 合同金额
     */
    @Column(name = "contract_amount")
    private BigDecimal contractAmount;

    /**
     * 预付款
     */
    @Column(name = "prepay_amount")
    private BigDecimal prepayAmount;

    /**
     * 已结算金额
     */
    @Column(name = "settlement_amount")
    private BigDecimal settlementAmount;

    /**
     * 已完成间夜
     */
    @Column(name = "done_room_night")
    private Integer doneRoomNight;

    /**
     * 未完成间夜
     */
    @Column(name = "un_done_room_night")
    private Integer unDoneRoomNight;

    /**
     * 币种
     */
    private String currency;

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
     * 获取机构id
     *
     * @return supply_id - 机构id
     */
    public Integer getSupplyId() {
        return supplyId;
    }

    /**
     * 设置机构id
     *
     * @param supplyId 机构id
     */
    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    /**
     * 获取合同有效开始日期
     *
     * @return valid_begin_date - 合同有效开始日期
     */
    public Date getValidBeginDate() {
        return validBeginDate;
    }

    /**
     * 设置合同有效开始日期
     *
     * @param validBeginDate 合同有效开始日期
     */
    public void setValidBeginDate(Date validBeginDate) {
        this.validBeginDate = validBeginDate;
    }

    /**
     * 获取合同有效结束日期
     *
     * @return valid_end_date - 合同有效结束日期
     */
    public Date getValidEndDate() {
        return validEndDate;
    }

    /**
     * 设置合同有效结束日期
     *
     * @param validEndDate 合同有效结束日期
     */
    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    /**
     * @return hotel_id
     */
    public Integer getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId
     */
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return hotel_name
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * 获取目标间夜总数
     *
     * @return target_room_night_sum - 目标间夜总数
     */
    public Integer getTargetRoomNightSum() {
        return targetRoomNightSum;
    }

    /**
     * 设置目标间夜总数
     *
     * @param targetRoomNightSum 目标间夜总数
     */
    public void setTargetRoomNightSum(Integer targetRoomNightSum) {
        this.targetRoomNightSum = targetRoomNightSum;
    }

    /**
     * 获取合同金额
     *
     * @return contract_amount - 合同金额
     */
    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    /**
     * 设置合同金额
     *
     * @param contractAmount 合同金额
     */
    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * 获取预付款
     *
     * @return prepay_amount - 预付款
     */
    public BigDecimal getPrepayAmount() {
        return prepayAmount;
    }

    /**
     * 设置预付款
     *
     * @param prepayAmount 预付款
     */
    public void setPrepayAmount(BigDecimal prepayAmount) {
        this.prepayAmount = prepayAmount;
    }

    /**
     * 获取已结算金额
     *
     * @return settlement_amount - 已结算金额
     */
    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * 设置已结算金额
     *
     * @param settlementAmount 已结算金额
     */
    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    /**
     * 获取已完成间夜
     *
     * @return done_room_night - 已完成间夜
     */
    public Integer getDoneRoomNight() {
        return doneRoomNight;
    }

    /**
     * 设置已完成间夜
     *
     * @param doneRoomNight 已完成间夜
     */
    public void setDoneRoomNight(Integer doneRoomNight) {
        this.doneRoomNight = doneRoomNight;
    }

    /**
     * 获取未完成间夜
     *
     * @return un_done_room_night - 未完成间夜
     */
    public Integer getUnDoneRoomNight() {
        return unDoneRoomNight;
    }

    /**
     * 设置未完成间夜
     *
     * @param unDoneRoomNight 未完成间夜
     */
    public void setUnDoneRoomNight(Integer unDoneRoomNight) {
        this.unDoneRoomNight = unDoneRoomNight;
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