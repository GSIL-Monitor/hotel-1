package com.fangcang.agent.service;

import com.fangcang.agent.domain.AgentUserBindDO;
import com.fangcang.agent.dto.AgentBankCardDTO;
import com.fangcang.agent.dto.AgentVisitInfoDTO;
import com.fangcang.agent.dto.FrequentAgentDTO;
import com.fangcang.agent.dto.QueryAgentUserBindDTO;
import com.fangcang.agent.request.AddAgentContractFileRequestDTO;
import com.fangcang.agent.request.AddAgentRequestDTO;
import com.fangcang.agent.request.AddBankCardRequestDTO;
import com.fangcang.agent.request.AgentAddVisitRequestDTO;
import com.fangcang.agent.request.AgentListQueryRequestDTO;
import com.fangcang.agent.request.AgentUserRequestDTO;
import com.fangcang.agent.request.CommonAgentListRequestDTO;
import com.fangcang.agent.request.DeductAgentCreditLineRequestDTO;
import com.fangcang.agent.request.DeleteAgentContractFileRequestDTO;
import com.fangcang.agent.request.GetAgentAmountLogRequestDTO;
import com.fangcang.agent.request.GetAgentOperLogRequestDTO;
import com.fangcang.agent.request.GetCreditLinesListRequestDTO;
import com.fangcang.agent.request.GetVisitListQueryDTO;
import com.fangcang.agent.request.ModifyAmountRequestDTO;
import com.fangcang.agent.request.QueryOrderDeductCreditDTO;
import com.fangcang.agent.request.SetAgentUserActiveRequestDTO;
import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.agent.response.AddAgentContractFileResponseDTO;
import com.fangcang.agent.response.AddAgentResponseDTO;
import com.fangcang.agent.response.AddBankCardResponseDTO;
import com.fangcang.agent.response.AgentAddVisitResponseDTO;
import com.fangcang.agent.response.AgentListResponseDTO;
import com.fangcang.agent.response.AgentUserResponseDTO;
import com.fangcang.agent.response.GetAgentAmountLogResponseDTO;
import com.fangcang.agent.response.GetAgentOperLogResponseDTO;
import com.fangcang.agent.response.GetCreditLinesListResponseDTO;
import com.fangcang.agent.response.OrderDeductCreditDTO;
import com.fangcang.agent.response.SingleAgentResponseDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/28 15:59
 * @Description: 分销商Service接口
 */
public interface AgentService {
    /**
     * 保存分销商
     *
     * @param addAgentRequestDTO
     * @return
     */
    public ResponseDTO<AddAgentResponseDTO> saveAgent(AddAgentRequestDTO addAgentRequestDTO);

    /**
     * 设置常用分销商1-常用，0-不常用
     *
     * @param singleAgentRequestDTO
     * @return
     */
    public ResponseDTO setFrequentlyUse(SingleAgentRequestDTO singleAgentRequestDTO);

    /**
     * 设置分销商是否停用 1-启用；0-停用
     *
     * @param singleAgentRequestDTO
     * @return
     */
    public ResponseDTO setActive(SingleAgentRequestDTO singleAgentRequestDTO);

    /**
     * 分页查询分销商列表
     *
     * @param agentListQueryRequestDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<AgentListResponseDTO>> getListForPage(@RequestBody AgentListQueryRequestDTO agentListQueryRequestDTO);

    /**
     * 保存单张银行卡信息
     *
     * @param addBankCardRequestDTO
     * @return
     */
    public ResponseDTO<AddBankCardResponseDTO> saveBankCard(AddBankCardRequestDTO addBankCardRequestDTO);

    /**
     * 查询常用分销商列表
     *
     * @param commonAgentListRequestDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<FrequentAgentDTO>> queryCommonAgentList(CommonAgentListRequestDTO commonAgentListRequestDTO);


    /**
     * 添加拜访记录
     *
     * @param addVisitRequestDTO
     * @return
     */
    public ResponseDTO<AgentAddVisitResponseDTO> addVisit(AgentAddVisitRequestDTO addVisitRequestDTO);

    /**
     * 查询单个分销商详情
     *
     * @param singleAgentRequestDTO
     * @return
     */
    public ResponseDTO<SingleAgentResponseDTO> getAgentById(SingleAgentRequestDTO singleAgentRequestDTO);

    /**
     * 保存单个分销商账户信息
     *
     * @param agentUserRequestDTO
     * @return
     */
    public ResponseDTO saveUser(AgentUserRequestDTO agentUserRequestDTO);

    /**
     * 设置分销商用户是否启用
     *
     * @param setAgentUserActiveRequestDTO
     * @return
     */
    public ResponseDTO setUserActive(SetAgentUserActiveRequestDTO setAgentUserActiveRequestDTO);

    /**
     * 修改分销商信用额度
     *
     * @param modifyAmountRequestDTO
     * @return
     */
    public ResponseDTO modifyAmount(ModifyAmountRequestDTO modifyAmountRequestDTO);

    /**
     * 上传合同文件
     *
     * @param addAgentContractFileRequestDTO
     * @return
     */
    public ResponseDTO<AddAgentContractFileResponseDTO> uploadContractForAgent(MultipartFile file, AddAgentContractFileRequestDTO addAgentContractFileRequestDTO);

    /**
     * 分销商拜访记录列表
     *
     * @param getVisitListQueryDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<AgentVisitInfoDTO>> getVisitList(GetVisitListQueryDTO getVisitListQueryDTO);


    /**
     * 查询用户日志列表
     *
     * @return
     */
    public ResponseDTO<GetAgentOperLogResponseDTO> queryAgentOperLogList(GetAgentOperLogRequestDTO getAgentOperLogRequestDTO);

    /**
     * 下载分销商合同文件
     *
     * @param realPath
     * @return
     */
    public ResponseEntity<byte[]> getFile(String realPath, Long contractFileId);


    /**
     * 根据bankCardId删除银行卡信息
     *
     * @param bankCardId
     * @return
     */
    ResponseDTO deleteBankCardById(Long bankCardId);

    /**
     * 获取信用额度列表
     *
     * @param getCreditLinesListRequestDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<GetCreditLinesListResponseDTO>> queryCreditLinesList(GetCreditLinesListRequestDTO getCreditLinesListRequestDTO);


    /**
     * 查询单个分销商用户详情
     *
     * @param agentUserRequestDTO
     * @return
     */
    ResponseDTO<AgentUserResponseDTO> getUserInfo(AgentUserRequestDTO agentUserRequestDTO);

    /**
     * 查询分销商额度调整日志列表
     *
     * @param agentAmountLogRequestDTO
     * @return
     */
    ResponseDTO<GetAgentAmountLogResponseDTO> queryAgentAmountLogList(GetAgentAmountLogRequestDTO agentAmountLogRequestDTO);

    /**
     * 删除合同文件接口
     *
     * @param deleteAgentContractFileRequestDTO
     * @return
     */
    public ResponseDTO deleteContract(DeleteAgentContractFileRequestDTO deleteAgentContractFileRequestDTO);

    /**
     * 扣除剩余信用额度
     *
     * @param agentCreditLineRequestDTO
     * @return
     */
    public ResponseDTO deductAgentCreditLine(DeductAgentCreditLineRequestDTO agentCreditLineRequestDTO);

    /**
     * 查询订单已挂账金额
     */
    public OrderDeductCreditDTO queryOrderDeductCredit(QueryOrderDeductCreditDTO requestDTO);

    /**
     * 保存微信绑定用户信息
     *
     * @param agentUserBindDO
     * @return
     */
    public ResponseDTO saveAgentUserBind(AgentUserBindDO agentUserBindDO);

    /**
     * 查询微信绑定用户信息
     */
    public AgentUserBindDO queryAgentUserBind(QueryAgentUserBindDTO requestDTO);

    /**
     * 根据商家编码和分销商编码查询分销商银行卡列表
     * @param agentCode
     * @return
     */
    List<AgentBankCardDTO> queryAgentBankCardList(String agentCode);


}
