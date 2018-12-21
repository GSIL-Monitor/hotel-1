package com.travel.hotel.dlt.service.impl;

import com.alibaba.fastjson.JSON;
import com.travel.channel.dao.ChannelAgentPOMapper;
import com.travel.channel.dao.DltMapRoomPOMapper;
import com.travel.channel.dto.request.HotelOrderOperateRequestDTO;
import com.travel.channel.dto.response.HotelOrderOperateResponseDTO;
import com.travel.channel.entity.po.ChannelAgentPO;
import com.travel.channel.entity.po.ChannelAgentPOExample;
import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.channel.entity.po.DltMapRoomPOExample;
import com.travel.common.enums.ChannelEnum;
import com.travel.common.enums.ChannelOrderStateEnum;
import com.travel.common.enums.OrderOperateEnum;
import com.travel.common.enums.OrderStateEnum;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.dlt.dao.ChannelOrderStatusEnum;
import com.travel.hotel.dlt.dao.DltOrderCancelRulesPOMapper;
import com.travel.hotel.dlt.dao.DltOrderDayPricePOMapper;
import com.travel.hotel.dlt.dao.DltOrderDetailPOMapper;
import com.travel.hotel.dlt.dao.DltOrderPOMapper;
import com.travel.hotel.dlt.entity.po.DltOrderCancelRulesPO;
import com.travel.hotel.dlt.entity.po.DltOrderDayPricePO;
import com.travel.hotel.dlt.entity.po.DltOrderDayPricePOExample;
import com.travel.hotel.dlt.entity.po.DltOrderDetailPO;
import com.travel.hotel.dlt.entity.po.DltOrderDetailPOExample;
import com.travel.hotel.dlt.entity.po.DltOrderPO;
import com.travel.hotel.dlt.entity.po.DltOrderPOExample;
import com.travel.hotel.dlt.enums.OrderStatusEnum;
import com.travel.hotel.dlt.enums.ResultCodeEnum;
import com.travel.hotel.dlt.request.dto.AddOrderReqRequestDTO;
import com.travel.hotel.dlt.request.dto.OrderRequestDTO;
import com.travel.hotel.dlt.request.dto.OrderRequestPriceDTO;
import com.travel.hotel.dlt.response.ResponseDTO;
import com.travel.hotel.dlt.response.dto.CancelRule;
import com.travel.hotel.dlt.response.dto.ChangeChannelOrderCodeRequestDTO;
import com.travel.hotel.dlt.response.dto.CreateOrderProductRequestDTO;
import com.travel.hotel.dlt.response.dto.CreateOrderRequestDTO;
import com.travel.hotel.dlt.response.dto.DltOrderInfo;
import com.travel.hotel.dlt.response.dto.DltOrderRoomPrice;
import com.travel.hotel.dlt.response.dto.OrderDetailRequestDTO;
import com.travel.hotel.dlt.response.dto.OrderDetailResponseDTO;
import com.travel.hotel.dlt.response.dto.SupplyProductPriceDTO;
import com.travel.hotel.dlt.service.DltHotelOrderOperateService;
import com.travel.hotel.dlt.service.DltHotelOrderService;
import com.travel.hotel.dlt.utils.OrderInterfaceInvoker;
import com.travel.hotel.dlt.utils.StringUtil;
import com.travel.hotel.order.dao.OrderPOMapper;
import com.travel.hotel.order.entity.po.OrderPO;
import com.travel.hotel.order.entity.po.OrderPOExample;
import com.travel.hotel.order.service.HotelOrderService;
import com.travel.hotel.product.entity.po.PricePlanPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *   2018/3/12.
 */
@Service("dltHotelOrderService")
public class DltHotelOrderServiceImpl implements DltHotelOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(DltHotelOrderServiceImpl.class);

    @Autowired
    private HotelOrderService hotelOrderServiceImpl;

    @Autowired
    private ChannelAgentPOMapper channelAgentPOMapper;

    @Autowired
    private DltMapRoomPOMapper dltMapRoomPOMapper;

    @Autowired
    private DltOrderPOMapper dltOrderPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private DltOrderDetailPOMapper dltOrderDetailPOMapper;

    @Autowired
    private DltOrderCancelRulesPOMapper dltOrderCancelRulesPOMapper;

    @Autowired
    private DltOrderDayPricePOMapper dltOrderDayPricePOMapper;

    @Autowired
    private HotelOrderService hotelDltOrderServiceImpl;

    @Autowired
    private DltHotelOrderOperateService dltHotelOrderOperateServiceImpl;

    @Override
    public void createOrder(DltOrderInfo dltOrderInfo,String merchantCode) {

        if (null == dltOrderInfo || null == dltOrderInfo.getDltOrderId()) {
            LOG.error("创建代理通订单参数错误，dltOrderInfo=" + dltOrderInfo);
            return;
        }

        if (null == dltOrderInfo.getRoomId()) {
            LOG.error("创建代理通订单参数错误，roomid=null");
            return;
        }

        StringBuilder orderModifyLog = new StringBuilder();
        String dltOrderId = dltOrderInfo.getDltOrderId();
        String mOrderId = null;
        boolean roomIdChangeFlag = false;
        boolean checkInDateChangeFlag = false;
        boolean checkOutDateChangeFlag = false;
        boolean guestNameChangeFlag = false;
        boolean roomNumChangeFlag = false;
        boolean orderStatusChangeFlag = false;
        StringBuilder orderChangeSB = new StringBuilder();

        // 将订单信息保存到本地库
        LOG.debug("createOrder step1 saveOrderDetail:"+dltOrderInfo);
        try {
            DltOrderDetailPO dltOrderDetailPO = this.copyOrderDetail(dltOrderInfo);

            DltOrderDetailPOExample example = new DltOrderDetailPOExample();
            example.createCriteria().andDltOrderIdEqualTo(dltOrderId);
            List<DltOrderDetailPO> dltOrderDetailPOList = dltOrderDetailPOMapper.selectByExample(example);

            // 订单不存在，直接插入
            if (CollectionUtils.isEmpty(dltOrderDetailPOList)) {
                //判断关联订单是否存在:延住单不用判断，直接生成新单即可
                LOG.info("lastDltOrderId:"+dltOrderDetailPO.getLastDltOrderId());
                if ( !"D".equals(dltOrderDetailPO.getFormType())){
                    if (StringUtil.isValidString(dltOrderDetailPO.getLastDltOrderId())){
                        example = new DltOrderDetailPOExample();
                        example.createCriteria().andDltOrderIdEqualTo(dltOrderDetailPO.getLastDltOrderId());
                        dltOrderDetailPOList = dltOrderDetailPOMapper.selectByExample(example);
                        LOG.info("last_dltOrderDetailPOList.size:"+dltOrderDetailPOList.size());
                    }
                    if (CollectionUtils.isEmpty(dltOrderDetailPOList)) {
                        example = new DltOrderDetailPOExample();
                        example.createCriteria().andOrderIdEqualTo(dltOrderDetailPO.getOrderId());
                        example.createCriteria().andZhOrderCodeIsNotNull();
                        dltOrderDetailPOList = dltOrderDetailPOMapper.selectByExample(example);
                        LOG.info("morderid_last_dltOrderDetailPOList.size:"+dltOrderDetailPOList.size());
                    }
                    if (!CollectionUtils.isEmpty(dltOrderDetailPOList)) {
                        DltOrderDetailPO existsOrderDetailPo = dltOrderDetailPOList.get(0);
                        mOrderId = existsOrderDetailPo.getZhOrderCode();
                        dltOrderDetailPO.setZhOrderCode(mOrderId);

                        orderStatusChangeFlag = this.compareString(existsOrderDetailPo.getOrderStatus(), dltOrderDetailPO.getOrderStatus());
                        orderChangeSB = this.compareOrderDetail(existsOrderDetailPo, dltOrderDetailPO);
                        roomIdChangeFlag = this.compareString(existsOrderDetailPo.getRoomId(),dltOrderDetailPO.getRoomId());
                        checkInDateChangeFlag = this.compareDate(existsOrderDetailPo.getCheckInDate(),dltOrderDetailPO.getCheckInDate());
                        checkOutDateChangeFlag = this.compareDate(existsOrderDetailPo.getCheckOutDate(),dltOrderDetailPO.getCheckOutDate());
                        guestNameChangeFlag = this.compareString(existsOrderDetailPo.getCustomerName(),dltOrderDetailPO.getCustomerName());
                        roomNumChangeFlag = this.compareInteger(existsOrderDetailPo.getRoomNum(),dltOrderDetailPO.getRoomNum());
                    }
                }
                dltOrderDetailPOMapper.insert(dltOrderDetailPO);
            } else {
                // 订单存在，需要对比，看看哪里发生变化了，需要提示客户
                DltOrderDetailPO existsOrderDetailPo = dltOrderDetailPOList.get(0);
                mOrderId = existsOrderDetailPo.getZhOrderCode();
                LOG.info("代理通订单"+existsOrderDetailPo.getDltOrderId()+"存在，mOrderId:"+mOrderId);
                orderStatusChangeFlag = this.compareString(existsOrderDetailPo.getOrderStatus(), dltOrderDetailPO.getOrderStatus());
                orderChangeSB = this.compareOrderDetail(existsOrderDetailPo, dltOrderDetailPO);
                checkInDateChangeFlag = this.compareDate(existsOrderDetailPo.getCheckInDate(),dltOrderDetailPO.getCheckInDate());
                checkOutDateChangeFlag = this.compareDate(existsOrderDetailPo.getCheckOutDate(),dltOrderDetailPO.getCheckOutDate());
                guestNameChangeFlag = this.compareString(existsOrderDetailPo.getCustomerName(),dltOrderDetailPO.getCustomerName());
                roomNumChangeFlag = this.compareInteger(existsOrderDetailPo.getRoomNum(),dltOrderDetailPO.getRoomNum());
                // 如果没有发生变化，不需要更新db
                if (orderChangeSB.length() > 0) {
                    orderModifyLog.append(orderChangeSB);

                    dltOrderDetailPO.setId(existsOrderDetailPo.getId());
                    dltOrderDetailPO.setModifier("system");
                    dltOrderDetailPO.setModifyDate(new Date());
                    dltOrderDetailPOMapper.updateByPrimaryKeySelective(dltOrderDetailPO);
                }
            }
        } catch (Exception e) {
            LOG.error("插入或更新代理通订单详细信息至数据库失败", e);
            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "插入或更新代理通订单详细信息至数据库失败");
            return;
        }


//        // 将订单取消政策保存到本地库
//        try {
//            List<DltOrderCancelRulesPO> dltOrderCancelRulesPOList = this.copyOrderCancelRules(dltOrderInfo);
//            // 查询档期库里面的取消政策
//            DltOrderCancelRulesPOExample example = new DltOrderCancelRulesPOExample();
//            example.createCriteria().andDltOrderIdEqualTo(dltOrderId);
//            List<DltOrderCancelRulesPO> existsOrderCancelRulesPOList = dltOrderCancelRulesPOMapper.selectByExample(example);
//
//            if (!CollectionUtils.isEmpty(dltOrderCancelRulesPOList)) {
//
//                if (CollectionUtils.isEmpty(existsOrderCancelRulesPOList)) {
//                    orderModifyLog.append("\n新增了订单取消规则");
//                    dltOrderCancelRulesPOMapper.insertBatch(dltOrderCancelRulesPOList);
//                } else {
//
//                    StringBuilder sb = this.compareOrderCancelRules(existsOrderCancelRulesPOList, dltOrderCancelRulesPOList);
//                    if (sb.length() > 0) {
//                        orderModifyLog.append(sb);
//
//                        dltOrderCancelRulesPOMapper.deleteByExample(example);
//                        dltOrderCancelRulesPOMapper.insertBatch(dltOrderCancelRulesPOList);
//                    }
//                }
//            } else {
//                if (!CollectionUtils.isEmpty(existsOrderCancelRulesPOList)) {
//                    orderModifyLog.append("\n删除了订单取消规则");
//                }
//            }
//        } catch (Exception e) {
//            LOG.error("插入代理通订单取消规则详细信息至数据库失败", e);
//            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "插入代理通订单取消规则详细信息至数据库失败");
//            return;
//        }

        // 将订单每日价格保存到本地库
        try {
            List<DltOrderDayPricePO> dltOrderDayPricePOList = this.copyOrderDayPrices(dltOrderInfo);

            if (!CollectionUtils.isEmpty(dltOrderDayPricePOList)) {

                DltOrderDayPricePOExample example = new DltOrderDayPricePOExample();
                example.createCriteria().andDltOrderIdEqualTo(dltOrderId);
                List<DltOrderDayPricePO> existsOrderDayPriceList = dltOrderDayPricePOMapper.selectByExample(example);
                if (CollectionUtils.isEmpty(existsOrderDayPriceList)) {
                    orderModifyLog.append("订单新增了每日价格信息；");
                    dltOrderDayPricePOMapper.insertBatch(dltOrderDayPricePOList);
                } else {

                    StringBuilder sb = this.compareOrderDayPrice(existsOrderDayPriceList, dltOrderDayPricePOList);
                    if (sb.length() > 0) {
                        orderModifyLog.append(sb);

                        dltOrderDayPricePOMapper.deleteByExample(example);
                        dltOrderDayPricePOMapper.insertBatch(dltOrderDayPricePOList);
                    }
                }
            } else {
                LOG.error("代理通订单每日价格详细信息缺失, dltOrderId:" + dltOrderInfo.getDltOrderId());
                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "代理通订单每日价格详细信息缺失");
                return;
            }
        } catch (Exception e) {
            LOG.error("插入代理通订单每日价格详细信息至数据库失败", e);
            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "插入代理通订单每日价格详细信息至数据库失败");
            return;
        }

        String channelCode = this.getChannel(dltOrderInfo.getChannel());
        if (null == channelCode) {
            LOG.error("代理通订单渠道无法识别，channel:" + dltOrderInfo.getChannel() + ", dltOrderId: " + dltOrderInfo.getDltOrderId());
            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false,
                    "代理通订单渠道无法识别，channel:" + dltOrderInfo.getChannel() + ", dltOrderId: " + dltOrderInfo.getDltOrderId());
            return;
        }

        //更新渠道订单状态
        LOG.debug("createOrder step2 UpdateChannelOrderStatus:mOrderId="+mOrderId+";dltOrderInfo:"+dltOrderInfo);
        try{
            if (StringUtil.isValidString(mOrderId) && Integer.valueOf(mOrderId)>0 && !orderStatusChangeFlag){
                if(!this.saveOrUpdateChannelOrderStatus(dltOrderInfo.getChannel(),dltOrderInfo.getFormType(),Integer.valueOf(dltOrderInfo.getOrderStatus()),merchantCode,Integer.valueOf(mOrderId))) {
                    LOG.error("更新渠道订单状态失败,dltOrderStatus="+dltOrderInfo.getOrderStatus()+",mOrderId="+mOrderId);
                }
            }
        } catch (Exception e){
            LOG.error("更新渠道订单状态异常",e);
        }

        String orderState = this.transferOrderState(dltOrderInfo.getFormType());
        if (null == orderState) {
            LOG.error("代理通订单formType: "+dltOrderInfo.getFormType()+"，无须处理，dltOrderId: " + dltOrderInfo.getDltOrderId());
            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false,
                    "代理通订单formType: "+dltOrderInfo.getFormType()+"，无须处理，dltOrderId: " + dltOrderInfo.getDltOrderId());
            return;
        }
        //获取处理状态
        Integer processState= ChannelOrderStatusEnum.getProcessStateByChannelAndStatus(dltOrderInfo.getChannel(),Integer.valueOf(dltOrderInfo.getOrderStatus()));
        if (processState==null || processState==2){
            LOG.error("代理通已拒绝请求，无须处理，dltOrderId: " + dltOrderInfo.getDltOrderId());
            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false,
                    "代理通已拒绝请求，无须处理，dltOrderId: " + dltOrderInfo.getDltOrderId());
            return;
        }
        if (processState==3){
            LOG.error("订单未支付，无须处理，dltOrderId: " + dltOrderInfo.getDltOrderId());
            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false,
                    "订单未支付，无须处理，dltOrderId: " + dltOrderInfo.getDltOrderId());
            return;
        }

        ResponseDTO<OrderDetailResponseDTO> queryOrderResponse = null;
        OrderDetailResponseDTO orderDetailResponseDTO = null;
        /**
         *FIXME 有可能调用远程BMS接口创建成功，但是在更新mOrderId失败了。如果此时用mOrderId去查询。是查询不到数据的。
         *FIXME 应该用 customer_order_code + channel_code + merchantCode 把BMS的订单查询回来，然后更新到t_dlt_Order_detail表中去mOrderId
         */
        if(StringUtil.isValidString(mOrderId) && Integer.valueOf(mOrderId)>0) {
            OrderDetailRequestDTO orderDetailRequestDTO = new OrderDetailRequestDTO();
            orderDetailRequestDTO.setOrderId(Integer.valueOf(mOrderId));
            queryOrderResponse = OrderInterfaceInvoker.queryOrder(JSON.toJSONString(orderDetailRequestDTO));
            String jsonStr = JSON.toJSONString(queryOrderResponse.getModel());
            orderDetailResponseDTO = JSON.parseObject(jsonStr,OrderDetailResponseDTO.class);
        }

        LOG.debug("createOrder step3 addOrderRequest:"+orderState);
        /**
         * 处理取消申请
         * 申请取消的如果已经确认或者已经取消，就直接调一次接口拒绝掉，不影响内部订单状态，否则就改为申请取消中，拒绝后变为处理中
         */
        if (OrderStateEnum.APPLYING_CANCEL.code.equals(orderState)) {
            if (null == orderDetailResponseDTO) {
                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "未查询到订单记录");
                return;
            }

            // 订单已经是取消状态
            if (OrderStatusEnum.CANCELED.key == orderDetailResponseDTO.getOrderStatus()) {
                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), true, "订单取消成功");
                return;
            }

            // 订单已经确认或者已经拒绝，则不可申请取消，直接发一个拒绝回去
//            if (OrderStatusEnum.TRADED.key == orderDetailResponseDTO.getOrderStatus()
//                    || OrderStatusEnum.CANCELED.key == orderDetailResponseDTO.getOrderStatus()) {
//                Boolean bln = this.refuse(orderDetailResponseDTO, dltOrderInfo);
//                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), bln, "拒绝取消" + (bln ? "成功" : "失败"));
//                return;
//            }

            //订单是非取消状态，发送取消申请到订单系统
            OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
            orderRequestDTO.setOtaHandleResult(processState);
            orderRequestDTO.setOrderId(orderDetailResponseDTO.getOrderId());
            orderRequestDTO.setRequestType((byte)1);
            orderRequestDTO.setCreator("代理通");
            orderRequestDTO.setNote("渠道客人发起取消");
            ResponseDTO cancelOrderResponseDTO = OrderInterfaceInvoker.addOrderRequest(JSON.toJSONString(orderRequestDTO));

            if (null == cancelOrderResponseDTO || null == cancelOrderResponseDTO.getResult()
                    || 0 == cancelOrderResponseDTO.getResult() || null == cancelOrderResponseDTO.getModel()) {
                LOG.error("调用订单服务发送订单取消申请失败，参数：" + orderDetailResponseDTO.getOrderId());
                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, cancelOrderResponseDTO.getFailReason());
                return;
            } else {
                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), true, "订单取消申请发送成功");
            }
        }
        else {

            // 向订单系统发送订单
            //申请修改的如果已经确认或者已经取消，就直接调一次接口拒绝掉，不影响内部订单状态，否则就直接修改订单信息，确认的时候提示订单状态处于修改申请中，请再次确认订单信息
            ResponseDTO responseDTO = null;
            CreateOrderRequestDTO createOrderRequestDTO;
            try {
                createOrderRequestDTO = this.buildCreateOrderRequestDTO(dltOrderInfo, channelCode,merchantCode);
//                createOrderRequestDTO.setRemark(orderModifyLog.toString());
//                createOrderRequestDTO.setOrderState(orderState);
                createOrderRequestDTO.setChannelCode(channelCode);
            } catch (Exception e) {
                String msg = e instanceof ServiceException ? e.getMessage() : "构建下单请求失败";
                LOG.error(msg);
                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, msg);
                return;
            }

            try {
                //新单或者修改单但找不到原单，都创建订单
                if (OrderStateEnum.NEW.code.equals(orderState)
                        || OrderStateEnum.APPLYING_MODIFY.code.equals(orderState) && null==orderDetailResponseDTO) {
                    if(null==orderDetailResponseDTO) {
                        responseDTO = OrderInterfaceInvoker.addManualOrder(JSON.toJSONString(createOrderRequestDTO));
                        LOG.info(createOrderRequestDTO.getCustomerOrderCode()+"下单结果："+JSON.toJSONString(responseDTO));
                        if(null != responseDTO && null != responseDTO.getModel()) {
                            mOrderId = responseDTO.getModel().toString();
                            LOG.info("下单成功，mOrderId:"+mOrderId);
                            DltOrderDetailPO po = new DltOrderDetailPO();
                            po.setZhOrderCode(responseDTO.getModel().toString());
                            po.setModifier("system");
                            po.setModifyDate(new Date());

                            DltOrderDetailPOExample orderDetailPOExample = new DltOrderDetailPOExample();
                            orderDetailPOExample.createCriteria().andDltOrderIdEqualTo(dltOrderId);
                            dltOrderDetailPOMapper.updateByExampleSelective(po, orderDetailPOExample);
                            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), true, "订单处理成功");

                            //更新渠道订单状态
                            try{
                                if(!this.saveOrUpdateChannelOrderStatus(dltOrderInfo.getChannel(),dltOrderInfo.getFormType(),Integer.valueOf(dltOrderInfo.getOrderStatus()),merchantCode,Integer.valueOf(null==mOrderId?"0":mOrderId))) {
                                    LOG.error("更新渠道订单状态失败,dltOrderStatus="+dltOrderInfo.getOrderStatus()+",mOrderId="+mOrderId);
                                }
                            } catch (Exception e){
                                LOG.error("更新渠道订单状态异常",e);
                            }
                        } else{
                            LOG.error("向订单系统下单或修改单失败，下单参数：" + createOrderRequestDTO);
                            this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false,
                                    null == responseDTO ? "向订单系统下单或修改单失败" :responseDTO.getFailReason());
                            return;
                        }

                    }else {
                        this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "订单已经存在");
                    }

                } else if (OrderStateEnum.APPLYING_MODIFY.code.equals(orderState)) {
                    if (null == orderDetailResponseDTO) {
                        //有可能此时预订单还没同步回来， 直接被修改了。
                        this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "未查询到订单记录");
                        return;
                    }

                    // 已经取消，则不可申请修改，直接发一个拒绝回去
                    // 代理通发起修改，但是订单内容没有变化
                    if (OrderStatusEnum.CANCELED.key == orderDetailResponseDTO.getOrderStatus()
                            || orderChangeSB.length() <= 0) {
                        Boolean bln = this.refuse(orderDetailResponseDTO, dltOrderInfo);
                        this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), bln, "拒绝修改" + (bln ? "成功" : "失败"));
                        return;
                    }

                    //订单是非取消状态
                    AddOrderReqRequestDTO orderRequestDTO = new AddOrderReqRequestDTO();
                    orderRequestDTO.setOtaHandleResult(processState);
                    orderRequestDTO.setOrderId(orderDetailResponseDTO.getOrderId());
                    orderRequestDTO.setRequestType((byte)2);
                    orderRequestDTO.setCreator("代理通");
                    orderRequestDTO.setCustomerOrderCode(dltOrderInfo.getOrderId());
                    StringBuilder note=new StringBuilder();
                    //入住日期发生变更
                    orderRequestDTO.setCheckinDate(dltOrderInfo.getCheckinDate());
                    if(!checkInDateChangeFlag) {
                        note.append("入住日期发生变更；");
                    }
                    //离店日期发生变更
                    orderRequestDTO.setCheckoutDate(dltOrderInfo.getCheckoutDate());
                    if(!checkOutDateChangeFlag) {
                        note.append("离店日期发生变更；");
                    }
                    //产品发生变更
                    orderRequestDTO.setRateplanId(createOrderRequestDTO.getRatePlanList().get(0).getRateplanId());
                    if (!roomIdChangeFlag){
                        note.append("产品发生变更；");
                    }

                    List<OrderRequestPriceDTO> orderRequestPriceDTOList=new ArrayList<>();
                    List<SupplyProductPriceDTO> priceDTOList=createOrderRequestDTO.getRatePlanList().get(0).getDayList();
                    for (SupplyProductPriceDTO priceDTO:priceDTOList){
                        OrderRequestPriceDTO orderRequestPriceDTO=new OrderRequestPriceDTO();
                        orderRequestPriceDTO.setSaleDate(priceDTO.getSaleDate());
                        orderRequestPriceDTO.setSalePrice(priceDTO.getSalePrice());
                        orderRequestPriceDTOList.add(orderRequestPriceDTO);
                    }
                    orderRequestDTO.setOrderRequestPriceDTOS(orderRequestPriceDTOList);

                    //客人姓名发生变更
                    orderRequestDTO.setGuestNames(dltOrderInfo.getCustomerName());
                    if(!guestNameChangeFlag) {
                        note.append("客人姓名发生变更；");
                    }
                    //房间数发生变更
                    orderRequestDTO.setRoomNum(dltOrderInfo.getRoomnum());
                    if(!roomNumChangeFlag) {
                        note.append("房间数发生变更；");
                    }
                    orderRequestDTO.setNote(note.toString());
                    ResponseDTO modifyOrderResponseDTO = OrderInterfaceInvoker.addOrderRequest(JSON.toJSONString(orderRequestDTO));

                    if (null == modifyOrderResponseDTO || null == modifyOrderResponseDTO.getResult()
                            || 0 == modifyOrderResponseDTO.getResult() || null == modifyOrderResponseDTO.getModel()) {
                        LOG.error("调用订单服务发送订单修改申请失败，参数：" + orderDetailResponseDTO.getOrderId());
                        this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, modifyOrderResponseDTO.getFailReason());
                        return;
                    } else {
                        this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), true, "订单修改申请发送成功");
                    }
                } else {
                    LOG.error("订单操作类型无效，无法操作订单：" + createOrderRequestDTO);
                    this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "订单操作类型无效");
                    return;
                }
            } catch (Exception e) {
                LOG.error("向订单系统下单或修改单失败", e);
                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false, "向订单系统下单或修改单失败");
                return;
            }
//这个responseDTO 只在创建手工单的时候才赋值了。为什么要放到这里执行。为什么不直接放到上面的创建订单。
//            if (null != responseDTO  && null != responseDTO.getResult() && responseDTO.getResult() == 1) {
//                DltOrderDetailPO po = new DltOrderDetailPO();
//                po.setZhOrderCode(responseDTO.getModel().toString());
//                po.setModifier("system");
//                po.setModifyDate(new Date());
//
//                DltOrderDetailPOExample orderDetailPOExample = new DltOrderDetailPOExample();
//                orderDetailPOExample.createCriteria().andDltOrderIdEqualTo(dltOrderId);
//                dltOrderDetailPOMapper.updateByExampleSelective(po, orderDetailPOExample);
//                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), true, "订单处理成功");
//            } else {
//                LOG.error("向订单系统下单或修改单失败，下单参数：" + createOrderRequestDTO);
//                this.updateOrderHandleResult(dltOrderInfo.getDltOrderId(), false,
//                        null == responseDTO ? "向订单系统下单或修改单失败" :responseDTO.getFailReason());
//                return;
//            }
            LOG.debug("createOrder step4 end");
        }
    }

    private Boolean refuse(OrderDetailResponseDTO orderDetailResponseDTO, DltOrderInfo dltOrderInfo) {
        try {
            HotelOrderOperateRequestDTO requestDTO = new HotelOrderOperateRequestDTO();
            requestDTO.setChannelCode(orderDetailResponseDTO.getChannelCode());
            requestDTO.setOrderCode(orderDetailResponseDTO.getOrderCode());
            requestDTO.setCustomerOrderCode(orderDetailResponseDTO.getCustomerOrderCode());
            requestDTO.setOrderState(transferOrderStateToDlt(orderDetailResponseDTO.getOrderStatus()));


            String orderState = this.transferOrderState(dltOrderInfo.getFormType());
            String operateType = OrderOperateEnum.CTRIP_REFUSE.code;
            if (requestDTO.getChannelCode().startsWith("ctrip")) {
                operateType = OrderStateEnum.APPLYING_CANCEL.code.equals(orderState) ? OrderOperateEnum.CTRIP_REFUSE_CANCEL.code : OrderOperateEnum.CTRIP_REFUSE.code;
            } else if (requestDTO.getChannelCode().startsWith("qunar")) {
                operateType = OrderStateEnum.APPLYING_CANCEL.code.equals(orderState) ? OrderOperateEnum.QUNAR_REFUSE_UNSUBSCRIBE.code : OrderOperateEnum.CTRIP_REFUSE.code;
            }
            requestDTO.setOperateType(operateType);
            requestDTO.setRefuseType(3);
            HotelOrderOperateResponseDTO responseDTO = dltHotelOrderOperateServiceImpl.operateOrder(requestDTO);
            return (null != responseDTO && 1 == responseDTO.getIsSuccess());
        } catch (Exception e) {
            LOG.error("拒绝取消或拒绝修改操作失败", e);
        }
        return false;
    }


    private OrderPO queryOrderByCustomerOrderCode(String customerOrderCode, String channelCode) {

        List<OrderPO> orderPOList;
        try {
            OrderPOExample orderPOExample = new OrderPOExample();
            orderPOExample.createCriteria().andCustomerOrderCodeEqualTo(customerOrderCode).andChannelCodeEqualTo(channelCode);
            orderPOList = orderPOMapper.selectByExample(orderPOExample);
        } catch (Exception e) {
            LOG.error("查询订单出错， customerOrderCode=" + customerOrderCode + ", channelCode=" + channelCode, e);
            return null;
        }
        if (CollectionUtils.isEmpty(orderPOList)) {
            return null;
        }
        return orderPOList.get(0);
    }

//    private CreateOrderRequestDTO buildCreateOrderRequestDTO(DltOrderInfo dltOrderInfo, String channelCode) {
//        CreateOrderRequestDTO request = new CreateOrderRequestDTO();
//        request.setCustomerOrderCode(dltOrderInfo.getOrderId());// orderId才是代理通订单号，可以在代理通后台搜索的
//        request.setPayMethod(PayMethodEnum.PREPAYALL.code);
//        request.setSaleCurrency(null == dltOrderInfo.getOrderCurrency() ? CurrencyEnum.CNY.code : dltOrderInfo.getOrderCurrency());
//        request.setSalePrice(dltOrderInfo.getOrderPrice());
//        request.setChildChannelCode(dltOrderInfo.getChannel());
//        request.setChannelState(this.transferChannelState(dltOrderInfo.getOrderStatus(), channelCode));
//        request.setGuestName(dltOrderInfo.getCustomerName());
//        request.setCheckinDate(dltOrderInfo.getCheckinDate());
//        request.setCheckoutDate(dltOrderInfo.getCheckoutDate());
//
//        ChannelAgentPOExample example = new ChannelAgentPOExample();
//        example.createCriteria().andChannelCodeEqualTo(channelCode);
//        List<ChannelAgentPO> channelAgentPOList = channelAgentPOMapper.selectByExample(example);
//        if (CollectionUtils.isEmpty(channelAgentPOList)) {
//            throw new ServiceException("未查询到客户和渠道关联关系，请检查t_channel_agent表的配置, channel=" + channelCode);
//        }
//        request.setAgentCode(channelAgentPOList.get(0).getAgentCode());
//        request.setAgentName(channelAgentPOList.get(0).getAgentName());
//        request.setCreator(channelAgentPOList.get(0).getAgentName());
//
//        DltMapRoomPOExample roomPOExample = new DltMapRoomPOExample();
//        roomPOExample.createCriteria().andDltRoomIdEqualTo(Long.valueOf(dltOrderInfo.getRoomId()));
//        List<DltMapRoomPO> dltMapRoomPOList = dltMapRoomPOMapper.selectByExample(roomPOExample);
//        if (CollectionUtils.isEmpty(dltMapRoomPOList)) {
//            throw new ServiceException("未查询到代理通售卖房型和本地价格计划的匹配信息，roomId=" + dltOrderInfo.getRoomId());
//        }
//        DltMapRoomPO dltMapRoomPO = dltMapRoomPOList.get(0);
//
//        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
//        OrderProductDTO product = new OrderProductDTO();
//        product.setHotelId(dltMapRoomPO.getZhHotelId());
//        product.setRoomTypeId(dltMapRoomPO.getZhRoomId());
//        product.setPriceplanId(dltMapRoomPO.getZhRpId());
//        product.setCheckinDate(dltOrderInfo.getCheckinDate());
//        product.setCheckoutDate(dltOrderInfo.getCheckoutDate());
//        product.setProductType(OrderProductTypeEnum.ROOM.code);
//
//        List<OrderDayPriceDTO> orderDayPriceDTOList = new ArrayList<>();
//        for (DltOrderRoomPrice dorp : dltOrderInfo.getRoomPriceList()) {
//            OrderDayPriceDTO dayPrice = new OrderDayPriceDTO();
//            dayPrice.setPriceplanId(dltMapRoomPO.getZhRpId());
//            dayPrice.setSaleDate(dorp.getEffectDate());
//            dayPrice.setSaleBCurrency(dorp.getCurrency());
//            dayPrice.setSaleBPrice(dorp.getPrice());
//            dayPrice.setBreakfastNum(BreakfastEnum.getKeyByNum(dorp.getBreakfastNum()));//床位早如何表示？
//            dayPrice.setRooms(dltOrderInfo.getRoomnum());
//            orderDayPriceDTOList.add(dayPrice);
//        }
//
//        product.setOrderDayPriceDTOList(orderDayPriceDTOList);
//        orderProductDTOList.add(product);
//        request.setOrderProductDTOList(orderProductDTOList);
//        return request;
//    }

    /**
     * 构建下单参数
     * @param dltOrderInfo
     * @param channelCode
     * @return
     */
    private CreateOrderRequestDTO buildCreateOrderRequestDTO(DltOrderInfo dltOrderInfo, String channelCode, String merchantCode) {
        CreateOrderRequestDTO request = new CreateOrderRequestDTO();
        request.setCustomerOrderCode(dltOrderInfo.getOrderId());// orderId才是代理通订单号，可以在代理通后台搜索的
        request.setChannelCode(dltOrderInfo.getChannel());
        request.setIsHoldRoom((StringUtil.isValidString(dltOrderInfo.getIsHoldRoom()) && "R".equals(dltOrderInfo.getIsHoldRoom())) ? "1" : "0");
        List<String> guestNameLst = new ArrayList<>();
        guestNameLst.add(dltOrderInfo.getCustomerName());
        request.setGuestList(guestNameLst);
        request.setIsGroupRoom(0);
        request.setIsManualOrder(0);
       request.setMerchantCode(merchantCode);
//        request.setMerchantName();
        request.setOrderSum(dltOrderInfo.getOrderPrice());
        request.setSpecialRequest(dltOrderInfo.getSpecialMemo());

        ChannelAgentPOExample example = new ChannelAgentPOExample();
        ChannelAgentPOExample.Criteria criteria = example.createCriteria();
        criteria.andChannelCodeEqualTo(channelCode);
        criteria.andMerchantCodeEqualTo(merchantCode);
        List<ChannelAgentPO> channelAgentPOList = channelAgentPOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(channelAgentPOList)) {
            throw new ServiceException("未查询到客户和渠道关联关系，请检查t_channel_agent表的配置, channel=" + channelCode);
        }
        request.setAgentCode(channelAgentPOList.get(0).getAgentCode());
        request.setCreator(channelAgentPOList.get(0).getAgentName());

        DltMapRoomPOExample roomPOExample = new DltMapRoomPOExample();
        roomPOExample.createCriteria().andDltRoomIdEqualTo(Long.valueOf(dltOrderInfo.getRoomId()));
        //roomPOExample.createCriteria().andDltRoomIdEqualTo(96866321l);
        List<DltMapRoomPO> dltMapRoomPOList = dltMapRoomPOMapper.selectByExample(roomPOExample);
        if (CollectionUtils.isEmpty(dltMapRoomPOList)) {
            throw new ServiceException("未查询到代理通售卖房型和本地价格计划的匹配信息，roomId=" + dltOrderInfo.getRoomId());
        }
        DltMapRoomPO dltMapRoomPO = dltMapRoomPOList.get(0);

        List<CreateOrderProductRequestDTO> ratePlanList = new ArrayList<>();
        CreateOrderProductRequestDTO productRequestDTO = new CreateOrderProductRequestDTO();

        request.setHotelId(dltMapRoomPO.getZhHotelId().intValue());
        productRequestDTO.setRoomTypeId(dltMapRoomPO.getZhRoomId().toString());
        productRequestDTO.setRoomTypeName(dltMapRoomPO.getDltRoomName());
        productRequestDTO.setRateplanId(dltMapRoomPO.getZhRpId().intValue());
        productRequestDTO.setCheckinDate(DateUtil.dateToString(dltOrderInfo.getCheckinDate()));
        productRequestDTO.setCheckoutDate(DateUtil.dateToString(dltOrderInfo.getCheckoutDate()));
        productRequestDTO.setRoomNum(dltOrderInfo.getRoomnum());
        //早餐转换
//        Integer breakfastType = 0;
//        if(!CollectionUtils.isEmpty(dltOrderInfo.getRoomPriceList())) {
//            if(null != dltOrderInfo.getRoomPriceList().get(0).getBreakfastNum()) {
//                if(dltOrderInfo.getRoomPriceList().get(0).getBreakfastNum() == -1) {
//                    breakfastType = 4;//人头早
//                }else {
//                    //apollo 早餐定义：1，无早 2：单早，3：双早，4：人头早
//                    breakfastType = dltOrderInfo.getRoomPriceList().get(0).getBreakfastNum() + 1;
//                }
//            }
//        }
//        productRequestDTO.setBreakfastType(dltOrderInfo.getRoomPriceList().get(0).getBreakfastNum());

        // 早餐不取代理通的，直接取内部系统价格计划的早餐。 modify by zhengxiongwei
        PricePlanPO breakfastPO = dltMapRoomPOMapper.queryPricePlan(dltMapRoomPO.getZhRpId().intValue());
        productRequestDTO.setBreakfastType(breakfastPO.getBreakfast());

        List<SupplyProductPriceDTO> dayList = new ArrayList<>();
        for (DltOrderRoomPrice dorp : dltOrderInfo.getRoomPriceList()) {
            SupplyProductPriceDTO dayPrice = new SupplyProductPriceDTO();
            dayPrice.setSupplyProductId(dltMapRoomPO.getZhRpId().intValue());
            dayPrice.setSaleDate(DateUtil.dateToString(dorp.getEffectDate()));
            dayPrice.setSalePrice(dorp.getPrice());
            dayList.add(dayPrice);
        }

        productRequestDTO.setDayList(dayList);
        ratePlanList.add(productRequestDTO);
        request.setRatePlanList(ratePlanList);
        return request;
    }

    private String getChannel(String channel) {

        if (StringUtils.isEmpty(channel)) {
            LOG.error("代理通渠道或子渠道为空");
            return null;
        }

        if (channel.toLowerCase().equals(ChannelEnum.QUNAR.key)) {
            return ChannelEnum.QUNAR.key;
        }

//        if (channel.toLowerCase().equals("ebk")
//                || channel.toLowerCase().equals("send")
//                || channel.toLowerCase().equals("email")
//                || channel.toLowerCase().equals("sms")
//                || channel.toLowerCase().equals("fax")
//                || channel.toLowerCase().equals("directconn")
//                || channel.toLowerCase().equals("notctriporder")
//                || channel.toLowerCase().equals("supplier")) {
//            return ChannelEnum.CTRIP.key;
//        }

        if (channel.toLowerCase().equals("ebk")) {
            return ChannelEnum.CTRIP.key;
        }

        if (channel.toLowerCase().equals("b2b") || channel.toLowerCase().equals("b2boffline")) {
            return ChannelEnum.CTRIP_B2B.key;
        }

        if (channel.toLowerCase().equals("tc")) {
            return ChannelEnum.CTRIP_CHANNEL_A.key;
        }

        return null;
    }

    private String transferChannelState(String statusCode, String channel) {
        return ChannelOrderStateEnum.getOrderStateName(channel, statusCode);
    }

    private boolean saveOrUpdateChannelOrderStatus(String channel,String formType,Integer orderStatus,String merchantCode,Integer mOrderId) {
        boolean flag = false;
        try {
            if(StringUtil.isValidString(channel) && null!=orderStatus && StringUtil.isValidString(merchantCode) && null!=mOrderId ) {
                ChangeChannelOrderCodeRequestDTO changeChannelOrderCodeRequestDTO = new ChangeChannelOrderCodeRequestDTO();
                changeChannelOrderCodeRequestDTO.setOrderId(mOrderId);
                changeChannelOrderCodeRequestDTO.setCreator("代理通");
                changeChannelOrderCodeRequestDTO.setModifier("代理通");
//                changeChannelOrderCodeRequestDTO.setChannelOrderStatus(orderStatus+":"+ChannelOrderStatusEnum.getValueByChannelAndStatus(channel,orderStatus));

                String channelOrderStatus="";
                if ("N".equals(formType)){
                    channelOrderStatus="新单"+ ChannelOrderStatusEnum.getValueByChannelAndStatus(channel,orderStatus);
                }else if("M".equals(formType)){
                    channelOrderStatus="修改单"+ ChannelOrderStatusEnum.getValueByChannelAndStatus(channel,orderStatus);
                }else if("C".equals(formType)){
                    channelOrderStatus="取消单"+ ChannelOrderStatusEnum.getValueByChannelAndStatus(channel,orderStatus);
                }else if("D".equals(formType)){
                    channelOrderStatus="修改单"+ ChannelOrderStatusEnum.getValueByChannelAndStatus(channel,orderStatus);
                }else {
                    return true;
                }
                changeChannelOrderCodeRequestDTO.setChannelOrderStatus(channelOrderStatus);
                ResponseDTO responseDTO = OrderInterfaceInvoker.changeOrderStatus(JSON.toJSONString(changeChannelOrderCodeRequestDTO));
                if(responseDTO.getResult().equals(ResultCodeEnum.SUCCESS.code)) {
                    flag = true;
                    LOG.info("更新订单状态成功，mOrderId:"+mOrderId);
                }
            }
        }catch (Exception e) {
            LOG.error("更新渠道订单状态失败",e);
        }

        return flag;
    }

    private String transferOrderState(String formType) {
        switch (formType) {
            case "N" : return OrderStateEnum.NEW.code;
//            case "M" : return OrderStateEnum.NEW.code;
            case "M" : return OrderStateEnum.APPLYING_MODIFY.code;
            case "C" : return OrderStateEnum.APPLYING_CANCEL.code;
            case "D" : return OrderStateEnum.NEW.code;
//            case "S" : return OrderStateEnum.CANCELED.code;
//            default : return OrderStateEnum.APPLYING_CANCEL.code;
            default : return null;
        }
    }

    private String transferOrderStateToDlt(Integer mOrderStatus) {
        switch (mOrderStatus) {
            case 1 : return OrderStateEnum.NEW.code;
            case 2 : return OrderStateEnum.PROCESSING.code;
            case 3 : return OrderStateEnum.CONFIRMED.code;
            case 4 : return OrderStateEnum.CANCELED.code;
            default : return OrderStateEnum.CANCELED.code;
        }
    }

    private DltOrderDetailPO copyOrderDetail(DltOrderInfo dltOrderInfo) {
        String updateTimeStr = dltOrderInfo.getUpdateTime();
        Date updateTime = null;
        if (!StringUtils.isEmpty(updateTimeStr)) {
            dltOrderInfo.setUpdateTime(null);//dlt订单这个字段格式有问题，需要手动转化为时间类型
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                updateTime = df.parse(updateTimeStr);
            } catch (ParseException e) {
                LOG.error("时间解析失败", e);
            }
        }

        DltOrderDetailPO dltOrderDetailPO = BeanCopy.copyProperties(dltOrderInfo, DltOrderDetailPO.class);
        dltOrderDetailPO.setUpdateTime(updateTime);
        dltOrderDetailPO.setConfirmNo(dltOrderInfo.getConfirmno());
        dltOrderDetailPO.setCheckInDate(dltOrderInfo.getCheckinDate());
        dltOrderDetailPO.setCheckOutDate(dltOrderInfo.getCheckoutDate());
        dltOrderDetailPO.setCityEname(dltOrderInfo.getCityEName());
        dltOrderDetailPO.setHotelEname(dltOrderInfo.getHotelEName());
        dltOrderDetailPO.setRoomEname(dltOrderInfo.getRoomEName());
        dltOrderDetailPO.setRoomNum(dltOrderInfo.getRoomnum());
        dltOrderDetailPO.setIsHoldRoom(dltOrderInfo.getIsHoldRoom());
        dltOrderDetailPO.setCreator("system");
        dltOrderDetailPO.setModifier("system");
        dltOrderDetailPO.setCreateDate(new Date());
        dltOrderDetailPO.setModifyDate(new Date());
        if (dltOrderInfo.getReleationOrder()!=null){
            dltOrderDetailPO.setLastDltOrderId(dltOrderInfo.getReleationOrder().getLastDltOrderId());
            dltOrderDetailPO.setNextDltOrderId(dltOrderInfo.getReleationOrder().getNextDltOrderId());
            dltOrderDetailPO.setLastOrderId(dltOrderInfo.getReleationOrder().getLastOrderId());
            dltOrderDetailPO.setNextOrderId(dltOrderInfo.getReleationOrder().getNextOrderId());
        }
        return dltOrderDetailPO;
    }

    private List<DltOrderCancelRulesPO> copyOrderCancelRules(DltOrderInfo dltOrderInfo) {
        List<DltOrderCancelRulesPO> dltOrderCancelRulesPOList = new ArrayList<>();

        List<CancelRule> cancelRules = dltOrderInfo.getCancelRules();
        if (!CollectionUtils.isEmpty(cancelRules)) {
            for (CancelRule cr : cancelRules) {
                DltOrderCancelRulesPO po = BeanCopy.copyProperties(cr, DltOrderCancelRulesPO.class);
                po.setDltOrderId(dltOrderInfo.getDltOrderId());
                po.setCreator("system");
                po.setModifier("system");
                po.setCreateDate(new Date());
                po.setModifyDate(new Date());
                dltOrderCancelRulesPOList.add(po);
            }
        }

        return dltOrderCancelRulesPOList;
    }

    private List<DltOrderDayPricePO> copyOrderDayPrices(DltOrderInfo dltOrderInfo) {
        List<DltOrderDayPricePO> dltOrderDayPricePOList = new ArrayList<>();

        List<DltOrderRoomPrice> dltOrderRoomPriceList = dltOrderInfo.getRoomPriceList();
        if (!CollectionUtils.isEmpty(dltOrderRoomPriceList))
            for (DltOrderRoomPrice roomPrice : dltOrderRoomPriceList)
            { DltOrderDayPricePO po = BeanCopy.copyProperties(roomPrice, DltOrderDayPricePO.class);
                po.setDltOrderId(dltOrderInfo.getDltOrderId()); po.setCreator("system");
                po.setModifier("system");
                po.setCreateDate(new Date());
                po.setModifyDate(new Date()); dltOrderDayPricePOList.add(po); }
        return dltOrderDayPricePOList; }

        private StringBuilder compareOrderDetail(DltOrderDetailPO a, DltOrderDetailPO b) {
            StringBuilder sb = new StringBuilder();
            try { if (!compareString(a.getDltOrderId(), b.getDltOrderId())) sb.append("代理通订单号变化；");
                if (!compareString(a.getChannel(), b.getChannel())) sb.append("代理通订单渠道变化；");
                if (!compareString(a.getChildChannel(), b.getChildChannel())) sb.append("代理通订单子渠道变化；");
                if (!compareDateTime(a.getOrderDate(), b.getOrderDate())) sb.append("代理通订单时间变化；");
                if (!compareString(a.getOrderCurrency(), b.getOrderCurrency())) sb.append("代理通订单币种变化；");
                if (!compareBigDecimal(a.getOrderPrice(), b.getOrderPrice())) sb.append("代理通订单金额变化；");
                if (!compareString(a.getOrderStatus(), b.getOrderStatus())) sb.append("代理通订单状态变化；");
                if (!compareDate(a.getCheckInDate(), b.getCheckInDate())) sb.append("代理通订单入住时间变化；");
                if (!compareDate(a.getCheckOutDate(), b.getCheckOutDate())) sb.append("代理通订单入住时间变化；");
                if (!compareString(a.getHotelId(), b.getHotelId())) sb.append("代理通订单酒店变化；");
                if (!compareString(a.getRoomId(), b.getRoomId())) sb.append("代理通订单房型变化；");
                if (!compareString(a.getBedType(), b.getBedType())) sb.append("代理通订单床型变化；");
                if (!compareString(a.getCustomerName(), b.getCustomerName())) sb.append("代理通订单客人姓名变化；");
                if (!compareString(a.getCustomerDid(), b.getCustomerDid())) sb.append("代理通订单客人联系方式变化；");
                if (!compareString(a.getSpecialMemo(), b.getSpecialMemo())) sb.append("代理通订单特殊要求变化；");
            } catch (Exception e) {
                String errorMsg = "已存在的订单详细信息出错，请详细查看订单是否有变化";
                LOG.error(errorMsg, e); sb.append(errorMsg); } if (sb.length() > 0) sb.insert(0, "\n"); return sb; }

        private Boolean compareString(String a, String b) { return null == a ? null == b : null != b && a.trim().equals(b.trim()); }
        private Boolean compareDate(Date a, Date b) { return null == a ? null == b : null != b && 0 == DateUtil.compare(a, b); }
        private Boolean compareDateTime(Date a, Date b) { return null == a ? null == b : null != b && a.getTime() == b.getTime(); }
        private Boolean compareBigDecimal(BigDecimal a, BigDecimal b) { return null == a ? null == b : null != b && 0 == a.compareTo(b); }
        private Boolean compareInteger(Integer a, Integer b) { return null == a ? null == b : null != b && a.equals(b); }
        private StringBuilder compareOrderCancelRules(List<DltOrderCancelRulesPO> listA, List<DltOrderCancelRulesPO> listB) {
        StringBuilder sb = new StringBuilder();
        if (!(CollectionUtils.isEmpty(listA) ? CollectionUtils.isEmpty(listB) : null != listB && listA.size() != listB.size())) {
            sb.append("\n代理通取消政策发生变化；");
            return sb;
        }

        Iterator<DltOrderCancelRulesPO> iteratorListA =  listA.iterator();
        while (iteratorListA.hasNext()) {
            DltOrderCancelRulesPO poa = iteratorListA.next();
            Boolean isChanged = true;

            Iterator<DltOrderCancelRulesPO> iteratorListB =  listB.iterator();
            while (iteratorListB.hasNext()) {
                DltOrderCancelRulesPO pob = iteratorListB.next();
                if (poa.toString().equals(pob.toString())) {
                    isChanged = false;
                    iteratorListB.remove();
                    break;
                }
            }

            if (isChanged) {
                sb.append("\n代理通取消政策发生变化；");
                return sb;
            }
        }
        return sb;
    }

    private StringBuilder compareOrderDayPrice(List<DltOrderDayPricePO> listA, List<DltOrderDayPricePO> listB) {
        StringBuilder sb = new StringBuilder();

        if (CollectionUtils.isEmpty(listA) && CollectionUtils.isEmpty(listB)) {
            return sb;
        }

        if (CollectionUtils.isEmpty(listA) || CollectionUtils.isEmpty(listB) || listA.size() != listB.size()) {
            sb.append("\n代理通每日价格或早餐信息发生变化；");
            return sb;
        }

        Iterator<DltOrderDayPricePO> iteratorListA =  listA.iterator();
        while (iteratorListA.hasNext()) {
            DltOrderDayPricePO poa = iteratorListA.next();
            Boolean isChanged = true;

            Iterator<DltOrderDayPricePO> iteratorListB =  listB.iterator();
            while (iteratorListB.hasNext()) {
                DltOrderDayPricePO pob = iteratorListB.next();
                if (poa.toString().equals(pob.toString())) {
                    isChanged = false;
                    iteratorListB.remove();
                    break;
                }
            }

            if (isChanged) {
                sb.append("\n代理通每日价格或早餐信息发生变化；");
                return sb;
            }
        }

        return sb;
    }

    public void updateOrderHandleResult(String dltOrderId, Boolean success, String msg) {
        try {
            DltOrderPOExample example = new DltOrderPOExample();
            example.createCriteria().andDltOrderIdEqualTo(dltOrderId);

            List<DltOrderPO> dltOrderPOList = dltOrderPOMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(dltOrderPOList)) {
                LOG.error("未查询到订单记录，dltOrderId:" + dltOrderId);
                return;
            }

            DltOrderPO dop = dltOrderPOList.get(0);
            dop.setHandleDate(new Date());
            dop.setId(null);
            dop.setCreateDate(null);
            if (success) {
                dop.setIsHandled(0);//处理成功归0
                dop.setHandleResult("success");
                dop.setHandleRemark(null == msg ? "处理成功" : msg);
            } else {
                dop.setIsHandled(dop.getIsHandled() + 1);//处理失败的次数加1
                dop.setHandleResult("failure");
                dop.setHandleRemark(null == msg ? "查询订单详情接口，返回失败" : msg);
            }

            dltOrderPOMapper.updateByExampleSelective(dop, example);
        } catch (Exception e) {
            LOG.error("更新订单详情查询失败次数失败，下次将继续处理该订单", e);
        }
    }
}
