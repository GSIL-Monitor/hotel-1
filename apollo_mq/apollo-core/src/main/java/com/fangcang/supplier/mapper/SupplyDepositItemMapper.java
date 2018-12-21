package com.fangcang.supplier.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyDepositItemDO;
import com.fangcang.supplier.request.QuerySupplyDepositItemDTO;
import com.fangcang.supplier.response.SupplyDepositItemDTO;

import java.util.List;

public interface SupplyDepositItemMapper extends MyMapper<SupplyDepositItemDO> {

    public List<SupplyDepositItemDTO> querySupplyDepositItem(QuerySupplyDepositItemDTO request);
}