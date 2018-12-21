package com.fangcang.agent.mapper;


import com.fangcang.agent.domain.AgentAmountLogDO;
import com.fangcang.agent.dto.AgentAmountLogDTO;
import com.fangcang.agent.request.GetAgentAmountLogRequestDTO;
import com.fangcang.common.MyMapper;

import java.util.List;

/**
* @Description:    分销商额度调整日志Mapper
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/6/8 17:17
*/
public interface AgentAmountLogMapper extends MyMapper<AgentAmountLogDO> {

    /**
     * 新增一条额度调整日志
     * @param agentAmountLogDO
     * @return
     */
    Integer insertAmountLog(AgentAmountLogDO agentAmountLogDO);


    /**
     * 查看额度调整日志列表
     * @param agentAmountLogRequestDTO
     * @return
     */
    List<AgentAmountLogDTO> queryAgentAmountLogList(GetAgentAmountLogRequestDTO agentAmountLogRequestDTO);
}