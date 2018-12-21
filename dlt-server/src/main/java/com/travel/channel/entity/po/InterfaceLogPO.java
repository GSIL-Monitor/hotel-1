package com.travel.channel.entity.po;

import java.util.Date;

public class InterfaceLogPO {
    private Long id;

    private String channelCode;

    private String interfaceName;

    private Long hotelId;

    private Long roomTypeId;

    private Long pricePlanId;

    private Date checkinDate;

    private Date checkoutDate;

    private Integer reutrnCode;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName == null ? null : interfaceName.trim();
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId) {
        this.pricePlanId = pricePlanId;
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

    public Integer getReutrnCode() {
        return reutrnCode;
    }

    public void setReutrnCode(Integer reutrnCode) {
        this.reutrnCode = reutrnCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}