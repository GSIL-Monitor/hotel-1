package com.fangcang.report.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.report.mapper.PrepayContractReportMapper;
import com.fangcang.report.request.PrepayContractReportQueryDTO;
import com.fangcang.report.response.PrepayContractReportResponseDTO;
import com.fangcang.report.service.PrepayContractReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/11
 */
@Service
@Slf4j
public class PrepayContractReportServiceImpl implements PrepayContractReportService {
    @Resource
    private PrepayContractReportMapper prepayContractReportMapper;

    @Override
    public PaginationSupportDTO<PrepayContractReportResponseDTO> prepayContractReport(PrepayContractReportQueryDTO requestDTO) {
        log.info("prepayContractReport param: {}", requestDTO);
        // 1. 分页查询预付款报表
        if (StringUtils.isNotEmpty(requestDTO.getDate())) {
            String[] dateArr = requestDTO.getDate().split("/");
            requestDTO.setYear(dateArr[0]);
            int month = Integer.parseInt(dateArr[1]);
            requestDTO.setMonth(month + "");
        }
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<PrepayContractReportResponseDTO> list = prepayContractReportMapper.prepayContractReport(requestDTO);
        PageInfo<PrepayContractReportResponseDTO> page = new PageInfo<>(list);

        PaginationSupportDTO<PrepayContractReportResponseDTO> paginationSupport = new PaginationSupportDTO<>();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }
}
