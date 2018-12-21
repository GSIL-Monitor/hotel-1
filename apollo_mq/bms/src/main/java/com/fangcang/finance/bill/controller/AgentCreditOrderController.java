package com.fangcang.finance.bill.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.bill.request.QueryCheckOutDTO;
import com.fangcang.finance.bill.service.AgentCreditOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping(("/finance/creditOrder"))
public class AgentCreditOrderController extends BaseController {

    @Autowired
    private AgentCreditOrderService agentCreditOrderService;

    /**
     * 查询分销商可出账信息
     */
    @RequestMapping(value = "/queryAgentCheckOut", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentCheckOut(@RequestBody QueryCheckOutDTO requestDTO) {
        log.info("queryAgentCheckOut param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=agentCreditOrderService.queryAgentCheckOut(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentCreditOrderService.queryAgentCheckOut 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询可出账的订单
     */
    @RequestMapping(value = "/queryCheckOutOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryCheckOutOrder(@RequestBody QueryCheckOutDTO requestDTO) {
        log.info("queryCheckOutOrder param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getMerchantCode());
            requestDTO.setAccountStatusList(Arrays.asList(0,1));
            PaginationSupportDTO paginationSupportDTO=agentCreditOrderService.queryCheckOutOrder(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("agentCreditOrderService.queryCheckOutOrder 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
