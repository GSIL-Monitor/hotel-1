package com.fangcang.finance.bill.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.bill.domain.SupplyBillOrderItemDO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.BillOrderIdDTO;
import com.fangcang.finance.bill.response.BillOrderItemDTO;
import com.fangcang.finance.bill.response.BillOrderItemExportDTO;

import java.util.List;

public interface SupplyBillOrderItemMapper extends MyMapper<SupplyBillOrderItemDO> {

    /**
     * 查询查询账单的订单明细
     */
    public List<BillOrderItemDTO> querySupplyBillOrderItem(BillOrderIdDTO requestDTO);

    /**
     * 导出账单的订单明细
     */
    public List<BillOrderItemExportDTO> exportBillOrderItem(BillIdDTO requestDTO);
}