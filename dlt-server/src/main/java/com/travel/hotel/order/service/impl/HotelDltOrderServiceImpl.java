package com.travel.hotel.order.service.impl;

import com.travel.common.dto.ResultDTO;
import com.travel.common.dto.finance.request.CreditRequestDTO;
import com.travel.common.dto.order.OrderDayPriceDTO;
import com.travel.common.dto.order.OrderProductDTO;
import com.travel.common.dto.order.request.CancelOrderRequestDTO;
import com.travel.common.dto.order.request.CreateOrderRequestDTO;
import com.travel.common.dto.order.request.QueryOrderRequestDTO;
import com.travel.common.dto.order.response.CancelOrderResponseDTO;
import com.travel.common.dto.order.response.CreateOrderResponseDTO;
import com.travel.common.dto.order.response.QueryOrderStateResponseDTO;
import com.travel.common.dto.product.request.PriceDTO;
import com.travel.common.dto.product.request.PriceQueryDTO;
import com.travel.common.dto.product.request.QuotaDTO;
import com.travel.common.enums.*;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.GenericValidate;
import com.travel.common.utils.OrgCodeUtil;
import com.travel.common.validate.CreateOTAOrderValidate;
import com.travel.common.validate.CreateOrderValidate;
import com.travel.hotel.order.entity.po.*;
import com.travel.hotel.order.service.HotelOrderService;
import com.travel.hotel.order.service.abstracts.AbstractHotelOrderService;
import com.travel.hotel.order.task.HotelOrderQuotaTask;
import com.travel.hotel.product.entity.po.*;
import com.travel.member.entity.Agent;
import com.travel.member.entity.Supply;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代理通渠道订单后台服务类
 *
 * @author  2018/1/10
 */
@Service
public class HotelDltOrderServiceImpl extends AbstractHotelOrderService implements HotelOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(HotelDltOrderServiceImpl.class);

    /**
     * @param requestDTO CreateOrderRequestDTO
     * @return ResultDTO<CreateOrderResponseDTO>
     */
    @Override
    public ResultDTO<CreateOrderResponseDTO> createOrder(CreateOrderRequestDTO requestDTO) {

        CreateOrderResponseDTO response = new CreateOrderResponseDTO();

        /** 入参对象判空 */
        if (null == requestDTO) {
            LOG.error("创建订单请求对象为空");
            return new ResultDTO<>(ResultTypeEnum.INPUT_PARAM_INVALID, "创建订单请求对象为空", response);
        }

        /** 入参对象属性校验 */
        String msg = this.requestParameterValidate(requestDTO);
        if (!org.apache.commons.lang3.StringUtils.isEmpty(msg)) {
            LOG.error("创建订单入参校验失败， 原因：" + msg);
            return new ResultDTO<>(ResultTypeEnum.INPUT_PARAM_INVALID, msg);
        }

        this.checkDuplicateOrder(requestDTO, response);
        if (null != response.getOrderCode()) {
            LOG.error("改订单已存在， 请不要重复下单，customerOrderCode：" + requestDTO.getCustomerOrderCode() + ", channel: " + requestDTO.getChannelCode());
            return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
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
        if (null == agent || null == agent.getAgentCode()) {
            LOG.error("创建订单, 未查询到客户， 编码：" + agentCode);
            return new ResultDTO<>(ResultTypeEnum.AGENT_NOT_EXISTS_OR_INVALID, response);
        }

        /** 订单总价校验 */
        BigDecimal salePrice = requestDTO.getSalePrice();
        if (salePrice.compareTo(new BigDecimal(0)) <= 0) {
            LOG.error("创建订单, 订单总售价必须大于0， 总价：" + salePrice);
            return new ResultDTO<>(ResultTypeEnum.ORDER_SALE_PRICE_MUST_GT_ZERO, response);
        }

        /** 校验请求的订单产品信息 */
        List<OrderProductDTO> orderProductDTOList = requestDTO.getOrderProductDTOList();
        if (CollectionUtils.isEmpty(orderProductDTOList)) {
            LOG.error("创建订单, 订单产品信息为空");
            return new ResultDTO<>(ResultTypeEnum.ORDER_PRODUCTS_IS_EMPTY, response);
        }

        /** 酒店校验 1.多条产品记录中酒店ID必须一致 */
        HotelPO hotel;
        try {
            hotel = this.checkHotel(orderProductDTOList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_HOTEL_FAIL;
            return new ResultDTO<>(rte, response);
        }

        List<Long> pricePlanList = new ArrayList<>();
        Map<Long, OrderProductDTO> pricePlanProductMap = new HashMap<>(16);
        for (OrderProductDTO dto : orderProductDTOList) {
            if (OrderProductTypeEnum.ROOM.code.equals(dto.getProductType())) {
                pricePlanList.add(dto.getPriceplanId());
                pricePlanProductMap.put(dto.getPriceplanId(), dto);
            }
        }

        /** 价格计划校验 */
        try {
            this.checkPricePlans(pricePlanList);
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
            productPriceDTO = this.checkProductPrice(pricePlanProductMap, hotel, false);
        } catch (Exception e) {
            ResultTypeEnum rte = (e instanceof ServiceException) ? ((ServiceException) e).getResultTypeEnum() : ResultTypeEnum.CHECK_PRODUCT_SUM_PRICE_FAIL;
            LOG.error(rte.desc, e);
            return new ResultDTO<>(rte, response);
        }

        /** 生成订单号 */
        String orderCode = OrgCodeUtil.generateZhOrderCode();
        if (StringUtils.isEmpty(orderCode) && StringUtils.isEmpty(orderCode = OrgCodeUtil.generateZhOrderCode())) {
            LOG.error("生成订单号失败");
            return new ResultDTO<>(ResultTypeEnum.GENERATE_ORDER_CODE_FAIL, response);
        }

        /** 订单主表入库 */
        OrderPO orderPO = BeanCopy.copyProperties(requestDTO, OrderPO.class);
        try {
            orderPO.setOrderCode(orderCode);
            orderPO.setSupplyCode(supply.getSupplyCode());
            orderPO.setSupplyName(supply.getSupplyName());
            orderPO.setAgentCode(agent.getAgentCode());
            orderPO.setAgentName(agent.getAgentName());
            orderPO.setSettlement(agent.getSettlement());
            orderPO.setOrderState(OrderStateEnum.NEW.code);
            orderPO.setBaseCurrency(productPriceDTO.getBaseCurrency());
            orderPO.setBasePrice(productPriceDTO.getBasePrice());
            orderPO.setSaleCurrency(productPriceDTO.getSaleBCurrency());
            orderPO.setSalePrice(productPriceDTO.getSaleBPrice().compareTo(requestDTO.getSalePrice()) > 0
                    ? productPriceDTO.getSaleBPrice() : requestDTO.getSalePrice());
            orderPO.setPayable(orderPO.getSalePrice());
            orderPO.setReceivable(productPriceDTO.getSaleBPrice());
            orderPO.setCreateDate(new Date());
            orderPO.setModifier(requestDTO.getCreator());
            orderPO.setModifyDate(orderPO.getCreateDate());
            orderPO.setRemark(requestDTO.getRemark());
            orderPO.setDeptNo(requestDTO.getDeptNo());
            orderPO.setDeptName(requestDTO.getDeptName());
            orderPO.setConfirmNo(requestDTO.getConfirmNo());
            orderPO.setRoomNo(requestDTO.getRoomNo());
            orderPO.setChildChannelCode(requestDTO.getChildChannelCode());
            orderPOMapper.insert(orderPO);
        } catch (Exception e) {
            LOG.error("写入订单表失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_FAIL, "写入订单表失败", e);
        }

        /** 订单产品入库 */
        OrderProductPO orderProductPO;
        OrderProductDTO orderProductDTO = requestDTO.getOrderProductDTOList().get(0);
        try {
            orderProductPO = BeanCopy.copyProperties(orderProductDTO, OrderProductPO.class);
            orderProductPO.setHotelName(hotel.getHotelName());
            orderProductPO.setCreator(requestDTO.getCreator());
            orderProductPO.setCreateDate(new Date());
            orderProductPO.setModifier(requestDTO.getCreator());
            orderProductPO.setModifyDate(orderProductPO.getCreateDate());
            orderProductPO.setIsactive(1);
            orderProductPO.setOrderCode(orderCode);

            orderProductPOMapper.insert(orderProductPO);

            // 插入之后，把op_id都查询出来，赋值给下面的每日表
            OrderProductPOExample orderProductPOExample = new OrderProductPOExample();
            orderProductPOExample.createCriteria().andOrderCodeEqualTo(orderCode)
                    .andPriceplanIdEqualTo(orderProductDTO.getPriceplanId()).andIsactiveEqualTo(1);
            List<OrderProductPO> oppList = orderProductPOMapper.selectByExample(orderProductPOExample);

            /** 订单每日价格入库 */
            if (CollectionUtils.isEmpty(oppList)) {
                LOG.error("查询写入的订单产品失败");
                throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "查询写入的订单产品失败");
            }
            orderProductPO = oppList.get(0);
        } catch (Exception e) {
            LOG.error("写入订单产品失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "写入订单产品失败", e);
        }

        try {
            List<OrderDayPricePO> orderDayPricePOList = new ArrayList<>();
            //查询汇率，保存下单时人民币对下单币种的汇率
            List<ExchangeRatePO> exchangeList = this.exchangeRateService.queryList();
            Map<String, ExchangeRatePO> rateMap = new ConcurrentHashMap<>();
            if (CollectionUtils.isNotEmpty(exchangeList)) {
                for (ExchangeRatePO er : exchangeList) {
                    rateMap.put(er.getSourceCurrency() + "-" + er.getTargetCurrency(), er);
                }
            }

            List<OrderDayPriceDTO> orderDayPriceDTOList = orderProductDTO.getOrderDayPriceDTOList();
            for (OrderDayPriceDTO orderDayPriceDTO : orderDayPriceDTOList) {

                OrderDayPricePO orderDayPricePO = BeanCopy.copyProperties(orderDayPriceDTO, OrderDayPricePO.class);
                orderDayPricePO.setOpId(orderProductPO.getOpid());
                orderDayPricePO.setCreator(requestDTO.getCreator());
                orderDayPricePO.setCreateDate(new Date());
                orderDayPricePO.setModifier(requestDTO.getCreator());
                orderDayPricePO.setModifyDate(orderDayPricePO.getCreateDate());
                orderDayPricePO.setOrderCode(orderCode);

                PriceQueryDTO priceQuery = new PriceQueryDTO();
                priceQuery.setPricePlanId(orderDayPriceDTO.getPriceplanId());
                priceQuery.setSaleDate(orderDayPriceDTO.getSaleDate());
                PricePO price = this.pricePOMapper.selectPriceByCondition(priceQuery);

                if (null != price) {
                    orderDayPricePO.setBaseCurrency(price.getBaseCurrency());
                    orderDayPricePO.setBasePrice(price.getBasePrice());
                }
                if (StringUtils.isNotBlank(orderDayPricePO.getSaleBCurrency())) {
                    ExchangeRatePO ex = rateMap.get(CurrencyEnum.CNY.code + "-" + orderDayPricePO.getSaleBCurrency());
                    BigDecimal channelRateRate = null == ex || null == ex.getRate() ? new BigDecimal("1") : ex.getRate();
                    orderDayPricePO.setSaleChannelRate(channelRateRate);
                }
                if (StringUtils.isNotBlank(orderDayPricePO.getBaseCurrency())) {
                    ExchangeRatePO ex = rateMap.get(CurrencyEnum.CNY.code + "-" + orderDayPricePO.getBaseCurrency());
                    BigDecimal baseRate = null == ex || null == ex.getRate() ? new BigDecimal("1") : ex.getRate();
                    orderDayPricePO.setBaseRate(baseRate);
                }
                orderDayPricePOList.add(orderDayPricePO);
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
            creditRequestDTO.setOrderCreditAmount(orderPO.getSalePrice());
            creditRequestDTO.setOrderId(orderPO.getOrderId());
            creditRequestDTO.setOrderCode(orderPO.getOrderCode());
            agentBusinessService.updateAgentUsedCredit(creditRequestDTO);
        } catch (Exception e) {
            LOG.error("创建订单, 挂账失败， salePrice: " + salePrice);
        }

        /** 扣配额 */
        for (QuotaDTO qd : quotaDTOList) {
            qd.setOrderCode(orderCode);
            qd.setDistributeCode(agentCode);
            //格式化为yyyy-MM-dd格式
            qd.setSaleDate(DateUtil.dateFormat(qd.getSaleDate()));
        }
        HotelOrderQuotaTask task = new HotelOrderQuotaTask(quotaDTOList, QuotaOperateEnum.DEDUCT);
        hotelOrderQuotaTaskExecutor.execute(task);

        /** 组装返回 */
        response.setOrderCode(orderCode);
        return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
    }

    @Override
    public ResultDTO<QueryOrderStateResponseDTO> queryOrderState(QueryOrderRequestDTO queryOrderRequestDTO) {
        return null;
    }

    @Override
    public ResultDTO<CancelOrderResponseDTO> cancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO) {

        if (null == cancelOrderRequestDTO || null == cancelOrderRequestDTO.getChannelCode()
                || (null == cancelOrderRequestDTO.getCustomerOrderCode() && null == cancelOrderRequestDTO.getOrderCode())) {
            LOG.error("取消订单参数错误, cancelOrderRequestDTO=" + cancelOrderRequestDTO);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_FAIL, "取消订单参数错误, cancelOrderRequestDTO=" + cancelOrderRequestDTO);
        }

        OrderPOExample orderPOExample = new OrderPOExample();
        orderPOExample.createCriteria().andChannelCodeEqualTo(cancelOrderRequestDTO.getChannelCode())
                .andOrderCodeEqualTo(cancelOrderRequestDTO.getOrderCode())
                .andCustomerOrderCodeEqualTo(cancelOrderRequestDTO.getCustomerOrderCode());

        Integer result;
        try {

            OrderPO orderPO = new OrderPO();
            orderPO.setOrderState(OrderStateEnum.APPLYING_CANCEL.code);
            orderPO.setRemark("客人申请取消订单，请及时处理");
            result = orderPOMapper.updateByExampleSelective(orderPO, orderPOExample);
        } catch (Exception e) {
            LOG.error("申请取消操作失败", e);
            throw new ServiceException(ResultTypeEnum.APPLY_CANCEL_ORDER_FAIL, "申请取消操作失败", e);
        }

        if (result > 0) {
            CancelOrderResponseDTO response = new CancelOrderResponseDTO();
            response.setOrderCode(cancelOrderRequestDTO.getOrderCode());
            response.setOrderState(OrderStateEnum.APPLYING_CANCEL.code);
            return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
        }
        return new ResultDTO<>(ResultTypeEnum.APPLY_CANCEL_ORDER_FAIL);
    }

    @Override
    public ResultDTO<CreateOrderResponseDTO> modifyOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        /** 订单主表入库 */
        String orderCode = createOrderRequestDTO.getOrderCode();
        OrderPO existsOrderPO;
        if (StringUtils.isEmpty(orderCode)) {
            try {
                OrderPOExample orderPOExample = new OrderPOExample();
                orderPOExample.createCriteria().andCustomerOrderCodeEqualTo(createOrderRequestDTO.getCustomerOrderCode())
                        .andChannelCodeEqualTo(createOrderRequestDTO.getChannelCode());
                List<OrderPO> orderPOList = orderPOMapper.selectByExample(orderPOExample);
                if (CollectionUtils.isEmpty(orderPOList)) {
                    LOG.error("订单不存在，customerOrderCode:" + createOrderRequestDTO.getCustomerOrderCode());
                    throw new ServiceException(ResultTypeEnum.ORDER_NOT_EXISTS);
                }
                existsOrderPO = orderPOList.get(0);
                orderCode = existsOrderPO.getOrderCode();
            } catch (Exception e) {
                LOG.error("查询订单失败，customerOrderCode:" + createOrderRequestDTO.getCustomerOrderCode(), e);
                throw new ServiceException(ResultTypeEnum.QUERY_ORDER_DETAIL_FAIL, e);
            }
        }

        Integer result;
        try {
            OrderPO orderPO = BeanCopy.copyProperties(createOrderRequestDTO, OrderPO.class);
            orderPO.setModifier(createOrderRequestDTO.getCreator());
            orderPO.setModifyDate(new Date());
            // 这3个字段不更新
            orderPO.setCreateDate(null);
            orderPO.setCreator(null);
            orderPO.setRemark(null);
            // 订单状态变为申请修改中
            orderPO.setOrderState(OrderStateEnum.APPLYING_MODIFY.code);

            OrderPOExample orderPOExample = new OrderPOExample();
            orderPOExample.createCriteria().andOrderCodeEqualTo(orderCode);
            result = orderPOMapper.updateByExampleSelective(orderPO, orderPOExample);
        } catch (Exception e) {
            LOG.error("更新订单表失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_FAIL, "更新订单表失败", e);
        }

        if (result < 1) {
            LOG.error("更新订单表失败,未更新到任何订单记录，customerOrderCode:" + createOrderRequestDTO.getCustomerOrderCode());
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_FAIL, "更新订单表失败,未更新到任何订单记录");
        }

        /** 订单产品入库 */
        OrderProductDTO orderProductDTO = createOrderRequestDTO.getOrderProductDTOList().get(0);
        try {
            OrderProductPO copyProductPo = BeanCopy.copyProperties(orderProductDTO, OrderProductPO.class);

            OrderProductPOExample orderProductPOExample = new OrderProductPOExample();
            orderProductPOExample.createCriteria().andOrderCodeEqualTo(orderCode);
            result = orderProductPOMapper.updateByExampleSelective(copyProductPo, orderProductPOExample);
        } catch (Exception e) {
            LOG.error("更新订单产品失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "更新订单产品失败", e);
        }

        if (result < 1) {
            LOG.error("更新订单产品失败,未更新到任何订单产品记录，customerOrderCode:" + createOrderRequestDTO.getCustomerOrderCode());
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_PRODUCT_FAIL, "更新订单产品失败,未更新到任何订单产品记录");
        }

        try {

            OrderDayPricePOExample orderDayPricePOExample = new OrderDayPricePOExample();
            orderDayPricePOExample.createCriteria().andOrderCodeEqualTo(orderCode);
            List<OrderDayPricePO> orderDayPricePOList = orderDayPricePOMapper.selectByExample(orderDayPricePOExample);
            if (!CollectionUtils.isEmpty(orderDayPricePOList)) {
                for (OrderDayPricePO po : orderDayPricePOList) {
                    for (OrderDayPriceDTO dto : orderProductDTO.getOrderDayPriceDTOList()) {
                        if (po.getSaleDate().equals(dto.getSaleDate()) && po.getPriceplanId().equals(dto.getPriceplanId())) {
                            po.setSaleBCurrency(dto.getSaleBCurrency());
                            po.setSaleBPrice(dto.getSaleBPrice());
                            po.setRooms(dto.getRooms());
                            po.setBreakfastNum(dto.getBreakfastNum());
                            orderDayPricePOMapper.updateByPrimaryKeySelective(po);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("更新订单每日价格失败", e);
            throw new ServiceException(ResultTypeEnum.SAVE_ORDER_DAY_PRICE_FAIL, "更新订单每日价格失败", e);
        }

        // 表示有更新日志，需要些到订单操作日志表中
        if (StringUtils.isEmpty(createOrderRequestDTO.getRemark())) {
            try {
                OrderOperateLogPO orderOperateLogPO = new OrderOperateLogPO();
                orderOperateLogPO.setOrderCode(orderCode);
                orderOperateLogPO.setCreator("渠道发起订单修改");
                orderOperateLogPO.setCreateDate(new Date());
                orderOperateLogPOMapper.insert(orderOperateLogPO);
            } catch (Exception e) {
                LOG.error("写入订单操作日志失败", e);
            }
        }

        CreateOrderResponseDTO response = new CreateOrderResponseDTO();
        response.setOrderCode(orderCode);
        return new ResultDTO<>(ResultTypeEnum.SUCCESS, response);
    }

    @Override
    protected String requestParameterValidate(CreateOrderRequestDTO requestDTO) {
        return GenericValidate.validate(requestDTO, new Class[]{CreateOrderValidate.class, CreateOTAOrderValidate.class});
    }

    @Override
    protected void checkDuplicateOrder(CreateOrderRequestDTO requestDTO, CreateOrderResponseDTO responseDTO) {

        OrderPOExample ope = new OrderPOExample();
        ope.createCriteria().andCustomerOrderCodeEqualTo(requestDTO.getCustomerOrderCode())
                .andChannelCodeEqualTo(requestDTO.getChannelCode());
        List<OrderPO> orderPoList = orderPOMapper.selectByExample(ope);
        if (!CollectionUtils.isEmpty(orderPoList)) {
            responseDTO.setOrderCode(orderPoList.get(0).getOrderCode());
        }
    }

    protected Map<Long, Map<Date, HtlRestrictPO>> checkRestricts(Map<Long, OrderProductDTO> pricePlanProductMap) {
        return new HashMap<>();
    }

    /**
     * @param pricePlanProductMap
     * @param hotel
     * @param isManual(true:手工单;false:非手工单) 这里都是false
     * @return
     */
    protected PriceDTO checkProductPrice(Map<Long, OrderProductDTO> pricePlanProductMap, HotelPO hotel, boolean isManual) {
        // 售价
        BigDecimal sumSaleBPrice = BigDecimal.ZERO;
        // 底价
        BigDecimal sumBasePrice = BigDecimal.ZERO;
        //售价币种
        String tempSaleCurrency = "";
        //底价币种
        String tempBaseCurrency = "";

        if (null == pricePlanProductMap || pricePlanProductMap.size() == 0) {
            return null;
        }

        for (Map.Entry<Long, OrderProductDTO> entry : pricePlanProductMap.entrySet()) {
            List<OrderDayPriceDTO> orderDayPriceDTOList = entry.getValue().getOrderDayPriceDTOList();
            if (CollectionUtils.isEmpty(orderDayPriceDTOList)) {
                LOG.error("订单每日价格信息为空");
                throw new ServiceException(ResultTypeEnum.ORDER_DAY_PRICE_IS_EMPTY);
            }
            for (OrderDayPriceDTO orderDayPriceDTO : orderDayPriceDTOList) {
                PriceQueryDTO priceQuery = new PriceQueryDTO();
                priceQuery.setPricePlanId(orderDayPriceDTO.getPriceplanId());
                priceQuery.setSaleDate(orderDayPriceDTO.getSaleDate());
                PricePO price = this.pricePOMapper.selectPriceByCondition(priceQuery);

                if (null != price && null != price.getBasePrice()) {
                    tempBaseCurrency = StringUtils.isEmpty(price.getBaseCurrency()) ? "CNY" : orderDayPriceDTO.getBaseCurrency();

                    BigDecimal daySumBasePrice = price.getBasePrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                    sumBasePrice = sumBasePrice.add(daySumBasePrice);
                }

                tempSaleCurrency = StringUtils.isEmpty(orderDayPriceDTO.getSaleBCurrency()) ? "CNY" : orderDayPriceDTO.getSaleBCurrency();

                BigDecimal daySumSaleBPrice = orderDayPriceDTO.getSaleBPrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumSaleBPrice = sumSaleBPrice.add(daySumSaleBPrice);

            }
        }

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setSaleBCurrency(tempSaleCurrency);
        priceDTO.setSaleBPrice(sumSaleBPrice);
        priceDTO.setBaseCurrency(tempBaseCurrency);
        priceDTO.setBasePrice(sumBasePrice);
        priceDTO.setSaleCPrice(null);
        priceDTO.setAgentCommission(null);
        priceDTO.setSupplyCommission(null);
        return priceDTO;
    }
}
