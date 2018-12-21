package com.fangcang.agent.mapper;

import com.fangcang.agent.domain.AgentCreditItemDO;
import com.fangcang.agent.request.QueryOrderDeductCreditDTO;
import com.fangcang.agent.response.OrderDeductCreditDTO;
import com.fangcang.common.MyMapper;

public interface AgentCreditItemMapper extends MyMapper<AgentCreditItemDO> {

    /**
     * 查询订单已挂账金额
     * @param requestDTO
     * @return
     */
    public OrderDeductCreditDTO queryOrderDeductCredit(QueryOrderDeductCreditDTO requestDTO);
}