package com.fangcang.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.agent.dto.UserInfoDTO;
import com.fangcang.agent.request.SingleAgentRequestDTO;
import com.fangcang.agent.response.SingleAgentResponseDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.UploadResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.SaleStateEnum;
import com.fangcang.common.enums.SupplyWayEnum;
import com.fangcang.common.enums.order.NoteTypeEnum;
import com.fangcang.common.enums.order.OrderConfirmTypeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.enums.order.PayMethodEnum;
import com.fangcang.common.enums.order.PayStatusEnum;
import com.fangcang.common.enums.order.SupplyStatusEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.FileUploadUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.UploadFileConfig;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.response.HotelBaseInfoRsponseDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.order.domain.GuestDO;
import com.fangcang.order.domain.OrderAttachDO;
import com.fangcang.order.domain.OrderCheckDetailDO;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderNoteDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.OrderRequestDO;
import com.fangcang.order.domain.OrderRequestPriceDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.dto.DeratePolicyPriceDTO;
import com.fangcang.order.dto.GuestDTO;
import com.fangcang.order.dto.OrderCheckDetailDTO;
import com.fangcang.order.dto.OrderDTO;
import com.fangcang.order.dto.OrderFinanceDTO;
import com.fangcang.order.dto.OrderMessageRedisCacheDTO;
import com.fangcang.order.dto.OrderRequestDTO;
import com.fangcang.order.dto.OrderRequestPriceDTO;
import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import com.fangcang.order.dto.SupplyFinanceDTO;
import com.fangcang.order.dto.SupplyOrderCofirmMsgDTO;
import com.fangcang.order.dto.SupplyOrderDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import com.fangcang.order.mapper.GuestMapper;
import com.fangcang.order.mapper.OrderAttachMapper;
import com.fangcang.order.mapper.OrderCheckDetailMapper;
import com.fangcang.order.mapper.OrderMapper;
import com.fangcang.order.mapper.OrderNoteMapper;
import com.fangcang.order.mapper.OrderRequestMapper;
import com.fangcang.order.mapper.OrderRequestPriceMapper;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.order.request.AddAbatementOrderDTO;
import com.fangcang.order.request.AddAttachRequestDTO;
import com.fangcang.order.request.AddNoteRequestDTO;
import com.fangcang.order.request.AddOrderReqRequestDTO;
import com.fangcang.order.request.ChangeAgentRequestDTO;
import com.fangcang.order.request.ChangeBalanceMethodRequestDTO;
import com.fangcang.order.request.ChangeChannelOrderCodeRequestDTO;
import com.fangcang.order.request.ChangeGuestRequestDTO;
import com.fangcang.order.request.ChangeGuideRequestDTO;
import com.fangcang.order.request.ChangeSpecialReqeustDTO;
import com.fangcang.order.request.CountsRequestDTO;
import com.fangcang.order.request.CreateOrderAdditionChargeRequestDTO;
import com.fangcang.order.request.CreateOrderProductRequestDTO;
import com.fangcang.order.request.CreateOrderRequestDTO;
import com.fangcang.order.request.DeleteAttachRequestDTO;
import com.fangcang.order.request.DeleteOrderMessageRequestDTO;
import com.fangcang.order.request.DeleteSupplyOrderCofirmMsgRequestDTO;
import com.fangcang.order.request.DeratePolicyRequestDTO;
import com.fangcang.order.request.HandleOrderReqRequestDTO;
import com.fangcang.order.request.LockOrderRequestDTO;
import com.fangcang.order.request.NotifyAgentRequestDTO;
import com.fangcang.order.request.OrderCancelRequestDTO;
import com.fangcang.order.request.OrderConfirmRequestDTO;
import com.fangcang.order.request.OrderCreditPayOrRefundRequestDTO;
import com.fangcang.order.request.OrderDebuctOrRetreatQuotaRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.OrderListRequestDTO;
import com.fangcang.order.request.UpdateOrderExceptionAmountDTO;
import com.fangcang.order.response.AssemblyCreateOrderResponseDTO;
import com.fangcang.order.response.DeratePolicyResponseDTO;
import com.fangcang.order.response.OrderChannelCountResponseDTO;
import com.fangcang.order.response.OrderCountResponseDTO;
import com.fangcang.order.response.OrderRequestCountDTO;
import com.fangcang.order.response.OrderStatisticsDTO;
import com.fangcang.order.response.QueryChannelListResponseDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;
import com.fangcang.order.response.SupplyProductResponseDTO;
import com.fangcang.order.service.OrderCommonService;
import com.fangcang.order.service.OrderService;
import com.fangcang.order.service.SupplyOrderService;
import com.fangcang.product.domain.MerchantSaleChannelDO;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.response.PricePlanResponseDTO;
import com.fangcang.product.response.SaleStateResponseDTO;
import com.fangcang.product.service.PricePlanService;
import com.fangcang.product.service.SaleStateService;
import com.fangcang.supplier.dto.MerchantOPDTO;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.response.SingleSupplyInfoResponseDTO;
import com.fangcang.supplier.service.SupplyService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单服务
 *
 * @author : zhanwang
 * @date : 2018/5/23
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SupplyOrderMapper supplyOrderMapper;
    @Resource
    private OrderAttachMapper orderAttachMapper;
    @Resource
    private OrderNoteMapper orderNoteMapper;
    @Resource
    private GuestMapper guestMapper;
    @Resource
    private OrderRequestMapper orderRequestMapper;
    @Resource
    private OrderRequestPriceMapper orderRequestPriceMapper;
    @Resource
    private OrderCheckDetailMapper checkDetailMapper;

    @Resource
    private SupplyOrderService supplyOrderService;
    @Resource
    private OrderCommonService orderCommonService;
    @Resource
    private PricePlanService pricePlanService;
    @Resource
    private SaleStateService saleStateService;
    @Resource
    private SupplyService supplyService;
    @Resource
    private HotelInfoService hotelInfoService;
    @Autowired
    private AgentService agentService;

    @Autowired
    private UploadFileConfig uploadFileConfig;

    @Override
    public PaginationSupportDTO<OrderDTO> orderList(OrderListRequestDTO requestDTO) {
        log.info("orderList param: {}", requestDTO);
        // 1. 去前后空格
        requestDTO.setOrderCode(StringUtils.trim(requestDTO.getOrderCode()));
        requestDTO.setGuest(StringUtils.trim(requestDTO.getGuest()));
        requestDTO.setSupplyOrderCode(StringUtils.trim(requestDTO.getSupplyOrderCode()));
        requestDTO.setConfirmNo(StringUtils.trim(requestDTO.getConfirmNo()));
        requestDTO.setCustomerOrderCode(StringUtils.trim(requestDTO.getCustomerOrderCode()));

        // 2. 添加过滤条件
        Example example = new Example(OrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("merchantCode", requestDTO.getMerchantCode());
        if (requestDTO.getQuickQueryType() == null) {
            if (!StringUtils.isEmpty(requestDTO.getOrderCode())) {
                criteria.andLike("orderCode", "%" + requestDTO.getOrderCode() + "%");
            }
            if (requestDTO.getIsGroupRoom() != null) {
                criteria.andEqualTo("isGroupRoom", requestDTO.getIsGroupRoom());
            }
            if (requestDTO.getDateQueryType() != null && !StringUtils.isEmpty(requestDTO.getStartDate())
                    && !StringUtils.isEmpty(requestDTO.getEndDate())) {
                // 日期查询类型，1：下单日期，2：入住日期，3：离店日期
                if (requestDTO.getDateQueryType() == 1) {
                    criteria.andBetween("createTime", requestDTO.getStartDate(), requestDTO.getEndDate().trim() + " 23:59:59");
                } else if (requestDTO.getDateQueryType() == 2) {
                    criteria.andBetween("checkinDate", requestDTO.getStartDate(), requestDTO.getEndDate());
                } else if (requestDTO.getDateQueryType() == 3) {
                    criteria.andBetween("checkoutDate", requestDTO.getStartDate(), requestDTO.getEndDate());
                }
            }
            if (!StringUtils.isEmpty(requestDTO.getCreateDateStart()) && !StringUtils.isEmpty(requestDTO.getCreateDateEnd())) {
                criteria.andBetween("createTime", requestDTO.getStartDate(), requestDTO.getEndDate().trim() + " 23:59:59");
            }
            if (!StringUtils.isEmpty(requestDTO.getCheckInDateStart()) && !StringUtils.isEmpty(requestDTO.getCheckInDateEnd())) {
                criteria.andBetween("checkinDate", requestDTO.getStartDate(), requestDTO.getEndDate());
            }
            if (!StringUtils.isEmpty(requestDTO.getCheckOutDateStart()) && !StringUtils.isEmpty(requestDTO.getCheckOutDateEnd())) {
                criteria.andBetween("checkoutDate", requestDTO.getStartDate(), requestDTO.getEndDate());
            }
            if (!StringUtils.isEmpty(requestDTO.getGuest())) {
                criteria.andLike("guestNames", "%" + requestDTO.getGuest() + "%");
            }
            if (requestDTO.getHotelId() != null) {
                criteria.andEqualTo("hotelId", requestDTO.getHotelId());
            } else {
                if (!StringUtils.isEmpty(requestDTO.getHotelName())) {
                    criteria.andLike("hotelName", "%" + requestDTO.getHotelName() + "%");
                }
            }
            if (requestDTO.getPayStatus() != null) {
                criteria.andEqualTo("payStatus", requestDTO.getPayStatus());
            }
            if (!CollectionUtils.isEmpty(requestDTO.getPayStatusList())) {
                criteria.andIn("payStatus", requestDTO.getPayStatusList());
            }
            if (requestDTO.getOrderStatus() != null) {
                criteria.andEqualTo("orderStatus", requestDTO.getOrderStatus());
            }
            if (!CollectionUtils.isEmpty(requestDTO.getOrderStatusList())) {
                criteria.andIn("orderStatus", requestDTO.getOrderStatusList());
            }
            if (!StringUtils.isEmpty(requestDTO.getSupplyOrderCode())) {
                criteria.andLike("supplyOrderCodes", "%" + requestDTO.getSupplyOrderCode() + "%");
            }
            if (!StringUtils.isEmpty(requestDTO.getConfirmNo())) {
                criteria.andLike("supplyConfirmNo", "%" + requestDTO.getConfirmNo() + "%");
            }
            if (!StringUtils.isEmpty(requestDTO.getCustomerOrderCode())) {
                criteria.andLike("customerOrderCode", "%" + requestDTO.getCustomerOrderCode() + "%");
            }
            if (!StringUtils.isEmpty(requestDTO.getAgentCode())) {
                criteria.andEqualTo("agentCode", requestDTO.getAgentCode());
            } else {
                if (!StringUtils.isEmpty(requestDTO.getAgentName())) {
                    criteria.andLike("agentName", "%" + requestDTO.getAgentName() + "%");
                }
            }
            if (StringUtils.isNotEmpty(requestDTO.getMerchantBm())) {
                criteria.andLike("merchantBm", "%" + requestDTO.getMerchantBm() + "%");
            }
            if (requestDTO.getSupplyStatus() != null) {
                criteria.andLike("supplyStatus", "%" + requestDTO.getSupplyStatus().toString() + "%");
            }
            if (!StringUtils.isEmpty(requestDTO.getBelongName())) {
                criteria.andLike("belongName", "%" + requestDTO.getBelongName() + "%");
            }
            if (!StringUtils.isEmpty(requestDTO.getSupplyCode())) {
                criteria.andLike("supplyCodes", "%" + requestDTO.getSupplyCode() + "%");
            }
        } else {
            // 快捷查询类型，1:我的订单，2：今日入住，3：未处理订单，4：取消订单，5：今日新订单，6：手工单，7：待支付，8已支付，9待确认
            Date date = DateUtil.getCurrentDate();
            if (requestDTO.getQuickQueryType() == 1) {
                criteria.andEqualTo("belongUser", requestDTO.getOperatorUser());
            } else if (requestDTO.getQuickQueryType() == 2) {
                criteria.andEqualTo("checkinDate", DateUtil.dateToString(date));
            } else if (requestDTO.getQuickQueryType() == 3) {
                Example.Criteria criteria2=example.createCriteria();
                criteria2.andEqualTo("orderStatus", OrderStatusEnum.NEWORDER.key);
                criteria2.orEqualTo("orderStatus", OrderStatusEnum.PROCESSING.key);
                criteria2.orLike("supplyStatus","%1%");
                criteria2.orLike("supplyStatus","%2%");
                example.and(criteria2);
            } else if (requestDTO.getQuickQueryType() == 4) {
                criteria.andEqualTo("orderStatus", OrderStatusEnum.CANCELED.key);
            } else if (requestDTO.getQuickQueryType() == 5) {
                criteria.andEqualTo("orderStatus",1);
                criteria.andBetween("createTime", DateUtil.dateToString(date), DateUtil.dateToString(date) + " 23:59:59");
            } else if (requestDTO.getQuickQueryType() == 6) {
                criteria.andEqualTo("isManualOrder", 1);
            }
        }
        if (!StringUtils.isEmpty(requestDTO.getChannelCode())) {
            if (StringUtils.equals(requestDTO.getChannelCode(), ChannelTypeEnum.CTRIP.key)) {
                List<String> ctripChannels = new ArrayList<>();
                ctripChannels.add(ChannelTypeEnum.CTRIP.key);
                ctripChannels.add(ChannelTypeEnum.CTRIP_B2B.key);
                ctripChannels.add(ChannelTypeEnum.CTRIP_CHANNEL_A.key);
                criteria.andIn("channelCode", ctripChannels);
            } else {
                criteria.andEqualTo("channelCode", requestDTO.getChannelCode());
            }
        }
        // 3.分页查询
        String orderBy = "id desc";
        Page<OrderDO> page = PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                                       .doSelectPage(() -> orderMapper.queryOrderList(requestDTO));
        // 4. 组装响应对象
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Map<Integer,OrderDTO> orderDTOMap=new HashMap();
        for (OrderDO orderDO : page.getResult()) {
            OrderDTO orderDTO = PropertyCopyUtil.transfer(orderDO, OrderDTO.class);
            orderDTO.setOrderId(orderDO.getId());
            orderDTO.setChannelName(ChannelTypeEnum.getValueByKey(orderDO.getChannelCode()));
            orderDTO.setCheckinDate(DateUtil.dateToString(orderDO.getCheckinDate()));
            orderDTO.setCheckoutDate(DateUtil.dateToString(orderDO.getCheckoutDate()));
            orderDTO.setCreateTime(DateUtil.dateToStringWithHms(orderDO.getCreateTime()));
            // 是否锁单
            Integer isLock = 0;
            if (StringUtils.isNotEmpty(orderDO.getLockUser()) && !StringUtils.equals(orderDO.getLockUser(), requestDTO.getOperatorUser())) {
                isLock = 1;
            }
            orderDTO.setIsLock(isLock);

            orderDTO.setTagList(new ArrayList<>());
            if(Math.abs(DateUtil.getDay(DateUtil.stringToDate(orderDTO.getCheckinDate()),DateUtil.stringToDate(DateUtil.dateToString(new Date()))))<1){
                orderDTO.getTagList().add("今");
            }
            if("1".equals(orderDTO.getIsHoldRoom())){
                orderDTO.getTagList().add("留");
            }
            orderDTOList.add(orderDTO);
            orderDTOMap.put(orderDO.getId(),orderDTO);
        }
        if (orderDTOMap.size()>0){
            List<OrderRequestCountDTO> orderRequestCountDTOList=orderRequestMapper.queryOrderRequestCount(new ArrayList<>(orderDTOMap.keySet()));
            for (OrderRequestCountDTO orderRequestCountDTO:orderRequestCountDTOList){
                OrderDTO orderDTO=orderDTOMap.get(orderRequestCountDTO.getOrderId());
                if (orderRequestCountDTO.getCancelCount()>0){
                    orderDTO.getTagList().add("取");
                }
                if (orderRequestCountDTO.getModifyCount()>0){
                    orderDTO.getTagList().add("改");
                }
                if (orderRequestCountDTO.getDelayCount()>0){
                    orderDTO.getTagList().add("延");
                }
            }
        }
        PaginationSupportDTO paginationSupport = new PaginationSupportDTO();
        paginationSupport.setItemList(orderDTOList);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    public OrderStatisticsDTO queryOrderStatistics(String merchantCode,String operator){
        Example unTreatedExample = new Example(OrderDO.class);
        Example.Criteria unTreatedCriteria=unTreatedExample.createCriteria();
        unTreatedCriteria.andEqualTo("merchantCode",merchantCode);
        unTreatedCriteria.andNotEqualTo("orderStatus",4);
        Example.Criteria unTreatedCriteria2=unTreatedExample.createCriteria();
        unTreatedCriteria2.andEqualTo("orderStatus", OrderStatusEnum.NEWORDER.key);
        unTreatedCriteria2.orEqualTo("orderStatus", OrderStatusEnum.PROCESSING.key);
        unTreatedCriteria2.orLike("supplyStatus","%1%");
        unTreatedCriteria2.orLike("supplyStatus","%2%");
        unTreatedExample.and(unTreatedCriteria2);
        int unTreatedCount=orderMapper.selectCountByExample(unTreatedExample);

        Example myUnTreatedExample = new Example(OrderDO.class);
        Example.Criteria myUnTreatedCriteria=myUnTreatedExample.createCriteria();
        myUnTreatedCriteria.andEqualTo("merchantCode",merchantCode);
        myUnTreatedCriteria.andNotEqualTo("orderStatus",4);
        myUnTreatedCriteria.andEqualTo("belongUser",operator);
        Example.Criteria myUnTreatedCriteria2=myUnTreatedExample.createCriteria();
        myUnTreatedCriteria2.andEqualTo("orderStatus", OrderStatusEnum.NEWORDER.key);
        myUnTreatedCriteria2.orEqualTo("orderStatus", OrderStatusEnum.PROCESSING.key);
        myUnTreatedCriteria2.orLike("supplyStatus","%1%");
        myUnTreatedCriteria2.orLike("supplyStatus","%2%");
        myUnTreatedExample.and(myUnTreatedCriteria2);
        int myUnTreatedCount=orderMapper.selectCountByExample(myUnTreatedExample);

        Example todayNewExample = new Example(OrderDO.class);
        Example.Criteria todayNewExampleCriteria=todayNewExample.createCriteria();
        todayNewExampleCriteria.andEqualTo("merchantCode",merchantCode);
        todayNewExampleCriteria.andEqualTo("orderStatus",1);
        todayNewExampleCriteria.andBetween("createTime", DateUtil.dateToString(new Date()), DateUtil.dateToString(new Date()) + " 23:59:59");
        int todayNewCount=orderMapper.selectCountByExample(todayNewExample);

        OrderRequestCountDTO orderRequestCountDTO=orderRequestMapper.queryOrderRequestStatistics(merchantCode);

        OrderStatisticsDTO orderStatisticsDTO=new OrderStatisticsDTO();
        orderStatisticsDTO.setUnTreatedCount(unTreatedCount);
        orderStatisticsDTO.setMyUnTreatedCount(myUnTreatedCount);
        orderStatisticsDTO.setTodayNewCount(todayNewCount);
        orderStatisticsDTO.setCancelRequestCount(orderRequestCountDTO.getCancelCount());
        return orderStatisticsDTO;
    }

    @Override
    public ResponseDTO updateOrderExceptionAmount(UpdateOrderExceptionAmountDTO request) {
        ResponseDTO responseDTO=new ResponseDTO();
        OrderDO orderParam=new OrderDO();
        orderParam.setId(request.getOrderId());
        orderParam.setExceptionAmount(request.getExceptionAmount());
        orderParam.setModifier(request.getOperator());
        orderParam.setModifyTime(new Date());
        orderMapper.updateByPrimaryKeySelective(orderParam);

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(request.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("修改订单其他应收："+request.getExceptionAmount());
        logDO.setOperator(request.getOperator());
        logDO.setOperateTime(new Date());
        logDO.setContent(content.toString());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO confirmOrder(OrderConfirmRequestDTO requestDTO) {
        log.info("confirmOrder param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 检查并更新订单状态
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.CANCELED.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单已取消！");
            return responseDTO;
        }

        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(requestDTO.getOrderId());
        orderUpdate.setOrderStatus(Byte.valueOf(OrderStatusEnum.TRADED.key + ""));
        orderUpdate.setConfirmContent(requestDTO.getConfirmContent());
        if (!StringUtils.isEmpty(requestDTO.getConfirmNo())) {
            orderUpdate.setConfirmNo(requestDTO.getConfirmNo());
        }
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 2. 非单结自动挂账
        if (orderDO.getBalanceMethod().intValue() != BalanceMethodEnum.SINGLE.key) {
            OrderCreditPayOrRefundRequestDTO creditRequestDTO = PropertyCopyUtil.transfer(orderDO, OrderCreditPayOrRefundRequestDTO.class);
            creditRequestDTO.setOrderStatus(OrderStatusEnum.TRADED.key);
            creditRequestDTO.setOperateType(1);
            creditRequestDTO.setOperator(requestDTO.getCreator());
            creditRequestDTO.setOrderId(orderDO.getId());
            orderCommonService.orderCreditPayOrRefund(creditRequestDTO);
        }
        // 3. 发消息<一期不做>
        // 4. 添加商家给分销商备注
        if (!StringUtils.isEmpty(requestDTO.getConfirmContent())) {
            OrderNoteDO noteInsert = new OrderNoteDO();
            noteInsert.setOrderId(requestDTO.getOrderId());
            noteInsert.setCreator(requestDTO.getModifier());
            noteInsert.setCreateTime(requestDTO.getModifyTime());
            noteInsert.setNote(requestDTO.getConfirmContent());
            noteInsert.setNoteObject(orderDO.getAgentName());
            noteInsert.setNoteType(Byte.valueOf(NoteTypeEnum.AGENT_NOTE.key + ""));
            orderNoteMapper.insert(noteInsert);
        }

        // 5. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        String content = "确认客人成功，确认方式：" + OrderConfirmTypeEnum.getValueByKey(requestDTO.getConfirmType());
        if (StringUtils.isNotEmpty(requestDTO.getConfirmNo())) {
            content = content + "，确认号：" + requestDTO.getConfirmNo();
        }
        logDO.setContent(content);
        logDO.setOperateObject(orderDO.getAgentName());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);


        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO cancelOrder(OrderCancelRequestDTO requestDTO) {
        log.info("cancelOrder param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 检查订单状态
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.CANCELED.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单已取消！");
            return responseDTO;
        }
        // 2. 检查供货单状态：没有已确认的供货单才能取消
        OrderDetailRequestDTO detailRequestDTO = new OrderDetailRequestDTO();
        detailRequestDTO.setOrderId(requestDTO.getOrderId());
        ResponseDTO<List<SupplyOrderDTO>> supplyListResponse = supplyOrderService.querySupplyList(detailRequestDTO);
        List<SupplyOrderDTO> supplyList = supplyListResponse.getModel();
        for (SupplyOrderDTO supplyOrderDTO : supplyList) {
            if (supplyOrderDTO.getSupplyStatus().intValue() == SupplyStatusEnum.CONFIRMED.key) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("供货单（" + supplyOrderDTO.getSupplyOrderCode() + "）已确认，请先取消！");
                return responseDTO;
            }
        }
        // 3. 更新订单状态
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(requestDTO.getOrderId());
        orderUpdate.setOrderStatus(Byte.valueOf(OrderStatusEnum.CANCELED.key + ""));
        orderUpdate.setChangeFee(requestDTO.getChangeFee());
        orderUpdate.setCancelReason(requestDTO.getCancelReason());
        BigDecimal orderSum = BigDecimal.ZERO;
        if (requestDTO.getChangeFee() != null) {
            orderSum = requestDTO.getChangeFee();
        }
        orderUpdate.setOrderSum(orderSum);
        orderUpdate.setReceivableAmount(orderSum);
        orderUpdate.setProfit(orderSum);
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 4. 删除对账明细
        OrderCheckDetailDO checkDetailDelete = new OrderCheckDetailDO();
        checkDetailDelete.setOderId(requestDTO.getOrderId());
        checkDetailMapper.delete(checkDetailDelete);
        // 5. 更新订单财务信息（单结订单）：支付状态、结算状态
        if (orderDO.getBalanceMethod().intValue() == BalanceMethodEnum.SINGLE.key) {
            orderCommonService.updateOrderFinanceInfo(orderDO.getId(), orderUpdate.getReceivableAmount(), null, null);
        } else {
            // 6. 非单结自动退挂账
            OrderCreditPayOrRefundRequestDTO creditRequestDTO = PropertyCopyUtil.transfer(orderDO, OrderCreditPayOrRefundRequestDTO.class);
            creditRequestDTO.setOrderStatus(OrderStatusEnum.CANCELED.key);
            creditRequestDTO.setReceivableAmount(orderUpdate.getReceivableAmount());
            creditRequestDTO.setOperateType(2);
            creditRequestDTO.setOperator(requestDTO.getCreator());
            creditRequestDTO.setOrderId(orderDO.getId());
            orderCommonService.orderCreditPayOrRefund(creditRequestDTO);
        }

        // 7. 添加商家给分销商备注
        if (!StringUtils.isEmpty(requestDTO.getCancelReason())) {
            OrderNoteDO noteInsert = new OrderNoteDO();
            noteInsert.setOrderId(requestDTO.getOrderId());
            noteInsert.setCreator(requestDTO.getModifier());
            noteInsert.setCreateTime(requestDTO.getModifyTime());
            noteInsert.setNote(requestDTO.getCancelReason());
            noteInsert.setNoteObject(orderDO.getAgentName());
            noteInsert.setNoteType(Byte.valueOf(NoteTypeEnum.AGENT_NOTE.key + ""));
            orderNoteMapper.insert(noteInsert);
        }
        // 8. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        String content = "取消订单";
        if (StringUtils.isNotEmpty(requestDTO.getCancelReason())) {
            content = content + "，取消原因：" + requestDTO.getCancelReason();
        }
        logDO.setContent(content);
        logDO.setOperateObject(orderDO.getAgentName());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO changeAgent(ChangeAgentRequestDTO requestDTO) {
        log.info("changeAgent param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();

        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        // 1. 更新订单
        OrderDO updateOrderDO = new OrderDO();
        updateOrderDO.setId(requestDTO.getOrderId());
        updateOrderDO.setAgentCode(requestDTO.getAgentCode());
        updateOrderDO.setAgentName(requestDTO.getAgentName());
        updateOrderDO.setContractName(requestDTO.getContractName());
        updateOrderDO.setContractPhone(requestDTO.getContractPhone());
        if (requestDTO.getBalanceMethod()!=null){
            updateOrderDO.setBalanceMethod(requestDTO.getBalanceMethod().byteValue());
        }
        updateOrderDO.setModifier(requestDTO.getModifier());
        updateOrderDO.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(updateOrderDO);

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("修改分销商：");
        if (!StringUtils.equals(requestDTO.getContractName(), orderDO.getContractName())) {
            content.append("联系人姓名由" + orderDO.getContractName() + "变更为" + requestDTO.getContractName() + "；");
        }
        if (!StringUtils.equals(requestDTO.getContractPhone(), orderDO.getContractPhone())) {
            content.append("联系人电话由" + orderDO.getContractPhone() + "变更为" + requestDTO.getContractPhone() + "；");
        }
        logDO.setContent(content.toString());
        logDO.setOperateObject("--");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;

    }

    @Override
    @Transactional
    public ResponseDTO changeChannelOrderCode(ChangeChannelOrderCodeRequestDTO requestDTO) {
        log.info("changeChannelOrderCode param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 更新订单
        OrderDO updateOrderDO = new OrderDO();
        updateOrderDO.setId(requestDTO.getOrderId());
        updateOrderDO.setCustomerOrderCode(requestDTO.getCustomerOrderCode());
        updateOrderDO.setChannelOrderStatus(requestDTO.getChannelOrderStatus());
        updateOrderDO.setModifier(requestDTO.getModifier());
        updateOrderDO.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(updateOrderDO);

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("修改渠道信息");
        if (!StringUtils.isEmpty(requestDTO.getCustomerOrderCode())) {
            content.append("，渠道单号：" + requestDTO.getCustomerOrderCode());
        }
        if (!StringUtils.isEmpty(requestDTO.getChannelOrderStatus())) {
            content.append("，渠道订单状态：" + requestDTO.getChannelOrderStatus());
        }
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        logDO.setContent(content.toString());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;

    }

    @Override
    @Transactional
    public ResponseDTO changeBalanceMethod(ChangeBalanceMethodRequestDTO requestDTO) {
        log.info("changeBalanceMethod param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 检查订单
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.CANCELED.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单已取消！");
            return responseDTO;
        }
        if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.TRADED.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单已确认！");
            return responseDTO;
        }
        if (orderDO.getBalanceMethod().intValue() == BalanceMethodEnum.SINGLE.key
                && !requestDTO.getBalanceMethod().equals(BalanceMethodEnum.SINGLE.key)) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("不支持单结改为非单结！");
            return responseDTO;
        }


        // 2. 更新订单
        OrderDO updateOrderDO = new OrderDO();
        updateOrderDO.setId(requestDTO.getOrderId());
        updateOrderDO.setBalanceMethod(requestDTO.getBalanceMethod().byteValue());
        if (orderDO.getBalanceMethod() != BalanceMethodEnum.SINGLE.key
                && requestDTO.getBalanceMethod().equals(BalanceMethodEnum.SINGLE.key)) {
            updateOrderDO.setPayStatus(Byte.valueOf(PayStatusEnum.UN_PAID.key + ""));
        }
        updateOrderDO.setModifier(requestDTO.getModifier());
        updateOrderDO.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(updateOrderDO);

        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        logDO.setContent("修改订单结算方式：由" + BalanceMethodEnum.getValueByKey(orderDO.getBalanceMethod())
                + "变更为" + BalanceMethodEnum.getValueByKey(requestDTO.getBalanceMethod()));
        logDO.setOperateObject("--");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;

    }

    @Override
    @Transactional
    public ResponseDTO changeGuest(ChangeGuestRequestDTO requestDTO) {
        log.info("changeGuest param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        String guestNames = StringUtil.listToSQLString((requestDTO.getGuestList()));
        if (StringUtils.equals(orderDO.getGuestNames(), guestNames)) {
            responseDTO.setResult(Constant.YES);
            return responseDTO;
        }

        // 1.更新订单表
        OrderDO updateOrderDO = new OrderDO();
        updateOrderDO.setId(requestDTO.getOrderId());

        updateOrderDO.setGuestNames(guestNames);
        updateOrderDO.setModifier(requestDTO.getModifier());
        updateOrderDO.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(updateOrderDO);

        // 2.更新入住人表
        GuestDO guestDelete = new GuestDO();
        guestDelete.setOrderId(requestDTO.getOrderId());
        guestMapper.delete(guestDelete);
        List<GuestDO> guestList = new ArrayList<>();
        for (String guest : requestDTO.getGuestList()) {
            GuestDO guestDO = new GuestDO();
            guestDO.setOrderId(requestDTO.getOrderId());
            guestDO.setGuestName(guest);
            guestDO.setCreator(requestDTO.getCreator());
            guestDO.setCreateTime(requestDTO.getCreateTime());
            guestList.add(guestDO);
        }
        guestMapper.insertList(guestList);

        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        logDO.setContent("修改客人：由" + (StringUtils.isEmpty(orderDO.getGuestNames()) ? "--" : orderDO.getGuestNames()) + "变更为" + guestNames);
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO changeSpecialRequest(ChangeSpecialReqeustDTO requestDTO) {
        log.info("changeSpecialRequest param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 更新订单表
        OrderDO updateOrderDO = new OrderDO();
        updateOrderDO.setId(requestDTO.getOrderId());
        updateOrderDO.setSpecialRequest(requestDTO.getSpecialRequest());
        updateOrderDO.setModifier(requestDTO.getModifier());
        updateOrderDO.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(updateOrderDO);

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        logDO.setContent("修改客人特殊要求：" + requestDTO.getSpecialRequest());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO changeGuide(ChangeGuideRequestDTO requestDTO) {
        log.info("changeGuide param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 更新订单表
        OrderDO updateOrderDO = new OrderDO();
        updateOrderDO.setId(requestDTO.getOrderId());
        if (StringUtils.isNotEmpty(requestDTO.getGroupNo())) {
            updateOrderDO.setGroupNo(requestDTO.getGroupNo());
        }
        if (StringUtils.isNotEmpty(requestDTO.getGuide())) {
            updateOrderDO.setGuide(requestDTO.getGuide());
        }
        if (StringUtils.isNotEmpty(requestDTO.getGuidePhone())) {
            updateOrderDO.setGuidePhone(requestDTO.getGuidePhone());
        }
        updateOrderDO.setModifier(requestDTO.getModifier());
        updateOrderDO.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(updateOrderDO);

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("修改团号或向导信息：");
        if (StringUtils.isNotEmpty(requestDTO.getGroupNo())) {
            content.append("团号：" + requestDTO.getGroupNo());
        }
        if (StringUtils.isNotEmpty(requestDTO.getGuide())) {
            content.append("导游：" + requestDTO.getGuide());
        }
        if (StringUtils.isNotEmpty(requestDTO.getGuidePhone())) {
            content.append("导游电话：" + requestDTO.getGuidePhone());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getModifier());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO preBooking(CreateOrderRequestDTO requestDTO) {
        log.info("preBooking param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 分销商有效性、月结分销商信用额度
        SingleAgentRequestDTO singleAgentRequestDTO = new SingleAgentRequestDTO();
        singleAgentRequestDTO.setAgentCode(requestDTO.getAgentCode());
        ResponseDTO<SingleAgentResponseDTO> agentResponse = agentService.getAgentById(singleAgentRequestDTO);
        if (agentResponse == null || agentResponse.getModel() == null) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
            responseDTO.setFailReason("分销商【" + requestDTO.getAgentCode() + "】不存在");
            return responseDTO;
        }
        SingleAgentResponseDTO agentInfo = agentResponse.getModel();
        if (Constant.NO.equals(agentInfo.getIsActive())) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
            responseDTO.setFailReason("分销商【" + agentInfo.getAgentName() + "】已停用");
            return responseDTO;
        }
        if (!agentInfo.getBillingMethod().equals(BalanceMethodEnum.SINGLE.key) && (agentInfo.getAmount().compareTo(requestDTO.getOrderSum()) == -1)) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
            responseDTO.setFailReason("分销商【" + agentInfo.getAgentName() + "】信用额度不够");
            return responseDTO;
        }
        List<CreateOrderProductRequestDTO> ratePlanList = requestDTO.getRatePlanList();
        if (requestDTO.getIsGroupRoom().equals(Constant.NO) && ratePlanList.size() > 1) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
            responseDTO.setFailReason("散房订单不能预订多个产品");
            return responseDTO;
        }


        BigDecimal orderSum = BigDecimal.ZERO;
        for (CreateOrderProductRequestDTO rateplanRequestDTO : ratePlanList) {
            // 计算订单总售价
            for (SupplyProductPriceDTO productPriceRequest : rateplanRequestDTO.getDayList()) {
                orderSum = orderSum.add(productPriceRequest.getSalePrice().multiply(new BigDecimal(rateplanRequestDTO.getRoomNum())));
            }
            boolean productIsExist = rateplanRequestDTO.getRateplanId() != null && rateplanRequestDTO.getRateplanId() > 0;
            if (!productIsExist) {
                continue;
            }
            // 2. 校验产品有效性
            PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
            pricePlanQueryDTO.setPricePlanId(rateplanRequestDTO.getRateplanId().longValue());
            ResponseDTO<PricePlanResponseDTO> pricePlanResponseDTO = pricePlanService.queryPricePlanInfo(pricePlanQueryDTO);
            PricePlanResponseDTO ratePlanResponseDTO = pricePlanResponseDTO.getModel();
            if (pricePlanResponseDTO.getResult().equals(ResultCodeEnum.FAILURE.code) || ratePlanResponseDTO == null) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                responseDTO.setFailReason("产品【" + rateplanRequestDTO.getRateplanName() + "】不存在");
                return responseDTO;
            }
            if (ratePlanResponseDTO.getIsActive().equals(Constant.NO)) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                responseDTO.setFailReason("产品【" + rateplanRequestDTO.getRateplanName() + "】已失效");
                return responseDTO;
            }

            // 3. 校验产品上下架状态，modify by zhengxiongwei 手工单不用校验上下架。
            if (null!= requestDTO.getIsManualOrder() && 1 != requestDTO.getIsManualOrder()){

                MerchantSaleChannelDO merchantSaleChannelDO = new MerchantSaleChannelDO();
                merchantSaleChannelDO.setMerchantCode(requestDTO.getMerchantCode());
                ResponseDTO<SaleStateResponseDTO> saleStateResponseDTO = saleStateService.pricePlanIsOnSale(pricePlanQueryDTO, merchantSaleChannelDO);
                SaleStateResponseDTO saleStateDTO = saleStateResponseDTO.getModel();
                if (saleStateDTO == null) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                    responseDTO.setFailReason("产品【" + ratePlanResponseDTO.getPricePlanName() + "】未上架");
                    return responseDTO;
                }
                Integer saleStatus = null;
                if (requestDTO.getChannelCode().equals(ChannelTypeEnum.B2B.key)) {
                    saleStatus = saleStateDTO.getB2bSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.CTRIP.key)) {
                    saleStatus = saleStateDTO.getCtripSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.ELONG.key)) {
                    saleStatus = saleStateDTO.getElongSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.TONGCHENG.key)) {
                    saleStatus = saleStateDTO.getTongchengSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.TUNIU.key)) {
                    saleStatus = saleStateDTO.getTuniuSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.XMD.key)) {
                    saleStatus = saleStateDTO.getXmdSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.JD.key)) {
                    saleStatus = saleStateDTO.getJdSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.QUNAR.key)) {
                    saleStatus = saleStateDTO.getQunarSaleState();
//                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.QUNAR_B2B.key)) {
//                    saleStatus = saleStateDTO.getQunarB2BSaleState();
//                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.QUNAR_NGT.key)) {
//                    saleStatus = saleStateDTO.getQunarNgtSaleState();
//                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.QUNAR_USD.key)) {
//                    saleStatus = saleStateDTO.getQunarUsdSaleState();
                } else if (requestDTO.getChannelCode().equals(ChannelTypeEnum.TAOBAO.key)) {
                    saleStatus = saleStateDTO.getTaobaoSaleState();
                }
                if (SaleStateEnum.ON_SALE.key != saleStatus) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                    responseDTO.setFailReason("产品【" + ratePlanResponseDTO.getPricePlanName() + "】未上架");
                    return responseDTO;
                }
            }
            // 4. 校验产品价格、校验配额
            Map<String, SupplyProductPriceResponseDTO> productPriceMap = orderCommonService.getProductDailyPriceMap(requestDTO.getChannelCode(),
                    rateplanRequestDTO.getRateplanId(), rateplanRequestDTO.getCheckinDate(), rateplanRequestDTO.getCheckoutDate(), rateplanRequestDTO.getRoomNum(), requestDTO.getIsGroupRoom());
            List<SupplyProductPriceDTO> dayList = rateplanRequestDTO.getDayList();
            for (SupplyProductPriceDTO productPriceRequest : dayList) {
                SupplyProductPriceResponseDTO productPriceResponse = productPriceMap.get(productPriceRequest.getSaleDate());
                if (productPriceRequest == null || productPriceResponse == null || productPriceResponse.getSalePrice() == null) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                    responseDTO.setFailReason("产品【" + ratePlanResponseDTO.getPricePlanName() + "】在" + productPriceRequest.getSaleDate() + "号无价格");
                    return responseDTO;
                }
                if (productPriceRequest.getSalePrice().compareTo(productPriceResponse.getSalePrice()) != 0) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                    responseDTO.setFailReason("产品【" + ratePlanResponseDTO.getPricePlanName() + "】在" + productPriceRequest.getSaleDate() + "号价格已变化");
                    return responseDTO;
                }
                // 下散房订单才需要校验配额
                if (requestDTO.getIsGroupRoom() == 0) {
                    if (productPriceResponse.getQuotaNum() == null || productPriceResponse.getQuotaState() == 0) {
                        responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                        responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                        responseDTO.setFailReason("产品【" + ratePlanResponseDTO.getPricePlanName() + "】在" + productPriceRequest.getSaleDate() + "号无配额");
                        return responseDTO;
                    }
                    if (productPriceResponse.getOverDraft() == 0 && rateplanRequestDTO.getRoomNum() > productPriceResponse.getQuotaNum()) {
                        responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                        responseDTO.setFailCode(ErrorCodeEnum.PRE_BOOKING_FAILED.errorCode);
                        responseDTO.setFailReason("产品【" + ratePlanResponseDTO.getPricePlanName() + "】在" + productPriceRequest.getSaleDate() + "号配额不够");
                        return responseDTO;
                    }
                }
                if (productPriceResponse.getBasePrice() == null) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);

                    responseDTO.setFailReason("产品【" + ratePlanResponseDTO.getPricePlanName() + "】在" + productPriceRequest.getSaleDate() + "号无底价");
                    return responseDTO;
                }
            }
        }

        // 5.附加项
        List<CreateOrderAdditionChargeRequestDTO> additionChargeList = requestDTO.getAdditionChargeList();
        if (!CollectionUtils.isEmpty(additionChargeList)) {
            for (CreateOrderAdditionChargeRequestDTO additionChargeDTO : additionChargeList) {
                orderSum = orderSum.add(additionChargeDTO.getSalePrice().multiply(new BigDecimal(additionChargeDTO.getNum()).multiply(new BigDecimal(additionChargeDTO.getDays()))));
            }
        }
        // 6.减免政策
        List<DeratePolicyRequestDTO> deratePolicyList = requestDTO.getDeratePolicyList();
        if (!CollectionUtils.isEmpty(deratePolicyList)) {
            for (DeratePolicyRequestDTO deratePolicyRequestDTO : deratePolicyList) {
                List<DeratePolicyPriceDTO> dayList = deratePolicyRequestDTO.getDayList();
                if (!CollectionUtils.isEmpty(dayList)) {
                    for (DeratePolicyPriceDTO deratePolicyPriceDTO : dayList) {
                        deratePolicyPriceDTO.setSalePrice(deratePolicyPriceDTO.getSalePrice() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getSalePrice());
                        deratePolicyPriceDTO.setRoomNum(deratePolicyPriceDTO.getRoomNum() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getRoomNum());
                        deratePolicyPriceDTO.setSalePrice(deratePolicyPriceDTO.getSalePrice().negate());
                        orderSum = orderSum.add(deratePolicyPriceDTO.getSalePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                    }
                }
            }
        }

        // 7. 校验订单总价
        if (orderSum.compareTo(requestDTO.getOrderSum()) != 0) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("下单失败，订单总额已变化");
            return responseDTO;
        }


        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO create(CreateOrderRequestDTO requestDTO) {
        log.info("createOrder param: {}", requestDTO);
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        // 1. 试预定
        ResponseDTO preBookingResponse = preBooking(requestDTO);
        if (preBookingResponse.getResult().equals(ResultCodeEnum.FAILURE.code)) {
            return preBookingResponse;
        }
        // 2. 组装下单数据
        requestDTO.setIsManualOrder(Constant.NO);
        AssemblyCreateOrderResponseDTO assemblyOrderData = assemblyOrderData(requestDTO);
        // 3. 下单
        Integer orderId = orderCommonService.booking(assemblyOrderData);
        // 4. 扣配额：调用产品模块，散房扣配额；团房不扣配额，扣配额失败，不影响下单
        debuctQuota(assemblyOrderData, orderId);
        // 5. 缓存到Redis
        OrderMessageRedisCacheDTO cacheRequestDTO = PropertyCopyUtil.transfer(assemblyOrderData, OrderMessageRedisCacheDTO.class);
        cacheRequestDTO.setOrderType(1);
        orderCommonService.saveOrderMessageToRedis(cacheRequestDTO);

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(orderId);
        return responseDTO;
    }

    @Override
    public ResponseDTO addManualOrder(CreateOrderRequestDTO requestDTO) {
        log.info("addManualOrder param: {}", JSON.toJSONString(requestDTO));
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        // 1. OTA渠道订单需要验重， 如果订单已存在直接返回
        if (!StringUtils.equals(requestDTO.getChannelCode(), ChannelTypeEnum.B2B.key) && StringUtils.isNotEmpty(requestDTO.getCustomerOrderCode())) {
            OrderDO orderQuery = new OrderDO();
            orderQuery.setChannelCode(requestDTO.getChannelCode());
            orderQuery.setCustomerOrderCode(requestDTO.getCustomerOrderCode());
            List<OrderDO> orderDOS = orderMapper.select(orderQuery);
            if (!CollectionUtils.isEmpty(orderDOS)) {
                responseDTO.setResult(Constant.YES);
                responseDTO.setModel(orderDOS.get(0).getId());
                return responseDTO;
            }
        }
        // 2. 组装下单数据
        if (requestDTO.getIsManualOrder() == null) {
            requestDTO.setIsManualOrder(Constant.YES);
        }
        AssemblyCreateOrderResponseDTO assemblyOrderData = assemblyOrderData(requestDTO);

        //0.1-B2B渠道疑似重复单：酒店相同，入离日期相同，客人姓名相同，modify by zhengxiongwei
        if (requestDTO.getIsImportRepeat()==0
            && ChannelTypeEnum.B2B.key.equals(assemblyOrderData.getChannelCode()) && isSameOrder(assemblyOrderData)){
            log.error("下单失败：客人姓名重复，{}", JSON.toJSONString(assemblyOrderData));
            responseDTO = new ResponseDTO<>(ErrorCodeEnum.ORDER_BOOK_SAME_ORDER_ERROR);
            return responseDTO;
        }

        // 3. 下单
        Integer orderId = orderCommonService.booking(assemblyOrderData);
        // 4. 扣配额：调用产品模块，散房扣配额；团房不扣配额，扣配额失败，不影响下单
        debuctQuota(assemblyOrderData, orderId);
        // 5. 缓存到Redis
        OrderMessageRedisCacheDTO cacheRequestDTO = PropertyCopyUtil.transfer(assemblyOrderData, OrderMessageRedisCacheDTO.class);
        cacheRequestDTO.setOrderType(1);
        orderCommonService.saveOrderMessageToRedis(cacheRequestDTO);

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(orderId);
        return responseDTO;
    }

    private boolean isSameOrder(AssemblyCreateOrderResponseDTO assemblyOrderData){
        boolean sameOrder = false;

        Example sameOrderExample = new Example(OrderDO.class);
        Example.Criteria sameOrderCriteria = sameOrderExample.createCriteria();
        sameOrderCriteria.andEqualTo("hotelId",assemblyOrderData.getHotelId());
        sameOrderCriteria.andEqualTo("channelCode",assemblyOrderData.getChannelCode());
        sameOrderCriteria.andEqualTo("checkinDate",assemblyOrderData.getCheckinDate());
        sameOrderCriteria.andEqualTo("checkoutDate",assemblyOrderData.getCheckoutDate());
        sameOrderCriteria.andEqualTo("guestNames",assemblyOrderData.getGuestNames());
        List<OrderDO> sameOrderList = orderMapper.selectByExample(sameOrderExample);
        sameOrder = !CollectionUtils.isEmpty(sameOrderList) && sameOrderList.size() > 0;
        return sameOrder;
    }

    /**
     * 创建核销单
     */
    @Override
    public ResponseDTO addAbatementOrder(AddAbatementOrderDTO requestDTO){
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        //组装订单信息
        AssemblyCreateOrderResponseDTO assemblyOrderData=assemblyAbatementOrderData(requestDTO);
        //生成订单
        Integer orderId = orderCommonService.booking(assemblyOrderData);
        OrderDO newOrderDO = orderMapper.selectByPrimaryKey(orderId);
        //更新原单的关联订单号
        OrderDO orderParam=new OrderDO();
        orderParam.setId(requestDTO.getOrderId());
        orderParam.setRelationOrderCode(newOrderDO.getOrderCode());
        orderMapper.updateByPrimaryKeySelective(orderParam);
        responseDTO.setModel(orderId);
        return responseDTO;
    }

    private AssemblyCreateOrderResponseDTO assemblyAbatementOrderData(AddAbatementOrderDTO requestDTO){
        //设置订单信息
        AssemblyCreateOrderResponseDTO assemblyOrderData=new AssemblyCreateOrderResponseDTO();
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        assemblyOrderData.setOrderStatus(OrderStatusEnum.NEWORDER.key);
        assemblyOrderData.setPayMethod(PayMethodEnum.PRE_PAY.key);
        assemblyOrderData.setPayStatus(PayStatusEnum.UN_PAID.key);
        assemblyOrderData.setOrderSum(requestDTO.getAgentAmount());
        assemblyOrderData.setReceivableAmount(requestDTO.getAgentAmount());
        assemblyOrderData.setCreator(requestDTO.getOperator());
        assemblyOrderData.setIsAbatementOrder(1);
        assemblyOrderData.setRelationOrderCode(orderDO.getOrderCode());

        assemblyOrderData.setSaleCurrency(orderDO.getSaleCurrency());
        assemblyOrderData.setChannelCode(orderDO.getChannelCode());
        assemblyOrderData.setIsManualOrder(orderDO.getIsManualOrder().intValue());
        assemblyOrderData.setBalanceMethod(orderDO.getBalanceMethod().intValue());
        assemblyOrderData.setAgentCode(orderDO.getAgentCode());
        assemblyOrderData.setAgentName(orderDO.getAgentName());
        assemblyOrderData.setMerchantCode(orderDO.getMerchantCode());
        assemblyOrderData.setMerchantName(orderDO.getMerchantName());
        assemblyOrderData.setSpecialRequest(orderDO.getSpecialRequest());
        assemblyOrderData.setContractName(orderDO.getContractName());
        assemblyOrderData.setContractPhone(orderDO.getContractPhone());
        assemblyOrderData.setBelongUser(orderDO.getBelongUser());
        assemblyOrderData.setBelongName(orderDO.getBelongName());
        assemblyOrderData.setCityCode(orderDO.getCityCode());
        assemblyOrderData.setCityName(orderDO.getCityName());
        assemblyOrderData.setHotelId(orderDO.getHotelId());
        assemblyOrderData.setHotelName(orderDO.getHotelName());
        assemblyOrderData.setRoomTypeNames(orderDO.getRoomTypeNames());
        assemblyOrderData.setCheckinDate(DateUtil.dateToString(orderDO.getCheckinDate()));
        assemblyOrderData.setCheckoutDate(DateUtil.dateToString(orderDO.getCheckoutDate()));
        assemblyOrderData.setRoomNum(orderDO.getRoomNum());
        assemblyOrderData.setIsGroupRoom(orderDO.getIsGroupRoom().intValue());
        assemblyOrderData.setGuestNames(orderDO.getGuestNames());
        assemblyOrderData.setBreakfastType(orderDO.getBreakfastType().intValue());
        assemblyOrderData.setGroupNo(orderDO.getGroupNo());
        assemblyOrderData.setMerchantBm(orderDO.getMerchantBm());
        assemblyOrderData.setMerchantPm(orderDO.getMerchantPm());
        assemblyOrderData.setMerchantOp(orderDO.getMerchantOp());
        assemblyOrderData.setGuide(orderDO.getGuide());
        assemblyOrderData.setGuidePhone(orderDO.getGuidePhone());
        assemblyOrderData.setRateplanName(orderDO.getRateplanName());
        assemblyOrderData.setCancelPolicy(orderDO.getCancelPolicy());
        assemblyOrderData.setRate(orderDO.getRate());
        assemblyOrderData.setProfit(orderDO.getProfit());

        //设置入住人信息
        GuestDO guestParam=new GuestDO();
        guestParam.setOrderId(requestDTO.getOrderId());
        List<GuestDO> guestDOList=guestMapper.select(guestParam);
        if (guestDOList.size()>0){
            List<GuestDTO> guestList=new ArrayList<>();
            for (GuestDO guestDO:guestDOList){
                GuestDTO guestDTO=new GuestDTO();
                BeanUtils.copyProperties(guestDO,guestDTO);
                guestDTO.setId(null);
                guestDTO.setOrderId(null);
                guestDTO.setModifier(null);
                guestDTO.setModifyTime(null);
                guestDTO.setCreator(requestDTO.getOperator());
                guestDTO.setCreateTime(new Date());
                guestList.add(guestDTO);
            }
            assemblyOrderData.setGuestList(guestList);
        }

        OrderFinanceDTO orderFinanceDTO=new OrderFinanceDTO();
        orderFinanceDTO.setSettlementAmount(BigDecimal.ZERO);
        orderFinanceDTO.setRefundAmount(BigDecimal.ZERO);
        orderFinanceDTO.setSettlementStatus(Byte.valueOf("0"));
        orderFinanceDTO.setAccountStatus(Byte.valueOf("0"));
        orderFinanceDTO.setFinanceLockStatus(Byte.valueOf("2"));
        orderFinanceDTO.setCreator(requestDTO.getOperator());
        orderFinanceDTO.setCreateTime(new Date());
        assemblyOrderData.setOrderFinanceDTO(orderFinanceDTO);

        //设置供货单信息
        SupplyOrderDO supplyOrderParam=new SupplyOrderDO();
        supplyOrderParam.setOrderId(requestDTO.getOrderId());
        supplyOrderParam.setSupplyCode(requestDTO.getSupplyCode());
        List<SupplyOrderDO> supplyOrderDOList=supplyOrderMapper.select(supplyOrderParam);
        SupplyOrderResponseDTO supplyOrderResponseDTO=new SupplyOrderResponseDTO();
        supplyOrderResponseDTO.setBaseCurrency(supplyOrderDOList.get(0).getBaseCurrency());
        supplyOrderResponseDTO.setBalanceMethod(supplyOrderDOList.get(0).getBalanceMethod().intValue());
        supplyOrderResponseDTO.setRate(supplyOrderDOList.get(0).getRate());

        supplyOrderResponseDTO.setSupplyStatus(SupplyStatusEnum.UN_SEND.key);
        supplyOrderResponseDTO.setPayStatus(PayStatusEnum.UN_PAID.key);
        if (BalanceMethodEnum.SINGLE.key != supplyOrderResponseDTO.getBalanceMethod()) {
            supplyOrderResponseDTO.setPayStatus(PayStatusEnum.UN_CREDIT.key);
        }
        supplyOrderResponseDTO.setSupplyWay(SupplyWayEnum.SupplyWayEnum.key);
        supplyOrderResponseDTO.setSupplySum(requestDTO.getSupplyAmount());
        supplyOrderResponseDTO.setSupplyCode(requestDTO.getSupplyCode());
        supplyOrderResponseDTO.setSupplyName(requestDTO.getSupplyName());
        supplyOrderResponseDTO.setPayableAmount(requestDTO.getSupplyAmount());
        supplyOrderResponseDTO.setSalePriceSum(assemblyOrderData.getOrderSum());
        supplyOrderResponseDTO.setCreator(requestDTO.getOperator());

        supplyOrderResponseDTO.setHotelId(assemblyOrderData.getHotelId());
        supplyOrderResponseDTO.setHotelName(assemblyOrderData.getHotelName());
        supplyOrderResponseDTO.setCheckinDate(assemblyOrderData.getCheckinDate());
        supplyOrderResponseDTO.setCheckoutDate(assemblyOrderData.getCheckoutDate());
        supplyOrderResponseDTO.setRoomNum(assemblyOrderData.getRoomNum());
        supplyOrderResponseDTO.setRoomTypeName(assemblyOrderData.getRoomTypeNames());
        supplyOrderResponseDTO.setRateplanName(assemblyOrderData.getRateplanName());
        supplyOrderResponseDTO.setMerchantBm(assemblyOrderData.getMerchantBm());
        supplyOrderResponseDTO.setMerchantPm(assemblyOrderData.getMerchantPm());
        supplyOrderResponseDTO.setMerchantOp(assemblyOrderData.getMerchantOp());

        SupplyAdditionChargeDTO supplyAdditionChargeDTO=new SupplyAdditionChargeDTO();
        supplyAdditionChargeDTO.setName(requestDTO.getName());
        supplyAdditionChargeDTO.setBasePriceSum(requestDTO.getSupplyAmount());
        supplyAdditionChargeDTO.setSalePriceSum(requestDTO.getAgentAmount());
        supplyAdditionChargeDTO.setNum(1);
        supplyAdditionChargeDTO.setDays(1);
        supplyAdditionChargeDTO.setAdditionType(1);
        supplyAdditionChargeDTO.setBasePrice(requestDTO.getSupplyAmount());
        supplyAdditionChargeDTO.setSalePrice(requestDTO.getAgentAmount());
        supplyOrderResponseDTO.setSupplyAdditionChargeList(Arrays.asList(supplyAdditionChargeDTO));

        SupplyFinanceDTO supplyFinanceDTO = new SupplyFinanceDTO();
        supplyFinanceDTO.setSettlementAmount(BigDecimal.ZERO);
        supplyFinanceDTO.setReceiptAmount(BigDecimal.ZERO);
        supplyFinanceDTO.setSettlementStatus(Byte.valueOf("0"));
        supplyFinanceDTO.setAccountStatus(Byte.valueOf("0"));
        supplyFinanceDTO.setCreator(requestDTO.getOperator());
        supplyFinanceDTO.setCreateTime(new Date());
        supplyOrderResponseDTO.setSupplyFinanceDTO(supplyFinanceDTO);

        supplyOrderResponseDTO.setSupplyProductList(new ArrayList<>());
        assemblyOrderData.setSupplyOrderList(Arrays.asList(supplyOrderResponseDTO));
        return assemblyOrderData;
    }

    /**
     * 扣配额
     *
     * @param assemblyOrderData
     * @param orderId
     */
    private void debuctQuota(AssemblyCreateOrderResponseDTO assemblyOrderData, Integer orderId) {
        SupplyProductResponseDTO product = assemblyOrderData.getSupplyOrderList().get(0).getSupplyProductList().get(0);
        if (Constant.NO.equals(assemblyOrderData.getIsGroupRoom())) {
            OrderDO order = orderMapper.selectByPrimaryKey(orderId);
            SupplyOrderDO supplyOrder = supplyOrderMapper.selectByPrimaryKey(product.getSupplyOrderId());
            List<String> dateList = new ArrayList<>();
            for (SupplyProductPriceDTO productPriceDTO : product.getProductPriceDTOList()) {
                dateList.add(productPriceDTO.getSaleDate());
            }
            OrderDebuctOrRetreatQuotaRequestDTO debuctRequestDTO = new OrderDebuctOrRetreatQuotaRequestDTO();
            debuctRequestDTO.setDebuctQuotaType(1);
            debuctRequestDTO.setOrderCode(order.getOrderCode());
            debuctRequestDTO.setDateList(dateList);
            debuctRequestDTO.setQuotaAccountId(product.getQuotaAccountId());
            debuctRequestDTO.setRatePlanId(product.getRateplanId());
            debuctRequestDTO.setRoomNum(product.getRoomNum());
            debuctRequestDTO.setSupplyOrderCode(supplyOrder.getSupplyOrderCode());
            debuctRequestDTO.setSupplyOrderId(product.getSupplyOrderId());
            debuctRequestDTO.setStartDate(DateUtil.stringToDate(product.getCheckinDate()));
            debuctRequestDTO.setEndDate(DateUtil.stringToDate(product.getCheckoutDate()));
            debuctRequestDTO.setMerchantCode(order.getMerchantCode());
            debuctRequestDTO.setOrderId(orderId);
            orderCommonService.debuctOrRetreatQuota(debuctRequestDTO);
        }
    }


    /**
     * 组装下单数据
     *
     * @param requestDTO
     * @return
     */
    private AssemblyCreateOrderResponseDTO assemblyOrderData(CreateOrderRequestDTO requestDTO) {
        // 1. 组装订单信息
        AssemblyCreateOrderResponseDTO orderData = new AssemblyCreateOrderResponseDTO();
        BeanUtils.copyProperties(requestDTO, orderData);
        orderData.setOrderStatus(OrderStatusEnum.NEWORDER.key);
        orderData.setPayMethod(PayMethodEnum.PRE_PAY.key);
        orderData.setPayStatus(PayStatusEnum.UN_PAID.key);
        orderData.setOrderSum(requestDTO.getOrderSum());
        orderData.setReceivableAmount(requestDTO.getOrderSum());
        orderData.setCreator(requestDTO.getCreator());
        orderData.setCreateTime(DateUtil.dateToStringWithHms(requestDTO.getCreateTime()));
        // 查分销商信息
        SingleAgentRequestDTO singleAgentRequestDTO = new SingleAgentRequestDTO();
        singleAgentRequestDTO.setAgentCode(requestDTO.getAgentCode());
        ResponseDTO<SingleAgentResponseDTO> agentResponse = agentService.getAgentById(singleAgentRequestDTO);
        SingleAgentResponseDTO agentInfo = agentResponse.getModel();
        orderData.setSaleCurrency(agentInfo.getFinanceCurrency());
        orderData.setBalanceMethod(agentInfo.getBillingMethod());
        if (BalanceMethodEnum.SINGLE.key != agentInfo.getBillingMethod()) {
            orderData.setPayStatus(PayStatusEnum.UN_CREDIT.key);
        }
        orderData.setAgentName(agentInfo.getAgentName());
        if (!CollectionUtils.isEmpty(agentInfo.getUserList())) {
            UserInfoDTO userInfoDTO = agentInfo.getUserList().get(0);
            if (StringUtils.isEmpty(orderData.getContractName())) {
                orderData.setContractName(userInfoDTO.getRealName());
            }
            if (StringUtils.isEmpty(orderData.getContractPhone())) {
                orderData.setContractPhone(userInfoDTO.getUserName());
            }
            if (StringUtils.isEmpty(orderData.getCreator())) {
                orderData.setCreator(userInfoDTO.getRealName());
            }
            orderData.setMerchantBm(agentInfo.getMerchantBMName());
            orderData.setMerchantPm("");
            orderData.setMerchantOp("");
        }
        if (!CollectionUtils.isEmpty(requestDTO.getGuestList())) {
            orderData.setGuestNames(StringUtil.listToSQLString(requestDTO.getGuestList()));
        }
        orderData.setNote(requestDTO.getNote());

        // 2. 组装订单财务
        OrderFinanceDTO orderFinanceDTO = new OrderFinanceDTO();
        orderFinanceDTO.setSettlementAmount(BigDecimal.ZERO);
        orderFinanceDTO.setRefundAmount(BigDecimal.ZERO);
        orderFinanceDTO.setSettlementStatus(Byte.valueOf("0"));
        orderFinanceDTO.setAccountStatus(Byte.valueOf("0"));
        orderFinanceDTO.setFinanceLockStatus(Byte.valueOf("2"));
        orderFinanceDTO.setCreator(requestDTO.getCreator());
        orderFinanceDTO.setCreateTime(requestDTO.getCreateTime());
        orderData.setOrderFinanceDTO(orderFinanceDTO);

        // 3. 组装入住人
        List<GuestDTO> guestList = new ArrayList<>();
        for (String guest : requestDTO.getGuestList()) {
            GuestDTO guestDTO = new GuestDTO();
            guestDTO.setGuestName(guest);
            guestDTO.setCreator(requestDTO.getCreator());
            guestDTO.setCreateTime(requestDTO.getCreateTime());
            guestList.add(guestDTO);
        }
        orderData.setGuestList(guestList);

        // 4. 组装供货单
        List<SupplyOrderResponseDTO> supplyOrderDTOList = new ArrayList<>();
        Map<String, List<CreateOrderProductRequestDTO>> supplyOrderMap = new LinkedHashMap<>();
        for (CreateOrderProductRequestDTO productDTO : requestDTO.getRatePlanList()) {
            boolean productIsExist = productDTO.getRateplanId() != null && productDTO.getRateplanId() > 0;
            // 如果产品供应商编码为空，则需要查询产品供应商编码
            if (StringUtils.isEmpty(productDTO.getSupplyCode()) && productIsExist) {
                PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
                pricePlanQueryDTO.setPricePlanId(productDTO.getRateplanId().longValue());
                ResponseDTO<PricePlanResponseDTO> pricePlanResponseDTO = pricePlanService.queryPricePlanInfo(pricePlanQueryDTO);
                PricePlanResponseDTO pricePlanResponse = pricePlanResponseDTO.getModel();
                productDTO.setSupplyCode(pricePlanResponse.getSupplyCode());
            }
            if (supplyOrderMap.containsKey(productDTO.getSupplyCode())) {
                supplyOrderMap.get(productDTO.getSupplyCode()).add(productDTO);
            } else {
                List<CreateOrderProductRequestDTO> productDTOList = new ArrayList<>();
                productDTOList.add(productDTO);
                supplyOrderMap.put(productDTO.getSupplyCode(), productDTOList);
            }
        }
        for (Map.Entry<String, List<CreateOrderProductRequestDTO>> entry : supplyOrderMap.entrySet()) {
            String supplyCode = entry.getKey();
            SupplyOrderResponseDTO supplyOrderDTO = new SupplyOrderResponseDTO();
            // 调用供应商模块，查询供应商信息，组装供货单
            SingleUserRequestDTO singleUserRequestDTO = new SingleUserRequestDTO();
            singleUserRequestDTO.setSupplyCode(supplyCode);
            ResponseDTO<SingleSupplyInfoResponseDTO> supplyResponse = supplyService.getSupplyById(singleUserRequestDTO);
            SingleSupplyInfoResponseDTO supplyInfo = supplyResponse.getModel();
            if (StringUtil.isValidString(orderData.getMerchantPm())){
                orderData.setMerchantPm(orderData.getMerchantPm()+","+supplyInfo.getMerchantPMName());
            }else{
                orderData.setMerchantPm(supplyInfo.getMerchantPMName());
            }
            if (StringUtil.isValidString(orderData.getMerchantOp())){
                for (MerchantOPDTO merchantOPDTO:supplyInfo.getMerchantOPs()){
                    orderData.setMerchantOp(orderData.getMerchantOp()+","+merchantOPDTO.getMerchantOPName());
                }
            }else{
                if (supplyInfo.getMerchantOPs().size()>0){
                    for (MerchantOPDTO merchantOPDTO:supplyInfo.getMerchantOPs()){
                        orderData.setMerchantOp(orderData.getMerchantOp()+","+merchantOPDTO.getMerchantOPName());
                    }
                    orderData.setMerchantOp(orderData.getMerchantOp().substring(1,orderData.getMerchantOp().length()));
                }
            }
            supplyOrderDTO.setMerchantBm(orderData.getMerchantBm());
            supplyOrderDTO.setMerchantPm(supplyInfo.getMerchantPMName());
            supplyOrderDTO.setMerchantOp("");
            if (supplyInfo.getMerchantOPs().size()>0){
                for (MerchantOPDTO merchantOPDTO:supplyInfo.getMerchantOPs()){
                    supplyOrderDTO.setMerchantOp(supplyOrderDTO.getMerchantOp()+","+merchantOPDTO.getMerchantOPName());
                }
                supplyOrderDTO.setMerchantOp(supplyOrderDTO.getMerchantOp().substring(1,supplyOrderDTO.getMerchantOp().length()));
            }
            supplyOrderDTO.setBaseCurrency(supplyInfo.getBaseCurrency());
            supplyOrderDTO.setSupplyCode(supplyCode);
            supplyOrderDTO.setSupplyName(supplyInfo.getSupplyName());
            supplyOrderDTO.setBalanceMethod(supplyInfo.getBillingMethod());
            supplyOrderDTO.setSupplyStatus(SupplyStatusEnum.UN_SEND.key);
            supplyOrderDTO.setPayStatus(PayStatusEnum.UN_PAID.key);
            if (BalanceMethodEnum.SINGLE.key != supplyInfo.getBillingMethod()) {
                supplyOrderDTO.setPayStatus(PayStatusEnum.UN_CREDIT.key);
            }
            supplyOrderDTO.setSupplyWay(SupplyWayEnum.SupplyWayEnum.key);
            supplyOrderDTO.setRate(BigDecimal.ONE);
            supplyOrderDTO.setCreator(requestDTO.getCreator());
            supplyOrderDTO.setCreateTime(DateUtil.dateToStringWithHms(requestDTO.getCreateTime()));
            // 5. 组装供货单财务
            SupplyFinanceDTO supplyFinanceDTO = new SupplyFinanceDTO();
            supplyFinanceDTO.setSettlementAmount(BigDecimal.ZERO);
            supplyFinanceDTO.setReceiptAmount(BigDecimal.ZERO);
            supplyFinanceDTO.setSettlementStatus(Byte.valueOf("0"));
            supplyFinanceDTO.setAccountStatus(Byte.valueOf("0"));
            supplyFinanceDTO.setCreator(requestDTO.getCreator());
            supplyFinanceDTO.setCreateTime(requestDTO.getCreateTime());
            supplyOrderDTO.setSupplyFinanceDTO(supplyFinanceDTO);

            // 6. 组装供货产品
            BigDecimal supplyBasePriceSum = BigDecimal.ZERO;
            BigDecimal supplySalePriceSum = BigDecimal.ZERO;
            List<SupplyProductResponseDTO> supplyProductDTOList = new ArrayList<>();
            List<CreateOrderProductRequestDTO> productRequestDTOList = entry.getValue();
            for (CreateOrderProductRequestDTO productRequest : productRequestDTOList) {
                SupplyProductResponseDTO productDTO = PropertyCopyUtil.transfer(productRequest, SupplyProductResponseDTO.class);
                // 下存在的产品，需查产品信息；手工单下不存在产品，需要传产品信息；
                boolean productIsExist = productRequest.getRateplanId() != null && productRequest.getRateplanId() > 0;
                if (productIsExist) {
                    PricePlanQueryDTO pricePlanQueryDTO = new PricePlanQueryDTO();
                    pricePlanQueryDTO.setPricePlanId(productRequest.getRateplanId().longValue());
                    ResponseDTO<PricePlanResponseDTO> pricePlanResponseDTO = pricePlanService.queryPricePlanInfo(pricePlanQueryDTO);
                    PricePlanResponseDTO pricePlanResponse = pricePlanResponseDTO.getModel();
                    productDTO.setRoomTypeId(pricePlanResponse.getRoomTypeId().intValue());
                    productDTO.setRoomTypeName(pricePlanResponse.getRoomTypeName());
                    productDTO.setRateplanName(pricePlanResponse.getPricePlanName());
                    productDTO.setBedtype(pricePlanResponse.getBedType());
                    productDTO.setCancelPolicy(pricePlanResponse.getCancelPolicy());
                    if (productDTO.getBreakfastType() == null) {
                        productDTO.setBreakfastType(pricePlanResponse.getBreakFastType());
                    }
                    if (productDTO.getQuotaType() == null) {
                        productDTO.setQuotaType(pricePlanResponse.getQuotaType());
                    }
                    productDTO.setQuotaAccountId(pricePlanResponse.getQuotaAccountId().intValue());
                    requestDTO.setHotelId(pricePlanResponse.getHotelId().intValue());
                    requestDTO.setHotelName(pricePlanResponse.getHotelName());
                    if (supplyOrderDTO.getHotelId() == null) {
                        supplyOrderDTO.setHotelId(pricePlanResponse.getHotelId().intValue());
                        supplyOrderDTO.setHotelName(pricePlanResponse.getHotelName());
                    }
                }
                productDTO.setCheckinDate(productRequest.getCheckinDate());
                productDTO.setCheckoutDate(productRequest.getCheckoutDate());
                productDTO.setRoomNum(productRequest.getRoomNum());
                productDTO.setCreator(requestDTO.getCreator());
                productDTO.setCreateTime(requestDTO.getCreateTime());
                // 7. 组装供货价格
                BigDecimal salePriceSum = BigDecimal.ZERO;
                BigDecimal basePriceSum = BigDecimal.ZERO;
                Map<String, SupplyProductPriceResponseDTO> productPriceMap = new HashMap<>();
                if (productIsExist) {
                    productPriceMap = orderCommonService.getProductDailyPriceMap(requestDTO.getChannelCode(),
                            productRequest.getRateplanId(), productRequest.getCheckinDate(), productRequest.getCheckoutDate(),
                            productRequest.getRoomNum(), requestDTO.getIsGroupRoom());
                }
                List<SupplyProductPriceDTO> dayList = productRequest.getDayList();
                for (SupplyProductPriceDTO productPriceDTO : dayList) {
                    BigDecimal basePrice = BigDecimal.ZERO;
                    // 没传底价，需要查产品底价<渠道过来订单>
                    if (productPriceDTO.getBasePrice() != null) {
                        basePrice = productPriceDTO.getBasePrice();
                    } else {
                        SupplyProductPriceResponseDTO productPriceResponseDTO = productPriceMap.get(productPriceDTO.getSaleDate());
                        if (productPriceResponseDTO != null && productPriceResponseDTO.getBasePrice() != null) {
                            productPriceDTO.setBasePrice(productPriceResponseDTO.getBasePrice());
                            basePrice = productPriceResponseDTO.getBasePrice();
                        } else {
                            productPriceDTO.setBasePrice(BigDecimal.ZERO);
                        }
                    }
                    salePriceSum = salePriceSum.add(productPriceDTO.getSalePrice().multiply(new BigDecimal(productRequest.getRoomNum())));
                    basePriceSum = basePriceSum.add(basePrice.multiply(new BigDecimal(productRequest.getRoomNum())));
                }
                supplySalePriceSum = supplySalePriceSum.add(salePriceSum);
                supplyBasePriceSum = supplyBasePriceSum.add(basePriceSum);
                // 8. 根据每日价格， 推演对账明细
                List<OrderCheckDetailDTO> checkDetailDTOList = orderCommonService.assemblyCheckDetail(productRequest.getRoomNum(), dayList);
                productDTO.setCheckDetailDTOList(checkDetailDTOList);
                productDTO.setProductPriceDTOList(dayList);
                productDTO.setSalePriceSum(salePriceSum);
                productDTO.setBasePriceSum(basePriceSum);
                supplyProductDTOList.add(productDTO);
            }
            supplyOrderDTO.setSupplySum(supplyBasePriceSum);
            supplyOrderDTO.setPayableAmount(supplyBasePriceSum);
            supplyOrderDTO.setSalePriceSum(supplySalePriceSum);
            if (requestDTO.getHotelId() != null) {
                supplyOrderDTO.setHotelId(requestDTO.getHotelId());
                supplyOrderDTO.setHotelName(requestDTO.getHotelName());
            }
            supplyOrderDTO.setSupplyProductList(supplyProductDTOList);
            supplyOrderDTOList.add(supplyOrderDTO);
        }
        orderData.setSupplyOrderList(supplyOrderDTOList);

        // 9. 组装供货附加项，附加项放在第一个供货单下面
        BigDecimal salePriceSum = BigDecimal.ZERO;
        BigDecimal basePriceSum = BigDecimal.ZERO;
        SupplyOrderResponseDTO firstSupplyOrderResponseDTO = orderData.getSupplyOrderList().get(0);
        if (!CollectionUtils.isEmpty(requestDTO.getAdditionChargeList())) {
            List<SupplyAdditionChargeDTO> additionChargeDTOList = PropertyCopyUtil.transferArray(requestDTO.getAdditionChargeList(), SupplyAdditionChargeDTO.class);
            for (SupplyAdditionChargeDTO additionChargeDTO : additionChargeDTOList) {
                additionChargeDTO.setAdditionType(1);
                if (additionChargeDTO.getBasePrice() == null) {
                    additionChargeDTO.setBasePrice(additionChargeDTO.getSalePrice());
                }
                additionChargeDTO.setSalePriceSum(additionChargeDTO.getSalePrice().multiply(new BigDecimal(additionChargeDTO.getNum())).multiply(new BigDecimal(additionChargeDTO.getDays())));
                additionChargeDTO.setBasePriceSum(additionChargeDTO.getSalePriceSum());
                salePriceSum = salePriceSum.add(additionChargeDTO.getSalePriceSum());
                basePriceSum = basePriceSum.add(additionChargeDTO.getBasePriceSum());
            }
            firstSupplyOrderResponseDTO.setSupplyAdditionChargeList(additionChargeDTOList);
        }

        // 10. 组装减免政策，附加项放在第一个供货单下面
        if (!CollectionUtils.isEmpty(requestDTO.getDeratePolicyList())) {
            List<DeratePolicyResponseDTO> deratePolicyDTOList = PropertyCopyUtil.transferArray(requestDTO.getDeratePolicyList(), DeratePolicyResponseDTO.class);
            for (DeratePolicyResponseDTO deratePolicyDTO : deratePolicyDTOList) {
                BigDecimal policySalePriceSum = BigDecimal.ZERO;
                BigDecimal policyBasePriceSum = BigDecimal.ZERO;
                BigDecimal roomNumSum = BigDecimal.ZERO;
                if (!CollectionUtils.isEmpty(deratePolicyDTO.getDayList())) {
                    for (DeratePolicyPriceDTO deratePolicyPriceDTO : deratePolicyDTO.getDayList()) {
                        deratePolicyPriceDTO.setSalePrice(deratePolicyPriceDTO.getSalePrice() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getSalePrice());
                        deratePolicyPriceDTO.setBasePrice(deratePolicyPriceDTO.getBasePrice() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getBasePrice());
                        deratePolicyPriceDTO.setRoomNum(deratePolicyPriceDTO.getRoomNum() == null ? BigDecimal.ZERO : deratePolicyPriceDTO.getRoomNum());
                        deratePolicyPriceDTO.setSalePrice(deratePolicyPriceDTO.getSalePrice().abs().negate());
                        deratePolicyPriceDTO.setBasePrice(deratePolicyPriceDTO.getBasePrice().abs().negate());

                        policySalePriceSum = policySalePriceSum.add(deratePolicyPriceDTO.getSalePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                        policyBasePriceSum = policyBasePriceSum.add(deratePolicyPriceDTO.getBasePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                        salePriceSum = salePriceSum.add(deratePolicyPriceDTO.getSalePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                        basePriceSum = basePriceSum.add(deratePolicyPriceDTO.getBasePrice().multiply(deratePolicyPriceDTO.getRoomNum()));
                        roomNumSum = roomNumSum.add(deratePolicyPriceDTO.getRoomNum());
                    }
                }
                deratePolicyDTO.setSalePriceSum(policySalePriceSum);
                deratePolicyDTO.setBasePriceSum(policyBasePriceSum);
                deratePolicyDTO.setRoomNumSum(roomNumSum);
                deratePolicyDTO.setCreator(requestDTO.getCreator());
                deratePolicyDTO.setCreateTime(requestDTO.getCreateTime());
                deratePolicyDTO.setDayList(deratePolicyDTO.getDayList());
            }
            firstSupplyOrderResponseDTO.setDerateList(deratePolicyDTOList);
        }

        // 11. 更新第一个供货单金额
        firstSupplyOrderResponseDTO.setSupplySum(firstSupplyOrderResponseDTO.getSupplySum().add(basePriceSum));
        firstSupplyOrderResponseDTO.setPayableAmount(firstSupplyOrderResponseDTO.getSupplySum());
        firstSupplyOrderResponseDTO.setSalePriceSum(firstSupplyOrderResponseDTO.getSalePriceSum().add(salePriceSum));
        // 12. 更新供货单冗余字段, 冗余供货单下第一个产品信息
        for (SupplyOrderResponseDTO supplyOrderResponseDTO : orderData.getSupplyOrderList()) {
            SupplyProductResponseDTO firstProduct = supplyOrderResponseDTO.getSupplyProductList().get(0);
            supplyOrderResponseDTO.setCheckinDate(firstProduct.getCheckinDate());
            supplyOrderResponseDTO.setCheckoutDate(firstProduct.getCheckoutDate());
            supplyOrderResponseDTO.setRoomNum(firstProduct.getRoomNum());
            supplyOrderResponseDTO.setRoomTypeName(firstProduct.getRoomTypeName());
            supplyOrderResponseDTO.setRateplanName(firstProduct.getRateplanName());
        }
        // 13. 更新订单冗余字段
        HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO = new HotelBaseInfoRequestDTO();
        hotelBaseInfoRequestDTO.setHotelId(firstSupplyOrderResponseDTO.getHotelId().longValue());
        ResponseDTO<HotelBaseInfoRsponseDTO> hotelBaseInfoRsponseDTO = hotelInfoService.queryHotelInfoByHotelId(hotelBaseInfoRequestDTO);
        HotelBaseInfoRsponseDTO hotelBaseInfo = hotelBaseInfoRsponseDTO.getModel();
        orderData.setCityCode(hotelBaseInfo.getCityCode());
        orderData.setCityName(hotelBaseInfo.getCityName());
        orderData.setHotelId(firstSupplyOrderResponseDTO.getHotelId());
        orderData.setHotelName(hotelBaseInfo.getHotelName());
        // 订单中冗余第一个产品信息
        SupplyProductResponseDTO firstProductDTO = firstSupplyOrderResponseDTO.getSupplyProductList().get(0);
        orderData.setBreakfastType(firstProductDTO.getBreakfastType());
        orderData.setCheckinDate(firstProductDTO.getCheckinDate());
        orderData.setCheckoutDate(firstProductDTO.getCheckoutDate());
        orderData.setRoomNum(firstProductDTO.getRoomNum());
        orderData.setRateplanName(firstProductDTO.getRateplanName());
        orderData.setCancelPolicy(firstProductDTO.getCancelPolicy());
        orderData.setRate(BigDecimal.ONE);
        // 利润 = 总应收-总应付
        BigDecimal profit = orderData.getReceivableAmount();
        for (SupplyOrderResponseDTO supplyOrderResponseDTO : orderData.getSupplyOrderList()) {
            profit = profit.subtract(supplyOrderResponseDTO.getPayableAmount());
        }
        orderData.setProfit(profit);
        return orderData;
    }

    @Override
    @Transactional
    public ResponseDTO handleOrderRequest(HandleOrderReqRequestDTO requestDTO) {
        log.info("handleOrderRequest param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. b2b订单未取消不能直接点同意取消
        OrderRequestDO orderRequestDO = orderRequestMapper.selectByPrimaryKey(requestDTO.getOrderRequestId());
        OrderDO orderDO = orderMapper.selectByPrimaryKey(orderRequestDO.getOrderId());
        if (orderDO.getChannelCode().equals(ChannelTypeEnum.B2B.key)
                && requestDTO.getHandleResult() == 1 && orderDO.getOrderStatus().intValue() != OrderStatusEnum.CANCELED.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单未取消，不能直接点同意取消");
            return responseDTO;
        }
        // 2. 修改订单申请处理状态
        OrderRequestDO orderRequestUpdate = new OrderRequestDO();
        orderRequestUpdate.setId(requestDTO.getOrderRequestId());
        orderRequestUpdate.setHandleResult(Byte.valueOf(requestDTO.getHandleResult() + ""));
        orderRequestUpdate.setNote(requestDTO.getNote());
        orderRequestUpdate.setModifier(requestDTO.getModifier());
        orderRequestUpdate.setModifyTime(requestDTO.getModifyTime());
        orderRequestMapper.updateByPrimaryKeySelective(orderRequestUpdate);
        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderRequestDO.getOrderId());
        StringBuilder content = new StringBuilder();
        content.append("处理订单申请：申请类型：" + (orderRequestDO.getRequestType().intValue() == 1 ? "取消单申请" : "修改单申请") + "，处理结果：" + (requestDTO.getHandleResult() == 1 ? "同意申请" : "拒绝申请"));
        if (StringUtils.isNotEmpty(requestDTO.getNote())) {
            content.append("，备注：" + requestDTO.getNote());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getModifier());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<OrderRequestDTO>> queryOrderRequestList(OrderDetailRequestDTO requestDTO) {
        log.info("queryOrderRequestList param: {}", requestDTO);
        ResponseDTO<List<OrderRequestDTO>> responseDTO = new ResponseDTO<>();
        if (requestDTO.getOrderId() == null) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("无效的请求参数");
            return responseDTO;
        }

        OrderRequestDO orderRequestQuery = new OrderRequestDO();
        orderRequestQuery.setOrderId(requestDTO.getOrderId());
        orderRequestQuery.setHandleResult(Byte.valueOf("0"));
        List<OrderRequestDO> orderRequestDOList = orderRequestMapper.select(orderRequestQuery);
        if (!CollectionUtils.isEmpty(orderRequestDOList)) {
            List<OrderRequestDTO> orderRequestDTOList = new ArrayList<>();
            for (OrderRequestDO orderRequestDO : orderRequestDOList) {
                OrderRequestDTO orderRequestDTO = PropertyCopyUtil.transfer(orderRequestDO, OrderRequestDTO.class);
                orderRequestDTO.setCreateTime(DateUtil.dateToStringWithHms(orderRequestDO.getCreateTime()));
                orderRequestDTOList.add(orderRequestDTO);
            }
            responseDTO.setModel(orderRequestDTOList);
        }

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<QueryChannelListResponseDTO>> queryChannelList() {
        ResponseDTO<List<QueryChannelListResponseDTO>> responseDTO = new ResponseDTO();
        List<QueryChannelListResponseDTO> channelList = new ArrayList<>();
        ChannelTypeEnum[] values = ChannelTypeEnum.values();
        for (int i = 0; i < values.length; i++) {
            ChannelTypeEnum channel = values[i];
            if (channel.key.equals("ctrip_channel_a")
                    || channel.key.equals("qunar_B2B")
                    || channel.key.equals("qunar_ngt")
                    || channel.key.equals("qunar_usd")
                    || channel.key.equals("qunar_son")) {
                continue;
            }
            QueryChannelListResponseDTO channelListResponseDTO = new QueryChannelListResponseDTO();
            channelListResponseDTO.setChannelCode(channel.key);
            channelListResponseDTO.setChannelName(channel.value);
            channelList.add(channelListResponseDTO);
        }

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(channelList);
        return responseDTO;
    }

    @Override
    public ResponseDTO notifyAgent(NotifyAgentRequestDTO requestDTO) {
        /**
         * 一期不做
         */
        log.info("notifyAgent param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 发消息

        // 2. 记日志

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<OrderCountResponseDTO>> counts(CountsRequestDTO requestDTO) {
        ResponseDTO<List<OrderCountResponseDTO>> responseDTO = new ResponseDTO();
        List<OrderCountResponseDTO> countResponseDTOList = new ArrayList<>();
        OrderCountResponseDTO countResponseDTO = new OrderCountResponseDTO();
        // 1. 统计为处理订单数量
        Example orderExample = new Example(OrderDO.class);
        Example.Criteria orderCriteria = orderExample.createCriteria();
        orderCriteria.andEqualTo("merchantCode", requestDTO.getMerchantCode());
        orderCriteria.andEqualTo("orderStatus", OrderStatusEnum.NEWORDER.key);
        // 订单列表类型： 1，B2B订单列表， 2：OTA订单列表
        if (requestDTO.getListType() == 1) {
            orderCriteria.andEqualTo("channelCode", ChannelTypeEnum.B2B.key);
        } else {
            orderCriteria.andNotEqualTo("channelCode", ChannelTypeEnum.B2B.key);
        }

        List<OrderDO> orderDOList = orderMapper.selectByExample(orderExample);
        // 快捷类型，3：未处理订单
        countResponseDTO.setQuickQueryType(3);
        countResponseDTO.setCounts(orderDOList.size());
        // 2. 统计渠道订单数量， 只有OTA订单列表需要显示渠道订单数
        if (requestDTO.getListType() == 2) {
            Map<String, Integer> channelMap = new HashMap<>(12);
            for (OrderDO orderDO : orderDOList) {
                if (channelMap.containsKey(orderDO.getChannelCode())) {
                    channelMap.put(orderDO.getChannelCode(), channelMap.get(orderDO.getChannelCode()) + 1);
                } else {
                    channelMap.put(orderDO.getChannelCode(), 1);
                }
            }
            // 将ctrip,ctrip_b2b,ctrip_channel_a 都统计到ctrip渠道下面
            Integer ctrip = 0;
            Integer ctripCounts = channelMap.get(ChannelTypeEnum.CTRIP.key);
            if (ctripCounts != null) {
                ctrip = ctrip + ctripCounts;
            }
            Integer ctripB2bCounts = channelMap.get(ChannelTypeEnum.CTRIP_B2B.key);
            if (ctripB2bCounts != null) {
                ctrip = ctrip + ctripB2bCounts;
            }
            Integer ctripChannelACounts = channelMap.get(ChannelTypeEnum.CTRIP_CHANNEL_A.key);
            if (ctripChannelACounts != null) {
                ctrip = ctrip + ctripChannelACounts;
            }
            if (ctrip > 0) {
                channelMap.put(ChannelTypeEnum.CTRIP.key, ctrip);
                channelMap.remove(ChannelTypeEnum.CTRIP_B2B.key);
                channelMap.remove(ChannelTypeEnum.CTRIP_CHANNEL_A.key);
            }

            List<OrderChannelCountResponseDTO> channelList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : channelMap.entrySet()) {
                OrderChannelCountResponseDTO channelCount = new OrderChannelCountResponseDTO();
                channelCount.setChannelCode(entry.getKey());
                channelCount.setCounts(entry.getValue());
                channelList.add(channelCount);
            }
            countResponseDTO.setChannelList(channelList);
        }
        countResponseDTOList.add(countResponseDTO);

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(countResponseDTOList);
        return responseDTO;
    }

    @Override
    public ResponseDTO addAttach(AddAttachRequestDTO requestDTO) {
        log.info("addAttach param: 订单Id：{}，附件类型：{}", requestDTO.getOrderId(), requestDTO.getAttachType());
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 校验附件
        MultipartFile[] files = requestDTO.getFiles();
        if (files == null) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("上传文件不能为空");
            return responseDTO;
        }
        // 入住名单只能上传一份<入住名单:9>
        int fileNum = files.length;
        if (requestDTO.getAttachType() == 9) {
            if (fileNum > 1) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("入住名单只能上传一份");
                return responseDTO;
            }
            // 如果入住名单已插入附加表，不能重复插入
            OrderAttachDO orderAttachQuery = new OrderAttachDO();
            orderAttachQuery.setOrderId(requestDTO.getOrderId());
            orderAttachQuery.setType(Byte.valueOf(requestDTO.getAttachType() + ""));
            List<OrderAttachDO> orderAttachDOList = orderAttachMapper.select(orderAttachQuery);
            if (!CollectionUtils.isEmpty(orderAttachDOList)) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("入住名单只能上传一份，请先删除旧的入住名单");
                return responseDTO;
            }
        }
        // 2. 上传附件到服务器
        List<String> fileNameList = new ArrayList<>();
        List<OrderAttachDO> orderAttachList = new ArrayList<>();
        for (int i = 0; i < fileNum; i++) {
            MultipartFile file = files[i];
            // 获取文件后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String[] allowTypes = Constant.ORDER_ALLOW_TYPES;
            List<String> typeList = Arrays.asList(allowTypes);
            if (!typeList.contains(suffix)) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("上传文件的格式不正确");
                return responseDTO;
            }
            ResponseDTO uploadResponse = FileUploadUtil.addFile(file, Constant.FILE_UPLOAD_ORDER_DIR, null, uploadFileConfig);
            if (uploadResponse.getResult().equals(ResultCodeEnum.SUCCESS.code)) {
                fileNameList.add(file.getOriginalFilename());
                UploadResponseDTO uploadResponseDTO = (UploadResponseDTO) uploadResponse.getModel();
                OrderAttachDO attachDO = new OrderAttachDO();
                attachDO.setCreator(requestDTO.getCreator());
                attachDO.setCreateTime(requestDTO.getCreateTime());
                attachDO.setName(file.getOriginalFilename());
                attachDO.setOrderId(requestDTO.getOrderId());
                attachDO.setType(Byte.valueOf(requestDTO.getAttachType() + ""));
                attachDO.setUrl(uploadResponseDTO.getFileUrl());
                attachDO.setRealpath(uploadResponseDTO.getRealFileName());
                orderAttachList.add(attachDO);
            }
        }

        // 3. 插入附件表
        if (!CollectionUtils.isEmpty(orderAttachList)) {
            orderAttachMapper.insertList(orderAttachList);
        }

        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        logDO.setContent("上传附件（" + StringUtil.listToSQLString(fileNameList) + "）成功");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getCreateTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO deleteAttach(DeleteAttachRequestDTO requestDTO) {
        log.info("deleteAttach param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        OrderAttachDO orderAttachDO = orderAttachMapper.selectByPrimaryKey(requestDTO.getOrderAttachId());
        if (orderAttachDO == null) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("附件不存在");
            return responseDTO;
        }
        String realpath = orderAttachDO.getRealpath();
        // 1. 删除服务器附件
        FileUploadUtil.deleteFile(Constant.FILE_UPLOAD_ORDER_DIR, realpath, uploadFileConfig);
        // 2. 删除附件表附件
        orderAttachMapper.deleteByPrimaryKey(requestDTO.getOrderAttachId());
        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderAttachDO.getOrderId());
        logDO.setContent("删除附件（" + orderAttachDO.getName() + "）成功");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO addNote(AddNoteRequestDTO requestDTO) {
        log.info("addNote param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 添加备注
        OrderNoteDO orderNoteDO = PropertyCopyUtil.transfer(requestDTO, OrderNoteDO.class);
        orderNoteDO.setCreateTime(DateUtil.stringToDateWithHms(requestDTO.getCreateTime()));
        orderNoteMapper.insert(orderNoteDO);

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderNoteDO.getOrderId());
        logDO.setContent("添加备注");
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(DateUtil.stringToDateWithHms(requestDTO.getCreateTime()));
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO handleOrder(OrderDetailRequestDTO requestDTO) {
        log.info("handleOrder param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 如果订单已经接单过，不能再接单
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        if (StringUtils.isNotEmpty(orderDO.getBelongName()) && StringUtils.isNotEmpty(orderDO.getBelongUser())) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailCode("REPEAT_RECEIVE_ORDER_ERROR");
            responseDTO.setFailReason("订单不能重复接单");
            return responseDTO;
        }
        // 2. 开始处理订单： 修改订单为处理中
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(requestDTO.getOrderId());
        orderUpdate.setOrderStatus(Byte.valueOf(OrderStatusEnum.PROCESSING.key + ""));
        orderUpdate.setBelongUser(requestDTO.getCreator());
        orderUpdate.setBelongName(requestDTO.getModifier());
        orderUpdate.setLockUser(requestDTO.getCreator());
        orderUpdate.setLockName(requestDTO.getModifier());
        orderUpdate.setLockTime(requestDTO.getModifyTime());
        orderUpdate.setModifier(requestDTO.getModifier());
        orderUpdate.setModifyTime(requestDTO.getModifyTime());
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        logDO.setContent("开始接单，订单已锁定");
        logDO.setOperator(requestDTO.getOperator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO lockOrder(LockOrderRequestDTO requestDTO) {
        log.info("lockOrder param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 校验
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        if (StringUtils.isNotEmpty(orderDO.getLockUser()) && requestDTO.getLockType() == 1) {
            if (StringUtils.equals(orderDO.getLockUser(), requestDTO.getCreator())) {
                responseDTO.setResult(Constant.YES);
                return responseDTO;
            } else {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailCode("REPEAT_RECEIVE_ORDER_ERROR");
                responseDTO.setFailReason("订单已被" + orderDO.getLockName() + "锁定");
                return responseDTO;
            }
        }

        // 2. 加解锁
        if (requestDTO.getLockType() == 1) {
            OrderDO orderUpdate = new OrderDO();
            orderUpdate.setId(requestDTO.getOrderId());
            orderUpdate.setLockUser(requestDTO.getCreator());
            orderUpdate.setLockName(requestDTO.getModifier());
            orderUpdate.setLockTime(requestDTO.getCreateTime());
            orderUpdate.setModifier(requestDTO.getModifier());
            orderUpdate.setModifyTime(requestDTO.getModifyTime());
            orderMapper.updateByPrimaryKeySelective(orderUpdate);
        } else {
            orderDO.setLockTime(null);
            orderDO.setLockUser(null);
            orderDO.setLockName(null);
            orderDO.setModifier(requestDTO.getModifier());
            orderDO.setModifyTime(requestDTO.getModifyTime());
            orderMapper.updateByPrimaryKey(orderDO);
        }

        // 3. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        logDO.setContent(requestDTO.getLockType() == 1 ? "订单已锁定" : "订单已解锁");
        logDO.setOperator(requestDTO.getOperator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO addOrderRequest(AddOrderReqRequestDTO requestDTO) {
        log.info("addOrderRequest param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 校验
        if (requestDTO.getRequestType() == 1) {
            // 1.1 如果有未处理的取消申请，不重复添加取消申请
            OrderRequestDO orderRequestQuery = new OrderRequestDO();
            orderRequestQuery.setOrderId(requestDTO.getOrderId());
            orderRequestQuery.setRequestType(requestDTO.getRequestType());
            orderRequestQuery.setHandleResult(Byte.valueOf("0"));
            List<OrderRequestDO> orderRequestDOS = orderRequestMapper.select(orderRequestQuery);
            if (!CollectionUtils.isEmpty(orderRequestDOS)) {
                responseDTO.setResult(Constant.YES);
                return responseDTO;
            }
        }
        // 2. 添加订单申请
        OrderRequestDO orderRequestDO = PropertyCopyUtil.transfer(requestDTO, OrderRequestDO.class);
        orderRequestDO.setHandleResult(Byte.valueOf("0"));
        orderRequestMapper.insert(orderRequestDO);
        Integer orderRequestId = orderRequestDO.getId();
        // 3. 添加订单申请价格明细
        if (!CollectionUtils.isEmpty(requestDTO.getOrderRequestPriceDTOS())) {
            List<OrderRequestPriceDO> requestPriceDOS = new ArrayList<>();
            for (OrderRequestPriceDTO orderRequestPriceDTO : requestDTO.getOrderRequestPriceDTOS()) {
                OrderRequestPriceDO requestPriceDO = PropertyCopyUtil.transfer(orderRequestPriceDTO, OrderRequestPriceDO.class);
                requestPriceDO.setOrderRequestId(orderRequestId);
                requestPriceDOS.add(requestPriceDO);
            }
            orderRequestPriceMapper.insertList(requestPriceDOS);
        }
        // 4. 缓存到Redis
        OrderDO orderNew = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        OrderMessageRedisCacheDTO cacheRequestDTO = PropertyCopyUtil.transfer(orderNew, OrderMessageRedisCacheDTO.class);
        cacheRequestDTO.setOrderType(requestDTO.getRequestType().intValue() == 1 ? 2 : 3);
        cacheRequestDTO.setCheckinDate(DateUtil.dateToString(orderNew.getCheckinDate()));
        cacheRequestDTO.setCheckoutDate(DateUtil.dateToString(orderNew.getCheckoutDate()));
        cacheRequestDTO.setOrderId(requestDTO.getOrderId());
        orderCommonService.saveOrderMessageToRedis(cacheRequestDTO);
        // 5. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(orderRequestDO.getOrderId());
        logDO.setContent("添加" + (requestDTO.getRequestType().intValue() == 1 ? "取消申请" : "修改申请"));
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(DateUtil.stringToDateWithHms(requestDTO.getCreateTime()));
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<OrderMessageRedisCacheDTO>> queryOrderMessageFromRedis(String merchantCode) {
        return orderCommonService.queryOrderMessageFromRedis(merchantCode);
    }

    @Override
    public ResponseDTO deleteOrderMessageFromRedis(DeleteOrderMessageRequestDTO requestDTO) {
        log.info("deleteOrderMessageFromRedis param: {}", requestDTO);
        return orderCommonService.deleteOrderMessageFromRedis(requestDTO);
    }

    @Override
    public void saveSupplyOrderCofirmMsgToRedis(SupplyOrderCofirmMsgDTO supplyOrderCofirmMsgDTO) {
        orderCommonService.saveSupplyOrderCofirmMsgToRedis(supplyOrderCofirmMsgDTO);
    }

    @Override
    public ResponseDTO<List<SupplyOrderCofirmMsgDTO>> querySupplyOrderCofirmMsgFromRedis(String merchantCode) {
        return orderCommonService.querySupplyOrderCofirmMsgFromRedis(merchantCode);
    }

    @Override
    public ResponseDTO deleteSupplyOrderCofirmMsgFromRedis(DeleteSupplyOrderCofirmMsgRequestDTO deleteSupplyOrderCofirmMsgRequestDTO) {
        return orderCommonService.deleteSupplyOrderCofirmMsgFromRedis(deleteSupplyOrderCofirmMsgRequestDTO);
    }

}
