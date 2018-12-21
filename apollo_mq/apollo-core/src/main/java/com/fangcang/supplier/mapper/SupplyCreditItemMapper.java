package com.fangcang.supplier.mapper;

import com.fangcang.supplier.request.QuerySupplyOrderDeductCreditDTO;
import com.fangcang.supplier.response.SupplyOrderDeductCreditDTO;
import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyCreditItemDO;

public interface SupplyCreditItemMapper extends MyMapper<SupplyCreditItemDO> {

    /**
     * 查询订单已挂账金额
     * @param requestDTO
     * @return
     */
    public SupplyOrderDeductCreditDTO querySupplyOrderDeductCredit(QuerySupplyOrderDeductCreditDTO requestDTO);
}