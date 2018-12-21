package com.fangcang.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.agent.domain.AgentDO;
import com.fangcang.agent.mapper.AgentMapper;
import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.common.IncrementDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.B2B.BookTypeEnum;
import com.fangcang.common.enums.ChannelPriceEnum;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.PriceOperateTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.IncrementConfig;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.URLSplitUtil;
import com.fangcang.common.util.WeekUtil;
import com.fangcang.product.domain.PriceInfoDO;
import com.fangcang.product.domain.PriceInfoLogDO;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.domain.QuotaStateDO;
import com.fangcang.product.dto.BatchQuotaStateDTO;
import com.fangcang.product.dto.DateSegmentPriceDTO;
import com.fangcang.product.dto.DateSegmentQuotaDTO;
import com.fangcang.product.dto.PriceInfoOperateDTO;
import com.fangcang.product.dto.ProductDailyDTO;
import com.fangcang.product.mapper.PriceInfoLogMapper;
import com.fangcang.product.mapper.PriceInfoMapper;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.mapper.QuotaStateMapper;
import com.fangcang.product.request.BatchModifyPriceRequestDTO;
import com.fangcang.product.request.BatchSaveQuotaStateRequestDTO;
import com.fangcang.product.request.DynamicPricePlanQueryDTO;
import com.fangcang.product.request.ModifyPriceQuotaStateRequestDTO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import com.fangcang.product.service.IncrementService;
import com.fangcang.product.service.PriceInfoService;
import com.fangcang.product.service.QuotaStateService;
import com.fangcang.product.thread.IncrementThread;
import com.fangcang.product.thread.PriceInfoThread;
import com.fangcang.product.thread.QuotaStateThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ASUS on 2018/5/31.
 */
@Service
@Slf4j
class PriceInfoServiceImpl implements PriceInfoService {

    @Autowired
    private PriceInfoMapper priceInfoMapper;

    @Autowired
    private PriceInfoLogMapper priceInfoLogMapper;

    @Autowired
    private QuotaStateMapper quotaStateMapper;

    @Autowired
    private PricePlanMapper pricePlanMapper;

    @Autowired
    private PriceInfoService priceInfoService;

    @Autowired
    private QuotaStateService quotaStateService;

    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource(name = "incrementExecutor")
    private ThreadPoolTaskExecutor incrementExecutor;

    @Autowired
    private IncrementConfig incrementConfig;

    @Autowired
    private IncrementService incrementService;

    @Autowired
    private AgentMapper agentMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO batchModifyPrice(BatchModifyPriceRequestDTO batchModifyPriceRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            Date incrementStartDate=null;
            Date incrementEndDate=null;
            //改价基准
            Integer priceStandard = batchModifyPriceRequestDTO.getPriceStandard();
            //调整价格
            Integer[] modifyPriceStr = batchModifyPriceRequestDTO.getModifyPriceStr();
            //日志对象
            List<PriceInfoLogDO> priceInfoLogDOList = new ArrayList<>();
            //增量推送
            List<IncrementDTO> incrementDTOList = new ArrayList<>();
            PriceInfoLogDO priceInfoLogDO = null;
            List<PriceInfoOperateDTO> priceInfoOperateList = batchModifyPriceRequestDTO.getPriceInfoOperateList();
            for (PriceInfoOperateDTO priceInfoOperateDTO : priceInfoOperateList) {
                List<DateSegmentPriceDTO> dateSegmentPriceList = priceInfoOperateDTO.getDateSegmentPriceList();
                if(null == priceInfoOperateDTO
                        || null == priceInfoOperateDTO.getPricePlanId()
                        || CollectionUtils.isEmpty(dateSegmentPriceList)){
                    log.error("PricePlanId Or dateSegmentPriceList has null.");
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                    responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                    return responseDTO;
                }
                for (DateSegmentPriceDTO dateSegmentPriceDTO : dateSegmentPriceList) {
                    if(null == dateSegmentPriceDTO
                            || null == dateSegmentPriceDTO.getStartDate()
                            || null == dateSegmentPriceDTO.getEndDate()
                            || !StringUtil.isValidString(dateSegmentPriceDTO.getWeeks())){
                        log.error("startDate or endDate or weeks has null.");
                        responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                        responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                        responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                        return responseDTO;
                    }
                    //组装日志对象  记录操作日志
                    for(Integer modifyPrice : modifyPriceStr){
                        priceInfoLogDO = new PriceInfoLogDO();
                        priceInfoLogDO.setPricePlanId(priceInfoOperateDTO.getPricePlanId());
                        priceInfoLogDO.setStartDate(dateSegmentPriceDTO.getStartDate());
                        priceInfoLogDO.setEndDate(dateSegmentPriceDTO.getEndDate());
                        priceInfoLogDO.setWeeks(dateSegmentPriceDTO.getWeeks());
                        priceInfoLogDO.setMerchantCode(batchModifyPriceRequestDTO.getMerchantCode());
                        priceInfoLogDO.setCreator(batchModifyPriceRequestDTO.getCreator());
                        priceInfoLogDO.setPriceStandard(priceStandard);
                        priceInfoLogDO.setModifyPrice(modifyPrice);
                        assemblePriceLogPO(modifyPrice,priceInfoLogDO,dateSegmentPriceDTO);
                        priceInfoLogDO.setStandardPrice(dateSegmentPriceDTO.getStandardPrice());
                        priceInfoLogDOList.add(priceInfoLogDO);
                    }
                }
            }

            List<PriceInfoDO> priceInfoDOList = new ArrayList<>();
            PriceInfoDO priceInfoDO = null;
            for (PriceInfoOperateDTO priceInfoOperateDTO : priceInfoOperateList) {
                List<DateSegmentPriceDTO> dateSegmentPriceList = priceInfoOperateDTO.getDateSegmentPriceList();
                for (DateSegmentPriceDTO dateSegmentPriceDTO : dateSegmentPriceList) {
                    /**
                     * 1.当改价基准为无基本价的时候,则无基准价格
                     * 2.当改价基准不是无基本价的时候，有以下情况：
                     * 基准价格不为空，则根据基准价格更新其他价格
                     * 基准价格为空,则去数据库查询
                     */
                    //设置基准价格
                    Date startDate = DateUtil.dateFormat(dateSegmentPriceDTO.getStartDate(),DateUtil.defaultFormat);
                    Date endDate = DateUtil.dateFormat(dateSegmentPriceDTO.getEndDate(),DateUtil.defaultFormat);
                    Map<Date,BigDecimal> datePrice = new HashMap<>();
                    if(null != priceStandard && priceStandard != ChannelPriceEnum.NO_BASE_PRICE.key){
                        if(null != dateSegmentPriceDTO.getStandardPrice()){
                            List<Date> dateList = DateUtil.getDateList(startDate,endDate);
                            for (Date date : dateList) {
                                datePrice.put(date,dateSegmentPriceDTO.getStandardPrice());
                            }
                        }else{
                            ProductDailyInfoQueryDTO productDailyInfoQueryDTO = new ProductDailyInfoQueryDTO();
                            productDailyInfoQueryDTO.setPricePlanId(priceInfoOperateDTO.getPricePlanId());
                            productDailyInfoQueryDTO.setStartDate(startDate);
                            productDailyInfoQueryDTO.setEndDate(endDate);
                            List<PriceInfoDO> priceInfoDOS = priceInfoMapper.queryDailyPriceInfo(productDailyInfoQueryDTO);
                            if(!CollectionUtils.isEmpty(priceInfoDOS)){
                                for (PriceInfoDO infoDO : priceInfoDOS) {
                                    assembleStandardPrice(priceStandard,datePrice,infoDO);
                                }
                            }
                        }
                    }

                    IncrementDTO incrementDTO = new IncrementDTO();
                    incrementDTO.setHasIncrement(false);
                    List<Date> dateList = WeekUtil.getSaleDate(startDate, endDate, dateSegmentPriceDTO.getWeeks());
                    for (Date saleDate : dateList) {
                        priceInfoDO = new PriceInfoDO();
                        priceInfoDO.setPricePlanId(priceInfoOperateDTO.getPricePlanId());
                        priceInfoDO.setSaleDate(saleDate);
                        priceInfoDO.setCreator(batchModifyPriceRequestDTO.getCreator());
                        priceInfoDO.setModifier(batchModifyPriceRequestDTO.getCreator());
                        //设置基准价格
                        if(null != priceStandard && priceStandard != ChannelPriceEnum.NO_BASE_PRICE.key
                                && null != dateSegmentPriceDTO.getStandardPrice()){
                            setStandardPrice(priceStandard,dateSegmentPriceDTO.getStandardPrice(),priceInfoDO,incrementDTO);
                        }
                        //调整了那些价格
                        for(Integer modifyPrice : modifyPriceStr){
                            //设置调整价格
                            BigDecimal price = datePrice.containsKey(saleDate) ? datePrice.get(saleDate) : new BigDecimal(0);
                            setPricePO(priceStandard,modifyPrice, priceInfoDO, price,dateSegmentPriceDTO,incrementDTO);
                        }
                        priceInfoDOList.add(priceInfoDO);
                    }
                    if(incrementDTO.getHasIncrement()){
                        incrementDTO.setMerchantCode(batchModifyPriceRequestDTO.getMerchantCode());
                        incrementDTO.setMRatePlanId(priceInfoOperateDTO.getPricePlanId());
                        incrementDTO.setStartDate(DateUtil.dateToString(dateSegmentPriceDTO.getStartDate()));
                        incrementDTO.setEndDate(DateUtil.dateToString(dateSegmentPriceDTO.getEndDate()));
                        incrementDTO.setHasIncrement(null);
                        incrementDTOList.add(incrementDTO);

                        if (incrementStartDate==null){
                            incrementStartDate=dateSegmentPriceDTO.getStartDate();
                        }else if(DateUtil.compare(incrementStartDate,dateSegmentPriceDTO.getStartDate())==1){
                            incrementStartDate=dateSegmentPriceDTO.getStartDate();
                        }
                        if (incrementEndDate==null){
                            incrementEndDate=dateSegmentPriceDTO.getEndDate();
                        }else if(DateUtil.compare(incrementEndDate,dateSegmentPriceDTO.getEndDate())==-1){
                            incrementEndDate=dateSegmentPriceDTO.getEndDate();
                        }
                    }
                }
            }
            //批量保存修改价格
            if(!CollectionUtils.isEmpty(priceInfoDOList)){
                priceInfoMapper.batchSaveOrUpdatePriceInfo(priceInfoDOList);
            }
            //批量保存价格操作日志
            if(!CollectionUtils.isEmpty(priceInfoLogDOList)){
                priceInfoLogMapper.batchSavePriceInfoLog(priceInfoLogDOList);
            }
            if(!CollectionUtils.isEmpty(incrementDTOList)){
                Set<Long> pricePlanSet = new HashSet<>();
                StringBuilder pricePlanIds = new StringBuilder();
                for(IncrementDTO incrementDTO : incrementDTOList){
                    if(!pricePlanSet.contains(incrementDTO.getMRatePlanId())){
                        pricePlanIds.append(incrementDTO.getMRatePlanId()).append(",");
                        pricePlanSet.add(incrementDTO.getMRatePlanId());
                    }
                }
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
                //增量批推送次数过多，以酒店为单位进行推送
                if (!CollectionUtils.isEmpty(fiterAfterList) && fiterAfterList.size()>6){
                    fiterAfterList.clear();
                    Set<Long> hotelIdSet=new HashSet<>();
                    for(IncrementDTO incrementDTO : incrementDTOList){
                        if(pricePlanDOMap.containsKey(incrementDTO.getMRatePlanId())){
                            PricePlanDO pricePlanDO = pricePlanDOMap.get(incrementDTO.getMRatePlanId());
                            if (pricePlanDO!=null && !hotelIdSet.contains(pricePlanDO.getHotelId())){
                                IncrementDTO fiterIncrementDTO=new IncrementDTO();
                                fiterIncrementDTO.setMerchantCode(incrementDTO.getMerchantCode());
                                fiterIncrementDTO.setStartDate(DateUtil.dateToString(incrementStartDate));
                                fiterIncrementDTO.setEndDate(DateUtil.dateToString(incrementEndDate));
                                fiterIncrementDTO.setMHotelId(pricePlanDO.getHotelId());
                                fiterIncrementDTO.setHasIncrement(true);
                                fiterAfterList.add(fiterIncrementDTO);
                                hotelIdSet.add(pricePlanDO.getHotelId());
                            }
                        }
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
            log.error("batchModifyPrice has error.",e);
            throw new ServiceException("batchModifyPrice has error",e);
        }
        return responseDTO;
    }


    public ResponseDTO<List<ProductDailyInfoResponseDTO>> queryProductDailyInfo(ProductDailyInfoQueryDTO productDailyInfoQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == productDailyInfoQueryDTO
                || null == productDailyInfoQueryDTO.getEndDate()
                || null == productDailyInfoQueryDTO.getStartDate()
                || (null == productDailyInfoQueryDTO.getPricePlanId() && null == productDailyInfoQueryDTO.getHotelId())){
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return responseDTO;
        }
        try {
            //1.先查询价格计划信息
            DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO = new DynamicPricePlanQueryDTO();
            dynamicPricePlanQueryDTO = PropertyCopyUtil.transfer(productDailyInfoQueryDTO,DynamicPricePlanQueryDTO.class);
            List<PricePlanDO> pricePlanDOList = pricePlanMapper.dynamicQueryPricePlanList(dynamicPricePlanQueryDTO);
            Map<Long,PricePlanDO> pricePlanDOMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(pricePlanDOList)){
                //根据酒店查询
                StringBuilder pricePlanIdstr = new StringBuilder();
                for (int i = 0; i < pricePlanDOList.size(); i++) {
                    PricePlanDO pricePlanDO = pricePlanDOList.get(i);
                    //只查上架的产品
                    if(StringUtil.isValidString(productDailyInfoQueryDTO.getChannelCode())
                            && null != productDailyInfoQueryDTO.getSaleState()
                            && 1 == productDailyInfoQueryDTO.getSaleState()){
                        if(ChannelTypeEnum.B2B.key.equals(productDailyInfoQueryDTO.getChannelCode())
                            && (null == pricePlanDO.getB2bSaleState() || 0 == pricePlanDO.getB2bSaleState())){
                            continue;
                        }
                        if(ChannelTypeEnum.ELONG.key.equals(productDailyInfoQueryDTO.getChannelCode())
                                && (null == pricePlanDO.getElongSaleState() || 0 == pricePlanDO.getElongSaleState())){
                            continue;
                        }
                    }
                    pricePlanDOMap.put(pricePlanDO.getPricePlanId(),pricePlanDO);
                    pricePlanIdstr.append(pricePlanDO.getPricePlanId()).append(",");
                    if (i % 999 == 0 && i > 0 && i != pricePlanDOList.size()) {
                        pricePlanIdstr.deleteCharAt(pricePlanIdstr.length() - 1);
                        pricePlanIdstr.append(" ) or p.priceplan_id in (");
                    }
                }
                if(pricePlanIdstr.length() <= 0){
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailReason("没有上架的产品");
                    return responseDTO;
                }
                productDailyInfoQueryDTO.setPricePlanIds(pricePlanIdstr.deleteCharAt(pricePlanIdstr.length() - 1).toString());
                //2.分别创建线程查询价格、房态
                Map<String,PriceInfoDO> priceInfoDOMap = new HashMap<>();
                Map<String,QuotaStateDO> quotaStateDOMap = new HashMap<>();
                CountDownLatch countDownLatch = new CountDownLatch(2);
                PriceInfoThread priceInfoThread = new PriceInfoThread(priceInfoMapper,priceInfoDOMap,countDownLatch,productDailyInfoQueryDTO);
                threadPoolTaskExecutor.execute(priceInfoThread);

                QuotaStateThread quotaStateThread = new QuotaStateThread(quotaStateMapper,quotaStateDOMap,countDownLatch,productDailyInfoQueryDTO);
                threadPoolTaskExecutor.execute(quotaStateThread);

                //等待子线程执行完成
                countDownLatch.await();

                List<Date> dateList = DateUtil.getDateList(productDailyInfoQueryDTO.getStartDate(),productDailyInfoQueryDTO.getEndDate());
                //售价转换为
                String settlementCurrency = null;
                if(StringUtil.isValidString(productDailyInfoQueryDTO.getAgentCode())){
                    SingleAgentRequestDTO singleAgentRequestDTO = new SingleAgentRequestDTO();
                    singleAgentRequestDTO.setAgentCode(productDailyInfoQueryDTO.getAgentCode());
                    AgentDO agentDO = agentMapper.selectSingleAgentInfo(singleAgentRequestDTO);
                    settlementCurrency = agentDO.getFinanceCurrency();
                }

                //组装结果
                List<ProductDailyInfoResponseDTO> productDailyInfoResponseDTOS = new ArrayList<>();
                ProductDailyInfoResponseDTO productDailyInfoResponseDTO = null;
                ProductDailyDTO productDailyDTO = null;
                StringBuilder key = new StringBuilder();
                StringBuilder bookTypeBuilder = new StringBuilder();
                StringBuilder scatteredRoomBookType = new StringBuilder();
                StringBuilder groupRoomBookType = new StringBuilder();
                BigDecimal startPrice = null;//起价
                for(PricePlanDO pricePlanDO : pricePlanDOList){
                    if(!pricePlanDOMap.containsKey(pricePlanDO.getPricePlanId())){
                        continue;
                    }
                    startPrice = null;
                    productDailyInfoResponseDTO = PropertyCopyUtil.transfer(pricePlanDO,ProductDailyInfoResponseDTO.class);
                    if(StringUtil.isValidString(pricePlanDO.getFreeRoomPolicyStr())){
                        String[] freeRoomPolicy = pricePlanDO.getFreeRoomPolicyStr().split(",");
                        productDailyInfoResponseDTO.setFreeRoomPolicy(freeRoomPolicy);
                    }
                    if(StringUtil.isValidString(settlementCurrency)
                            && StringUtil.isValidString(pricePlanDO.getCurrency())
                            && !pricePlanDO.getCurrency().equals(settlementCurrency)){
                        //币种不同 需要转换  产品币种 渠道币种 分销商结算币种
                    }
                    List<ProductDailyDTO> productDailyList = new ArrayList<>();
                    for(Date date : dateList){
                        key.append(pricePlanDO.getPricePlanId()).append(":").append(DateUtil.dateToString(date));
                        Date saleDate = DateUtil.dateFormat(date,DateUtil.defaultFormat);
                        productDailyDTO = new ProductDailyDTO();
                        productDailyDTO.setSaleDate(saleDate);
                        //组装价格对象
                        if(priceInfoDOMap.containsKey(key.toString())){
                            productDailyDTO = PropertyCopyUtil.transfer(priceInfoDOMap.get(key.toString()),ProductDailyDTO.class);
                            productDailyInfoResponseDTO.setHasPrice(1);
                        }
                        //起价
                        if(ChannelTypeEnum.B2B.key.equals(productDailyInfoQueryDTO.getChannelCode())
                            && null != productDailyDTO && null != productDailyDTO.getB2bSalePrice()){
                            if(null == startPrice){
                                startPrice = productDailyDTO.getB2bSalePrice();
                            }else if(null != startPrice && startPrice.compareTo(productDailyDTO.getB2bSalePrice()) > 0){
                                startPrice = productDailyDTO.getB2bSalePrice();
                            }
                        }
                        if(ChannelTypeEnum.ELONG.key.equals(productDailyInfoQueryDTO.getChannelCode())
                                && null != productDailyDTO && null != productDailyDTO.getElongSalePrice()){
                            if(null == startPrice){
                                startPrice = productDailyDTO.getElongSalePrice();
                            }else if(null != startPrice && startPrice.compareTo(productDailyDTO.getElongSalePrice()) > 0){
                                startPrice = productDailyDTO.getElongSalePrice();
                            }
                        }

                        //组装配额房态对象
                        if(quotaStateDOMap.containsKey(key.toString())){
                            QuotaStateDO quotaStateDO = quotaStateDOMap.get(key.toString());
                            productDailyDTO.setQuotaNum(quotaStateDO.getQuotaNum());
                            productDailyDTO.setAllQuotaNum(quotaStateDO.getAllQuotaNum());
                            productDailyDTO.setOverDraft(quotaStateDO.getOverDraft());
                            productDailyDTO.setQuotaState(quotaStateDO.getQuotaState());
                        }
                        productDailyDTO.setShowCurrency(settlementCurrency);

                        //每一天配额大于0 有房 则可定
                        bookTypeBuilder.append(DateUtil.dateToString(date)).append(":");
                        scatteredRoomBookType.append(DateUtil.dateToString(date)).append(":");


                        if(null != productDailyInfoQueryDTO.getOrderType() && 1 == productDailyInfoQueryDTO.getOrderType()){
                            //下散房单
                            if(ChannelTypeEnum.B2B.key.equals(productDailyInfoQueryDTO.getChannelCode())
                                    && null != productDailyDTO.getB2bSalePrice()
                                    && new BigDecimal(0).compareTo(productDailyDTO.getB2bSalePrice()) < 0
                                    && null != productDailyDTO.getQuotaNum()
                                    && null != productDailyDTO.getQuotaState()
                                    && (Integer.valueOf(0).compareTo(productDailyDTO.getQuotaNum()) < 0
                                    || (Integer.valueOf(0).compareTo(productDailyDTO.getQuotaNum()) == 0
                                    && null != productDailyDTO.getOverDraft()
                                    && 1 == productDailyDTO.getOverDraft()
                                        ))
                                    && 1 == productDailyDTO.getQuotaState()){
                                bookTypeBuilder.append("true");
                                productDailyDTO.setBookType(BookTypeEnum.RESERVATION.key);
                            }else if(ChannelTypeEnum.ELONG.key.equals(productDailyInfoQueryDTO.getChannelCode())
                                    && null != productDailyDTO.getElongSalePrice()
                                    && new BigDecimal(0).compareTo(productDailyDTO.getElongSalePrice()) < 0
                                    && null != productDailyDTO.getQuotaNum()
                                    && null != productDailyDTO.getQuotaState()
                                    && (Integer.valueOf(0).compareTo(productDailyDTO.getQuotaNum()) < 0
                                    || (Integer.valueOf(0).compareTo(productDailyDTO.getQuotaNum()) == 0
                                    && null != productDailyDTO.getOverDraft()
                                    && 1 == productDailyDTO.getOverDraft()
                                        ))
                                    && 1 == productDailyDTO.getQuotaState()){
                                bookTypeBuilder.append("true");
                                productDailyDTO.setBookType(BookTypeEnum.RESERVATION.key);
                            }else{
                                bookTypeBuilder.append("false");
                                productDailyDTO.setBookType(BookTypeEnum.NOT_RESERVATION.key);
                            }
                        }else if(null != productDailyInfoQueryDTO.getOrderType() && 2 == productDailyInfoQueryDTO.getOrderType()){
                            //下团房单
                            if(((null != productDailyDTO.getB2bSalePrice() && new BigDecimal(0).compareTo(productDailyDTO.getB2bSalePrice()) < 0)
                                    || (null != productDailyDTO.getGroupSalePrice() && new BigDecimal(0).compareTo(productDailyDTO.getGroupSalePrice()) < 0))
                                    && null != productDailyDTO.getQuotaState()
                                    && 1 == productDailyDTO.getQuotaState()){
                                bookTypeBuilder.append("true");
                                productDailyDTO.setBookType(BookTypeEnum.RESERVATION.key);
                            }else{
                                bookTypeBuilder.append("false");
                                productDailyDTO.setBookType(BookTypeEnum.NOT_RESERVATION.key);
                            }
                        }

                        if(ChannelTypeEnum.B2B.key.equals(productDailyInfoQueryDTO.getChannelCode())
                            && null != productDailyDTO.getB2bSalePrice() && new BigDecimal(0).compareTo(productDailyDTO.getB2bSalePrice()) < 0){
                            scatteredRoomBookType.append("true");
                        }else if(ChannelTypeEnum.ELONG.key.equals(productDailyInfoQueryDTO.getChannelCode())
                                && null != productDailyDTO.getElongSalePrice() && new BigDecimal(0).compareTo(productDailyDTO.getElongSalePrice()) < 0){
                            scatteredRoomBookType.append("true");
                        }else{
                            scatteredRoomBookType.append("false");
                        }

                        if(null != productDailyDTO.getGroupSalePrice() && new BigDecimal(0).compareTo(productDailyDTO.getGroupSalePrice()) < 0){
                            groupRoomBookType.append("true");
                        }else{
                            groupRoomBookType.append("false");
                        }

                        key.setLength(0);
                        productDailyList.add(productDailyDTO);
                    }

                    if(bookTypeBuilder.toString().indexOf("true") == -1){
                        //不可订
                        productDailyInfoResponseDTO.setBookType(BookTypeEnum.NOT_RESERVATION.key);
                    }else{
                        if(bookTypeBuilder.toString().indexOf("false") != -1){
                            //部分可订
                            productDailyInfoResponseDTO.setBookType(BookTypeEnum.PARTIALLYDEFINABLE.key);
                        }else{
                            //可订
                            productDailyInfoResponseDTO.setBookType(BookTypeEnum.RESERVATION.key);
                        }
                    }

                    if(scatteredRoomBookType.toString().indexOf("true") == -1){
                        //不可订
                        productDailyInfoResponseDTO.setScatteredRoomBookType(BookTypeEnum.NOT_RESERVATION.key);
                    }else{
                        if(scatteredRoomBookType.toString().indexOf("false") != -1){
                            //部分可订
                            productDailyInfoResponseDTO.setScatteredRoomBookType(BookTypeEnum.PARTIALLYDEFINABLE.key);
                        }else{
                            //可订
                            productDailyInfoResponseDTO.setScatteredRoomBookType(BookTypeEnum.RESERVATION.key);
                        }
                    }

                    if(groupRoomBookType.toString().indexOf("true") == -1){
                        //不可订
                        productDailyInfoResponseDTO.setGroupRoomBookType(BookTypeEnum.NOT_RESERVATION.key);
                    }else{
                        if(groupRoomBookType.toString().indexOf("false") != -1){
                            //部分可订
                            productDailyInfoResponseDTO.setGroupRoomBookType(BookTypeEnum.PARTIALLYDEFINABLE.key);
                        }else{
                            //可订
                            productDailyInfoResponseDTO.setGroupRoomBookType(BookTypeEnum.RESERVATION.key);
                        }
                    }

                    productDailyInfoResponseDTO.setStartPrice(startPrice);
                    productDailyInfoResponseDTO.setProductDailyList(productDailyList);
                    productDailyInfoResponseDTOS.add(productDailyInfoResponseDTO);
                    bookTypeBuilder.setLength(0);
                    scatteredRoomBookType.setLength(0);
                    groupRoomBookType.setLength(0);
                }
                responseDTO.setModel(productDailyInfoResponseDTOS);
            }
        } catch (InterruptedException e) {
            log.error("queryProductDailyInfo",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO updatePriceAndQuotaState(ModifyPriceQuotaStateRequestDTO modifyPriceQuotaStateRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            //批量改价格

            BatchModifyPriceRequestDTO batchModifyPriceRequestDTO = new BatchModifyPriceRequestDTO();
            batchModifyPriceRequestDTO.setMerchantCode(modifyPriceQuotaStateRequestDTO.getMerchantCode());
            batchModifyPriceRequestDTO.setCreator(modifyPriceQuotaStateRequestDTO.getCreator());
            batchModifyPriceRequestDTO.setModifier(modifyPriceQuotaStateRequestDTO.getCreator());
            batchModifyPriceRequestDTO.setPriceStandard(modifyPriceQuotaStateRequestDTO.getPriceStandard());
            batchModifyPriceRequestDTO.setModifyPriceStr(modifyPriceQuotaStateRequestDTO.getModifyPriceStr());
            List<PriceInfoOperateDTO> priceInfoOperateDTOList = new ArrayList<>();

            PriceInfoOperateDTO priceInfoOperateDTO = new PriceInfoOperateDTO();
            priceInfoOperateDTO.setPricePlanId(modifyPriceQuotaStateRequestDTO.getPricePlanId());
            List<DateSegmentPriceDTO> dateSegmentPriceList = new ArrayList<>();
            DateSegmentPriceDTO dateSegmentPriceDTO = null;
            dateSegmentPriceDTO = PropertyCopyUtil.transfer(modifyPriceQuotaStateRequestDTO,DateSegmentPriceDTO.class);
            dateSegmentPriceDTO.setStartDate(modifyPriceQuotaStateRequestDTO.getStartDate());
            dateSegmentPriceDTO.setEndDate(modifyPriceQuotaStateRequestDTO.getEndDate());
            dateSegmentPriceDTO.setWeeks("1,2,3,4,5,6,7");
            dateSegmentPriceList.add(dateSegmentPriceDTO);

            priceInfoOperateDTO.setDateSegmentPriceList(dateSegmentPriceList);
            priceInfoOperateDTOList.add(priceInfoOperateDTO);
            batchModifyPriceRequestDTO.setPriceInfoOperateList(priceInfoOperateDTOList);

            ResponseDTO savePriceResponse = priceInfoService.batchModifyPrice(batchModifyPriceRequestDTO);
            if(null == savePriceResponse || ResultCodeEnum.FAILURE.code == savePriceResponse.getResult()){
                log.error("Update price has error");
                throw new ServiceException("Update price has error");
            }

            //批量改房态
            BatchSaveQuotaStateRequestDTO batchSaveQuotaStateRequestDTO = new BatchSaveQuotaStateRequestDTO();
            List<BatchQuotaStateDTO> quotaStateList = new ArrayList<>();

            BatchQuotaStateDTO batchQuotaStateDTO = new BatchQuotaStateDTO();
            batchQuotaStateDTO.setPricePlanId(modifyPriceQuotaStateRequestDTO.getPricePlanId());
            batchQuotaStateDTO.setQuotaAccountId(modifyPriceQuotaStateRequestDTO.getQuotaAccountId());

            List<DateSegmentQuotaDTO> dateSegmentQuotaList = new ArrayList<>();
            DateSegmentQuotaDTO dateSegmentQuotaDTO = null;
            dateSegmentQuotaDTO = PropertyCopyUtil.transfer(modifyPriceQuotaStateRequestDTO,DateSegmentQuotaDTO.class);
            dateSegmentQuotaDTO.setStartDate(modifyPriceQuotaStateRequestDTO.getStartDate());
            dateSegmentQuotaDTO.setEndDate(modifyPriceQuotaStateRequestDTO.getEndDate());
            dateSegmentQuotaDTO.setWeeks("1,2,3,4,5,6,7");
            dateSegmentQuotaList.add(dateSegmentQuotaDTO);

            batchQuotaStateDTO.setDateSegmentQuotaList(dateSegmentQuotaList);
            quotaStateList.add(batchQuotaStateDTO);
            batchSaveQuotaStateRequestDTO.setQuotaStateList(quotaStateList);
            batchSaveQuotaStateRequestDTO.setMerchantCode(modifyPriceQuotaStateRequestDTO.getMerchantCode());
            batchSaveQuotaStateRequestDTO.setCreator(modifyPriceQuotaStateRequestDTO.getCreator());
            batchSaveQuotaStateRequestDTO.setModifier(modifyPriceQuotaStateRequestDTO.getModifier());

            ResponseDTO quotaStateResponse = quotaStateService.batchModifyQuotaState(batchSaveQuotaStateRequestDTO);
            if(null == quotaStateResponse || ResultCodeEnum.FAILURE.code == quotaStateResponse.getResult()){
                log.error("Update quotastate has error");
                throw new ServiceException("Update quotastate has error.");
            }
        } catch (ServiceException e) {
            log.error("updatePriceAndQuotaState has error.",e);
            throw new ServiceException("updatePriceAndQuotaState has error.",e);
        }
        return responseDTO;
    }

    /**
     * 日历界面调整价格和房态
     * @param modifyPriceQuotaStateRequestDTO
     * @return
     */
    @Override
    public ResponseDTO updatePriceAndQuotaStateByCalendar(ModifyPriceQuotaStateRequestDTO modifyPriceQuotaStateRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        if (CollectionUtils.isEmpty(modifyPriceQuotaStateRequestDTO.getSaleDateList())){
            log.error("日历保存价格房态失败，参数不合法：{}", JSON.toJSONString(modifyPriceQuotaStateRequestDTO));
            responseDTO.setErrorCode(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }
        /*for (Date saleDateTemp : modifyPriceQuotaStateRequestDTO.getSaleDateList()){
            Date saleDate = DateUtil.dateFormat(saleDateTemp,DateUtil.defaultFormat);
            modifyPriceQuotaStateRequestDTO.setStartDate(saleDate);
            modifyPriceQuotaStateRequestDTO.setEndDate(saleDate);
            this.updatePriceAndQuotaState(modifyPriceQuotaStateRequestDTO);
        }*/
        //增量推送过于频繁，日期相连的进行聚合
        Collections.sort(modifyPriceQuotaStateRequestDTO.getSaleDateList());
        Date startDate=null;
        Date endDate=null;
        for (Date currDate : modifyPriceQuotaStateRequestDTO.getSaleDateList()){
            if (startDate==null){
                startDate=currDate;
            }
            if (endDate!=null && DateUtil.getDay(endDate,currDate)>1){
                modifyPriceQuotaStateRequestDTO.setStartDate(startDate);
                modifyPriceQuotaStateRequestDTO.setEndDate(endDate);
                this.updatePriceAndQuotaState(modifyPriceQuotaStateRequestDTO);
                startDate=currDate;
            }
            endDate=currDate;
        }
        if (startDate!=null && endDate!=null){
            modifyPriceQuotaStateRequestDTO.setStartDate(startDate);
            modifyPriceQuotaStateRequestDTO.setEndDate(endDate);
            this.updatePriceAndQuotaState(modifyPriceQuotaStateRequestDTO);
        }
        return responseDTO;
    }

    /**
     * 设置基准价格
     * @param priceStandard
     * @param standardPrice
     * @param priceInfoDO
     * @param incrementDTO
     */
    private void setStandardPrice(Integer priceStandard,BigDecimal standardPrice,PriceInfoDO priceInfoDO,IncrementDTO incrementDTO){
        if(null != priceStandard){
            if(ChannelPriceEnum.BASE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setBasePrice(standardPrice);
                priceInfoDO.setBasePriceOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if (ChannelPriceEnum.CTRIP_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setCtripSalePrice(standardPrice);
                priceInfoDO.setCtripOperateType(PriceOperateTypeEnum.SET_TO.key);
                incrementDTO.setHasIncrement(true);
            }else if(ChannelPriceEnum.QUNAR_B2B_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setQunarB2bSalePrice(standardPrice);
                priceInfoDO.setQunarB2BOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.QUNAR_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setQunarSalePrice(standardPrice);
                priceInfoDO.setQunarOperateType(PriceOperateTypeEnum.SET_TO.key);
                incrementDTO.setHasIncrement(true);
            } else if(ChannelPriceEnum.QUNAR_NGT_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setQunarNgtSalePrice(standardPrice);
                priceInfoDO.setQunarNgtOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.ELONG_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setElongSalePrice(standardPrice);
                priceInfoDO.setElongOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.GROUP_BASE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setGroupBasePrice(standardPrice);
                priceInfoDO.setGroupBaseOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.GROUP_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setGroupSalePrice(standardPrice);
                priceInfoDO.setGroupSaleOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.B2B_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setB2bSalePrice(standardPrice);
                priceInfoDO.setB2bOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.TONGCHENG_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setTongchengSalePrice(standardPrice);
                priceInfoDO.setTongchengOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.TUNIU_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setTuniuSalePrice(standardPrice);
                priceInfoDO.setTuniuOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.XMD_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setXmdSalePrice(standardPrice);
                priceInfoDO.setXmdOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.JD_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setJdSalePrice(standardPrice);
                priceInfoDO.setJdOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.TAOBAO_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setTaobaoSalePrice(standardPrice);
                priceInfoDO.setTaobaoOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.QUNAR_USD_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setQunarUsdSalePrice(standardPrice);
                priceInfoDO.setQunarUsdOperateType(PriceOperateTypeEnum.SET_TO.key);
            }else if(ChannelPriceEnum.QUNAR_SON_SALE_PRICE.key.compareTo(priceStandard) == 0){
                priceInfoDO.setQunarSonSalePrice(standardPrice);
                priceInfoDO.setQunarSonOperateType(PriceOperateTypeEnum.SET_TO.key);
            }
        }
    }


    private void assembleStandardPrice(Integer priceStandard,Map<Date,BigDecimal> datePrice,PriceInfoDO infoDO){
        if(null != priceStandard){
            if(ChannelPriceEnum.BASE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getBasePrice() == null ? new BigDecimal(0) : infoDO.getBasePrice());
            }else if (ChannelPriceEnum.CTRIP_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getCtripSalePrice() == null ? new BigDecimal(0) : infoDO.getCtripSalePrice());
            }else if(ChannelPriceEnum.QUNAR_B2B_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getQunarB2bSalePrice() == null ? new BigDecimal(0) : infoDO.getQunarB2bSalePrice());
            }else if(ChannelPriceEnum.QUNAR_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getQunarSalePrice() == null ? new BigDecimal(0) : infoDO.getQunarSalePrice());
            } else if(ChannelPriceEnum.QUNAR_NGT_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getQunarNgtSalePrice() == null ? new BigDecimal(0) : infoDO.getQunarNgtSalePrice());
            }else if(ChannelPriceEnum.ELONG_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getElongSalePrice() == null ? new BigDecimal(0) : infoDO.getElongSalePrice());
            }else if(ChannelPriceEnum.GROUP_BASE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getGroupBasePrice() == null ? new BigDecimal(0) : infoDO.getGroupBasePrice());
            }else if(ChannelPriceEnum.GROUP_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getGroupSalePrice() == null ? new BigDecimal(0) : infoDO.getGroupSalePrice());
            }else if(ChannelPriceEnum.B2B_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getB2bSalePrice() == null ? new BigDecimal(0) : infoDO.getB2bSalePrice());
            }else if(ChannelPriceEnum.TONGCHENG_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getTongchengSalePrice() == null ? new BigDecimal(0) : infoDO.getTongchengSalePrice());
            }else if(ChannelPriceEnum.TUNIU_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getTuniuSalePrice() == null ? new BigDecimal(0) : infoDO.getTuniuSalePrice());
            }else if(ChannelPriceEnum.XMD_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getXmdSalePrice() == null ? new BigDecimal(0) : infoDO.getXmdSalePrice());
            }else if(ChannelPriceEnum.JD_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getJdSalePrice() == null ? new BigDecimal(0) : infoDO.getJdSalePrice());
            }else if(ChannelPriceEnum.TAOBAO_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getTaobaoSalePrice() == null ? new BigDecimal(0) : infoDO.getTaobaoSalePrice());
            }else if(ChannelPriceEnum.QUNAR_USD_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getQunarUsdSalePrice() == null ? new BigDecimal(0) : infoDO.getQunarUsdSalePrice());
            }else if(ChannelPriceEnum.QUNAR_SON_SALE_PRICE.key.compareTo(priceStandard) == 0){
                datePrice.put(infoDO.getSaleDate(),infoDO.getQunarSonSalePrice() == null ? new BigDecimal(0) : infoDO.getQunarSonSalePrice());
            }
        }
    }

    /**
     *
     * @param priceStandard          改价基准
     * @param modifyPrice            调整价格
     * @param priceInfoDO
     * @param price                  基准价格
     * @param dateSegmentPriceDTO     入参调整类型和数值
     */
    private void setPricePO(Integer priceStandard,Integer modifyPrice, PriceInfoDO priceInfoDO, BigDecimal price,DateSegmentPriceDTO dateSegmentPriceDTO,IncrementDTO incrementDTO){
        BigDecimal afterPrice = new BigDecimal(0);
        if(null != modifyPrice){
            if(ChannelPriceEnum.BASE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getBasePriceIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setBasePrice(dateSegmentPriceDTO.getBasePriceIncreaseAmount());
                    priceInfoDO.setBasePriceOperateType(dateSegmentPriceDTO.getBasePriceOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getBasePriceOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getBasePriceIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getBasePriceOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getBasePriceIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getBasePriceOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getBasePriceIncreaseAmount());
                    }
                    priceInfoDO.setBasePrice(afterPrice);
                    priceInfoDO.setBasePriceOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if (ChannelPriceEnum.CTRIP_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getCtripIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setCtripSalePrice(dateSegmentPriceDTO.getCtripIncreaseAmount());
                    priceInfoDO.setCtripOperateType(dateSegmentPriceDTO.getCtripOperateType());
                    incrementDTO.setHasIncrement(true);
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getCtripOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getCtripIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getCtripOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getCtripIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getCtripOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getCtripIncreaseAmount());
                    }
                    priceInfoDO.setCtripSalePrice(afterPrice);
                    priceInfoDO.setCtripOperateType(PriceOperateTypeEnum.SET_TO.key);
                    incrementDTO.setHasIncrement(true);
                }
            }else if(ChannelPriceEnum.QUNAR_B2B_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getQunarB2BIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setQunarB2bSalePrice(dateSegmentPriceDTO.getQunarB2BIncreaseAmount());
                    priceInfoDO.setQunarB2BOperateType(dateSegmentPriceDTO.getQunarB2BOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getQunarB2BOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getQunarB2BIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getQunarB2BOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getQunarB2BIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getQunarB2BOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getQunarB2BIncreaseAmount());
                    }
                    priceInfoDO.setQunarB2bSalePrice(afterPrice);
                    priceInfoDO.setQunarB2BOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.QUNAR_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getQunarIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setQunarSalePrice(dateSegmentPriceDTO.getQunarIncreaseAmount());
                    priceInfoDO.setQunarOperateType(dateSegmentPriceDTO.getQunarOperateType());
                    incrementDTO.setHasIncrement(true);
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getQunarOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getQunarIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getQunarOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getQunarIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getQunarOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getQunarIncreaseAmount());
                    }
                    priceInfoDO.setQunarSalePrice(afterPrice);
                    priceInfoDO.setQunarOperateType(PriceOperateTypeEnum.SET_TO.key);
                    incrementDTO.setHasIncrement(true);
                }
            } else if(ChannelPriceEnum.QUNAR_NGT_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getQunarNgtIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setQunarNgtSalePrice(dateSegmentPriceDTO.getQunarNgtIncreaseAmount());
                    priceInfoDO.setQunarNgtOperateType(dateSegmentPriceDTO.getQunarNgtOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getQunarNgtOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getQunarNgtIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getQunarNgtOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getQunarNgtIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getQunarNgtOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getQunarNgtIncreaseAmount());
                    }
                    priceInfoDO.setQunarNgtSalePrice(afterPrice);
                    priceInfoDO.setQunarNgtOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.ELONG_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getElongIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setElongSalePrice(dateSegmentPriceDTO.getElongIncreaseAmount());
                    priceInfoDO.setElongOperateType(dateSegmentPriceDTO.getElongOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getElongOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getElongIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getElongOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getElongIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getElongOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getElongIncreaseAmount());
                    }
                    priceInfoDO.setElongSalePrice(afterPrice);
                    priceInfoDO.setElongOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.GROUP_BASE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getGroupBaseIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setGroupBasePrice(dateSegmentPriceDTO.getGroupBaseIncreaseAmount());
                    priceInfoDO.setGroupBaseOperateType(dateSegmentPriceDTO.getGroupBaseOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getGroupBaseOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getGroupBaseIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getGroupBaseOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getGroupBaseIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getGroupBaseOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getGroupBaseIncreaseAmount());
                    }
                    priceInfoDO.setGroupBasePrice(afterPrice);
                    priceInfoDO.setGroupBaseOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.GROUP_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getGroupSaleIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setGroupSalePrice(dateSegmentPriceDTO.getGroupSaleIncreaseAmount());
                    priceInfoDO.setGroupSaleOperateType(dateSegmentPriceDTO.getGroupSaleOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getGroupSaleOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getGroupSaleIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getGroupSaleOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getGroupSaleIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getGroupSaleOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getGroupSaleIncreaseAmount());
                    }
                    priceInfoDO.setGroupSalePrice(afterPrice);
                    priceInfoDO.setGroupSaleOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.B2B_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getB2bIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setB2bSalePrice(dateSegmentPriceDTO.getB2bIncreaseAmount());
                    priceInfoDO.setB2bOperateType(dateSegmentPriceDTO.getB2bOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getB2bOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getB2bIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getB2bOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getB2bIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getB2bOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getB2bIncreaseAmount());
                    }
                    priceInfoDO.setB2bSalePrice(afterPrice);
                    priceInfoDO.setB2bOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.TONGCHENG_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getTongchengIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setTongchengSalePrice(dateSegmentPriceDTO.getTongchengIncreaseAmount());
                    priceInfoDO.setTongchengOperateType(dateSegmentPriceDTO.getTongchengOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getTongchengOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getTongchengIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getTongchengOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getTongchengIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getTongchengOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getTongchengIncreaseAmount());
                    }
                    priceInfoDO.setTongchengSalePrice(afterPrice);
                    priceInfoDO.setTongchengOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.TUNIU_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getTuniuIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setTuniuSalePrice(dateSegmentPriceDTO.getTuniuIncreaseAmount());
                    priceInfoDO.setTuniuOperateType(dateSegmentPriceDTO.getTuniuOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getTuniuOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getTuniuIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getTuniuOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getTuniuIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getTuniuOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getTuniuIncreaseAmount());
                    }
                    priceInfoDO.setTuniuSalePrice(afterPrice);
                    priceInfoDO.setTuniuOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.XMD_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getXmdIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setXmdSalePrice(dateSegmentPriceDTO.getXmdIncreaseAmount());
                    priceInfoDO.setXmdOperateType(dateSegmentPriceDTO.getXmdOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getXmdOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getXmdIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getXmdOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getXmdIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getXmdOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getXmdIncreaseAmount());
                    }
                    priceInfoDO.setXmdSalePrice(afterPrice);
                    priceInfoDO.setXmdOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.JD_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getJdIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setJdSalePrice(dateSegmentPriceDTO.getJdIncreaseAmount());
                    priceInfoDO.setJdOperateType(dateSegmentPriceDTO.getJdOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getJdOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getJdIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getJdOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getJdIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getJdOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getJdIncreaseAmount());
                    }
                    priceInfoDO.setJdSalePrice(afterPrice);
                    priceInfoDO.setJdOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.TAOBAO_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getTaobaoIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setTaobaoSalePrice(dateSegmentPriceDTO.getTaobaoIncreaseAmount());
                    priceInfoDO.setTaobaoOperateType(dateSegmentPriceDTO.getTaobaoOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getTaobaoOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getTaobaoIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getTaobaoOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getTaobaoIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getTaobaoOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getTaobaoIncreaseAmount());
                    }
                    priceInfoDO.setTaobaoSalePrice(afterPrice);
                    priceInfoDO.setTaobaoOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.QUNAR_USD_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getQunarUsdIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setQunarUsdSalePrice(dateSegmentPriceDTO.getQunarUsdIncreaseAmount());
                    priceInfoDO.setQunarUsdOperateType(dateSegmentPriceDTO.getQunarUsdOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getQunarUsdOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getQunarUsdIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getQunarUsdOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getQunarUsdIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getQunarUsdOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getQunarUsdIncreaseAmount());
                    }
                    priceInfoDO.setQunarUsdSalePrice(afterPrice);
                    priceInfoDO.setQunarUsdOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }else if(ChannelPriceEnum.QUNAR_SON_SALE_PRICE.key == modifyPrice && null != dateSegmentPriceDTO.getQunarSonIncreaseAmount()){
                if(null != priceStandard && priceStandard == ChannelPriceEnum.NO_BASE_PRICE.key){
                    //无基本价
                    priceInfoDO.setQunarSonSalePrice(dateSegmentPriceDTO.getQunarSonIncreaseAmount());
                    priceInfoDO.setQunarSonOperateType(dateSegmentPriceDTO.getQunarSonOperateType());
                }else{
                    if(PriceOperateTypeEnum.ADD.key == dateSegmentPriceDTO.getQunarSonOperateType()){
                        afterPrice = price.add(dateSegmentPriceDTO.getQunarSonIncreaseAmount());
                    }else if(PriceOperateTypeEnum.DECREASE.key == dateSegmentPriceDTO.getQunarSonOperateType()){
                        afterPrice = price.subtract(dateSegmentPriceDTO.getQunarSonIncreaseAmount());
                    }else if(PriceOperateTypeEnum.MULTIPLICATION.key == dateSegmentPriceDTO.getQunarSonOperateType()){
                        afterPrice = price.multiply(dateSegmentPriceDTO.getQunarSonIncreaseAmount());
                    }
                    priceInfoDO.setQunarSonSalePrice(afterPrice);
                    priceInfoDO.setQunarSonOperateType(PriceOperateTypeEnum.SET_TO.key);
                }
            }
        }
    }

    private void assemblePriceLogPO(Integer modifyPrice,PriceInfoLogDO priceInfoLogDO,DateSegmentPriceDTO dateSegmentPriceDTO){
        if(null != modifyPrice){
            if(ChannelPriceEnum.BASE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getBasePriceOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getB2bIncreaseAmount());
            }else if (ChannelPriceEnum.CTRIP_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getCtripOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getCtripIncreaseAmount());
            }else if(ChannelPriceEnum.QUNAR_B2B_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getQunarB2BOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getQunarB2BIncreaseAmount());
            }else if(ChannelPriceEnum.QUNAR_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getQunarOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getQunarIncreaseAmount());
            } else if(ChannelPriceEnum.QUNAR_NGT_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getQunarNgtOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getQunarNgtIncreaseAmount());
            }else if(ChannelPriceEnum.ELONG_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getElongOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getElongIncreaseAmount());
            }else if(ChannelPriceEnum.GROUP_BASE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getGroupBaseOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getGroupBaseIncreaseAmount());
            }else if(ChannelPriceEnum.GROUP_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getGroupSaleOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getGroupSaleIncreaseAmount());
            }else if(ChannelPriceEnum.B2B_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getB2bOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getB2bIncreaseAmount());
            }else if(ChannelPriceEnum.TONGCHENG_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getTongchengOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getTongchengIncreaseAmount());
            }else if(ChannelPriceEnum.TUNIU_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getTuniuOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getTuniuIncreaseAmount());
            }else if(ChannelPriceEnum.XMD_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getXmdOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getXmdIncreaseAmount());
            }else if(ChannelPriceEnum.JD_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getJdOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getJdIncreaseAmount());
            }else if(ChannelPriceEnum.TAOBAO_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getTaobaoOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getTaobaoIncreaseAmount());
            }else if(ChannelPriceEnum.QUNAR_USD_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getQunarUsdOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getQunarUsdIncreaseAmount());
            }else if(ChannelPriceEnum.QUNAR_SON_SALE_PRICE.key.compareTo(modifyPrice) == 0){
                priceInfoLogDO.setModifyType(dateSegmentPriceDTO.getQunarSonOperateType());
                priceInfoLogDO.setModifyValue(dateSegmentPriceDTO.getQunarSonIncreaseAmount());
            }
        }
    }
}
