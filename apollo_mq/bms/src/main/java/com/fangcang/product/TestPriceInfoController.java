package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.product.dto.ProductDailyDTO;
import com.fangcang.product.request.BatchModifyPriceRequestDTO;
import com.fangcang.product.request.ModifyPriceQuotaStateRequestDTO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@RestController
@RequestMapping(value = "/test/product")
public class TestPriceInfoController {

    @RequestMapping(value = "/batchModifyPrice",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO batchModifyPrice(@RequestBody BatchModifyPriceRequestDTO batchModifyPriceRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/queryProductDailyInfo",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<ProductDailyInfoResponseDTO> queryProductDailyInfo(@RequestBody ProductDailyInfoQueryDTO productDailyInfoQueryDTO){
        ProductDailyInfoResponseDTO productDailyInfoResponseDTO = new ProductDailyInfoResponseDTO();
        productDailyInfoResponseDTO.setHotelId(Long.valueOf(100000));
        productDailyInfoResponseDTO.setHotelName("深圳喜马拉雅大酒店");
        productDailyInfoResponseDTO.setRoomTypeId(66666L);
        productDailyInfoResponseDTO.setRoomTypeName("大床房 ");
        productDailyInfoResponseDTO.setPricePlanId(productDailyInfoQueryDTO.getPricePlanId());
        productDailyInfoResponseDTO.setPricePlanName("免费双床-游泳池");
        productDailyInfoResponseDTO.setIsShare(1);
        productDailyInfoResponseDTO.setQuotaAccountId(8888L);
        productDailyInfoResponseDTO.setQuotaAccountName("大床房配额池");

        List<Date> dateList = null;
        if(null == productDailyInfoQueryDTO.getStartDate() || null == productDailyInfoQueryDTO.getEndDate()){
            dateList = DateUtil.getDateList(DateUtil.getCurrentDate(),DateUtil.getCurrentDate());
        }else{
            dateList = DateUtil.getDateList(productDailyInfoQueryDTO.getStartDate(),productDailyInfoQueryDTO.getEndDate());
        }
        List<ProductDailyDTO> productDailyDTOList = new ArrayList<>();
        for (Date date : dateList) {
            ProductDailyDTO productDailyDTO = new ProductDailyDTO();
            productDailyDTO.setSaleDate(DateUtil.dateFormat(date,DateUtil.defaultFormat));
            BigDecimal basePrice = new BigDecimal(10);
            BigDecimal salePrice = new BigDecimal(30);
            productDailyDTO.setBasePrice(basePrice);
            productDailyDTO.setGroupBasePrice(basePrice);
            productDailyDTO.setGroupSalePrice(salePrice);
            productDailyDTO.setB2bSalePrice(salePrice);
            productDailyDTOList.add(productDailyDTO);
        }
        productDailyInfoResponseDTO.setProductDailyList(productDailyDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(productDailyInfoResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/updatePriceAndQuotaState",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO updatePriceAndQuotaState(@RequestBody ModifyPriceQuotaStateRequestDTO modifyPriceQuotaStateRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }
}
