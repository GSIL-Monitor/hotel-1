package com.travel.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.query.OrderAppealQuery;
import com.travel.common.dto.finance.request.OrderAppealRequestDTO;
import com.travel.common.dto.finance.response.OrderAppealResponseDTO;
import com.travel.common.enums.AppealStatusEnum;
import com.travel.common.exception.ServiceException;
import com.travel.finance.service.OrderAppealService;
import com.travel.hotel.order.dao.OrderAppealPOMapper;
import com.travel.hotel.order.entity.po.OrderAppealPO;

/**
 * @Description : 订单申诉业务接口实现
 * @author : Zhiping Sun
 * @date : 2018年1月17日下午4:00:27
 */
@Service("orderAppealService")
public class OrderAppealServiceImpl implements OrderAppealService {
	
	@Autowired
	private OrderAppealPOMapper orderAppealPOMapper;

	@Override
	public Long saveOrderAppeal(OrderAppealRequestDTO orderAppeal) {
		OrderAppealPO oa = this.orderAppealPOMapper.queryOrderAppealByOrderCode(orderAppeal.getOrderCode());
		if (null != oa) {
			if (oa.getStatus().equals(AppealStatusEnum.SUCCESS.code)) {
				throw new ServiceException("当前订单已申诉成功");
			} else if (oa.getStatus().equals(AppealStatusEnum.PROCESSING.code)) {
				throw new ServiceException("当前订单已在申诉中");
			} else if (oa.getStatus().equals(AppealStatusEnum.NEW.code)) {
				throw new ServiceException("当前订单已发起申诉");
			}
		}
		OrderAppealPO oal = new OrderAppealPO();
		BeanUtils.copyProperties(orderAppeal, oal);
		oal.setComplainant(orderAppeal.getCreator());
		this.orderAppealPOMapper.insert(oal);
		return oal.getAppealId();
	}

	@Override
	public void updateOrderAppeal(OrderAppealRequestDTO orderAppeal) {
		OrderAppealPO oa = this.orderAppealPOMapper.selectByPrimaryKey(orderAppeal.getAppealId());
		if (null != oa) {
			if (oa.getStatus().equals(AppealStatusEnum.SUCCESS.code)) {
				throw new ServiceException("当前订单已申诉成功");
			}
		} else {
			throw new ServiceException("当前订单无申诉记录");
		}
		BeanUtils.copyProperties(orderAppeal, oa);
		this.orderAppealPOMapper.updateByPrimaryKeySelective(oa);
	}

	@Override
	public void deleteOrderAppeal(Long appealId) {
		OrderAppealPO oa = this.orderAppealPOMapper.selectByPrimaryKey(appealId);
		if (null == oa) {
			throw new ServiceException("当前订单无申诉记录");
		}
		this.orderAppealPOMapper.deleteByPrimaryKey(appealId);
	}

	@Override
	public OrderAppealResponseDTO queryOrderAppealById(Long appealId) {
		OrderAppealResponseDTO orderAppeal = new OrderAppealResponseDTO();
		OrderAppealPO oa = this.orderAppealPOMapper.selectByPrimaryKey(appealId);
		if (null == oa) {
			return null;
		}
		BeanUtils.copyProperties(oa, orderAppeal);
		if(StringUtils.isNotBlank(orderAppeal.getStatus())) {
			orderAppeal.setStatusText(AppealStatusEnum.getDescByCode(orderAppeal.getStatus()));
		}
		return orderAppeal;
	}

	@Override
	public PaginationDTO<OrderAppealResponseDTO> listOrderAppealForPage(OrderAppealQuery orderAppealQuery) {
		PaginationDTO<OrderAppealResponseDTO> pagination = new PaginationDTO<OrderAppealResponseDTO>();
		OrderAppealResponseDTO oar = null;
		List<OrderAppealResponseDTO> recordList = new ArrayList<OrderAppealResponseDTO>();
		PageHelper.startPage(orderAppealQuery.getCurrentPage(), orderAppealQuery.getPageSize());
		List<OrderAppealPO> list = this.orderAppealPOMapper.listOrderAppeal(orderAppealQuery);
		PageInfo<OrderAppealPO> page = new PageInfo<OrderAppealPO>(list);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (OrderAppealPO oa : page.getList()) {
				oar = new OrderAppealResponseDTO();
				BeanUtils.copyProperties(oa, oar);
				if (null != oar.getStatus()) {
					oar.setStatusText(AppealStatusEnum.getDescByCode(oar.getStatus()));
				}
				recordList.add(oar);
			}
		}
		pagination.setRecordList(recordList);
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		return pagination;
	}

}
