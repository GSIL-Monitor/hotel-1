package com.fangcang.report.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.mapper.OrderDetailReportMapper;
import com.fangcang.report.request.QueryOrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailSummaryDTO;
import com.fangcang.report.service.OrderDetailReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailReportServiceImpl implements OrderDetailReportService {

    @Autowired
    private OrderDetailReportMapper orderDetailReportMapper;

    @Override
    public PaginationSupportDTO<OrderDetailReportDTO> queryOrderDetail(QueryOrderDetailReportDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<OrderDetailReportDTO> list =orderDetailReportMapper.queryOrderDetail(requestDTO);
        PageInfo<OrderDetailReportDTO> page = new PageInfo<OrderDetailReportDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public OrderDetailSummaryDTO queryOrderDetailSummary(QueryOrderDetailReportDTO requestDTO) {
        return orderDetailReportMapper.queryOrderDetailSummary(requestDTO);
    }
}
