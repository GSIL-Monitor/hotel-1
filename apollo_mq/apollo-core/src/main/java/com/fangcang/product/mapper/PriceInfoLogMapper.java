package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.PriceInfoLogDO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/31.
 */
public interface PriceInfoLogMapper extends MyMapper<PriceInfoLogDO> {

    /**
     * 批量保存日志
     * @param priceInfoLogDOList
     */
    public void batchSavePriceInfoLog(List<PriceInfoLogDO> priceInfoLogDOList);
}
