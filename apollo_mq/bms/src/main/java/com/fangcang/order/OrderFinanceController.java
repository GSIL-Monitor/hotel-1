package com.fangcang.order;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.finance.financeorder.request.AgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.QueryAgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.order.service.OrderFinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderFinanceController extends BaseController {

    @Resource
    private OrderFinanceService orderFinanceService;

    @RequestMapping(value = "/notifyReceivableAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifyReceivableAmount(@RequestBody @Valid AgentFinanceOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderFinanceService.notifyReceivableAmount(requestDTO);
        } catch (Exception e) {
            log.error("notifyReceivableAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/notifyRefundAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifyRefundAmount(@RequestBody @Valid AgentFinanceOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderFinanceService.notifyRefundAmount(requestDTO);
        } catch (Exception e) {
            log.error("notifyRefundAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryTradeListForOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(@RequestBody @Valid QueryAgentFinanceOrderRequestDTO requestDTO) {
        ResponseDTO<List<QueryTradeListForOrderResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = orderFinanceService.queryTradeListForOrder(requestDTO);
        } catch (Exception e) {
            log.error("queryTradeListForOrder error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/maxNotifyReceivableAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<BigDecimal> maxNotifyReceivableAmount(@RequestBody @Valid QueryAgentFinanceOrderRequestDTO requestDTO) {
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = orderFinanceService.maxNotifyReceivableAmount(requestDTO);
        } catch (Exception e) {
            log.error("maxNotifyReceivableAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/maxNotifyRefundAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<BigDecimal> maxNotifyRefundAmount(@RequestBody @Valid QueryAgentFinanceOrderRequestDTO requestDTO) {
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = orderFinanceService.maxNotifyRefundAmount(requestDTO);
        } catch (Exception e) {
            log.error("maxNotifyRefundAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
