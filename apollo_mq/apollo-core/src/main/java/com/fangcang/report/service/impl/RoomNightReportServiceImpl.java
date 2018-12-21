package com.fangcang.report.service.impl;

import com.fangcang.common.util.DateUtil;
import com.fangcang.report.mapper.RoomNightReportMapper;
import com.fangcang.report.request.QueryRoomNightReportDTO;
import com.fangcang.report.response.RoomNightDailyReportDTO;
import com.fangcang.report.response.RoomNightReportDTO;
import com.fangcang.report.response.RoomNightSummaryDTO;
import com.fangcang.report.service.RoomNightReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomNightReportServiceImpl implements RoomNightReportService {

    @Autowired
    private RoomNightReportMapper roomNightReportMapper;

    @Override
    public RoomNightSummaryDTO queryRoomNightReport(QueryRoomNightReportDTO requestDTO) {
        RoomNightSummaryDTO paginationSupport=roomNightReportMapper.queryRoomNightSummary(requestDTO);

        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<RoomNightReportDTO> list =roomNightReportMapper.queryRoomNightReport(requestDTO);
        PageInfo<RoomNightReportDTO> page = new PageInfo<RoomNightReportDTO>(list);

        if (paginationSupport==null){
            paginationSupport=new RoomNightSummaryDTO(0,0,0, BigDecimal.ZERO);
        }
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());

        if (list.size()>0){
            //查询每日统计
            requestDTO.setHotelId(null);
            requestDTO.setHotelName(null);
            Date beginDate= DateUtil.stringToDate(requestDTO.getBeginDate());
            Date endDate=DateUtil.stringToDate(requestDTO.getEndDate());
            Long day=DateUtil.getDay(beginDate,endDate)+1;

            Map<Integer,Map<String,RoomNightDailyReportDTO>> roomNightMap=new HashMap<>();
            //按1000条分组，因为in只支持1000个
            int size=list.size()%1000==0?list.size()/1000:list.size()/1000+1;
            for(int i=0;i<size;i++){
                List<RoomNightReportDTO> curlist;
                if(i==list.size()/1000){
                    curlist=list.subList(i*1000, i*1000+list.size()%1000);
                }else{
                    curlist=list.subList(i*1000, (i+1)*1000);
                }
                List<Integer> hotelIdList=new ArrayList<>();
                for (RoomNightReportDTO roomNightReportDTO:curlist){
                    if (roomNightReportDTO.getRoomNight()==null){
                        roomNightReportDTO.setRoomNight(0);
                        roomNightReportDTO.setGrouponRoomNight(0);
                        roomNightReportDTO.setLooseRoomNight(0);
                    }
                    if (roomNightReportDTO.getRoomNight()>0){
                        hotelIdList.add(roomNightReportDTO.getHotelId());
                    }

                    Map<String,RoomNightDailyReportDTO> roomNightDailyMap=new HashMap<>();
                    for (int j=0;j<day;j++) {
                        String curDate = DateUtil.dateToString(DateUtil.getDate(beginDate, j, 0));
                        RoomNightDailyReportDTO roomNightDailyReportDTO=new RoomNightDailyReportDTO();
                        roomNightDailyReportDTO.setSaleDate(curDate);
                        roomNightDailyReportDTO.setRoomNight(0);
                        roomNightDailyMap.put(curDate,roomNightDailyReportDTO);
                    }
                    roomNightMap.put(roomNightReportDTO.getHotelId(),roomNightDailyMap);
                    roomNightReportDTO.setDailyList(new ArrayList<>(roomNightDailyMap.values()));
                    Collections.sort(roomNightReportDTO.getDailyList(),new Comparator<RoomNightDailyReportDTO>() {
                        @Override
                        public int compare(RoomNightDailyReportDTO item1, RoomNightDailyReportDTO item2) {
                            // 返回值为int类型，大于0表示正序，小于0表示逆序
                            return (int)DateUtil.getDay(DateUtil.stringToDate(item2.getSaleDate()),DateUtil.stringToDate(item1.getSaleDate()));
                        }
                    });
                }
                requestDTO.setHotelIdList(hotelIdList);
                if (hotelIdList.size()>0){
                    List<RoomNightDailyReportDTO> dailyList=roomNightReportMapper.queryRoomNightDailyReport(requestDTO);
                    for (RoomNightDailyReportDTO dailyDTO:dailyList){
                        RoomNightDailyReportDTO roomNightDailyReportDTO=roomNightMap.get(dailyDTO.getHotelId()).get(dailyDTO.getSaleDate());
                        roomNightDailyReportDTO.setRoomNight(dailyDTO.getRoomNight());
                        roomNightDailyReportDTO.setLooseRoomNight(dailyDTO.getLooseRoomNight());
                        roomNightDailyReportDTO.setGrouponRoomNight(dailyDTO.getGrouponRoomNight());
                    }
                }
            }
        }
        return paginationSupport;
    }
}
