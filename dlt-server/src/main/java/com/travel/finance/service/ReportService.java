package com.travel.finance.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.query.PayableQueryDTO;
import com.travel.common.dto.finance.query.ReceivableQueryDTO;
import com.travel.finance.entity.Payable;
import com.travel.finance.entity.Receivable;

/**
 * @Description : 报表查询业务接口
 * @author : Zhiping Sun
 * @date : 2018年4月23日 上午10:35:03
 */
public interface ReportService {
	
	/**
	 * 应收报表查询
	 * @param receivableQuery
	 * @return
	 */
	PaginationDTO<Receivable> searchAgentReceivableList(ReceivableQueryDTO receivableQuery);
	
	/**
	 * 应收毛利报表查询
	 * @param receivableQuery
	 * @return
	 */
	PaginationDTO<Receivable> searchAgentReceivableProfit(ReceivableQueryDTO receivableQuery);
	
	/**
	 * 应付报表查询
	 * @param payableQuery
	 * @return
	 */
	PaginationDTO<Payable> searchSupplyPayableList(PayableQueryDTO payableQuery);

}
