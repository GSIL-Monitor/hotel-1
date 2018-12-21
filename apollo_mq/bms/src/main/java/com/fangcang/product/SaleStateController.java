package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.SaleStateEnum;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.request.SaleStateRequestDTO;
import com.fangcang.product.response.SaleStateResponseDTO;
import com.fangcang.product.service.SaleStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ASUS on 2018/5/19.
 */
@RestController
@RequestMapping(value = "/product")
public class SaleStateController extends BaseController{

    @Autowired
    private SaleStateService saleStateService;
    /**
     * 价格计划上下架详情
     * @param pricePlanQueryDTO
     * @return
     */
    @RequestMapping(value = "/pricePlanIsOnSale",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SaleStateResponseDTO> pricePlanIsOnSale(@RequestBody @Valid PricePlanQueryDTO pricePlanQueryDTO){
        //TODO 商家编码
        MerchantSaleChannelDO merchantSaleChannelDO = new MerchantSaleChannelDO();
        merchantSaleChannelDO.setMerchantCode(super.getCacheUser().getMerchantCode());
        return  saleStateService.pricePlanIsOnSale(pricePlanQueryDTO,merchantSaleChannelDO);
    }

    /**
     * 批量上下架
     * @param saleStateRequestDTO
     * @return
     */
    @RequestMapping(value = "/batchOnSale",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO batchOnSale(@RequestBody SaleStateRequestDTO saleStateRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserDTO userDTO = super.getCacheUser();
            responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            saleStateRequestDTO.setCreator(super.getFullName());
            saleStateRequestDTO.setModifier(super.getFullName());
            saleStateRequestDTO.setMerchantCode(userDTO.getMerchantCode());
            responseDTO = saleStateService.batchOnSale(saleStateRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
