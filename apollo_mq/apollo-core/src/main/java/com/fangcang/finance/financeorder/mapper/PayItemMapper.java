package com.fangcang.finance.financeorder.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.dto.PayItemDTO;
import com.fangcang.finance.financeorder.domain.PayItemDO;
import com.fangcang.finance.financeorder.request.QueryPayItemDTO;

import java.util.List;

public interface PayItemMapper extends MyMapper<PayItemDO> {

    public List<PayItemDTO> queryPayItem(QueryPayItemDTO requestDTO);
}