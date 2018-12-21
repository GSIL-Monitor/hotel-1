package com.fangcang.b2b.service.impl;

import com.fangcang.agent.dto.AgentUserDTO;
import com.fangcang.b2b.dto.AdditionChargeDTO;
import com.fangcang.b2b.dto.DailyDTO;
import com.fangcang.b2b.dto.HotelDeratePolicyDailyDTO;
import com.fangcang.b2b.dto.HotelDeratePolicyRequestDTO;
import com.fangcang.b2b.dto.HotelOrderDerateDTO;
import com.fangcang.b2b.dto.HotelOrderRequestDTO;
import com.fangcang.b2b.dto.PreBookPricePlanDTO;
import com.fangcang.b2b.dto.RatePricePlanDTO;
import com.fangcang.b2b.request.ApplyCancelOrderRequestDTO;
import com.fangcang.b2b.request.BookOrderRequestDTO;
import com.fangcang.b2b.request.HotelChangeGuideRequestDTO;
import com.fangcang.b2b.request.HotelOrderDetailRequestDTO;
import com.fangcang.b2b.request.HotelOrderListRequestDTO;
import com.fangcang.b2b.request.PreBookRequestDTO;
import com.fangcang.b2b.response.BookOrderResponseDTO;
import com.fangcang.b2b.response.GetOfflinePayResponseDTO;
import com.fangcang.b2b.response.HotelOrderDetailResponseDTO;
import com.fangcang.b2b.response.OrderListResponseDTO;
import com.fangcang.b2b.response.PreBookResponseDTO;
import com.fangcang.b2b.service.HotelOrderService;
import com.fangcang.b2b.thread.HotelOrderDetailThread;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.enums.order.PayStatusEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import com.fangcang.hotelinfo.mapper.HotelMapper;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;
import com.fangcang.merchant.domain.UserBankCardDO;
import com.fangcang.merchant.dto.BankCardDTO;
import com.fangcang.merchant.mapper.UserBankCardMapper;
import com.fangcang.order.dto.OrderDTO;
import com.fangcang.order.dto.OrderRequestDTO;
import com.fangcang.order.request.AddOrderReqRequestDTO;
import com.fangcang.order.request.ChangeGuideRequestDTO;
import com.fangcang.order.request.CreateOrderRequestDTO;
import com.fangcang.order.request.OrderListRequestDTO;
import com.fangcang.order.service.OrderDetailService;
import com.fangcang.order.service.OrderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Created by ASUS on 2018/7/4.
 */
@Slf4j
@Service
public class HotelOrderServiceImpl implements HotelOrderService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserBankCardMapper userBankCardMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public ResponseDTO<PreBookResponseDTO> prebook(PreBookRequestDTO preBookRequestDTO, AgentUserDTO agentUserDTO) {
        ResponseDTO preResult = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            CreateOrderRequestDTO createOrderRequestDTO = PropertyCopyUtil.transfer(preBookRequestDTO, CreateOrderRequestDTO.class);
            createOrderRequestDTO.setChannelCode(ChannelTypeEnum.B2B.key);
            createOrderRequestDTO.setAgentCode(agentUserDTO.getAgentCode());
            createOrderRequestDTO.setIsManualOrder(0);//是否手工单，1：是，0：否
            preResult = orderService.preBooking(createOrderRequestDTO);
            if (null != preResult && null != preResult.getResult() && ResultCodeEnum.SUCCESS.code == preResult.getResult()) {
                //试预订成功
                PreBookResponseDTO preBookResponseDTO = PropertyCopyUtil.transfer(preBookRequestDTO, PreBookResponseDTO.class);
                preBookResponseDTO.setContractName(agentUserDTO.getRealName());
                preBookResponseDTO.setContractPhone(agentUserDTO.getUserName());
                if(!CollectionUtils.isEmpty(preBookResponseDTO.getRatePlanList())){
                    for(PreBookPricePlanDTO preBookPricePlanDTO : preBookResponseDTO.getRatePlanList()){
                        Integer minQuotaNum = null;
                        Integer overDraft = null;//可超
                        preBookPricePlanDTO.setNight(DateUtil.getDay(
                                DateUtil.stringToDate(preBookPricePlanDTO.getCheckinDate()),DateUtil.stringToDate(preBookPricePlanDTO.getCheckoutDate())
                        ));
                        List<DailyDTO> dayList = preBookPricePlanDTO.getDayList();
                        for (DailyDTO dailyDTO : dayList) {
                            if(null == minQuotaNum && null != dailyDTO.getQuotaNum()){
                                minQuotaNum = dailyDTO.getQuotaNum();
                            }else if(null != minQuotaNum && null != dailyDTO.getQuotaNum() && minQuotaNum.compareTo(dailyDTO.getQuotaNum()) > 0){
                                minQuotaNum = dailyDTO.getQuotaNum();
                            }
                            if(null == overDraft && null != dailyDTO.getOverDraft()){
                                overDraft = dailyDTO.getOverDraft();
                            }else if(null != dailyDTO.getOverDraft() && Integer.valueOf(0).compareTo(dailyDTO.getOverDraft()) == 0){
                                overDraft = dailyDTO.getOverDraft();
                            }
                        }
                        preBookPricePlanDTO.setMinQuotaNum(minQuotaNum);
                        preBookPricePlanDTO.setOverDraft(overDraft);
                    }
                }
                //附加项
                if (!CollectionUtils.isEmpty(preBookRequestDTO.getAdditionalList())) {
                    List<AdditionChargeDTO> additionChargeList = new ArrayList<>();
                    for (HotelAdditionalDTO hotelAdditionalDTO : preBookRequestDTO.getAdditionalList()) {
                        AdditionChargeDTO additionChargeDTO = new AdditionChargeDTO();
                        additionChargeDTO.setName(hotelAdditionalDTO.getAdditionalName());
                        additionChargeDTO.setType(hotelAdditionalDTO.getAdditionalType());
                        additionChargeDTO.setSalePrice(hotelAdditionalDTO.getAdditionalPrice());
                        additionChargeList.add(additionChargeDTO);
                    }
                    preBookResponseDTO.setAdditionChargeList(additionChargeList);
                }
                //减免政策
                if(!CollectionUtils.isEmpty(preBookRequestDTO.getDeratePolicyList())){
                    List<HotelOrderDerateDTO> hotelOrderDerateDTOS = new ArrayList<>();
                    Map<String,BigDecimal> derateMap = new HashMap<>();
                    for (HotelDeratePolicyRequestDTO hotelDeratePolicy : preBookRequestDTO.getDeratePolicyList()){
                        for(HotelDeratePolicyDailyDTO hotelDeratePolicyDailyDTO : hotelDeratePolicy.getDayList()){
                            if(null != hotelDeratePolicyDailyDTO.getRoomNum() && null != hotelDeratePolicyDailyDTO.getSalePrice()){
                                BigDecimal deratePrice = hotelDeratePolicyDailyDTO.getRoomNum().multiply(hotelDeratePolicyDailyDTO.getSalePrice());
                                if(derateMap.containsKey(hotelDeratePolicy.getName())){
                                    derateMap.get(hotelDeratePolicy.getName()).add(deratePrice);
                                }else{
                                    derateMap.put(hotelDeratePolicy.getName(),deratePrice);
                                }
                            }

                        }
                    }
                    if(derateMap.size() > 0){
                        for(Map.Entry<String,BigDecimal> m : derateMap.entrySet()){
                            HotelOrderDerateDTO hotelOrderDerateDTO = new HotelOrderDerateDTO();
                            hotelOrderDerateDTO.setName(m.getKey());
                            hotelOrderDerateDTO.setSalePriceSum(m.getValue());
                            hotelOrderDerateDTOS.add(hotelOrderDerateDTO);
                        }
                        preBookResponseDTO.setDerateList(hotelOrderDerateDTOS);
                    }
                }
                preResult.setModel(preBookResponseDTO);
            }
        } catch (Exception e) {
            log.error("prebook has error", e);
            preResult.setResult(ResultCodeEnum.FAILURE.code);
            preResult.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            preResult.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return preResult;
    }

    public ResponseDTO<BookOrderResponseDTO> book(BookOrderRequestDTO bookOrderRequestDTO, AgentUserDTO agentUserDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            CreateOrderRequestDTO createOrderRequestDTO = PropertyCopyUtil.transfer(bookOrderRequestDTO,CreateOrderRequestDTO.class);
            createOrderRequestDTO.setChannelCode(ChannelTypeEnum.B2B.key);
            createOrderRequestDTO.setAgentCode(agentUserDTO.getAgentCode());
            createOrderRequestDTO.setIsManualOrder(0);//是否手工单，1：是，0：否
            createOrderRequestDTO.setMerchantCode(agentUserDTO.getMerchantCode());
            createOrderRequestDTO.setMerchantName(agentUserDTO.getMerchantName());
            createOrderRequestDTO.setCreateTime(new Date());
            responseDTO = orderService.create(createOrderRequestDTO);
        } catch (Exception e) {
            log.error("book has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<GetOfflinePayResponseDTO> getOfflinePay(Long merchantId) {
        ResponseDTO responseDTO = new ResponseDTO();
        GetOfflinePayResponseDTO getOfflinePayResponseDTO = new GetOfflinePayResponseDTO();
        List<UserBankCardDO> bankCardList = null;
        try {
            bankCardList = userBankCardMapper.queryUserBankCardList(merchantId);
            if(!CollectionUtils.isEmpty(bankCardList)){
                List<BankCardDTO> result = new ArrayList<>();
                BankCardDTO bankCardDTO = null;
                String alipayUrl = null;//支付宝收款码
                String wechatPayUrl = null;//微信收款码
                for (UserBankCardDO userBankCardDO : bankCardList) {
                    if(null != userBankCardDO.getType() && 1 == userBankCardDO.getType()){
                        bankCardDTO = PropertyCopyUtil.transfer(userBankCardDO, BankCardDTO.class);
                        result.add(bankCardDTO);
                    }else if(null != userBankCardDO.getType() && 2 == userBankCardDO.getType()){
                        alipayUrl = userBankCardDO.getAccountNumber();
                    }else if(null != userBankCardDO.getType() && 3 == userBankCardDO.getType()){
                        wechatPayUrl = userBankCardDO.getAccountNumber();
                    }
                }
                getOfflinePayResponseDTO.setBankCardList(result);
                getOfflinePayResponseDTO.setAlipayUrl(alipayUrl);
                getOfflinePayResponseDTO.setWechatPayUrl(wechatPayUrl);
            }
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(getOfflinePayResponseDTO);
        } catch (Exception e) {
            log.error("getOfflinePay has error", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    public ResponseDTO applyCancelOrder(ApplyCancelOrderRequestDTO applyCancelOrderRequestDTO){
        AddOrderReqRequestDTO orderRequestDTO = new AddOrderReqRequestDTO();
        orderRequestDTO.setOrderId(applyCancelOrderRequestDTO.getOrderId().intValue());
        orderRequestDTO.setCreator(applyCancelOrderRequestDTO.getCreator());
        orderRequestDTO.setCreateTime(DateUtil.dateToStringWithHms(new Date()));
        byte requestType = 1;
        orderRequestDTO.setRequestType(requestType);//订单申请类型，1取消单申请，2修改单申请
        return orderService.addOrderRequest(orderRequestDTO);
    }

    public ResponseDTO<PaginationSupportDTO<OrderListResponseDTO>> queryOrderList(HotelOrderListRequestDTO hotelOrderListRequestDTO,AgentUserDTO agentUserDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == hotelOrderListRequestDTO){
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            return responseDTO;
        }
        try {
            OrderListRequestDTO orderListRequestDTO = new OrderListRequestDTO();
            orderListRequestDTO.setPageSize(hotelOrderListRequestDTO.getPageSize());
            orderListRequestDTO.setCurrentPage(hotelOrderListRequestDTO.getCurrentPage());
            orderListRequestDTO.setAgentCode(agentUserDTO.getAgentCode());
            orderListRequestDTO.setMerchantCode(agentUserDTO.getMerchantCode());
            orderListRequestDTO.setChannelCode(ChannelTypeEnum.B2B.key);
            //orderStatus 1 待付款  2 待确认 3 已确认 4 已支付  不传则查全部
            //支付状态，1：待支付，2：已支付，3：已挂账，4：未挂账 5:已退款
            //订单状态，1：新建，2：处理中，3：已确认，4：已取消
            if(null != hotelOrderListRequestDTO.getOrderStatus() && 1 == hotelOrderListRequestDTO.getOrderStatus()){
                orderListRequestDTO.setPayStatus(PayStatusEnum.UN_PAID.key);
            }else if(null != hotelOrderListRequestDTO.getOrderStatus() && 2 == hotelOrderListRequestDTO.getOrderStatus()){
                List<Integer> orderStatusList = new ArrayList<>();
                orderStatusList.add(OrderStatusEnum.NEWORDER.key);
                orderStatusList.add(OrderStatusEnum.PROCESSING.key);
                orderListRequestDTO.setOrderStatusList(orderStatusList);
            }else if(null != hotelOrderListRequestDTO.getOrderStatus() && 3 == hotelOrderListRequestDTO.getOrderStatus()){
                //orderStatus
                orderListRequestDTO.setOrderStatus(OrderStatusEnum.TRADED.key);
            }else if(null != hotelOrderListRequestDTO.getOrderStatus() && 4 == hotelOrderListRequestDTO.getOrderStatus()){
                List<Integer> payStatusList = new ArrayList<>();
                payStatusList.add(PayStatusEnum.PAID.key);
                payStatusList.add(PayStatusEnum.CREDIT.key);
                orderListRequestDTO.setPayStatusList(payStatusList);
            }
            PaginationSupportDTO<OrderListResponseDTO> returnPs = new PaginationSupportDTO<>();
            PaginationSupportDTO<OrderDTO> paginationSupportDTO = orderService.orderList(orderListRequestDTO);
            returnPs.setPageSize(paginationSupportDTO.getPageSize());
            returnPs.setCurrentPage(paginationSupportDTO.getCurrentPage());
            returnPs.setTotalPage(paginationSupportDTO.getTotalPage());
            returnPs.setTotalCount(paginationSupportDTO.getTotalCount());
            if(null != paginationSupportDTO && !CollectionUtils.isEmpty(paginationSupportDTO.getItemList())){
                List<OrderListResponseDTO> orderListResponseDTOList = null;
                orderListResponseDTOList = PropertyCopyUtil.transferArray(paginationSupportDTO.getItemList(),OrderListResponseDTO.class);
                if(!CollectionUtils.isEmpty(orderListResponseDTOList)){
                    for (OrderListResponseDTO orderListResponseDTO : orderListResponseDTOList) {
                        if(StringUtil.isValidString(orderListResponseDTO.getCheckinDate()) && StringUtil.isValidString(orderListResponseDTO.getCheckoutDate())){
                            orderListResponseDTO.setNight(DateUtil.getDay(
                                    DateUtil.stringToDate(orderListResponseDTO.getCheckinDate()),DateUtil.stringToDate(orderListResponseDTO.getCheckoutDate())
                            ));
                        }
                    }
                }
                returnPs.setItemList(orderListResponseDTOList);
            }
            responseDTO.setModel(returnPs);
        } catch (Exception e) {
            log.error("queryOrderList has error", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }


    public ResponseDTO<HotelOrderDetailResponseDTO> queryOrderDetail(HotelOrderDetailRequestDTO hotelOrderDetailRequestDTO, AgentUserDTO agentUserDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            CountDownLatch countDownLatch = new CountDownLatch(4);
            //订单信息
            HotelOrderDetailThread hotelOrderDetailThread = new HotelOrderDetailThread(countDownLatch,1,orderDetailService,agentUserDTO,hotelOrderDetailRequestDTO,orderService);
            //产品信息
            HotelOrderDetailThread productInfoThread = new HotelOrderDetailThread(countDownLatch,2,orderDetailService,agentUserDTO,hotelOrderDetailRequestDTO,orderService);
            //取消修改记录
            HotelOrderDetailThread orderRequestThread = new HotelOrderDetailThread(countDownLatch,3,orderDetailService,agentUserDTO,hotelOrderDetailRequestDTO,orderService);
            //备注信息
            HotelOrderDetailThread noteThread = new HotelOrderDetailThread(countDownLatch,4,orderDetailService,agentUserDTO,hotelOrderDetailRequestDTO,orderService);


            Future<HotelOrderDetailResponseDTO> hotelOrderDetailFuture = threadPoolTaskExecutor.submit(hotelOrderDetailThread);
            Future<HotelOrderDetailResponseDTO> productInfoFuture = threadPoolTaskExecutor.submit(productInfoThread);
            Future<HotelOrderDetailResponseDTO> orderRequestFuture = threadPoolTaskExecutor.submit(orderRequestThread);
            Future<HotelOrderDetailResponseDTO> noteFuture = threadPoolTaskExecutor.submit(noteThread);

            countDownLatch.await();
            HotelOrderDetailResponseDTO hotelOrderDetailResponseDTO = hotelOrderDetailFuture.get();
            if(null == hotelOrderDetailResponseDTO || !StringUtil.isValidString(hotelOrderDetailResponseDTO.getOrderCode())){
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
                responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
                return responseDTO;
            }
            HotelOrderDetailResponseDTO productDetail = productInfoFuture.get();
            if(null != productDetail){
                hotelOrderDetailResponseDTO.setRatePlanList(productDetail.getRatePlanList());
                hotelOrderDetailResponseDTO.setOrderDerateList(productDetail.getOrderDerateList());
                hotelOrderDetailResponseDTO.setAdditionChargeList(productDetail.getAdditionChargeList());
            }
            HotelListQueryDTO hotelListQueryDTO = new HotelListQueryDTO();
            hotelListQueryDTO.setHotelId(Long.valueOf(hotelOrderDetailResponseDTO.getHotelId()));
            List<HotelDO> hotelList = hotelMapper.getHotelList(hotelListQueryDTO);
            if(!CollectionUtils.isEmpty(hotelList)){
                HotelDO hotelDO = hotelList.get(0);
                hotelOrderDetailResponseDTO.setHotelAddress(hotelDO.getHotelAddress());
                if(null != hotelDO.getImagedo()){
                    hotelOrderDetailResponseDTO.setImageUrl(hotelDO.getImagedo().getImageUrl());
                }
            }
            //是否展示取消
            HotelOrderDetailResponseDTO orderRequest = orderRequestFuture.get();
            List<HotelOrderRequestDTO> hotelOrderRequestList = null;
            if(null != orderRequest){
                hotelOrderRequestList = orderRequest.getHotelOrderRequestList();
                hotelOrderDetailResponseDTO.setHotelOrderRequestList(hotelOrderRequestList);
            }
            judgeCancelOrder(hotelOrderDetailResponseDTO,hotelOrderRequestList);
            BigDecimal beforeOrderSum = hotelOrderDetailResponseDTO.getOrderSum();
            if(!CollectionUtils.isEmpty(hotelOrderDetailResponseDTO.getOrderDerateList())){
                for (HotelOrderDerateDTO hotelOrderDerateDTO : hotelOrderDetailResponseDTO.getOrderDerateList()) {
                    if(null != beforeOrderSum && null != hotelOrderDerateDTO.getSalePriceSum()){
                        beforeOrderSum = beforeOrderSum.subtract(hotelOrderDerateDTO.getSalePriceSum()); //100 - (-30)
                    }
                }
            }
            hotelOrderDetailResponseDTO.setBeforeOrderSum(beforeOrderSum);

            //备注信息
            HotelOrderDetailResponseDTO noteResponse = noteFuture.get();
            if(null != noteResponse){
                hotelOrderDetailResponseDTO.setOrderNoteList(noteResponse.getOrderNoteList());
            }
            responseDTO.setModel(hotelOrderDetailResponseDTO);
        } catch (Exception e) {
            log.error("queryOrderDetail has error.",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    private void judgeCancelOrder(HotelOrderDetailResponseDTO hotelOrderDetailResponseDTO,List<HotelOrderRequestDTO> hotelOrderRequestList) {
        try {
            hotelOrderDetailResponseDTO.setShowCancel(1);
            //1.订单状态是否取消
            if(String.valueOf(OrderStatusEnum.CANCELED.key).equals(String.valueOf(hotelOrderDetailResponseDTO.getOrderStatus()))){
                hotelOrderDetailResponseDTO.setShowCancel(0);
                hotelOrderDetailResponseDTO.setCanNotCancelReason("订单已取消!请联系商家处理!");
                return;
            }
            //2.已过退房日期
            List<RatePricePlanDTO> ratePlanList = hotelOrderDetailResponseDTO.getRatePlanList();
            for (RatePricePlanDTO ratePricePlanDTO : ratePlanList) {
                if(DateUtil.dateFormat(new Date(),"yyyy-MM-dd").compareTo(DateUtil.stringToDate(ratePricePlanDTO.getCheckoutDate())) > 0){
                    hotelOrderDetailResponseDTO.setShowCancel(0);
                    hotelOrderDetailResponseDTO.setCanNotCancelReason("当前已过离店日期!请联系商家处理!");
                    return;
                }
            }
            //3.已出账或结算金额不等于0
            if(String.valueOf(BalanceMethodEnum.SINGLE.key).equals(String.valueOf(hotelOrderDetailResponseDTO.getBalanceMethod()))){
                //单结
                if(null == hotelOrderDetailResponseDTO.getPaidInAmount() || BigDecimal.ZERO.compareTo(hotelOrderDetailResponseDTO.getPaidInAmount()) == 0){
                    hotelOrderDetailResponseDTO.setShowCancel(1);
                }else{
                    hotelOrderDetailResponseDTO.setShowCancel(0);
                    hotelOrderDetailResponseDTO.setCanNotCancelReason("该订单已结算，不能修改或取消!请联系商家处理!");
                    return;
                }
            }else{
                //非单结
                if(String.valueOf(OrderStatusEnum.NEWORDER.key).equals(String.valueOf(hotelOrderDetailResponseDTO.getOrderStatus()))
                        && (null == hotelOrderDetailResponseDTO.getPaidInAmount() || BigDecimal.ZERO.compareTo(hotelOrderDetailResponseDTO.getPaidInAmount()) == 0)){
                    hotelOrderDetailResponseDTO.setShowCancel(1);
                }else{
                    hotelOrderDetailResponseDTO.setShowCancel(0);
                    hotelOrderDetailResponseDTO.setCanNotCancelReason("该订单已结算，不能修改或取消!请联系商家处理!");
                    return;
                }
            }
            //是否有处理中的申请
            if(!CollectionUtils.isEmpty(hotelOrderRequestList)){
                for (HotelOrderRequestDTO hotelOrderRequestDTO : hotelOrderRequestList) {
                    if(null != hotelOrderRequestDTO.getHandleResult() && 0 == hotelOrderRequestDTO.getHandleResult()){
                        hotelOrderDetailResponseDTO.setShowCancel(0);
                        hotelOrderDetailResponseDTO.setCanNotCancelReason("该订单已有正在处理的取消或修改申请!请联系商家处理!");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            log.error("judgeCancelOrder", e);
        }
    }

    public ResponseDTO changeGuide(HotelChangeGuideRequestDTO hotelChangeGuideRequestDTO){
        ChangeGuideRequestDTO changeGuideRequestDTO = PropertyCopyUtil.transfer(hotelChangeGuideRequestDTO,ChangeGuideRequestDTO.class);
        return orderService.changeGuide(changeGuideRequestDTO);
    }
}
