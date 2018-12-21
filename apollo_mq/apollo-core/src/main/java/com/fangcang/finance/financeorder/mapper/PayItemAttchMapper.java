package com.fangcang.finance.financeorder.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.dto.PayItemAttchDTO;
import com.fangcang.finance.financeorder.domain.PayItemAttchDO;

import java.util.List;

public interface PayItemAttchMapper extends MyMapper<PayItemAttchDO> {

    public List<PayItemAttchDTO> queryPayItemAttch(List<Integer> payItemIdList);
}