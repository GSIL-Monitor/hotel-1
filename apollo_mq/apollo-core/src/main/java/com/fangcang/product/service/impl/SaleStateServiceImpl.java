package com.fangcang.product.service.impl;

import com.fangcang.common.IncrementDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.SaleStateEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.IncrementConfig;
import com.fangcang.common.util.URLSplitUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.domain.SaleStateDO;
import com.fangcang.product.domain.SaleStateLogDO;
import com.fangcang.product.dto.SaleStateDTO;
import com.fangcang.product.mapper.MerchantSaleChannelMapper;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.mapper.SaleStateLogMapper;
import com.fangcang.product.mapper.SaleStateMapper;
import com.fangcang.product.request.DynamicPricePlanQueryDTO;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.request.SaleStateRequestDTO;
import com.fangcang.product.response.SaleStateResponseDTO;
import com.fangcang.product.service.IncrementService;
import com.fangcang.product.service.SaleStateService;
import com.fangcang.product.thread.IncrementThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/5/23.
 */
@Service
@Slf4j
public class SaleStateServiceImpl implements SaleStateService {

    @Autowired
    private SaleStateMapper saleStateMapper;

    @Autowired
    private MerchantSaleChannelMapper merchantSaleChannelMapper;

    @Autowired
    private SaleStateLogMapper saleStateLogMapper;

    @Autowired
    private PricePlanMapper pricePlanMapper;

    @Resource(name = "incrementExecutor")
    private ThreadPoolTaskExecutor incrementExecutor;

    @Autowired
    private IncrementConfig incrementConfig;

    @Autowired
    private IncrementService incrementService;

    @Override
    public ResponseDTO<SaleStateResponseDTO> pricePlanIsOnSale(PricePlanQueryDTO pricePlanQueryDTO,MerchantSaleChannelDO merchantSaleChannelDO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        try {
            Map<String,MerchantSaleChannelDO> merchantSaleChannelMap = new HashMap<String,MerchantSaleChannelDO>();
            List<MerchantSaleChannelDO> merchantSaleChannelList = merchantSaleChannelMapper.queryMerchantSaleChannelInfo(merchantSaleChannelDO);
            if(!CollectionUtils.isEmpty(merchantSaleChannelList)){
                for (MerchantSaleChannelDO merchantSaleChannel : merchantSaleChannelList) {
                    merchantSaleChannelMap.put(merchantSaleChannel.getChannelCode(),merchantSaleChannel);
                }
            }

            SaleStateDO saleStateDO = new SaleStateDO();
            saleStateDO.setPricePlanId(pricePlanQueryDTO.getPricePlanId());
            SaleStateDO saleStateResult = saleStateMapper.queryPricePlanSaleStateInfo(saleStateDO);
            Integer isOnSale = SaleStateEnum.OFF_SALE.key;
            SaleStateResponseDTO saleStateResponseDTO = new SaleStateResponseDTO();
            if(null != saleStateResult){
                saleStateResponseDTO = PropertyCopyUtil.transfer(saleStateResult,SaleStateResponseDTO.class);
            }
            saleStateResponseDTO.setPricePlanId(pricePlanQueryDTO.getPricePlanId());
            /**
             * 售卖状态查出来为空
             *      如果商家渠道已配置开通，则默认展示下架
             *      如果商家渠道已配置未开通，则展示未开通
             *      如果商家渠道没有配置 ，则不需要展示
             */
            if(null == saleStateResponseDTO.getB2bSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.B2B, merchantSaleChannelMap);
                saleStateResponseDTO.setB2bSaleState(saleState);
            }else if(null != saleStateResponseDTO.getB2bSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getB2bSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //携程渠道
            if(null == saleStateResponseDTO.getCtripSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.CTRIP, merchantSaleChannelMap);
                saleStateResponseDTO.setCtripSaleState(saleState);
            }else if(null != saleStateResponseDTO.getCtripSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getCtripSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //去哪儿渠道
            if(null == saleStateResponseDTO.getQunarSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.QUNAR, merchantSaleChannelMap);
                saleStateResponseDTO.setQunarSaleState(saleState);
            }else if(null != saleStateResponseDTO.getQunarSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getQunarSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //艺龙渠道
            if(null == saleStateResponseDTO.getElongSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.ELONG, merchantSaleChannelMap);
                saleStateResponseDTO.setElongSaleState(saleState);
            }else if(null != saleStateResponseDTO.getElongSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getElongSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //同程渠道
            if(null == saleStateResponseDTO.getTongchengSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.TONGCHENG, merchantSaleChannelMap);
                saleStateResponseDTO.setTongchengSaleState(saleState);
            }else if(null != saleStateResponseDTO.getTongchengSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getTongchengSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //途牛渠道
            if(null == saleStateResponseDTO.getTuniuSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.TUNIU, merchantSaleChannelMap);
                saleStateResponseDTO.setTuniuSaleState(saleState);
            }else if(null != saleStateResponseDTO.getTuniuSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getTuniuSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //新美大渠道
            if(null == saleStateResponseDTO.getXmdSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.XMD, merchantSaleChannelMap);
                saleStateResponseDTO.setXmdSaleState(saleState);
            }else if(null != saleStateResponseDTO.getXmdSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getXmdSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //京东渠道
            if(null == saleStateResponseDTO.getJdSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.JD, merchantSaleChannelMap);
                saleStateResponseDTO.setJdSaleState(saleState);
            }else if(null != saleStateResponseDTO.getJdSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getJdSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //淘宝渠道
            if(null == saleStateResponseDTO.getTaobaoSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.TAOBAO, merchantSaleChannelMap);
                saleStateResponseDTO.setTaobaoSaleState(saleState);
            }else if(null != saleStateResponseDTO.getTaobaoSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getTaobaoSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //去哪儿大B
            if(null == saleStateResponseDTO.getQunarB2BSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.QUNAR_B2B, merchantSaleChannelMap);
                saleStateResponseDTO.setQunarB2BSaleState(saleState);
            }else if(null != saleStateResponseDTO.getQunarB2BSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getQunarB2BSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //去哪儿夜宵
            if(null == saleStateResponseDTO.getQunarNgtSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.QUNAR_NGT, merchantSaleChannelMap);
                saleStateResponseDTO.setQunarNgtSaleState(saleState);
            }else if(null != saleStateResponseDTO.getQunarNgtSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getQunarNgtSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //去哪儿美元渠道
            if(null == saleStateResponseDTO.getQunarUsdSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.QUNAR_USD, merchantSaleChannelMap);
                saleStateResponseDTO.setQunarUsdSaleState(saleState);
            }else if(null != saleStateResponseDTO.getQunarUsdSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getQunarUsdSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            //去哪儿子渠道
            if(null == saleStateResponseDTO.getQunarSonSaleState()){
                Integer saleState = getSaleState(ChannelTypeEnum.QUNAR_SON, merchantSaleChannelMap);
                saleStateResponseDTO.setQunarSonSaleState(saleState);
            }else if(null != saleStateResponseDTO.getQunarSonSaleState() && SaleStateEnum.ON_SALE.key == saleStateResponseDTO.getQunarSonSaleState()){
                isOnSale = SaleStateEnum.ON_SALE.key;
            }
            saleStateResponseDTO.setIsOnSale(isOnSale);
            responseDTO.setModel(saleStateResponseDTO);
        } catch (Exception e) {
            log.error("pricePlanIsOnSale",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    private Integer getSaleState(ChannelTypeEnum channelTypeEnum,Map<String,MerchantSaleChannelDO> merchantSaleChannelMap){
        if(merchantSaleChannelMap.containsKey(channelTypeEnum.key) ){
            MerchantSaleChannelDO merchantSaleChannelDB = merchantSaleChannelMap.get(channelTypeEnum.key);
            if(null != merchantSaleChannelDB.getIsOpen() && 1 == merchantSaleChannelDB.getIsOpen()){
                return SaleStateEnum.OFF_SALE.key;//下架
            }else if(null != merchantSaleChannelDB.getIsOpen() && 0 == merchantSaleChannelDB.getIsOpen()){
                return SaleStateEnum.NO_OPEN.key;//未开通
            }
        }else{
            return SaleStateEnum.NO_SHOW.key;//不展示
        }
        return SaleStateEnum.NO_SHOW.key;//不展示
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO batchOnSale(SaleStateRequestDTO saleStateRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            List<SaleStateLogDO> saleStateLogDOList = new ArrayList<>();
            List<SaleStateDO> saleStateDOList = new ArrayList<>();
            List<SaleStateDTO> saleStateList = saleStateRequestDTO.getSaleStateList();
            List<IncrementDTO> incrementDTOList = new ArrayList<>();//增量推送
            StringBuilder pricePlanIds = new StringBuilder();
            for (SaleStateDTO saleStateDTO : saleStateList) {
                Boolean hasIncrement = false;
                if(null == saleStateDTO.getPricePlanId()){
                    continue;
                }
                SaleStateDO saleStateDO = new SaleStateDO();
                saleStateDO.setPricePlanId(saleStateDTO.getPricePlanId());
                //B2B渠道
                if(null != saleStateDTO.getB2bSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getB2bSaleState()){
                    saleStateDO.setB2bSaleState(saleStateDTO.getB2bSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.B2B.key, saleStateDTO.getB2bSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //携程渠道
                if(null != saleStateDTO.getCtripSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getCtripSaleState()){
                    saleStateDO.setCtripSaleState(saleStateDTO.getCtripSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.CTRIP.key, saleStateDTO.getCtripSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);

                    hasIncrement = true;

                }
                //去哪儿渠道
                if(null != saleStateDTO.getQunarSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getQunarSaleState()){
                    saleStateDO.setQunarSaleState(saleStateDTO.getQunarSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.QUNAR.key, saleStateDTO.getQunarSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);

                    hasIncrement = true;
                }
                //艺龙渠道
                if(null != saleStateDTO.getElongSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getElongSaleState()){
                    saleStateDO.setElongSaleState(saleStateDTO.getElongSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.ELONG.key, saleStateDTO.getElongSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //同程售卖渠道
                if(null != saleStateDTO.getTongchengSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getTongchengSaleState()){
                    saleStateDO.setTongchengSaleState(saleStateDTO.getTongchengSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.TONGCHENG.key, saleStateDTO.getTongchengSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //途牛渠道
                if(null != saleStateDTO.getTuniuSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getTuniuSaleState()){
                    saleStateDO.setTuniuSaleState(saleStateDTO.getTuniuSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.TUNIU.key, saleStateDTO.getTuniuSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //新美大
                if(null != saleStateDTO.getXmdSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getXmdSaleState()){
                    saleStateDO.setXmdSaleState(saleStateDTO.getXmdSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.XMD.key, saleStateDTO.getXmdSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //京东
                if(null != saleStateDTO.getJdSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getJdSaleState()){
                    saleStateDO.setJdSaleState(saleStateDTO.getJdSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.JD.key, saleStateDTO.getJdSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //淘宝
                if(null != saleStateDTO.getTaobaoSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getTaobaoSaleState()){
                    saleStateDO.setTaobaoSaleState(saleStateDTO.getTaobaoSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.TAOBAO.key, saleStateDTO.getTaobaoSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //去哪儿大B渠道
                if(null != saleStateDTO.getQunarB2BSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getQunarB2BSaleState()){
                    saleStateDO.setQunarB2BSaleState(saleStateDTO.getQunarB2BSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.QUNAR_B2B.key, saleStateDTO.getQunarB2BSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //去哪儿夜销
                if(null != saleStateDTO.getQunarNgtSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getQunarNgtSaleState()){
                    saleStateDO.setQunarNgtSaleState(saleStateDTO.getQunarNgtSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.QUNAR_NGT.key, saleStateDTO.getQunarNgtSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //去哪儿美元渠道
                if(null != saleStateDTO.getQunarUsdSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getQunarUsdSaleState()){
                    saleStateDO.setQunarUsdSaleState(saleStateDTO.getQunarUsdSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.QUNAR_USD.key, saleStateDTO.getQunarUsdSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);
                }
                //去哪儿子渠道
                if(null != saleStateDTO.getQunarSonSaleState() && SaleStateEnum.NO_CHANGED.key != saleStateDTO.getQunarSonSaleState()){
                    saleStateDO.setQunarSonSaleState(saleStateDTO.getQunarSonSaleState());
                    SaleStateLogDO saleStateLogDO = getSaleStateLogDO(saleStateDTO.getPricePlanId(), saleStateRequestDTO.getMerchantCode(),
                            ChannelTypeEnum.QUNAR_SON.key, saleStateDTO.getQunarSonSaleState(), saleStateRequestDTO.getCreator());
                    saleStateLogDOList.add(saleStateLogDO);

                    hasIncrement = true;
                }
                saleStateDO.setModifier(saleStateRequestDTO.getModifier());
                saleStateDO.setCreator(saleStateRequestDTO.getCreator());

                if(null == saleStateDO.getB2bSaleState()
                        && null == saleStateDO.getCtripSaleState()
                        && null == saleStateDO.getQunarSaleState()
                        && null == saleStateDO.getElongSaleState()
                        && null == saleStateDO.getTongchengSaleState()
                        && null == saleStateDO.getTuniuSaleState()
                        && null == saleStateDO.getXmdSaleState()
                        && null == saleStateDO.getJdSaleState()
                        && null == saleStateDO.getTaobaoSaleState()
                        && null == saleStateDO.getQunarB2BSaleState()
                        && null == saleStateDO.getQunarNgtSaleState()
                        && null == saleStateDO.getQunarUsdSaleState()
                        && null == saleStateDO.getQunarSonSaleState()){
                    continue;
                }
                if(hasIncrement){
                    pricePlanIds.append(saleStateDTO.getPricePlanId()).append(",");
                    IncrementDTO incrementDTO = new IncrementDTO();
                    incrementDTO.setMerchantCode(saleStateRequestDTO.getMerchantCode());
                    incrementDTO.setMRatePlanId(saleStateDTO.getPricePlanId());
                    incrementDTOList.add(incrementDTO);
                }
                saleStateDOList.add(saleStateDO);
            }
            if(!CollectionUtils.isEmpty(saleStateDOList)){
                saleStateMapper.batchOnSale(saleStateDOList);
            }
            if(!CollectionUtils.isEmpty(saleStateLogDOList)){
                saleStateLogMapper.batchSaveSaleStateLog(saleStateLogDOList);
            }
            if(!CollectionUtils.isEmpty(incrementDTOList)){
                DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO = new DynamicPricePlanQueryDTO();
                dynamicPricePlanQueryDTO.setPricePlanIds(pricePlanIds.toString().substring(0,pricePlanIds.toString().length() - 1));
                List<PricePlanDO> pricePlanDOList = pricePlanMapper.dynamicQueryPricePlanList(dynamicPricePlanQueryDTO);
                Map<Long,PricePlanDO> pricePlanDOMap = new HashMap<>();
                for (PricePlanDO pricePlanDO : pricePlanDOList) {
                    pricePlanDOMap.put(pricePlanDO.getPricePlanId(),pricePlanDO);
                }
                List<IncrementDTO> fiterAfterList = new ArrayList<>();
                for(IncrementDTO incrementDTO : incrementDTOList){
                    if(pricePlanDOMap.containsKey(incrementDTO.getMRatePlanId())){
                        PricePlanDO pricePlanDO = pricePlanDOMap.get(incrementDTO.getMRatePlanId());
                        incrementDTO.setMHotelId(pricePlanDO.getHotelId());
                        incrementDTO.setMRoomTypeId(pricePlanDO.getRoomTypeId());
                        fiterAfterList.add(incrementDTO);
                    }
                }
                if(!CollectionUtils.isEmpty(fiterAfterList)){
                    //推送增量
                    String url = URLSplitUtil.getUrl(incrementConfig);
                    IncrementThread incrementThread = new IncrementThread(fiterAfterList,url,incrementService);
                    incrementExecutor.execute(incrementThread);
                }
            }
        } catch (Exception e) {
            log.error("batchOnSale has error",e);
            throw new ServiceException("batchOnSale has error",e);
        }
        return responseDTO;
    }

    private SaleStateLogDO getSaleStateLogDO(Long pricePlanId,String merchantCode,String channelCode,Integer saleState,String creator){
        SaleStateLogDO saleStateLogDO = new SaleStateLogDO();
        saleStateLogDO.setPricePlanId(pricePlanId);
        saleStateLogDO.setMerchantCode(merchantCode);
        saleStateLogDO.setChannelCode(channelCode);
        saleStateLogDO.setCreator(creator);
        saleStateLogDO.setSaleState(saleState);
        return saleStateLogDO;
    }
}
