package com.fangcang.mapping.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.util.StringUtil;
import com.fangcang.mapping.mapper.ElongHotelMappingMapper;
import com.fangcang.mapping.mapper.ElongRatePlanMappingMapper;
import com.fangcang.mapping.mapper.ElongRoomMappingMapper;
import com.fangcang.mapping.request.MappingQueryRequest;
import com.fangcang.mapping.response.ElongHotelMappingResponse;
import com.fangcang.mapping.response.ElongRatePlanMappingResponse;
import com.fangcang.mapping.response.ElongRoomMappingResponse;
import com.fangcang.mapping.service.ElongMappingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ElongMappingServiceImpl implements ElongMappingService{

    @Autowired
    private ElongHotelMappingMapper elongHotelMappingMapper;

    @Autowired
    private ElongRoomMappingMapper elongRoomMappingMapper;

    @Autowired
    private ElongRatePlanMappingMapper elongRatePlanMappingMapper;

    @Override
    public ResponseDTO<PaginationSupportDTO<ElongHotelMappingResponse>> queryElongMapping(MappingQueryRequest mappingQueryRequest) {
        ResponseDTO<PaginationSupportDTO<ElongHotelMappingResponse>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
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
        List<ElongHotelMappingResponse> hotelList = elongHotelMappingMapper.queryMappingHotel(mappingQueryRequest);

        if (CollectionUtils.isEmpty(hotelList)){
            log.info("未查到有产品的酒店，直接返回。请求参数{}", JSON.toJSONString(mappingQueryRequest));
            responseDTO = new ResponseDTO<>(ErrorCodeEnum.MAPPING_HOTEL_RATEPLAN_NOT_EXIST_ERROR);
            return responseDTO;
        }
        List<Integer> hotelIdList = new ArrayList<>();
        hotelList.forEach(tempHotel -> {hotelIdList.add(tempHotel.getHotelId());});

        List<ElongRoomMappingResponse> roomMappingList = elongRoomMappingMapper.queryMappingRoom(mappingQueryRequest.getMerchantCode(),hotelIdList);
        List<ElongRatePlanMappingResponse> rateMappingList=elongRatePlanMappingMapper.queryMappingRatePlan(mappingQueryRequest.getMerchantCode(),hotelIdList);

        //Map<房型ID,List<价格计划>>
        Map<Integer,List<ElongRatePlanMappingResponse>> roomIdRatePlanMappingMap = new HashMap();
        for (ElongRatePlanMappingResponse ratePlanMapping: rateMappingList){
            if (roomIdRatePlanMappingMap.containsKey(ratePlanMapping.getRoomTypeId())){
                roomIdRatePlanMappingMap.get(ratePlanMapping.getRoomTypeId()).add(ratePlanMapping);
            }
            else{
                List<ElongRatePlanMappingResponse> tempRateMappingList = new ArrayList<>();
                tempRateMappingList.add(ratePlanMapping);
                roomIdRatePlanMappingMap.put(ratePlanMapping.getRoomTypeId(),tempRateMappingList);
            }
        }
        //Map<酒店ID,List<房型>>
        Map<Integer,List<ElongRoomMappingResponse>> hotelIdRoomMappingMap = new HashMap();
        for (ElongRoomMappingResponse roomMapping: roomMappingList){
            if (hotelIdRoomMappingMap.containsKey(roomMapping.getRoomTypeId())){
                hotelIdRoomMappingMap.get(roomMapping.getHotelId()).add(roomMapping);
            }
            else{
                List<ElongRoomMappingResponse> tempRoomMappingList = new ArrayList<>();
                tempRoomMappingList.add(roomMapping);
                hotelIdRoomMappingMap.put(roomMapping.getHotelId(),tempRoomMappingList);
            }
            roomMapping.setRatePlanList(roomIdRatePlanMappingMap.get(roomMapping.getRoomTypeId()));
        }

        //价格计划填充到对应的酒店对象里。
        hotelList.forEach(temp -> {temp.setRoomList(hotelIdRoomMappingMap.get(temp.getHotelId()));});

        PageInfo<ElongHotelMappingResponse> pageInfo = new PageInfo<>(hotelList);
        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(pageInfo.getTotal());
        paginationSupportDTO.setTotalPage(pageInfo.getPages());
        paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
        paginationSupportDTO.setPageSize(pageInfo.getPageSize());
        paginationSupportDTO.setItemList(hotelList);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }
}
