package com.fangcang.finance.bill.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.bill.domain.SupplyBillDO;
import com.fangcang.finance.bill.request.QueryBillDTO;
import com.fangcang.finance.bill.response.BillDTO;

import java.util.List;

public interface SupplyBillMapper extends MyMapper<SupplyBillDO> {

    /**
     * 查询账单
     * @param requestDTO
     * @return
     */
    public List<BillDTO> querySupplyBill(QueryBillDTO requestDTO);
}