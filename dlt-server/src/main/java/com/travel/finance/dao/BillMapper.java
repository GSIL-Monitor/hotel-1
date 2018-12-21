package com.travel.finance.dao;

import java.util.List;

import com.travel.common.dto.finance.query.BillOffQueryDTO;
import com.travel.finance.entity.BillOff;
import com.travel.finance.entity.BillOffDetail;
import com.travel.finance.entity.CreditItem;
import com.travel.finance.entity.OrgCheckOutDetail;
import com.travel.finance.entity.ReverseBillOff;

/**
 * @Description : 对账数据操作接口
 * @author : Zhiping Sun
 * @date : 2018年1月17日上午10:12:14
 */
public interface BillMapper {

	/**
	 * 保存挂账记录
	 * @param credit
	 */
	public void saveCreditItem(CreditItem credit);
	
	/**
	 * 销账记录查询
	 * @param billOffQuery
	 * @return
	 */
	public List<BillOff> queryBillOffList(BillOffQueryDTO billOffQuery);
	
	/**
	 * 批量保存对账记录
	 * @param detailList
	 */
	public void batchSaveCheckOut(List<OrgCheckOutDetail> detailList);
	
	/**
	 * 保存销账
	 * @param billOff
	 */
	public void saveBillOff(BillOff billOff);
	
	/**
	 * 修改销账
	 * @param billOff
	 */
	public void updateBillOff(BillOff billOff);
	
	/**
	 * 批量保存销账记录
	 * @param detailList
	 */
	public void batchSaveBillOffDetail(List<BillOffDetail> detailList);
	
	/**
	 * 查询销账明细
	 * @param billOffIdList
	 * @return
	 */
	public List<BillOffDetail> queryBillOffDetailList(List<Long> billOffIdList);
	
	/**
	 * 保存反核销
	 * @param reverseBillOff
	 */
	public void saveReverse(ReverseBillOff reverseBillOff);
}
