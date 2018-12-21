package com.fangcang.mapping.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.mapping.domain.HotelMappingDO;
import com.fangcang.mapping.domain.RatePlanMappingDO;
import com.fangcang.mapping.dto.Mapping;
import com.fangcang.mapping.mapper.HotelMappingMapper;
import com.fangcang.mapping.mapper.RatePlanMappingMapper;
import com.fangcang.mapping.request.HotelMappingRequest;
import com.fangcang.mapping.request.MappingQueryRequest;
import com.fangcang.mapping.request.RatePlanMappingRequest;
import com.fangcang.mapping.response.HotelMappingResponse;
import com.fangcang.mapping.response.RatePlanMappingResponse;
import com.fangcang.mapping.response.RoomMappingResponse;
import com.fangcang.mapping.service.HotelMappingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Owen on 2018/6/8.
 */
@Slf4j
@Service
public class HotelMappingServiceImpl implements HotelMappingService {

    @Autowired
    private HotelMappingMapper hotelMappingMapper;

    @Autowired
    private RatePlanMappingMapper ratePlanMappingMapper;

    @Override
    public ResponseDTO<PaginationSupportDTO<List<HotelMappingResponse>>> queryMappingNew(MappingQueryRequest mappingQueryRequest) {
        ResponseDTO<PaginationSupportDTO<List<HotelMappingResponse>>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        if (null == mappingQueryRequest ){
            log.error("查询酒店映射失败：参数为空");
            responseDTO = new ResponseDTO<>(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }

        if(!StringUtil.isValidString(mappingQueryRequest.getMerchantCode())) {
            log.error("查询酒店映射失败，商家编码为空，{}", JSON.toJSONString(mappingQueryRequest));
            responseDTO = new ResponseDTO<>(ErrorCodeEnum.INVALID_INPUTPARAM);
            return responseDTO;
        }

        //分页查询酒店列表
        PageHelper.startPage(mappingQueryRequest.getCurrentPage(), mappingQueryRequest.getPageSize());
        mappingQueryRequest.setMappingStatus(null==mappingQueryRequest.getMappingStatus() ? 2 : mappingQueryRequest.getMappingStatus().intValue());
        List<HotelMappingResponse> hotelList = hotelMappingMapper.queryDltMappingHotelList(mappingQueryRequest);

        if (CollectionUtils.isEmpty(hotelList)){
            log.info("未查到有产品的酒店，直接返回。请求参数{}", JSON.toJSONString(mappingQueryRequest));
            responseDTO = new ResponseDTO<>(ErrorCodeEnum.MAPPING_HOTEL_RATEPLAN_NOT_EXIST_ERROR);
            return responseDTO;
        }
        List<Long> hotelIdList = new ArrayList<>();
        hotelList.forEach(tempHotel -> {hotelIdList.add(tempHotel.getHotelId());});

        List<RatePlanMappingResponse> rateMappingList = hotelMappingMapper.queryDltRateMapping(mappingQueryRequest.getMerchantCode(),hotelIdList);

        //Map<酒店ID,List<价格计划>>
        Map<Long,List<RatePlanMappingResponse>> rateHotelIdMappingMap = new HashMap();
        for (RatePlanMappingResponse ratePlanMappingDO : rateMappingList){
            if (rateHotelIdMappingMap.containsKey(ratePlanMappingDO.getHotelId())){
                rateHotelIdMappingMap.get(ratePlanMappingDO.getHotelId()).add(ratePlanMappingDO);
            }
            else{
                List<RatePlanMappingResponse> tempRateMappingList = new ArrayList<>();
                tempRateMappingList.add(ratePlanMappingDO);
                rateHotelIdMappingMap.put(ratePlanMappingDO.getHotelId(),tempRateMappingList);
            }
        }

        //价格计划填充到对应的酒店对象里。
        hotelList.forEach(temp -> {temp.setRatePlanList(rateHotelIdMappingMap.get(temp.getHotelId()));});

        PageInfo<HotelMappingResponse> pageInfo = new PageInfo<>(hotelList);
        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(pageInfo.getTotal());
        paginationSupportDTO.setTotalPage(pageInfo.getPages());
        paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
        paginationSupportDTO.setPageSize(pageInfo.getPageSize());
        paginationSupportDTO.setItemList(hotelList);

        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }

    /**
     * 查询映射关系
     * @param mappingQueryRequest
     * @return
     */
    @Override
    public ResponseDTO queryMapping(MappingQueryRequest mappingQueryRequest) {
        ResponseDTO responseDTO = null;

        if(null!=mappingQueryRequest && StringUtil.isValidString(mappingQueryRequest.getMerchantCode())) {
            responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            //分页查询酒店列表
            PageHelper.startPage(mappingQueryRequest.getCurrentPage(), mappingQueryRequest.getPageSize());

            List<Long> hotelIdList = null;

            switch (null==mappingQueryRequest.getMappingStatus()?2:mappingQueryRequest.getMappingStatus().intValue()) {
                case 0:
                    hotelIdList = hotelMappingMapper.queryUnMappingHotel(mappingQueryRequest);break;
                case 1:
                    hotelIdList = hotelMappingMapper.queryMappingHotel(mappingQueryRequest);break;
                default:
                    hotelIdList = hotelMappingMapper.queryAllHotel(mappingQueryRequest);
            }

            PageInfo<Long> pageInfo = new PageInfo<>(hotelIdList);

            PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setPageSize(pageInfo.getPageSize());

            List<HotelMappingResponse> hotelMappingResponseList = null;

            //查询映射信息
            if(!CollectionUtils.isEmpty(hotelIdList)) {

                List<Mapping> mappingList = ratePlanMappingMapper.queryMapping(mappingQueryRequest.getMerchantCode(),hotelIdList);
                //组装映射返回对象

                if(null!=mappingList) {
                    hotelMappingResponseList = assembleMappingHotel(mappingList);
                }
            }

            paginationSupportDTO.setItemList(hotelMappingResponseList);
            responseDTO.setModel(paginationSupportDTO);
        }else {
            responseDTO = new ResponseDTO(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("参数不合法");
        }

        return responseDTO;
    }

    /**
     * 新增或者修改酒店映射
     * @param hotelMappingRequest
     * @return
     */
    @Override
    public ResponseDTO saveOrUpdateHotelMapping(HotelMappingRequest hotelMappingRequest) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null!=hotelMappingRequest && null!=hotelMappingRequest.getHotelId() && null!=hotelMappingRequest.getMerchantCode()) {
            HotelMappingDO example = new HotelMappingDO();
            example.setHotelId(hotelMappingRequest.getHotelId());
            example.setMerchantCode(hotelMappingRequest.getMerchantCode());
            List<HotelMappingDO> hotelMappingDOList = hotelMappingMapper.select(example);

            if(!CollectionUtils.isEmpty(hotelMappingDOList)) {
                HotelMappingDO hotelMappingDO = hotelMappingDOList.get(0);
                hotelMappingDO.setHotelId(hotelMappingRequest.getHotelId());
                hotelMappingDO.setDltHotelId(hotelMappingRequest.getDltHotelId());
                hotelMappingDO.setModifier(hotelMappingRequest.getModifier());

                Example updateExample = new Example(HotelMappingDO.class);
                Example.Criteria updateCriteria = updateExample.createCriteria();
                updateCriteria.andEqualTo("hotelId",hotelMappingRequest.getHotelId());
                updateCriteria.andEqualTo("merchantCode",hotelMappingRequest.getMerchantCode());
                hotelMappingMapper.updateByExampleSelective(hotelMappingDO,updateExample);

            }else {
                HotelMappingDO hotelMappingDO = PropertyCopyUtil.transfer(hotelMappingRequest, HotelMappingDO.class);
                hotelMappingMapper.insert(hotelMappingDO);
            }
        }else {
            responseDTO = new ResponseDTO(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
        }

        return responseDTO;
    }

    /**
     * 新增或者修改房型映射
     * @param ratePlanMappingRequest
     * @return
     */
    @Override
    public ResponseDTO saveOrUpdateRatePlanMapping(RatePlanMappingRequest ratePlanMappingRequest) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null!=ratePlanMappingRequest && null!=ratePlanMappingRequest.getRatePlanId() && null!=ratePlanMappingRequest.getMerchantCode()) {
            RatePlanMappingDO example = new RatePlanMappingDO();
            example.setRatePlanId(ratePlanMappingRequest.getRatePlanId());
            example.setMerchantCode(ratePlanMappingRequest.getMerchantCode());
            List<RatePlanMappingDO> ratePlanMappingDOList = ratePlanMappingMapper.select(example);

            if(!CollectionUtils.isEmpty(ratePlanMappingDOList)) {
                RatePlanMappingDO ratePlanMappingDO = ratePlanMappingDOList.get(0);
                ratePlanMappingDO.setDltRatePlanId(ratePlanMappingRequest.getDltRatePlanId());
                ratePlanMappingDO.setModifier(ratePlanMappingRequest.getModifier());

                Example updateExample = new Example(RatePlanMappingDO.class);
                Example.Criteria updateCriteria = updateExample.createCriteria();
                updateCriteria.andEqualTo("ratePlanId",ratePlanMappingRequest.getRatePlanId());
                updateCriteria.andEqualTo("merchantCode",ratePlanMappingRequest.getMerchantCode());
                ratePlanMappingMapper.updateByExampleSelective(ratePlanMappingDO,updateExample);
            }else {
                RatePlanMappingDO ratePlanMappingDO = PropertyCopyUtil.transfer(ratePlanMappingRequest, RatePlanMappingDO.class);
                ratePlanMappingMapper.insert(ratePlanMappingDO);
            }
        }else {
            responseDTO = new ResponseDTO(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
        }

        return responseDTO;
    }

    /**
     * 组装酒店映射返回对象
     * @param mappingList
     * @return
     */
    private List<HotelMappingResponse> assembleMappingHotel(List<Mapping> mappingList) {
        List<HotelMappingResponse> hotelMappingResponseList = null;
        Map<Long,Map<Long,Map<Long,Mapping>>> mappingMap = null;
        if(!CollectionUtils.isEmpty(mappingList)) {
            mappingMap = new HashMap<Long,Map<Long,Map<Long,Mapping>>>();
            for(Mapping mapping : mappingList) {
                if(null==mappingMap.get(mapping.getHotelId())) {
                    Map<Long,Mapping> ratePlanMap = new HashMap<Long,Mapping>();
                    ratePlanMap.put(mapping.getRatePlanId(),mapping);

                    Map<Long,Map<Long,Mapping>> roomTypeMap = new HashMap<Long,Map<Long,Mapping>>();
                    roomTypeMap.put(mapping.getRoomTypeId(),ratePlanMap);

                    mappingMap.put(mapping.getHotelId(),roomTypeMap);
                }else if(null==mappingMap.get(mapping.getHotelId()).get(mapping.getRoomTypeId())){
                    Map<Long,Mapping> ratePlanMap = new HashMap<Long,Mapping>();
                    ratePlanMap.put(mapping.getRatePlanId(),mapping);

                    mappingMap.get(mapping.getHotelId()).put(mapping.getRoomTypeId(),ratePlanMap);
                }else {
                    mappingMap.get(mapping.getHotelId()).get(mapping.getRoomTypeId()).put(mapping.getRatePlanId(),mapping);
                }
            }
        }

        if(null!=mappingMap) {
            hotelMappingResponseList = new ArrayList<HotelMappingResponse>();
            for(Long hotelId : mappingMap.keySet()) {
                //酒店房型信息只需要组装一次，任意取一个mapping的数据就好
                Mapping mappingHotel = null;
                HotelMappingResponse hotelMappingResponse = new HotelMappingResponse();
                List<RoomMappingResponse> roomTypeList = new ArrayList<RoomMappingResponse>();

                for(Long roomTypeId : mappingMap.get(hotelId).keySet()) {
                    Mapping mappingRoom = null;
                    RoomMappingResponse roomMappingResponse = new RoomMappingResponse();
                    List<RatePlanMappingResponse> ratePlanMappingResponseList = new ArrayList<RatePlanMappingResponse>();

                    for(Long ratePlanId : mappingMap.get(hotelId).get(roomTypeId).keySet()) {
                        Mapping mapping = mappingMap.get(hotelId).get(roomTypeId).get(ratePlanId);

                        if(null == mappingHotel || null == mappingHotel.getDltHotelId()) {
                            mappingHotel = mapping;
                        }

                        if(null == mappingRoom) {
                            mappingRoom = mapping;
                        }
                        RatePlanMappingResponse ratePlanMappingResponse = new RatePlanMappingResponse();
                        ratePlanMappingResponse.setDltRatePlanId(mapping.getDltRatePlanId());
                        ratePlanMappingResponse.setRatePlanId(mapping.getRatePlanId());
                        ratePlanMappingResponse.setRatePlanName(mapping.getRatePlanName());
                        ratePlanMappingResponseList.add(ratePlanMappingResponse);
                    }

                    roomMappingResponse.setRoomTypeId(mappingRoom.getRoomTypeId());
                    roomMappingResponse.setRoomTypeName(mappingRoom.getRoomTypeName());
                    roomMappingResponse.setRatePlanList(ratePlanMappingResponseList);
                    roomTypeList.add(roomMappingResponse);
                }

                hotelMappingResponse.setMerchantCode(mappingHotel.getMerchantCode());
                hotelMappingResponse.setCityCode(mappingHotel.getCityCode());
                hotelMappingResponse.setCityName(mappingHotel.getCityName());
                hotelMappingResponse.setHotelId(mappingHotel.getHotelId());
                hotelMappingResponse.setHotelName(mappingHotel.getHotelName());
                hotelMappingResponse.setDltHotelId(mappingHotel.getDltHotelId());
                hotelMappingResponse.setRoomTypeList(roomTypeList);
                hotelMappingResponseList.add(hotelMappingResponse);
            }
        }

        return hotelMappingResponseList;

    }

}
