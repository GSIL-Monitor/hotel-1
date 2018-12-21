package com.fangcang.product.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.request.SaleStateRequestDTO;
import com.fangcang.product.response.SaleStateResponseDTO;

/**
 * Created by ASUS on 2018/5/23.
 */
public interface SaleStateService {

    /**
     * 价格计划上下架详情
     * @param pricePlanQueryDTO
     * @return
     */
    public ResponseDTO<SaleStateResponseDTO> pricePlanIsOnSale(PricePlanQueryDTO pricePlanQueryDTO,MerchantSaleChannelDO merchantSaleChannelDO);

    /**
     * 批量上下架
     * @param saleStateRequestDTO
     * @return
     */
    public ResponseDTO batchOnSale(SaleStateRequestDTO saleStateRequestDTO);
}
