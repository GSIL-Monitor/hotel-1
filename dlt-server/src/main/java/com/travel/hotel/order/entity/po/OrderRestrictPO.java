package com.travel.hotel.order.entity.po;

import java.util.Date;

public class OrderRestrictPO {
    private Long restrictId;

    private Long priceplanId;

    private Date saleDate;

    private Integer bookDays;

    private Integer occupancyType;

    private Integer occupancyDays;

    private Integer cancelType;

    private Integer cancelDays;

    private String cancelTime;

    private String cancelRemark;

    private Integer bookRooms;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private String bookTime;

    private String orderCode;

    public Long getRestrictId() {
        return restrictId;
    }

    public void setRestrictId(Long restrictId) {
        this.restrictId = restrictId;
    }

    public Long getPriceplanId() {
        return priceplanId;
    }

    public void setPriceplanId(Long priceplanId) {
        this.priceplanId = priceplanId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getBookDays() {
        return bookDays;
    }

    public void setBookDays(Integer bookDays) {
        this.bookDays = bookDays;
    }

    public Integer getOccupancyType() {
        return occupancyType;
    }

    public void setOccupancyType(Integer occupancyType) {
        this.occupancyType = occupancyType;
    }

    public Integer getOccupancyDays() {
        return occupancyDays;
    }

    public void setOccupancyDays(Integer occupancyDays) {
        this.occupancyDays = occupancyDays;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public Integer getCancelDays() {
        return cancelDays;
    }

    public void setCancelDays(Integer cancelDays) {
        this.cancelDays = cancelDays;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime == null ? null : cancelTime.trim();
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark == null ? null : cancelRemark.trim();
    }

    public Integer getBookRooms() {
        return bookRooms;
    }

    public void setBookRooms(Integer bookRooms) {
        this.bookRooms = bookRooms;
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

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime == null ? null : bookTime.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }
}