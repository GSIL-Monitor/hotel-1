package com.travel.hotel.product.dao;


import com.travel.hotel.product.entity.po.DltChannelConfig;

import java.util.List;

/**
 *  m_ 2018/6/6.
 */
public interface ChannelConfigMapper {

    List<DltChannelConfig> queryAllConfig();
}
