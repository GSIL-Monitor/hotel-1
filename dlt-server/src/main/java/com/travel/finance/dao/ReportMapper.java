package com.travel.finance.dao;

import java.util.List;

import com.travel.common.dto.finance.query.PayableQueryDTO;
import com.travel.common.dto.finance.query.ReceivableQueryDTO;
import com.travel.finance.entity.Payable;
import com.travel.finance.entity.Receivable;

/**
 * @Description : 报表接口
 * @author : Zhiping Sun
 * @date : 2018年4月17日上午10:12:14
 */
public interface ReportMapper {
	
	/**
	 * 应收报表查询
	 * @param receivableQuery
	 * @return
	 */
	List<Receivable> queryAgentReceivableList(ReceivableQueryDTO receivableQuery);
	
	/**
	 * 应收毛利报表查询
	 * @param receivableQuery
	 * @return
	 */
	List<Receivable> queryAgentReceivableProfit(ReceivableQueryDTO receivableQuery);
	
	/**
	 * 应付报表查询
	 * @param payableQuery
	 * @return
	 */
	List<Payable> querySupplyPayableList(PayableQueryDTO payableQuery);

}
