package com.fangcang.agent;

import com.alibaba.fastjson.JSON;
import com.fangcang.agent.dto.AgentVisitInfoDTO;
import com.fangcang.agent.request.AddAgentContractFileRequestDTO;
import com.fangcang.agent.request.AddAgentRequestDTO;
import com.fangcang.agent.request.AddBankCardRequestDTO;
import com.fangcang.agent.request.AgentAddVisitRequestDTO;
import com.fangcang.agent.request.AgentListQueryRequestDTO;
import com.fangcang.agent.request.AgentUserRequestDTO;
import com.fangcang.agent.request.CommonAgentListRequestDTO;
import com.fangcang.agent.request.DeductAgentCreditLineRequestDTO;
import com.fangcang.agent.request.DeleteAgentBanCardRequestDTO;
import com.fangcang.agent.request.DeleteAgentContractFileRequestDTO;
import com.fangcang.agent.request.GetAgentAmountLogRequestDTO;
import com.fangcang.agent.request.GetAgentOperLogRequestDTO;
import com.fangcang.agent.request.GetCreditLinesListRequestDTO;
import com.fangcang.agent.request.GetVisitListQueryDTO;
import com.fangcang.agent.request.ModifyAmountRequestDTO;
import com.fangcang.agent.request.SetAgentUserActiveRequestDTO;
import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.agent.response.AddAgentContractFileResponseDTO;
import com.fangcang.agent.response.AddAgentResponseDTO;
import com.fangcang.agent.response.AddBankCardResponseDTO;
import com.fangcang.agent.response.AgentAddVisitResponseDTO;
import com.fangcang.agent.response.AgentListResponseDTO;
import com.fangcang.agent.response.AgentUserResponseDTO;
import com.fangcang.agent.response.FrequentAgentResponseDTO;
import com.fangcang.agent.response.GetAgentAmountLogResponseDTO;
import com.fangcang.agent.response.GetCreditLinesListResponseDTO;
import com.fangcang.agent.response.SingleAgentResponseDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.merchant.response.AllRoleResponseDTO;
import com.fangcang.supplier.response.AddVisitResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/29 16:56
 * @Description: 分销商相关Controller
 */
@RestController
@RequestMapping("/agent")
@Slf4j
public class AgentController extends BaseController {

    @Autowired
    private AgentService agentService;

    /**
     * 保存分销商（添加/编辑时使用）
     *
     * @param addAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddAgentResponseDTO> saveAgent(@RequestBody @Valid AddAgentRequestDTO addAgentRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            addAgentRequestDTO.setCreator(this.getCacheUser().getUsername());
            addAgentRequestDTO.setCreateTime(DateUtil.getCurrentDate());
            addAgentRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = agentService.saveAgent(addAgentRequestDTO);
        } catch (Exception e) {
            log.error("保存分销商异常：{}"+ JSON.toJSONString(addAgentRequestDTO),e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 设置常用分销商1-常用，0-不常用
     *
     * @param singleAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/setFrequentlyUse", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setFrequentlyUse(@RequestBody SingleAgentRequestDTO singleAgentRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            singleAgentRequestDTO.setModifier(this.getCacheUser().getUsername());
            responseDTO = agentService.setFrequentlyUse(singleAgentRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 设置分销商是否停用
     * 1-启用；0-停用
     *
     * @param singleAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/setActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setActive(@RequestBody SingleAgentRequestDTO singleAgentRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            singleAgentRequestDTO.setModifier(this.getCacheUser().getUsername());
            responseDTO = agentService.setActive(singleAgentRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 查看分销商列表
     *
     * @param agentListQueryRequestDTO
     * @return
     */
    @RequestMapping(value = "/getListForPage", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<AgentListResponseDTO>> getListForPage(@RequestBody AgentListQueryRequestDTO agentListQueryRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            agentListQueryRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = agentService.getListForPage(agentListQueryRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 保存银行卡信息
     *
     * @param addBankCardRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveBankCard", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddBankCardResponseDTO> saveBankCard(@RequestBody @Valid AddBankCardRequestDTO addBankCardRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            addBankCardRequestDTO.setCreator(this.getCacheUser().getUsername());
            responseDTO = agentService.saveBankCard(addBankCardRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }


    /**
     * 常用分销商列表
     * 入参的商家列表从Session里面取
     *
     * @return
     */
    @RequestMapping(value = "/queryCommonAgentList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<FrequentAgentResponseDTO> queryCommonAgentList(@RequestBody CommonAgentListRequestDTO commonAgentListRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            commonAgentListRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = agentService.queryCommonAgentList(commonAgentListRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 添加拜访记录
     *
     * @param addVisitRequestDTO
     * @return
     */
    @RequestMapping(value = "/addVisit", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AgentAddVisitResponseDTO> addVisit(@RequestBody @Valid AgentAddVisitRequestDTO addVisitRequestDTO) {

        AddVisitResponseDTO addVisitResponseDTO = new AddVisitResponseDTO();
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            addVisitRequestDTO.setCreator(this.getCacheUser().getUsername());
            responseDTO = agentService.addVisit(addVisitRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询单个分销商详情
     *
     * @param singleAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/getAgentById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SingleAgentResponseDTO> getAgentById(@RequestBody SingleAgentRequestDTO singleAgentRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = agentService.getAgentById(singleAgentRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 保存用户（编辑使用）
     *
     * @param agentUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveUser(@RequestBody @Valid AgentUserRequestDTO agentUserRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            agentUserRequestDTO.setCreator(this.getCacheUser().getUsername());
            agentUserRequestDTO.setModifier(this.getCacheUser().getUsername());
            agentUserRequestDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = agentService.saveUser(agentUserRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;
    }

    /**
     * 设置供应商用户是否启用
     *
     * @param setAgentUserActiveRequestDTO
     * @return
     */
    @RequestMapping(value = "/setUserActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setUserActive(@RequestBody @Valid SetAgentUserActiveRequestDTO setAgentUserActiveRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            setAgentUserActiveRequestDTO.setModifier(this.getCacheUser().getUsername());
            responseDTO = agentService.setUserActive(setAgentUserActiveRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }
        return responseDTO;
    }

    /**
     * 修改分销商额度
     *
     * @param modifyAmountRequestDTO
     * @return
     */
    @RequestMapping(value = "/modifyAmount", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO modifyAmount(@RequestBody @Valid ModifyAmountRequestDTO modifyAmountRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            /** 修改后的总额度必须大于等于0 **/
            if (null != modifyAmountRequestDTO.getAmount() && (modifyAmountRequestDTO.getAmount().compareTo(BigDecimal.ZERO) == 0
                    || modifyAmountRequestDTO.getAmount().compareTo(BigDecimal.ZERO) == 1)) {
                modifyAmountRequestDTO.setModifier(this.getCacheUser().getUsername());
                responseDTO = agentService.modifyAmount(modifyAmountRequestDTO);
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            } else {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            }
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }

        return responseDTO;

    }

    /**
     * 扣除分销商剩余额度
     *
     * @param agentCreditLineRequestDTO
     * @return
     */
    @RequestMapping(value = "/deductAgentCreditLine", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deductAgentCreditLine(@Valid DeductAgentCreditLineRequestDTO agentCreditLineRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            /**如果所需扣除的额度不等于null才继续**/
            if (null != agentCreditLineRequestDTO.getDebuctAmount()) {
                agentCreditLineRequestDTO.setCreator(this.getCacheUser().getUsername());
                responseDTO = agentService.deductAgentCreditLine(agentCreditLineRequestDTO);
            } else {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            }
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }
        return responseDTO;
    }

    /**
     * 合同文件上传
     *
     * @param addAgentContractFileRequestDTO
     * @return
     */
    @RequestMapping(value = "/uploadContract")
    public ResponseDTO<AddAgentContractFileResponseDTO> uploadContract(@RequestParam(required = false) MultipartFile file,
                                                                       AddAgentContractFileRequestDTO addAgentContractFileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            addAgentContractFileRequestDTO.setCreateTime(new Date());
            addAgentContractFileRequestDTO.setCreator(this.getCacheUser().getUsername());
            responseDTO = agentService.uploadContractForAgent(file, addAgentContractFileRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

        }
        return responseDTO;
    }

    /**
     * 获取分销商拜访记录列表
     *
     * @param getVisitListQueryDTO
     * @return
     */
    @RequestMapping(value = "/getVisitList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<AgentVisitInfoDTO>> getVisitList(@RequestBody GetVisitListQueryDTO getVisitListQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            getVisitListQueryDTO.setMerchantId(this.getCacheUser().getMerchantId());
            responseDTO = agentService.getVisitList(getVisitListQueryDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询用户日志列表
     *
     * @return
     */
    @RequestMapping(value = "/getUserLog", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AllRoleResponseDTO> getAllRoleList(@RequestBody GetAgentOperLogRequestDTO getAgentOperLogRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = agentService.queryAgentOperLogList(getAgentOperLogRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 分销商下载合同文件
     *
     * @param realPath 合同文件真实路径
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadContract", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@RequestParam(required = true) String realPath, @RequestParam(required = true) Long contractFileId) throws IOException {
        ResponseEntity<byte[]> entity = null;
        if (!"".equals(realPath) && null != contractFileId) {
            entity = agentService.getFile(realPath, contractFileId);
        }
        return entity;
    }

    /**
     * 根据bankCardId删除银行卡信息
     *
     * @param banCardRequestDTO
     * @return
     */
    @RequestMapping(value = "/deleteBankCard", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deleteBankCardById(@RequestBody DeleteAgentBanCardRequestDTO banCardRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (null != banCardRequestDTO.getBankCardId()) {
            Long bankCardId = banCardRequestDTO.getBankCardId();
            responseDTO = agentService.deleteBankCardById(bankCardId);
        } else {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
        }
        return responseDTO;
    }

    /**
     * 获取信用额度列表
     *
     * @param getCreditLinesListRequestDTO
     * @return
     */
    @RequestMapping(value = "/queryCreditLinesList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<GetCreditLinesListResponseDTO>> queryCreditLinesList(@RequestBody @Valid GetCreditLinesListRequestDTO getCreditLinesListRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserDTO userDTO = super.getCacheUser();
            getCreditLinesListRequestDTO.setMerchantId(userDTO.getMerchantId());
            responseDTO = agentService.queryCreditLinesList(getCreditLinesListRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询单个用户
     *
     * @param agentUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AgentUserResponseDTO> getUserInfo(@RequestBody AgentUserRequestDTO agentUserRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
            if (null != agentUserRequestDTO.getUserId()) {
            responseDTO = agentService.getUserInfo(agentUserRequestDTO);
        } else {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
        }
        return responseDTO;
    }


    /**
     * 查询分销商额度调整日志列表
     *
     * @param agentAmountLogRequestDTO
     * @return
     */
    @RequestMapping(value = "/getAmountLog", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<GetAgentAmountLogResponseDTO> queryAgentAmountLogList(@RequestBody @Valid GetAgentAmountLogRequestDTO agentAmountLogRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (null != agentAmountLogRequestDTO.getAgentId()) {
            responseDTO = agentService.queryAgentAmountLogList(agentAmountLogRequestDTO);
        } else {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
        }
        return responseDTO;
    }

    /**
     * 删除合同文件
     *
     * @param deleteAgentContractFileRequestDTO
     * @return
     */
    @RequestMapping(value = "/deleteContract")
    public ResponseDTO deleteContract(@RequestBody DeleteAgentContractFileRequestDTO deleteAgentContractFileRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            responseDTO = agentService.deleteContract(deleteAgentContractFileRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
