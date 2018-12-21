package com.fangcang.order.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "o_order_request")
public class OrderRequestDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 处理结果：0:未处理，1同意申请，2拒绝申请
     */
    @Column(name = "handle_result")
    private Byte handleResult;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

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
     * 客人名称，多个以逗号分割
     */
    @Column(name = "guest_names")
    private String guestNames;

    /**
     * 价格计划id
     */
    @Column(name = "rateplan_id")
    private Integer rateplanId;

    /**
     * 客户单号
     */
    @Column(name = "customer_order_code")
    private String customerOrderCode;

    /**
     * 订单申请类型，1取消单申请，2修改单申请
     */
    @Column(name = "request_type")
    private Byte requestType;

    /**
     * OTA订单处理结果，0：未处理，1：已处理
     */
    @Column(name = "ota_handle_result")
    private Byte otaHandleResult;

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
     * 获取处理结果：0:未处理，1同意申请，2拒绝申请
     *
     * @return handle_result - 处理结果：0:未处理，1同意申请，2拒绝申请
     */
    public Byte getHandleResult() {
        return handleResult;
    }

    /**
     * 设置处理结果：0:未处理，1同意申请，2拒绝申请
     *
     * @param handleResult 处理结果：0:未处理，1同意申请，2拒绝申请
     */
    public void setHandleResult(Byte handleResult) {
        this.handleResult = handleResult;
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
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改人
     *
     * @return modifier - 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改人
     *
     * @param modifier 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
     * 获取客人名称，多个以逗号分割
     *
     * @return guest_names - 客人名称，多个以逗号分割
     */
    public String getGuestNames() {
        return guestNames;
    }

    /**
     * 设置客人名称，多个以逗号分割
     *
     * @param guestNames 客人名称，多个以逗号分割
     */
    public void setGuestNames(String guestNames) {
        this.guestNames = guestNames;
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
     * 获取订单申请类型，1取消单申请，2修改单申请
     *
     * @return request_type - 订单申请类型，1取消单申请，2修改单申请
     */
    public Byte getRequestType() {
        return requestType;
    }

    /**
     * 设置订单申请类型，1取消单申请，2修改单申请
     *
     * @param requestType 订单申请类型，1取消单申请，2修改单申请
     */
    public void setRequestType(Byte requestType) {
        this.requestType = requestType;
    }

    /**
     * 获取OTA订单处理结果，0：未处理，1：已处理
     *
     * @return ota_handle_result - OTA订单处理结果，0：未处理，1：已处理
     */
    public Byte getOtaHandleResult() {
        return otaHandleResult;
    }

    /**
     * 设置OTA订单处理结果，0：未处理，1：已处理
     *
     * @param otaHandleResult OTA订单处理结果，0：未处理，1：已处理
     */
    public void setOtaHandleResult(Byte otaHandleResult) {
        this.otaHandleResult = otaHandleResult;
    }
}