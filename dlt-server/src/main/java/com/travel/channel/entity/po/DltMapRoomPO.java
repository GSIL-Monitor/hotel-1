package com.travel.channel.entity.po;

import java.util.Date;

public class DltMapRoomPO {
    private Long id;

    private Long zhHotelId;

    private Long zhRoomId;

    private String merchantCode;

    private Long zhRpId;

    private Long dltHotelId;

    private Long dltRoomId;

    private String dltRoomName;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getZhHotelId() {
        return zhHotelId;
    }

    public void setZhHotelId(Long zhHotelId) {
        this.zhHotelId = zhHotelId;
    }

    public Long getZhRoomId() {
        return zhRoomId;
    }

    public void setZhRoomId(Long zhRoomId) {
        this.zhRoomId = zhRoomId;
    }

    public Long getZhRpId() {
        return zhRpId;
    }

    public void setZhRpId(Long zhRpId) {
        this.zhRpId = zhRpId;
    }

    public Long getDltHotelId() {
        return dltHotelId;
    }

    public void setDltHotelId(Long dltHotelId) {
        this.dltHotelId = dltHotelId;
    }

    public Long getDltRoomId() {
        return dltRoomId;
    }

    public void setDltRoomId(Long dltRoomId) {
        this.dltRoomId = dltRoomId;
    }

    public String getDltRoomName() {
        return dltRoomName;
    }

    public void setDltRoomName(String dltRoomName) {
        this.dltRoomName = dltRoomName == null ? null : dltRoomName.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
}