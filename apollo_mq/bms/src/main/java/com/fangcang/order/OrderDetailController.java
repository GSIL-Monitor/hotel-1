package com.fangcang.order;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.order.dto.OrderOperationLogDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.UnlockRequestDTO;
import com.fangcang.order.response.OrderAttachGroupResponseDTO;
import com.fangcang.order.response.OrderCustomerDTO;
import com.fangcang.order.response.OrderDetailResponseDTO;
import com.fangcang.order.response.OrderNoteGroupResponseDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;
import com.fangcang.order.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@Slf4j
@RequestMapping("/order/detail")
public class OrderDetailController extends BaseController {

    @Resource
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/orderInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<OrderDetailResponseDTO> orderInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getCacheUser().getUsername());
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderDetailService.orderInfo(requestDTO);
        } catch (Exception e) {
            log.error("orderInfo error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryOrderCustomer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<OrderCustomerDTO> queryOrderCustomer(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getCacheUser().getUsername());
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderDetailService.queryOrderCustomer(requestDTO);
        } catch (Exception e) {
            log.error("queryOrderCustomer error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/productInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<SupplyOrderResponseDTO>> productInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderDetailService.productInfo(requestDTO);
        } catch (Exception e) {
            log.error("productInfo error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/attachInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderAttachGroupResponseDTO>> attachInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderDetailService.attachInfo(requestDTO);
        } catch (Exception e) {
            log.error("attachInfo error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/noteInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderNoteGroupResponseDTO>> noteInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderDetailService.noteInfo(requestDTO);
        } catch (Exception e) {
            log.error("noteInfo error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/logInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<OrderOperationLogDTO>> logInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getMerchantCode());
            responseDTO = orderDetailService.logInfo(requestDTO);
        } catch (Exception e) {
            log.error("logInfo error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }


    @RequestMapping(value = "unlock")
    @ResponseBody
    public ResponseDTO unlock(@RequestBody UnlockRequestDTO unlockRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        if (null == unlockRequestDTO || null == unlockRequestDTO.getOrderId()){
            log.error("解锁失败,参数为空,{}",JSON.toJSONString(unlockRequestDTO));
            responseDTO = new ResponseDTO(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }

        try{
            unlockRequestDTO.setLockUser(getCacheUser().getUsername());
            unlockRequestDTO.setCreator(getCacheUser().getRealName());
            unlockRequestDTO.setCreateTime(DateUtil.getCurrentDate());
            responseDTO = orderDetailService.unlock(unlockRequestDTO);
        } catch (Exception e){
            log.error("解锁失败,{}", JSON.toJSONString(unlockRequestDTO),e);
            responseDTO = new ResponseDTO(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }

}
