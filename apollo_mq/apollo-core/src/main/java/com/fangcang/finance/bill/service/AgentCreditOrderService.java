package com.fangcang.finance.bill.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.bill.request.QueryCheckOutDTO;
import com.fangcang.finance.bill.request.UpdateOrderFinanceDTO;
import com.fangcang.finance.bill.response.CheckOutDTO;
import com.fangcang.finance.bill.response.CheckOutOrderDTO;
import com.fangcang.finance.bill.response.CheckOutOrderItemDTO;

import java.util.List;

public interface AgentCreditOrderService {

    /**
     * 批量更新订单财务状态
     */
    public ResponseDTO updateBatchOrderFinance(List<UpdateOrderFinanceDTO> list);

    /**
     * 查询分销商可出账信息
     */
    public PaginationSupportDTO<CheckOutDTO> queryAgentCheckOut(QueryCheckOutDTO requestDTO);

    /**
     * 查询可出账的订单
     */
    public PaginationSupportDTO<CheckOutOrderDTO> queryCheckOutOrder(QueryCheckOutDTO requestDTO);

    /**
     * 查询可出账的订单明细
     */
    public PaginationSupportDTO<CheckOutOrderItemDTO> queryCheckOutOrderItem(List<String> orderCodeList);
}
