package com.travel.finance.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.query.OrderAppealQuery;
import com.travel.common.dto.finance.request.OrderAppealRequestDTO;
import com.travel.common.dto.finance.response.OrderAppealResponseDTO;

/**
 * @Description : 订单申诉业务接口
 * @author : Zhiping Sun
 * @date : 2018年1月17日下午3:52:31
 */
public interface OrderAppealService {

	/**
	 * 保存申诉
	 * @param orderAppeal
	 */
	public Long saveOrderAppeal(OrderAppealRequestDTO orderAppeal);
	
	/**
	 * 修改申诉
	 * @param orderAppeal
	 */
	public void updateOrderAppeal(OrderAppealRequestDTO orderAppeal);
	
	/**
	 * 删除申诉
	 * @param appealId
	 */
	public void deleteOrderAppeal(Long appealId);
	
	/**
	 * 查询申诉
	 * @param appealId
	 */
	public OrderAppealResponseDTO queryOrderAppealById(Long appealId);
	
	/**
	 * 查询申诉集合
	 * @param orderAppealQuery
	 */
	public PaginationDTO<OrderAppealResponseDTO> listOrderAppealForPage(OrderAppealQuery orderAppealQuery);
}
