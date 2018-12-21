package com.fangcang.agent.mapper;


import com.fangcang.agent.domain.AgentDO;
import com.fangcang.agent.request.AgentListQueryRequestDTO;
import com.fangcang.agent.request.CommonAgentListRequestDTO;
import com.fangcang.agent.request.DeductAgentCreditLineRequestDTO;
import com.fangcang.agent.request.GetCreditLinesListRequestDTO;
import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.common.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
* @Description:    分销商Mapper接口
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/28 09:49
*/
public interface AgentMapper extends MyMapper<AgentDO> {

    /**
     * 添加分销商
     * @param agentDO
     * @return
     */
    Integer insertAgent(AgentDO agentDO);

    /**
     * 修改供分销商
     * @param agentDO
     * @return
     */
    Integer updateAgent(AgentDO agentDO);

    /**
     * 设置是否常用
     * @param agentDO
     * @return
     */
    Integer setFrequentlyUse(AgentDO agentDO);

    /**
     * 设置是否停用
     * @param agentDO
     * @return
     */
    Integer setActive(AgentDO agentDO);

    /**
     * 获取总记录数
     * @return
     */
    Long getToalCount();

    /**
     * 查询分销商列表
     * @return
     */
    List<AgentDO> listAgent(AgentListQueryRequestDTO agentListQueryRequestDTO);

    /**
     * 查询常用分销商列表
     * @param commonAgentListResquestDTO
     * @return
     */
    List<AgentDO> queryCommonAgentList(CommonAgentListRequestDTO commonAgentListResquestDTO);

    /**
     * 根据agentId查询供应商编码
     * @param agentId
     * @return
     */
    String getAgentCodeById(@Param("agentId") Long agentId);

    /**
     * 获取当前总额度
     * @param agentId
     * @return
     */
    BigDecimal getAmount(@Param("agentId") Long agentId);

    /**
     * 获取当前总额度
     * @param agentCode
     * @return
     */
    BigDecimal getAmountByAgentCode(@Param("agentCode") String agentCode);

    /**
     * 根据分销商id获取当前已用额度
     * @param agentId
     * @return
     */
    BigDecimal getUsedAmount(@Param("agentId") Long agentId);

    /**
     * 根据agentCode获取当前已用额度
     * @param agentCode
     * @return
     */
    BigDecimal getUsedAmountByAgentCode(@Param("agentCode") String agentCode);


    /**
     * 修改分销商额度
     * @param agentDO
     * @return
     */
    Integer modifyAmount(AgentDO agentDO);

    /**
     * 查询单个分销商详情
     * @param singleAgentRequestDTO
     * @return
     */
    AgentDO selectSingleAgentInfo(SingleAgentRequestDTO singleAgentRequestDTO);
    
    /**
	 * 获得信用额度列表
	 * @param getCreditLinesListRequestDTO
	 * @return
	 */
	public List<AgentDO> queryCreditLinesList(GetCreditLinesListRequestDTO getCreditLinesListRequestDTO);

    /**
     * 扣除剩余信用额度
     * @return
     */
	//Integer deductAgentCreditLine(AgentDO agentDO);
    public int deductAgentCreditLine(DeductAgentCreditLineRequestDTO agentCreditLineRequestDTO);

    /**
     * 退还信息额度
     */
    public int returnAgentCreditLine(DeductAgentCreditLineRequestDTO agentCreditLineRequestDTO);
}