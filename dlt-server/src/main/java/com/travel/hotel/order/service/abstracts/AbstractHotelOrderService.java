package com.travel.hotel.order.service.abstracts;

import com.travel.channel.dao.ChannelAgentPOMapper;
import com.travel.common.dto.member.query.SupplyQueryDTO;
import com.travel.common.dto.order.OrderDayPriceDTO;
import com.travel.common.dto.order.OrderProductDTO;
import com.travel.common.dto.order.request.CreateOrderRequestDTO;
import com.travel.common.dto.order.response.CreateOrderResponseDTO;
import com.travel.common.dto.product.request.PriceDTO;
import com.travel.common.dto.product.request.PriceQueryDTO;
import com.travel.common.dto.product.request.QuotaDTO;
import com.travel.common.enums.ResultTypeEnum;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.DateUtil;
import com.travel.finance.service.ExchangeRateService;
import com.travel.hotel.order.dao.*;
import com.travel.hotel.product.dao.*;
import com.travel.hotel.product.entity.po.*;
import com.travel.hotel.product.service.QuotaService;
import com.travel.member.dao.AgentDao;
import com.travel.member.dao.SupplyDao;
import com.travel.member.entity.Supply;
import com.travel.member.service.AgentBusinessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.math.BigDecimal;
import java.util.*;

/**
 * 订单后台服务抽象类，会衍生出B2B订单和渠道订单子类
 * @author  2018/1/10
 */
public abstract class AbstractHotelOrderService{

    private static final Logger LOG = LoggerFactory.getLogger(AbstractHotelOrderService.class);

    @Autowired
    protected OrderPOMapper orderPOMapper;

    @Autowired
    protected AgentDao agentDao;

    @Autowired
    protected ChannelAgentPOMapper channelAgentPOMapper;

    @Autowired
    protected HotelPOMapper hotelPOMapper;

    @Autowired
    protected RoomPOMapper roomPOMapper;

    @Autowired
    protected PricePOMapper pricePOMapper;

    @Autowired
    protected PricePlanPOMapper pricePlanPOMapper;

    @Autowired
    protected OrderProductPOMapper orderProductPOMapper;

    @Autowired
    protected OrderDayPricePOMapper orderDayPricePOMapper;

    @Autowired
    protected OrderRestrictPOMapper orderRestrictPOMapper;

    @Autowired
    protected HtlRestrictPOMapper htlRestrictPOMapper;

    @Autowired
    protected QuotaPOMapper quotaPOMapper;

    @Autowired
    protected AgentBusinessService agentBusinessService;

    @Autowired
    protected QuotaService quotaService;

    @Autowired
    protected SupplyHotelRelationPOMapper supplyHotelRelationPOMapper;

    @Autowired
    protected SupplyDao supplyDao;

    @Autowired
    protected AdditionalChargePOMapper additionalChargePOMapper;

    @Autowired
    protected ThreadPoolTaskExecutor hotelOrderQuotaTaskExecutor;

    @Autowired
    protected ExchangeRateService exchangeRateService;

    @Autowired
    protected OrderOperateLogPOMapper orderOperateLogPOMapper;


    protected String requestParameterValidate(CreateOrderRequestDTO requestDTO) {
        throw new ServiceException("请调用子类覆盖的requestParameterValidate方法");
    }

    protected void checkDuplicateOrder(CreateOrderRequestDTO requestDTO, CreateOrderResponseDTO responseDTO) {
        throw new ServiceException("请调用子类覆盖的checkDuplicateOrder方法");
    }


    /**
     * 
     * @param pricePlanProductMap
     * @param hotel
     * @param isManual(true:手工单;false:非手工单) 是否手工单，当下单时手工单时不需要校验底价和产品底价一致性，渠道下单需要校验底价和产品底价一致性
     * @return
     */
    protected PriceDTO checkProductPrice(Map<Long, OrderProductDTO> pricePlanProductMap, HotelPO hotel, boolean isManual) {
        // 售价
        BigDecimal sumSaleBPrice =  BigDecimal.ZERO;
        // 直客价
        BigDecimal sumSaleCPrice =  BigDecimal.ZERO;
        // 底价
        BigDecimal sumBasePrice = BigDecimal.ZERO;
        //分销返佣
        BigDecimal totalSaleCommission = BigDecimal.ZERO;
        //供应返佣
        BigDecimal totalBaseCommission = BigDecimal.ZERO;
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
                if (null == price) {
                    LOG.error("订单每日底价为空");
                    throw new ServiceException(ResultTypeEnum.PRICE_PLAN_BASE_PRICE_EMPTY);
                }
                BigDecimal tempSaleCommission = BigDecimal.ZERO;
                BigDecimal tempBaseCommission = BigDecimal.ZERO;
                BigDecimal tempSaleCPrice = BigDecimal.ZERO;
                BigDecimal tempBasePrice = BigDecimal.ZERO;

                if (price.getBasePrice() == null) {
                    price.setBasePrice(BigDecimal.ZERO);
                }
                tempBasePrice = price.getBasePrice() == null ? BigDecimal.ZERO : price.getBasePrice();
                //验证传入的售价币种和产品售价币种是否一致
                if (null != orderDayPriceDTO.getSaleBCurrency()) {
                    if (StringUtils.isBlank(price.getSaleChannelCurrency()) || !orderDayPriceDTO.getSaleBCurrency().equals(price.getSaleChannelCurrency())) {
                        throw new ServiceException(ResultTypeEnum.SALE_B_CURRENCY_IS_DIFFERENT);
                    }
                }
                //验证传入的底价币种和产品底价币种是否一致
                if (null != orderDayPriceDTO.getBaseCurrency()) {
                    if (StringUtils.isBlank(price.getBaseCurrency()) || !orderDayPriceDTO.getBaseCurrency().equals(price.getBaseCurrency())) {
                        throw new ServiceException(ResultTypeEnum.BASE_CURRENCY_IS_DIFFERENT);
                    }
                }

                /**
                 * 手工下单可以改底价，底价不需要验证和产品底价是否一致
                 * 渠道下单需要校验底价和产品底价一致
                 */
                if (!isManual) {
                    if (null != orderDayPriceDTO.getBasePrice() && tempBasePrice.compareTo(orderDayPriceDTO.getBasePrice()) != 0) {
                        throw new ServiceException(ResultTypeEnum.BASE_PRICE_IS_DIFFERENT);
                    }
                }
                //验证同一个订单下多个产品的售价币种是否一致
                if (StringUtils.isNotBlank(tempSaleCurrency)) {
                    if (!tempSaleCurrency.equals(orderDayPriceDTO.getSaleBCurrency())) {
                        throw new ServiceException(ResultTypeEnum.PRODUCT_SALE_B_CURRENCY_IS_DIFFERENT);
                    }
                }
                //验证同一个订单下多个产品的底价币种是否一致
                if (StringUtils.isNotBlank(tempBaseCurrency)) {
                    if (!tempBaseCurrency.equals(orderDayPriceDTO.getBaseCurrency())) {
                        throw new ServiceException(ResultTypeEnum.PRODUCT_BASE_CURRENCY_IS_DIFFERENT);
                    }
                }
                tempSaleCPrice = price.getSaleCPrice() == null ? BigDecimal.ZERO : price.getSaleCPrice();
                tempSaleCurrency = orderDayPriceDTO.getSaleBCurrency();
                tempBaseCurrency = orderDayPriceDTO.getBaseCurrency();

                BigDecimal daySumSaleBPrice = orderDayPriceDTO.getSaleBPrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumSaleBPrice = sumSaleBPrice.add(daySumSaleBPrice);

                BigDecimal daySumSaleCPrice = tempSaleCPrice.multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumSaleCPrice = sumSaleCPrice.add(daySumSaleCPrice);

                if (null == orderDayPriceDTO.getBasePrice()) {
                    orderDayPriceDTO.setBasePrice(null == price.getBasePrice() ? BigDecimal.ZERO : price.getBasePrice());
                }
                BigDecimal daySumBasePrice = orderDayPriceDTO.getBasePrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumBasePrice = sumBasePrice.add(daySumBasePrice);

                BigDecimal customerRebateTax = hotel.getCustomerRebateTax() == null ? new BigDecimal(100) : hotel.getCustomerRebateTax();
                BigDecimal rebateTax = hotel.getRebateTax() == null ? new BigDecimal(100) : hotel.getRebateTax();

                BigDecimal customerRebate = new BigDecimal(1).subtract(customerRebateTax.divide(new BigDecimal(100)));
                BigDecimal rebate = new BigDecimal(1).subtract(rebateTax.divide(new BigDecimal(100)));
                tempSaleCommission = daySumSaleCPrice.subtract(daySumSaleBPrice).multiply(customerRebate);
                tempBaseCommission = daySumSaleCPrice.subtract(daySumBasePrice).multiply(rebate);
                orderDayPriceDTO.setAgentCommission(tempSaleCommission);
                orderDayPriceDTO.setSupplyCommission(tempBaseCommission);
                totalSaleCommission = totalSaleCommission.add(tempSaleCommission);
                totalBaseCommission = totalBaseCommission.add(tempBaseCommission);
            }
        }

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setSaleBCurrency(tempSaleCurrency);
        priceDTO.setSaleBPrice(sumSaleBPrice);
        priceDTO.setBaseCurrency(tempBaseCurrency);
        priceDTO.setBasePrice(sumBasePrice);
        priceDTO.setSaleCPrice(sumSaleCPrice);
        priceDTO.setAgentCommission(totalSaleCommission);
        priceDTO.setSupplyCommission(totalBaseCommission);
        return priceDTO;
    }

    protected PriceDTO checkAdditionPrice(Map<Long, OrderProductDTO> additionProductMap, HotelPO hotel) {
        //售价
        BigDecimal sumSaleBPrice =  BigDecimal.ZERO;
        //直客价
        BigDecimal sumSaleCPrice =  BigDecimal.ZERO;
        //底价
        BigDecimal sumBasePrice = BigDecimal.ZERO;
        //分销返佣
        BigDecimal totalSaleCommission = BigDecimal.ZERO;
        //供应返佣
        BigDecimal totalBaseCommission = BigDecimal.ZERO;
        //售价币种
        String tempSaleCurrency = "";
        //底价币种
        String tempBaseCurrency = "";

        if (null == additionProductMap || additionProductMap.size() == 0) {
            return null;
        }
        for (Map.Entry<Long, OrderProductDTO> entry : additionProductMap.entrySet()) {
            List<OrderDayPriceDTO> orderDayPriceDTOList = entry.getValue().getOrderDayPriceDTOList();
            if (CollectionUtils.isEmpty(orderDayPriceDTOList)) {
                throw new ServiceException(ResultTypeEnum.ORDER_DAY_PRICE_IS_EMPTY);
            }
            for (OrderDayPriceDTO orderDayPriceDTO : orderDayPriceDTOList) {
                BigDecimal tempSaleCommission = BigDecimal.ZERO;
                BigDecimal tempBaseCommission = BigDecimal.ZERO;
                BigDecimal tempSaleCPrice = BigDecimal.ZERO;

                //验证同一个订单下多个产品的售价币种是否一致
                if (StringUtils.isNotBlank(tempSaleCurrency)) {
                    if (!tempSaleCurrency.equals(orderDayPriceDTO.getSaleBCurrency())) {
                        throw new ServiceException(ResultTypeEnum.PRODUCT_SALE_B_CURRENCY_IS_DIFFERENT);
                    }
                }
                //验证同一个订单下多个产品的底价币种是否一致
                if (StringUtils.isNotBlank(tempBaseCurrency)) {
                    if (!tempBaseCurrency.equals(orderDayPriceDTO.getBaseCurrency())) {
                        throw new ServiceException(ResultTypeEnum.PRODUCT_BASE_CURRENCY_IS_DIFFERENT);
                    }
                }
                tempSaleCurrency = orderDayPriceDTO.getSaleBCurrency();
                tempBaseCurrency = orderDayPriceDTO.getBaseCurrency();

                BigDecimal daySumSaleBPrice = orderDayPriceDTO.getSaleBPrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumSaleBPrice = sumSaleBPrice.add(daySumSaleBPrice);

                BigDecimal daySumSaleCPrice = tempSaleCPrice.multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumSaleCPrice = sumSaleCPrice.add(daySumSaleCPrice);

                BigDecimal daySumBasePrice = orderDayPriceDTO.getBasePrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumBasePrice = sumBasePrice.add(daySumBasePrice);

                BigDecimal customerRebateTax = hotel.getCustomerRebateTax() == null ? new BigDecimal(100) : hotel.getCustomerRebateTax();
                BigDecimal rebateTax = hotel.getRebateTax() == null ? new BigDecimal(100) : hotel.getRebateTax();

                BigDecimal customerRebate = new BigDecimal(1).subtract(customerRebateTax.divide(new BigDecimal(100)));
                BigDecimal rebate = new BigDecimal(1).subtract(rebateTax.divide(new BigDecimal(100)));
                tempSaleCommission = daySumSaleCPrice.subtract(daySumSaleBPrice).multiply(customerRebate);
                tempBaseCommission = daySumSaleCPrice.subtract(daySumBasePrice).multiply(rebate);
                orderDayPriceDTO.setAgentCommission(tempSaleCommission);
                orderDayPriceDTO.setSupplyCommission(tempBaseCommission);
                totalSaleCommission = totalSaleCommission.add(tempSaleCommission);
                totalBaseCommission = totalBaseCommission.add(tempBaseCommission);
            }
        }
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setSaleBCurrency(tempSaleCurrency);
        priceDTO.setSaleBPrice(sumSaleBPrice);
        priceDTO.setBaseCurrency(tempBaseCurrency);
        priceDTO.setSaleCPrice(sumSaleCPrice);
        priceDTO.setBasePrice(sumBasePrice);
        priceDTO.setAgentCommission(totalSaleCommission);
        priceDTO.setSupplyCommission(totalBaseCommission);
        return priceDTO;
    }

    protected PriceDTO getSumPrice(Map<Long, OrderProductDTO> productMap, HotelPO hotel, boolean isCommission) {

        // 售价
        BigDecimal sumSaleBPrice =  BigDecimal.ZERO;
        // 直客价
        BigDecimal sumSaleCPrice =  BigDecimal.ZERO;
        // 底价
        BigDecimal sumBasePrice = BigDecimal.ZERO;

        BigDecimal totalSaleCommission = BigDecimal.ZERO;
        BigDecimal totalBaseCommission = BigDecimal.ZERO;

        String tempSaleCurrency = "";
        String tempBaseCurrency = "";
        if (null == productMap || productMap.size() == 0) {
            return null;
        }
        for (Map.Entry<Long, OrderProductDTO> entry : productMap.entrySet()) {
            List<OrderDayPriceDTO> orderDayPriceDTOList = entry.getValue().getOrderDayPriceDTOList();
            if (CollectionUtils.isEmpty(orderDayPriceDTOList)) {
                throw new ServiceException(ResultTypeEnum.ORDER_DAY_PRICE_IS_EMPTY);
            }
            for (OrderDayPriceDTO orderDayPriceDTO : orderDayPriceDTOList) {
                PriceQueryDTO priceQuery = new PriceQueryDTO();
                priceQuery.setPricePlanId(orderDayPriceDTO.getPriceplanId());
                priceQuery.setSaleDate(orderDayPriceDTO.getSaleDate());
                PricePO price = this.pricePOMapper.selectPriceByCondition(priceQuery);
                BigDecimal tempSaleCommission = BigDecimal.ZERO;
                BigDecimal tempBaseCommission = BigDecimal.ZERO;
                BigDecimal tempSaleCPrice = BigDecimal.ZERO;
                if (null != price) {
                    //验证传入的售价币种和产品售价币种是否一致
                    if (StringUtils.isBlank(price.getSaleChannelCurrency()) || !orderDayPriceDTO.getSaleBCurrency().equals(price.getSaleChannelCurrency())) {
                        throw new ServiceException(ResultTypeEnum.SALE_B_CURRENCY_IS_DIFFERENT);
                    }
                    //验证传入的底价币种和产品底价币种是否一致
                    if (StringUtils.isBlank(price.getBaseCurrency()) || !orderDayPriceDTO.getBaseCurrency().equals(price.getBaseCurrency())) {
                        throw new ServiceException(ResultTypeEnum.BASE_CURRENCY_IS_DIFFERENT);
                    }
                    //验证传入的底价和产品底价是否一致
//	                	if (tempBasePrice.compareTo(orderDayPriceDTO.getBasePrice()) != 0) {
//	                		throw new ServiceException(ResultTypeEnum.BASE_PRICE_IS_DIFFERENT);
//	                	}
                    //验证同一个订单下多个产品的售价币种是否一致
                    if (StringUtils.isNotBlank(tempSaleCurrency)) {
                        if (!tempSaleCurrency.equals(orderDayPriceDTO.getSaleBCurrency())) {
                            throw new ServiceException(ResultTypeEnum.PRODUCT_SALE_B_CURRENCY_IS_DIFFERENT);
                        }
                    }
                    //验证同一个订单下多个产品的底价币种是否一致
                    if (StringUtils.isNotBlank(tempBaseCurrency)) {
                        if (!tempBaseCurrency.equals(orderDayPriceDTO.getBaseCurrency())) {
                            throw new ServiceException(ResultTypeEnum.PRODUCT_BASE_CURRENCY_IS_DIFFERENT);
                        }
                    }
                    tempSaleCPrice = price.getSaleCPrice() == null ? BigDecimal.ZERO : price.getSaleCPrice();
                    tempSaleCurrency = orderDayPriceDTO.getSaleBCurrency();
                    tempBaseCurrency = orderDayPriceDTO.getBaseCurrency();
                }

                BigDecimal daySumSaleBPrice = orderDayPriceDTO.getSaleBPrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumSaleBPrice = sumSaleBPrice.add(daySumSaleBPrice);

                BigDecimal daySumSaleCPrice = tempSaleCPrice.multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumSaleCPrice = sumSaleCPrice.add(daySumSaleCPrice);

                BigDecimal daySumBasePrice = orderDayPriceDTO.getBasePrice().multiply(new BigDecimal(orderDayPriceDTO.getRooms()));
                sumBasePrice = sumBasePrice.add(daySumBasePrice);

                BigDecimal customerRebateTax = hotel.getCustomerRebateTax() == null ? new BigDecimal(100) : hotel.getCustomerRebateTax();
                BigDecimal rebateTax = hotel.getRebateTax() == null ? new BigDecimal(100) : hotel.getRebateTax();

                BigDecimal customerRebate = new BigDecimal(1).subtract(customerRebateTax.divide(new BigDecimal(100)));
                BigDecimal rebate = new BigDecimal(1).subtract(rebateTax.divide(new BigDecimal(100)));
                tempSaleCommission = daySumSaleCPrice.subtract(daySumSaleBPrice).multiply(customerRebate);
                tempBaseCommission = daySumSaleCPrice.subtract(daySumBasePrice).multiply(rebate);
                orderDayPriceDTO.setAgentCommission(tempSaleCommission);
                orderDayPriceDTO.setSupplyCommission(tempBaseCommission);
                totalSaleCommission = totalSaleCommission.add(tempSaleCommission);
                totalBaseCommission = totalBaseCommission.add(tempBaseCommission);
            }
        }

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setSaleBCurrency(tempSaleCurrency);
        priceDTO.setSaleBPrice(sumSaleBPrice);
        priceDTO.setBaseCurrency(tempBaseCurrency);
        priceDTO.setSaleCPrice(sumSaleCPrice);
        priceDTO.setBasePrice(sumBasePrice);
        priceDTO.setAgentCommission(totalSaleCommission);
        priceDTO.setSupplyCommission(totalBaseCommission);
        return priceDTO;
    }

    protected List<QuotaDTO> checkProductQuota(Map<Long, OrderProductDTO> productMap) {
        List<QuotaDTO> quotaDTOList = new ArrayList<>();
        for (Map.Entry<Long, OrderProductDTO> entry : productMap.entrySet()) {
            OrderProductDTO productDTO = entry.getValue();
            for (OrderDayPriceDTO orderDayPriceDTO : productDTO.getOrderDayPriceDTOList()) {
                QuotaDTO quotaDTO = new QuotaDTO();
                quotaDTO.setPricePlanId(entry.getKey());
                quotaDTO.setPricePlanName(orderDayPriceDTO.getPriceplanName());
                quotaDTO.setSaleDate(DateUtil.dateFormat(orderDayPriceDTO.getSaleDate()));
                quotaDTO.setQuotaNum(orderDayPriceDTO.getRooms());
                quotaDTOList.add(quotaDTO);
            }
        }
        return quotaDTOList;
    }

    protected PriceDTO checkSumPrice(PriceDTO productPriceDTO, PriceDTO additionPriceDTO, String saleCurrency, BigDecimal salePrice) {
        if (null == productPriceDTO || null == productPriceDTO.getSaleBCurrency() || null == productPriceDTO.getSaleBPrice()) {
            throw new ServiceException(ResultTypeEnum.CHANNEL_PRICE_INVALID);
        }

        String baseCurrency;
        BigDecimal sumBasePrice;
        String saleBCurrency;
        BigDecimal sumSaleBPrice;
        BigDecimal agentCommission;
        BigDecimal supplyCommission;
        if (null == additionPriceDTO || null == additionPriceDTO.getSaleBCurrency() || null == additionPriceDTO.getSaleBPrice()) {
            sumSaleBPrice = productPriceDTO.getSaleBPrice();
            saleBCurrency = productPriceDTO.getSaleBCurrency();
            sumBasePrice = productPriceDTO.getBasePrice();
            baseCurrency = productPriceDTO.getBaseCurrency();
            agentCommission = productPriceDTO.getAgentCommission();
            supplyCommission = productPriceDTO.getSupplyCommission();
        } else {
            if (!productPriceDTO.getSaleBCurrency().equals(additionPriceDTO.getSaleBCurrency())) {
                throw new ServiceException(ResultTypeEnum.PRODUCT_ADDITION_SALE_B_CURRENCY_IS_DIFFERENT);
            }

            if (!productPriceDTO.getBaseCurrency().equals(additionPriceDTO.getBaseCurrency())) {
                throw new ServiceException(ResultTypeEnum.PRODUCT_ADDITION_BASE_CURRENCY_IS_DIFFERENT);
            }

            sumSaleBPrice = productPriceDTO.getSaleBPrice().add(additionPriceDTO.getSaleBPrice());
            saleBCurrency = productPriceDTO.getSaleBCurrency();

            sumBasePrice = productPriceDTO.getBasePrice().add(additionPriceDTO.getBasePrice());
            baseCurrency = productPriceDTO.getBaseCurrency();

            agentCommission = productPriceDTO.getAgentCommission().add(additionPriceDTO.getAgentCommission());
            supplyCommission = productPriceDTO.getSupplyCommission().add(additionPriceDTO.getSupplyCommission());
        }

        if (!saleBCurrency.equals(saleCurrency)) {
            throw new ServiceException(ResultTypeEnum.ORDER_CURRENCY_INVALID);
        }

        if (sumSaleBPrice.compareTo(salePrice) != 0) {
            throw new ServiceException(ResultTypeEnum.ORDER_SUM_PRICE_INVALID);
        }

        PriceDTO sumPriceDTO = new PriceDTO();
        sumPriceDTO.setBasePrice(sumBasePrice);
        sumPriceDTO.setBaseCurrency(baseCurrency);
        sumPriceDTO.setSaleBPrice(sumSaleBPrice);
        sumPriceDTO.setSaleBCurrency(saleBCurrency);
        sumPriceDTO.setAgentCommission(agentCommission);
        sumPriceDTO.setSupplyCommission(supplyCommission);
        return sumPriceDTO;
    }

//    private List<ChannelAgentPO> checkChannelAgent(String agentCode, String channelCode) {
//        ChannelAgentPOExample channelAgentPOExample = new ChannelAgentPOExample();
//        channelAgentPOExample.createCriteria().andAgentCodeEqualTo(agentCode).andChannelCodeEqualTo(channelCode).andIsActiveEqualTo(1);
//        List<ChannelAgentPO> channelAgentPOList = channelAgentPOMapper.selectByExample(channelAgentPOExample);
//        if (CollectionUtils.isEmpty(channelAgentPOList)) {
//            LOG.error("创建订单, 该客户未开通该渠道，无法下单， 客户编码：" + agentCode + ", 渠道：" + channelCode);
//            throw new ServiceException(ResultTypeEnum.AGENT_CHANNEL_NOT_ENABLE, "创建订单, 挂账失败");
//        }
//        return channelAgentPOList;
//    }

    protected HotelPO checkHotel(List<OrderProductDTO> orderProductDTOList) {
        if (null == orderProductDTOList) {
            LOG.error("创建订单, 订单产品信息为空");
            throw new ServiceException(ResultTypeEnum.ORDER_PRODUCTS_IS_EMPTY);
        }

        Long hotelId = null;
        for (OrderProductDTO dto : orderProductDTOList) {
            Long hid = dto.getHotelId();
            if (hotelId != null && !hotelId.equals(hid)) {
                LOG.error("创建订单, 订单产品信息不属于同一个酒店");
                throw new ServiceException(ResultTypeEnum.ORDER_PRODUCTS_NOT_BELONG_A_SAME_HOTEL);
            }

            if (CollectionUtils.isEmpty(dto.getOrderDayPriceDTOList())) {
                LOG.error("创建订单, 订单每日价格信息为空");
                throw new ServiceException(ResultTypeEnum.ORDER_DAY_PRICE_IS_EMPTY);
            }

            hotelId = hid;
        }

        /** 酒店校验 2.酒店必须是存在的，且有效的 */
        HotelPO hotelPO = new HotelPO();
        hotelPO.setHotelId(hotelId);
        hotelPO.setIsactive(1);
        List<HotelPO> hotelPOList = hotelPOMapper.selectByCondition(hotelPO);
        if (CollectionUtils.isEmpty(hotelPOList)) {
            LOG.error("创建订单, 酒店信息不存在或无效， hotelId: " + hotelId);
            throw new ServiceException(ResultTypeEnum.ORDER_HOTEL_NOT_EXISTS_OR_INVALID);
        }
        return hotelPOList.get(0);
    }

    protected void checkRooms(List<Long> roomTypeIdList) {
        List<RoomPO> roomPOList = roomPOMapper.selectByRoomIds(roomTypeIdList);
        for (Long roomId : roomTypeIdList) {
            boolean bln = false;
            for (RoomPO po : roomPOList) {
                if (roomId.equals(po.getRoomTypeId()) && 1 == po.getIsactive()) {
                    bln = true;
                }
            }
            if (!bln) {
                LOG.error("创建订单, 房型信息不存在或无效， roomId: " + roomId);
                throw new ServiceException(ResultTypeEnum.ORDER_ROOM_NOT_EXISTS_OR_INVALID);
            }
        }
    }

    protected Map<Long, PricePlanPO> checkPricePlans(List<Long> pricePlanList) {
        Map<Long, PricePlanPO> pricePlanPOMap = new HashMap<>(16);
        /** 价格计划校验 */
        for (Long ppid : pricePlanList) {
            PricePlanPO ppPO = pricePlanPOMapper.selectByPrimaryKey(ppid);
            if (null == ppPO) {
                LOG.error("创建订单, 价格计划信息不存在或无效， ppid: " + ppid);
                throw new ServiceException(ResultTypeEnum.ORDER_PRICEPLAN_NOT_EXISTS_OR_INVALID);
            }
            pricePlanPOMap.put(ppid, ppPO);
            this.checkRooms(Arrays.asList(ppPO.getRoomTypeId()));
        }
        if (MapUtils.isEmpty(pricePlanPOMap)) {
            LOG.error("创建订单, 价格计划信息不存在或无效， ppids: " + pricePlanList);
            throw new ServiceException(ResultTypeEnum.ORDER_PRICEPLAN_NOT_EXISTS_OR_INVALID);
        }
        return pricePlanPOMap;
    }

    protected Supply checkSupply(Long hotelId) {
        SupplyHotelRelationPO po = new SupplyHotelRelationPO();
        po.setHotelId(hotelId);
        List<SupplyHotelRelationPO> supplyHotelRelationPOList = supplyHotelRelationPOMapper.selectByCondition(po);
        if (CollectionUtils.isEmpty(supplyHotelRelationPOList) || null == supplyHotelRelationPOList.get(0)
                || null == supplyHotelRelationPOList.get(0).getSupplyCode()) {
            LOG.error("创建订单, 供应商信息不存在或无效， hotelId: " + hotelId);
            throw new ServiceException(ResultTypeEnum.SUPPLIER_NOT_EXISTS_OR_INVALID);
        }

        SupplyQueryDTO supplyQueryDTO = new SupplyQueryDTO();
        supplyQueryDTO.setSupplyCode(supplyHotelRelationPOList.get(0).getSupplyCode());
        List<Supply> supplyList = supplyDao.listSupply(supplyQueryDTO);
        if (CollectionUtils.isEmpty(supplyList)) {
            LOG.error("创建订单, 供应商信息不存在或无效， supplyCode: " + supplyQueryDTO.getSupplyCode());
            throw new ServiceException(ResultTypeEnum.SUPPLIER_NOT_EXISTS_OR_INVALID);
        }
        return supplyList.get(0);
    }

    protected List<SupplyHotelRelationPO> checkHotelSupplyRelation(String supplyCode, Long hotelId) {
        SupplyHotelRelationPO po = new SupplyHotelRelationPO();
        po.setSupplyCode(supplyCode);
        po.setHotelId(hotelId);
        List<SupplyHotelRelationPO> supplyHotelRelationPOList = supplyHotelRelationPOMapper.selectByCondition(po);
        if (CollectionUtils.isEmpty(supplyHotelRelationPOList)) {
            LOG.error("创建订单, 供应商未供应该酒店， supplyCode: " + supplyCode + "， hotelId: " + hotelId);
            throw new ServiceException(ResultTypeEnum.SUPPLIER_NOT_SUPPLY_THIS_HOTEL);
        }
        return supplyHotelRelationPOList;
    }

    protected Map<Long, Map<Date, HtlRestrictPO>> checkRestricts(Map<Long, OrderProductDTO> pricePlanProductMap) {
        Map<Long, Map<Date, HtlRestrictPO>> ppEveryDayRestrictMap = new HashMap<>(16);
        for (Map.Entry<Long, OrderProductDTO> entry : pricePlanProductMap.entrySet()) {

            Long ppId = entry.getKey();
            OrderProductDTO productDTO = entry.getValue();

            // 查询得到单个产品所有日期的条款信息
            Map<String, Object> paramMap = new HashMap<>(16);
            paramMap.put("priceplanId", ppId);
            paramMap.put("checkInDate", productDTO.getCheckinDate());
            paramMap.put("checkOutDate", productDTO.getCheckoutDate());
            List<HtlRestrictPO> restrictPOList =  htlRestrictPOMapper.selectByCondition(paramMap);
            // 无任何条款设置，直接通过校验，进行下一个rp
            if (CollectionUtils.isEmpty(restrictPOList)) {
                LOG.info("该产品在指定开始结束日期内未设置任何条款信息， map: " + paramMap);
                continue;
            }

            // 入住条款验证
            HtlRestrictPO checkInDayRestrict = restrictPOList.get(0);
            int stayDays = DateUtil.getDay(productDTO.getCheckinDate(),productDTO.getCheckoutDate());
            if (null != checkInDayRestrict.getOccupancyType() && null != checkInDayRestrict.getOccupancyDays()) {
                if (1 == checkInDayRestrict.getOccupancyType() && stayDays < checkInDayRestrict.getOccupancyDays()) {
                    LOG.error("创建订单, 不满足连住条款， ppid: " + ppId);
                    throw new ServiceException(ResultTypeEnum.DISSATISFY_OCCUPANCYCLAUSE, "不满足连住条款");
                } else if (2 == checkInDayRestrict.getOccupancyType() && stayDays > checkInDayRestrict.getOccupancyDays()) {
                    LOG.error("创建订单, 不满足限住条款， ppid: " + ppId);
                    throw new ServiceException(ResultTypeEnum.DISSATISFY_OCCUPANCYCLAUSE, "不满足限住条款");
                } else if (3 == checkInDayRestrict.getOccupancyType() && (stayDays != checkInDayRestrict.getOccupancyDays())) {
                    LOG.error("创建订单, 不满足必住条款， ppid: " + ppId);
                    throw new ServiceException(ResultTypeEnum.DISSATISFY_OCCUPANCYCLAUSE, "不满足必住条款");
                }
            }

            // 将条款按日期存储，方便后面两个校验按日期取
            Map<Date, HtlRestrictPO> restrictPOMap = new HashMap<>(16);
            for (HtlRestrictPO htlRestrictPO : restrictPOList) {
                // 取消条款默认为1，一经预订不可取消
                htlRestrictPO.setCancelType(1);
                restrictPOMap.put(htlRestrictPO.getSaleDate(), htlRestrictPO);
            }

            Map<Date, HtlRestrictPO> currRestrictMap = new HashMap<>(16);
            List<OrderDayPriceDTO> orderDayPriceDTOList = productDTO.getOrderDayPriceDTOList();
            for (OrderDayPriceDTO dayPriceDTO : orderDayPriceDTOList) {

                HtlRestrictPO dayRestrictPO = restrictPOMap.get(dayPriceDTO.getSaleDate());
                if (null == dayRestrictPO) {
                    LOG.info("该产品今天没有设置任何条款， ppid:" + ppId + ", day:" + dayPriceDTO.getSaleDate());
                    continue;
                }

                // 提前预订条款验证
                Integer bookDays = dayRestrictPO.getBookDays();
                Integer bookTimes = dayRestrictPO.getBookTime();
//                Integer hours = DateUtil.getHours(new Date(), DateUtil.getDate(dayPriceDTO.getSaleDate(), 1, 0));
//                if (hours < (bookDays * 24 + bookTimes)) {
//                    LOG.error("创建订单, 不满足提前预订条款， ppid: " + ppId);
//                    throw new ServiceException(ResultTypeEnum.DISSATISFY_BOOKINGCLAUSE, "不满足提前预订条款");
//                }

                // 间数条款
                Integer bookRooms = dayRestrictPO.getBookRooms();
                if (null != bookRooms && bookRooms > dayPriceDTO.getRooms()) {
                    LOG.error("创建订单, 不满足预订间数条款， ppid: " + ppId);
                    throw new ServiceException(ResultTypeEnum.DISSATISFY_BOOKROOMSCLAUSE, "不满足预订间数条款");
                }

                currRestrictMap.put(dayPriceDTO.getSaleDate(), dayRestrictPO);
            }
            // 记录每个产品每天的条款信息，存到快照表（t_order_restrict)
            ppEveryDayRestrictMap.put(ppId, currRestrictMap);

        }
        return ppEveryDayRestrictMap;
    }


}
