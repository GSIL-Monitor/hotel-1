package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.SaleStateLogDO;

import java.util.List;

/**
 * Created by ASUS on 2018/6/6.
 */
public interface SaleStateLogMapper extends MyMapper<SaleStateLogDO>{

    /**
     * 批量保存上下架操作日志
     * @param saleStateLogDOS
     */
    public void batchSaveSaleStateLog(List<SaleStateLogDO> saleStateLogDOS);
}
