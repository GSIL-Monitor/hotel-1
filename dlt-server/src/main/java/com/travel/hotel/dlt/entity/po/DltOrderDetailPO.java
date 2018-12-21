package com.travel.hotel.dlt.entity.po;

import java.math.BigDecimal;
import java.util.Date;

public class DltOrderDetailPO {
    private Long id;

    private String dltOrderId;

    private String orderId;

    private String lastDltOrderId;

    private String nextDltOrderId;

    private String lastOrderId;

    private String nextOrderId;

    private String channel;

    private String childChannel;

    private Date updateTime;

    private Date orderDate;

    private String orderCurrency;

    private BigDecimal orderPrice;

    private String formType;

    private String orderStatus;

    private String paymentType;

    private String confirmNo;

    private Date checkInDate;

    private Date checkOutDate;

    private Long cityId;

    private String cityName;

    private String cityEname;

    private String hotelId;

    private String hotelName;

    private String hotelEname;

    private String roomId;

    private String roomName;

    private String roomEname;

    private Integer roomNum;

    private String bedType;

    private String customerName;

    private String customerDid;

    private String specialMemo;

    private String orderMemo;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private String zhOrderCode;

    private String isHoldRoom;

    public String getIsHoldRoom() {
        return isHoldRoom;
    }

    public void setIsHoldRoom(String isHoldRoom) {
        this.isHoldRoom = isHoldRoom;
    }

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getChildChannel() {
        return childChannel;
    }

    public void setChildChannel(String childChannel) {
        this.childChannel = childChannel == null ? null : childChannel.trim();
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

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency == null ? null : orderCurrency.trim();
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType == null ? null : formType.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getConfirmNo() {
        return confirmNo;
    }

    public void setConfirmNo(String confirmNo) {
        this.confirmNo = confirmNo == null ? null : confirmNo.trim();
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityEname() {
        return cityEname;
    }

    public void setCityEname(String cityEname) {
        this.cityEname = cityEname == null ? null : cityEname.trim();
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId == null ? null : hotelId.trim();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getHotelEname() {
        return hotelEname;
    }

    public void setHotelEname(String hotelEname) {
        this.hotelEname = hotelEname == null ? null : hotelEname.trim();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomEname() {
        return roomEname;
    }

    public void setRoomEname(String roomEname) {
        this.roomEname = roomEname == null ? null : roomEname.trim();
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType == null ? null : bedType.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerDid() {
        return customerDid;
    }

    public void setCustomerDid(String customerDid) {
        this.customerDid = customerDid == null ? null : customerDid.trim();
    }

    public String getSpecialMemo() {
        return specialMemo;
    }

    public void setSpecialMemo(String specialMemo) {
        this.specialMemo = specialMemo == null ? null : specialMemo.trim();
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo == null ? null : orderMemo.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getZhOrderCode() {
        return zhOrderCode;
    }

    public void setZhOrderCode(String zhOrderCode) {
        this.zhOrderCode = zhOrderCode == null ? null : zhOrderCode.trim();
    }

    public String getLastDltOrderId() {
        return lastDltOrderId;
    }

    public void setLastDltOrderId(String lastDltOrderId) {
        this.lastDltOrderId = lastDltOrderId;
    }

    public String getNextDltOrderId() {
        return nextDltOrderId;
    }

    public void setNextDltOrderId(String nextDltOrderId) {
        this.nextDltOrderId = nextDltOrderId;
    }

    public String getLastOrderId() {
        return lastOrderId;
    }

    public void setLastOrderId(String lastOrderId) {
        this.lastOrderId = lastOrderId;
    }

    public String getNextOrderId() {
        return nextOrderId;
    }

    public void setNextOrderId(String nextOrderId) {
        this.nextOrderId = nextOrderId;
    }
}