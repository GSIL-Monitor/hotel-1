package com.fangcang.b2b.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.agent.dto.AgentUserDTO;
import com.fangcang.b2b.dto.HotelSimpleDTO;
import com.fangcang.b2b.dto.ProductDetailsDTO;
import com.fangcang.b2b.dto.RoomTypeDetailsDTO;
import com.fangcang.b2b.request.GetHotelDetailRequestDTO;
import com.fangcang.b2b.request.QueryHotelListRequestDTO;
import com.fangcang.b2b.request.QueryMerchantAllCityDTO;
import com.fangcang.b2b.response.GetHotelDetailResponseDTO;
import com.fangcang.b2b.response.HotelBaseInfoWithImagesResponseDTO;
import com.fangcang.b2b.response.QueryHotelListRsponseDTO;
import com.fangcang.b2b.response.QueryMerchantCommonCityResponseDTO;
import com.fangcang.b2b.service.HotelSearchService;
import com.fangcang.b2b.thread.HotelBasicInfoThread;
import com.fangcang.b2b.thread.HotelListThread;
import com.fangcang.base.dto.CommonCityDTO;
import com.fangcang.base.mapper.CommonCityMapper;
import com.fangcang.base.service.CommonCityService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ProductTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import com.fangcang.hotelinfo.mapper.HotelMapper;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.hotelinfo.response.RoomTypeRsponseDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.hotelinfo.service.ImageService;
import com.fangcang.hotelinfo.service.RoomTypeService;
import com.fangcang.product.mapper.PriceInfoMapper;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import com.fangcang.product.service.PriceInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * Created by ASUS on 2018/7/2.
 */
@Slf4j
@Service
public class HotelSearchServiceImpl implements HotelSearchService {

    @Autowired
    private PricePlanMapper pricePlanMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private PriceInfoMapper priceInfoMapper;

    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private PriceInfoService priceInfoService;

    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommonCityService commonCityService;

    @Autowired
    private CommonCityMapper commonCityMapper;


    public ResponseDTO<PaginationSupportDTO<QueryHotelListRsponseDTO>> queryHotelList(QueryHotelListRequestDTO queryHotelListRequestDTO, AgentUserDTO agentUserDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            /**
             * 1.分页查酒店
             * 2.再根据酒店查产品  根据价格范围筛选价格计划  再计算起价，
             * 3.再根据起价排序
             */
            HotelListQueryDTO hotelListQueryDTO = PropertyCopyUtil.transfer(queryHotelListRequestDTO, HotelListQueryDTO.class);
            hotelListQueryDTO.setIsActive(1);
            PageHelper.startPage(queryHotelListRequestDTO.getCurrentPage(), queryHotelListRequestDTO.getPageSize());
            List<HotelDO> hotelList = hotelMapper.getHotelList(hotelListQueryDTO);
            PageInfo<HotelDO> pageInfo = new PageInfo<>(hotelList);
            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
            paginationSupportDTO.setPageSize(pageInfo.getPageSize());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            List<QueryHotelListRsponseDTO> itemList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(hotelList)) {
                QueryHotelListRsponseDTO queryHotelListRsponseDTO = null;
                ProductDailyInfoQueryDTO productDailyInfoQueryDTO = null;
                CountDownLatch countDownLatch = new CountDownLatch(hotelList.size());
                for (HotelDO hotelDO : hotelList) {
                    queryHotelListRsponseDTO = new QueryHotelListRsponseDTO();
                    queryHotelListRsponseDTO.setHotelId(hotelDO.getHotelId());
                    queryHotelListRsponseDTO.setChineseName(hotelDO.getHotelName());
                    queryHotelListRsponseDTO.setEnglishName(hotelDO.getEngHotelName());
                    queryHotelListRsponseDTO.setChineseAddress(hotelDO.getHotelAddress());
                    queryHotelListRsponseDTO.setEnglishAddress(hotelDO.getEngHotelAddress());
                    queryHotelListRsponseDTO.setHotelStar(hotelDO.getHotelStar());
                    queryHotelListRsponseDTO.setShowCurrency(agentUserDTO.getFinanceCurrency());
                    if (null != hotelDO.getImagedo()) {
                        queryHotelListRsponseDTO.setImgUrl(hotelDO.getImagedo().getImageUrl());
                    }
                    HotelListThread hotelListThread = new HotelListThread(countDownLatch, queryHotelListRequestDTO, queryHotelListRsponseDTO, hotelDO, priceInfoMapper);
                    threadPoolTaskExecutor.execute(hotelListThread);
                    itemList.add(queryHotelListRsponseDTO);
                }
                countDownLatch.await();
                //排序
                Comparator<QueryHotelListRsponseDTO> comparator = (QueryHotelListRsponseDTO response1, QueryHotelListRsponseDTO response2) -> {
                    if (null == response1.getStartPrice() && null == response2.getStartPrice()) {
                        return 0;//排在上面
                    } else if (null == response1.getStartPrice() && null != response2.getStartPrice()) {
                        return 1;//排在下面
                    } else if (null != response1.getStartPrice() && null == response2.getStartPrice()) {
                        return -1;//排在上面
                    } else if (null != response1.getStartPrice() && null != response2.getStartPrice()) {
                        return response1.getStartPrice().compareTo(response2.getStartPrice());
                    }
                    return 0;
                };
                Collections.sort(itemList, comparator);
            }
            paginationSupportDTO.setItemList(itemList);
            responseDTO.setModel(paginationSupportDTO);
        } catch (Exception e) {
            log.error("queryHotelList has error:" + JSON.toJSONString(queryHotelListRequestDTO), e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 酒店详情
     *
     * @param getHotelDetailRequestDTO
     * @param agentUserDTO
     * @return
     */
    public ResponseDTO<GetHotelDetailResponseDTO> getHotelDetail(GetHotelDetailRequestDTO getHotelDetailRequestDTO, AgentUserDTO agentUserDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            GetHotelDetailResponseDTO getHotelDetailResponseDTO = new GetHotelDetailResponseDTO();
            getHotelDetailResponseDTO.setHotelId(getHotelDetailRequestDTO.getHotelId());
            //1.根据酒店ID查询所有房型
            RoomTypeQueryDTO roomTypeQueryDTO = new RoomTypeQueryDTO();
            roomTypeQueryDTO.setHotelId(getHotelDetailRequestDTO.getHotelId());
            ResponseDTO<RoomTypeRsponseDTO> roomTypeRsponseDTOResponseDTO = roomTypeService.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);
            if (null != roomTypeRsponseDTOResponseDTO
                    && ResultCodeEnum.SUCCESS.code == roomTypeRsponseDTOResponseDTO.getResult()
                    && null != roomTypeRsponseDTOResponseDTO.getModel()) {
                RoomTypeRsponseDTO roomTypeRsponseDTO = roomTypeRsponseDTOResponseDTO.getModel();
                List<RoomTypeDTO> roomTypeList = roomTypeRsponseDTO.getRoomTypeList();
                if (!CollectionUtils.isEmpty(roomTypeList)) {
                    //2.查询所有产品信息
                    ProductDailyInfoQueryDTO productDailyInfoQueryDTO = new ProductDailyInfoQueryDTO();
                    productDailyInfoQueryDTO.setHotelId(getHotelDetailRequestDTO.getHotelId());
                    productDailyInfoQueryDTO.setStartDate(getHotelDetailRequestDTO.getCheckInDate());
                    productDailyInfoQueryDTO.setEndDate(DateUtil.getDate(getHotelDetailRequestDTO.getCheckOutDate(),-1,0));
                    productDailyInfoQueryDTO.setMerchantCode(agentUserDTO.getMerchantCode());
                    productDailyInfoQueryDTO.setIsActive(1);
                    productDailyInfoQueryDTO.setAgentCode(agentUserDTO.getAgentCode());
                    productDailyInfoQueryDTO.setChannelCode(ChannelTypeEnum.B2B.key);
                    if (String.valueOf(ProductTypeEnum.SCATTERED_ROOM.key).equals(getHotelDetailRequestDTO.getProductType())) {
                        //散房查散房  团房查所有
                        productDailyInfoQueryDTO.setProductType(ProductTypeEnum.SCATTERED_ROOM.key);
                    }
                    //只查上架产品
                    productDailyInfoQueryDTO.setSaleState(1);
                    productDailyInfoQueryDTO.setOrderType(Integer.valueOf(getHotelDetailRequestDTO.getProductType()));
                    ResponseDTO<List<ProductDailyInfoResponseDTO>> productDailyInfoResponse = priceInfoService.queryProductDailyInfo(productDailyInfoQueryDTO);
                    List<ProductDailyInfoResponseDTO> productDailyInfoResponseDTOList = productDailyInfoResponse.getModel();
                    //roomTypeId
                    Map<Long, RoomTypeDetailsDTO> roomTypeDTOMap = new HashMap<>();
                    if (null != productDailyInfoResponse
                            && ResultCodeEnum.SUCCESS.code == productDailyInfoResponse.getResult()
                            && !CollectionUtils.isEmpty(productDailyInfoResponseDTOList)) {
                        ProductDetailsDTO productDetailsDTO = null;
                        for (ProductDailyInfoResponseDTO p : productDailyInfoResponseDTOList) {
                            productDetailsDTO = PropertyCopyUtil.transfer(p, ProductDetailsDTO.class);
                            productDetailsDTO.setFirstDayPrice(productDetailsDTO.getProductDailyList().get(0).getB2bSalePrice());
                            productDetailsDTO.setFirstGroupSalePrice(productDetailsDTO.getProductDailyList().get(0).getGroupSalePrice());
                            productDetailsDTO.setShowCurrency(agentUserDTO.getFinanceCurrency());
                            if (roomTypeDTOMap.containsKey(p.getRoomTypeId())) {
                                roomTypeDTOMap.get(p.getRoomTypeId()).getProductDetails().add(productDetailsDTO);
                            } else {
                                RoomTypeDetailsDTO roomTypeDTO = new RoomTypeDetailsDTO();
                                roomTypeDTO.setRoomTypeId(p.getRoomTypeId());
                                roomTypeDTO.setRoomTypeName(p.getRoomTypeName());
                                List<ProductDetailsDTO> pricePlanInfoDTOList = new ArrayList<>();
                                pricePlanInfoDTOList.add(productDetailsDTO);
                                roomTypeDTO.setProductDetails(pricePlanInfoDTOList);
                                roomTypeDTOMap.put(p.getRoomTypeId(), roomTypeDTO);
                            }
                        }
                    }
                    List<RoomTypeDetailsDTO> roomTypeDTOList = new ArrayList<>();
                    for (RoomTypeDTO roomTypeDTO : roomTypeList) {
                        if (roomTypeDTOMap.containsKey(roomTypeDTO.getRoomTypeId())) {
                            BigDecimal startPrice = null;//起价
                            RoomTypeDetailsDTO roomTypeDetailsDTO = roomTypeDTOMap.get(roomTypeDTO.getRoomTypeId());
                            roomTypeDetailsDTO.setHotelId(roomTypeDTO.getHotelId());
                            roomTypeDetailsDTO.setFloor(roomTypeDTO.getFloor());
                            roomTypeDetailsDTO.setArea(roomTypeDTO.getArea());
                            roomTypeDetailsDTO.setBedDescription(roomTypeDTO.getBedDescription());
                            roomTypeDetailsDTO.setShowCurrency(agentUserDTO.getFinanceCurrency());
                            roomTypeDetailsDTO.setImageUrl(roomTypeDTO.getImageUrl());
                            List<ProductDetailsDTO> productDetails = roomTypeDetailsDTO.getProductDetails();
                            for (ProductDetailsDTO productDetail : productDetails) {
                                if (null == startPrice && null != productDetail.getStartPrice()) {
                                    startPrice = productDetail.getStartPrice();
                                } else if (null != startPrice && null != productDetail.getStartPrice()) {
                                    if (startPrice.compareTo(productDetail.getStartPrice()) > 0) {
                                        startPrice = productDetail.getStartPrice();
                                    }
                                }
                            }
                            roomTypeDetailsDTO.setStartPrice(startPrice);
                            roomTypeDTOList.add(roomTypeDetailsDTO);
                        }
                    }
                    getHotelDetailResponseDTO.setRoomTypeDetails(roomTypeDTOList);
                }
                responseDTO.setModel(getHotelDetailResponseDTO);
            } else {
                responseDTO.setResult(roomTypeRsponseDTOResponseDTO.getResult());
                responseDTO.setFailCode(roomTypeRsponseDTOResponseDTO.getFailCode());
                responseDTO.setFailReason(roomTypeRsponseDTOResponseDTO.getFailReason());
            }
        } catch (Exception e) {
            log.error("getHotelDetail has error", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<HotelBaseInfoWithImagesResponseDTO> queryHotelBaseInfo(HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            HotelBaseInfoWithImagesResponseDTO hotelBaseInfoWithImagesResponseDTO = new HotelBaseInfoWithImagesResponseDTO();

            //一个查基本信息,一个查图片
            CountDownLatch countDownLatch = new CountDownLatch(2);

            HotelBasicInfoThread hotelBasicInfoThread1 =
                    new HotelBasicInfoThread(1, countDownLatch, hotelBaseInfoRequestDTO, hotelBaseInfoWithImagesResponseDTO, hotelInfoService, imageService);
            //result1  查询基本信息结果
            FutureTask<HotelBaseInfoWithImagesResponseDTO> result1 = new FutureTask<>(hotelBasicInfoThread1);

            HotelBasicInfoThread hotelBasicInfoThread2 =
                    new HotelBasicInfoThread(2, countDownLatch, hotelBaseInfoRequestDTO, hotelBaseInfoWithImagesResponseDTO, hotelInfoService, imageService);
            //result2  查询图片结果
            FutureTask<HotelBaseInfoWithImagesResponseDTO> result2 = new FutureTask<>(hotelBasicInfoThread2);

            threadPoolTaskExecutor.submit(result1);
            threadPoolTaskExecutor.submit(result2);

            countDownLatch.await();

            hotelBaseInfoWithImagesResponseDTO = result1.get();
            if (null != result2.get()) {
                hotelBaseInfoWithImagesResponseDTO.setImageTypeList(result2.get().getImageTypeList());
                hotelBaseInfoWithImagesResponseDTO.setRoomTypeImageList(result2.get().getRoomTypeImageList());
                hotelBaseInfoWithImagesResponseDTO.setMeetingRoomImageList(result2.get().getMeetingRoomImageList());
            }

            responseDTO.setModel(hotelBaseInfoWithImagesResponseDTO);

        } catch (Exception e) {
            log.error("queryHotelBaseInfo has error:" + JSON.toJSONString(hotelBaseInfoRequestDTO), e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<QueryMerchantCommonCityResponseDTO> queryCommonCity(String merchantCode) {
        ResponseDTO responseDTO = new ResponseDTO();
        QueryMerchantCommonCityResponseDTO commonCityResponseDTO = null;
        List<QueryMerchantCommonCityResponseDTO> result = new ArrayList<>();
        List<CommonCityDTO> commonCityDTOS = new ArrayList<>();
        try {
            commonCityDTOS = commonCityService.queryCommonCity(merchantCode);
            for (CommonCityDTO commonCityDTO : commonCityDTOS) {
                commonCityResponseDTO = new QueryMerchantCommonCityResponseDTO();
                commonCityResponseDTO = PropertyCopyUtil.transfer(commonCityDTO, QueryMerchantCommonCityResponseDTO.class);
                result.add(commonCityResponseDTO);
            }
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(result);
        } catch (Exception e) {
            log.error("queryCommonCity has error", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }


        return responseDTO;
    }

    @Override
    public ResponseDTO<QueryMerchantCommonCityResponseDTO> fuzzyQueryMerchantCity(QueryMerchantAllCityDTO queryMerchantAllCityDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        QueryMerchantCommonCityResponseDTO commonCityResponseDTO = null;
        List<CommonCityDTO> commonCityDTOS = new ArrayList<>();
        List<QueryMerchantCommonCityResponseDTO> result = new ArrayList<>();
        try {
            PageHelper.startPage(queryMerchantAllCityDTO.getCurrentPage(), queryMerchantAllCityDTO.getPageSize());
            commonCityDTOS = commonCityMapper.queryB2BMerchantCity(queryMerchantAllCityDTO);
            PageInfo<CommonCityDTO> page = new PageInfo<>(commonCityDTOS);
            for (CommonCityDTO commonCityDTO : commonCityDTOS) {
                commonCityResponseDTO = new QueryMerchantCommonCityResponseDTO();
                commonCityResponseDTO = PropertyCopyUtil.transfer(commonCityDTO, QueryMerchantCommonCityResponseDTO.class);
                result.add(commonCityResponseDTO);
            }
            responseDTO.setModel(result);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        } catch (Exception e) {
            log.error("fuzzyQueryMerchantCity has error", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }


        return responseDTO;
    }

    public ResponseDTO<List<HotelSimpleDTO>> associateHotelInfo(HotelListQueryDTO hotelListQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            List<HotelSimpleDTO> hotelSimpleDTOS = null;
            // 每页显示记录数
            PageHelper.startPage(hotelListQueryDTO.getCurrentPage(), hotelListQueryDTO.getPageSize());
            List<HotelDO> hotelList = hotelMapper.getHotelList(hotelListQueryDTO);
            if(!CollectionUtils.isEmpty(hotelList)){
                hotelSimpleDTOS = PropertyCopyUtil.transferArray(hotelList,HotelSimpleDTO.class);
                responseDTO.setModel(hotelSimpleDTOS);
            }
        } catch (Exception e) {
            log.error("associateHotelInfo has error", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

}
