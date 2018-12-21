package com.fangcang.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.SharedPoolLogEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.domain.QuotaAccountDO;
import com.fangcang.product.domain.SharedPoolLogDO;
import com.fangcang.product.dto.BatchQuotaStateDTO;
import com.fangcang.product.dto.DateSegmentQuotaDTO;
import com.fangcang.product.dto.NeedSharedPoolRoomTypeDTO;
import com.fangcang.product.dto.PricePlanDTO;
import com.fangcang.product.dto.SharedPoolDTO;
import com.fangcang.product.dto.SharedPoolDailyDTO;
import com.fangcang.product.dto.SharedPoolPricePlanDTO;
import com.fangcang.product.dto.SupplierDTO;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.mapper.QuotaAccountMapper;
import com.fangcang.product.mapper.QuotaStateMapper;
import com.fangcang.product.mapper.SharedPoolLogMapper;
import com.fangcang.product.request.AddProductToSharedPoolRequestDTO;
import com.fangcang.product.request.AddSharedPoolRequestDTO;
import com.fangcang.product.request.BatchSaveQuotaStateRequestDTO;
import com.fangcang.product.request.CancelSharingRequestDTO;
import com.fangcang.product.request.QuotaStateQueryDTO;
import com.fangcang.product.request.SharedPoolQueryDTO;
import com.fangcang.product.request.SharedPoolRequestDTO;
import com.fangcang.product.response.AllSharedPoolResponseDTO;
import com.fangcang.product.response.NeedSharedProductResponseDTO;
import com.fangcang.product.response.SharedPoolResponseDTO;
import com.fangcang.product.service.QuotaStateService;
import com.fangcang.product.service.SharedPoolService;
import com.fangcang.supplier.domain.SupplyDO;
import com.fangcang.supplier.mapper.SupplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ASUS on 2018/5/24.
 */
@Service
@Slf4j
public class SharedPoolServiceImpl implements SharedPoolService {

    @Autowired
    private QuotaAccountMapper quotaAccountMapper;

    @Autowired
    private QuotaStateMapper quotaStateMapper;

    @Autowired
    private PricePlanMapper pricePlanMapper;

    @Autowired
    private SupplyMapper supplyMapper;

    @Autowired
    private QuotaStateService quotaStateService;

    @Autowired
    private SharedPoolLogMapper sharedPoolLogMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO createSharedPool(AddSharedPoolRequestDTO addSharedPoolRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            List<SharedPoolDailyDTO> sharedPoolDailyList = addSharedPoolRequestDTO.getSharedPoolDailyList();
            if(!CollectionUtils.isEmpty(sharedPoolDailyList)){
                for (SharedPoolDailyDTO sharedPoolDailyDTO : sharedPoolDailyList) {
                    if(null == sharedPoolDailyDTO || null == sharedPoolDailyDTO.getStartDate()
                            || null == sharedPoolDailyDTO.getEndDate()
                            || !StringUtil.isValidString(sharedPoolDailyDTO.getWeeks())
                            || null == sharedPoolDailyDTO.getQuotaNum()
                            || null == sharedPoolDailyDTO.getOverDraft()
                            || null == sharedPoolDailyDTO.getQuotaState()){
                        responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                        responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                        responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                        return responseDTO;
                    }
                }
            }
            //新增配额账号
            QuotaAccountDO quotaAccountDO = new QuotaAccountDO();
            quotaAccountDO = PropertyCopyUtil.transfer(addSharedPoolRequestDTO,QuotaAccountDO.class);
            quotaAccountMapper.insertQuotaAccount(quotaAccountDO);
            //新增配额
            BatchSaveQuotaStateRequestDTO batchSaveQuotaStateRequestDTO = new BatchSaveQuotaStateRequestDTO();
            batchSaveQuotaStateRequestDTO.setMerchantCode(addSharedPoolRequestDTO.getMerchantCode());
            batchSaveQuotaStateRequestDTO.setCreator(addSharedPoolRequestDTO.getCreator());

            List<BatchQuotaStateDTO> quotaStateList = new ArrayList<>();
            BatchQuotaStateDTO batchQuotaStateDTO = new BatchQuotaStateDTO();
            batchQuotaStateDTO.setQuotaAccountId(quotaAccountDO.getQuotaAccountId());

            List<DateSegmentQuotaDTO> dateSegmentQuotaList = new ArrayList<>();
            DateSegmentQuotaDTO dateSegmentQuotaDTO = null;
            for (SharedPoolDailyDTO sharedPoolDailyDTO : sharedPoolDailyList) {
                dateSegmentQuotaDTO = new DateSegmentQuotaDTO();
                dateSegmentQuotaDTO.setStartDate(sharedPoolDailyDTO.getStartDate());
                dateSegmentQuotaDTO.setEndDate(sharedPoolDailyDTO.getEndDate());
                dateSegmentQuotaDTO.setWeeks(sharedPoolDailyDTO.getWeeks());
                dateSegmentQuotaDTO.setQuotaState(sharedPoolDailyDTO.getQuotaState());
                dateSegmentQuotaDTO.setOperateType(3);
                dateSegmentQuotaDTO.setQuotaNum(sharedPoolDailyDTO.getQuotaNum());
                dateSegmentQuotaDTO.setOverDraft(sharedPoolDailyDTO.getOverDraft());
                dateSegmentQuotaList.add(dateSegmentQuotaDTO);
            }
            batchQuotaStateDTO.setDateSegmentQuotaList(dateSegmentQuotaList);
            quotaStateList.add(batchQuotaStateDTO);
            batchSaveQuotaStateRequestDTO.setQuotaStateList(quotaStateList);
             ResponseDTO quotaStateResponse = quotaStateService.batchModifyQuotaState(batchSaveQuotaStateRequestDTO);
             if(null == quotaStateResponse || ResultCodeEnum.FAILURE.code == quotaStateResponse.getResult()){
                 log.error("Add quotaState has error,return result:" + JSON.toJSONString(quotaStateResponse));
                 throw new ServiceException("createSharedPool has error");
             }

            /********************************/
            //保存价格计划于配额账号的关系
            AddProductToSharedPoolRequestDTO addProductToSharedPoolRequestDTO = new AddProductToSharedPoolRequestDTO();
            addProductToSharedPoolRequestDTO.setQuotaAccountId(quotaAccountDO.getQuotaAccountId());
            addProductToSharedPoolRequestDTO.setPricePlanList(addSharedPoolRequestDTO.getPricePlanList());
            addProductToSharedPoolRequestDTO.setModifier(addSharedPoolRequestDTO.getModifier());
            this.addProductToSharedPool(addProductToSharedPoolRequestDTO);

             //记录日志
            List<SharedPoolLogDO> sharedPoolLogDOList = new ArrayList<>();
            SharedPoolLogDO sharedPoolLogDO = new SharedPoolLogDO();
            sharedPoolLogDO.setMerchantCode(addSharedPoolRequestDTO.getMerchantCode());
            sharedPoolLogDO.setOperateType(SharedPoolLogEnum.CREATE_SHARED_POOL.key);
            sharedPoolLogDO.setQuotaAccountId(quotaAccountDO.getQuotaAccountId());
            sharedPoolLogDO.setCreator(addSharedPoolRequestDTO.getCreator());
            sharedPoolLogDOList.add(sharedPoolLogDO);
            sharedPoolLogMapper.batchSaveSharedPoolLog(sharedPoolLogDOList);
        } catch (Exception e) {
            log.error("createSharedPool has error",e);
            throw new ServiceException("createSharedPool has error",e);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO renameSharedPool(SharedPoolRequestDTO sharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == sharedPoolRequestDTO || null == sharedPoolRequestDTO.getQuotaAccountId()
                || !StringUtil.isValidString(sharedPoolRequestDTO.getQuotaAccountName())){
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return  responseDTO;
        }
        try {
            QuotaAccountDO quotaAccountDO = new QuotaAccountDO();
            quotaAccountDO = PropertyCopyUtil.transfer(sharedPoolRequestDTO,QuotaAccountDO.class);
            quotaAccountMapper.updateQuotaAccount(quotaAccountDO);
        } catch (Exception e) {
            log.error("renameSharedPool has error",e);
            throw new ServiceException("renameSharedPool has error",e);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO addProductToSharedPool(AddProductToSharedPoolRequestDTO addProductToSharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<PricePlanDTO> pricePlanList = addProductToSharedPoolRequestDTO.getPricePlanList();
        if(CollectionUtils.isEmpty(pricePlanList)){
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return  responseDTO;
        }
        List<PricePlanDO> pricePlanDOList = new ArrayList<>();

        List<SharedPoolLogDO> sharedPoolLogDOList = new ArrayList<>();
        SharedPoolLogDO sharedPoolLogDO = null;
        for (PricePlanDTO pricePlanDTO : pricePlanList) {
            if(null == pricePlanDTO || null == pricePlanDTO.getPricePlanId()){
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                return  responseDTO;
            }
            PricePlanDO pricePlanDO = new PricePlanDO();
            pricePlanDO.setPricePlanId(pricePlanDTO.getPricePlanId());
            pricePlanDO.setQuotaAccountId(addProductToSharedPoolRequestDTO.getQuotaAccountId());
            pricePlanDO.setModifier(addProductToSharedPoolRequestDTO.getModifier());
            pricePlanDOList.add(pricePlanDO);

            sharedPoolLogDO = new SharedPoolLogDO();
            sharedPoolLogDO.setMerchantCode(addProductToSharedPoolRequestDTO.getMerchantCode());
            sharedPoolLogDO.setOperateType(SharedPoolLogEnum.ADD_TO_SHARED_POOL.key);
            sharedPoolLogDO.setQuotaAccountId(addProductToSharedPoolRequestDTO.getQuotaAccountId());
            sharedPoolLogDO.setCreator(addProductToSharedPoolRequestDTO.getCreator());
            sharedPoolLogDO.setPricePlanId(pricePlanDTO.getPricePlanId());
            sharedPoolLogDOList.add(sharedPoolLogDO);
        }

        try {
            if(!CollectionUtils.isEmpty(pricePlanDOList)){
                pricePlanMapper.addProductToSharedPool(pricePlanDOList);
            }

            if(!CollectionUtils.isEmpty(sharedPoolLogDOList)){
                sharedPoolLogMapper.batchSaveSharedPoolLog(sharedPoolLogDOList);
            }
        } catch (Exception e) {
            log.error("addProductToSharedPool has error",e);
            throw new ServiceException("addProductToSharedPool has error",e);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO deleteProductFromSharedPool(CancelSharingRequestDTO cancelSharingRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            PricePlanDO pricePlanDO = new PricePlanDO();
            pricePlanDO = PropertyCopyUtil.transfer(cancelSharingRequestDTO,PricePlanDO.class);
            pricePlanMapper.deleteProductFromSharedPool(pricePlanDO);

            //记录日志
            List<SharedPoolLogDO> sharedPoolLogDOList = new ArrayList<>();
            SharedPoolLogDO sharedPoolLogDO = new SharedPoolLogDO();
            sharedPoolLogDO.setMerchantCode(cancelSharingRequestDTO.getMerchantCode());
            sharedPoolLogDO.setOperateType(SharedPoolLogEnum.CANCEL_SHARED_POOL.key);
            sharedPoolLogDO.setQuotaAccountId(cancelSharingRequestDTO.getQuotaAccountId());
            sharedPoolLogDO.setPricePlanId(cancelSharingRequestDTO.getPricePlanId());
            sharedPoolLogDO.setCreator(cancelSharingRequestDTO.getCreator());
            sharedPoolLogDOList.add(sharedPoolLogDO);

            sharedPoolLogMapper.batchSaveSharedPoolLog(sharedPoolLogDOList);
        } catch (Exception e) {
            log.error("deleteProductFromSharedPool has error",e);
            throw new ServiceException("deleteProductFromSharedPool has error",e);
        }
        return responseDTO;
    }

    public ResponseDTO<NeedSharedProductResponseDTO> queryNeedSharedProduct(SharedPoolRequestDTO sharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            if(null == sharedPoolRequestDTO
                    || null == sharedPoolRequestDTO.getHotelId()
                    || !StringUtil.isValidString(sharedPoolRequestDTO.getSupplyCode())
                    || null == sharedPoolRequestDTO.getQuotaAccountId()){
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
                return  responseDTO;
            }
            PricePlanDO pricePlanDO = new PricePlanDO();
            pricePlanDO = PropertyCopyUtil.transfer(sharedPoolRequestDTO,PricePlanDO.class);
            pricePlanDO.setIsActive(1);
            List<PricePlanDO> pricePlanDOList = pricePlanMapper.queryNeedSharedProduct(pricePlanDO);
            if(!CollectionUtils.isEmpty(pricePlanDOList)){
                NeedSharedProductResponseDTO needSharedProductResponseDTO = new NeedSharedProductResponseDTO();
                Map<Long,NeedSharedPoolRoomTypeDTO> roomTypeDTOMap = new HashMap<>();
                NeedSharedPoolRoomTypeDTO needSharedPoolRoomTypeDTO = null;
                PricePlanDTO pricePlanDTO = null;
                for (PricePlanDO planDO : pricePlanDOList) {
                    pricePlanDTO = new PricePlanDTO();
                    pricePlanDTO = PropertyCopyUtil.transfer(planDO,PricePlanDTO.class);
                    if(roomTypeDTOMap.containsKey(planDO.getRoomTypeId())){
                        roomTypeDTOMap.get(planDO.getRoomTypeId()).getPricePlanList().add(pricePlanDTO);
                    }else{
                        needSharedPoolRoomTypeDTO = new NeedSharedPoolRoomTypeDTO();
                        needSharedPoolRoomTypeDTO.setRoomTypeId(planDO.getRoomTypeId());
                        needSharedPoolRoomTypeDTO.setRoomTypeName(planDO.getRoomTypeName());

                        List<PricePlanDTO> pricePlanDTOList = new ArrayList<>();
                        pricePlanDTOList.add(pricePlanDTO);
                        needSharedPoolRoomTypeDTO.setPricePlanList(pricePlanDTOList);
                        roomTypeDTOMap.put(planDO.getRoomTypeId(),needSharedPoolRoomTypeDTO);
                    }
                }
                needSharedProductResponseDTO.setRoomTypeList(new ArrayList<>(roomTypeDTOMap.values()));
                responseDTO.setModel(needSharedProductResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryNeedSharedProduct",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    public ResponseDTO<SharedPoolResponseDTO> queryCurrentSharedPool(QuotaStateQueryDTO quotaStateQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == quotaStateQueryDTO
                || null == quotaStateQueryDTO.getPricePlanId()){
            assembleResponseDTO(responseDTO,ResultCodeEnum.FAILURE.code,ErrorCodeEnum.INVALID_INPUTPARAM.errorCode,ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return  responseDTO;
        }
        try {
            PricePlanDO pricePlanDO = new PricePlanDO();
            pricePlanDO.setQuotaAccountId(quotaStateQueryDTO.getQuotaAccountId());
            pricePlanDO.setIsActive(1);
            List<PricePlanDO> pricePlanDOList = pricePlanMapper.queryCurrentSharedPool(pricePlanDO);
            if(!CollectionUtils.isEmpty(pricePlanDOList)){
                SharedPoolResponseDTO sharedPoolResponseDTO = new SharedPoolResponseDTO();
                List<SharedPoolPricePlanDTO> sharedPoolPricePlanDTOList = new ArrayList<>();
                sharedPoolPricePlanDTOList = PropertyCopyUtil.transferArray(pricePlanDOList,SharedPoolPricePlanDTO.class);
                for (SharedPoolPricePlanDTO sharedPoolPricePlanDTO : sharedPoolPricePlanDTOList) {
                    if(null != sharedPoolPricePlanDTO
                            && null != sharedPoolPricePlanDTO.getPricePlanId()
                            && quotaStateQueryDTO.getPricePlanId() == sharedPoolPricePlanDTO.getPricePlanId()){
                        sharedPoolPricePlanDTO.setIsCurrentPricePlan(1);
                    }else{
                        sharedPoolPricePlanDTO.setIsCurrentPricePlan(0);
                    }
                }
                sharedPoolResponseDTO.setQuotaAccountId(quotaStateQueryDTO.getQuotaAccountId());
                sharedPoolResponseDTO.setQuotaAccountName(pricePlanDOList.get(0).getQuotaAccountName());
                sharedPoolResponseDTO.setSharedPoolPricePlanList(sharedPoolPricePlanDTOList);
                responseDTO.setModel(sharedPoolResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryCurrentSharedPool",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    public ResponseDTO<AllSharedPoolResponseDTO> queryAllSharedPool(SharedPoolQueryDTO sharedPoolQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == sharedPoolQueryDTO || null == sharedPoolQueryDTO.getHotelId()){
            assembleResponseDTO(responseDTO,ResultCodeEnum.FAILURE.code,ErrorCodeEnum.INVALID_INPUTPARAM.errorCode,ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return  responseDTO;
        }
        try {
            PricePlanDO pp = new PricePlanDO();
            pp.setMerchantCode(sharedPoolQueryDTO.getMerchantCode());
            pp.setHotelId(sharedPoolQueryDTO.getHotelId());
            List<SupplyDO> supplyDOS = pricePlanMapper.queryHasProductSupplyList(pp);
            if(!CollectionUtils.isEmpty(supplyDOS)){
                AllSharedPoolResponseDTO allSharedPoolResponseDTO = new AllSharedPoolResponseDTO();
                allSharedPoolResponseDTO.setHotelId(sharedPoolQueryDTO.getHotelId());
                PricePlanDO pricePlanDO = new PricePlanDO();
                pricePlanDO.setHotelId(sharedPoolQueryDTO.getHotelId());
                pricePlanDO.setIsShare(1);
                //quotaAccountId
                Map<Long,List<SharedPoolPricePlanDTO>> pricePlanDOMap = new HashMap<>();
                //quotaAccountId
                Map<Long,SharedPoolDTO> quotaAccountMap = new HashMap<>();
                //supplyCode
                Map<String,Set<Long>> supplyCodeMap = new HashMap<>();
                List<PricePlanDO> pricePlanDOList = pricePlanMapper.queryCurrentSharedPool(pricePlanDO);
                if(!CollectionUtils.isEmpty(pricePlanDOList)){
                    for (PricePlanDO planDO : pricePlanDOList) {
                        if(null != planDO.getPricePlanId()){
                            SharedPoolPricePlanDTO sharedPoolPricePlanDTO = new SharedPoolPricePlanDTO();
                            sharedPoolPricePlanDTO = PropertyCopyUtil.transfer(planDO,SharedPoolPricePlanDTO.class);
                            if(pricePlanDOMap.containsKey(planDO.getQuotaAccountId())){
                                pricePlanDOMap.get(planDO.getQuotaAccountId()).add(sharedPoolPricePlanDTO);
                            }else {
                                List<SharedPoolPricePlanDTO> sharedPoolPricePlanDTOList = new ArrayList<>();
                                sharedPoolPricePlanDTOList.add(sharedPoolPricePlanDTO);
                                pricePlanDOMap.put(planDO.getQuotaAccountId(),sharedPoolPricePlanDTOList);
                            }
                        }
                        SharedPoolDTO sharedPoolDTO = new SharedPoolDTO();
                        sharedPoolDTO =  PropertyCopyUtil.transfer(planDO,SharedPoolDTO.class);
                        if(!quotaAccountMap.containsKey(planDO.getQuotaAccountId())){
                            quotaAccountMap.put(planDO.getQuotaAccountId(),sharedPoolDTO);
                        }
                        if(supplyCodeMap.containsKey(planDO.getSupplyCode())){
                            supplyCodeMap.get(planDO.getSupplyCode()).add(planDO.getQuotaAccountId());
                        }else{
                            Set<Long> quotaAccountSet = new HashSet<>();
                            quotaAccountSet.add(planDO.getQuotaAccountId());
                            supplyCodeMap.put(planDO.getSupplyCode(),quotaAccountSet);
                        }
                    }
                    //组装配额池下面的价格计划
                    for(Map.Entry<Long,SharedPoolDTO> quotaAccount : quotaAccountMap.entrySet()){
                        if(pricePlanDOMap.containsKey(quotaAccount.getKey())){
                            quotaAccount.getValue().setSharedPoolPricePlanList(pricePlanDOMap.get(quotaAccount.getKey()));
                        }
                    }
                }
                //组装供应商下面的配额池
                List<SupplierDTO> supplierDTOList = new ArrayList<>();
                for(SupplyDO supplyDO : supplyDOS){
                    SupplierDTO supplierDTO = new SupplierDTO();
                    supplierDTO.setSupplyCode(supplyDO.getSupplyCode());
                    supplierDTO.setSupplyName(supplyDO.getSupplyName());
                    if(supplyCodeMap.containsKey(supplyDO.getSupplyCode())){
                        Set<Long> quotaAccountSet = supplyCodeMap.get(supplyDO.getSupplyCode());
                        List<SharedPoolDTO> sharedPoolList = new ArrayList<>();
                        for (Long quotaAccountId : quotaAccountSet) {
                            if(quotaAccountMap.containsKey(quotaAccountId)){
                                sharedPoolList.add(quotaAccountMap.get(quotaAccountId));
                            }
                        }
                        supplierDTO.setSharedPoolList(sharedPoolList);
                    }
                    supplierDTOList.add(supplierDTO);
                }
                allSharedPoolResponseDTO.setSupplierList(supplierDTOList);
                responseDTO.setModel(allSharedPoolResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryAllSharedPool has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    private void assembleResponseDTO(ResponseDTO responseDTO,Integer code,String errorCode,String errorDesc){
        responseDTO.setResult(code);
        responseDTO.setFailCode(errorCode);
        responseDTO.setFailReason(errorDesc);
    }
}
