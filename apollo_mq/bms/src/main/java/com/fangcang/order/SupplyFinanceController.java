package com.fangcang.order;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.finance.financeorder.request.QuerySupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QueryTradeListForOrderResponseDTO;
import com.fangcang.order.service.SupplyFinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@Slf4j
@RequestMapping("/supplyOrder")
public class SupplyFinanceController extends BaseController {

    @Resource
    private SupplyFinanceService supplyFinanceService;

    @RequestMapping(value = "/notifyPayableAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifyPayableAmount(@RequestBody @Valid SupplyFinanceOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = supplyFinanceService.notifyPayableAmount(requestDTO);
        } catch (Exception e) {
            log.error("notifyPayableAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/notifyReceiptAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO notifyReceiptAmount(@RequestBody @Valid SupplyFinanceOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getFullName());
            requestDTO.setCreateTime(DateUtil.getCurrentDate());
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(DateUtil.getCurrentDate());
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = supplyFinanceService.notifyReceiptAmount(requestDTO);
        } catch (Exception e) {
            log.error("notifyReceiptAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryTradeListForOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<QueryTradeListForOrderResponseDTO>> queryTradeListForOrder(@RequestBody @Valid QuerySupplyFinanceOrderRequestDTO requestDTO) {
        ResponseDTO<List<QueryTradeListForOrderResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = supplyFinanceService.queryTradeListForOrder(requestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
            log.error("queryTradeListForOrder error:", e);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/maxNotifyPayableAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<BigDecimal> maxNotifyPayableAmount(@RequestBody @Valid QuerySupplyFinanceOrderRequestDTO requestDTO) {
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = supplyFinanceService.maxNotifyPayableAmount(requestDTO);
        } catch (Exception e) {
            log.error("maxNotifyPayableAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/maxNotifyReceiptAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<BigDecimal> maxNotifyReceiptAmount(@RequestBody @Valid QuerySupplyFinanceOrderRequestDTO requestDTO) {
        ResponseDTO<BigDecimal> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = supplyFinanceService.maxNotifyReceiptAmount(requestDTO);
        } catch (Exception e) {
            log.error("maxNotifyReceiptAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
