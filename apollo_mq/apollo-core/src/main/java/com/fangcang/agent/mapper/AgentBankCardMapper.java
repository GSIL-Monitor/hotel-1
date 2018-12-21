package com.fangcang.agent.mapper;

import com.fangcang.agent.domain.AgentBankCardDO;
import com.fangcang.common.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description:    分销商银行卡管理Mapper接口
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/29 14:08
*/
public interface AgentBankCardMapper extends MyMapper<AgentBankCardDO> {

    /**
     * 添加分销商银行卡信息
     * @return
     */
    Integer insertAgentBankCard(List<AgentBankCardDO> agentBankCardDOS);

    /**
     * 修改分销商银行卡信息
     * @param agentBankCardDOS
     * @return
     */
    Integer batchUpdateAgentBankCard(List<AgentBankCardDO> agentBankCardDOS);

    /**
     * 添加单个银行卡信息
     * @param agentBankCardDO
     * @return
     */
    Integer insertSingleAgentBankCard(AgentBankCardDO agentBankCardDO);

    /**
     * 修改单个银行卡信息
     * @param agentBankCardDO
     * @return
     */
    Integer updateSingleAgentBankCard(AgentBankCardDO agentBankCardDO);

    /**
     * 删除分销商银行卡信息
     * @param bankCardId
     * @return
     */
    Integer deleteBankCardById(@Param("bankCardId")Long bankCardId);


}