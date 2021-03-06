package com.fangcang.finance.bill.mapper;

import com.fangcang.finance.bill.domain.CheckOutCurrencyDO;
import com.fangcang.finance.bill.request.QueryCheckOutCurrencyDTO;
import com.fangcang.finance.bill.request.QueryCheckOutDTO;
import com.fangcang.finance.bill.request.UpdateOrderFinanceDTO;
import com.fangcang.finance.bill.response.CheckOutDTO;
import com.fangcang.finance.bill.response.CheckOutOrderDTO;
import com.fangcang.finance.bill.response.CheckOutOrderItemDTO;

import java.util.List;

public interface AgentCreditOrderMapper {

    /**
     * 批量更新订单财务状态
     */
    public int updateOrderFinance(UpdateOrderFinanceDTO request);

    /**
     * 查询分销商可出账信息
     */
    public List<CheckOutDTO> queryAgentCheckOut(QueryCheckOutDTO queryCheckOutDTO);

    /**
     * 查询分销商可出账信息，多币种金额
     */
    public List<CheckOutCurrencyDO> queryAgentCheckOutCurrency(QueryCheckOutCurrencyDTO request);

    /**
     * 查询可出账的订单
     */
    public List<CheckOutOrderDTO> queryCheckOutOrder(QueryCheckOutDTO requestDTO);

    /**
     * 查询可出账的订单明细
     */
    public List<CheckOutOrderItemDTO> queryCheckOutOrderItem(List<String> orderCodeList);
}
