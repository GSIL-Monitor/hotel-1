package com.travel.hotel.product.entity;

import com.travel.hotel.product.entity.po.SaleStatePO;

/**
 *   2018/2/7.
 */
public class SaleStateDTO extends SaleStatePO {
    private String channelName;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
