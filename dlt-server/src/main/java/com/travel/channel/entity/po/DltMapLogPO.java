package com.travel.channel.entity.po;

import java.util.Date;

public class DltMapLogPO {
    private Long id;

    private Long zhHotelId;

    private Long zhRpId;

    private String content;

    private String creator;

    private Date createDate;

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

    public Long getZhRpId() {
        return zhRpId;
    }

    public void setZhRpId(Long zhRpId) {
        this.zhRpId = zhRpId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
}