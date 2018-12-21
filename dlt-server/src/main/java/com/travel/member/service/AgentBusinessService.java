package com.travel.member.service;

import com.travel.common.dto.AutoCompleteDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.request.CreditRequestDTO;
import com.travel.common.dto.member.query.AgentQueryDTO;
import com.travel.common.dto.member.request.AgentRequestDTO;
import com.travel.common.dto.member.response.AgentResponseDTO;

import java.util.List;

/**
 * @Description 分销商业务操作接口
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午10:48:36
 */
public interface AgentBusinessService {
	
	/**
	 * 保存分销商
	 * @param agent
	 * @return
	 */
	public Long saveAgent(AgentRequestDTO agent);
	
	/**
	 * 修改分销商
	 * @param agent
	 * @return
	 */
	public Long updateAgent(AgentRequestDTO agent);
	
	/**
	 * 客户挂账(新建订单和取消订单需要修改信用额度)
	 * @param creditRequest
	 */
	public void updateAgentUsedCredit(CreditRequestDTO creditRequest);
	
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
	public AgentResponseDTO getAgentById(Long agentId);
	
	/**
	 * 根据分销商编码查询分销商
	 * @param agentCode
	 * @return
	 */
	public AgentResponseDTO getAgentByCode(String agentCode);
	
	/**
	 * 查询分销商集合
	 * @param agentQuery
	 * @return
	 */
	public List<AgentResponseDTO> listAgent(AgentQueryDTO agentQuery);
	
	/**
	 * 分销商联想输入
	 * @return
	 */
	public List<AutoCompleteDTO> autocomplete();
	
	/**
	 * 分页查询分销商集合
	 * @param agentQuery
	 * @return
	 */
	public PaginationDTO<AgentResponseDTO> listAgentForPage(AgentQueryDTO agentQuery);

}
