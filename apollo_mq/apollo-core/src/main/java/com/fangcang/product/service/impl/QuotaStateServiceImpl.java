package com.fangcang.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.IncrementDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.PageOperateEnum;
import com.fangcang.common.enums.QuotaOperateTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.*;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.domain.QuotaAccountDO;
import com.fangcang.product.domain.QuotaLogDO;
import com.fangcang.product.domain.QuotaStateDO;
import com.fangcang.product.domain.SystemDebitedQuotaDO;
import com.fangcang.product.dto.*;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.mapper.QuotaAccountMapper;
import com.fangcang.product.mapper.QuotaLogMapper;
import com.fangcang.product.mapper.QuotaStateMapper;
import com.fangcang.product.mapper.SystemDebitedQuotaMapper;
import com.fangcang.product.request.*;
import com.fangcang.product.response.DebuctOrRetreatQuotaResponseDTO;
import com.fangcang.product.response.DebuctQuotaQueryResponseDTO;
import com.fangcang.product.response.QuotaStateResponseDTO;
import com.fangcang.product.service.IncrementService;
import com.fangcang.product.service.QuotaStateService;
import com.fangcang.product.thread.IncrementThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by ASUS on 2018/5/28.
 */
@Slf4j
@Service
public class QuotaStateServiceImpl implements QuotaStateService {

    @Autowired
    private QuotaStateMapper quotaStateMapper;

    @Autowired
    private RedisLockUtil redisLockUtil;

    @Autowired
    private PricePlanMapper pricePlanMapper;

    @Autowired
    private QuotaAccountMapper quotaAccountMapper;

    @Autowired
    private QuotaLogMapper quotaLogMapper;

    @Autowired
    private QuotaStateService quotaStateService;

    @Autowired
    private SystemDebitedQuotaMapper systemDebitedQuotaMapper;

    @Resource(name = "incrementExecutor")
    private ThreadPoolTaskExecutor incrementExecutor;

    @Autowired
    private IncrementConfig incrementConfig;

    @Autowired
    private IncrementService incrementService;

    private static final int TIMEOUT = 10 * 1000;//超时时间10s

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO batchModifyQuotaState(BatchSaveQuotaStateRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            Date incrementStartDate=null;
            Date incrementEndDate=null;
            List<QuotaStateDO> quotaStateDOList = new ArrayList<>();
            List<BatchQuotaStateDTO> quotaStateList = requestDTO.getQuotaStateList();
            QuotaStateDO quotaStateDO = null;
            //新增配额账号的集合
            List<Long> needAddQuotaAccountList = new ArrayList<>();
            //记录操作日志
            List<QuotaLogDO> quotaLogDOList = new ArrayList<>();
            //增量推送
            List<IncrementDTO> incrementDTOList = new ArrayList<>();
            QuotaLogDO quotaLogDO = null;
            for (BatchQuotaStateDTO quotaStateDTO : quotaStateList) {
                if(null == quotaStateDTO.getQuotaAccountId()){
                    log.error("QuotaAccountId or PricePlanId has null.");
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                    responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                    return responseDTO;
                }
                List<DateSegmentQuotaDTO> dateSegmentQuotaList = quotaStateDTO.getDateSegmentQuotaList();
                if(CollectionUtils.isEmpty(dateSegmentQuotaList)){
                    log.error("dateSegmentQuotaList has empty.");
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                    responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                    return responseDTO;
                }
                //产品页面批量调整传价格计划ID，共享池批量调整不传价格计划ID
                if(null != quotaStateDTO.getPricePlanId() && Long.valueOf(0).compareTo(quotaStateDTO.getQuotaAccountId()) == 0){
                    needAddQuotaAccountList.add(quotaStateDTO.getPricePlanId());
                }

                Map<Date,QuotaStateDO> dateQuotaStateDOMap = new HashMap<>();//重复日期直接覆盖
                for (DateSegmentQuotaDTO dateSegmentQuotaDTO : dateSegmentQuotaList) {
                    if(null == dateSegmentQuotaDTO
                            || null == dateSegmentQuotaDTO.getStartDate()
                            || null == dateSegmentQuotaDTO.getEndDate()
                            || !StringUtil.isValidString(dateSegmentQuotaDTO.getWeeks())
                            || null == dateSegmentQuotaDTO.getQuotaState()
                            || null == dateSegmentQuotaDTO.getOverDraft()){
                        log.error("Parameter has null.");
                        responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                        responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                        responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                        return responseDTO;
                    }
                    if (PageOperateEnum.NO_CHANGED.key == dateSegmentQuotaDTO.getQuotaState()
                            && ((QuotaOperateTypeEnum.ADD.key == dateSegmentQuotaDTO.getOperateType()
                            || QuotaOperateTypeEnum.DECREASE.key == dateSegmentQuotaDTO.getOperateType())
                            && (null == dateSegmentQuotaDTO.getQuotaNum() || 0 == dateSegmentQuotaDTO.getQuotaNum())
                            )
                            && PageOperateEnum.NO_CHANGED.key == dateSegmentQuotaDTO.getOverDraft()
                            ) {
                        continue;//没有变化无需更新
                    }
                    //组装日志对象
                    quotaLogDO = new QuotaLogDO();
                    quotaLogDO.setPricePlanId(quotaStateDTO.getPricePlanId());
                    quotaLogDO.setQuotaAccountId(quotaStateDTO.getQuotaAccountId());
                    quotaLogDO.setStartDate(dateSegmentQuotaDTO.getStartDate());
                    quotaLogDO.setEndDate(dateSegmentQuotaDTO.getEndDate());
                    quotaLogDO.setWeeks(dateSegmentQuotaDTO.getWeeks());
                    quotaLogDO.setMerchantCode(requestDTO.getMerchantCode());
                    quotaLogDO.setCreator(requestDTO.getCreator());
                    quotaLogDO.setOperateType(dateSegmentQuotaDTO.getOperateType());
                    quotaLogDO.setQuotaNum(dateSegmentQuotaDTO.getQuotaNum());
                    quotaLogDO.setOverDraft(dateSegmentQuotaDTO.getOverDraft());
                    quotaLogDO.setQuotaState(dateSegmentQuotaDTO.getQuotaState());
                    quotaLogDOList.add(quotaLogDO);

                    List<Date> saleDateList = WeekUtil.getSaleDate(dateSegmentQuotaDTO.getStartDate(), dateSegmentQuotaDTO.getEndDate(), dateSegmentQuotaDTO.getWeeks());
                    for (Date date : saleDateList) {
                        quotaStateDO = new QuotaStateDO();
                        quotaStateDO.setPricePlanId(quotaStateDTO.getPricePlanId());
                        quotaStateDO.setQuotaAccountId(quotaStateDTO.getQuotaAccountId());
                        quotaStateDO.setSaleDate(DateUtil.dateFormat(date, DateUtil.defaultFormat));
                        //房态
                        if (PageOperateEnum.NO_CHANGED.key != dateSegmentQuotaDTO.getQuotaState()) {
                            quotaStateDO.setQuotaState(dateSegmentQuotaDTO.getQuotaState());
                        }
                        if (null != dateSegmentQuotaDTO.getQuotaNum() &&
                                !((QuotaOperateTypeEnum.ADD.key == dateSegmentQuotaDTO.getOperateType()
                                        || QuotaOperateTypeEnum.DECREASE.key == dateSegmentQuotaDTO.getOperateType())
                                        && 0 == dateSegmentQuotaDTO.getQuotaNum()
                                )) {
                            quotaStateDO.setQuotaNum(dateSegmentQuotaDTO.getQuotaNum());
                            quotaStateDO.setAllQuotaNum(dateSegmentQuotaDTO.getQuotaNum());
                            quotaStateDO.setOperateType(dateSegmentQuotaDTO.getOperateType());
                        }
                        if (PageOperateEnum.NO_CHANGED.key != dateSegmentQuotaDTO.getOverDraft()) {
                            quotaStateDO.setOverDraft(dateSegmentQuotaDTO.getOverDraft());
                        }
                        quotaStateDO.setCreator(requestDTO.getCreator());
                        quotaStateDO.setModifier(requestDTO.getModifier());
                        dateQuotaStateDOMap.put(quotaStateDO.getSaleDate(),quotaStateDO);
                    }
                    //增量推送
                    IncrementDTO incrementDTO = new IncrementDTO();
                    incrementDTO.setMerchantCode(requestDTO.getMerchantCode());
                    incrementDTO.setQuotaAccountId(quotaStateDTO.getQuotaAccountId());
                    incrementDTO.setMRatePlanId(quotaStateDTO.getPricePlanId());
                    incrementDTO.setStartDate(DateUtil.dateToString(dateSegmentQuotaDTO.getStartDate()));
                    incrementDTO.setEndDate(DateUtil.dateToString(dateSegmentQuotaDTO.getEndDate()));
                    incrementDTOList.add(incrementDTO);

                    if (incrementStartDate==null){
                        incrementStartDate=dateSegmentQuotaDTO.getStartDate();
                    }else if(DateUtil.compare(incrementStartDate,dateSegmentQuotaDTO.getStartDate())==1){
                        incrementStartDate=dateSegmentQuotaDTO.getStartDate();
                    }
                    if (incrementEndDate==null){
                        incrementEndDate=dateSegmentQuotaDTO.getEndDate();
                    }else if(DateUtil.compare(incrementEndDate,dateSegmentQuotaDTO.getEndDate())==-1){
                        incrementEndDate=dateSegmentQuotaDTO.getEndDate();
                    }
                }
                quotaStateDOList.addAll(new ArrayList(dateQuotaStateDOMap.values()));
            }
            Map<Long,Long> pricePlanQuotaAccountMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(needAddQuotaAccountList)){
                //批量新增配额账号ID
                StringBuilder stringBuilder = new StringBuilder();
                for (Long pricePlanId : needAddQuotaAccountList) {
                    stringBuilder.append(pricePlanId).append(",");
                }
                DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO = new DynamicPricePlanQueryDTO();
                dynamicPricePlanQueryDTO.setPricePlanIds(stringBuilder.toString().substring(0,stringBuilder.toString().length() - 1));
                List<PricePlanDO> pricePlanDOList = pricePlanMapper.dynamicQueryPricePlanList(dynamicPricePlanQueryDTO);
                List<QuotaAccountDO> quotaAccountDOList = new ArrayList<>();
                QuotaAccountDO quotaAccountDO = null;
                for (PricePlanDO pricePlanDO : pricePlanDOList) {
                    quotaAccountDO = new QuotaAccountDO();
                    quotaAccountDO.setQuotaAccountName(pricePlanDO.getPricePlanName());
                    quotaAccountDO.setHotelId(pricePlanDO.getHotelId());
                    quotaAccountDO.setSupplyCode(pricePlanDO.getSupplyCode());
                    quotaAccountDO.setCreator(pricePlanDO.getCreator());
                    quotaAccountDO.setIsShare(0);
                    quotaAccountDOList.add(quotaAccountDO);
                }
                //批量新增配额账号
                quotaAccountMapper.batchSaveQuotaAccount(quotaAccountDOList);
                for (int i = 0; i < quotaAccountDOList.size(); i++) {
                    QuotaAccountDO quotaAccountDOResult = quotaAccountDOList.get(i);
                    PricePlanDO pricePlanDO = pricePlanDOList.get(i);
                    pricePlanQuotaAccountMap.put(pricePlanDO.getPricePlanId(),quotaAccountDOResult.getQuotaAccountId());
                }
                //价格计划绑定配额账号
                List<PricePlanDO> bindQuotaAccountList = new ArrayList<>();
                PricePlanDO bindQuotaAccountDO = null;
                for(Map.Entry<Long,Long> m : pricePlanQuotaAccountMap.entrySet()){
                    bindQuotaAccountDO = new PricePlanDO();
                    bindQuotaAccountDO.setPricePlanId(m.getKey());
                    bindQuotaAccountDO.setQuotaAccountId(m.getValue());
                    bindQuotaAccountDO.setModifier(requestDTO.getModifier());
                    bindQuotaAccountList.add(bindQuotaAccountDO);
                }
                pricePlanMapper.batchUpdatePricePlan(bindQuotaAccountList);
            }
            //批量保存配额房态
            if (!CollectionUtils.isEmpty(quotaStateDOList)) {
                for (QuotaStateDO quotaState : quotaStateDOList) {
                    if(pricePlanQuotaAccountMap.containsKey(quotaState.getPricePlanId())){
                        quotaState.setQuotaAccountId(pricePlanQuotaAccountMap.get(quotaState.getPricePlanId()));
                    }
                }
                quotaStateMapper.batchSaveOrUpdateQuotaState(quotaStateDOList);
            }
            //批量保存操作日志
            if(!CollectionUtils.isEmpty(quotaLogDOList)){
                for(QuotaLogDO quotaLog : quotaLogDOList){
                    if(pricePlanQuotaAccountMap.containsKey(quotaLog.getPricePlanId())){
                        quotaLog.setQuotaAccountId(pricePlanQuotaAccountMap.get(quotaLog.getPricePlanId()));
                    }
                }
                quotaLogMapper.batchSaveQuotaLog(quotaLogDOList);
            }
            //增量推送
            if(!CollectionUtils.isEmpty(incrementDTOList)){
                StringBuilder quotaAccountIds = new StringBuilder();
                for(IncrementDTO incrementDTO : incrementDTOList){
                    if(pricePlanQuotaAccountMap.containsKey(incrementDTO.getMRatePlanId())){
                        incrementDTO.setQuotaAccountId(pricePlanQuotaAccountMap.get(incrementDTO.getMRatePlanId()));
                    }
                    if(Long.valueOf(0).compareTo(incrementDTO.getQuotaAccountId()) != 0){
                        quotaAccountIds.append(incrementDTO.getQuotaAccountId()).append(",");
                    }
                }
                Map<Long,QuotaAccountDO> quotaAccountDOMap = new HashMap<>();
                if(quotaAccountIds.length() > 0){
                    QuotaAccountQueryDTO quotaAccountQueryDTO = new QuotaAccountQueryDTO();
                    quotaAccountQueryDTO.setQuotaAccountIds(quotaAccountIds.toString().substring(0,quotaAccountIds.toString().length() -1));
                    List<QuotaAccountDO> quotaAccountDOList = quotaAccountMapper.dynamicQueryQuotaAccount(quotaAccountQueryDTO);
                    for (QuotaAccountDO quotaAccountDO : quotaAccountDOList) {
                        quotaAccountDOMap.put(quotaAccountDO.getQuotaAccountId(),quotaAccountDO);
                    }
                }
                List<IncrementDTO> fiterAfterList = new ArrayList<>();
                for(IncrementDTO incrementDTO : incrementDTOList){
                    if(quotaAccountDOMap.containsKey(incrementDTO.getQuotaAccountId())){
                        incrementDTO.setMHotelId(quotaAccountDOMap.get(incrementDTO.getQuotaAccountId()).getHotelId());
                        fiterAfterList.add(incrementDTO);
                    }
                }
                //增量批推送次数过多，以酒店为单位进行推送
                if (!CollectionUtils.isEmpty(fiterAfterList) && fiterAfterList.size()>6){
                    fiterAfterList.clear();
                    Set<Long> hotelIdSet=new HashSet<>();
                    for (IncrementDTO incrementDTO : incrementDTOList){
                        if (incrementDTO.getQuotaAccountId()!=null
                                && quotaAccountDOMap.get(incrementDTO.getQuotaAccountId())!=null
                                && quotaAccountDOMap.get(incrementDTO.getQuotaAccountId()).getHotelId()!=null
                                && !hotelIdSet.contains(quotaAccountDOMap.get(incrementDTO.getQuotaAccountId()).getHotelId())){
                            IncrementDTO fiterIncrementDTO=new IncrementDTO();
                            fiterIncrementDTO.setMerchantCode(incrementDTO.getMerchantCode());
                            fiterIncrementDTO.setStartDate(DateUtil.dateToString(incrementStartDate));
                            fiterIncrementDTO.setEndDate(DateUtil.dateToString(incrementEndDate));
                            fiterIncrementDTO.setMHotelId(quotaAccountDOMap.get(incrementDTO.getQuotaAccountId()).getHotelId());
                            fiterIncrementDTO.setHasIncrement(true);
                            fiterAfterList.add(fiterIncrementDTO);
                            hotelIdSet.add(quotaAccountDOMap.get(incrementDTO.getQuotaAccountId()).getHotelId());
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
            log.error("batchModifyQuotaState has error",e);
            throw new ServiceException("batchModifyQuotaState has error",e);
        }
        return responseDTO;
    }

    public ResponseDTO<QuotaStateResponseDTO> queryQuotaStateDailyInfo(QuotaStateQueryDTO quotaStateQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == quotaStateQueryDTO.getStartDate() || null == quotaStateQueryDTO.getEndDate()){
            log.error("Startdate or endDate has null.");
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return responseDTO;
        }
        try {
            QuotaStateResponseDTO quotaStateResponseDTO = new QuotaStateResponseDTO();
            quotaStateResponseDTO.setQuotaAccountId(quotaStateQueryDTO.getQuotaAccountId());
            quotaStateQueryDTO.setStartDate(DateUtil.dateFormat(quotaStateQueryDTO.getStartDate(),DateUtil.defaultFormat));
            quotaStateQueryDTO.setEndDate(DateUtil.dateFormat(quotaStateQueryDTO.getEndDate(),DateUtil.defaultFormat));
            List<QuotaStateDO> quotaStateDOList = quotaStateMapper.queryQuotaStateDailyInfo(quotaStateQueryDTO);
            Map<String,QuotaStateDailyDTO> saleDateMap = new HashMap<>();
            QuotaStateDailyDTO quotaStateDailyDTO = null;
            if(!CollectionUtils.isEmpty(quotaStateDOList)){
                quotaStateResponseDTO.setQuotaAccountName(quotaStateDOList.get(0).getQuotaAccountName());
                for (QuotaStateDO quotaStateDO : quotaStateDOList) {
                    if(null != quotaStateDO.getSaleDate()){
                        quotaStateDailyDTO = new QuotaStateDailyDTO();
                        quotaStateDailyDTO = PropertyCopyUtil.transfer(quotaStateDO,QuotaStateDailyDTO.class);
                        saleDateMap.put(DateUtil.dateToString(quotaStateDO.getSaleDate()),quotaStateDailyDTO);
                    }
                }
            }
            List<QuotaStateDailyDTO> quotaStateDailyList = new ArrayList<>();
            List<Date> dateList = DateUtil.getDateList(quotaStateQueryDTO.getStartDate(),quotaStateQueryDTO.getEndDate());
            for (Date date : dateList) {
                if(saleDateMap.containsKey(DateUtil.dateToString(date))){
                    quotaStateDailyDTO = saleDateMap.get(DateUtil.dateToString(date));
                }else{
                    quotaStateDailyDTO = new QuotaStateDailyDTO();
                    quotaStateDailyDTO.setSaleDate(date);
                }
                quotaStateDailyList.add(quotaStateDailyDTO);
            }
            quotaStateResponseDTO.setQuotaStateDailyList(quotaStateDailyList);
            responseDTO.setModel(quotaStateResponseDTO);
        } catch (Exception e) {
            log.error("queryQuotaStateDailyInfo",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO saveOrUpdateQuotaStateDaily(QuotaStateDailyRequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            if (PageOperateEnum.NO_CHANGED.key == requestDTO.getQuotaState()
                    && ((QuotaOperateTypeEnum.ADD.key == requestDTO.getOperateType()
                    || QuotaOperateTypeEnum.DECREASE.key == requestDTO.getOperateType())
                    && (null == requestDTO.getQuotaNum() || 0 == requestDTO.getQuotaNum())
                    )
                    && PageOperateEnum.NO_CHANGED.key == requestDTO.getOverDraft()
                    ) {
                log.warn("No change does not need to be updated.");
                return responseDTO;
            }
            BatchSaveQuotaStateRequestDTO batchSaveQuotaStateRequestDTO = new BatchSaveQuotaStateRequestDTO();
            batchSaveQuotaStateRequestDTO.setMerchantCode(requestDTO.getMerchantCode());
            batchSaveQuotaStateRequestDTO.setCreator(requestDTO.getCreator());
            batchSaveQuotaStateRequestDTO.setModifier(requestDTO.getModifier());

            List<BatchQuotaStateDTO> quotaStateList = new ArrayList<>();
            BatchQuotaStateDTO batchQuotaStateDTO = new BatchQuotaStateDTO();
            batchQuotaStateDTO.setQuotaAccountId(requestDTO.getQuotaAccountId());

            List<DateSegmentQuotaDTO> dateSegmentQuotaList = new ArrayList<>();
            DateSegmentQuotaDTO dateSegmentQuotaDTO = null;
            List<DateDTO> saleDateList = requestDTO.getSaleDateList();
            for (DateDTO dateDTO : saleDateList) {
                dateSegmentQuotaDTO = new DateSegmentQuotaDTO();
                dateSegmentQuotaDTO.setStartDate(dateDTO.getSaleDate());
                dateSegmentQuotaDTO.setEndDate(dateDTO.getSaleDate());
                dateSegmentQuotaDTO.setWeeks("1,2,3,4,5,6,7");
                dateSegmentQuotaDTO.setQuotaNum(requestDTO.getQuotaNum());
                dateSegmentQuotaDTO.setQuotaState(requestDTO.getQuotaState());
                dateSegmentQuotaDTO.setOperateType(requestDTO.getOperateType());
                dateSegmentQuotaDTO.setOverDraft(requestDTO.getOverDraft());
                dateSegmentQuotaList.add(dateSegmentQuotaDTO);
            }
            batchQuotaStateDTO.setDateSegmentQuotaList(dateSegmentQuotaList);
            quotaStateList.add(batchQuotaStateDTO);
            batchSaveQuotaStateRequestDTO.setQuotaStateList(quotaStateList);

            responseDTO = quotaStateService.batchModifyQuotaState(batchSaveQuotaStateRequestDTO);
        } catch (Exception e) {
            log.error("saveOrUpdateQuotaStateDaily has error",e);
            throw new ServiceException("saveOrUpdateQuotaStateDaily has error",e);
        }
        return responseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO<DebuctOrRetreatQuotaResponseDTO> debuctOrRetreatQuota(DebuctOrRetreatQuotaRequestDTO debuctOrRetreatQuotaRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        log.warn("debuctOrRetreatQuota param : " + JSON.toJSONString(debuctOrRetreatQuotaRequestDTO));
        /**
         * 扣配额逻辑:
         *      1.先查出剩余配额数
         *      2.如果剩余配额大余要扣配额数，则全部扣除；如果剩余配额小余要扣配额数，则返回失败
         *      3.修改配额表
         *      4.t_htlpro_systemdebitedquota新增记录
         *      5.返回成功扣除或退的配额数
         * 退配额逻辑:
         *      1.查询该供货单，该配额的扣退配额记录。
         *      2.比较退配额数目和已扣配额数目。
         *      3.记退配额日志
         */
        String key = Constant.DEBUCT_QUOTA_KEY_PREFIX + debuctOrRetreatQuotaRequestDTO.getSupplyOrderCode();
        Long value = System.currentTimeMillis() + TIMEOUT;
        Boolean lockResult = redisLockUtil.lock(key,value.toString());
        DebuctOrRetreatQuotaResponseDTO debuctOrRetreatQuotaResponseDTO = new DebuctOrRetreatQuotaResponseDTO();
        try {
            if(lockResult){
                List<QuotaStateDailyDTO> quotaStateDailyList = debuctOrRetreatQuotaRequestDTO.getQuotaStateDailyList();
                if(1 == debuctOrRetreatQuotaRequestDTO.getQuotaType()){
                    //扣配额
                    Map<String,QuotaStateDO> quotaStateDOMap = new HashMap<>();
                    QuotaStateQueryDTO quotaStateQueryDTO = new QuotaStateQueryDTO();
                    quotaStateQueryDTO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
                    quotaStateQueryDTO.setStartDate(debuctOrRetreatQuotaRequestDTO.getStartDate());
                    quotaStateQueryDTO.setEndDate(debuctOrRetreatQuotaRequestDTO.getEndDate());
                    //1.先查出剩余配额数
                    List<QuotaStateDO> quotaStateDBList = quotaStateMapper.queryQuotaStateDailyInfo(quotaStateQueryDTO);
                    if(!CollectionUtils.isEmpty(quotaStateDBList)){
                        for (QuotaStateDO stateDO : quotaStateDBList) {
                            Integer quotaNum = stateDO.getQuotaNum() == null ? new Integer(0) : stateDO.getQuotaNum();
                            stateDO.setQuotaNum(quotaNum);
                            quotaStateDOMap.put(DateUtil.dateToString(stateDO.getSaleDate()),stateDO);
                        }
                    }
                    //2.如果剩余配额大余要扣配额数，则全部扣除；如果剩余配额小余要扣配额数，则返回失败
                    List<QuotaStateDO> quotaStateDOList = new ArrayList<>();
                    List<SystemDebitedQuotaDO> systemDebitedQuotaDOList = new ArrayList<>();
                    List<QuotaStateDTO> returnQuotaStateList = new ArrayList<>();
                    QuotaStateDO quotaStateDO = null;
                    SystemDebitedQuotaDO systemDebitedQuotaDO = null;
                    QuotaStateDTO quotaStateDTO = null;
                    int needDebuctQuotaNum = 0;

                    for (QuotaStateDailyDTO quotaStateDailyDTO : quotaStateDailyList) {
                        if(null == quotaStateDailyDTO || null == quotaStateDailyDTO.getQuotaNum()){
                            continue;
                        }
                        Integer dbQuotaNum = 0;
                        if(quotaStateDOMap.containsKey(DateUtil.dateToString(quotaStateDailyDTO.getSaleDate()))){
                            dbQuotaNum = quotaStateDOMap.get(DateUtil.dateToString(quotaStateDailyDTO.getSaleDate())).getQuotaNum() == null ? new Integer(0) : quotaStateDOMap.get(DateUtil.dateToString(quotaStateDailyDTO.getSaleDate())).getQuotaNum();
                        }
                        if(quotaStateDailyDTO.getQuotaNum() > dbQuotaNum){
                            StringBuilder errorBulder = new StringBuilder();
                            errorBulder.append("订单号:").append(debuctOrRetreatQuotaRequestDTO.getOrderCode()).append(",该售卖日期:")
                                    .append( DateUtil.dateToString(quotaStateDailyDTO.getSaleDate())).append(",配额不够,数据库配额数量:")
                                    .append(dbQuotaNum).append(",需要扣的配额数量:" ).append(quotaStateDailyDTO.getQuotaNum());
                            log.error(errorBulder.toString());
                            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                            responseDTO.setFailReason(errorBulder.toString());
                            return responseDTO;
                        }
                        needDebuctQuotaNum = quotaStateDailyDTO.getQuotaNum();
                        if(needDebuctQuotaNum > 0){
                            quotaStateDO = new QuotaStateDO();
                            quotaStateDO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
                            quotaStateDO.setSaleDate(quotaStateDailyDTO.getSaleDate());
                            quotaStateDO.setModifier(debuctOrRetreatQuotaRequestDTO.getModifier());
                            quotaStateDO.setQuotaNum(0 - needDebuctQuotaNum);
                            quotaStateDO.setUsedQuotaNum(needDebuctQuotaNum);
                            quotaStateDO.setOperateType(1);
                            quotaStateDOList.add(quotaStateDO);
                            //扣配额明细
                            systemDebitedQuotaDO = new SystemDebitedQuotaDO();
                            systemDebitedQuotaDO.setSupplyOrderId(debuctOrRetreatQuotaRequestDTO.getSupplyOrderId());
                            systemDebitedQuotaDO.setSupplyOrderCode(debuctOrRetreatQuotaRequestDTO.getSupplyOrderCode());
                            systemDebitedQuotaDO.setPricePlanId(debuctOrRetreatQuotaRequestDTO.getPricePlanId());
                            systemDebitedQuotaDO.setSaleDate(quotaStateDailyDTO.getSaleDate());
                            systemDebitedQuotaDO.setQuotaNum(needDebuctQuotaNum);
                            systemDebitedQuotaDO.setQuotaType(1);
                            systemDebitedQuotaDO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
                            systemDebitedQuotaDO.setOrderCode(debuctOrRetreatQuotaRequestDTO.getOrderCode());
                            systemDebitedQuotaDO.setCreator(debuctOrRetreatQuotaRequestDTO.getModifier());
                            systemDebitedQuotaDOList.add(systemDebitedQuotaDO);
                            //返回成功扣除的配额
                            quotaStateDTO = new QuotaStateDTO();
                            quotaStateDTO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
                            quotaStateDTO.setSaleDate(quotaStateDailyDTO.getSaleDate());
                            quotaStateDTO.setQuotaNum(needDebuctQuotaNum);
                            returnQuotaStateList.add(quotaStateDTO);
                        }else if(needDebuctQuotaNum <= 0){
                            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                            responseDTO.setFailReason(DateUtil.dateToString(quotaStateDailyDTO.getSaleDate()) + "无配额可扣");
                            return responseDTO;
                        }
                    }
                    if(!CollectionUtils.isEmpty(quotaStateDOList)){
                        quotaStateMapper.batchSaveOrUpdateQuotaState(quotaStateDOList);
                    }
                    if(!CollectionUtils.isEmpty(systemDebitedQuotaDOList)){
                        systemDebitedQuotaMapper.batchSaveSystemDebitedQuota(systemDebitedQuotaDOList);
                    }
                    debuctOrRetreatQuotaResponseDTO.setQuotaStateList(returnQuotaStateList);
                }else if(2 == debuctOrRetreatQuotaRequestDTO.getQuotaType()){
                    //退配额
                    //1.查询该供货单，该配额的扣退配额记录。
                    SystemDebitedQuotaDO systemDebitedQuotaDO = new SystemDebitedQuotaDO();
                    systemDebitedQuotaDO.setSupplyOrderId(debuctOrRetreatQuotaRequestDTO.getSupplyOrderId());
                    systemDebitedQuotaDO.setSupplyOrderCode(debuctOrRetreatQuotaRequestDTO.getSupplyOrderCode());
                    List<SystemDebitedQuotaDO> systemDebitedQuotaDOList = systemDebitedQuotaMapper.dynamicQuerySystemDebitedQuota(systemDebitedQuotaDO);
                    if(CollectionUtils.isEmpty(systemDebitedQuotaDOList)){
                        log.error("Retrea quota has error,param:" + JSON.toJSONString(debuctOrRetreatQuotaRequestDTO));
                        responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                        responseDTO.setFailCode(ErrorCodeEnum.RETREAT_QUOTA_FAILED.errorCode);
                        responseDTO.setFailReason(ErrorCodeEnum.RETREAT_QUOTA_FAILED.errorDesc);
                        return responseDTO;
                    }
                    Map<String,SystemDebitedQuotaDO> dedectedQuotaNumMap = new HashMap<>();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (SystemDebitedQuotaDO debitedQuotaDO : systemDebitedQuotaDOList) {
                        stringBuilder.append(debitedQuotaDO.getSupplyOrderCode()).append("-").append(debitedQuotaDO.getQuotaAccountId()).append("-").append(DateUtil.dateToString(debitedQuotaDO.getSaleDate(),"yyyy-MM-dd"));
                        if(dedectedQuotaNumMap.containsKey(stringBuilder.toString())){
                            SystemDebitedQuotaDO d = dedectedQuotaNumMap.get(stringBuilder.toString());
                            if(1 == debitedQuotaDO.getQuotaType()){
                                d.setQuotaNum(d.getQuotaNum() + debitedQuotaDO.getQuotaNum());
                                dedectedQuotaNumMap.put(stringBuilder.toString(),d);
                            }else if(2 == debitedQuotaDO.getQuotaType()){
                                d.setQuotaNum(d.getQuotaNum() - debitedQuotaDO.getQuotaNum());
                                dedectedQuotaNumMap.put(stringBuilder.toString(),d);
                            }
                        }else{
                            if(1 == debitedQuotaDO.getQuotaType()){
                                dedectedQuotaNumMap.put(stringBuilder.toString(),debitedQuotaDO);
                            }else if(2 == debitedQuotaDO.getQuotaType()){//退配额
                                debitedQuotaDO.setQuotaNum(0 - debitedQuotaDO.getQuotaNum());
                                dedectedQuotaNumMap.put(stringBuilder.toString(),debitedQuotaDO);
                            }
                        }
                        stringBuilder.setLength(0);
                    }
                    //2.比较退配额数目和已扣配额数目。已扣配额数大于0，则可退配额;
                    List<QuotaStateDO> quotaStateDOList = new ArrayList<>();
                    List<SystemDebitedQuotaDO> systemRetreatQuotaDOList = new ArrayList<>();
                    List<QuotaStateDTO> returnQuotaStateList = new ArrayList<>();
                    QuotaStateDO quotaStateDO = null;
                    SystemDebitedQuotaDO systemRetreatQuotaDO = null;
                    QuotaStateDTO quotaStateDTO = null;
                    int needRetreatQuotaNum = 0;
                    for(QuotaStateDailyDTO quotaStateDailyDTO :debuctOrRetreatQuotaRequestDTO.getQuotaStateDailyList()){
                        if(null == quotaStateDailyDTO || null == quotaStateDailyDTO.getQuotaNum()){
                            continue;
                        }
                        stringBuilder.append(debuctOrRetreatQuotaRequestDTO.getSupplyOrderCode()).append("-").append(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId()).append("-").append(DateUtil.dateToString(quotaStateDailyDTO.getSaleDate(),"yyyy-MM-dd"));
                        if(dedectedQuotaNumMap.containsKey(stringBuilder.toString())
                                && dedectedQuotaNumMap.get(stringBuilder.toString()).getQuotaNum() > 0){
                            Integer hasDebuctQuotaNum = dedectedQuotaNumMap.get(stringBuilder.toString()).getQuotaNum();
                            needRetreatQuotaNum = quotaStateDailyDTO.getQuotaNum() > hasDebuctQuotaNum ? hasDebuctQuotaNum : quotaStateDailyDTO.getQuotaNum();
                            if(needRetreatQuotaNum > 0){
                                quotaStateDO = new QuotaStateDO();
                                quotaStateDO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
                                quotaStateDO.setSaleDate(quotaStateDailyDTO.getSaleDate());
                                quotaStateDO.setModifier(debuctOrRetreatQuotaRequestDTO.getModifier());
                                quotaStateDO.setQuotaNum(needRetreatQuotaNum);
                                quotaStateDO.setUsedQuotaNum(0 - needRetreatQuotaNum);
                                quotaStateDO.setOperateType(1);
                                quotaStateDOList.add(quotaStateDO);
                                //扣配额明细
                                systemDebitedQuotaDO = new SystemDebitedQuotaDO();
                                systemDebitedQuotaDO.setSupplyOrderId(debuctOrRetreatQuotaRequestDTO.getSupplyOrderId());
                                systemDebitedQuotaDO.setSupplyOrderCode(debuctOrRetreatQuotaRequestDTO.getSupplyOrderCode());
                                systemDebitedQuotaDO.setPricePlanId(debuctOrRetreatQuotaRequestDTO.getPricePlanId());
                                systemDebitedQuotaDO.setSaleDate(quotaStateDailyDTO.getSaleDate());
                                systemDebitedQuotaDO.setQuotaNum(needRetreatQuotaNum);
                                systemDebitedQuotaDO.setQuotaType(2);
                                systemDebitedQuotaDO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
                                systemDebitedQuotaDO.setOrderCode(debuctOrRetreatQuotaRequestDTO.getOrderCode());
                                systemDebitedQuotaDO.setCreator(debuctOrRetreatQuotaRequestDTO.getModifier());
                                systemRetreatQuotaDOList.add(systemDebitedQuotaDO);
                                //返回成功扣除的配额
                                quotaStateDTO = new QuotaStateDTO();
                                quotaStateDTO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
                                quotaStateDTO.setSaleDate(quotaStateDailyDTO.getSaleDate());
                                quotaStateDTO.setQuotaNum(needRetreatQuotaNum);
                                returnQuotaStateList.add(quotaStateDTO);
                            }
                        }else{
                            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                            responseDTO.setFailReason(DateUtil.dateToString(quotaStateDailyDTO.getSaleDate()) + "无配额可退");
                            return responseDTO;
                        }

                        stringBuilder.setLength(0);
                    }
                    if(!CollectionUtils.isEmpty(quotaStateDOList)){
                        quotaStateMapper.batchSaveOrUpdateQuotaState(quotaStateDOList);
                    }
                    if(!CollectionUtils.isEmpty(systemRetreatQuotaDOList)){
                        systemDebitedQuotaMapper.batchSaveSystemDebitedQuota(systemRetreatQuotaDOList);
                    }
                    debuctOrRetreatQuotaResponseDTO.setQuotaStateList(returnQuotaStateList);
                }
                DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO = new DynamicPricePlanQueryDTO();
                dynamicPricePlanQueryDTO.setPricePlanId(debuctOrRetreatQuotaRequestDTO.getPricePlanId());
                List<PricePlanDO> pricePlanDOList = pricePlanMapper.dynamicQueryPricePlanList(dynamicPricePlanQueryDTO);
                IncrementDTO incrementDTO = new IncrementDTO();
                incrementDTO.setMerchantCode(debuctOrRetreatQuotaRequestDTO.getMerchantCode());
                PricePlanDO pricePlanDO = pricePlanDOList.get(0);
                if(null != pricePlanDO){
                    incrementDTO.setMHotelId(pricePlanDO.getHotelId());
                }
                incrementDTO.setMRatePlanId(debuctOrRetreatQuotaRequestDTO.getPricePlanId());
                incrementDTO.setStartDate(DateUtil.dateToString(debuctOrRetreatQuotaRequestDTO.getStartDate()));
                incrementDTO.setEndDate(DateUtil.dateToString(debuctOrRetreatQuotaRequestDTO.getEndDate()));
                List<IncrementDTO> incrementDTOList = new ArrayList<>();
                incrementDTOList.add(incrementDTO);
                //推送增量
                String url = URLSplitUtil.getUrl(incrementConfig);
                IncrementThread incrementThread = new IncrementThread(incrementDTOList,url,incrementService);
                incrementExecutor.execute(incrementThread);
            }else{
                log.error("Get lock has error,param:" + JSON.toJSONString(debuctOrRetreatQuotaRequestDTO));
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.GET_REDIS_LOCK_FAILED.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.GET_REDIS_LOCK_FAILED.errorDesc);
            }
        } catch (Exception e) {
            log.error("debuctOrRetreatQuotaResponseDTO has error",e);
            throw new ServiceException("debuctOrRetreatQuotaResponseDTO has error",e);
        }finally {
            redisLockUtil.unLock(key,value.toString());
        }
        responseDTO.setModel(debuctOrRetreatQuotaResponseDTO);
        return responseDTO;
    }

    public ResponseDTO<DebuctQuotaQueryResponseDTO> queryOrderDebuctQuotaInfo(DebuctQuotaQueryDTO debuctQuotaQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            DebuctQuotaQueryResponseDTO debuctQuotaQueryResponseDTO = new DebuctQuotaQueryResponseDTO();
            debuctQuotaQueryResponseDTO.setOrderCode(debuctQuotaQueryDTO.getOrderCode());

            SystemDebitedQuotaDO systemDebitedQuotaDO = new SystemDebitedQuotaDO();
            systemDebitedQuotaDO.setOrderCode(debuctQuotaQueryDTO.getOrderCode());
            systemDebitedQuotaDO.setPricePlanId(debuctQuotaQueryDTO.getPricePlanId());
            List<SystemDebitedQuotaDO> systemDebitedQuotaDOList = systemDebitedQuotaMapper.dynamicQuerySystemDebitedQuota(systemDebitedQuotaDO);
            if(!CollectionUtils.isEmpty(systemDebitedQuotaDOList)){
                Map<String,QuotaStateDTO> debuctQuotaMap = new HashMap<>();
                Map<String,DebuctQuotaQueryPricePlanDTO> quotaQueryPricePlanDTOMap = new HashMap<>();
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder quotaQueryPricePlanBuilder = new StringBuilder();
                for (SystemDebitedQuotaDO debitedQuotaDO : systemDebitedQuotaDOList) {
                    stringBuilder.append(debitedQuotaDO.getOrderCode()).append(":").
                            append(debitedQuotaDO.getSupplyOrderCode()).append(":").
                            append(debitedQuotaDO.getPricePlanId()).append(":").
                            append(DateUtil.dateToString(debitedQuotaDO.getSaleDate()));
                    if(debuctQuotaMap.containsKey(stringBuilder.toString())){
                        QuotaStateDTO quotaStateDTO = debuctQuotaMap.get(stringBuilder.toString());
                        if(1 == debitedQuotaDO.getQuotaType()){
                            quotaStateDTO.setQuotaNum(quotaStateDTO.getQuotaNum() + debitedQuotaDO.getQuotaNum());
                            debuctQuotaMap.put(stringBuilder.toString(),quotaStateDTO);
                        }else if(2 == debitedQuotaDO.getQuotaType()){
                            quotaStateDTO.setQuotaNum(quotaStateDTO.getQuotaNum() - debitedQuotaDO.getQuotaNum());
                            debuctQuotaMap.put(stringBuilder.toString(),quotaStateDTO);
                        }
                    }else{
                        QuotaStateDTO quotaStateDTO = new QuotaStateDTO();
                        quotaStateDTO.setSaleDate(debitedQuotaDO.getSaleDate());
                        if(1 == debitedQuotaDO.getQuotaType()){//扣配额
                            quotaStateDTO.setQuotaNum(debitedQuotaDO.getQuotaNum());
                        }else if(2 == debitedQuotaDO.getQuotaType()){//退配额
                            quotaStateDTO.setQuotaNum(0 - debitedQuotaDO.getQuotaNum());
                        }
                        debuctQuotaMap.put(stringBuilder.toString(),quotaStateDTO);
                    }
                    stringBuilder.setLength(0);
                }

                for (SystemDebitedQuotaDO debitedQuotaDO : systemDebitedQuotaDOList) {
                    stringBuilder.append(debitedQuotaDO.getOrderCode()).append(":").
                            append(debitedQuotaDO.getSupplyOrderCode()).append(":").
                            append(debitedQuotaDO.getPricePlanId()).append(":").
                            append(DateUtil.dateToString(debitedQuotaDO.getSaleDate()));
                    quotaQueryPricePlanBuilder.append(debitedQuotaDO.getOrderCode()).append(":").
                            append(debitedQuotaDO.getSupplyOrderCode()).append(":").
                            append(debitedQuotaDO.getPricePlanId());
                    if(quotaQueryPricePlanDTOMap.containsKey(quotaQueryPricePlanBuilder.toString())){
                        if(debuctQuotaMap.containsKey(stringBuilder.toString())){
                            DebuctQuotaQueryPricePlanDTO debuctQuotaQueryPricePlanDTO = quotaQueryPricePlanDTOMap.get(quotaQueryPricePlanBuilder.toString());
                            List<QuotaStateDTO> quotaStateList = debuctQuotaQueryPricePlanDTO.getQuotaStateList();
                            quotaStateList.add(debuctQuotaMap.get(stringBuilder.toString()));
                            debuctQuotaQueryPricePlanDTO.setQuotaStateList(quotaStateList);
                            quotaQueryPricePlanDTOMap.put(quotaQueryPricePlanBuilder.toString(),debuctQuotaQueryPricePlanDTO);
                            debuctQuotaMap.remove(stringBuilder.toString());
                        }
                    }else{
                        DebuctQuotaQueryPricePlanDTO debuctQuotaQueryPricePlanDTO = new DebuctQuotaQueryPricePlanDTO();
                        debuctQuotaQueryPricePlanDTO.setPricePlanId(debitedQuotaDO.getPricePlanId());
                        debuctQuotaQueryPricePlanDTO.setSupplyOrderCode(debitedQuotaDO.getSupplyOrderCode());
                        debuctQuotaQueryPricePlanDTO.setSupplyOrderId(debitedQuotaDO.getSupplyOrderId());
                        if(debuctQuotaMap.containsKey(stringBuilder.toString())){
                            List<QuotaStateDTO> quotaStateList = new ArrayList<>();
                            quotaStateList.add(debuctQuotaMap.get(stringBuilder.toString()));
                            debuctQuotaQueryPricePlanDTO.setQuotaStateList(quotaStateList);
                            debuctQuotaMap.remove(stringBuilder.toString());
                        }
                        quotaQueryPricePlanDTOMap.put(quotaQueryPricePlanBuilder.toString(),debuctQuotaQueryPricePlanDTO);
                    }
                    stringBuilder.setLength(0);
                    quotaQueryPricePlanBuilder.setLength(0);
                }
                debuctQuotaQueryResponseDTO.setDebuctQuotaQueryPricePlanDTOS(new ArrayList<>(quotaQueryPricePlanDTOMap.values()));
            }
            responseDTO.setModel(debuctQuotaQueryResponseDTO);
        } catch (Exception e) {
            log.error("queryOrderDebuctQuotaInfo has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.GET_REDIS_LOCK_FAILED.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.GET_REDIS_LOCK_FAILED.errorDesc);
        }
        return responseDTO;
    }
}
