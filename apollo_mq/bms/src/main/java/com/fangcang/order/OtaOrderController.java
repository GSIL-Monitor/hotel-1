package com.fangcang.order;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.InitData;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.order.request.AddOrderReqRequestDTO;
import com.fangcang.order.request.ChangeChannelOrderCodeRequestDTO;
import com.fangcang.order.request.CreateOrderRequestDTO;
import com.fangcang.order.request.OrderCancelRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.response.OrderDetailResponseDTO;
import com.fangcang.order.service.OrderDetailService;
import com.fangcang.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@RestController
@Slf4j
@RequestMapping("/otaOrder")
public class OtaOrderController extends BaseController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO create(@RequestBody @Valid CreateOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreateTime(new Date());
            requestDTO.setMerchantName(InitData.MERCHANT_CODE_NAME_MAP.get(requestDTO.getMerchantCode()));
            responseDTO = orderService.addManualOrder(requestDTO);
        } catch (Exception e) {
            log.error("otaOrder create error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO cancel(@RequestBody @Valid OrderCancelRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setModifyTime(new Date());
            responseDTO = orderService.cancelOrder(requestDTO);
        } catch (Exception e) {
            log.error("otaOrder cancel error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/orderInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<OrderDetailResponseDTO> orderInfo(@RequestBody @Valid OrderDetailRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = orderDetailService.orderInfo(requestDTO);
        } catch (Exception e) {
            log.error("otaOrderInfo error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/addOrderRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<OrderDetailResponseDTO> addOrderRequest(@RequestBody @Valid AddOrderReqRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreateTime(DateUtil.dateToStringWithHms(new Date()));
            responseDTO = orderService.addOrderRequest(requestDTO);
        } catch (Exception e) {
            log.error("addOrderRequest error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/changeChannelOrderCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO changeChannelOrderCode(@RequestBody @Valid ChangeChannelOrderCodeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setModifyTime(new Date());
            responseDTO = orderService.changeChannelOrderCode(requestDTO);
        } catch (Exception e) {
            log.error("changeChannelOrderCode error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
