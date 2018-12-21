package com.fangcang.report.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.mapper.OrderExceptionReportMapper;
import com.fangcang.report.request.QueryOrderExceptionDTO;
import com.fangcang.report.response.OrderExceptionDTO;
import com.fangcang.report.service.OrderExceptionReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderExceptionReportServiceImpl implements OrderExceptionReportService {

    @Autowired
    private OrderExceptionReportMapper orderExceptionReportMapper;

    @Override
    public PaginationSupportDTO<OrderExceptionDTO> queryOrderException(QueryOrderExceptionDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<OrderExceptionDTO> list =orderExceptionReportMapper.queryOrderException(requestDTO);
        PageInfo<OrderExceptionDTO> page = new PageInfo<OrderExceptionDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }
}
