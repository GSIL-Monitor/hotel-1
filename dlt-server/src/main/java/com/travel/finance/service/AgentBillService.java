package com.travel.finance.service;

import java.util.List;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.query.AgentCheckOutQueryDTO;
import com.travel.common.dto.finance.query.BillOffQueryDTO;
import com.travel.common.dto.finance.request.CheckOutRequestDTO;
import com.travel.common.dto.finance.request.ReverseRequestDTO;
import com.travel.common.dto.finance.request.BillOffRequestDTO;
import com.travel.common.dto.finance.response.BillOffResponseDTO;
import com.travel.common.dto.finance.response.DayCheckOutResponseDTO;
import com.travel.common.dto.finance.response.OrgCheckOutDetailResponseDTO;
import com.travel.common.dto.finance.response.OrgCheckOutResponseDTO;

/**
 * @Description : 客户财务业务接口
 * @author : Zhiping Sun
 * @date : 2018年1月14日上午10:34:44
 */
public interface AgentBillService {
	
	/**
	 * 分页查询订单
	 * @param agentCheckOutQuery
	 * @return
	 */
	public PaginationDTO<OrgCheckOutResponseDTO> listAgentCheckOutForPage(AgentCheckOutQueryDTO agentCheckOutQuery);
	
	/**
	 * 分销商每日小结分页查询
	 * @param agentCheckOutQuery
	 * @return
	 */
	public List<DayCheckOutResponseDTO> listAgentDayCheckOutList(AgentCheckOutQueryDTO agentCheckOutQuery);
	
	/**
	 * 分销商销账分页查询
	 * @param billOffQuery
	 * @return
	 */
	public PaginationDTO<BillOffResponseDTO> listAgentBillOffForPage(BillOffQueryDTO billOffQuery);
	
	/**
	 * 查询分销商出账每日明细
	 * @param agentCheckOutQuery
	 * @return
	 */
	public List<OrgCheckOutDetailResponseDTO> listOrgCheckOutDetailList(AgentCheckOutQueryDTO agentCheckOutQuery);
	
	/**
	 * 查询分销商账单明细合并
	 * @param agentCheckOutQuery
	 * @return
	 */
	public List<OrgCheckOutDetailResponseDTO> listOrgMergeBill(AgentCheckOutQueryDTO agentCheckOutQuery);
	
	/**
	 * 客户订单对账
	 * @param billOffDetailRequestList
	 */
	public void checkOut(List<CheckOutRequestDTO> checkOutRequestList);
	
	/**
	 * 客户订单销账
	 * @param billOffRequest
	 */
	public void billOff(BillOffRequestDTO billOffRequest);
	
	/**
	 * 客户订单反销账
	 * @param
	 */
	public void reverse(ReverseRequestDTO reverseRequest);

}
