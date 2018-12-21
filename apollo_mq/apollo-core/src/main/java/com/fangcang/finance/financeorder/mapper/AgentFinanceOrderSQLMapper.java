package com.fangcang.finance.financeorder.mapper;

import com.fangcang.finance.financeorder.domain.AgentSingleBalanceDO;

import java.util.List;

/**
 * Created by Vinney on 2018/7/19.
 */
public interface AgentFinanceOrderSQLMapper{

    /**
     * 查询待收款工单列表
     * @param agentSingleBalanceDO
     * @return
     */
    List<AgentSingleBalanceDO> queryUnreceived(AgentSingleBalanceDO agentSingleBalanceDO);

    /**
     * 查询已收款工单列表
     * @param agentSingleBalanceDO
     * @return
     */
    List<AgentSingleBalanceDO> queryReceived(AgentSingleBalanceDO agentSingleBalanceDO);

    /**
     * 查询未完成工单列表
     * @param agentSingleBalanceDO
     * @return
     */
    List<AgentSingleBalanceDO> queryUnfinished(AgentSingleBalanceDO agentSingleBalanceDO);

}
