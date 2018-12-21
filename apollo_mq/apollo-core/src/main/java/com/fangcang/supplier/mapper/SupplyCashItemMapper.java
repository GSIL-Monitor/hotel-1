package com.fangcang.supplier.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyCashItemDO;
import com.fangcang.supplier.request.QuerySupplyCashItemDTO;
import com.fangcang.supplier.response.SupplyCashItemDTO;

import java.util.List;

public interface SupplyCashItemMapper extends MyMapper<SupplyCashItemDO> {

    public List<SupplyCashItemDTO> querySupplyCashItem(QuerySupplyCashItemDTO request);
}