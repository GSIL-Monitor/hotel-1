package com.fangcang.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "o_order_check_detail")
public class OrderCheckDetailDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "oder_id")
    private Integer oderId;

    /**
     * 供货单id
     */
    @Column(name = "supply_order_id")
    private Integer supplyOrderId;

    /**
     * 对象类型为1， 此为供货产品id；对象类型为2，此为供货附加项id
     */
    @Column(name = "supply_product_id")
    private Integer supplyProductId;

    /**
     * 日期段，开始时间
     */
    @Column(name = "checkin_date")
    private Date checkinDate;

    /**
     * 日期段，结束时间
     */
    @Column(name = "checkout_date")
    private Date checkoutDate;

    /**
     * 间数
     */
    @Column(name = "room_num")
    private Integer roomNum;

    /**
     * 底价
     */
    @Column(name = "base_price")
    private BigDecimal basePrice;

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
     * 获取订单id
     *
     * @return oder_id - 订单id
     */
    public Integer getOderId() {
        return oderId;
    }

    /**
     * 设置订单id
     *
     * @param oderId 订单id
     */
    public void setOderId(Integer oderId) {
        this.oderId = oderId;
    }

    /**
     * 获取供货单id
     *
     * @return supply_order_id - 供货单id
     */
    public Integer getSupplyOrderId() {
        return supplyOrderId;
    }

    /**
     * 设置供货单id
     *
     * @param supplyOrderId 供货单id
     */
    public void setSupplyOrderId(Integer supplyOrderId) {
        this.supplyOrderId = supplyOrderId;
    }

    /**
     * 获取对象类型为1， 此为供货产品id；对象类型为2，此为供货附加项id
     *
     * @return supply_product_id - 对象类型为1， 此为供货产品id；对象类型为2，此为供货附加项id
     */
    public Integer getSupplyProductId() {
        return supplyProductId;
    }

    /**
     * 设置对象类型为1， 此为供货产品id；对象类型为2，此为供货附加项id
     *
     * @param supplyProductId 对象类型为1， 此为供货产品id；对象类型为2，此为供货附加项id
     */
    public void setSupplyProductId(Integer supplyProductId) {
        this.supplyProductId = supplyProductId;
    }

    /**
     * 获取日期段，开始时间
     *
     * @return checkin_date - 日期段，开始时间
     */
    public Date getCheckinDate() {
        return checkinDate;
    }

    /**
     * 设置日期段，开始时间
     *
     * @param checkinDate 日期段，开始时间
     */
    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    /**
     * 获取日期段，结束时间
     *
     * @return checkout_date - 日期段，结束时间
     */
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * 设置日期段，结束时间
     *
     * @param checkoutDate 日期段，结束时间
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
     * 获取底价
     *
     * @return base_price - 底价
     */
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    /**
     * 设置底价
     *
     * @param basePrice 底价
     */
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
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