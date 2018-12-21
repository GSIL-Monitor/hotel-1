package com.travel.finance.dao;

import java.util.List;

import com.travel.common.dto.finance.query.SupplyCheckOutQueryDTO;
import com.travel.finance.entity.DayCheckOut;
import com.travel.finance.entity.OrgCheckOut;
import com.travel.finance.entity.OrgCheckOutDetail;

/**
 * @Description : 供应商出账数据接口
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午4:36:29
 */
public interface SupplyCheckOutMapper {
	
	/**
	 * 供应商出账汇总
	 * @param supplyCheckOutQuery
	 * @return
	 */
	public List<OrgCheckOut> querySupplyCheckOutList(SupplyCheckOutQueryDTO supplyCheckOutQuery);
	
	/**
	 * 供应商每日小结查询
	 * @param supplyCheckOutQuery
	 * @return
	 */
	public List<DayCheckOut> querySupplyDayCheckOutList(SupplyCheckOutQueryDTO supplyCheckOutQuery);
	
	/**
	 * 供应商每日小结明细查询
	 * @param supplyCheckOutQuery
	 * @return
	 */
	public List<OrgCheckOutDetail> querySupplyDayCheckOutDetailList(SupplyCheckOutQueryDTO supplyCheckOutQuery);

}
