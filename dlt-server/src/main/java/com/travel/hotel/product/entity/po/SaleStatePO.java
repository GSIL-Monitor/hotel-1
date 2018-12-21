package com.travel.hotel.product.entity.po;

import com.travel.common.dto.GenericQueryDTO;

import java.util.Date;

public class SaleStatePO extends GenericQueryDTO {
    private Long stateId;

    private Long priceplanId;

    private String channelCode;

    private Integer saleState;

    private String creator;

    private Date createdate;

    private String modifier;

    private Date modifydate;

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getPriceplanId() {
        return priceplanId;
    }

    public void setPriceplanId(Long priceplanId) {
        this.priceplanId = priceplanId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public Integer getSaleState() {
        return saleState;
    }

    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}