package com.travel.hotel.product.dao;

import com.travel.common.dto.product.request.PriceQueryDTO;
import com.travel.hotel.product.entity.po.PricePO;

import java.util.List;
import java.util.Map;

public interface PricePOMapper {
    int deleteByPrimaryKey(Long priceId);

    int deleteByPricePlanIdAndSaleDate(PricePO record);

    int insert(PricePO record);

    int insertSelective(PricePO record);

    PricePO selectByPrimaryKey(Long priceId);

    List<PricePO> selectByCondition(Map<String,Object> param);
    
    /**
     * 根据价格计划和售卖日期查询价格
     * @param priceQuery
     * @return
     */
    PricePO selectPriceByCondition(PriceQueryDTO priceQuery);

    int updateByPrimaryKeySelective(PricePO record);

    int updateByPrimaryKey(PricePO record);
    
    /**
     * 根据酒店查询早餐
     * @param hotelId
     * @return
     */
    List<PricePO> queryBreakfastByHotelId(Long hotelId);
    

    /**
     * 批量保存售价
     * @param pricePOList
     * @return
     */
    int batchInsertSelective(List<PricePO> pricePOList);
}