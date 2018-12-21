package com.travel.hotel.product.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.response.SaleStateResponseDTO;
import com.travel.hotel.product.entity.SaleStateDTO;
import com.travel.hotel.product.entity.po.SaleStatePO;

import java.util.List;

/**
 *   2018/2/7.
 */
public interface SaleStateService {
    List<SaleStateDTO> queryByCondition(SaleStatePO queryPO);

    SaleStateResponseDTO querySaleStateByPricePlanId(Long pricePlanId);

    int saveSaleState(SaleStateResponseDTO saleStateResponseDTO);

    PaginationDTO<SaleStatePO> querySaleStateByPage(SaleStatePO queryPO);

    int batchSaveSaleState(List<SaleStateResponseDTO> saleStateResponseDTOList,String userName);
}
