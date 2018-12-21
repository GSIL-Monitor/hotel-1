package com.fangcang.product;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.product.request.BatchModifyPriceRequestDTO;
import com.fangcang.product.request.ModifyPriceQuotaStateRequestDTO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import com.fangcang.product.service.PriceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/product")
public class PriceInfoController extends BaseController{

    @Autowired
    private PriceInfoService priceInfoService;

    /**
     * 批量调价
     * @param batchModifyPriceRequestDTO
     * @return
     */
    @RequestMapping(value = "/batchModifyPrice",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO batchModifyPrice(@RequestBody @Valid BatchModifyPriceRequestDTO batchModifyPriceRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            batchModifyPriceRequestDTO.setCreator(super.getFullName());
            batchModifyPriceRequestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
            priceInfoService.batchModifyPrice(batchModifyPriceRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询产品每日价格房态
     * @param productDailyInfoQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryProductDailyInfo",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<ProductDailyInfoResponseDTO> queryProductDailyInfo(@RequestBody ProductDailyInfoQueryDTO productDailyInfoQueryDTO){
        ResponseDTO<ProductDailyInfoResponseDTO> responseDTO = new ResponseDTO<>();
        ResponseDTO<List<ProductDailyInfoResponseDTO>> listResponseDTO = priceInfoService.queryProductDailyInfo(productDailyInfoQueryDTO);
        if(null != listResponseDTO && null != listResponseDTO.getModel()){
            List<ProductDailyInfoResponseDTO> productDailyInfoResponseDTOList = listResponseDTO.getModel();
            responseDTO.setModel(productDailyInfoResponseDTOList.get(0));
        }
        responseDTO.setResult(listResponseDTO.getResult());
        responseDTO.setFailReason(listResponseDTO.getFailReason());
        responseDTO.setFailCode(listResponseDTO.getFailCode());
        return responseDTO;
    }

    /**
     * 单日调整价格和房态配额
     * @param modifyPriceQuotaStateRequestDTO
     * @return
     */
    @RequestMapping(value = "/updatePriceAndQuotaState",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO updatePriceAndQuotaState(@RequestBody ModifyPriceQuotaStateRequestDTO modifyPriceQuotaStateRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO cacheUser = super.getCacheUser();
            modifyPriceQuotaStateRequestDTO.setMerchantCode(cacheUser.getMerchantCode());
            modifyPriceQuotaStateRequestDTO.setCreator(super.getFullName());
            modifyPriceQuotaStateRequestDTO.setModifier(super.getFullName());
            Date saleDate = DateUtil.dateFormat(modifyPriceQuotaStateRequestDTO.getSaleDate(),DateUtil.defaultFormat);
            modifyPriceQuotaStateRequestDTO.setStartDate(saleDate);
            modifyPriceQuotaStateRequestDTO.setEndDate(saleDate);
            priceInfoService.updatePriceAndQuotaState(modifyPriceQuotaStateRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     *
     * @param productDailyInfoQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryDetailByMonth",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<ProductDailyInfoResponseDTO> queryDetailByMonth(@RequestBody ProductDailyInfoQueryDTO productDailyInfoQueryDTO) {
        ResponseDTO<ProductDailyInfoResponseDTO> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        if (null == productDailyInfoQueryDTO.getStartDate() || null == productDailyInfoQueryDTO.getEndDate()
                || null == productDailyInfoQueryDTO.getPricePlanId()){
            log.error("参数为空:{}", JSON.toJSONString(productDailyInfoQueryDTO));
            responseDTO.setErrorCode(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }

        ResponseDTO<List<ProductDailyInfoResponseDTO>> listResponseDTO = priceInfoService.queryProductDailyInfo(productDailyInfoQueryDTO);
        if(null != listResponseDTO && null != listResponseDTO.getModel()){
            List<ProductDailyInfoResponseDTO> productDailyInfoResponseDTOList = listResponseDTO.getModel();
            responseDTO.setModel(productDailyInfoResponseDTOList.get(0));
        }

        return responseDTO;
    }


    @RequestMapping(value = "/updatePriceAndQuotaStateByCalendar",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO updatePriceAndQuotaStateByCalendar(@RequestBody ModifyPriceQuotaStateRequestDTO modifyPriceQuotaStateRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO cacheUser = super.getCacheUser();
            modifyPriceQuotaStateRequestDTO.setMerchantCode(cacheUser.getMerchantCode());
            modifyPriceQuotaStateRequestDTO.setCreator(super.getFullName());
            modifyPriceQuotaStateRequestDTO.setModifier(super.getFullName());
            responseDTO = priceInfoService.updatePriceAndQuotaStateByCalendar(modifyPriceQuotaStateRequestDTO);
        } catch (Exception e) {
            log.error("日历保存价格房态异常:{}",JSON.toJSONString(modifyPriceQuotaStateRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
        return responseDTO;
    }
}
