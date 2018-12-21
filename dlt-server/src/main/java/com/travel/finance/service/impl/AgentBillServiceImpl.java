package com.travel.finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.query.AgentCheckOutQueryDTO;
import com.travel.common.dto.finance.query.BillOffQueryDTO;
import com.travel.common.dto.finance.request.BillOffRequestDTO;
import com.travel.common.dto.finance.request.CheckOutRequestDTO;
import com.travel.common.dto.finance.request.ReverseRequestDTO;
import com.travel.common.dto.finance.response.BillOffResponseDTO;
import com.travel.common.dto.finance.response.DayCheckOutResponseDTO;
import com.travel.common.dto.finance.response.OrgCheckOutDetailResponseDTO;
import com.travel.common.dto.finance.response.OrgCheckOutResponseDTO;
import com.travel.common.enums.ActiveEnum;
import com.travel.common.enums.BillStatusEnum;
import com.travel.common.enums.FinanceLockEnum;
import com.travel.common.enums.OrderStateEnum;
import com.travel.common.enums.PayMethodEnum;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.DateUtil;
import com.travel.finance.dao.AgentCheckOutMapper;
import com.travel.finance.dao.BillMapper;
import com.travel.finance.entity.BillOff;
import com.travel.finance.entity.BillOffDetail;
import com.travel.finance.entity.DayCheckOut;
import com.travel.finance.entity.OrgCheckOut;
import com.travel.finance.entity.OrgCheckOutDetail;
import com.travel.finance.entity.ReverseBillOff;
import com.travel.finance.service.AgentBillService;
import com.travel.hotel.order.dao.OrderDayPricePOMapper;
import com.travel.hotel.order.dao.OrderPOMapper;
import com.travel.hotel.order.entity.po.OrderDayPricePO;
import com.travel.hotel.order.entity.po.OrderPO;

/**
 * @Description : 客户财务接口实现
 * @author : Zhiping Sun
 * @date : 2018年1月14日上午10:35:56
 */
@Service("agentBillService")
public class AgentBillServiceImpl implements AgentBillService {
	
	private static Logger logger = LoggerFactory.getLogger(AgentBillServiceImpl.class);
	
	@Autowired
	private AgentCheckOutMapper agentCheckOutMapper;
	
	@Autowired
	private BillMapper billMapper;
	
	@Autowired
	private OrderDayPricePOMapper orderDayPricePOMapper;
	
	@Autowired
	private OrderPOMapper orderPOMapper;
	
	@Override
	public PaginationDTO<OrgCheckOutResponseDTO> listAgentCheckOutForPage(AgentCheckOutQueryDTO agentCheckOutQuery) {
		PaginationDTO<OrgCheckOutResponseDTO> pagination = new PaginationDTO<OrgCheckOutResponseDTO>();
		OrgCheckOutResponseDTO oco = null;
		List<OrgCheckOutResponseDTO> recordList = new ArrayList<OrgCheckOutResponseDTO>();
		PageHelper.startPage(agentCheckOutQuery.getCurrentPage(), agentCheckOutQuery.getPageSize());
		List<OrgCheckOut> list = this.agentCheckOutMapper.queryAgentCheckOutList(agentCheckOutQuery);
		PageInfo<OrgCheckOut> page = new PageInfo<OrgCheckOut>(list);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (OrgCheckOut oc : page.getList()) {
				oco = new OrgCheckOutResponseDTO();
				BeanUtils.copyProperties(oc, oco);
				recordList.add(oco);
			}
		}
		pagination.setRecordList(recordList);
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		return pagination;
	}
	
	@Override
	public List<DayCheckOutResponseDTO> listAgentDayCheckOutList(AgentCheckOutQueryDTO agentCheckOutQuery) {
		DayCheckOutResponseDTO dco = null;
		List<DayCheckOutResponseDTO> recordList = new ArrayList<DayCheckOutResponseDTO>();
		List<DayCheckOut> list = this.agentCheckOutMapper.queryAgentDayCheckOutList(agentCheckOutQuery);
		if (CollectionUtils.isNotEmpty(list)) {
			Map<String,List<OrgCheckOutDetailResponseDTO>> map = this.getOrgCheckOutDetailForMap(agentCheckOutQuery);
			for (DayCheckOut dc : list) {
				Date saleDate = DateUtil.stringToDate(dc.getSaleDate());
				Date checkOutDate = DateUtil.getDate(saleDate, 1, 0);
				dco = new DayCheckOutResponseDTO();
				BeanUtils.copyProperties(dc, dco);
				dco.setCheckInDate(dc.getSaleDate());
				dco.setCheckOutDate(DateUtil.dateToString(checkOutDate));
				dco.setOrgCheckOutDetailList(map.get(dc.getSaleDate()));
				recordList.add(dco);
			}
		}
		return recordList;
	}
	
	/**
	 * 查询每日小结明细
	 * @param agentCheckOutQuery
	 * @return Map key:售卖日期; value:当前日期售卖信息集合
	 */
	private Map<String,List<OrgCheckOutDetailResponseDTO>> getOrgCheckOutDetailForMap(AgentCheckOutQueryDTO agentCheckOutQuery) {
		Map<String,List<OrgCheckOutDetailResponseDTO>> map = new ConcurrentHashMap<String,List<OrgCheckOutDetailResponseDTO>>();
		OrgCheckOutDetailResponseDTO oco = null;
		List<OrgCheckOutDetail> detailList = this.agentCheckOutMapper.queryAgentDayCheckOutDetailList(agentCheckOutQuery);
		if (CollectionUtils.isNotEmpty(detailList)) {
			for (OrgCheckOutDetail oc : detailList) {
				oco = new OrgCheckOutDetailResponseDTO();
				Date saleDate = DateUtil.stringToDate(oc.getCheckInDate());
				Date checkOutDate = DateUtil.getDate(saleDate, 1, 0);
				oc.setCheckOutDate(DateUtil.dateToString(checkOutDate));
				BigDecimal receivable = oc.getReceivable() == null ? BigDecimal.ZERO : oc.getReceivable();
				BigDecimal actualReceived = oc.getActualReceived() == null ? BigDecimal.ZERO : oc.getActualReceived();
				oc.setUnreceivable(receivable.subtract(actualReceived));
				BeanUtils.copyProperties(oc, oco);
				if (StringUtils.isNoneBlank(oc.getOrderState())) {
					oco.setOrderStateText(OrderStateEnum.getStateByCode(oc.getOrderState()));
				}
				if (map.get(oc.getCheckInDate()) == null) {
					map.put(oc.getCheckInDate(), new ArrayList<OrgCheckOutDetailResponseDTO>());
				}
				map.get(oc.getCheckInDate()).add(oco);
			}
		}
		return map;
	}
	
	@Override
	@Transactional
	public void checkOut(List<CheckOutRequestDTO> checkOutRequestList) {
		AgentCheckOutQueryDTO agentCheckOutQuery = new AgentCheckOutQueryDTO();
		List<Long> dayPriceIdList = new ArrayList<Long>();
		Map<Long,CheckOutRequestDTO> map = new ConcurrentHashMap<Long,CheckOutRequestDTO>();
		for (CheckOutRequestDTO br : checkOutRequestList) {
			dayPriceIdList.add(br.getDayPriceId());
			map.put(br.getDayPriceId(), br);
		}
		agentCheckOutQuery.setDayPriceIdList(dayPriceIdList);
		List<OrgCheckOutDetail> detailList = this.agentCheckOutMapper.queryAgentDayCheckOutDetailList(agentCheckOutQuery);
		if (CollectionUtils.isEmpty(detailList)) {
			throw new ServiceException("当前入住明细不存在");
		}
		OrderDayPricePO od = null;
		List<String> orderCodeList = new ArrayList<String>();
		String creator = "";
		for (OrgCheckOutDetail ocd : detailList) {
			int billStatus = ocd.getOrderBillStatus() == null ? 0 : ocd.getOrderBillStatus();
			if (billStatus == BillStatusEnum.CHECKOUTED.key) {
				throw new ServiceException("当前入住明细已对账");
			}
			if (billStatus == BillStatusEnum.BILLOFF.key) {
				throw new ServiceException("当前入住明细已销账");
			}
			if (null != map.get(ocd.getDayPriceId())) {
				ocd.setCurrentActualReceived(map.get(ocd.getDayPriceId()).getCurrentActualReceived());
				ocd.setCreator(map.get(ocd.getDayPriceId()).getCreator());
			}
			if (!orderCodeList.contains(ocd.getOrderCode())) {
				orderCodeList.add(ocd.getOrderCode());
			}
			od = new OrderDayPricePO();
			od.setDayPriceId(ocd.getDayPriceId());
			od.setOrderBillStatus(BillStatusEnum.CHECKOUTED.key);
			od.setOrderBillUser(map.get(ocd.getDayPriceId()).getCreator());
			od.setModifier(map.get(ocd.getDayPriceId()).getCreator());
			od.setModifyDate(new Date());
			creator = map.get(ocd.getDayPriceId()).getCreator();
			this.orderDayPricePOMapper.updateByPrimaryKeySelective(od);
		}
		OrderPO o = null;
		List<OrderPO> orderList = new ArrayList<OrderPO>();
		for (String orderCode : orderCodeList) {
			o = new OrderPO();
			o.setOrderCode(orderCode);
			o.setFinanceLockStatus(FinanceLockEnum.LOCKED.key);
			o.setFinanceLocker(creator);
			o.setModifier(creator);
			orderList.add(o);
		}
		if (orderList.size() > 0) {
			this.orderPOMapper.batchLockOrder(orderList);
		}
		this.billMapper.batchSaveCheckOut(detailList);
	}

	@Override
	@Transactional
	public void billOff(BillOffRequestDTO billOffRequest) {
		BillOff bo = new BillOff();
		bo.setOrgCode(billOffRequest.getOrgCode());
		bo.setOrgName(billOffRequest.getOrgName());
		bo.setBillOffAmount(billOffRequest.getBillOffAmount());
		bo.setBankNo(billOffRequest.getBankNo());
		bo.setBankName(billOffRequest.getBankName());
		bo.setReceivable(billOffRequest.getReceivable());
		bo.setSerialNumber(billOffRequest.getSerialNumber());
		bo.setRemark(billOffRequest.getRemark());
		//状态默认为未反核销
		bo.setState(ActiveEnum.INVALID.key);
		bo.setCreator(billOffRequest.getCreator());
		this.billMapper.saveBillOff(bo);
		if (CollectionUtils.isNotEmpty(billOffRequest.getDayPriceIdList())) {
			AgentCheckOutQueryDTO agentCheckOutQuery = new AgentCheckOutQueryDTO();
			agentCheckOutQuery.setDayPriceIdList(billOffRequest.getDayPriceIdList());
			List<OrgCheckOutDetail> detailList = this.agentCheckOutMapper.queryAgentDayCheckOutDetailList(agentCheckOutQuery);
			if (CollectionUtils.isNotEmpty(detailList)) {
				BillOffDetail billOffDetail = null;
				OrderDayPricePO od = null;
				List<BillOffDetail> billOffDetailList = new ArrayList<BillOffDetail>();
				List<OrderDayPricePO> updateList = new ArrayList<OrderDayPricePO>();
				Map<String, List<OrgCheckOutDetail>> map = new ConcurrentHashMap<String, List<OrgCheckOutDetail>>();
				for (OrgCheckOutDetail oco : detailList) {
					billOffDetail = new BillOffDetail();
					od = new OrderDayPricePO();
					od.setDayPriceId(oco.getDayPriceId());
					BigDecimal received = oco.getActualReceived() == null ? BigDecimal.ZERO : oco.getActualReceived();
					BigDecimal tmp = oco.getCurrentActualReceived() == null ? BigDecimal.ZERO : oco.getCurrentActualReceived();
					od.setActualReceived(received.add(tmp));
					if (oco.getReceivable().compareTo(od.getActualReceived()) == 0) {
						od.setOrderBillStatus(BillStatusEnum.BILLOFF.key);
						od.setOrderBillUser(billOffRequest.getCreator());
					}
					updateList.add(od);
					billOffDetail.setBillOffId(bo.getBillOffId());
					billOffDetail.setCurrentActualReceived(oco.getCurrentActualReceived());
					billOffDetail.setActualReceived(oco.getActualReceived());
					billOffDetail.setCreator(billOffRequest.getCreator());
					billOffDetail.setDayPriceId(oco.getDayPriceId());
					billOffDetail.setOrderCode(oco.getOrderCode());
					billOffDetail.setOrgCode(billOffRequest.getOrgCode());
					billOffDetail.setOrgName(billOffRequest.getOrgName());
					billOffDetail.setReceivable(oco.getReceivable());
					billOffDetailList.add(billOffDetail);
					if (null == map.get(oco.getOrderCode())) {
						map.put(oco.getOrderCode(), new ArrayList<OrgCheckOutDetail>());
					}
					map.get(oco.getOrderCode()).add(oco);
				}
				if (billOffDetailList.size() > 0) {
					this.billMapper.batchSaveBillOffDetail(billOffDetailList);
				}
				if (updateList.size() > 0) {
					for (OrderDayPricePO o : updateList) {
						this.orderDayPricePOMapper.updateByPrimaryKeySelective(o);
					}
				}
				for (Map.Entry<String, List<OrgCheckOutDetail>> entry : map.entrySet()) {
					BigDecimal tmp = BigDecimal.ZERO;
					for (OrgCheckOutDetail oc : entry.getValue()) {
						tmp = tmp.add(oc.getCurrentActualReceived() == null ? BigDecimal.ZERO : oc.getCurrentActualReceived());
					}
					OrderPO order = this.orderPOMapper.selectByOrderCode(entry.getKey());
					if (null != order) {
						BigDecimal received = order.getActualReceived() == null ? BigDecimal.ZERO : order.getActualReceived();
						OrderPO orderPO = new OrderPO();
						orderPO.setOrderId(order.getOrderId());
						orderPO.setActualReceived(received.add(tmp));
						this.orderPOMapper.updateByPrimaryKeySelective(orderPO);
					}
				}
			}
		}
	}

	@Override
	@Transactional
	public void reverse(ReverseRequestDTO reverseRequest) {
		BillOffQueryDTO billOffQuery = new BillOffQueryDTO();
		billOffQuery.setBillOffId(reverseRequest.getBillOffId());
		billOffQuery.setOrgType(reverseRequest.getOrgType());
		List<BillOff> billOffList = this.billMapper.queryBillOffList(billOffQuery);
		if (CollectionUtils.isEmpty(billOffList)) {
			throw new ServiceException("销账记录不存在");
		}
		if (billOffList.size() != 1) {
			logger.error("预期返回一条记录，实际返回多条记录");
			throw new ServiceException("当前销账记录反核销异常");
		}
		BillOff billOff = billOffList.get(0);
		if (billOff.getState() == ActiveEnum.ACTIVE.key) {
			throw new ServiceException("当前销账记录已反核销");
		}
		//查询销账记录明细中本次收金额
		List<BillOffDetail> list = this.billMapper.queryBillOffDetailList(Arrays.asList(reverseRequest.getBillOffId()));
		if (CollectionUtils.isNotEmpty(list)) {
			List<Long> dayPriceIdList = new ArrayList<Long>();
			Map<Long,BillOffDetail> map = new ConcurrentHashMap<Long, BillOffDetail>();
			Map<String,List<BillOffDetail>> billOffDetailMap = new ConcurrentHashMap<String, List<BillOffDetail>>();
			OrderDayPricePO odp = null;
			for (BillOffDetail bod : list) {
				dayPriceIdList.add(bod.getDayPriceId());
				map.put(bod.getDayPriceId(), bod);
				if (null == billOffDetailMap.get(bod.getOrderCode())) {
					billOffDetailMap.put(bod.getOrderCode(), new ArrayList<BillOffDetail>());
				}
				billOffDetailMap.get(bod.getOrderCode()).add(bod);
			}
			//查询订单每日售卖的已收金额
			List<OrderDayPricePO> orderDayPriceList = this.orderDayPricePOMapper.selectByPrimaryKeyList(dayPriceIdList);
			if (CollectionUtils.isNotEmpty(orderDayPriceList)) {
				for (OrderDayPricePO od : orderDayPriceList) {
					BigDecimal tmp = od.getActualReceived() == null ? BigDecimal.ZERO : od.getActualReceived();
					BigDecimal actualReceived = BigDecimal.ZERO;
					BillOffDetail billOffDetail = map.get(od.getDayPriceId());
					if (null == billOffDetail) {
						actualReceived = BigDecimal.ZERO;
					} else {
						actualReceived = billOffDetail.getCurrentActualReceived() == null ? BigDecimal.ZERO : billOffDetail.getCurrentActualReceived();
					}
					odp = new OrderDayPricePO();
					odp.setDayPriceId(od.getDayPriceId());
					odp.setActualReceived(tmp.subtract(actualReceived));
					//对账状态改为“已对账”
					odp.setOrderBillStatus(BillStatusEnum.CHECKOUTED.key);
					//更新订单每日售卖已收金额
					this.orderDayPricePOMapper.updateByPrimaryKeySelective(odp);
				}
			}
			//更新订单的已收金额
			for (Map.Entry<String, List<BillOffDetail>> entry : billOffDetailMap.entrySet()) {
				OrderPO order = this.orderPOMapper.selectByOrderCode(entry.getKey());
				BigDecimal tmp = order.getActualReceived() == null ? BigDecimal.ZERO : order.getActualReceived();
				BigDecimal actualReceived = BigDecimal.ZERO;
				for (BillOffDetail bo : entry.getValue()) {
					actualReceived = actualReceived.add(bo.getCurrentActualReceived() == null ? BigDecimal.ZERO : bo.getCurrentActualReceived());
				}
				order.setActualReceived(tmp.subtract(actualReceived));
				this.orderPOMapper.updateByPrimaryKeySelective(order);
			}
			ReverseBillOff rev = new ReverseBillOff();
			rev.setBillOffId(reverseRequest.getBillOffId());
			rev.setOrgCode(billOff.getOrgCode());
			rev.setOrgName(billOff.getOrgName());
			rev.setSerialNumber(billOff.getSerialNumber());
			rev.setReverseAmount(billOff.getBillOffAmount());
			rev.setCreator(reverseRequest.getCreator());
			this.billMapper.saveReverse(rev);
			BillOff bo = new BillOff();
			bo.setBillOffId(billOff.getBillOffId());
			bo.setState(ActiveEnum.ACTIVE.key);
			this.billMapper.updateBillOff(bo);
		}
	}

	@Override
	public PaginationDTO<BillOffResponseDTO> listAgentBillOffForPage(BillOffQueryDTO billOffQuery) {
		PaginationDTO<BillOffResponseDTO> pagination = new PaginationDTO<BillOffResponseDTO>();
		BillOffResponseDTO bor = null;
		List<BillOffResponseDTO> recordList = new ArrayList<BillOffResponseDTO>();
		PageHelper.startPage(billOffQuery.getCurrentPage(), billOffQuery.getPageSize());
		List<BillOff> list = this.billMapper.queryBillOffList(billOffQuery);
		PageInfo<BillOff> page = new PageInfo<BillOff>(list);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (BillOff bo : page.getList()) {
				bor = new BillOffResponseDTO();
				BeanUtils.copyProperties(bo, bor);
				recordList.add(bor);
			}
		}
		pagination.setRecordList(recordList);
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		return pagination;
	}

	@Override
	public List<OrgCheckOutDetailResponseDTO> listOrgCheckOutDetailList(AgentCheckOutQueryDTO agentCheckOutQuery) {
		OrgCheckOutDetailResponseDTO ocd = null;
		List<OrgCheckOutDetailResponseDTO> list = new ArrayList<OrgCheckOutDetailResponseDTO>();
		List<OrgCheckOutDetail> detailList = this.agentCheckOutMapper.queryAgentDayCheckOutDetailList(agentCheckOutQuery);
		if (CollectionUtils.isNotEmpty(detailList)) {
			for (OrgCheckOutDetail oc : detailList) {
				ocd = new OrgCheckOutDetailResponseDTO();
				BeanUtils.copyProperties(oc,ocd);
				if (StringUtils.isNotBlank(oc.getOrderState())) {
					ocd.setOrderStateText(OrderStateEnum.getStateByCode(oc.getOrderState()));
				}
				if (StringUtils.isNotBlank(oc.getPayMethod())) {
					ocd.setPayMethodText(PayMethodEnum.getDescByCode(oc.getPayMethod()));
				}
				if (StringUtils.isNotBlank(oc.getCheckInDate()) && StringUtils.isNotBlank(oc.getCheckOutDate())) {
					Date checkInDate = DateUtil.stringToDate(oc.getCheckInDate(), "yyyy-MM-dd");
					Date checkOutDate = DateUtil.stringToDate(oc.getCheckOutDate(), "yyyy-MM-dd");
					ocd.setNights(DateUtil.getDay(checkInDate, checkOutDate));
				}
				ocd.setOrderCreator(ocd.getCreator());
				list.add(ocd);
			}
		}
		return list;
	}
	
	@Override
	public List<OrgCheckOutDetailResponseDTO> listOrgMergeBill(AgentCheckOutQueryDTO agentCheckOutQuery) {
		OrgCheckOutDetailResponseDTO ocodr = null;
		List<OrgCheckOutDetailResponseDTO> list = new LinkedList<OrgCheckOutDetailResponseDTO>();
		List<OrgCheckOutDetail> detailList = this.agentCheckOutMapper.queryAgentDayCheckOutDetailList(agentCheckOutQuery);
		if (CollectionUtils.isNotEmpty(detailList)) {
			Map<String, List<OrgCheckOutDetail>> map = new LinkedHashMap<String, List<OrgCheckOutDetail>>();
			for (OrgCheckOutDetail oco : detailList) {
//				String key = oco.getOrderCode()+"-"+oco.getHotelId()+"-"+oco.getPricePlanId()+"-"+oco.getRoomTypeId()+"-"+oco.getRooms()+"-"+oco.getReceivable();
				String key = oco.getHotelId()+"-"+oco.getPricePlanId()+"-"+oco.getRoomTypeId()+"-"+oco.getRooms()+"-"+oco.getReceivable();
				if (map.get(key) == null) {
					map.put(key, new LinkedList<OrgCheckOutDetail>());
				}
				map.get(key).add(oco);
			}
			for (Map.Entry<String, List<OrgCheckOutDetail>> entry : map.entrySet()) {
				Date startDate = null;
				int roomNights = 0;
				int flag = 0;
				BigDecimal receivable = BigDecimal.ZERO;
				for (OrgCheckOutDetail ocod : entry.getValue()) {
					flag += 1;
					if (null == startDate) {
						ocodr = new OrgCheckOutDetailResponseDTO();
						BeanUtils.copyProperties(ocod, ocodr);
						if (StringUtils.isNotBlank(ocodr.getOrderState())) {
							ocodr.setOrderStateText(OrderStateEnum.getStateByCode(ocodr.getOrderState()));
						}
						if (StringUtils.isNotBlank(ocodr.getPayMethod())) {
							ocodr.setPayMethodText(PayMethodEnum.getDescByCode(ocodr.getPayMethod()));
						}
						roomNights = 1;
						ocodr.setNights(roomNights);
					} else {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(startDate);
						LocalDate curDate = new LocalDate(new DateTime(calendar.getTime()));
						calendar.add(Calendar.DAY_OF_MONTH, 1);
						LocalDate nextDate = new LocalDate(new DateTime(calendar.getTime()));
						LocalDate checkInDate = new LocalDate(new DateTime(DateUtil.stringToDate(ocod.getCheckInDate())));
						if (nextDate.equals(checkInDate) || curDate.equals(checkInDate)) {
							roomNights += 1;
							ocodr.setNights(roomNights);
							receivable = ocodr.getReceivable().add(ocod.getReceivable() == null ? BigDecimal.ZERO : ocod.getReceivable());
							ocodr.setReceivable(receivable);
							ocodr.setCheckOutDate(ocod.getCheckOutDate());
							if (flag == entry.getValue().size()) {
								list.add(ocodr);
							}
						} else {
							list.add(ocodr);
							ocodr = new OrgCheckOutDetailResponseDTO();
							BeanUtils.copyProperties(ocod, ocodr);
							if (StringUtils.isNotBlank(ocodr.getOrderState())) {
								ocodr.setOrderStateText(OrderStateEnum.getStateByCode(ocodr.getOrderState()));
							}
							if (StringUtils.isNotBlank(ocodr.getPayMethod())) {
								ocodr.setPayMethodText(PayMethodEnum.getDescByCode(ocodr.getPayMethod()));
							}
							roomNights = 1;
							ocodr.setNights(roomNights);
							if (flag == entry.getValue().size()) {
								list.add(ocodr);
							}
						}
					}
					startDate = DateUtil.stringToDate(ocod.getCheckInDate());
				}
			}
		}
		Collections.sort(list, new Comparator<OrgCheckOutDetailResponseDTO>() {

			@Override
			public int compare(OrgCheckOutDetailResponseDTO o1, OrgCheckOutDetailResponseDTO o2) {
				Date startDate = DateUtil.stringToDate(o1.getCheckInDate());
				Date endDate = DateUtil.stringToDate(o2.getCheckInDate());
				if (startDate.getTime() > endDate.getTime()) {
					return 1;
				} else if (startDate.getTime() < endDate.getTime()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

}
