package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.dto.MerchantChannelDTO;
import com.fangcang.product.response.MerchantChannelInfoResponseDTO;
import com.fangcang.product.response.ShowChannelPriceResponseDTO;
import com.fangcang.product.service.MerchantSaleChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@RestController
@RequestMapping(value = "/product")
public class MerchantChannelController extends BaseController{

    @Autowired
    private MerchantSaleChannelService merchantSaleChannelService;

    /**
     * 查询商家售卖渠道信息
     * @return
     */
    @RequestMapping(value = "/queryMerchantChannelInfo",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<MerchantChannelInfoResponseDTO> queryMerchantChannelInfo(){
        MerchantSaleChannelDO merchantSaleChannelDO = new MerchantSaleChannelDO();
        merchantSaleChannelDO.setMerchantCode(super.getCacheUser().getMerchantCode());
        return merchantSaleChannelService.queryMerchantSaleChannelInfo(merchantSaleChannelDO);
    }

    /**
     * 显示渠道价格  前端展示渠道价格用
     * @return
     */
    @RequestMapping(value = "/showChannelPrice",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<ShowChannelPriceResponseDTO> showChannelPrice(){
        MerchantSaleChannelDO merchantSaleChannelDO = new MerchantSaleChannelDO();
        merchantSaleChannelDO.setMerchantCode(super.getCacheUser().getMerchantCode());
        return merchantSaleChannelService.showChannelPrice(merchantSaleChannelDO);
    }
}
