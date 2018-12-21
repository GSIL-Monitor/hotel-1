package com.travel.hotel.product.service;

import com.travel.common.dto.product.request.PriceQueryDTO;
import com.travel.hotel.product.entity.PriceDTO;
import com.travel.hotel.product.entity.po.PricePO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/24.
 */
public interface PriceService {

    /**
     * 新增之前，先根据价格计划ID和售卖日期进行删除
     * @param pricePOList
     * @return
     */
    int batchAddPrice(List<PricePO> pricePOList);

    /**
     *
     * @param pricePlanId
     * @param saleDate
     * @return
     */
    int deleteByPricePlanIdAndSaleDate(Long pricePlanId,Date saleDate);

    List<PricePO> queryPriceByCondition(Map<String,Object> param);
    
    /**
     * 根据价格计划和售卖日期查询价格
     * @param priceQuery
     * @return
     */
    PriceDTO queryPriceByCondition(PriceQueryDTO priceQuery);

    int updatePriceByCondition(PricePO po);

    int batchUpdatePriceById(List<PricePO> poList);

}
