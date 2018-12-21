package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.PriceInfoDO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/31.
 */
public interface PriceInfoMapper extends MyMapper<PriceInfoDO> {
    /**
     * 根据起始日期查询价格
     * @param productDailyInfoQueryDTO
     * @return
     */
    public List<PriceInfoDO> queryDailyPriceInfo(ProductDailyInfoQueryDTO productDailyInfoQueryDTO);

    /**
     * 批量调价
     * @param priceInfoDOList
     */
    public void batchSaveOrUpdatePriceInfo(List<PriceInfoDO> priceInfoDOList);

    /**
     * 根据酒店ID查询每日价格
     * @param productDailyInfoQueryDTO
     * @return
     */
    public List<PriceInfoDO> queryPriceInfoByHotelId(ProductDailyInfoQueryDTO productDailyInfoQueryDTO);
}
