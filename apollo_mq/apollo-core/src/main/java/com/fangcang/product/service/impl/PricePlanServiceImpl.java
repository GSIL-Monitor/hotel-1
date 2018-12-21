package com.fangcang.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.IncrementDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.BedTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.IncrementConfig;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.URLSplitUtil;
import com.fangcang.hotelinfo.domain.HotelAdditionalDO;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.dto.BedTypeDTO;
import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.hotelinfo.response.HotelBaseInfoRsponseDTO;
import com.fangcang.hotelinfo.response.RoomTypeRsponseDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.hotelinfo.service.RoomTypeService;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.domain.PriceInfoDO;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.domain.QuotaAccountDO;
import com.fangcang.product.domain.QuotaStateDO;
import com.fangcang.product.dto.PricePlanDTO;
import com.fangcang.product.dto.PricePlanInfoDTO;
import com.fangcang.product.dto.ProductDailyDTO;
import com.fangcang.product.dto.RestrictDTO;
import com.fangcang.product.dto.RoomTypeDTO;
import com.fangcang.product.mapper.PriceInfoMapper;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.mapper.QuotaAccountMapper;
import com.fangcang.product.mapper.QuotaStateMapper;
import com.fangcang.product.request.DynamicPricePlanQueryDTO;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.request.PricePlanRequestDTO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.request.ProductHotelListQueryDTO;
import com.fangcang.product.request.ProductPageQueryDTO;
import com.fangcang.product.request.ProductRoomTypeListQueryDTO;
import com.fangcang.product.request.ToAddPricePlanQueryDTO;
import com.fangcang.product.response.HotelDetailResponseDTO;
import com.fangcang.product.response.HotelListResponseDTO;
import com.fangcang.product.response.PricePlanListResponseDTO;
import com.fangcang.product.response.PricePlanResponseDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import com.fangcang.product.response.SaleStateResponseDTO;
import com.fangcang.product.response.ToAddPricePlanResponseDTO;
import com.fangcang.product.service.IncrementService;
import com.fangcang.product.service.PriceInfoService;
import com.fangcang.product.service.PricePlanService;
import com.fangcang.product.service.RestrictService;
import com.fangcang.product.service.SaleStateService;
import com.fangcang.product.thread.IncrementThread;
import com.fangcang.product.thread.PriceInfoThread;
import com.fangcang.product.thread.QuotaStateThread;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ASUS on 2018/5/22.
 */
@Service
@Slf4j
public class PricePlanServiceImpl implements PricePlanService{

    @Autowired
    private PricePlanMapper pricePlanMapper;

    @Autowired
    private QuotaAccountMapper quotaAccountMapper;

    @Autowired
    private PriceInfoService priceInfoService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private HotelInfoService hotelInfoService;

    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private PriceInfoMapper priceInfoMapper;

    @Autowired
    private QuotaStateMapper quotaStateMapper;

    @Resource(name = "incrementExecutor")
    private ThreadPoolTaskExecutor incrementExecutor;

    @Autowired
    private IncrementConfig incrementConfig;

    @Autowired
    private IncrementService incrementService;

    @Autowired
    private RestrictService restrictService;

    @Autowired
    private SaleStateService saleStateService;

    public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> queryHotelList(ProductHotelListQueryDTO queryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            PageHelper.startPage(queryDTO.getCurrentPage(),queryDTO.getPageSize());
            List<HotelDO> hotelDOS = pricePlanMapper.queryHotelList(queryDTO);
            PageInfo<HotelDO> pageInfo = new PageInfo<HotelDO>(hotelDOS);
            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setPageSize(pageInfo.getPageSize());
            List<HotelDO> itemList = pageInfo.getList();
            if(!CollectionUtils.isEmpty(itemList)){
                List<HotelListResponseDTO> hotelListResponseDTOList = new ArrayList<>();
                HotelListResponseDTO hotelListResponseDTO = null;
                ProductRoomTypeListQueryDTO productRoomTypeListQueryDTO = null;
                for (HotelDO hotelDO : itemList) {
                    hotelListResponseDTO = PropertyCopyUtil.transfer(hotelDO,HotelListResponseDTO.class);
                    if(queryDTO.getHasQueryProduct()){
                        productRoomTypeListQueryDTO = new ProductRoomTypeListQueryDTO();
                        productRoomTypeListQueryDTO.setHotelId(hotelDO.getHotelId());
                        productRoomTypeListQueryDTO.setStartDate(queryDTO.getStartDate());
                        productRoomTypeListQueryDTO.setEndDate(queryDTO.getEndDate());
                        productRoomTypeListQueryDTO.setMerchantCode(queryDTO.getMerchantCode());
                        ResponseDTO<HotelDetailResponseDTO> response = this.queryHotelInfo(productRoomTypeListQueryDTO);
                        if(null != response && ResultCodeEnum.SUCCESS.code == response.getResult() ){
                            HotelDetailResponseDTO hotelDetailResponseDTO = response.getModel();
                            if(null != hotelDetailResponseDTO){
                                hotelListResponseDTO.setRoomTypeList(hotelDetailResponseDTO.getRoomTypeList());
                            }
                        }
                    }
                    hotelListResponseDTOList.add(hotelListResponseDTO);
                }
                paginationSupportDTO.setItemList(hotelListResponseDTOList);
            }
            responseDTO.setModel(paginationSupportDTO);
        } catch (Exception e) {
            log.error("queryHotelList",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO saveOrUpdatePricePlan(PricePlanRequestDTO pricePlanRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {

            if(null == pricePlanRequestDTO.getPricePlanId()){
                /**
                 * 1.新增配额账号
                 * 2.新增价格计划
                 */
                QuotaAccountDO quotaAccountDO = new QuotaAccountDO();
                quotaAccountDO.setHotelId(pricePlanRequestDTO.getHotelId());
                quotaAccountDO.setIsShare(0);//1 共享  ,0 不共享
                quotaAccountDO.setQuotaAccountName(pricePlanRequestDTO.getPricePlanName());
                quotaAccountDO.setSupplyCode(pricePlanRequestDTO.getSupplyCode());
                quotaAccountDO.setCreator(pricePlanRequestDTO.getCreator());
                //新增配额账号
                quotaAccountMapper.insertQuotaAccount(quotaAccountDO);
                //新增价格计划
                PricePlanDO pricePlanDO = new PricePlanDO();
                pricePlanDO = PropertyCopyUtil.transfer(pricePlanRequestDTO,PricePlanDO.class);
                pricePlanDO.setQuotaAccountId(quotaAccountDO.getQuotaAccountId());
                pricePlanDO.setIsActive(1);
                String[] freeRoomPolicy = pricePlanRequestDTO.getFreeRoomPolicy();
                String freeRoomPolicyStr = getFreeRoomPolicyStr(pricePlanRequestDTO.getFreeRoomPolicy());
                pricePlanDO.setFreeRoomPolicyStr(freeRoomPolicyStr);
                pricePlanMapper.insertPricePlan(pricePlanDO);
                pricePlanRequestDTO.setPricePlanId(pricePlanDO.getPricePlanId());
            }else{
                PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
                pricePlanQueryDTO.setPricePlanId(pricePlanRequestDTO.getPricePlanId());
                ResponseDTO<PricePlanResponseDTO> response = this.queryPricePlanInfo(pricePlanQueryDTO);
                if(null == response || ResultCodeEnum.FAILURE.code == response.getResult() || null == response.getModel()){
                    log.error("Query priceplan info has error.");
                    throw new ServiceException("Query priceplan info has error.");
                }
                //修改价格计划
                PricePlanDO pricePlanDO = new PricePlanDO();
                pricePlanDO = PropertyCopyUtil.transfer(pricePlanRequestDTO,PricePlanDO.class);
                String[] freeRoomPolicy = pricePlanRequestDTO.getFreeRoomPolicy();
                String freeRoomPolicyStr = getFreeRoomPolicyStr(pricePlanRequestDTO.getFreeRoomPolicy());
                pricePlanDO.setFreeRoomPolicyStr(freeRoomPolicyStr);
                pricePlanMapper.updatePricePlan(pricePlanDO);
                //早餐类型修改推送增量
                PricePlanResponseDTO pricePlanResponseDTO  = response.getModel();
                if(null != pricePlanResponseDTO
                        && null != pricePlanRequestDTO
                        && null != pricePlanResponseDTO.getBreakFastType()
                        && null != pricePlanRequestDTO.getBreakFastType()
                        && pricePlanRequestDTO.getBreakFastType().compareTo(pricePlanResponseDTO.getBreakFastType()) != 0){
                    IncrementDTO incrementDTO = new IncrementDTO();
                    incrementDTO.setMerchantCode(pricePlanRequestDTO.getMerchantCode());
                    incrementDTO.setMHotelId(pricePlanResponseDTO.getHotelId());
                    incrementDTO.setMRoomTypeId(pricePlanResponseDTO.getRoomTypeId());
                    incrementDTO.setMRatePlanId(pricePlanResponseDTO.getPricePlanId());
                    List<IncrementDTO> incrementDTOList = new ArrayList<>();
                    incrementDTOList.add(incrementDTO);

                    String url = URLSplitUtil.getUrl(incrementConfig);
                    IncrementThread incrementThread = new IncrementThread(incrementDTOList,url,incrementService);
                    incrementExecutor.execute(incrementThread);
                }
            }

            //保存条款
            RestrictDTO restrictDTO = PropertyCopyUtil.transfer(pricePlanRequestDTO,RestrictDTO.class);
            restrictDTO.setRatePlanId(pricePlanRequestDTO.getPricePlanId());
            restrictService.saveRestrictByRatePlanId(restrictDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("saveOrUpdatePricePlan has error",e);
            throw new ServiceException("saveOrUpdatePricePlan has error",e);
        }
        return responseDTO;
    }

    private String getFreeRoomPolicyStr(String[] freeRoomPolicy){
        if(null != freeRoomPolicy && freeRoomPolicy.length > 0){
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : freeRoomPolicy) {
                stringBuilder.append(str).append(",");
            }
            return stringBuilder.toString().substring(0,stringBuilder.toString().length() - 1);
        }
        return null;
    }


    public ResponseDTO<PricePlanResponseDTO> queryPricePlanInfo(PricePlanQueryDTO pricePlanQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO = new DynamicPricePlanQueryDTO();
            dynamicPricePlanQueryDTO.setPricePlanId(pricePlanQueryDTO.getPricePlanId());
            List<PricePlanDO> pricePlanDOList = pricePlanMapper.dynamicQueryPricePlanList(dynamicPricePlanQueryDTO);

            if(!CollectionUtils.isEmpty(pricePlanDOList)){

                PricePlanDO pricePlanDO = pricePlanDOList.get(0);
                PricePlanResponseDTO pricePlanResponseDTO = new PricePlanResponseDTO();
                pricePlanResponseDTO = PropertyCopyUtil.transfer(pricePlanDO,PricePlanResponseDTO.class);
                if(StringUtil.isValidString(pricePlanDO.getFreeRoomPolicyStr())){
                    String[] freeRoomPolicy = pricePlanDO.getFreeRoomPolicyStr().split(",");
                    pricePlanResponseDTO.setFreeRoomPolicy(freeRoomPolicy);
                }
                List<HotelAdditionalDO> hotelAdditionalList = pricePlanDO.getHotelAdditionalList();
                StringBuilder stringBuilder = new StringBuilder();
                if(!CollectionUtils.isEmpty(hotelAdditionalList)){
                    for (HotelAdditionalDO hotelAdditionalDO : hotelAdditionalList) {
                        stringBuilder.append(hotelAdditionalDO.getAdditionalName())
                                .append(hotelAdditionalDO.getAdditionalPrice()).append("/份").append(",");
                    }
                    pricePlanResponseDTO.setAdditionalStr(stringBuilder.toString().substring(0,stringBuilder.length() - 1));
                }

                //条款
                RestrictDTO restrictQueryDTO = new RestrictDTO();
                restrictQueryDTO.setRatePlanId(pricePlanQueryDTO.getPricePlanId());
                RestrictDTO restrictDTO = restrictService.queryRestrictByRatePlanId(restrictQueryDTO);
                if (null != responseDTO){
                    pricePlanResponseDTO.setAdvanceBookingDays(restrictDTO.getAdvanceBookingDays());
                    pricePlanResponseDTO.setAdvanceBookingHours(restrictDTO.getAdvanceBookingHours());
                    pricePlanResponseDTO.setCancelType(restrictDTO.getCancelType());
                    pricePlanResponseDTO.setCancelDays(restrictDTO.getCancelDays());
                    pricePlanResponseDTO.setCancelHours(restrictDTO.getCancelHours());
                    pricePlanResponseDTO.setNumberOfBooking(restrictDTO.getNumberOfBooking());
                    pricePlanResponseDTO.setOccupancyOfDays(restrictDTO.getOccupancyOfDays());
                    pricePlanResponseDTO.setLastConfirmDays(restrictDTO.getLastConfirmDays());
                    pricePlanResponseDTO.setLastConfirmHours(restrictDTO.getLastConfirmHours());
                }
                responseDTO.setModel(pricePlanResponseDTO);
            }
            return responseDTO;
        } catch (Exception e) {
            log.error("queryPricePlanInfo error,{}", JSON.toJSONString(pricePlanQueryDTO),e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO deletePricePlan(PricePlanDO pricePlanDO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
            pricePlanQueryDTO.setPricePlanId(pricePlanDO.getPricePlanId());
            ResponseDTO<PricePlanResponseDTO> response = this.queryPricePlanInfo(pricePlanQueryDTO);
            if(null == response || ResultCodeEnum.FAILURE.code == response.getResult() || null == response.getModel()){
                log.error("Query priceplan info has error.");
                throw new ServiceException("Query priceplan info has error.");
            }
            pricePlanMapper.updatePricePlan(pricePlanDO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);

            //删除条款
            restrictService.deleteRestrictByRatePlanId(pricePlanDO.getPricePlanId());

            //推送增量
            PricePlanResponseDTO pricePlanResponseDTO = response.getModel();
            IncrementDTO incrementDTO = new IncrementDTO();
            incrementDTO.setMerchantCode(pricePlanDO.getMerchantCode());
            incrementDTO.setMHotelId(pricePlanResponseDTO.getHotelId());
            incrementDTO.setMRoomTypeId(pricePlanResponseDTO.getRoomTypeId());
            incrementDTO.setMRatePlanId(pricePlanResponseDTO.getPricePlanId());
            List<IncrementDTO> incrementDTOList = new ArrayList<>();
            incrementDTOList.add(incrementDTO);

            String url = URLSplitUtil.getUrl(incrementConfig);
            IncrementThread incrementThread = new IncrementThread(incrementDTOList,url,incrementService);
            incrementExecutor.execute(incrementThread);
        } catch (Exception e) {
            log.error("deletePricePlan has error",e);
            throw new ServiceException("deletePricePlan has error",e);
        }
        return responseDTO;
    }

    public ResponseDTO<PricePlanListResponseDTO> dynamicPricePlanList(DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            List<PricePlanDO> pricePlanDOList = pricePlanMapper.dynamicQueryPricePlanList(dynamicPricePlanQueryDTO);
            if(!CollectionUtils.isEmpty(pricePlanDOList)){
                PricePlanListResponseDTO pricePlanListResponseDTO = new PricePlanListResponseDTO();
                List<PricePlanDTO> pricePlanDTOList = new ArrayList<>();
                pricePlanDTOList = PropertyCopyUtil.transferArray(pricePlanDOList,PricePlanDTO.class);
                pricePlanListResponseDTO.setPricePlanList(pricePlanDTOList);
                responseDTO.setModel(pricePlanListResponseDTO);
            }
        } catch (Exception e) {
            log.error("dynamicPricePlanList",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    public ResponseDTO<HotelDetailResponseDTO> queryHotelInfo(ProductRoomTypeListQueryDTO queryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            HotelDetailResponseDTO hotelDetailResponseDTO = new HotelDetailResponseDTO();
            hotelDetailResponseDTO.setHotelId(queryDTO.getHotelId());
            //1.根据酒店ID查询所有房型
            RoomTypeQueryDTO roomTypeQueryDTO = new RoomTypeQueryDTO();
            roomTypeQueryDTO.setHotelId(queryDTO.getHotelId());
            ResponseDTO<RoomTypeRsponseDTO> roomTypeRsponseDTOResponseDTO = roomTypeService.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);
            if(null != roomTypeRsponseDTOResponseDTO
                    && ResultCodeEnum.SUCCESS.code == roomTypeRsponseDTOResponseDTO.getResult()
                    && null != roomTypeRsponseDTOResponseDTO.getModel()){
                RoomTypeRsponseDTO roomTypeRsponseDTO = roomTypeRsponseDTOResponseDTO.getModel();
                List<com.fangcang.hotelinfo.dto.RoomTypeDTO> roomTypeList = roomTypeRsponseDTO.getRoomTypeList();
                hotelDetailResponseDTO.setHotelName(roomTypeList.get(0).getHotelName());
                if(!CollectionUtils.isEmpty(roomTypeList)){
                    //2.查询所有产品信息
                    ProductDailyInfoQueryDTO productDailyInfoQueryDTO = null;
                    productDailyInfoQueryDTO = PropertyCopyUtil.transfer(queryDTO,ProductDailyInfoQueryDTO.class);
                    productDailyInfoQueryDTO.setIsActive(1);
                    ResponseDTO<List<ProductDailyInfoResponseDTO>> productDailyInfoResponse = priceInfoService.queryProductDailyInfo(productDailyInfoQueryDTO);
                    List<ProductDailyInfoResponseDTO> productDailyInfoResponseDTOList = productDailyInfoResponse.getModel();
                    //roomTypeId
                    Map<Long,RoomTypeDTO> roomTypeDTOMap = new HashMap<>();
                    if(null != productDailyInfoResponse
                            && ResultCodeEnum.SUCCESS.code == productDailyInfoResponse.getResult()
                            && !CollectionUtils.isEmpty(productDailyInfoResponseDTOList)){
                        ProductDailyInfoResponseDTO productDailyInfoResponseDTO = productDailyInfoResponseDTOList.get(0);

                        PricePlanInfoDTO pricePlanInfoDTO = null;
                        for (ProductDailyInfoResponseDTO p : productDailyInfoResponseDTOList) {
                            pricePlanInfoDTO = PropertyCopyUtil.transfer(p,PricePlanInfoDTO.class);
                            if(roomTypeDTOMap.containsKey(p.getRoomTypeId())){
                                roomTypeDTOMap.get(p.getRoomTypeId()).getPricePlanList().add(pricePlanInfoDTO);
                                if (pricePlanInfoDTO.getHasPrice()==1){
                                    roomTypeDTOMap.get(p.getRoomTypeId()).setHasPrice(1);
                                }
                            }else{
                                RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
                                roomTypeDTO.setRoomTypeId(p.getRoomTypeId());
                                roomTypeDTO.setRoomTypeName(p.getRoomTypeName());
                                if (pricePlanInfoDTO.getHasPrice()==1){
                                    roomTypeDTO.setHasPrice(1);
                                }
                                List<PricePlanInfoDTO> pricePlanInfoDTOList = new ArrayList<>();
                                pricePlanInfoDTOList.add(pricePlanInfoDTO);
                                roomTypeDTO.setPricePlanList(pricePlanInfoDTOList);
                                roomTypeDTOMap.put(p.getRoomTypeId(),roomTypeDTO);
                            }
                            //组装价格计划上下架情况
                            MerchantSaleChannelDO merchantSaleChannelDO = new MerchantSaleChannelDO();
                            merchantSaleChannelDO.setMerchantCode(queryDTO.getMerchantCode());
                            PricePlanQueryDTO pricePlanQueryDTO=new PricePlanQueryDTO();
                            pricePlanQueryDTO.setPricePlanId(pricePlanInfoDTO.getPricePlanId());
                            ResponseDTO<SaleStateResponseDTO> saleStateResponseDTO=saleStateService.pricePlanIsOnSale(pricePlanQueryDTO,merchantSaleChannelDO);
                            pricePlanInfoDTO.setSaleStateResponseDTO(saleStateResponseDTO.getModel());
                        }
                    }
                    List<RoomTypeDTO> roomTypeDTOList = new ArrayList<>();
                    for (com.fangcang.hotelinfo.dto.RoomTypeDTO roomTypeDTO : roomTypeList) {
                        if(roomTypeDTOMap.containsKey(roomTypeDTO.getRoomTypeId())){
                            roomTypeDTOList.add(roomTypeDTOMap.get(roomTypeDTO.getRoomTypeId()));
                            Collections.sort(roomTypeDTOMap.get(roomTypeDTO.getRoomTypeId()).getPricePlanList(),new Comparator<PricePlanInfoDTO>() {
                                @Override
                                public int compare(PricePlanInfoDTO item1, PricePlanInfoDTO item2) {
                                    // 返回值为int类型，大于0表示正序，小于0表示逆序
                                    return item2.getHasPrice()-item1.getHasPrice();
                                }
                            });
                        }else{
                            RoomTypeDTO noProductRoomType = new RoomTypeDTO();
                            noProductRoomType.setRoomTypeId(roomTypeDTO.getRoomTypeId());
                            noProductRoomType.setRoomTypeName(roomTypeDTO.getRoomTypeName());
                            roomTypeDTOList.add(noProductRoomType);
                        }
                    }
                    Collections.sort(roomTypeDTOList,new Comparator<RoomTypeDTO>() {
                        @Override
                        public int compare(RoomTypeDTO item1, RoomTypeDTO item2) {
                            // 返回值为int类型，大于0表示正序，小于0表示逆序
                            return item2.getHasPrice()-item1.getHasPrice();
                        }
                    });
                    hotelDetailResponseDTO.setRoomTypeList(roomTypeDTOList);
                }
            }else{
                responseDTO.setResult(roomTypeRsponseDTOResponseDTO.getResult());
                responseDTO.setFailCode(roomTypeRsponseDTOResponseDTO.getFailCode());
                responseDTO.setFailReason(roomTypeRsponseDTOResponseDTO.getFailReason());
            }
            responseDTO.setModel(hotelDetailResponseDTO);
        } catch (Exception e) {
            log.error("queryHotelInfo has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    public ResponseDTO<PaginationSupportDTO<ProductDailyInfoResponseDTO>> queryProductInfoByPage(ProductPageQueryDTO productPageQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == productPageQueryDTO
                || null == productPageQueryDTO.getStartDate()
                || null == productPageQueryDTO.getEndDate()){
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return responseDTO;
        }
        try {
            //1.分页查询出价格计划列表
            DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO = new DynamicPricePlanQueryDTO();
            dynamicPricePlanQueryDTO = PropertyCopyUtil.transfer(productPageQueryDTO,DynamicPricePlanQueryDTO.class);
            dynamicPricePlanQueryDTO.setIsActive(1);
            PageHelper.startPage(productPageQueryDTO.getCurrentPage(),productPageQueryDTO.getPageSize());
            List<PricePlanDO> pricePlanDOList = pricePlanMapper.dynamicQueryPricePlanList(dynamicPricePlanQueryDTO);
            PageInfo<PricePlanDO> pageInfo = new PageInfo<>(pricePlanDOList);
            List<PricePlanDO> pricePlanDOList1 = pageInfo.getList();
            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
            paginationSupportDTO.setPageSize(pageInfo.getPageSize());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            if(!CollectionUtils.isEmpty(pricePlanDOList1)){
                StringBuilder pricePlanIdstr = new StringBuilder();
                for (PricePlanDO pricePlanDO : pricePlanDOList1) {
                    pricePlanIdstr.append(pricePlanDO.getPricePlanId()).append(",");
                }
                ProductDailyInfoQueryDTO productDailyInfoQueryDTO = new ProductDailyInfoQueryDTO();
                productDailyInfoQueryDTO.setStartDate(productPageQueryDTO.getStartDate());
                productDailyInfoQueryDTO.setEndDate(productPageQueryDTO.getEndDate());
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
                //组装结果
                List<ProductDailyInfoResponseDTO> productDailyInfoResponseDTOS = new ArrayList<>();
                ProductDailyInfoResponseDTO productDailyInfoResponseDTO = null;
                ProductDailyDTO productDailyDTO = null;
                StringBuilder key = new StringBuilder();
                for(PricePlanDO pricePlanDO : pricePlanDOList){
                    productDailyInfoResponseDTO = PropertyCopyUtil.transfer(pricePlanDO,ProductDailyInfoResponseDTO.class);
                    if(StringUtil.isValidString(pricePlanDO.getFreeRoomPolicyStr())){
                        String[] freeRoomPolicy = pricePlanDO.getFreeRoomPolicyStr().split(",");
                        productDailyInfoResponseDTO.setFreeRoomPolicy(freeRoomPolicy);
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
                        }
                        //组装配额房态对象
                        if(quotaStateDOMap.containsKey(key.toString())){
                            QuotaStateDO quotaStateDO = quotaStateDOMap.get(key.toString());
                            productDailyDTO.setQuotaNum(quotaStateDO.getQuotaNum());
                            productDailyDTO.setAllQuotaNum(quotaStateDO.getAllQuotaNum());
                            productDailyDTO.setOverDraft(quotaStateDO.getOverDraft());
                            productDailyDTO.setQuotaState(quotaStateDO.getQuotaState());
                        }
                        key.setLength(0);
                        productDailyList.add(productDailyDTO);
                    }
                    productDailyInfoResponseDTO.setProductDailyList(productDailyList);
                    productDailyInfoResponseDTOS.add(productDailyInfoResponseDTO);
                }
                paginationSupportDTO.setItemList(productDailyInfoResponseDTOS);
            }
            responseDTO.setModel(paginationSupportDTO);
        } catch (Exception e) {
            log.error("queryProductInfoByPage has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    public ResponseDTO<ToAddPricePlanResponseDTO> toAddPricePlan(ToAddPricePlanQueryDTO queryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO = new HotelBaseInfoRequestDTO();
            hotelBaseInfoRequestDTO.setHotelId(queryDTO.getHotelId());
            hotelBaseInfoRequestDTO.setMerchantCode(queryDTO.getMerchantCode());
            ResponseDTO<HotelBaseInfoRsponseDTO> hotelBaseInfoRsponseDTO = hotelInfoService.queryHotelInfoByHotelId(hotelBaseInfoRequestDTO);
            if(null != hotelBaseInfoRsponseDTO && ResultCodeEnum.SUCCESS.code == hotelBaseInfoRsponseDTO.getResult()){
                 HotelBaseInfoRsponseDTO hotelBaseInfoDTO = hotelBaseInfoRsponseDTO.getModel();
                 if(null != hotelBaseInfoDTO){
                     ToAddPricePlanResponseDTO addPricePlanResponseDTO = new ToAddPricePlanResponseDTO();
                     addPricePlanResponseDTO.setHotelId(hotelBaseInfoDTO.getHotelId());
                     addPricePlanResponseDTO.setHotelName(hotelBaseInfoDTO.getHotelName());
                     addPricePlanResponseDTO.setCancelPolicy(hotelBaseInfoDTO.getCancelPolicy());
                     addPricePlanResponseDTO.setFreeRoomPolicy(hotelBaseInfoDTO.getFreeRoomPolicy());
                     List<com.fangcang.hotelinfo.dto.RoomTypeDTO> roomTypeList = hotelBaseInfoDTO.getRoomTypeList();
                     if(!CollectionUtils.isEmpty(roomTypeList)){
                         LinkedList<RoomTypeDTO> roomTypeDTOList = new LinkedList<>();
                         RoomTypeDTO roomTypeDTO = null;
                         for (com.fangcang.hotelinfo.dto.RoomTypeDTO roomType : roomTypeList) {
                             roomTypeDTO = new RoomTypeDTO();
                             roomTypeDTO.setRoomTypeId(roomType.getRoomTypeId());
                             roomTypeDTO.setRoomTypeName(roomType.getRoomTypeName());
                             List<BedTypeDTO> bedTypelist = roomType.getBedTypeList();
                             if(!CollectionUtils.isEmpty(bedTypelist)){
                                 List<com.fangcang.product.dto.BedTypeDTO> bedTypeDTOList = new ArrayList<>();
                                 com.fangcang.product.dto.BedTypeDTO bedTypeDTO = null;
                                 for (BedTypeDTO bedType : bedTypelist) {
                                     if(null != bedType && null != bedType.getBedType()){
                                         bedTypeDTO = new com.fangcang.product.dto.BedTypeDTO();
                                         bedTypeDTO.setBedTypeId(Long.valueOf(bedType.getBedType()));
                                         bedTypeDTO.setBedTypeName(BedTypeEnum.getValueByKey(bedType.getBedType()));
                                         bedTypeDTOList.add(bedTypeDTO);
                                     }
                                 }
                                 roomTypeDTO.setBedTypeList(bedTypeDTOList);
                             }
                             if(null != queryDTO.getRoomTypeId()
                                     && roomType.getRoomTypeId().compareTo(queryDTO.getRoomTypeId()) == 0 ){
                                 roomTypeDTOList.addFirst(roomTypeDTO);
                             }else{
                                 roomTypeDTOList.addLast(roomTypeDTO);
                             }
                         }
                         addPricePlanResponseDTO.setRoomTypeList(roomTypeDTOList);
                     }
                     List<HotelAdditionalDTO> additionalList = hotelBaseInfoDTO.getAdditionalList();
                     if(!CollectionUtils.isEmpty(additionalList)){
                         StringBuilder stringBuilder = new StringBuilder();
                         for (HotelAdditionalDTO hotelAdditionalDTO : additionalList) {
                             stringBuilder.append(hotelAdditionalDTO.getAdditionalName())
                                     .append(hotelAdditionalDTO.getAdditionalPrice()).append("/份").append(",");
                         }
                         addPricePlanResponseDTO.setAdditionalStr(stringBuilder.toString().substring(0,stringBuilder.toString().length() - 1));
                     }
                     responseDTO.setModel(addPricePlanResponseDTO);
                 }
            }
        } catch (Exception e) {
            log.error("toAddPricePlan has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

}
