package com.fangcang.agent.mapper;

import com.fangcang.agent.domain.AgentUserDO;
import com.fangcang.agent.request.AgentUserRequestDTO;
import com.fangcang.agent.response.AgentUserResponseDTO;
import com.fangcang.common.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 分销商里的账号Mapper接口
 * @Author: yanming.li@fangcang.com
 * @CreateDate: 2018/5/28 11:40
 */
public interface AgentUserMapper extends MyMapper<AgentUserDO> {

    /**
     * 添加分销商账号
     *
     * @param agentUserDOS
     * @return
     */
    Integer insertUsers(List<AgentUserDO> agentUserDOS);

    /**
     * 修改分销商账号信息
     *
     * @param agentUserDOS
     * @return
     */
    Integer batchUpdateUsers(List<AgentUserDO> agentUserDOS);


    /**
     * 根据agentUserId查询单个详情
     *
     * @param agentUserId
     * @return
     */
    AgentUserDO getUserByUserId(@Param("agentUserId") Long agentUserId);

    /**
     * 添加单个分销商账号
     *
     * @param agentUserDO
     * @return
     */
    Integer insertSingleAgentUser(AgentUserDO agentUserDO);

    /**
     * 编辑单个分销商账号
     *
     * @return
     */
    Integer updateSingleAgentUser(AgentUserDO agentUserDO);

    /**
     * 设置分销商用户是否启用
     *
     * @param agentUserDO
     * @return
     */
    Integer setUserActive(AgentUserDO agentUserDO);


    /**
     * 查询单个分销商用户信息
     *
     * @param agentUserRequestDTO
     * @return
     */
    AgentUserResponseDTO getUserInfo(AgentUserRequestDTO agentUserRequestDTO);

    /**
     * 根据username和merchantId查有多少条记录(新增单个用户时用)
     *
     * @param username
     * @param merchantId
     * @return
     */
    Long selectUserByUsernameAndMerchantId(@Param("username") String username, @Param("merchantId") Long merchantId);

    /**
     * 根据usernameList和merchantId查有多少条记录(新增分销商时用)
     * @param usernameList
     * @param merchantId
     * @return
     */
    Long selectUserByUsernameListAndMerchantId(@Param("list") List usernameList, @Param("merchantId") Long merchantId);

}