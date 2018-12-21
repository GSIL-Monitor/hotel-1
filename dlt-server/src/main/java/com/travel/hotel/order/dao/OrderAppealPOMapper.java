package com.travel.hotel.order.dao;

import com.travel.hotel.order.entity.po.OrderAppealPOExample;
import java.util.List;

import com.travel.common.dto.finance.query.OrderAppealQuery;
import com.travel.hotel.order.entity.po.OrderAppealPO;

public interface OrderAppealPOMapper {
    int deleteByPrimaryKey(Long appealId);

    int insert(OrderAppealPO record);

    int insertSelective(OrderAppealPO record);

    List<OrderAppealPO> selectByExample(OrderAppealPOExample example);

    OrderAppealPO selectByPrimaryKey(Long appealId);

    int updateByPrimaryKeySelective(OrderAppealPO record);

    int updateByPrimaryKey(OrderAppealPO record);
    
    /**
	 * 查询申诉
	 * @param orderCode
	 */
	public OrderAppealPO queryOrderAppealByOrderCode(String orderCode);
	
	/**
	 * 查询申诉集合
	 * @param orderAppealQuery
	 */
	public List<OrderAppealPO> listOrderAppeal(OrderAppealQuery orderAppealQuery);
}