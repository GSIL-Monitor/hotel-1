package com.travel.member.dao;

import java.util.List;

import com.travel.common.dto.member.query.AgentQueryDTO;
import com.travel.member.entity.Agent;

/**
 * @Description 分销商数据操作接口
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午10:50:49
 */
public interface AgentDao {
	
	/**
	 * 保存分销商
	 * @param agent
	 * @return
	 */
	public int saveAgent(Agent agent);
	
	/**
	 * 修改分销商
	 * @param agent
	 * @return
	 */
	public int updateAgent(Agent agent);
	
	/**
	 * 删除分销商
	 * @param agentId
	 * @return
	 */
	public void deleteAgent(Long agentId);
	
	/**
	 * 根据分销商id查询分销商
	 * @param agentId
	 * @return
	 */
	public Agent queryAgentById(Long agentId);

	/**
	 * 根据分销商编码查询分销商
	 * @param agentCode
	 * @return
	 */
	public Agent queryAgentByCode(String agentCode);
	
	/**
	 * 根据分销商名称查询分销商
	 * @param agentName
	 * @return
	 */
	public Agent queryAgentByName(String agentName);
	
	/**
	 * 查询分销商集合
	 * @param agentQuery
	 * @return
	 */
	public List<Agent> listAgent(AgentQueryDTO agentQuery);
}
