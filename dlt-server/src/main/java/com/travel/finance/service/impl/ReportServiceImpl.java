package com.travel.finance.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.query.PayableQueryDTO;
import com.travel.common.dto.finance.query.ReceivableQueryDTO;
import com.travel.finance.dao.ReportMapper;
import com.travel.finance.entity.Payable;
import com.travel.finance.entity.Receivable;
import com.travel.finance.service.ReportService;

/**
 * @Description : 报表查询业务接口实现
 * @author : Zhiping Sun
 * @date : 2018年4月23日 上午10:37:25
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportMapper reportMapper;

	@Override
	public PaginationDTO<Receivable> searchAgentReceivableList(ReceivableQueryDTO receivableQuery) {
		PaginationDTO<Receivable> pagination = new PaginationDTO<Receivable>();
		PageHelper.startPage(receivableQuery.getCurrentPage(), receivableQuery.getPageSize());
		List<Receivable> list = this.reportMapper.queryAgentReceivableList(receivableQuery);
		PageInfo<Receivable> page = new PageInfo<Receivable>(list);
		pagination.setRecordList(page.getList());
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		return pagination;
	}
	
	@Override
	public PaginationDTO<Receivable> searchAgentReceivableProfit(ReceivableQueryDTO receivableQuery) {
		PaginationDTO<Receivable> pagination = new PaginationDTO<Receivable>();
		PageHelper.startPage(receivableQuery.getCurrentPage(), receivableQuery.getPageSize());
		List<Receivable> list = this.reportMapper.queryAgentReceivableProfit(receivableQuery);
		PageInfo<Receivable> page = new PageInfo<Receivable>(list);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (Receivable r : page.getList()) {
				if (null != r.getReceivable()) {
					r.setReceivable(r.getReceivable().setScale(2, BigDecimal.ROUND_UP));
				}
				if (null != r.getPayable()) {
					r.setPayable(r.getPayable().setScale(2, BigDecimal.ROUND_UP));
				}
				if (null != r.getProfit()) {
					r.setProfit(r.getProfit().setScale(2, BigDecimal.ROUND_UP));
				}
			}
		}
		pagination.setRecordList(page.getList());
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		return pagination;
	}

	@Override
	public PaginationDTO<Payable> searchSupplyPayableList(PayableQueryDTO payableQuery) {
		PaginationDTO<Payable> pagination = new PaginationDTO<Payable>();
		PageHelper.startPage(payableQuery.getCurrentPage(), payableQuery.getPageSize());
		List<Payable> list = this.reportMapper.querySupplyPayableList(payableQuery);
		PageInfo<Payable> page = new PageInfo<Payable>(list);
		pagination.setRecordList(page.getList());
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		return pagination;
	}

}
