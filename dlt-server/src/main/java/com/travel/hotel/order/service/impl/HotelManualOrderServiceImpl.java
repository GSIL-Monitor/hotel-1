package com.travel.hotel.order.service.impl;

import com.travel.common.dto.ResultDTO;
import com.travel.common.dto.finance.request.CreditRequestDTO;
import com.travel.common.dto.order.OrderDayPriceDTO;
import com.travel.common.dto.order.OrderProductDTO;
import com.travel.common.dto.order.request.CreateManualOrderRequestDTO;
import com.travel.common.dto.order.response.CancelManualOrderResponseDTO;
import com.travel.common.dto.order.response.CreateManualOrderResponseDTO;
import com.travel.common.dto.product.request.PriceDTO;
import com.travel.common.dto.product.request.QuotaDTO;
import com.travel.common.enums.*;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.GenericValidate;
import com.travel.common.utils.OrgCodeUtil;
import com.travel.common.validate.CreateManualOrderValidate;
import com.travel.common.validate.CreateOrderValidate;
import com.travel.hotel.order.entity.po.*;
import com.travel.hotel.order.service.HotelManualOrderService;
import com.travel.hotel.order.service.abstracts.AbstractHotelOrderService;
import com.travel.hotel.order.task.HotelOrderQuotaTask;
import com.travel.hotel.product.entity.po.ExchangeRatePO;
import com.travel.hotel.product.entity.po.HotelPO;
import com.travel.hotel.product.entity.po.HtlRestrictPO;
import com.travel.hotel.product.entity.po.PricePlanPO;
import com.travel.member.entity.Agent;
import com.travel.member.entity.Supply;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * B2B订单后台服务类
 * @author  2018/1/10
 */
@Service
public class HotelManualOrderServiceImpl extends AbstractHotelOrderService implements HotelManualOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(HotelManualOrderServiceImpl.class);

    /**
     * @see <url>https://www.processon.com/view/link/5a5dbf32e4b05a8ff3050a43</url>
     * @param requestDTO 创建订单请求对象
     * @return ResultDTO<CreateManualOrderResponseDTO> 创建订单返回对象
     */
    @Override
    public ResultDTO<CreateManualOrderResponseDTO> createManualOrder(CreateManualOrderRequestDTO requestDTO) {

        CreateManualOrderResponseDTO response = new CreateManualOrderResponseDTO();
        
        /** 入参对象判空 */
        if (null == requestDTO) {
            LOG.error("创建订单请求对象为空");
            return new ResultDTO<>(ResultTypeEnum.INPUT_PARAM_INVALID, "创建订单请求对象为空", response);
        }

        /** 入参对象属性校验 */
        String msg = this.requestParameterValidate(requestDTO);
        if (!StringUtils.isEmpty(msg)) {
            LOG.error("创建订单入参校验失败， 原因：" + msg);
            return new ResultDTO<>(ResultTypeEnum.INPUT_PARAM_INVALID, response);
        }
        
        /** 客户校验 */
        Agent agent;
        String agentCode = requestDTO.getAgentCode();
        try {
            agent = agentDao.queryAgentByCode(agentCode);
        } catch (Exception e) {
            LOG.error("创建订单, 未查询到客户， 编码：" + agentCode);
            return new ResultDTO<>(ResultTypeEnum.AGENT_NOT_EXISTS_OR_INVALID, response);
        }

        /** 订单总价校验 */
        BigDecimal salePrice = requestDTO.getSalePrice();
        String saleCurrency = requestDTO.getSaleCurrency();
        if (salePrice.compareTo(new BigDecimal(0)) <= 0) {
            LOG.error("创建订单, 订单总售价必须大于0， 总价：" + salePrice);
            return new ResultDTO<>(ResultTypeEnum.ORDER_SALE_PRICE_MUST_GT_ZERO, response);
        }

        /** 客户信用额度校验 */
        if (null == agent.getCreditLine()) {
            LOG.error("创建订单, 客户信用额度不足");
            return new ResultDTO<>(ResultTypeEnum.AGENT_LACK_OF_CREDIT_LINE, response);
        }
        if (null == agent.getUsedCreditAmount()) {
            agent.setUsedCreditAmount(new BigDecimal("0.0"));
        }
        if (agent.getCreditLine().subtract(agent.getUsedCreditAmount()).compareTo(salePrice) < 0) {
            LOG.error("创建订单, 客户信用额度不足");
            return new ResultDTO<>(ResultTypeEnum.AGENT_LACK_OF_CREDIT_LINE, response);
        }

        /** 客户渠道校验 */
//        String channelCode = requestDTO.getChannelCode();
//        try {
//            this.checkChannelAgent(agentCode, channelCode);
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_AGENT_CHANNEL_FAIL;
//            return new ResultDTO<>(rte, response);
//        }

        /** 校验请求的订单产品信息 */
        List<OrderProductDTO> orderProductDTOList =  requestDTO.getOrderProductDTOList();
        if (CollectionUtils.isEmpty(orderProductDTOList)) {
            LOG.error("创建订单, 订单产品信息为空");
            return new ResultDTO<>(ResultTypeEnum.ORDER_PRODUCTS_IS_EMPTY, response);
        }

        /** 酒店校验 1.多条产品记录中酒店ID必须一致 */
        HotelPO hotel = null;
        try {
            hotel = this.checkHotel(orderProductDTOList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_HOTEL_FAIL;
            return new ResultDTO<>(rte, response);
        }

        List<Long> roomTypeIdList = new ArrayList<>();
        List<Long> pricePlanList = new ArrayList<>();
        Map<Long, OrderProductDTO> pricePlanProductMap = new HashMap<>(16);
        Map<Long, OrderProductDTO> additionProductMap = new HashMap<>(16);
        for (OrderProductDTO dto : orderProductDTOList) {
            if (OrderProductTypeEnum.ROOM.code.equals(dto.getProductType())) {
                roomTypeIdList.add(dto.getRoomTypeId());
                pricePlanList.add(dto.getPriceplanId());
                pricePlanProductMap.put(dto.getPriceplanId(), dto);
            } else {
                additionProductMap.put(dto.getPriceplanId(), dto);
            }
        }

        /** 房型校验 */
        try {
            this.checkRooms(roomTypeIdList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_ROOM_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 价格计划校验 */
        Map<Long, PricePlanPO> pricePlanPOMap;
        try {
            pricePlanPOMap = this.checkPricePlans(pricePlanList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_PRODUCT_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 供应商校验 */
        Supply supply;
        try {
            supply = this.checkSupply(hotel.getHotelId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_SUPPLY_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 酒店供应商关系校验 */
        /**
         * 先选酒店带出供应商，并且供应商可以改。所以不校验供应商和酒店关系
         */
//        try {
//            this.checkHotelSupplyRelation(supplyCode, hotel.getHotelId());
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_HOTEL_SUPPLY_RELATION_FAIL;
//            return new ResultDTO<>(rte, response);
//        }

        /** 条款校验, 入住条款（连住、限住、必住），间数条款，提前预订条款 */
        Map<Long, Map<Date, HtlRestrictPO>> ppEveryDayRestrictMap;
        try {
            ppEveryDayRestrictMap = this.checkRestricts(pricePlanProductMap);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_CLAUSE_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 房态、配额校验 */
        List<QuotaDTO> quotaDTOList;
        try {
            quotaDTOList = this.checkProductQuota(pricePlanProductMap);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_PRODUCT_QUOTA_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 产品价格校验 */
        PriceDTO productPriceDTO;
        try {
            productPriceDTO = this.checkProductPrice(pricePlanProductMap, hotel, true);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_PRODUCT_SUM_PRICE_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 杂费价格校验 */
        PriceDTO additionPriceDTO;
        try {
            additionPriceDTO = this.checkAdditionPrice(additionProductMap, hotel);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_ADDITION_SUM_PRICE_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 验证总价 */
        PriceDTO sumPriceDTO;
        try {
            sumPriceDTO = this.checkSumPrice(productPriceDTO, additionPriceDTO, saleCurrency, salePrice);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_SUM_PRICE_FAIL;
            return new ResultDTO<>(rte, response);
        }

        /** 生成订单号 */
        String orderCode = OrgCodeUtil.generateZhOrderCode();

        /** 订单主表入库 */
        OrderPO orderPO = BeanCopy.copyProperties(requestDTO, OrderPO.class);
        String payMethod = StringUtils.isBlank(requestDTO.getPayMethod()) ? "" : requestDTO.getPayMethod();
        try {
            orderPO.setOrderCode(orderCode);
            orderPO.setSupplyCode(supply.getSupplyCode());
            orderPO.setSupplyName(supply.getSupplyName());
            orderPO.setAgentCode(agent.getAgentCode());
            orderPO.setAgentName(agent.getAgentName());
            orderPO.setSettlement(agent.getSettlement());
            orderPO.setOrderState(OrderStateEnum.NEW.code);
            orderPO.setBaseCurrency(sumPriceDTO.getBaseCurrency());
            orderPO.setBasePrice(sumPriceDTO.getBasePrice());
            orderPO.setPayable(sumPriceDTO.getBasePrice());
            orderPO.setSaleCurrency(sumPriceDTO.getSaleBCurrency());
            orderPO.setSalePrice(sumPriceDTO.getSaleBPrice());
            orderPO.setReceivable(sumPriceDTO.getSaleBPrice());
            if (payMethod.equals(PayMethodEnum.PAY.code)) {
            	orderPO.setAgentCommission(sumPriceDTO.getAgentCommission());
            	orderPO.setSupplyCommission(sumPriceDTO.getSupplyCommission());
            }
            orderPO.setCreateDate(new Date());
            orderPO.setModifier(requestDTO.getCreator());
            orderPO.setModifyDate(orderPO.getCreateDate());
            orderPO.setRemark(requestDTO.getRemark());
            orderPO.setDeptNo(requestDTO.getDeptNo());
            orderPO.setDeptName(requestDTO.getDeptName());
            orderPO.setConfirmNo(requestDTO.getConfirmNo());
            orderPO.setRoomNo(requestDTO.getRoomNo());
            orderPOMapper.insert(orderPO);
        } catch (Exception e) {
            LOG.error("写入订单表失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_FAIL, "写入订单表失败", e);
        }

        /** 订单条款入库 */
        if (MapUtils.isNotEmpty(ppEveryDayRestrictMap)) {
            try {
                List<OrderRestrictPO> orderRestrictPOList = new ArrayList<>();
                for (Map.Entry<Long, Map<Date, HtlRestrictPO>> erveryDayRestrictPoMapEntry : ppEveryDayRestrictMap.entrySet()) {
                    Map<Date, HtlRestrictPO> entryVal = erveryDayRestrictPoMapEntry.getValue();
                    for (Map.Entry<Date, HtlRestrictPO> e : entryVal.entrySet()) {
                        OrderRestrictPO orderRestrictPO = BeanCopy.copyProperties(e.getValue(), OrderRestrictPO.class);
                        orderRestrictPO.setPriceplanId(erveryDayRestrictPoMapEntry.getKey());
                        orderRestrictPO.setCreator(requestDTO.getCreator());
                        orderRestrictPO.setCreateDate(new Date());
                        orderRestrictPO.setModifier(requestDTO.getCreator());
                        orderRestrictPO.setModifyDate(orderRestrictPO.getCreateDate());
                        orderRestrictPOList.add(orderRestrictPO);
                    }
                }
                if (CollectionUtils.isNotEmpty(orderRestrictPOList)) {
                    orderRestrictPOMapper.insertBatch(orderRestrictPOList);
                }
            } catch (Exception e) {
                LOG.error("写入订单条款失败", e);
                throw new ServiceException(ResultTypeEnum.SAVE_ORDER_RESTRICT_FAIL, "写入订单条款失败", e);
            }
        }

        /** 订单产品入库 */
        Map<String, OrderProductPO> orderProductPOMap = new HashMap<>(16);
        try {
            List<OrderProductPO> orderProductPOList = new ArrayList<>();
            for (OrderProductDTO orderProductDTO : requestDTO.getOrderProductDTOList()) {
                OrderProductPO orderProductPO = BeanCopy.copyProperties(orderProductDTO, OrderProductPO.class);
                orderProductPO.setCreator(requestDTO.getCreator());
                orderProductPO.setCreateDate(new Date());
                orderProductPO.setModifier(requestDTO.getCreator());
                orderProductPO.setModifyDate(orderProductPO.getCreateDate());
                orderProductPO.setIsactive(1);
                orderProductPO.setOrderCode(orderCode);
                orderProductPOList.add(orderProductPO);
            }

            if (CollectionUtils.isNotEmpty(orderProductPOList)) {
                orderProductPOMapper.insertBatch(orderProductPOList);
            }
            // 插入之后，把op_id都查询出来，赋值给下面的每日表
            OrderProductPOExample orderProductPOExample = new OrderProductPOExample();
            orderProductPOExample.createCriteria().andOrderCodeEqualTo(orderCode).andIsactiveEqualTo(1);
            List<OrderProductPO> oppList = orderProductPOMapper.selectByExample(orderProductPOExample);

            /** 订单每日价格入库 */
            if (CollectionUtils.isEmpty(oppList)) {
                LOG.error("查询写入的订单产品失败");
                throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "查询写入的订单产品失败");
            }
            for (OrderProductPO po : oppList) {
                // 为了解决产品和附加费的priceplanid一样的问题，加上类型来区分
                orderProductPOMap.put(po.getPriceplanId() + po.getProductType(), po);
            }
        } catch (Exception e) {
            LOG.error("写入订单产品失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "写入订单产品失败", e);
        }

        try {
            List<OrderDayPricePO> orderDayPricePOList = new ArrayList<>();
            //查询汇率，保存下单时人民币对下单币种的汇率
            List<ExchangeRatePO> exchangeList = this.exchangeRateService.queryList();
            Map<String, ExchangeRatePO> rateMap = new ConcurrentHashMap<String, ExchangeRatePO>();
            if (CollectionUtils.isNotEmpty(exchangeList)) {
            	for (ExchangeRatePO er : exchangeList) {
            		rateMap.put(er.getSourceCurrency()+"-"+er.getTargetCurrency(), er);
            	}
            }
            for (OrderProductDTO orderProductDTO : requestDTO.getOrderProductDTOList()) {
                OrderProductPO opp = orderProductPOMap.get(orderProductDTO.getPriceplanId() + orderProductDTO.getProductType());
                if (null == opp) {
                    LOG.error("查询写入的订单产品失败");
                    throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "查询写入的订单产品失败");
                }
                List<OrderDayPriceDTO> orderDayPriceDTOList = orderProductDTO.getOrderDayPriceDTOList();
                for (OrderDayPriceDTO orderDayPriceDTO : orderDayPriceDTOList) {

                    OrderDayPricePO orderDayPricePO = BeanCopy.copyProperties(orderDayPriceDTO, OrderDayPricePO.class);
                    orderDayPricePO.setOpId(opp.getOpid());
                    orderDayPricePO.setCreator(requestDTO.getCreator());
                    orderDayPricePO.setCreateDate(new Date());
                    orderDayPricePO.setModifier(requestDTO.getCreator());
                    orderDayPricePO.setModifyDate(orderDayPricePO.getCreateDate());
                    orderDayPricePO.setOrderCode(orderCode);
                    if (!payMethod.equals(PayMethodEnum.PAY.code)) {
                    	orderDayPricePO.setAgentCommission(null);
                    	orderDayPricePO.setSupplyCommission(null);
                    }
                	if (StringUtils.isNotBlank(orderDayPricePO.getSaleBCurrency())) {
                		BigDecimal channelRateRate = new BigDecimal("1");
                		ExchangeRatePO ex = rateMap.get(CurrencyEnum.CNY.code+"-"+orderDayPricePO.getSaleBCurrency());
                		if (null != ex) {
                			channelRateRate = ex.getRate() == null ? new BigDecimal("1") : ex.getRate();
                		}
                		orderDayPricePO.setSaleChannelRate(channelRateRate);
                	}
                	if (StringUtils.isNotBlank(orderDayPricePO.getSaleCCurrency())) {
                		BigDecimal saleCRate = new BigDecimal("1");
                		ExchangeRatePO ex = rateMap.get(CurrencyEnum.CNY.code+"-"+orderDayPricePO.getSaleCCurrency());
                		if (null != ex) {
                			saleCRate = ex.getRate() == null ? new BigDecimal("1") : ex.getRate();
                		}
                		orderDayPricePO.setSaleCRate(saleCRate);
                	}
                	if (StringUtils.isNotBlank(orderDayPricePO.getBaseCurrency())) {
                		BigDecimal baseRate = new BigDecimal("1");
                		ExchangeRatePO ex = rateMap.get(CurrencyEnum.CNY.code+"-"+orderDayPricePO.getBaseCurrency());
                		if (null != ex) {
                			baseRate = ex.getRate() == null ? new BigDecimal("1") : ex.getRate();
                		}
                		orderDayPricePO.setBaseRate(baseRate);
                	}
                    orderDayPricePOList.add(orderDayPricePO);
                }
            }

            if (CollectionUtils.isNotEmpty(orderDayPricePOList)) {
                orderDayPricePOMapper.insertBatch(orderDayPricePOList);
            }
        } catch (Exception e) {
            LOG.error("写入订单产品每日价格失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_DAY_PRICE_FAIL, "写入订单产品每日价格失败", e);
        }

        /** 扣信用额度 */
        try {
            CreditRequestDTO creditRequestDTO = new CreditRequestDTO();
            creditRequestDTO.setAgentCode(agent.getAgentCode());
            creditRequestDTO.setCreditType(CreditTypeEnum.CREDIT.key);
            creditRequestDTO.setOrderCreditAmount(sumPriceDTO.getSaleBPrice());
            creditRequestDTO.setOrderId(orderPO.getOrderId());
            creditRequestDTO.setOrderCode(orderPO.getOrderCode());
            agentBusinessService.updateAgentUsedCredit(creditRequestDTO);
        } catch (Exception e) {
            LOG.error("创建订单, 挂账失败， salePrice: " + salePrice);
            throw new ServiceException(ResultTypeEnum.ORDER_DEDUCT_CREDIT_LINE_FAIL, "创建订单, 挂账失败", e);
        }

        /** 扣配额 */
        for (QuotaDTO qd : quotaDTOList) {
            qd.setOrderCode(orderCode);
            qd.setDistributeCode(agentCode);
            qd.setSaleDate(DateUtil.dateFormat(qd.getSaleDate()));
        }
        HotelOrderQuotaTask task = new HotelOrderQuotaTask(quotaDTOList, QuotaOperateEnum.DEDUCT);
        hotelOrderQuotaTaskExecutor.execute(task);

        /** 组装返回 */
        response.setOrderCode(orderCode);
        return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
    }

    @Override
    public ResultDTO<CancelManualOrderResponseDTO> cancelManualOrder(String orderCode) {
        if (StringUtils.isEmpty(orderCode)) {
            LOG.error("取消订单操作传入的订单号为空");
            throw new ServiceException(ResultTypeEnum.ORDER_CODE_IS_EMPTY);
        }

        OrderPO orderPO = orderPOMapper.selectByOrderCode(orderCode);
        if (null == orderPO) {
            LOG.error("取消的订单不存在");
            throw new ServiceException(ResultTypeEnum.ORDER_NOT_EXISTS);
        }

        CancelManualOrderResponseDTO response = new CancelManualOrderResponseDTO();
        if (OrderStateEnum.CANCELED.code.equals(orderPO.getOrderState())) {
            LOG.info("订单" + orderCode + "已经取消成功");
            response.setOrderCode(orderCode);
            response.setOrderState(OrderStateEnum.CANCELED.code);
            return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
        }

        /** 修改订单状态 */
        orderPO.setOrderState(OrderStateEnum.CANCELED.code);
        orderPO.setReceivable(BigDecimal.ZERO);
        orderPO.setPayable(BigDecimal.ZERO);
        orderPO.setAgentCommission(BigDecimal.ZERO);
        orderPO.setSupplyCommission(BigDecimal.ZERO);
        orderPOMapper.updateByPrimaryKeySelective(orderPO);

        OrderDayPricePOExample example = new OrderDayPricePOExample();
        example.createCriteria().andOrderCodeEqualTo(orderCode);
        List<OrderDayPricePO> orderDayPricePOList = orderDayPricePOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(orderDayPricePOList)) {
            LOG.error("该订单每日价格配额信息不存在");
            throw new ServiceException(ResultTypeEnum.ORDER_DAY_PRICE_NOT_EXISTS);
        }

        /** 退配额 */
        List<QuotaDTO> quotaDTOList = new ArrayList<>();
        for (OrderDayPricePO odpp : orderDayPricePOList) {
            QuotaDTO qd = new QuotaDTO();
            qd.setSaleDate(odpp.getSaleDate());
            qd.setPricePlanId(odpp.getPriceplanId());
            qd.setQuotaNum(odpp.getRooms());
            qd.setOrderCode(orderCode);
            qd.setDistributeCode(orderPO.getAgentCode());
            quotaDTOList.add(qd);

            /**
             * 当执行撤单时，非现付的订单判断应收/应付是否等于已收/已付，应收/应付不等于已收/已付的时候要重新对账，更改订单每日售卖的状态为可对账状态
             * 现付的订单判断订单佣金是否等于已收/已付，佣金不等于已收/已付的时候要重新对账，更改订单每日售卖的状态为可对账状态
             * 当订单的应收/应付/佣金等于已收/已付的时候，对账状态不变
             */
            String payMethod = StringUtils.isBlank(orderPO.getPayMethod()) ? "" : orderPO.getPayMethod();
            BigDecimal actualReceived = odpp.getActualReceived() == null ? BigDecimal.ZERO : odpp.getActualReceived();
            BigDecimal actualPaied = odpp.getActualPaied() == null ? BigDecimal.ZERO : odpp.getActualPaied();
            int orderBillStatus = odpp.getOrderBillStatus() == null ? 0 : odpp.getOrderBillStatus();
            int supplyBillStatus = odpp.getSupplyBillStatus() == null ? 0 : odpp.getSupplyBillStatus();
            if (PayMethodEnum.PAY.code.equals(payMethod)) {
            	//现付订单撤单时，佣金都重置为0
            	odpp.setAgentCommission(BigDecimal.ZERO);
                odpp.setSupplyCommission(BigDecimal.ZERO);
            	if (odpp.getAgentCommission().compareTo(actualReceived) != 0) {
            		if (orderBillStatus == BillStatusEnum.BILLOFF.key) {
            			odpp.setOrderBillStatus(BillStatusEnum.UNCHECKOUT.key);
            		}
            	}
            	if (odpp.getSupplyCommission().compareTo(actualPaied) != 0) {
            		if (supplyBillStatus == BillStatusEnum.BILLOFF.key) {
            			odpp.setSupplyBillStatus(BillStatusEnum.UNCHECKOUT.key);
            		}
            	}
            } else {
            	//预付订单撤单时，应收和应付都重置为0
            	odpp.setSaleBPrice(BigDecimal.ZERO);
                odpp.setBasePrice(BigDecimal.ZERO);
            	if (odpp.getSaleBPrice().compareTo(actualReceived) != 0) {
            		if (orderBillStatus == BillStatusEnum.BILLOFF.key) {
            			odpp.setOrderBillStatus(BillStatusEnum.UNCHECKOUT.key);
            		}
            	}
            	if (odpp.getBasePrice().compareTo(actualPaied) != 0) {
            		if (supplyBillStatus == BillStatusEnum.BILLOFF.key) {
            			odpp.setSupplyBillStatus(BillStatusEnum.UNCHECKOUT.key);
            		}
            	}
            }
            orderDayPricePOMapper.updateByPrimaryKeySelective(odpp);
        }
        quotaService.batchRefundQuota(quotaDTOList);

        response.setOrderCode(orderCode);
        response.setOrderState(OrderStateEnum.CANCELED.code);
        return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
    }

    @Override
    public ResultDTO<CreateManualOrderResponseDTO> modifyManualOrder(CreateManualOrderRequestDTO createManualOrderRequestDTO) {
        /** 订单主表入库 */
        String orderCode = createManualOrderRequestDTO.getOrderCode();
        OrderPO orderPO = null;
        try {
            orderPO = BeanCopy.copyProperties(createManualOrderRequestDTO, OrderPO.class);
            orderPO.setReceivable(createManualOrderRequestDTO.getSalePrice());
            orderPO.setBasePrice(createManualOrderRequestDTO.getBasePrice());
            orderPO.setModifier(createManualOrderRequestDTO.getCreator());
            orderPO.setModifyDate(new Date());
            orderPO.setCreateDate(null);
            orderPO.setCreator(null);
            orderPOMapper.updateByPrimaryKeySelective(orderPO);
        } catch (Exception e) {
            LOG.error("更新订单表失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_FAIL, "更新订单表失败", e);
        }

        /** 订单产品入库 */
        try {
            OrderProductPOExample oppe = new OrderProductPOExample();
            oppe.createCriteria().andOrderCodeEqualTo(orderCode);
            List<OrderProductPO> dbOrderProductPOList = orderProductPOMapper.selectByExample(oppe);

            List<OrderProductDTO> requestOrderProductDTOList = createManualOrderRequestDTO.getOrderProductDTOList();

            // 新增的产品信息
            List<OrderProductDTO> newOrderProductDTOList = new ArrayList<>();
            // 修改的产品信息
            List<OrderProductDTO> updateOrderProductDTOList = new ArrayList<>();
            // 删除的产品信息
            List<OrderProductPO> deleteOrderProductDTOList = new ArrayList<>();

            for (OrderProductDTO opd : requestOrderProductDTOList) {

                boolean bln = false;
                for (OrderProductPO opp : dbOrderProductPOList) {
                    if (opd.getProductType().equals(opp.getProductType())
                            && opd.getPriceplanId().equals(opp.getPriceplanId()) && null != opd.getOpid()) {
                        updateOrderProductDTOList.add(opd);
                        bln = true;
                        break;
                    }
                }
                if (!bln) {
                    newOrderProductDTOList.add(opd);
                }
            }

            for (OrderProductPO opp : dbOrderProductPOList) {

                boolean bln = false;
                for (OrderProductDTO opd : requestOrderProductDTOList) {
                    if (opd.getProductType().equals(opp.getProductType())
                            && opd.getPriceplanId().equals(opp.getPriceplanId()) && null != opd.getOpid()) {
                        bln = true;
                        break;
                    }
                }
                if (!bln) {
                    deleteOrderProductDTOList.add(opp);
                }
            }

            // 针对删除的产品信息，直接对产品进行删除操作，对产品下的每日价格进行删除操作
            for (OrderProductPO po : deleteOrderProductDTOList) {
            	OrderDayPricePOExample example = new OrderDayPricePOExample();
            	example.createCriteria().andOpIdEqualTo(po.getOpid()).andOrderCodeEqualTo(orderCode);
            	orderDayPricePOMapper.deleteByExample(example);
                orderProductPOMapper.deleteByPrimaryKey(po.getOpid());
            }

            // 针对新增的产品信息，直接对产品进行插入操作，对产品下的每日价格进行插入操作，注意opid
            for (OrderProductDTO orderProductDTO : newOrderProductDTOList) {
                OrderProductPO orderProductPO = BeanCopy.copyProperties(orderProductDTO, OrderProductPO.class);
                orderProductPO.setCreator(createManualOrderRequestDTO.getCreator());
                orderProductPO.setCreateDate(new Date());
                orderProductPO.setModifier(createManualOrderRequestDTO.getCreator());
                orderProductPO.setModifyDate(orderProductPO.getCreateDate());
                orderProductPO.setIsactive(1);
                orderProductPO.setOrderCode(orderCode);
                orderProductPOMapper.insert(orderProductPO);

                List<OrderDayPricePO> orderDayPricePOList = new ArrayList<>();
                for (OrderDayPriceDTO orderDayPriceDTO : orderProductDTO.getOrderDayPriceDTOList()) {
                    OrderDayPricePO orderDayPricePO = BeanCopy.copyProperties(orderDayPriceDTO, OrderDayPricePO.class);
                    orderDayPricePO.setOpId(orderProductPO.getOpid());
                    orderDayPricePO.setCreator(orderProductPO.getCreator());
                    orderDayPricePO.setCreateDate(new Date());
                    orderDayPricePO.setModifier(orderProductPO.getCreator());
                    orderDayPricePO.setModifyDate(orderDayPricePO.getCreateDate());
                    orderDayPricePO.setOrderCode(orderCode);
                    orderDayPricePOList.add(orderDayPricePO);
                }
                orderDayPricePOMapper.insertBatch(orderDayPricePOList);
            }

            // 针对更新的产品信息，直接对产品进行更新操作，对产品下的每日价格信息，需要区分新增、修改、删除，并分别对待
            for (OrderProductDTO opd : updateOrderProductDTOList) {

                OrderDayPricePOExample example = new OrderDayPricePOExample();
                example.createCriteria().andOrderCodeEqualTo(orderCode).andPriceplanIdEqualTo(opd.getPriceplanId()).andOpIdEqualTo(opd.getOpid()).andSaleDateBetween(opd.getCheckinDate(), opd.getCheckoutDate());
                List<OrderDayPricePO> orderDayPricePOList = orderDayPricePOMapper.selectByExample(example);
                List<OrderDayPriceDTO> orderDayPriceDTOList = opd.getOrderDayPriceDTOList();

                for (OrderDayPriceDTO odpDTO : orderDayPriceDTOList) {

                    boolean bln = false;
                    for (OrderDayPricePO odpPO : orderDayPricePOList) {

                        if (odpDTO.getOrderCode().equals(odpPO.getOrderCode())
                                && odpDTO.getPriceplanId().equals(odpPO.getPriceplanId())
                                && DateUtil.dateToString(odpDTO.getSaleDate()).equals(DateUtil.dateToString(odpPO.getSaleDate()))) {

                            OrderDayPricePO orderDayPricePO = BeanCopy.copyProperties(odpDTO, OrderDayPricePO.class);
                            orderDayPricePO.setOpId(odpPO.getOpId());
                            orderDayPricePO.setModifier(odpDTO.getModifier());
                            orderDayPricePO.setModifyDate(new Date());
                            orderDayPricePOMapper.updateByPrimaryKey(orderDayPricePO);
                            bln = true;

                            break;
                        }
                    }
                    if (!bln) {
                        OrderDayPricePO orderDayPricePO = BeanCopy.copyProperties(odpDTO, OrderDayPricePO.class);
                        orderDayPricePO.setOpId(opd.getOpid());
                        orderDayPricePO.setCreator(opd.getCreator());
                        orderDayPricePO.setCreateDate(new Date());
                        orderDayPricePO.setModifier(opd.getCreator());
                        orderDayPricePO.setModifyDate(new Date());
                        orderDayPricePO.setOrderCode(orderCode);
                        orderDayPricePOMapper.insert(orderDayPricePO);
                    }
                }

                for (OrderDayPricePO odpPO : orderDayPricePOList) {

                    boolean bln = false;
                    for (OrderDayPriceDTO odpDTO : orderDayPriceDTOList) {
                        if (odpPO.getOrderCode().equals(odpDTO.getOrderCode())
                                && odpPO.getPriceplanId().equals(odpDTO.getPriceplanId())
                                && DateUtil.dateToString(odpPO.getSaleDate()).equals(DateUtil.dateToString(odpDTO.getSaleDate()))) {
                            bln = true;
                            break;
                        }
                    }
                    if (!bln) {
                        orderDayPricePOMapper.deleteByPrimaryKey(odpPO.getDayPriceId());
                    }
                }

            }
        } catch (Exception e) {
            LOG.error("更新订单产品失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "更新订单产品失败", e);
        }

        CreateManualOrderResponseDTO response = new CreateManualOrderResponseDTO();
        response.setOrderCode(createManualOrderRequestDTO.getOrderCode());
        return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
    }

    private String requestParameterValidate(CreateManualOrderRequestDTO requestDTO) {
        return GenericValidate.validate(requestDTO, new Class[]{CreateOrderValidate.class, CreateManualOrderValidate.class});
    }
}
