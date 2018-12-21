package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.MerchantSaleChannelDO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
public interface MerchantSaleChannelMapper extends MyMapper<MerchantSaleChannelDO> {

    /**
     * 根据商家编码查询售卖渠道信息
     * @param merchantSaleChannelDO
     * @return
     */
    public List<MerchantSaleChannelDO> queryMerchantSaleChannelInfo(MerchantSaleChannelDO merchantSaleChannelDO);
}
