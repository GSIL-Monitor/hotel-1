package com.fangcang.b2b.thread;

import com.fangcang.agent.dto.AgentUserDTO;
import com.fangcang.b2b.dto.AdditionChargeDTO;
import com.fangcang.b2b.dto.DailyDTO;
import com.fangcang.b2b.dto.HotelOrderDerateDTO;
import com.fangcang.b2b.dto.HotelOrderNoteDTO;
import com.fangcang.b2b.dto.HotelOrderRequestDTO;
import com.fangcang.b2b.dto.RatePricePlanDTO;
import com.fangcang.b2b.request.HotelOrderDetailRequestDTO;
import com.fangcang.b2b.response.HotelOrderDetailResponseDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.order.NoteTypeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.order.dto.OrderRequestDTO;
import com.fangcang.order.dto.SupplyAdditionChargeDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.response.DeratePolicyResponseDTO;
import com.fangcang.order.response.OrderDetailResponseDTO;
import com.fangcang.order.response.OrderNoteGroupResponseDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;
import com.fangcang.order.response.SupplyProductResponseDTO;
import com.fangcang.order.service.OrderDetailService;
import com.fangcang.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ASUS on 2018/7/5.
 */
@Slf4j
public class HotelOrderDetailThread implements Callable<HotelOrderDetailResponseDTO> {

    private CountDownLatch countDownLatch;

    /**
     * 操作类型 1查询订单信息  2 查询产品信息 3 查询订单申请通知
     */
    private Integer operateType;

    private OrderDetailService orderDetailService;

    private AgentUserDTO agentUserDTO;

    private HotelOrderDetailRequestDTO hotelOrderDetailRequestDTO;

    private OrderService orderService;

    public HotelOrderDetailThread(CountDownLatch countDownLatch, Integer operateType,
                                  OrderDetailService orderDetailService, AgentUserDTO agentUserDTO,
                                  HotelOrderDetailRequestDTO hotelOrderDetailRequestDTO, OrderService orderService) {
        this.countDownLatch = countDownLatch;
        this.operateType = operateType;
        this.orderDetailService = orderDetailService;
        this.agentUserDTO = agentUserDTO;
        this.hotelOrderDetailRequestDTO = hotelOrderDetailRequestDTO;
        this.orderService = orderService;
    }

    @Override
    public HotelOrderDetailResponseDTO call() throws Exception {
        HotelOrderDetailResponseDTO hotelOrderDetailResponseDTO = null;
        try {
            OrderDetailRequestDTO orderDetailRequestDTO = new OrderDetailRequestDTO();
            orderDetailRequestDTO.setMerchantCode(agentUserDTO.getMerchantCode());
            orderDetailRequestDTO.setOrderId(hotelOrderDetailRequestDTO.getOrderId().intValue());
            if(1 == operateType){
                ResponseDTO<OrderDetailResponseDTO> responseDTO = orderDetailService.orderInfo(orderDetailRequestDTO);
                if(null != responseDTO && ResultCodeEnum.SUCCESS.code == responseDTO.getResult()){
                    log.info("Query order detail info success,orderId:" + hotelOrderDetailRequestDTO.getOrderId());
                    hotelOrderDetailResponseDTO = PropertyCopyUtil.transfer(responseDTO.getModel(),HotelOrderDetailResponseDTO.class);
                }
            }else if(2 == operateType){
                ResponseDTO<List<SupplyOrderResponseDTO>> productResponse = orderDetailService.productInfo(orderDetailRequestDTO);
                if(null != productResponse && ResultCodeEnum.SUCCESS.code == productResponse.getResult()){
                    log.info("Query order product info success,orderId:" + hotelOrderDetailRequestDTO.getOrderId());
                    List<SupplyOrderResponseDTO> supplyOrderList = productResponse.getModel();
                    if(null == hotelOrderDetailResponseDTO){
                        hotelOrderDetailResponseDTO = new HotelOrderDetailResponseDTO();
                    }
                    if(!CollectionUtils.isEmpty(supplyOrderList)){
                        List<RatePricePlanDTO> ratePlanList = new ArrayList<>();
                        List<AdditionChargeDTO> additionChargeList = new ArrayList<>();
                        List<HotelOrderDerateDTO> orderDerateDTOList = new ArrayList<>();
                        Map<String,BigDecimal> orderDerateMap = new HashMap<>();
                        RatePricePlanDTO ratePricePlanDTO = null;
                        AdditionChargeDTO additionChargeDTO = null;
                        for (SupplyOrderResponseDTO supplyOrderResponseDTO : supplyOrderList) {
                            List<SupplyProductResponseDTO> supplyProductList = supplyOrderResponseDTO.getSupplyProductList();
                            for (SupplyProductResponseDTO supplyProductResponseDTO : supplyProductList) {
                                ratePricePlanDTO = PropertyCopyUtil.transfer(supplyProductResponseDTO,RatePricePlanDTO.class);
                                if(null != ratePricePlanDTO){
                                    ratePricePlanDTO.setSupplyCode(supplyOrderResponseDTO.getSupplyCode());
                                    ratePricePlanDTO.setSupplyName(supplyOrderResponseDTO.getSupplyName());
                                    if(StringUtil.isValidString(ratePricePlanDTO.getCheckinDate()) && StringUtil.isValidString(ratePricePlanDTO.getCheckoutDate())){
                                        ratePricePlanDTO.setNight(DateUtil.getDay(
                                                DateUtil.stringToDate(ratePricePlanDTO.getCheckinDate()),DateUtil.stringToDate(ratePricePlanDTO.getCheckoutDate())
                                        ));
                                    }
                                    List<DailyDTO> productPriceDTOList = ratePricePlanDTO.getProductPriceDTOList();
                                    for (DailyDTO dailyDTO : productPriceDTOList) {
                                        dailyDTO.setBasePrice(null);
                                    }
                                    ratePlanList.add(ratePricePlanDTO);
                                }
                            }
                            //附加项
                            if(!CollectionUtils.isEmpty(supplyOrderResponseDTO.getSupplyAdditionChargeList())){
                                for (SupplyAdditionChargeDTO supplyAdditionChargeDTO : supplyOrderResponseDTO.getSupplyAdditionChargeList()) {
                                    additionChargeDTO = new AdditionChargeDTO();
                                    additionChargeDTO.setName(supplyAdditionChargeDTO.getName());
                                    additionChargeDTO.setNum(supplyAdditionChargeDTO.getNum());
                                    additionChargeDTO.setDays(supplyAdditionChargeDTO.getDays());
                                    additionChargeDTO.setSalePrice(supplyAdditionChargeDTO.getSalePrice());
                                    additionChargeList.add(additionChargeDTO);
                                }
                            }
                            //减免政策
                            if(!CollectionUtils.isEmpty(supplyOrderResponseDTO.getDerateList())){
                                for (DeratePolicyResponseDTO deratePolicyResponseDTO : supplyOrderResponseDTO.getDerateList()) {
                                    if(orderDerateMap.containsKey(deratePolicyResponseDTO.getName())){
                                        orderDerateMap.get(deratePolicyResponseDTO.getName()).add(deratePolicyResponseDTO.getSalePriceSum());
                                    }else{
                                        orderDerateMap.put(deratePolicyResponseDTO.getName(),deratePolicyResponseDTO.getSalePriceSum());
                                    }
                                }
                            }
                        }
                        hotelOrderDetailResponseDTO.setRatePlanList(ratePlanList);
                        hotelOrderDetailResponseDTO.setAdditionChargeList(additionChargeList);
                        if(orderDerateMap.size() > 0){
                            for(Map.Entry<String,BigDecimal> m : orderDerateMap.entrySet()){
                                HotelOrderDerateDTO hotelOrderDerateDTO = new HotelOrderDerateDTO();
                                hotelOrderDerateDTO.setName(m.getKey());
                                hotelOrderDerateDTO.setSalePriceSum(m.getValue());
                                orderDerateDTOList.add(hotelOrderDerateDTO);
                            }
                        }
                        hotelOrderDetailResponseDTO.setOrderDerateList(orderDerateDTOList);
                    }
                }
            }else if(3 == operateType){
                ResponseDTO<List<OrderRequestDTO>> responseDTO = orderService.queryOrderRequestList(orderDetailRequestDTO);
                if(null != responseDTO && ResultCodeEnum.SUCCESS.code == responseDTO.getResult()){
                    log.info("Query order request info success,orderId:" + hotelOrderDetailRequestDTO.getOrderId());
                    List<OrderRequestDTO> orderRequestDTOList = responseDTO.getModel();
                    if(!CollectionUtils.isEmpty(orderRequestDTOList)){
                        if(null == hotelOrderDetailResponseDTO){
                            hotelOrderDetailResponseDTO = new HotelOrderDetailResponseDTO();
                        }

                        List<HotelOrderRequestDTO> hotelOrderRequestDTOS = PropertyCopyUtil.transferArray(orderRequestDTOList,HotelOrderRequestDTO.class);
                        hotelOrderDetailResponseDTO.setHotelOrderRequestList(hotelOrderRequestDTOS);
                    }
                }
            }else if(4 == operateType){
                ResponseDTO<List<OrderNoteGroupResponseDTO>> responseDTO = orderDetailService.noteInfo(orderDetailRequestDTO);
                if(null != responseDTO && ResultCodeEnum.SUCCESS.code == responseDTO.getResult()){
                    if(null == hotelOrderDetailResponseDTO){
                        hotelOrderDetailResponseDTO = new HotelOrderDetailResponseDTO();
                    }
                    List<HotelOrderNoteDTO> hotelOrderNoteDTOS = null;
                    List<OrderNoteGroupResponseDTO> orderNoteGroupResponseDTOS = responseDTO.getModel();
                    for (OrderNoteGroupResponseDTO orderNoteGroupResponseDTO : orderNoteGroupResponseDTOS) {
                        if(NoteTypeEnum.AGENT_NOTE.key == orderNoteGroupResponseDTO.getNoteType()){
                            hotelOrderNoteDTOS  = PropertyCopyUtil.transferArray(orderNoteGroupResponseDTO.getOrderNoteList(),HotelOrderNoteDTO.class);
                        }
                    }
                    if(!CollectionUtils.isEmpty(hotelOrderNoteDTOS)){
                        hotelOrderDetailResponseDTO.setOrderNoteList(hotelOrderNoteDTOS);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Query order detail has error.", e);
        } finally {
            countDownLatch.countDown();
        }
        return hotelOrderDetailResponseDTO;
    }

}
