package com.travel.hotel.product.entity.po;

public class QuotaAccountPO {
    private Long accountId;

    private Long roomTypeId;

    private String accountName;

    public QuotaAccountPO() {
    }

    public QuotaAccountPO(Long accountId, Long roomTypeId) {
        this.accountId = accountId;
        this.roomTypeId = roomTypeId;
    }

    public QuotaAccountPO(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}