package com.fangcang.supplier.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyConfirmTypeDO;
import com.fangcang.supplier.dto.SupplyConfirmTypeDTO;
import com.fangcang.supplier.request.QueryConfirmTypeDTO;

import java.util.List;

public interface SupplyConfirmTypeMapper extends MyMapper<SupplyConfirmTypeDO> {

    List<SupplyConfirmTypeDTO> queryCurrentConfirmType(QueryConfirmTypeDTO queryConfirmTypeDTO);
}