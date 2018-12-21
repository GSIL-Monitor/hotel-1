package com.fangcang.report.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.report.mapper.ReceivablePayableReportMapper;
import com.fangcang.report.request.PayableReportQueryDTO;
import com.fangcang.report.request.ReceivableReportQueryDTO;
import com.fangcang.report.response.PayableReportResponseDTO;
import com.fangcang.report.response.PayableSummaryDTO;
import com.fangcang.report.response.ReceivableReportResponseDTO;
import com.fangcang.report.response.ReceivableSummaryDTO;
import com.fangcang.report.service.ReceivablePayableReportService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/11
 */
@Service
@Slf4j
public class ReceivablePayableReportServiceImpl implements ReceivablePayableReportService {
    @Resource
    private ReceivablePayableReportMapper receivablePayableReportMapper;

    @Override
    public PaginationSupportDTO<ReceivableReportResponseDTO> receivableReport(ReceivableReportQueryDTO requestDTO) {
        // 1. 分页查询
        Page<ReceivableReportResponseDTO> page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                                                           .doSelectPage(() -> receivablePayableReportMapper.receivableReport(requestDTO));
        List<ReceivableReportResponseDTO> itemList = page.getResult();
        if (!CollectionUtils.isEmpty(itemList)) {
            for (ReceivableReportResponseDTO item : itemList) {
                item.setChannelName(ChannelTypeEnum.getValueByKey(item.getChannelCode()));
                item.setStartDate(requestDTO.getStartDate());
                item.setEndDate(requestDTO.getEndDate());
            }
        }

        // 2. 组装响应对象
        PaginationSupportDTO<ReceivableReportResponseDTO> pageResponse = new PaginationSupportDTO<>();
        pageResponse.setItemList(itemList);
        pageResponse.setPageSize(page.getPageSize());
        pageResponse.setTotalCount(page.getTotal());
        pageResponse.setTotalPage(page.getPages());
        pageResponse.setCurrentPage(page.getPageNum());
        return pageResponse;
    }

    @Override
    public ReceivableSummaryDTO receivableReportSum(ReceivableReportQueryDTO requestDTO) {
        // 1. 查汇总数据
        ReceivableSummaryDTO receivableSummaryDTO = receivablePayableReportMapper.receivableReportSum(requestDTO);
        if (receivableSummaryDTO == null) {
            receivableSummaryDTO = new ReceivableSummaryDTO(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return receivableSummaryDTO;
    }


    @Override
    public PaginationSupportDTO<PayableReportResponseDTO> payableReport(PayableReportQueryDTO requestDTO) {
        // 1. 分页查询
        Page<PayableReportResponseDTO> page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                                                        .doSelectPage(() -> receivablePayableReportMapper.payableReport(requestDTO));
        List<PayableReportResponseDTO> itemList = page.getResult();
        if (!CollectionUtils.isEmpty(itemList)) {
            for (PayableReportResponseDTO item : itemList) {
                item.setStartDate(requestDTO.getStartDate());
                item.setEndDate(requestDTO.getEndDate());
            }
        }

        // 2. 组装响应对象
        PaginationSupportDTO<PayableReportResponseDTO> pageResponse = new PaginationSupportDTO<>();
        pageResponse.setItemList(itemList);
        pageResponse.setPageSize(page.getPageSize());
        pageResponse.setTotalCount(page.getTotal());
        pageResponse.setTotalPage(page.getPages());
        pageResponse.setCurrentPage(page.getPageNum());
        return pageResponse;
    }

    @Override
    public PayableSummaryDTO payableReportSum(PayableReportQueryDTO requestDTO) {
        // 1. 查汇总数据
        PayableSummaryDTO payableSummaryDTO = receivablePayableReportMapper.payableReportSum(requestDTO);
        if (payableSummaryDTO == null) {
            payableSummaryDTO = new PayableSummaryDTO(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return payableSummaryDTO;
    }
}
