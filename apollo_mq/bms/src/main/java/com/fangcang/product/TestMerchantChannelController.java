package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.*;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.product.dto.MerchantChannelDTO;
import com.fangcang.product.dto.ShowChannelPriceDTO;
import com.fangcang.product.dto.ShowSaleStateDTO;
import com.fangcang.product.response.MerchantChannelInfoResponseDTO;
import com.fangcang.product.response.ShowChannelPriceResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@RestController
@RequestMapping(value = "/test/product")
public class TestMerchantChannelController {

    @RequestMapping(value = "/queryMerchantChannelInfo",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<MerchantChannelInfoResponseDTO> queryMerchantChannelInfo(){
        MerchantChannelInfoResponseDTO merchantChannelInfoResponseDTO = new MerchantChannelInfoResponseDTO();
        List<MerchantChannelDTO> merchantChannelDTOList = new ArrayList<>();
        MerchantChannelDTO merchantChannelDTO1 = new MerchantChannelDTO();
        merchantChannelDTO1.setChannelCode("B2B");
        merchantChannelDTO1.setChannelCurrency("CNY");
        merchantChannelDTO1.setIsOpen(1);
        merchantChannelDTO1.setMerchantCode("M10000001");

        MerchantChannelDTO merchantChannelDTO2 = new MerchantChannelDTO();
        merchantChannelDTO2.setChannelCode("ctrip");
        merchantChannelDTO2.setChannelCurrency("CNY");
        merchantChannelDTO2.setIsOpen(0);
        merchantChannelDTO2.setMerchantCode("M10000001");

        merchantChannelDTOList.add(merchantChannelDTO1);
        merchantChannelDTOList.add(merchantChannelDTO2);
        merchantChannelInfoResponseDTO.setMerchantChannelList(merchantChannelDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(merchantChannelInfoResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/showChannelPrice",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<ShowChannelPriceResponseDTO> showChannelPrice(){
        ResponseDTO<ShowChannelPriceResponseDTO> responseDTO = new ResponseDTO<>(ResultCodeEnum.SUCCESS.code);
        List<ShowChannelPriceDTO>  showChannelPriceDTOList = new ArrayList<>();
        ShowChannelPriceDTO showChannelPriceDTO = new ShowChannelPriceDTO();
        showChannelPriceDTO.setMerchantCode("M10000001");
        showChannelPriceDTO.setChannelCode(ChannelTypeEnum.B2B.key);
        showChannelPriceDTO.setChannelCurrency("CNY");
        showChannelPriceDTO.setIsOpen(1);
        showChannelPriceDTO.setPriceName("散房低价");
        showChannelPriceDTO.setEnPriceName("basePrice");
        showChannelPriceDTO.setPriceOperateType(ChannelPriceEnum.BASE_PRICE.key);

        ShowChannelPriceDTO showChannelPriceDTO2 = new ShowChannelPriceDTO();
        showChannelPriceDTO2 = PropertyCopyUtil.transfer(showChannelPriceDTO,ShowChannelPriceDTO.class);
        showChannelPriceDTO2.setPriceName("团房低价");
        showChannelPriceDTO2.setEnPriceName("groupBasePrice");
        showChannelPriceDTO2.setPriceOperateType(ChannelPriceEnum.GROUP_BASE_PRICE.key);

        ShowChannelPriceDTO showChannelPriceDTO3 = new ShowChannelPriceDTO();
        showChannelPriceDTO3 = PropertyCopyUtil.transfer(showChannelPriceDTO,ShowChannelPriceDTO.class);
        showChannelPriceDTO3.setPriceName("B2B散房售价");
        showChannelPriceDTO3.setEnPriceName("b2bSalePrice");
        showChannelPriceDTO3.setPriceOperateType(ChannelPriceEnum.B2B_SALE_PRICE.key);

        ShowChannelPriceDTO showChannelPriceDTO4 = new ShowChannelPriceDTO();
        showChannelPriceDTO4 = PropertyCopyUtil.transfer(showChannelPriceDTO,ShowChannelPriceDTO.class);
        showChannelPriceDTO4.setPriceName("B2B团房售价");
        showChannelPriceDTO4.setEnPriceName("groupSalePrice");
        showChannelPriceDTO4.setPriceOperateType(ChannelPriceEnum.GROUP_SALE_PRICE.key);

        showChannelPriceDTOList.add(showChannelPriceDTO);
        showChannelPriceDTOList.add(showChannelPriceDTO2);
        showChannelPriceDTOList.add(showChannelPriceDTO3);
        showChannelPriceDTOList.add(showChannelPriceDTO4);

        List<ShowSaleStateDTO> showSaleStateDTOS = new ArrayList<>();
        ShowSaleStateDTO showSaleStateDTO = new ShowSaleStateDTO();
        showSaleStateDTO.setMerchantCode("M10000001");
        showSaleStateDTO.setChannelCode("B2B");
        showSaleStateDTO.setIsOpen(1);
        showSaleStateDTO.setSaleStateName(ShowSaleStateEnum.B2B.saleStateName);

        ShowSaleStateDTO showSaleStateDTO2 = new ShowSaleStateDTO();
        showSaleStateDTO2.setMerchantCode("M10000001");
        showSaleStateDTO2.setChannelCode(ChannelTypeEnum.CTRIP.key);
        showSaleStateDTO2.setIsOpen(1);
        showSaleStateDTO2.setSaleStateName(ShowSaleStateEnum.CTRIP.saleStateName);
        showSaleStateDTOS.add(showSaleStateDTO);
        showSaleStateDTOS.add(showSaleStateDTO2);

        ShowChannelPriceResponseDTO showChannelPriceResponseDTO = new ShowChannelPriceResponseDTO();
        showChannelPriceResponseDTO.setChannelPriceList(showChannelPriceDTOList);
        showChannelPriceResponseDTO.setSaleStateList(showSaleStateDTOS);
        responseDTO.setModel(showChannelPriceResponseDTO);
        return responseDTO;
    }
}
