package com.travel.hotel.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.attachment.dao.AttachmentMapper;
import com.travel.common.constant.InitData;
import com.travel.common.dto.AttachmentDTO;
import com.travel.common.dto.EmailDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.ResultDTO;
import com.travel.common.dto.finance.query.OrderLockQueryDTO;
import com.travel.common.dto.member.query.BankAccountQueryDTO;
import com.travel.common.dto.member.response.BankAccountDTO;
import com.travel.common.dto.order.OrderDTO;
import com.travel.common.dto.order.OrderDayPriceDTO;
import com.travel.common.dto.order.OrderProductDTO;
import com.travel.common.dto.order.OrderRestrictDTO;
import com.travel.common.dto.order.request.OrderConfirmDTO;
import com.travel.common.dto.order.request.QueryOrderRequestDTO;
import com.travel.common.dto.order.request.SearchRoomQueryDTO;
import com.travel.common.dto.order.response.OrderDayProductPriceDTO;
import com.travel.common.dto.order.response.OrderInfoResponseDTO;
import com.travel.common.dto.product.request.PricePlanQueryDTO;
import com.travel.common.enums.ActiveEnum;
import com.travel.common.enums.AttachmentTypeEnum;
import com.travel.common.enums.BreakfastEnum;
import com.travel.common.enums.ChannelEnum;
import com.travel.common.enums.ChannelOrderStateEnum;
import com.travel.common.enums.ConfirmTypeEnum;
import com.travel.common.enums.FinanceLockEnum;
import com.travel.common.enums.OrderProductTypeEnum;
import com.travel.common.enums.OrderStateEnum;
import com.travel.common.enums.PayMethodEnum;
import com.travel.common.enums.ResultTypeEnum;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.FreemarkerUtil;
import com.travel.email.EmailSender;
import com.travel.hotel.order.dao.OrderPOMapper;
import com.travel.hotel.order.dao.OrderProductPOMapper;
import com.travel.hotel.order.dao.OrderQuotaLogPOMapper;
import com.travel.hotel.order.entity.HotelOrderCount;
import com.travel.hotel.order.entity.OrderInfo;
import com.travel.hotel.order.entity.po.OrderDayPricePO;
import com.travel.hotel.order.entity.po.OrderPO;
import com.travel.hotel.order.entity.po.OrderProductPO;
import com.travel.hotel.order.entity.po.OrderQuotaLogPO;
import com.travel.hotel.order.entity.po.UsedQuotaPO;
import com.travel.hotel.order.service.HotelOrderFrontService;
import com.travel.hotel.product.entity.PricePlanRoom;
import com.travel.hotel.product.service.PricePlanService;
import com.travel.member.dao.AgentDao;
import com.travel.member.dao.SupplyDao;
import com.travel.member.service.BankAccountService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单前台页面服务类实现
 * @author  2018/1/10
 */
@Service
public class HotelOrderFrontServiceImpl implements HotelOrderFrontService {

    private static final Logger LOG = LoggerFactory.getLogger(HotelOrderFrontServiceImpl.class);

	@Autowired
	private OrderQuotaLogPOMapper orderQuotaLogPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private OrderProductPOMapper orderProductPOMapper;
    
    @Autowired
    private AgentDao agentDao;
    
    @Autowired
    private SupplyDao supplyDao;
    
    @Autowired
    private PricePlanService pricePlanService;
    
    @Autowired
    private BankAccountService bankAccountService;
    
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public ResultDTO<List<OrderDTO>> queryOrderList(QueryOrderRequestDTO queryOrderRequestDTO) {
        return null;
    }

    @Override
    public ResultDTO<OrderDTO> queryOrderDetail(QueryOrderRequestDTO queryOrderRequestDTO) {

        ResultDTO<OrderDTO> resultDTO = new ResultDTO<>(ResultTypeEnum.SUCCESS);
		OrderDTO orderDTO = null;
        if (null == queryOrderRequestDTO || null == queryOrderRequestDTO.getOrderId() || queryOrderRequestDTO.getOrderId() < 1) {
            resultDTO =  new ResultDTO<>(ResultTypeEnum.FAIL);
            resultDTO.setErrorReason("查询订单详情参数无效，必须传入orderId");
            return resultDTO;
        }

        try {

            OrderPO orderPO = orderPOMapper.selectOrderDetailByPrimaryKey(queryOrderRequestDTO.getOrderId());
			if (null != orderPO) {
				orderDTO = BeanCopy.copyProperties(orderPO, OrderDTO.class);
				if (CollectionUtils.isNotEmpty(orderPO.getOrderRestrictPOList())) {
					List<OrderRestrictDTO> orderRestrictDTOList = BeanCopy.listCopy(orderPO.getOrderRestrictPOList(), OrderRestrictDTO.class);
					orderDTO.setOrderRestrictDTOList(orderRestrictDTOList);
				}
				List<AttachmentDTO> attachmentList = this.attachmentMapper.queryAttachmentList(orderPO.getOrderId());
				if (CollectionUtils.isNotEmpty(attachmentList)) {
					for (AttachmentDTO att : attachmentList) {
						if (StringUtils.isNoneBlank(att.getAttachmentType())) {
							att.setAttachmentTypeText(AttachmentTypeEnum.getValueByKey(att.getAttachmentType()));
						}
					}
				}
				orderDTO.setAttachmentList(attachmentList);
				List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
				if (CollectionUtils.isNotEmpty(orderPO.getOrderProductPOList())) {
					for (OrderProductPO opp : orderPO.getOrderProductPOList()) {
						OrderProductDTO orderProductDTO = BeanCopy.copyProperties(opp, OrderProductDTO.class);
						if (CollectionUtils.isNotEmpty(opp.getOrderDayPricePOList())) {
							List<OrderDayPriceDTO> orderDayPriceDTOList = BeanCopy.listCopy(opp.getOrderDayPricePOList(), OrderDayPriceDTO.class);
							orderProductDTO.setOrderDayPriceDTOList(orderDayPriceDTOList);
						}
						orderProductDTOList.add(orderProductDTO);
					}
				}
				orderDTO.setOrderProductDTOList(orderProductDTOList);
				if (StringUtils.isNotBlank(orderDTO.getOrderState())) {
					orderDTO.setOrderStateText(OrderStateEnum.getStateByCode(orderDTO.getOrderState()));
				}
				if (StringUtils.isNotBlank(orderDTO.getChannelState())) {
					orderDTO.setChannelOrderStateText(ChannelOrderStateEnum.getOrderStateName(orderDTO.getChannelCode(), orderDTO.getChannelState()));
				}
			}
            resultDTO.setObj(orderDTO);
        } catch (Exception e) {
            LOG.error("查询订单详情失败", e);
            resultDTO.setResult(ResultTypeEnum.FAIL.key);
            resultDTO.setErrorCode(ResultTypeEnum.FAIL.code);
            resultDTO.setErrorReason("查询订单详情失败");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<List<OrderProductDTO>> queryOrderProductList(String orderCode) {
        ResultDTO<List<OrderProductDTO>> resultDTO =  new ResultDTO(ResultTypeEnum.SUCCESS);
        try {
            OrderProductDTO queryDTO = new OrderProductDTO();
            queryDTO.setOrderCode(orderCode);
            List<OrderProductPO> orderProductPOList = orderProductPOMapper.queryList(queryDTO);

            resultDTO.setObj(BeanCopy.listCopy(orderProductPOList, OrderProductDTO.class));
        } catch (Exception e) {
            LOG.error("查询订单产品列表失败", e);
            resultDTO.setResult(ResultTypeEnum.FAIL.key);
            resultDTO.setErrorCode(ResultTypeEnum.FAIL.code);
            resultDTO.setErrorReason("查询订单产品列表失败");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<List<OrderDayPriceDTO>> queryOrderDayPriceList(String orderCode, Integer productId) {
        return null;
    }

    @Override
    public ResultDTO<List<OrderRestrictDTO>> queryOrderRestrictList(String orderCode) {
        return null;
    }

//    @Override
//    protected ResultDTO checkQuotaStateAndQuotaNum(OrderDayPriceDTO orderDayPriceDTO, QuotaPO quotaPO) {
//        return null;
//    }
//
//    @Override
//    protected ResultDTO checkDaySalePrice(OrderDayPriceDTO orderDayPriceDTO, PricePO pricePO) {
//        return null;
//    }

	@Override
	public PaginationDTO<OrderInfoResponseDTO> listOrderLockForPage(OrderLockQueryDTO orderLockQuery) {
		OrderInfoResponseDTO or = null;
		List<OrderInfoResponseDTO> orderList = new ArrayList<OrderInfoResponseDTO>();
		PaginationDTO<OrderInfoResponseDTO> pagination = new PaginationDTO<OrderInfoResponseDTO>();
		PageHelper.startPage(orderLockQuery.getCurrentPage(), orderLockQuery.getPageSize());
		List<OrderInfo> list = this.orderPOMapper.queryOrderLockList(orderLockQuery);
		PageInfo<OrderInfo> page = new PageInfo<OrderInfo>(list);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (OrderInfo oi : page.getList()) {
				or = new OrderInfoResponseDTO();
				BeanUtils.copyProperties(oi, or);
				if (null != oi.getFinanceLockStatus()) {
					or.setFinanceLockStateText(FinanceLockEnum.getDescByKey(oi.getFinanceLockStatus()));
				}
				if (org.apache.commons.lang3.StringUtils.isNotBlank(oi.getOrderState())) {
					or.setOrderStateText(OrderStateEnum.getStateByCode(oi.getOrderState()));
				}
				orderList.add(or);
			}
		}
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		pagination.setRecordList(orderList);
		return pagination;
	}

	@Override
	public PaginationDTO<OrderInfoResponseDTO> listOrderInfoForPage(QueryOrderRequestDTO queryOrderRequest) {
		OrderInfoResponseDTO or = null;
		List<OrderInfoResponseDTO> orderList = new ArrayList<OrderInfoResponseDTO>();
		PaginationDTO<OrderInfoResponseDTO> pagination = new PaginationDTO<OrderInfoResponseDTO>();
		PageHelper.startPage(queryOrderRequest.getCurrentPage(), queryOrderRequest.getPageSize());
		List<OrderInfo> list = this.orderPOMapper.queryOrderInfoList(queryOrderRequest);
		PageInfo<OrderInfo> page = new PageInfo<OrderInfo>(list);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (OrderInfo oi : page.getList()) {
				or = new OrderInfoResponseDTO();
				BeanUtils.copyProperties(oi, or);
				if (null != oi.getFinanceLockStatus()) {
					or.setFinanceLockStateText(FinanceLockEnum.getDescByKey(oi.getFinanceLockStatus()));
				}
				if (org.apache.commons.lang3.StringUtils.isNotBlank(oi.getOrderState())) {
					or.setOrderStateText(OrderStateEnum.getStateByCode(oi.getOrderState()));
				}
				if(StringUtils.isNotBlank(or.getChannelCode())) {
					or.setChannelCodeText(ChannelEnum.getValueByKey(or.getChannelCode()));
				}
				orderList.add(or);
			}
		}
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		pagination.setRecordList(orderList);
		return pagination;
	}

	@Override
	public void updateOrderLock(OrderDTO order) {
		OrderPO o = new OrderPO();
		o.setOrderId(order.getOrderId());
		o.setFinanceLockStatus(order.getFinanceLockStatus());
		o.setFinanceLocker(order.getFinanceLocker());
		this.orderPOMapper.updateByPrimaryKeySelective(o);
	}
	
	@Override
	public void updateOrder(OrderDTO order) {
		OrderPO o = new OrderPO();
		BeanUtils.copyProperties(order, o);
		this.orderPOMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void orderConfirm(OrderConfirmDTO orderConfirm) {
		//更新订单状态为已确认
		OrderPO order = this.orderPOMapper.selectByPrimaryKey(orderConfirm.getOrderId());
		if (null == order) {
			throw new ServiceException("订单不存在");
		}
		if (StringUtils.isNoneBlank(order.getOrderState()) && order.getOrderState().equals(OrderStateEnum.CONFIRMED.code)) {
			throw new ServiceException("订单已确认");
		}
		if (StringUtils.isNoneBlank(order.getOrderState()) && order.getOrderState().equals(OrderStateEnum.REFUSED.code)) {
			throw new ServiceException("订单已拒绝");
		}
		if (StringUtils.isNoneBlank(order.getOrderState()) && order.getOrderState().equals(OrderStateEnum.APPLYING_CANCEL.code)) {
			throw new ServiceException("订单申请取消中");
		}
		if (StringUtils.isNoneBlank(order.getOrderState()) && order.getOrderState().equals(OrderStateEnum.CANCELED.code)) {
			throw new ServiceException("订单已取消");
		}
		order.setOrderState(OrderStateEnum.CONFIRMED.code);
		this.orderPOMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public void orderConfirmEmailToAgent(OrderConfirmDTO orderConfirm) {
		String html = this.getOrderHtml(orderConfirm);
		EmailDTO email = new EmailDTO();
		email.setContent(html);
		email.setFrom(orderConfirm.getEmailFrom());
		email.setUserName(orderConfirm.getEmailFrom());
		email.setHost(orderConfirm.getEmailHost());
		email.setPort(orderConfirm.getEmailPort());
		email.setPassword(orderConfirm.getPassWord());
		email.setTo(orderConfirm.getEmailTo());
		email.setSubject(orderConfirm.getEmailSubject());
		EmailSender.sendHtmlEmail(email, orderConfirm);
	}
	
	/**
	 * 获取订单确认html
	 * @param orderConfirm
	 * @return
	 */
	private String getOrderHtml(OrderConfirmDTO orderConfirm) {
		Map<String,Object> data = new HashMap<String,Object>();
		String templatePath = "WEB-INF/templates/email";
		String templateName = "";
		OrderPO order = this.orderPOMapper.selectOrderDetailByPrimaryKey(orderConfirm.getOrderId());
		if (null == order) {
			throw new ServiceException("订单不存在");
		}
		if (ConfirmTypeEnum.CONFIRM.code.equals(orderConfirm.getConfirmType()) || ConfirmTypeEnum.PAY.code.equals(orderConfirm.getConfirmType())) {
			templateName = ConfirmTypeEnum.CONFIRM.code + ".ftl";
		} else if (ConfirmTypeEnum.REFUND.code.equals(orderConfirm.getConfirmType())) {
			templateName = ConfirmTypeEnum.REFUND.code + ".ftl";
		}
		data.put("orderCode", order.getOrderCode());
		data.put("agentName", order.getAgentName());
		data.put("emailFrom", orderConfirm.getEmailFrom());
		data.put("emailTo", orderConfirm.getEmailTo());
		data.put("sender", orderConfirm.getCreator());
		data.put("guestName", order.getGuestName());
		data.put("remark", order.getRemark());
		data.put("tel", InitData.merchantMap.get("tel"));
		data.put("fax", InitData.merchantMap.get("fax"));
		data.put("checkInDate", DateUtil.dateToString(order.getCheckinDate(), "yyyy-MM-dd"));
		data.put("checkOutDate", DateUtil.dateToString(order.getCheckoutDate(), "yyyy-MM-dd"));
		if (StringUtils.isBlank(orderConfirm.getLogoHtmlTarget())) {
			data.put("logoPath", orderConfirm.getLogoPath());
		} else {
			data.put("logoPath", orderConfirm.getLogoHtmlTarget());
		}
		OrderDayProductPriceDTO odp = null;
		List<OrderDayProductPriceDTO> orderDayProductPriceList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(order.getOrderProductPOList())) {
			data.put("hotelName", order.getOrderProductPOList().get(0).getHotelName());
			int rooms = 0;
			for (OrderProductPO op : order.getOrderProductPOList()) {
				PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
				pricePlanQueryDTO.setHotelId(op.getHotelId());
				pricePlanQueryDTO.setSupplyCode(order.getSupplyCode());
				List<PricePlanRoom> pricePlanRoomList = this.pricePlanService.queryPricePlanRoomByHotelId(pricePlanQueryDTO);
				if (CollectionUtils.isNotEmpty(op.getOrderDayPricePOList())) {
					for (OrderDayPricePO od : op.getOrderDayPricePOList()) {
						if (OrderProductTypeEnum.ROOM.code.equals(op.getProductType())) {
							rooms += od.getRooms() == null ? 0 : od.getRooms();
						}
						odp = new OrderDayProductPriceDTO();
						odp.setSaleDate(DateUtil.dateToString(od.getSaleDate(), "yyyy-MM-dd"));
						odp.setRoomTypeName(op.getRoomTypeName());
						odp.setRooms(od.getRooms()+"");
						odp.setBreakFastNum(BreakfastEnum.getValueByKey(od.getBreakfastNum()));
						odp.setPayMethod(PayMethodEnum.getDescByCode(order.getPayMethod()));
						odp.setSalePrice(od.getSaleBPrice() == null ? "" : od.getSaleBPrice() + od.getSaleBCurrency());
						if (CollectionUtils.isNotEmpty(pricePlanRoomList)) {
							for (PricePlanRoom pr : pricePlanRoomList) {
								if (od.getPriceplanId().equals(pr.getPricePlanId()) && op.getRoomTypeId().equals(pr.getRoomTypeId())) {
									odp.setBedTypeName(pr.getBedTypeText());
									break;
								}
							}
						}
						orderDayProductPriceList.add(odp);
					}
				}
			}
			data.put("rooms", rooms);
		}
		data.put("orderDayProductPriceList", orderDayProductPriceList);
		if (StringUtils.isNotBlank(orderConfirm.getConfirmType()) && orderConfirm.getConfirmType().equals(ConfirmTypeEnum.CONFIRM.code)) {
			data.put("confirmType", orderConfirm.getConfirmType());
			BankAccountQueryDTO bankAccountQuery = new BankAccountQueryDTO();
			bankAccountQuery.setIsActive(ActiveEnum.ACTIVE.key);
			List<BankAccountDTO> bankAccountList = this.bankAccountService.listBankAccountListByCondition(bankAccountQuery);
			if (CollectionUtils.isNotEmpty(bankAccountList)) {
				data.put("bankAccountList", bankAccountList);
			}
		}
		int nights = DateUtil.getDay(order.getCheckinDate(), order.getCheckoutDate());
		data.put("nights", nights);
		if (null != order.getSalePrice()) {
			data.put("receivable", order.getSalePrice()+order.getSaleCurrency());
		} else {
			data.put("receivable", null);
		}
		if (StringUtils.isNoneBlank(orderConfirm.getConfirmType())) {
			data.put("confirmType", orderConfirm.getConfirmType());
			data.put("title", ConfirmTypeEnum.getDescripByCode(orderConfirm.getConfirmType()));
		} else {
			data.put("confirmType", null);
			data.put("title", null);
		}
		return FreemarkerUtil.getHtmlByServletContext(templatePath, templateName, data);
	}

	@Override
	public void sendOrderEmailToSupply(OrderConfirmDTO orderConfirm) {
		String html = this.getSupplyOrderHtml(orderConfirm);
		EmailDTO email = new EmailDTO();
		email.setContent(html);
		email.setFrom(orderConfirm.getEmailFrom());
		email.setUserName(orderConfirm.getEmailFrom());
		email.setHost(orderConfirm.getEmailHost());
		email.setPort(orderConfirm.getEmailPort());
		email.setPassword(orderConfirm.getPassWord());
		email.setTo(orderConfirm.getEmailTo());
		email.setSubject(orderConfirm.getEmailSubject());
		EmailSender.sendHtmlEmail(email, orderConfirm);
	}
	
	private String getSupplyOrderHtml(OrderConfirmDTO orderConfirm) {
		Map<String,Object> data = new HashMap<String,Object>();
		String templatePath = "WEB-INF/templates/email";
		String templateName = "";
		OrderPO order = this.orderPOMapper.selectOrderDetailByPrimaryKey(orderConfirm.getOrderId());
		if (null == order) {
			throw new ServiceException("订单不存在");
		}
		if (ConfirmTypeEnum.BOOKING.code.equals(orderConfirm.getConfirmType()) ||
				ConfirmTypeEnum.EDIT.code.equals(orderConfirm.getConfirmType()) ||
				ConfirmTypeEnum.CANCEL.code.equals(orderConfirm.getConfirmType())) {
			templateName = ConfirmTypeEnum.BOOKING.code + ".ftl";
		}
		data.put("orderCode", order.getOrderCode());
		data.put("supplyName", order.getAgentName());
		data.put("emailFrom", orderConfirm.getEmailFrom());
		data.put("emailTo", orderConfirm.getEmailTo());
		data.put("sender", orderConfirm.getCreator());
		data.put("guestName", order.getGuestName());
		data.put("remark", order.getRemark());
		data.put("tel", InitData.merchantMap.get("tel"));
		data.put("fax", InitData.merchantMap.get("fax"));
		data.put("checkInDate", DateUtil.dateToString(order.getCheckinDate(), "yyyy-MM-dd"));
		data.put("checkOutDate", DateUtil.dateToString(order.getCheckoutDate(), "yyyy-MM-dd"));
		data.put("creator", order.getCreator());
		if (StringUtils.isBlank(orderConfirm.getLogoHtmlTarget())) {
			data.put("logoPath", orderConfirm.getLogoPath());
		} else {
			data.put("logoPath", orderConfirm.getLogoHtmlTarget());
		}
		if (StringUtils.isBlank(orderConfirm.getSignetHtmlTarget())) {
			data.put("signetPath", orderConfirm.getSignetPath());
		} else {
			data.put("signetPath", orderConfirm.getSignetHtmlTarget());
		}
		OrderDayProductPriceDTO odp = null;
		List<OrderDayProductPriceDTO> orderDayProductPriceList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(order.getOrderProductPOList())) {
			data.put("hotelName", order.getOrderProductPOList().get(0).getHotelName());
			int rooms = 0;
			for (OrderProductPO op : order.getOrderProductPOList()) {
				PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
				pricePlanQueryDTO.setHotelId(op.getHotelId());
				pricePlanQueryDTO.setSupplyCode(order.getSupplyCode());
				List<PricePlanRoom> pricePlanRoomList = this.pricePlanService.queryPricePlanRoomByHotelId(pricePlanQueryDTO);
				if (CollectionUtils.isNotEmpty(op.getOrderDayPricePOList())) {
					for (OrderDayPricePO od : op.getOrderDayPricePOList()) {
						if (OrderProductTypeEnum.ROOM.code.equals(op.getProductType())) {
							rooms += od.getRooms() == null ? 0 : od.getRooms();
						}
						odp = new OrderDayProductPriceDTO();
						odp.setSaleDate(DateUtil.dateToString(od.getSaleDate(), "yyyy-MM-dd"));
						odp.setRoomTypeName(op.getRoomTypeName());
						odp.setRooms(od.getRooms()+"");
						odp.setBreakFastNum(BreakfastEnum.getValueByKey(od.getBreakfastNum()));
						odp.setPayMethod(PayMethodEnum.getDescByCode(order.getPayMethod()));
						odp.setSalePrice(od.getBasePrice() == null ? "" : od.getBasePrice() + od.getBaseCurrency());
						if (CollectionUtils.isNotEmpty(pricePlanRoomList)) {
							for (PricePlanRoom pr : pricePlanRoomList) {
								if (od.getPriceplanId().equals(pr.getPricePlanId()) && op.getRoomTypeId().equals(pr.getRoomTypeId())) {
									odp.setBedTypeName(pr.getBedTypeText());
									break;
								}
							}
						}
						orderDayProductPriceList.add(odp);
					}
				}
			}
			data.put("rooms", rooms);
		}
		data.put("orderDayProductPriceList", orderDayProductPriceList);
		if (StringUtils.isNotBlank(orderConfirm.getConfirmType()) && orderConfirm.getConfirmType().equals(ConfirmTypeEnum.CONFIRM.code)) {
			data.put("confirmType", orderConfirm.getConfirmType());
			BankAccountQueryDTO bankAccountQuery = new BankAccountQueryDTO();
			bankAccountQuery.setIsActive(ActiveEnum.ACTIVE.key);
			List<BankAccountDTO> bankAccountList = this.bankAccountService.listBankAccountListByCondition(bankAccountQuery);
			if (CollectionUtils.isNotEmpty(bankAccountList)) {
				data.put("bankAccountList", bankAccountList);
			}
		}
		int nights = DateUtil.getDay(order.getCheckinDate(), order.getCheckoutDate());
		data.put("nights", nights);
		if (null != order.getBasePrice()) {
			data.put("receivable", order.getBasePrice()+order.getBaseCurrency());
		} else {
			data.put("receivable", null);
		}
		if (StringUtils.isNoneBlank(orderConfirm.getConfirmType())) {
			data.put("confirmType", orderConfirm.getConfirmType());
			data.put("title", ConfirmTypeEnum.getDescripByCode(orderConfirm.getConfirmType()));
		} else {
			data.put("confirmType", null);
			data.put("title", null);
		}
		return FreemarkerUtil.getHtmlByServletContext(templatePath, templateName, data);
	}

	@Override
	public List<UsedQuotaPO> queryUsedQuotaOrderList(OrderQuotaLogPO orderQuotaLogPO) {
		List<UsedQuotaPO> usedQuotaPOList = orderQuotaLogPOMapper.queryUsedQuotaOrderList(orderQuotaLogPO);
		return usedQuotaPOList;
	}

	@Override
	public String orderPreview(OrderConfirmDTO orderConfirm) {
		return this.getOrderHtml(orderConfirm);
	}

	@Override
	public String supplyOrderPreview(OrderConfirmDTO orderConfirm) {
		return this.getSupplyOrderHtml(orderConfirm);
	}

	@Override
	public PaginationDTO<HotelOrderCount> listHotelOrderCountForPage(SearchRoomQueryDTO searchRoomQuery) {
		PaginationDTO<HotelOrderCount> pagination = new PaginationDTO<HotelOrderCount>();
		List<HotelOrderCount> recordList = new ArrayList<HotelOrderCount>();
		PageHelper.startPage(searchRoomQuery.getCurrentPage(), searchRoomQuery.getPageSize());
		List<HotelOrderCount> list = this.orderPOMapper.queryOrderCountByHotel(searchRoomQuery);
		PageInfo<HotelOrderCount> page = new PageInfo<HotelOrderCount>(list);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (HotelOrderCount hoc : page.getList()) {
				recordList.add(hoc);
			}
		}
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		pagination.setRecordList(recordList);
		return pagination;
	}
}
