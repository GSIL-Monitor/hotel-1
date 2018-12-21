package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.SaleStateDO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
public interface SaleStateMapper extends MyMapper<SaleStateDO> {

    /**
     * 根据价格计划ID查询价格计划上下架详情
     * @param saleStateDO
     * @return
     */
    public SaleStateDO queryPricePlanSaleStateInfo(SaleStateDO saleStateDO);

    /**
     * 批量上下架
     * @param saleStateDOList
     */
    public void batchOnSale(List<SaleStateDO> saleStateDOList);
}
