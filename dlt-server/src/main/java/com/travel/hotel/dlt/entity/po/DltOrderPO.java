package com.travel.hotel.dlt.entity.po;

import java.util.Date;

public class DltOrderPO {
    private Long id;

    private String dltOrderId;

    private String merchantCode;

    private String orderId;
    private String channel;
    private String childChannel;
    private Date updateTime;
    private Date orderDate;
    private String formType;
    private String orderStatus;
    private Date checkinDate;
    private Date checkoutDate;

    private Date createDate;

    private Integer isHandled;

    private Date handleDate;

    private String handleResult;

    private String handleRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDltOrderId() {
        return dltOrderId;
    }

    public void setDltOrderId(String dltOrderId) {
        this.dltOrderId = dltOrderId == null ? null : dltOrderId.trim();
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsHandled() {
        return isHandled;
    }

    public void setIsHandled(Integer isHandled) {
        this.isHandled = isHandled;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult == null ? null : handleResult.trim();
    }

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark == null ? null : handleRemark.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChildChannel() {
        return childChannel;
    }

    public void setChildChannel(String childChannel) {
        this.childChannel = childChannel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}