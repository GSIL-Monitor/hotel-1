package com.fangcang.order.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.response.OrderSupplierDTO;

import java.util.List;

public interface SupplyOrderMapper extends MyMapper<SupplyOrderDO> {

    /**
     * 查询订单的供应商信息
     * @param orderId
     * @return
     */
    public List<OrderSupplierDTO> queryOrderSupplier(Integer orderId);
}