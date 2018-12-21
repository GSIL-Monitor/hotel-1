package com.travel.channel.entity.po;

import java.util.Date;

public class DltMapHotelPO {
    private Long id;

    private Long zhHotelId;

    private String zhHotelName;

    private Long dltMasterHotelId;

    private Long dltHotelId;

    private String dltHotelName;

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

    public String getZhHotelName() {
        return zhHotelName;
    }

    public void setZhHotelName(String zhHotelName) {
        this.zhHotelName = zhHotelName == null ? null : zhHotelName.trim();
    }

    public Long getDltMasterHotelId() {
        return dltMasterHotelId;
    }

    public void setDltMasterHotelId(Long dltMasterHotelId) {
        this.dltMasterHotelId = dltMasterHotelId;
    }

    public Long getDltHotelId() {
        return dltHotelId;
    }

    public void setDltHotelId(Long dltHotelId) {
        this.dltHotelId = dltHotelId;
    }

    public String getDltHotelName() {
        return dltHotelName;
    }

    public void setDltHotelName(String dltHotelName) {
        this.dltHotelName = dltHotelName == null ? null : dltHotelName.trim();
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
}