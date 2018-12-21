package com.fangcang.agent.mapper;

import java.util.List;

import com.fangcang.agent.domain.AgentOperLogDO;
import com.fangcang.agent.request.GetAgentOperLogRequestDTO;
import com.fangcang.common.MyMapper;

public interface AgentOperLogMapper extends MyMapper<AgentOperLogDO>{
    
    /**
     * 添加日志信息
     * @param agentOperLogDO
     * @return
     */
    public Integer addAgentOperLog(AgentOperLogDO agentOperLogDO); 
    
    /**
	 * 获取日志列表
	 * @return List<AgentOperLogDO>
	 */
    public List<AgentOperLogDO> queryAgentOperLogList(GetAgentOperLogRequestDTO getAgentOperLogRequestDTO);
}
