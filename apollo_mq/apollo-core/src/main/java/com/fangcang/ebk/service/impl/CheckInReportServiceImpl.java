package com.fangcang.ebk.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.ebk.mapper.CheckInReportMapper;
import com.fangcang.ebk.request.QueryCheckInReportDTO;
import com.fangcang.ebk.request.QuerySupplyHotelDTO;
import com.fangcang.ebk.response.CheckInReportDTO;
import com.fangcang.ebk.response.CheckInReportSummaryDTO;
import com.fangcang.ebk.response.SupplyHotelDTO;
import com.fangcang.ebk.response.SupplyHotelResponseDTO;
import com.fangcang.ebk.service.CheckInReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckInReportServiceImpl implements CheckInReportService {

    @Autowired
    private CheckInReportMapper checkInReportMapper;

    @Override
    public CheckInReportSummaryDTO queryCheckInReport(QueryCheckInReportDTO requestDTO) {
        CheckInReportSummaryDTO paginationSupport=checkInReportMapper.queryCheckInReportSummary(requestDTO);

        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<CheckInReportDTO> list =checkInReportMapper.queryCheckInReport(requestDTO);
        PageInfo<CheckInReportDTO> page = new PageInfo<CheckInReportDTO>(list);

        if (paginationSupport==null){
            paginationSupport=new CheckInReportSummaryDTO(0,0,0, BigDecimal.ZERO);
        }
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    public ResponseDTO<SupplyHotelResponseDTO> querySupplyHotel(QuerySupplyHotelDTO requestDTO){
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<SupplyHotelDTO> list =checkInReportMapper.querySupplyHotel(requestDTO);
        PageInfo<SupplyHotelDTO> page = new PageInfo<SupplyHotelDTO>(list);

        SupplyHotelResponseDTO supplyHotelResponseDTO=new SupplyHotelResponseDTO();
        supplyHotelResponseDTO.setHotelList(list);
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(supplyHotelResponseDTO);
        return responseDTO;
    }
}
