package com.fangcang.finance.financeorder.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.agent.dto.AgentBankCardDTO;
import com.fangcang.agent.request.AgentBankCardRequestDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.response.AgentOrderListResponseDTO;
import com.fangcang.finance.financeorder.response.ConfirmFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.service.AgentFinanceOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Vinney on 2018/7/7.
 */
@Slf4j
@RestController
@RequestMapping("/agentPay")
public class AgentPayController extends BaseController {

    @Autowired
    private AgentFinanceOrderService agentFinanceOrderService;

    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "/getUnreceived",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> getUnreceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){

        ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        try{
            PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupportDTO = agentFinanceOrderService.getUnreceived(singleBalanceQueryDTO);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("待收款查询异常,请求参数:{}",JSON.toJSONString(singleBalanceQueryDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_GET_UNRECEIVED_ERROR);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/getReceived",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> getReceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        try{
            PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupportDTO = agentFinanceOrderService.getReceived(singleBalanceQueryDTO);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("已收款查询异常,请求参数:{}",JSON.toJSONString(singleBalanceQueryDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_GET_UNRECEIVED_ERROR);
        }
        return responseDTO;
    }
    @RequestMapping(value = "/getUnfinished",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> getUnfinished (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        try{
            PaginationSupportDTO<AgentOrderListResponseDTO> paginationSupportDTO = agentFinanceOrderService.getUnfinished(singleBalanceQueryDTO);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("未完成查询异常,请求参数:{}",JSON.toJSONString(singleBalanceQueryDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_GET_UNRECEIVED_ERROR);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/cancelFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO cancelFinanceOrder (@RequestBody @Validated FinanceOrderRequestDTO financeOrderRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        try{
            financeOrderRequestDTO.setCreator(getFullName());
            financeOrderRequestDTO.setModifier(getFullName());
            financeOrderRequestDTO.setOperator(getFullName());
            financeOrderRequestDTO.setModifyTime(DateUtil.getCurrentDate());
            financeOrderRequestDTO.setCreateTime(DateUtil.getCurrentDate());
            agentFinanceOrderService.cancelFinanceOrder(financeOrderRequestDTO);
        }catch (Exception e){
            log.error("取消作废收款失败,请求参数:{}",JSON.toJSONString(financeOrderRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CANCEL_ERROR);
        }

        return responseDTO;
    }

    @RequestMapping(value = "/getAgentBankList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<AgentBankCardDTO>> getAgentBankList (@RequestBody AgentBankCardRequestDTO agentBankCardRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<AgentBankCardDTO> agentBankCardDTOList = agentService.queryAgentBankCardList(agentBankCardRequestDTO.getAgentCode());
        responseDTO.setModel(agentBankCardDTOList);
        return responseDTO;
    }

    @RequestMapping(value = "/confirmFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO confirmFinanceOrder (@RequestBody @Validated ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO){
        log.info("enter 确认收款，{}",JSON.toJSONString(confirmFinanceOrderRequestDTO));
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        try{
            confirmFinanceOrderRequestDTO.setCreator(getFullName());
            confirmFinanceOrderRequestDTO.setModifier(getFullName());
            confirmFinanceOrderRequestDTO.setModifyTime(DateUtil.getCurrentDate());
            confirmFinanceOrderRequestDTO.setCreateTime(DateUtil.getCurrentDate());
            agentFinanceOrderService.confirmFinanceOrder(confirmFinanceOrderRequestDTO);
        }catch (ServiceException e){
            log.error("确认收款失败：请求参数为 {}",JSON.toJSONString(confirmFinanceOrderRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CONFIRM_ERROR);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/getVoucher",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<ConfirmFinanceOrderResponseDTO> getVoucher (@RequestBody @Validated ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO){

        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        try{
            ConfirmFinanceOrderResponseDTO confirmFinanceOrderResponseDTO = agentFinanceOrderService.queryFinanceOrderDetail(confirmFinanceOrderRequestDTO);
            responseDTO.setModel(confirmFinanceOrderResponseDTO);
        }catch (Exception e){
            log.error("查询收款凭证失败,{}",JSON.toJSONString(confirmFinanceOrderRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_QUERY_DETAIL_ERROR);
        }

        return responseDTO;
    }

}
