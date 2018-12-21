package com.travel.hotel.product.service;

import com.travel.hotel.product.entity.po.HtlRestrictPO;

import java.util.Date;
import java.util.List;

/**
 *   2018/1/24.
 */
public interface RestrictService {

    /**
     * 新增之前，先根据价格计划ID和售卖日期进行删除
     * @param htlRestrictPOList
     * @return
     */
    public int batchAddRestrict(List<HtlRestrictPO> htlRestrictPOList);

    int batchUpdateRestrict(List<HtlRestrictPO> htlRestrictPOList);

    int deleteByPricePlanIdAndSaleDate(Long pricePlanId, Date saleDate);

    List<HtlRestrictPO> queryListByPricePlanIdAndSaleDate(Long pricePlanId,Date beginDate, Date endDate);
}
