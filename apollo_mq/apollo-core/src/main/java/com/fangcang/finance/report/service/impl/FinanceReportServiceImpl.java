package com.fangcang.finance.report.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.finance.report.mapper.FinanceReportMapper;
import com.fangcang.finance.report.request.QueryCheckReportDTO;
import com.fangcang.finance.report.response.OrderCheckDTO;
import com.fangcang.finance.report.response.SupplyOrderCheckDTO;
import com.fangcang.finance.report.service.FinanceReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceReportServiceImpl implements FinanceReportService {

    @Autowired
    private FinanceReportMapper financeReportMapper;

    /**
     * 分销商对账结算明细
     * @param request
     * @return
     */
    @Override
    public PaginationSupportDTO<OrderCheckDTO> queryAgentCheckReport(QueryCheckReportDTO request){
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<OrderCheckDTO> list =financeReportMapper.queryAgentCheckReport(request);
        PageInfo<OrderCheckDTO> page = new PageInfo<OrderCheckDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    /**
     * 供应商对账结算明细
     * @param request
     * @return
     */
    @Override
    public PaginationSupportDTO<SupplyOrderCheckDTO> querySupplyCheckReport(QueryCheckReportDTO request){
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<SupplyOrderCheckDTO> list =financeReportMapper.querySupplyCheckReport(request);
        PageInfo<SupplyOrderCheckDTO> page = new PageInfo<SupplyOrderCheckDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }
}
