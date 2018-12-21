package com.fangcang.report.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.BedTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.domain.RoomTypeDO;
import com.fangcang.hotelinfo.mapper.HotelMapper;
import com.fangcang.hotelinfo.mapper.RoomTypeMapper;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.request.DynamicPricePlanQueryDTO;
import com.fangcang.report.dto.HotelQuotaDTO;
import com.fangcang.report.dto.PricePlanQuotaDTO;
import com.fangcang.report.dto.QuotaDayDTO;
import com.fangcang.report.dto.RoomQuotaDTO;
import com.fangcang.report.request.QuotaReportQueryDTO;
import com.fangcang.report.service.QuotaReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ASUS on 2018/7/9.
 */
@Service
@Slf4j
public class QuotaReportServiceImpl implements QuotaReportService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Autowired
    private PricePlanMapper pricePlanMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public ResponseDTO<PaginationSupportDTO<HotelQuotaDTO>> quotaReportDetail(QuotaReportQueryDTO quotaReportQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        PaginationSupportDTO paginationSupportDTO = null;
        try {
            //返回对象
            HotelQuotaDTO hotelQuotaDTO = new HotelQuotaDTO();
            HotelListQueryDTO hotelListQueryDTO = new HotelListQueryDTO();
            hotelListQueryDTO.setHotelId(quotaReportQueryDTO.getHotelId());
            List<HotelDO> hotelList = hotelMapper.getHotelList(hotelListQueryDTO);
            if(CollectionUtils.isEmpty(hotelList)){
                log.error("hotelList has empty.");
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
                return responseDTO;
            }
            hotelQuotaDTO.setHotelId(hotelList.get(0).getHotelId());
            hotelQuotaDTO.setHotelName(hotelList.get(0).getHotelName());

            RoomTypeQueryDTO roomTypeQueryDTO = new RoomTypeQueryDTO();
            roomTypeQueryDTO.setHotelId(quotaReportQueryDTO.getHotelId());
            PageHelper.startPage(quotaReportQueryDTO.getCurrentPage(),quotaReportQueryDTO.getPageSize());
            List<RoomTypeDO> roomTypeDOS = roomTypeMapper.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);
            PageInfo<RoomTypeDO> pageInfo = new PageInfo<>(roomTypeDOS);

            paginationSupportDTO = new PaginationSupportDTO();
            paginationSupportDTO.setTotalCount(pageInfo.getTotal());
            paginationSupportDTO.setTotalPage(pageInfo.getPages());
            paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
            paginationSupportDTO.setPageSize(pageInfo.getPageSize());
            if(!CollectionUtils.isEmpty(roomTypeDOS)){
                List<Date> dateList = DateUtil.getDateList(DateUtil.stringToDate(quotaReportQueryDTO.getStartDate()),DateUtil.stringToDate(quotaReportQueryDTO.getEndDate()));

                List<Long> roomTypeIdList = new ArrayList<>();
                for (RoomTypeDO roomTypeDO : roomTypeDOS) {
                    roomTypeIdList.add(roomTypeDO.getRoomTypeId());
                }
                DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO = new DynamicPricePlanQueryDTO();
                dynamicPricePlanQueryDTO.setHotelId(quotaReportQueryDTO.getHotelId());
                dynamicPricePlanQueryDTO.setRoomTypeIds(roomTypeIdList);
                dynamicPricePlanQueryDTO.setStartDate(DateUtil.stringToDate(quotaReportQueryDTO.getStartDate()));
                dynamicPricePlanQueryDTO.setEndDate(DateUtil.stringToDate(quotaReportQueryDTO.getEndDate()));
                dynamicPricePlanQueryDTO.setSupplyCode(quotaReportQueryDTO.getSupplyCode());
                dynamicPricePlanQueryDTO.setQuotaType(quotaReportQueryDTO.getQuotaType());
                dynamicPricePlanQueryDTO.setMerchantCode(quotaReportQueryDTO.getMerchantCode());
                dynamicPricePlanQueryDTO.setProductType(1);
                List<PricePlanQuotaDTO> pricePlanQuotaDTOS = pricePlanMapper.queryPricePlanOfQuotaReport(dynamicPricePlanQueryDTO);
                Map<Long,List<PricePlanQuotaDTO>> roomTypeQuotaMap = new HashMap<>();
                //房型小计 roomTypeId
                Map<Long,QuotaDayDTO> roomTypeQuotaSumMap = new HashMap<>();
                //房型小计  每日 roomTypeId:saleDate
                Map<String,QuotaDayDTO> roomTypeDailyMap = new HashMap<>();
                StringBuilder stringBuilder = new StringBuilder();
                if(!CollectionUtils.isEmpty(pricePlanQuotaDTOS)){
                    Map<Date,QuotaDayDTO> quotaDailyMap = new HashMap<>();
                    Integer quotaNum = 0;
                    Integer usedQuotaNum = 0;
                    Integer allQuotaNum = 0;
                    StringBuilder bedTypeBuilder = new StringBuilder();
                    Set<Long> shareQuotaPool = new HashSet<>();//价格计划共享，配额只计算一次
                    for (PricePlanQuotaDTO pricePlanQuotaDTO : pricePlanQuotaDTOS) {
                        quotaNum = 0;
                        usedQuotaNum = 0;
                        allQuotaNum = 0;
                        quotaDailyMap.clear();

                        bedTypeBuilder.setLength(0);
                        if(StringUtil.isValidString(pricePlanQuotaDTO.getBedType())){
                            String bedTypeArray [] = pricePlanQuotaDTO.getBedType().split(",");
                            for(String bedType : bedTypeArray){
                                bedTypeBuilder.append(BedTypeEnum.getValueByKey(Integer.valueOf(bedType))).append(",");
                            }
                            if(bedTypeBuilder.length()>0){
                                pricePlanQuotaDTO.setBedTypeStr(bedTypeBuilder.toString().substring(0,bedTypeBuilder.toString().length() - 1));
                            }
                        }else{
                            pricePlanQuotaDTO.setBedTypeStr("");
                        }

                        List<QuotaDayDTO> quotaDayList = pricePlanQuotaDTO.getQuotaDayList();
                        for (QuotaDayDTO quotaDayDTO : quotaDayList) {
                            stringBuilder.setLength(0);
                            stringBuilder.append(pricePlanQuotaDTO.getRoomTypeId()).append(":").append(DateUtil.dateToString(quotaDayDTO.getSaleDate()));

                            quotaDailyMap.put(quotaDayDTO.getSaleDate(),quotaDayDTO);
                            quotaNum = quotaNum + quotaDayDTO.getQuotaNum();
                            usedQuotaNum = usedQuotaNum + quotaDayDTO.getUsedQuotaNum();
                            allQuotaNum = allQuotaNum + quotaDayDTO.getAllQuotaNum();
                            //共享产品配额只取一次
                            if((null != pricePlanQuotaDTO.getIsShare()
                                    && Integer.valueOf(1).compareTo(pricePlanQuotaDTO.getIsShare()) == 0
                                    && !shareQuotaPool.contains(pricePlanQuotaDTO.getQuotaAccountId()))
                                    ||  Integer.valueOf(0).compareTo(pricePlanQuotaDTO.getIsShare()) == 0){
                                if(roomTypeQuotaSumMap.containsKey(pricePlanQuotaDTO.getRoomTypeId())){
                                    QuotaDayDTO q = roomTypeQuotaSumMap.get(pricePlanQuotaDTO.getRoomTypeId());
                                    q.setQuotaNum(q.getQuotaNum() + quotaDayDTO.getQuotaNum());
                                    q.setUsedQuotaNum(q.getUsedQuotaNum() + quotaDayDTO.getUsedQuotaNum());
                                    q.setAllQuotaNum(q.getAllQuotaNum() + quotaDayDTO.getAllQuotaNum());
                                    roomTypeQuotaSumMap.put(pricePlanQuotaDTO.getRoomTypeId(),q);
                                }else{
                                    QuotaDayDTO roomTypeQuotaSum = new QuotaDayDTO();
                                    roomTypeQuotaSum.setQuotaNum(quotaDayDTO.getQuotaNum());
                                    roomTypeQuotaSum.setUsedQuotaNum(quotaDayDTO.getUsedQuotaNum());
                                    roomTypeQuotaSum.setAllQuotaNum(quotaDayDTO.getAllQuotaNum());
                                    roomTypeQuotaSumMap.put(pricePlanQuotaDTO.getRoomTypeId(),roomTypeQuotaSum);
                                }

                                if(roomTypeDailyMap.containsKey(stringBuilder.toString())){
                                    QuotaDayDTO q = roomTypeDailyMap.get(stringBuilder.toString());
                                    q.setQuotaNum(q.getQuotaNum() + quotaDayDTO.getQuotaNum());
                                    q.setUsedQuotaNum(q.getUsedQuotaNum() + quotaDayDTO.getUsedQuotaNum());
                                    q.setAllQuotaNum(q.getAllQuotaNum() + quotaDayDTO.getAllQuotaNum());
                                    roomTypeDailyMap.put(stringBuilder.toString(),q);
                                }else{
                                    QuotaDayDTO roomTypeDaily = new QuotaDayDTO();
                                    roomTypeDaily.setQuotaNum(quotaDayDTO.getQuotaNum());
                                    roomTypeDaily.setUsedQuotaNum(quotaDayDTO.getUsedQuotaNum());
                                    roomTypeDaily.setAllQuotaNum(quotaDayDTO.getAllQuotaNum());
                                    roomTypeDailyMap.put(stringBuilder.toString(),roomTypeDaily);
                                }
                            }
                        }

                        //共享产品
                        if(null != pricePlanQuotaDTO.getIsShare()
                                && Integer.valueOf(1).compareTo(pricePlanQuotaDTO.getIsShare()) == 0){
                            shareQuotaPool.add(pricePlanQuotaDTO.getQuotaAccountId());
                        }

                        List<QuotaDayDTO> newQuotaDayList = new ArrayList();
                        for(Date saleDate : dateList){
                            if(quotaDailyMap.containsKey(saleDate)){
                                newQuotaDayList.add(quotaDailyMap.get(saleDate));
                            }else{
                                QuotaDayDTO quotaDayDTO = new QuotaDayDTO();
                                quotaDayDTO.setPricePlanId(pricePlanQuotaDTO.getPricePlanId());
                                quotaDayDTO.setQuotaAccountId(pricePlanQuotaDTO.getQuotaAccountId());
                                quotaDayDTO.setQuotaNum(0);
                                quotaDayDTO.setUsedQuotaNum(0);
                                quotaDayDTO.setAllQuotaNum(0);
                                quotaDayDTO.setSaleDate(saleDate);
                                newQuotaDayList.add(quotaDayDTO);
                            }
                        }
                        pricePlanQuotaDTO.setQuotaDayList(newQuotaDayList);
                        pricePlanQuotaDTO.setQuotaNum(quotaNum);
                        pricePlanQuotaDTO.setUsedQuotaNum(usedQuotaNum);
                        pricePlanQuotaDTO.setAllQuotaNum(allQuotaNum);
                        if(roomTypeQuotaMap.containsKey(pricePlanQuotaDTO.getRoomTypeId())){
                            roomTypeQuotaMap.get(pricePlanQuotaDTO.getRoomTypeId()).add(pricePlanQuotaDTO);
                        }else{
                            List<PricePlanQuotaDTO> pricePlanQuotaList = new ArrayList<>();
                            pricePlanQuotaList.add(pricePlanQuotaDTO);
                            roomTypeQuotaMap.put(pricePlanQuotaDTO.getRoomTypeId(),pricePlanQuotaList);
                        }
                    }
                }

                List<RoomQuotaDTO> roomQuotaDTOS = new ArrayList<>();
                RoomQuotaDTO roomQuotaDTO = null;
                //酒店合计
                Integer quotaNum = 0;
                Integer usedQuotaNum = 0;
                Integer allQuotaNum = 0;
                Map<Date,QuotaDayDTO> hotelQuotaMap = new HashMap<>();
                for(RoomTypeDO roomTypeDO : roomTypeDOS){
                    //if(roomTypeQuotaMap.containsKey(roomTypeDO.getRoomTypeId())){
                        roomQuotaDTO = new RoomQuotaDTO();
                        roomQuotaDTO.setRoomTypeId(roomTypeDO.getRoomTypeId());
                        roomQuotaDTO.setRoomTypeName(roomTypeDO.getRoomTypeName());
                        if(roomTypeQuotaMap.containsKey(roomTypeDO.getRoomTypeId())){
                            roomQuotaDTO.setPricePlanQuotaList(roomTypeQuotaMap.get(roomTypeDO.getRoomTypeId()));
                        }else{
                            roomQuotaDTO.setPricePlanQuotaList(new ArrayList<>());
                        }

                        //房型小计
                        QuotaDayDTO quotaDayDTO = null;
                        if(roomTypeQuotaSumMap.containsKey(roomTypeDO.getRoomTypeId())){
                            quotaDayDTO = roomTypeQuotaSumMap.get(roomTypeDO.getRoomTypeId());
                        }else{
                            quotaDayDTO = new QuotaDayDTO();
                            quotaDayDTO.setQuotaNum(0);
                            quotaDayDTO.setUsedQuotaNum(0);
                            quotaDayDTO.setAllQuotaNum(0);
                        }
                        roomQuotaDTO.setQuotaNum(quotaDayDTO.getQuotaNum());
                        roomQuotaDTO.setUsedQuotaNum(quotaDayDTO.getUsedQuotaNum());
                        roomQuotaDTO.setAllQuotaNum(quotaDayDTO.getAllQuotaNum());
                        //房型小计  每日信息
                        List<QuotaDayDTO> roomQuotaDayList = new ArrayList<>();
                        QuotaDayDTO roomQuotaDay = null;
                        for(Date saleDate : dateList){
                            stringBuilder.setLength(0);
                            stringBuilder.append(roomTypeDO.getRoomTypeId()).append(":").append(DateUtil.dateToString(saleDate));

                            roomQuotaDay = new QuotaDayDTO();
                            roomQuotaDay.setSaleDate(saleDate);
                            if(roomTypeDailyMap.containsKey(stringBuilder.toString())){
                                QuotaDayDTO q = roomTypeDailyMap.get(stringBuilder.toString());
                                roomQuotaDay.setQuotaNum(q.getQuotaNum());
                                roomQuotaDay.setUsedQuotaNum(q.getUsedQuotaNum());
                                roomQuotaDay.setAllQuotaNum(q.getAllQuotaNum());
                            }else{
                                roomQuotaDay.setQuotaNum(0);
                                roomQuotaDay.setUsedQuotaNum(0);
                                roomQuotaDay.setAllQuotaNum(0);
                            }

                            //酒店合计
                            if(hotelQuotaMap.containsKey(saleDate)){
                                QuotaDayDTO q = hotelQuotaMap.get(saleDate);
                                q.setQuotaNum(q.getQuotaNum() + roomQuotaDay.getQuotaNum());
                                q.setUsedQuotaNum(q.getUsedQuotaNum() + roomQuotaDay.getUsedQuotaNum());
                                q.setAllQuotaNum(q.getAllQuotaNum() + roomQuotaDay.getAllQuotaNum());
                                hotelQuotaMap.put(saleDate,q);
                            }else{
                                QuotaDayDTO hotelQuotaDaily = new QuotaDayDTO();
                                hotelQuotaDaily.setQuotaNum(roomQuotaDay.getQuotaNum());
                                hotelQuotaDaily.setUsedQuotaNum(roomQuotaDay.getUsedQuotaNum());
                                hotelQuotaDaily.setAllQuotaNum(roomQuotaDay.getAllQuotaNum());
                                hotelQuotaMap.put(saleDate,hotelQuotaDaily);
                            }
                            roomQuotaDayList.add(roomQuotaDay);
                        }
                        roomQuotaDTO.setRoomQuotaDayList(roomQuotaDayList);
                        roomQuotaDTOS.add(roomQuotaDTO);

                        quotaNum = quotaNum + quotaDayDTO.getQuotaNum();
                        usedQuotaNum = usedQuotaNum + quotaDayDTO.getUsedQuotaNum();
                        allQuotaNum = allQuotaNum + quotaDayDTO.getAllQuotaNum();
                    }
                //}
                hotelQuotaDTO.setRoomQuotaDTOList(roomQuotaDTOS);
                hotelQuotaDTO.setQuotaNum(quotaNum);
                hotelQuotaDTO.setUsedQuotaNum(usedQuotaNum);
                hotelQuotaDTO.setAllQuotaNum(allQuotaNum);
                List<QuotaDayDTO> hotelQuotaDayList = new ArrayList<>();
                QuotaDayDTO quotaDayDTO = null;
                for(Date saleDate : dateList){
                    if(hotelQuotaMap.containsKey(saleDate)){
                        quotaDayDTO = hotelQuotaMap.get(saleDate);
                        quotaDayDTO.setSaleDate(saleDate);
                    }else{
                        quotaDayDTO = new QuotaDayDTO();
                        quotaDayDTO.setSaleDate(saleDate);
                        quotaDayDTO.setQuotaNum(0);
                        quotaDayDTO.setUsedQuotaNum(0);
                        quotaDayDTO.setAllQuotaNum(0);
                    }
                    hotelQuotaDayList.add(quotaDayDTO);
                }
                hotelQuotaDTO.setHotelQuotaDayList(hotelQuotaDayList);
            }
            List<HotelQuotaDTO> hotelQuotaDTOS = new ArrayList<>();
            hotelQuotaDTOS.add(hotelQuotaDTO);
            paginationSupportDTO.setItemList(hotelQuotaDTOS);
        } catch (Exception e) {
            log.error("quotaReportDetail has error.",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }
}
