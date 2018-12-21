package com.fangcang.product.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.*;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.dto.MerchantChannelDTO;
import com.fangcang.product.dto.ShowChannelPriceDTO;
import com.fangcang.product.dto.ShowSaleStateDTO;
import com.fangcang.product.mapper.MerchantSaleChannelMapper;
import com.fangcang.product.response.MerchantChannelInfoResponseDTO;
import com.fangcang.product.response.ShowChannelPriceResponseDTO;
import com.fangcang.product.service.MerchantSaleChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@Slf4j
@Service
public class MerchantSaleChannelServiceImpl implements MerchantSaleChannelService {

    @Autowired
    private MerchantSaleChannelMapper merchantSaleChannelMapper;

    @Override
    public ResponseDTO<MerchantChannelInfoResponseDTO> queryMerchantSaleChannelInfo(MerchantSaleChannelDO merchantSaleChannelDO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            MerchantChannelInfoResponseDTO merchantChannelInfoResponseDTO = new MerchantChannelInfoResponseDTO();
            List<MerchantSaleChannelDO> merchantSaleChannelList = merchantSaleChannelMapper.queryMerchantSaleChannelInfo(merchantSaleChannelDO);
            if(!CollectionUtils.isEmpty(merchantSaleChannelList)){
                List<MerchantChannelDTO> merchantChannelDTOList = new ArrayList<>();
                merchantChannelDTOList = PropertyCopyUtil.transferArray(merchantSaleChannelList,MerchantChannelDTO.class);
                merchantChannelInfoResponseDTO.setMerchantChannelList(merchantChannelDTOList);
                responseDTO.setModel(merchantChannelInfoResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryMerchantSaleChannelInfo",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    public ResponseDTO<ShowChannelPriceResponseDTO> showChannelPrice(MerchantSaleChannelDO merchantSaleChannelDO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            List<MerchantSaleChannelDO> merchantSaleChannelList = merchantSaleChannelMapper.queryMerchantSaleChannelInfo(merchantSaleChannelDO);
            if(!CollectionUtils.isEmpty(merchantSaleChannelList)){
                ShowChannelPriceResponseDTO showChannelPriceResponseDTO = new ShowChannelPriceResponseDTO();
                List<ShowChannelPriceDTO> channelPriceList = new ArrayList<>();
                List<ShowSaleStateDTO> showSaleStateDTOS = new ArrayList<>();
                for (MerchantSaleChannelDO saleChannelDO : merchantSaleChannelList) {
                    String channelCode = saleChannelDO.getChannelCode();
                    ShowSaleStateDTO showSaleStateDTO = null;
                    if(ChannelTypeEnum.B2B.key.equals(channelCode)){
                        //展示产品价格用
                        //散房低价
                        ShowChannelPriceDTO basePriceDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.BASE_PRICE);
                        //散房售价
                        ShowChannelPriceDTO salePriceDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.B2B_SALE_PRICE);
                        //团房低价
                        ShowChannelPriceDTO groupBaseDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.GROUP_BASE_PRICE);
                        //团房售价
                        ShowChannelPriceDTO groupSaleDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.GROUP_SALE_PRICE);
                        channelPriceList.add(basePriceDTO);
                        channelPriceList.add(salePriceDTO);
                        //mq不需要团房
                        /*channelPriceList.add(groupBaseDTO);
                        channelPriceList.add(groupSaleDTO);*/

                        //前端渠道上下架用
                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.B2B.saleStateName);
                    }else if(ChannelTypeEnum.CTRIP.key.equals(channelCode)){
                        //携程
                        ShowChannelPriceDTO ctripDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.CTRIP_SALE_PRICE);
                        channelPriceList.add(ctripDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.CTRIP.saleStateName);
                    }else if(ChannelTypeEnum.QUNAR.key.equals(channelCode)){
                        //去哪儿
                        ShowChannelPriceDTO qunarDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.QUNAR_SALE_PRICE);
                        channelPriceList.add(qunarDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.QUNAR.saleStateName);
                    }else if (ChannelTypeEnum.ELONG.key.equals(channelCode)){
                        //艺龙
                        ShowChannelPriceDTO elongDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.ELONG_SALE_PRICE);
                        channelPriceList.add(elongDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.ELONG.saleStateName);
                    }else if(ChannelTypeEnum.TONGCHENG.key.equals(channelCode)){
                        //同程
                        ShowChannelPriceDTO tongchengDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.TONGCHENG_SALE_PRICE);
                        channelPriceList.add(tongchengDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.TONGCHENG.saleStateName);
                    }else if(ChannelTypeEnum.TUNIU.key.equals(channelCode)){
                        //途牛
                        ShowChannelPriceDTO tuniuDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.TUNIU_SALE_PRICE);
                        channelPriceList.add(tuniuDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.TUNIU.saleStateName);
                    }else if(ChannelTypeEnum.XMD.key.equals(channelCode)){
                        //新美大
                        ShowChannelPriceDTO xmdDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.XMD_SALE_PRICE);
                        channelPriceList.add(xmdDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.XMD.saleStateName);
                    }else if(ChannelTypeEnum.JD.key.equals(channelCode)){
                        //京东
                        ShowChannelPriceDTO jdDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.JD_SALE_PRICE);
                        channelPriceList.add(jdDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.JD.saleStateName);
                    }else if(ChannelTypeEnum.TAOBAO.key.equals(channelCode)){
                        //淘宝
                        ShowChannelPriceDTO taobaoDTO = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.TAOBAO_SALE_PRICE);
                        channelPriceList.add(taobaoDTO);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.TAOBAO.saleStateName);
                    }else if(ChannelTypeEnum.QUNAR_B2B.key.equals(channelCode)){
                        //去哪儿B2B
                        ShowChannelPriceDTO qunarB2B = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.QUNAR_B2B_SALE_PRICE);
                        channelPriceList.add(qunarB2B);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.QUNAR_B2B.saleStateName);
                    }else if(ChannelTypeEnum.QUNAR_NGT.key.equals(channelCode)){
                        //去哪儿夜宵
                        ShowChannelPriceDTO qunarNgt = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.QUNAR_NGT_SALE_PRICE);
                        channelPriceList.add(qunarNgt);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.QUNAR_NGT.saleStateName);
                    }else if(ChannelTypeEnum.QUNAR_USD.key.equals(channelCode)){
                        //去哪儿美元
                        ShowChannelPriceDTO qunarUsd = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.QUNAR_USD_SALE_PRICE);
                        channelPriceList.add(qunarUsd);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.QUNAR_USD.saleStateName);
                    }else if(ChannelTypeEnum.QUNAR_SON.key.equals(channelCode)){
                        //去哪儿美元
                        ShowChannelPriceDTO qunarSon = getShowChannelPriceDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), saleChannelDO.getChannelCurrency(),
                                ChannelPriceEnum.QUNAR_SON_SALE_PRICE);
                        channelPriceList.add(qunarSon);

                        showSaleStateDTO = getShowSaleStateDTO(saleChannelDO.getMerchantCode(), channelCode,
                                saleChannelDO.getIsOpen(), ShowSaleStateEnum.QUNAR_SON.saleStateName);
                    }
                    showSaleStateDTOS.add(showSaleStateDTO);
                }
                showChannelPriceResponseDTO.setChannelPriceList(channelPriceList);
                showChannelPriceResponseDTO.setSaleStateList(showSaleStateDTOS);
                responseDTO.setModel(showChannelPriceResponseDTO);
            }
        } catch (Exception e) {
            log.error("showChannelPrice has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    private ShowChannelPriceDTO getShowChannelPriceDTO(String merchantCode,String channelCode,
                                                       Integer isOpen,String channelCurrency,
                                                       ChannelPriceEnum channelPriceEnum){
        ShowChannelPriceDTO showChannelPriceDTO = new ShowChannelPriceDTO();
        showChannelPriceDTO.setEnPriceName(channelPriceEnum.enPriceName);
        showChannelPriceDTO.setPriceName(channelPriceEnum.priceName);
        showChannelPriceDTO.setIsOpen(isOpen);
        showChannelPriceDTO.setMerchantCode(merchantCode);
        showChannelPriceDTO.setChannelCurrency(channelCurrency);
        showChannelPriceDTO.setChannelCode(channelCode);
        showChannelPriceDTO.setPriceOperateType(channelPriceEnum.key);
        return showChannelPriceDTO;
    }

    private ShowSaleStateDTO getShowSaleStateDTO(String merchantCode,String channelCode,
                                                 Integer isOpen,String saleStateName){
        ShowSaleStateDTO showSaleStateDTO = new ShowSaleStateDTO();
        showSaleStateDTO.setMerchantCode(merchantCode);
        showSaleStateDTO.setChannelCode(channelCode);
        showSaleStateDTO.setSaleStateName(saleStateName);
        showSaleStateDTO.setIsOpen(isOpen);
        return  showSaleStateDTO;
    }
}
