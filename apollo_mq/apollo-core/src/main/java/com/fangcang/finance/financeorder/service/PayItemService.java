package com.fangcang.finance.financeorder.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.dto.PayItemDTO;
import com.fangcang.finance.financeorder.request.QueryPayItemDTO;

import java.util.List;

public interface PayItemService {

    /**
     * 保存付款或收款凭证
     */
    public ResponseDTO savePayItem(PayItemDTO requestDTO);

    /**
     * 查询付款或收款凭证
     */
    public List<PayItemDTO> queryPayItem(QueryPayItemDTO requestDTO);
}
