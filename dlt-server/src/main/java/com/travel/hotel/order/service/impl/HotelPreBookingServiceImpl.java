package com.travel.hotel.order.service.impl;

import com.travel.channel.dao.ProductMapper;
import com.travel.channel.dto.request.QueryHotelProductDetailRequest;
import com.travel.channel.dto.response.QueryHotelProductDetailResponse;
import com.travel.common.dto.ResultDTO;
import com.travel.common.dto.order.request.PreBookingRequestDTO;
import com.travel.common.dto.order.response.PreBookingDayItemDTO;
import com.travel.common.dto.order.response.PreBookingResponseDTO;
import com.travel.common.enums.ChannelEnum;
import com.travel.common.enums.CurrencyEnum;
import com.travel.common.enums.ResultTypeEnum;
import com.travel.common.enums.RoomStateEnum;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.GenericValidate;
import com.travel.common.validate.PreBookingValidate;
import com.travel.hotel.order.service.HotelPreBookingService;
import com.travel.hotel.product.dao.HotelPOMapper;
import com.travel.hotel.product.dao.PricePlanPOMapper;
import com.travel.hotel.product.dao.RoomPOMapper;
import com.travel.hotel.product.dao.SaleStatePOMapper;
import com.travel.hotel.product.entity.po.*;
import com.travel.member.dao.AgentDao;
import com.travel.member.entity.Agent;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *   2018/2/2.
 */
@Service
public class HotelPreBookingServiceImpl implements HotelPreBookingService {

    private static final Logger LOG = LoggerFactory.getLogger(HotelPreBookingServiceImpl.class);

    @Autowired
    private PricePlanPOMapper pricePlanPOMapper;

    @Autowired
    private HotelPOMapper hotelPOMapper;

    @Autowired
    private RoomPOMapper roomPOMapper;

    @Autowired
    private SaleStatePOMapper saleStatePOMapper;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultDTO<PreBookingResponseDTO> preBooking(PreBookingRequestDTO request) {

        PreBookingResponseDTO response = new PreBookingResponseDTO();

        // 校验参数非空
        /** 入参对象属性校验 */
        String msg = GenericValidate.validate(request, new Class[]{PreBookingValidate.class});
        if (!StringUtils.isEmpty(msg)) {
            LOG.error("试预订入参校验失败， 原因：" + msg);
            return new ResultDTO<>(ResultTypeEnum.INPUT_PARAM_INVALID, msg, response);
        }

        // 如果为传入间数，默认为1间
        if (null == request.getRooms()) {
            request.setRooms(1);
        }

        // 校验入离店日期
        if (DateUtil.compare(new Date(), request.getCheckInDate()) < 0
                || DateUtil.compare(request.getCheckInDate(), request.getCheckOutDate()) >= 0) {
            LOG.error("创建订单入参校验失败， 原因：非法的入离店日期");
            return new ResultDTO<>(ResultTypeEnum.CHECKIN_CHECKOUT_DATE_INVALID, response);
        }

        // 校验渠道有效性
        if (null == ChannelEnum.getValueByKey(request.getChannelCode())) {
            LOG.error("创建订单入参校验失败， 原因：非法的渠道编码");
            return new ResultDTO<>(ResultTypeEnum.CHANNEL_CODE_INVALID, response);
        }

        // 校验币种有效性
        if (null == CurrencyEnum.getDescByCode(request.getSaleCurrency())) {
            LOG.error("创建订单入参校验失败， 原因：非法的售卖币种");
            return new ResultDTO<>(ResultTypeEnum.SALE_CURRENCY_INVALID, response);
        }

        // 校验产品有效性
        PricePlanPO pricePlanPO;
        try {
            pricePlanPO = pricePlanPOMapper.selectByPrimaryKey(request.getPricePlanId());
        } catch (Exception e) {
            LOG.error("试预订查询产品失败，priceplanid=" + request.getPricePlanId(), e);
            return new ResultDTO<>(ResultTypeEnum.QUERY_PRICE_PLAN_FAIL, response);
        }
        if (null == pricePlanPO) {
            LOG.error("试预订查询产品不存在，priceplanid=" + request.getPricePlanId());
            return new ResultDTO<>(ResultTypeEnum.PRICE_PLAN_NOT_EXISTS, response);
        }
        if (!pricePlanPO.getPayMethod().equals(request.getPayMethod())) {
            LOG.error("试预订产品支付方式不正确，priceplanid=" + request.getPricePlanId()
                    + "request paymethod=" + request.getPayMethod()
                    + ", priceplan paymethod=" + pricePlanPO.getPayMethod());
            return new ResultDTO<>(ResultTypeEnum.PAY_METHOD_ERROR, response);
        }

        // 校验房型有效性
        RoomPO roomPO;
        try {
            roomPO = roomPOMapper.selectByPrimaryKey(pricePlanPO.getRoomTypeId());
        } catch (Exception e) {
            LOG.error("试预订查询房型失败，RoomTypeId=" + pricePlanPO.getRoomTypeId(), e);
            return new ResultDTO<>(ResultTypeEnum.QUERY_ROOM_FAIL, response);
        }
        if (null == roomPO) {
            LOG.error("试预订查询房型不存在，RoomTypeId=" + pricePlanPO.getRoomTypeId());
            return new ResultDTO<>(ResultTypeEnum.ROOM_NOT_EXISTS, response);
        }

        // 校验酒店有效性
        HotelPO hotelPO;
        try {
            hotelPO = hotelPOMapper.selectByPrimaryKey(pricePlanPO.getHotelId());
        } catch (Exception e) {
            LOG.error("试预订查询酒店失败，HotelId=" + pricePlanPO.getHotelId(), e);
            return new ResultDTO<>(ResultTypeEnum.QUERY_HOTEL_FAIL, response);
        }
        if (null == hotelPO) {
            LOG.error("试预订查询酒店不存在，HotelId=" + pricePlanPO.getHotelId());
            return new ResultDTO<>(ResultTypeEnum.HOTEL_NOT_EXISTS, response);
        }

        // 校验产品在渠道的上架状态
        SaleStatePOExample saleStatePOExample = new SaleStatePOExample();
        saleStatePOExample.createCriteria().andChannelCodeEqualTo(request.getChannelCode()).andPriceplanIdEqualTo(request.getPricePlanId())
                .andSaleStateEqualTo(1);
        List<SaleStatePO> saleStatePOList;
        try {
            saleStatePOList = saleStatePOMapper.selectByExample(saleStatePOExample);
        } catch (Exception e) {
            LOG.error("试预订查询上架状态失败，priceplanid=" + request.getPricePlanId() + ", channel=" + request.getChannelCode(), e);
            return new ResultDTO<>(ResultTypeEnum.QUERY_SALE_STATE_FAIL, response);
        }
        if (CollectionUtils.isEmpty(saleStatePOList)) {
            LOG.error("试预订查询上架状态不存在，priceplanid=" + request.getPricePlanId() + ", channel=" + request.getChannelCode());
            return new ResultDTO<>(ResultTypeEnum.PRODUCT_NOT_ONSALE, response);
        }

        // 校验客户有效性
        Agent agent;
        try {
            agent = agentDao.queryAgentByCode(request.getAgentCode());
        } catch (Exception e) {
            LOG.error("试预订查询客户信息失败，AgentCode=" + request.getAgentCode(), e);
            return new ResultDTO<>(ResultTypeEnum.QUERY_AGENT_FAIL, response);
        }
        if (null == agent || agent.getIsActive() == 0) {
            LOG.error("试预订查询客户信息不存在，AgentCode=" + request.getAgentCode());
            return new ResultDTO<>(ResultTypeEnum.AGENT_NOT_EXISTS_OR_INVALID, response);
        }

        // 查询试预订产品在指定渠道指定入离日期的售卖详情
        QueryHotelProductDetailRequest queryHotelProductDetailRequest = new QueryHotelProductDetailRequest();
        queryHotelProductDetailRequest.setPricePlanId(request.getPricePlanId());
        queryHotelProductDetailRequest.setCheckInDate(request.getCheckInDate());
        queryHotelProductDetailRequest.setCheckOutDate(request.getCheckOutDate());
        List<QueryHotelProductDetailResponse> productDetailResponseList;
        try {
            productDetailResponseList = productMapper.selectProductDetail(queryHotelProductDetailRequest);
        } catch (Exception e) {
            LOG.error("试预订查询售卖详情信息失败，queryHotelProductDetailRequest=" + queryHotelProductDetailRequest, e);
            return new ResultDTO<>(ResultTypeEnum.QUERY_PRODUCT_DETAIL_FAIL, response);
        }
        if (CollectionUtils.isEmpty(productDetailResponseList)) {
            LOG.error("试预订查询售卖详情信息不存在，queryHotelProductDetailRequest=" + queryHotelProductDetailRequest);
            return new ResultDTO<>(ResultTypeEnum.PRODUCT_DETAIL_NOT_EXISTS, response);
        }

        // 组装返回结果
        BigDecimal totalSalePrice = BigDecimal.ZERO;
        BigDecimal totalBasePrice = BigDecimal.ZERO;
        BigDecimal rate = BigDecimal.ZERO;
        Integer canBooking = 1;
        Integer isImmediateConfirm = 1;

        List<PreBookingDayItemDTO> preBookingDayItemDTOList = new ArrayList<>();
        for (QueryHotelProductDetailResponse detailResponse : productDetailResponseList) {

            PreBookingDayItemDTO item = new PreBookingDayItemDTO();
            item.setSaleDate(DateUtil.dateToString(detailResponse.getSaleDate()));
            item.setSaleCurrency(detailResponse.getSaleChannelCurrency());
            item.setSalePrice(this.getSalePrice(detailResponse, request.getChannelCode()));
            item.setBaseCurrency(detailResponse.getBaseCurrency());
            item.setBasePrice(detailResponse.getBasePrice());
            item.setBreakfastNum(detailResponse.getBreakfastNum());
            item.setRoomState(detailResponse.getQuotaState());
            item.setOverDraft(detailResponse.getOverdraft());
            item.setQuotaNum(detailResponse.getQuotaNum());
            preBookingDayItemDTOList.add(item);

            // 校验底价、售价
            if (null == item.getBaseCurrency()) {
                LOG.error("试预订产品底价币种无效，item=" + item);
                return new ResultDTO<>(ResultTypeEnum.BASE_CURRENCY_INVALID, response);
            }
            if (null == item.getSaleCurrency()) {
                LOG.error("试预订产品售卖币种无效，item=" + item);
                return new ResultDTO<>(ResultTypeEnum.SALE_CURRENCY_INVALID, response);
            }
            if (null == item.getBasePrice()) {
                LOG.error("试预订产品底价无效，item=" + item);
                return new ResultDTO<>(ResultTypeEnum.PRICE_IS_EMPTY, response);
            }
            if (null == item.getSalePrice()) {
                LOG.error("试预订产品售卖无效，item=" + item);
                return new ResultDTO<>(ResultTypeEnum.SALE_PRICE_IS_EMPTY, response);
            }

            // 校验房态房量
            if (RoomStateEnum.FREESALE.key.equals(item.getRoomState())) {
                canBooking = 1;
                isImmediateConfirm = 1;
            } else if (RoomStateEnum.HAS_ROOM_WITH_OVER.key.equals(item.getRoomState())) {
                canBooking = 1;
                isImmediateConfirm = (item.getQuotaNum() >= request.getRooms()) ? 1 : 0;
            } else if (RoomStateEnum.HAS_ROOM_NOT_OVER.key.equals(item.getRoomState())) {
                canBooking = (item.getQuotaNum() >= request.getRooms()) ? 1 : 0;
                isImmediateConfirm = (item.getQuotaNum() >= request.getRooms()) ? 1 : 0;
            } else if (RoomStateEnum.ASK.key.equals(item.getRoomState())) {
                canBooking = 1;
                isImmediateConfirm = 0;
            }

        }
        response.setHotelId(pricePlanPO.getHotelId());
        response.setRoomTypeId(pricePlanPO.getRoomTypeId());
        response.setPricePlanId(pricePlanPO.getPriceplanId());
        response.setPricePlanName(pricePlanPO.getPriceplanName());
        response.setChannelCode(request.getChannelCode());
        response.setPayMethod(pricePlanPO.getPayMethod());
        response.setSaleCurrency(pricePlanPO.getSaleBCurrency());
        response.setBaseCurrency(pricePlanPO.getBaseCurrency());
        response.setTotalSalePrice(totalSalePrice);
        response.setTotalBasePrice(totalBasePrice);
        response.setRate(rate);
        response.setCanBooking(canBooking);
        response.setIsImmediateConfirm(isImmediateConfirm);
        response.setPreBookingDayItemDTOList(preBookingDayItemDTOList);

        ResultDTO<PreBookingResponseDTO> result = new ResultDTO<>(ResultTypeEnum.SUCCESS, response);

        // 记录试预订日志
        this.recordLog(request, result);

        // 返回结果
        return result;
    }

    private BigDecimal getSalePrice(QueryHotelProductDetailResponse detail, String channelCode) {
        BigDecimal price;
        switch (channelCode) {
            case "feizhu":
                price = detail.getTmPrice();
                break;
            case "ctrip":
                price = detail.getCtripPrice();
                break;
            case "jd":
                price = detail.getJdPrice();
                break;
            case "xmd":
                price = detail.getXmdPrice();
                break;
            case "tuniu":
                price = detail.getTuNiuPrice();
                break;
            case "tongcheng":
                price = detail.getTongChengPrice();
                break;
            default:
                price = BigDecimal.ZERO;
        }
        return price;
    }

    private void recordLog(PreBookingRequestDTO request, ResultDTO<PreBookingResponseDTO> result) {
        
    }
}
