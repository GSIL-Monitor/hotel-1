package com.travel.hotel.product.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 *   2018/3/20.
 */
public class QuotaReportPO implements Serializable {

    private Long hotelId;
    private String hotelName;
    private Long roomTypeId;
    private String roomName;
    private String accountId;
    private Date saleDate;
    private Integer allQuotaNum;
    private Integer onsaleQuotaNum;
    private Integer usedQuotaNum;

    public Long getHotelId() {

        return hotelId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getAllQuotaNum() {
        return allQuotaNum;
    }

    public void setAllQuotaNum(Integer allQuotaNum) {
        this.allQuotaNum = allQuotaNum;
    }

    public Integer getOnsaleQuotaNum() {
        return onsaleQuotaNum;
    }

    public void setOnsaleQuotaNum(Integer onsaleQuotaNum) {
        this.onsaleQuotaNum = onsaleQuotaNum;
    }

    public Integer getUsedQuotaNum() {
        return usedQuotaNum;
    }

    public void setUsedQuotaNum(Integer usedQuotaNum) {
        this.usedQuotaNum = usedQuotaNum;
    }
}
