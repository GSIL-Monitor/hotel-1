package com.travel.finance.service;

import java.util.List;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.query.BillOffQueryDTO;
import com.travel.common.dto.finance.query.SupplyCheckOutQueryDTO;
import com.travel.common.dto.finance.request.BillOffRequestDTO;
import com.travel.common.dto.finance.request.CheckOutRequestDTO;
import com.travel.common.dto.finance.request.ReverseRequestDTO;
import com.travel.common.dto.finance.response.BillOffResponseDTO;
import com.travel.common.dto.finance.response.DayCheckOutResponseDTO;
import com.travel.common.dto.finance.response.OrgCheckOutDetailResponseDTO;
import com.travel.common.dto.finance.response.OrgCheckOutResponseDTO;

/**
 * @Description : 供应商财务业务接口
 * @author : Zhiping Sun
 * @date : 2018年1月14日上午10:34:44
 */
public interface SupplyBillService {

	/**
	 * 分页查询订单
	 * @param supplyCheckOutQuery
	 * @return
	 */
	public PaginationDTO<OrgCheckOutResponseDTO> listSupplyCheckOutForPage(SupplyCheckOutQueryDTO supplyCheckOutQuery);
	
	/**
	 * 供应商每日小结分页查询
	 * @param supplyCheckOutQuery
	 * @return
	 */
	public List<DayCheckOutResponseDTO> listSupplyDayCheckOutList(SupplyCheckOutQueryDTO supplyCheckOutQuery);
	
	/**
	 * 查询供应商出账每日明细
	 * @param supplyCheckOutQuery
	 * @return
	 */
	public List<OrgCheckOutDetailResponseDTO> listOrgCheckOutDetailList(SupplyCheckOutQueryDTO supplyCheckOutQuery);
	
	/**
	 * 供应商销账分页查询
	 * @param billOffQuery
	 * @return
	 */
	public PaginationDTO<BillOffResponseDTO> listSupplyBillOffForPage(BillOffQueryDTO billOffQuery);
	
	/**
	 * 供应商订单对账
	 * @param billRequestList
	 */
	public void checkOut(List<CheckOutRequestDTO> billOffDetailRequestList);
	
	/**
	 * 供应商订单销账
	 * @param billRequest
	 */
	public void billOff(BillOffRequestDTO billOffRequest);
	
	/**
	 * 供应商订单反销账
	 * @param reverseRequest
	 */
	public void reverse(ReverseRequestDTO reverseRequest);
}
