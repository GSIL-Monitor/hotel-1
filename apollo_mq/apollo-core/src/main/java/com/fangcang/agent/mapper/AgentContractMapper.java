package com.fangcang.agent.mapper;

import com.fangcang.agent.domain.AgentContractFileDO;
import com.fangcang.common.MyMapper;

public interface AgentContractMapper extends MyMapper<AgentContractFileDO>{
    
    /**
     * 添加合同信息
     * @param agentContractFileDO
     * @return
     */
    public Integer addAgentContractFile(AgentContractFileDO agentContractFileDO);


    /**
     * 根据合同文件id查询合同文件名
     * @param contractFileId
     * @return
     */
    String getContractFileName(Long contractFileId);

}
