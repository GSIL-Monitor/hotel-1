package com.fangcang.order.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "o_supply_request")
public class SupplyRequestDO {
    /**
     * 供货单请求ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供货单id
     */
    @Column(name = "supply_order_id")
    private Integer supplyOrderId;

    /**
     * 供货单类型：1：预定单，2：重发预订单，3：修改单，4：取消单
     */
    @Column(name = "supply_type")
    private Byte supplyType;

    /**
     * 发送方式：1：EBK
     */
    @Column(name = "send_type")
    private Byte sendType;

    /**
     * 发送状态，1：成功，0：失败
     */
    @Column(name = "send_status")
    private Byte sendStatus;

    /**
     * 是否显示价格，1：显示，0：不显示
     */
    @Column(name = "is_display_price")
    private Byte isDisplayPrice;

    /**
     * 本次确认类型，0：未处理 1：已确认，2：已拒绝
     */
    @Column(name = "this_confirm_type")
    private Byte thisConfirmType;

    /**
     * 本次确认号
     */
    @Column(name = "this_confirm_no")
    private String thisConfirmNo;

    /**
     * 本次确认人
     */
    @Column(name = "this_confirm_name")
    private String thisConfirmName;

    /**
     * 拒绝原因：变价，满房，其他自定义
     */
    @Column(name = "refuse_reason")
    private String refuseReason;

    /**
     * 确认或取消备注
     */
    private String note;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 获取供货单请求ID
     *
     * @return id - 供货单请求ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置供货单请求ID
     *
     * @param id 供货单请求ID
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取供货单类型：1：预定单，2：重发预订单，3：修改单，4：取消单
     *
     * @return supply_type - 供货单类型：1：预定单，2：重发预订单，3：修改单，4：取消单
     */
    public Byte getSupplyType() {
        return supplyType;
    }

    /**
     * 设置供货单类型：1：预定单，2：重发预订单，3：修改单，4：取消单
     *
     * @param supplyType 供货单类型：1：预定单，2：重发预订单，3：修改单，4：取消单
     */
    public void setSupplyType(Byte supplyType) {
        this.supplyType = supplyType;
    }

    /**
     * 获取发送方式：1：EBK
     *
     * @return send_type - 发送方式：1：EBK
     */
    public Byte getSendType() {
        return sendType;
    }

    /**
     * 设置发送方式：1：EBK
     *
     * @param sendType 发送方式：1：EBK
     */
    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    /**
     * 获取发送状态，1：成功，0：失败
     *
     * @return send_status - 发送状态，1：成功，0：失败
     */
    public Byte getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置发送状态，1：成功，0：失败
     *
     * @param sendStatus 发送状态，1：成功，0：失败
     */
    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取是否显示价格，1：显示，0：不显示
     *
     * @return is_display_price - 是否显示价格，1：显示，0：不显示
     */
    public Byte getIsDisplayPrice() {
        return isDisplayPrice;
    }

    /**
     * 设置是否显示价格，1：显示，0：不显示
     *
     * @param isDisplayPrice 是否显示价格，1：显示，0：不显示
     */
    public void setIsDisplayPrice(Byte isDisplayPrice) {
        this.isDisplayPrice = isDisplayPrice;
    }

    /**
     * 获取本次确认类型，0：未处理 1：已确认，2：已拒绝
     *
     * @return this_confirm_type - 本次确认类型，0：未处理 1：已确认，2：已拒绝
     */
    public Byte getThisConfirmType() {
        return thisConfirmType;
    }

    /**
     * 设置本次确认类型，0：未处理 1：已确认，2：已拒绝
     *
     * @param thisConfirmType 本次确认类型，0：未处理 1：已确认，2：已拒绝
     */
    public void setThisConfirmType(Byte thisConfirmType) {
        this.thisConfirmType = thisConfirmType;
    }

    /**
     * 获取本次确认号
     *
     * @return this_confirm_no - 本次确认号
     */
    public String getThisConfirmNo() {
        return thisConfirmNo;
    }

    /**
     * 设置本次确认号
     *
     * @param thisConfirmNo 本次确认号
     */
    public void setThisConfirmNo(String thisConfirmNo) {
        this.thisConfirmNo = thisConfirmNo;
    }

    /**
     * 获取本次确认人
     *
     * @return this_confirm_name - 本次确认人
     */
    public String getThisConfirmName() {
        return thisConfirmName;
    }

    /**
     * 设置本次确认人
     *
     * @param thisConfirmName 本次确认人
     */
    public void setThisConfirmName(String thisConfirmName) {
        this.thisConfirmName = thisConfirmName;
    }

    /**
     * 获取拒绝原因：变价，满房，其他自定义
     *
     * @return refuse_reason - 拒绝原因：变价，满房，其他自定义
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * 设置拒绝原因：变价，满房，其他自定义
     *
     * @param refuseReason 拒绝原因：变价，满房，其他自定义
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    /**
     * 获取确认或取消备注
     *
     * @return note - 确认或取消备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置确认或取消备注
     *
     * @param note 确认或取消备注
     */
    public void setNote(String note) {
        this.note = note;
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