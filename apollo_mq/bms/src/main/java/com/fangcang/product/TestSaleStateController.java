package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.SaleStateEnum;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.request.SaleStateRequestDTO;
import com.fangcang.product.response.SaleStateResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ASUS on 2018/5/19.
 */
@RestController
@RequestMapping(value = "/test/product")
public class TestSaleStateController {

    @RequestMapping(value = "/pricePlanIsOnSale",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SaleStateResponseDTO> pricePlanIsOnSale(@RequestBody @Valid PricePlanQueryDTO pricePlanQueryDTO){
        SaleStateResponseDTO saleStateResponseDTO = new SaleStateResponseDTO();
        saleStateResponseDTO.setB2bSaleState(SaleStateEnum.ON_SALE.key);
        saleStateResponseDTO.setCtripSaleState(SaleStateEnum.OFF_SALE.key);
        saleStateResponseDTO.setQunarSaleState(SaleStateEnum.NO_OPEN.key);
        saleStateResponseDTO.setElongSaleState(SaleStateEnum.ON_SALE.key);
        saleStateResponseDTO.setTongchengSaleState(SaleStateEnum.OFF_SALE.key);
        saleStateResponseDTO.setTuniuSaleState(SaleStateEnum.NO_SHOW.key);
        saleStateResponseDTO.setXmdSaleState(SaleStateEnum.NO_OPEN.key);
        saleStateResponseDTO.setJdSaleState(SaleStateEnum.ON_SALE.key);
        saleStateResponseDTO.setTaobaoSaleState(SaleStateEnum.OFF_SALE.key);
        saleStateResponseDTO.setIsOnSale(SaleStateEnum.ON_SALE.key);
        saleStateResponseDTO.setPricePlanId(pricePlanQueryDTO.getPricePlanId());
        saleStateResponseDTO.setQunarB2BSaleState(SaleStateEnum.NO_SHOW.key);
        saleStateResponseDTO.setQunarNgtSaleState(SaleStateEnum.NO_OPEN.key);
        saleStateResponseDTO.setQunarUsdSaleState(SaleStateEnum.OFF_SALE.key);
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(saleStateResponseDTO);
        return  responseDTO;
    }

    @RequestMapping(value = "/batchOnSale",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO batchOnSale(@RequestBody SaleStateRequestDTO saleStateRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }
}
