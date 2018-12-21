package com.fangcang.product.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.response.MerchantChannelInfoResponseDTO;
import com.fangcang.product.response.ShowChannelPriceResponseDTO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
public interface MerchantSaleChannelService {

    /**
     * 根据商家编码查询售卖渠道信息
     * @param merchantSaleChannelDO
     * @return
     */
    public ResponseDTO<MerchantChannelInfoResponseDTO> queryMerchantSaleChannelInfo(MerchantSaleChannelDO merchantSaleChannelDO);

    /**
     * 显示渠道价格
     * @param merchantSaleChannelDO
     * @return
     */
    public ResponseDTO<ShowChannelPriceResponseDTO> showChannelPrice(MerchantSaleChannelDO merchantSaleChannelDO);
}
