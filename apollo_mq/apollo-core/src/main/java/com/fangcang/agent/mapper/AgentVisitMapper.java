package com.fangcang.agent.mapper;


import com.fangcang.agent.domain.AgentDO;
import com.fangcang.agent.domain.AgentVisitDO;
import com.fangcang.agent.request.GetVisitListQueryDTO;
import com.fangcang.common.MyMapper;

import java.util.List;

/**
* @Description:    分销商访问记录Mapper接口
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/6/2 15:42
*/
public interface AgentVisitMapper extends MyMapper<AgentDO> {

    /**
     * 添加拜访记录
     * @param agentVisitDO
     * @return
     */
    Integer addVisit(AgentVisitDO agentVisitDO);

    /**
     * 查询分销商拜访记录列表
     * @param getVisitListQueryDTO
     * @return
     */
    List<AgentVisitDO> getVisitList(GetVisitListQueryDTO getVisitListQueryDTO);


}