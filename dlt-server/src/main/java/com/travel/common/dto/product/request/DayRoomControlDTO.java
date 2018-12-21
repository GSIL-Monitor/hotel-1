package com.travel.common.dto.product.request;

import com.travel.common.dto.product.response.RoomControlResponseDTO;
import com.travel.common.utils.DateUtil;

import java.util.Date;

/**
 *   2018/2/1.
 */
public class DayRoomControlDTO extends RoomControlResponseDTO{

    private Date createDate;
    private String creator;
    private Date modifyDate = DateUtil.getCurrentDate();
    private String modifier;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
