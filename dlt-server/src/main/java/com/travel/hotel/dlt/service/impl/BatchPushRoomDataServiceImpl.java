package com.travel.hotel.dlt.service.impl;

import com.alibaba.fastjson.JSON;
import com.travel.channel.dao.DltMapRoomPOMapper;
import com.travel.channel.dao.ProductMapper;
import com.travel.channel.dto.request.QueryHotelProductDetailRequest;
import com.travel.channel.dto.response.QueryHotelProductDetailResponse;
import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.channel.entity.po.DltMapRoomPOExample;
import com.travel.common.enums.ChannelEnum;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.dlt.request.dto.BatchPushRoomDataRequest;
import com.travel.hotel.dlt.request.dto.CtripSellRule;
import com.travel.hotel.dlt.request.dto.RoomDataEntity;
import com.travel.hotel.dlt.request.dto.RoomInventoryModel;
import com.travel.hotel.dlt.request.dto.RoomPriceModel;
import com.travel.hotel.dlt.request.dto.RoomStatusModel;
import com.travel.hotel.dlt.request.dto.RoomType;
import com.travel.hotel.dlt.request.dto.SaleRule;
import com.travel.hotel.dlt.request.dto.SaleRuleModel;
import com.travel.hotel.dlt.request.dto.SellingRule;
import com.travel.hotel.dlt.request.dto.SetRoomSaleRuleRequest;
import com.travel.hotel.dlt.response.dto.HotelRestrictDTO;
import com.travel.hotel.dlt.service.BatchPushRoomDataService;
import com.travel.hotel.dlt.utils.DltInterfaceInvoker;
import com.travel.hotel.dlt.utils.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   2018/3/1.
 */
@Service("batchPushRoomDataService")
public class BatchPushRoomDataServiceImpl implements BatchPushRoomDataService {

    private static final Logger LOG = LoggerFactory.getLogger(BatchPushRoomDataServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DltMapRoomPOMapper dltMapRoomPOMapper;

    @Override
    public void pushRoomDataToDltByPricePlan(Long pricePlanId, Date checkInDate, Date checkOutDate) throws ServiceException {
        List<Long> pricePlanIdList = new ArrayList<>();
        pricePlanIdList.add(pricePlanId);
        this.pushRoomDataToDltByPricePlanList(pricePlanIdList, checkInDate, checkOutDate);
    }

    @Override
    public void pushRoomDataToDltByPricePlanList(List<Long> pricePlanIdList, Date checkInDate, Date checkOutDate) throws ServiceException {
        if (CollectionUtils.isEmpty(pricePlanIdList) || null == checkInDate || null == checkOutDate) {
            LOG.error("推送房型售卖详情传入的参数不合法，pricePlanIdList=" + pricePlanIdList + ", checkInDate="
                    + checkInDate + ", checkOutDate=" + checkOutDate);
            return;
        }

        DltMapRoomPOExample example = new DltMapRoomPOExample();
        example.createCriteria().andZhRpIdIn(pricePlanIdList).andDltRoomIdIsNotNull();
        List<DltMapRoomPO> dltMapRoomPOList = dltMapRoomPOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(dltMapRoomPOList)) {
            LOG.error("未查询到该价格计划和售卖房型的映射信息，pricePlanIdList=" + pricePlanIdList);
            return;
        }

        this.pushRoomDataToDltByMapRoomList(dltMapRoomPOList, checkInDate, checkOutDate,dltMapRoomPOList.get(0).getMerchantCode());
    }

    @Override
    public void pushRoomDataToDltByMapRoomList(List<DltMapRoomPO> dltMapRoomPOList, Date checkInDate, Date checkOutDate,String merchantCode) throws ServiceException {

        if (CollectionUtils.isEmpty(dltMapRoomPOList) || null == checkInDate || null == checkOutDate
                || !StringUtil.isValidString(merchantCode)) {
            LOG.error("推送房型售卖详情传入的参数不合法，pricePlanIdList=" + JSON.toJSONString(dltMapRoomPOList) + ", checkInDate="
                    + checkInDate + ", checkOutDate=" + checkOutDate + ",merchantCode=" + merchantCode);
            return;
        }

        // 将大的List，按照酒店ID划分到不同的小List中，按照每个酒店做一次推送请求，减少整体推送次数
        Map<Long, List<DltMapRoomPO>> perHotelMapRoomList = new HashMap<>();
        dltMapRoomPOList.forEach(dltMapRoomPO -> {

            if (null != perHotelMapRoomList.get(dltMapRoomPO.getZhHotelId())) {
                perHotelMapRoomList.get(dltMapRoomPO.getZhHotelId()).add(dltMapRoomPO);
            } else {
                List<DltMapRoomPO> subList = new ArrayList<>();
                subList.add(dltMapRoomPO);
                perHotelMapRoomList.put(dltMapRoomPO.getZhHotelId(), subList);
            }
        });

        for (Map.Entry<Long, List<DltMapRoomPO>> entry : perHotelMapRoomList.entrySet()) {

            Long hotelId = entry.getValue().get(0).getDltHotelId();
            List<DltMapRoomPO> subList = entry.getValue();

            //代理通限制每次推送的数据量的房型*日期不能大于300.
            //对房型*日期大于等于300的酒店数据，按单个房型推送
            //对房型*日期小于300的酒店数据，按酒店推送
            int days = DateUtil.getDay(checkInDate,checkOutDate);
            if(days * subList.size() * 8 >= 300) {
                int i = 0;
                //每60天循环一次
                while (i <= days/30) {
                    checkOutDate = (DateUtil.compare(DateUtil.getDate(checkInDate,30,0),DateUtil.getDate(DateUtil.getCurrentDate(),181,0))<0)?DateUtil.getDate(checkInDate,30,0):DateUtil.getDate(DateUtil.getCurrentDate(),180,0);
                    if(DateUtil.compare(checkInDate,checkOutDate)<0) {
                        for (DltMapRoomPO po : subList) {
                            List<RoomDataEntity> roomDataEntityList = new ArrayList<>();
                            hotelId = po.getDltHotelId();

                            // 查询指定产品指定日期的产品售卖详情9
                            QueryHotelProductDetailRequest request = new QueryHotelProductDetailRequest();
                            request.setPricePlanId(po.getZhRpId());
                            request.setCheckInDate(checkInDate);
                            request.setCheckOutDate(checkOutDate);
                            request.setChannelCode(ChannelEnum.CTRIP.key);
                            List<QueryHotelProductDetailResponse> ctripResponseList = productMapper.selectProductDetail(request);

                            if (CollectionUtils.isEmpty(ctripResponseList)) {
                                LOG.error("该产品在指定的日期段内未查询到任何售卖详情信息，request = {} " ,JSON.toJSONString(request));
                                continue;
                            }

                            request.setChannelCode(ChannelEnum.QUNAR.key);
                            List<QueryHotelProductDetailResponse> qunarResponseList = productMapper.selectProductDetail(request);

                            if (CollectionUtils.isEmpty(qunarResponseList)) {
                                LOG.error("该产品在指定的日期段内未查询到任何售卖详情信息，request = {}" ,JSON.toJSONString(request));
                                continue;
                            }

                            request.setChannelCode(ChannelEnum.QUNAR_SON.key);
                            List<QueryHotelProductDetailResponse> qunarSonResponseList = productMapper.selectProductDetail(request);

                            if (CollectionUtils.isEmpty(qunarResponseList)) {
                                LOG.error("该产品在指定的日期段内未查询到任何售卖详情信息，request = {}" ,JSON.toJSONString(request));
                                continue;
                            }

                            roomDataEntityList.addAll(this.buildRoomDataEntityList(ctripResponseList,qunarResponseList,qunarSonResponseList, po.getDltRoomId()));
                            // 构建请求对象，调用代理通接口输出数据
                            BatchPushRoomDataRequest batchPushRoomDataRequest = new BatchPushRoomDataRequest();
                            batchPushRoomDataRequest.setHotelId(hotelId);
                            batchPushRoomDataRequest.setRoomDataEntitys(roomDataEntityList);
                            DltInterfaceInvoker.invoke(batchPushRoomDataRequest,merchantCode);
                        }
                    }

                    i ++;
                    checkInDate = checkOutDate;
                }
            }else {
                List<RoomDataEntity> roomDataEntityList = new ArrayList<>();
                for (DltMapRoomPO po : subList) {
                    hotelId = po.getDltHotelId();

                    // 查询指定产品指定日期的产品售卖详情9
                    QueryHotelProductDetailRequest request = new QueryHotelProductDetailRequest();
                    request.setPricePlanId(po.getZhRpId());
                    request.setCheckInDate(checkInDate);
                    request.setCheckOutDate(checkOutDate);
                    request.setChannelCode(ChannelEnum.CTRIP.key);
                    List<QueryHotelProductDetailResponse> ctripResponseList = productMapper.selectProductDetail(request);

                    if (CollectionUtils.isEmpty(ctripResponseList)) {
                        LOG.error("该产品在指定的日期段内未查询到任何售卖详情信息，request = {}" ,JSON.toJSONString(request));
                        continue;
                    }

                    request.setChannelCode(ChannelEnum.QUNAR.key);
                    List<QueryHotelProductDetailResponse> qunarResponseList = productMapper.selectProductDetail(request);

                    if (CollectionUtils.isEmpty(qunarResponseList)) {
                        LOG.error("该产品在指定的日期段内未查询到任何售卖详情信息，request = {}" ,JSON.toJSONString(request));
                        continue;
                    }

                    request.setChannelCode(ChannelEnum.QUNAR_SON.key);
                    List<QueryHotelProductDetailResponse> qunarSonResponseList = productMapper.selectProductDetail(request);

                    if (CollectionUtils.isEmpty(qunarResponseList)) {
                        LOG.error("该产品在指定的日期段内未查询到任何售卖详情信息，request = {}" ,JSON.toJSONString(request));
                        continue;
                    }

                    roomDataEntityList.addAll(this.buildRoomDataEntityList(ctripResponseList,qunarResponseList,qunarSonResponseList, po.getDltRoomId()));
                }

                // 构建请求对象，调用代理通接口输出数据
                BatchPushRoomDataRequest request = new BatchPushRoomDataRequest();
                request.setHotelId(hotelId);
                request.setRoomDataEntitys(roomDataEntityList);
                DltInterfaceInvoker.invoke(request,merchantCode);
            }

        }

    }

    @Override
    public void setRoomSaleRule(List<DltMapRoomPO> dltMapRoomPOList, String merchantCode) throws ServiceException {
        if (CollectionUtils.isEmpty(dltMapRoomPOList)
                || !StringUtil.isValidString(merchantCode)) {
            LOG.error("推送房型售卖详情传入的参数不合法，pricePlanIdList=" + JSON.toJSONString(dltMapRoomPOList)
                    + ",merchantCode=" + merchantCode);
            return;
        }

        // 将大的List，按照酒店ID划分到不同的小List中，按照每个酒店做一次推送请求，减少整体推送次数
        Map<Long, List<DltMapRoomPO>> perHotelMapRoomList = new HashMap<>();
        dltMapRoomPOList.forEach(dltMapRoomPO -> {
            if (null != perHotelMapRoomList.get(dltMapRoomPO.getZhHotelId())) {
                perHotelMapRoomList.get(dltMapRoomPO.getZhHotelId()).add(dltMapRoomPO);
            } else {
                List<DltMapRoomPO> subList = new ArrayList<>();
                subList.add(dltMapRoomPO);
                perHotelMapRoomList.put(dltMapRoomPO.getZhHotelId(), subList);
            }
        });

        for (Map.Entry<Long, List<DltMapRoomPO>> entry : perHotelMapRoomList.entrySet()) {
            Long hotelId = entry.getValue().get(0).getDltHotelId();
            List<DltMapRoomPO> subList = entry.getValue();
            for (DltMapRoomPO po : subList) {
                hotelId = po.getDltHotelId();
                HotelRestrictDTO hotelRestrictDTO=productMapper.queryHotelRestrict(po.getZhRpId());
                SetRoomSaleRuleRequest request=new SetRoomSaleRuleRequest();
                request.setHotelId(hotelId);

                RoomType roomType=new RoomType();
                roomType.setRoomTypeId(po.getDltRoomId());
                roomType.setRoomTypeName(po.getDltRoomName());
                request.setRoomTypeList(Arrays.asList(roomType));
                request.setSetCtripDailyRule(false);

                SaleRule saleRule=new SaleRule();
                saleRule.setChannelType("qunar");
                saleRule.setIsSetLimit(true);
                saleRule.setLimitType(1);
                saleRule.setAdvanceType(0);
                saleRule.setAdvanceDay(hotelRestrictDTO.getBookDays()==null?0:hotelRestrictDTO.getBookDays());
                if (StringUtil.isValidString(hotelRestrictDTO.getBookTime())){
                    Integer bookTime=Integer.valueOf(hotelRestrictDTO.getBookTime())/100;
                    saleRule.setAdvanceTime(bookTime.toString()+":00");
                }else{
                    saleRule.setAdvanceTime("23:00");
                }
                saleRule.setIsSetCancel(true);
                if (hotelRestrictDTO.getCancelType()==1){
                    saleRule.setCanCancel(false);
                }else{
                    saleRule.setCanCancel(true);
                    saleRule.setCheckInDay(hotelRestrictDTO.getCancelDays()==null?0:hotelRestrictDTO.getCancelDays());
                    if (StringUtil.isValidString(hotelRestrictDTO.getCancelTime())){
                        Integer cancelTime=Integer.valueOf(hotelRestrictDTO.getCancelTime())/100;
                        saleRule.setCheckInTime(cancelTime.toString()+":00");
                    }else{
                        saleRule.setCheckInTime("23:00");
                    }
                }
                saleRule.setDeductType(0);
                if (hotelRestrictDTO.getOccupancyOfDays()!=null){
                    saleRule.setIsSetLast(true);
                    saleRule.setMinLastDay(hotelRestrictDTO.getOccupancyOfDays());
                    saleRule.setMaxLastDay(0);
                }else{
                    saleRule.setIsSetLast(false);
                }
                if (hotelRestrictDTO.getNumberOfBooking()!=null){
                    saleRule.setIsSetBookRoomNum(true);
                    saleRule.setMinBookRoomNum(hotelRestrictDTO.getNumberOfBooking());
                    saleRule.setMaxBookRoomNum(0);
                }else{
                    saleRule.setIsSetBookRoomNum(false);
                }
                saleRule.setIsSetSaleTime(false);
                saleRule.setIsSetAutoChange(false);
                saleRule.setIsSetLastBookTime(false);
                saleRule.setIsSetLastConfirmTime(false);
                request.setSaleRuleList(Arrays.asList(saleRule));
                DltInterfaceInvoker.invoke(request,merchantCode);
            }
        }
    }

    private List<RoomDataEntity> buildRoomDataEntityList(List<QueryHotelProductDetailResponse> ctripResponseList,
                                                         List<QueryHotelProductDetailResponse> qunarResponseList,
                                                         List<QueryHotelProductDetailResponse> qunarSonResponseList,
                                                         Long dltRoomId) {
        List<RoomDataEntity> roomDataEntityList = new ArrayList<>();

        // 每个渠道单独一个RoomDataEntityList
//        List<String> channelList = Arrays.asList("Ctrip", "Qunar");
        List<String> ctripChannelList = Arrays.asList("Ctrip", "B2B", "ChannelA");
        List<String> qunarChannelList = Arrays.asList("Qunar");
        List<String> qunarSonchannelList = Arrays.asList("QunarD", "QunarY","QunarT");

        //Ctrip,B2B,ChannelA渠道组装数据
        for (String channel : ctripChannelList) {
            RoomDataEntity previousRoomDataEntity = null;
            for (QueryHotelProductDetailResponse response : ctripResponseList) {

                RoomDataEntity entity = new RoomDataEntity();
                entity.setRoomId(dltRoomId);
                entity.setStartDate(DateUtil.dateToString(response.getSaleDate()));
                entity.setEndDate(DateUtil.dateToString(response.getSaleDate()));
                entity.setWeekDayIndex("1111111");

                // 不可卖的场景，构建默认满房的房型数据实体
                if (1 != response.getIsActive() || null == response.getSaleState()
                        || 0 == response.getSaleState() || null == response.getBaseCurrency() || null == response.getCtripPrice()
                        || null == response.getSaleChannelCurrency() || null == response.getQuotaState()
                        || null == response.getQuotaNum() || null == response.getPayMethod()) {

                    this.buildFullRoomRoomDataEntity(response, entity, channel);
                } else {

                    RoomPriceModel roomPriceModel = new RoomPriceModel();
                    RoomStatusModel roomStatusModel = new RoomStatusModel();
                    RoomInventoryModel roomInventoryModel = new RoomInventoryModel();

                    // 设置房价
                    this.setRoomPriceModel(response, roomPriceModel, channel);
                    // 设置房态房量
                    this.setRoomStatusAndInventory(response, roomStatusModel, roomInventoryModel, channel);

                    entity.setRoomPriceModel(roomPriceModel);
                    entity.setRoomStatusModel(roomStatusModel);
                    entity.setRoomInventoryModel(roomInventoryModel);
                }

                // 设置售卖规则
                SaleRuleModel saleRuleModel = new SaleRuleModel();
                this.setSaleRuleModel(response, saleRuleModel, channel);
                entity.setSaleRuleModel(saleRuleModel);

                // 将不同日期，但是房价、房态、房量、售卖规则一样的，聚合到一个roomDateEntity对象，只是更新一下endDate即可
                    /*if (null == previousRoomDataEntity || !this.compareRoomDateEntity(previousRoomDataEntity, entity)) {
                        roomDataEntityList.add(entity);
                        previousRoomDataEntity = entity;
                    } else {
                        roomDataEntityList.get(roomDataEntityList.size() - 1).setEndDate(DateUtil.dateToString(response.getSaleDate()));
                        previousRoomDataEntity.setEndDate(DateUtil.dateToString(response.getSaleDate()));
                    }*/
                roomDataEntityList.add(entity);
            }
        }
        for (String channel : qunarChannelList) {
            RoomDataEntity previousRoomDataEntity = null;
            for (QueryHotelProductDetailResponse response : qunarResponseList) {

                RoomDataEntity entity = new RoomDataEntity();
                entity.setRoomId(dltRoomId);
                entity.setStartDate(DateUtil.dateToString(response.getSaleDate()));
                entity.setEndDate(DateUtil.dateToString(response.getSaleDate()));
                entity.setWeekDayIndex("1111111");

                // 不可卖的场景，构建默认满房的房型数据实体
                if (1 != response.getIsActive() || null == response.getSaleState()
                        || 0 == response.getSaleState()
                        || null == response.getBaseCurrency()
                        || null == response.getSaleChannelCurrency() || null == response.getQuotaState()
                        || null == response.getQuotaNum() || null == response.getPayMethod()) {

                    this.buildFullRoomRoomDataEntity(response, entity, channel);
                } else {

                    RoomPriceModel roomPriceModel = new RoomPriceModel();
                    RoomStatusModel roomStatusModel = new RoomStatusModel();
                    RoomInventoryModel roomInventoryModel = new RoomInventoryModel();

                    // 设置房价
                    this.setRoomPriceModel(response, roomPriceModel, channel);
                    // 设置房态房量
                    this.setRoomStatusAndInventory(response, roomStatusModel, roomInventoryModel, channel);

                    entity.setRoomPriceModel(roomPriceModel);
                    entity.setRoomStatusModel(roomStatusModel);
                    entity.setRoomInventoryModel(roomInventoryModel);
                }

                // 设置售卖规则
                SaleRuleModel saleRuleModel = new SaleRuleModel();
                this.setSaleRuleModel(response, saleRuleModel, channel);
                entity.setSaleRuleModel(saleRuleModel);

                // 将不同日期，但是房价、房态、房量、售卖规则一样的，聚合到一个roomDateEntity对象，只是更新一下endDate即可
                /*if (null == previousRoomDataEntity || !this.compareRoomDateEntity(previousRoomDataEntity, entity)) {
                    roomDataEntityList.add(entity);
                    previousRoomDataEntity = entity;
                } else {
                    roomDataEntityList.get(roomDataEntityList.size() - 1).setEndDate(DateUtil.dateToString(response.getSaleDate()));
                    previousRoomDataEntity.setEndDate(DateUtil.dateToString(response.getSaleDate()));
                }*/
                roomDataEntityList.add(entity);
            }
        }
        for (String channel : qunarSonchannelList) {
            RoomDataEntity previousRoomDataEntity = null;
            for (QueryHotelProductDetailResponse response : qunarSonResponseList) {

                RoomDataEntity entity = new RoomDataEntity();
                entity.setRoomId(dltRoomId);
                entity.setStartDate(DateUtil.dateToString(response.getSaleDate()));
                entity.setEndDate(DateUtil.dateToString(response.getSaleDate()));
                entity.setWeekDayIndex("1111111");

                // 不可卖的场景，构建默认满房的房型数据实体
                if (1 != response.getIsActive() || null == response.getSaleState()
                        || 0 == response.getSaleState()
                        || null == response.getBaseCurrency() || null == response.getCtripPrice()
                        || null == response.getSaleChannelCurrency() || null == response.getQuotaState()
                        || null == response.getQuotaNum() || null == response.getPayMethod()) {

                    //this.buildFullRoomRoomDataEntity(response, entity, channel);
                    RoomPriceModel roomPriceModel = new RoomPriceModel();
                    roomPriceModel.setRoomPrice(BigDecimal.ZERO);
                    roomPriceModel.setTax(BigDecimal.ZERO);
                    roomPriceModel.setCurrency(null == response.getSaleChannelCurrency() ? "CNY" : response.getSaleChannelCurrency());
                    roomPriceModel.setChannel(channel);
                    //早餐转换，暂时不推早餐，直接用代理通后台关联的早餐
                    /*int breakfast = 0;
                    if(StringUtil.isValidString(response.getBreakfastNum())) {
                        if(Integer.valueOf(response.getBreakfastNum()) > 0){
                            breakfast = Integer.valueOf(response.getBreakfastNum());
                        }
                    }
                    roomPriceModel.setBreakfast(breakfast);*/
                    entity.setRoomPriceModel(roomPriceModel);
                } else {

                    RoomPriceModel roomPriceModel = new RoomPriceModel();
                    //RoomStatusModel roomStatusModel = new RoomStatusModel();
                    //RoomInventoryModel roomInventoryModel = new RoomInventoryModel();

                    // 设置房价
                    this.setRoomPriceModel(response, roomPriceModel, channel);
                    // 设置房态房量
                    //this.setRoomStatusAndInventory(response, roomStatusModel, roomInventoryModel, channel);

                    entity.setRoomPriceModel(roomPriceModel);
                    //entity.setRoomStatusModel(roomStatusModel);
                    //entity.setRoomInventoryModel(roomInventoryModel);
                }

                // 设置售卖规则
                //SaleRuleModel saleRuleModel = new SaleRuleModel();
                //this.setSaleRuleModel(response, saleRuleModel, channel);
                //entity.setSaleRuleModel(saleRuleModel);

                // 将不同日期，但是房价、房态、房量、售卖规则一样的，聚合到一个roomDateEntity对象，只是更新一下endDate即可
                /*if (null == previousRoomDataEntity || !this.compareRoomDateEntity(previousRoomDataEntity, entity)) {
                    roomDataEntityList.add(entity);
                    previousRoomDataEntity = entity;
                } else {
                    roomDataEntityList.get(roomDataEntityList.size() - 1).setEndDate(DateUtil.dateToString(response.getSaleDate()));
                    previousRoomDataEntity.setEndDate(DateUtil.dateToString(response.getSaleDate()));
                }*/
                roomDataEntityList.add(entity);
            }
        }
        return roomDataEntityList;
    }

    private Boolean compareRoomDateEntity(RoomDataEntity previous, RoomDataEntity current) {

        if (null == previous) {
            return false;
        }

        if (null == previous.getRoomId() || null == current.getRoomId()
                || !previous.getRoomId().equals(current.getRoomId())) {
            return false;
        }

        RoomPriceModel previousRoomPriceModel = previous.getRoomPriceModel();
        RoomPriceModel currentRoomPriceModel = current.getRoomPriceModel();
        if (null == previousRoomPriceModel || null == currentRoomPriceModel
                || !previousRoomPriceModel.toString().equals(currentRoomPriceModel.toString())) {
            return false;
        }

        RoomStatusModel previousRoomStatusModel = previous.getRoomStatusModel();
        RoomStatusModel currentRoomStatusModel = current.getRoomStatusModel();
        if (null == previousRoomStatusModel || null == currentRoomStatusModel
                || !previousRoomStatusModel.toString().equals(currentRoomStatusModel.toString())) {
            return false;
        }

        RoomInventoryModel previousRoomInventoryModel = previous.getRoomInventoryModel();
        RoomInventoryModel currentRoomInventoryModel = current.getRoomInventoryModel();
        if (null == previousRoomInventoryModel || null == currentRoomInventoryModel
                || !previousRoomInventoryModel.toString().equals(currentRoomInventoryModel.toString())) {
            return false;
        }

        SaleRuleModel previousSaleRuleModel = previous.getSaleRuleModel();
        SaleRuleModel currentSaleRuleModel = current.getSaleRuleModel();
        if (null == previousSaleRuleModel || null == currentSaleRuleModel
                || !previousSaleRuleModel.toString().equals(currentSaleRuleModel.toString())) {
            return false;
        }

        return true;
    }

    private RoomDataEntity buildFullRoomRoomDataEntity(QueryHotelProductDetailResponse response, RoomDataEntity entity, String channel) {
        // 设置房价
        RoomPriceModel roomPriceModel = new RoomPriceModel();
        roomPriceModel.setRoomPrice(BigDecimal.ZERO);
        roomPriceModel.setTax(BigDecimal.ZERO);
        roomPriceModel.setCurrency(null == response.getSaleChannelCurrency() ? "CNY" : response.getSaleChannelCurrency());
        roomPriceModel.setChannel(channel);
        //早餐转换，暂时不推早餐，直接用代理通后台关联的早餐
        /*int breakfast = 0;
        if(StringUtil.isValidString(response.getBreakfastNum())) {
            if(Integer.valueOf(response.getBreakfastNum()) > 0){
                breakfast = Integer.valueOf(response.getBreakfastNum());
            }
        }
        roomPriceModel.setBreakfast(breakfast);*/

        // 设置房态房量
        RoomStatusModel roomStatusModel = new RoomStatusModel();
        roomStatusModel.setSaleStatus(0);
        roomStatusModel.setChannel(channel);

        RoomInventoryModel roomInventoryModel = new RoomInventoryModel();
        roomInventoryModel.setPreservedQuantity(0);
        roomInventoryModel.setUnPreservedQuantity(0);
        roomInventoryModel.setAutoCloseRoom(2);
        roomInventoryModel.setChannel(channel);

        entity.setRoomPriceModel(roomPriceModel);
        entity.setRoomStatusModel(roomStatusModel);
        entity.setRoomInventoryModel(roomInventoryModel);
        return entity;
    }

    private void setRoomPriceModel(QueryHotelProductDetailResponse response, RoomPriceModel roomPriceModel, String channel) {
        roomPriceModel.setRoomPrice(response.getCtripPrice()==null?BigDecimal.ZERO:response.getCtripPrice());
        roomPriceModel.setTax(BigDecimal.ZERO);
        roomPriceModel.setCurrency(StringUtils.isEmpty(response.getSaleChannelCurrency()) ? "CNY" : response.getSaleChannelCurrency());
        //早餐转换，暂时不推早餐，直接用代理通后台关联的早餐
        /*int breakfast = 0;
        if(StringUtil.isValidString(response.getBreakfastNum())) {
            if(Integer.valueOf(response.getBreakfastNum()) > 0){
                breakfast = Integer.valueOf(response.getBreakfastNum())-1;
            }
        }
        roomPriceModel.setBreakfast(breakfast);*/
        roomPriceModel.setChannel(channel);
    }

    private void setRoomStatusAndInventory(QueryHotelProductDetailResponse response,
                                           RoomStatusModel roomStatusModel, RoomInventoryModel roomInventoryModel, String channel) {

        roomStatusModel.setChannel(channel);
        roomInventoryModel.setAutoCloseRoom(2);
        roomInventoryModel.setChannel(channel);

        //当天是无房态情况
        if (null == response.getQuotaState() || 0 == Integer.valueOf(response.getQuotaState())) {//房态为空或者关房
            roomStatusModel.setSaleStatus(0);
            roomInventoryModel.setPreservedQuantity(0);
            roomInventoryModel.setUnPreservedQuantity(0);
        } else if (1 == Integer.valueOf(response.getQuotaState())) {//开房
            if (null == response.getQuotaNum() || (response.getQuotaNum() <= 0 && (null == response.getOverdraft() || response.getOverdraft() <= 0))) {
                roomStatusModel.setSaleStatus(0);
                roomInventoryModel.setPreservedQuantity(0);
                roomInventoryModel.setUnPreservedQuantity(0);
            } else {
                roomStatusModel.setSaleStatus(1);
                roomInventoryModel.setPreservedQuantity(response.getQuotaNum());
                roomInventoryModel.setUnPreservedQuantity(0);
            }
        }
    }

    /**
     * 组装售卖规则
     * 代理通售卖规则设置总结如下：
     * ctrip：    可通过接口推送，且可默认到天
     * qunar:     可以在静态售卖规则中设置房型维度的取消规则默认值
     * channela:  可以在静态售卖规则中设置房型维度的取消规则默认值
     * B2B:       取消规则都是默认的不可取消
     * @param response
     * @param saleRuleModel
     * @param channel
     */
    private void setSaleRuleModel(QueryHotelProductDetailResponse response, SaleRuleModel saleRuleModel, String channel) {

        saleRuleModel.setChannel(channel);
        if (channel.toLowerCase().equals("ctrip")) {
            CtripSellRule ctripSellRule = new CtripSellRule();
            ctripSellRule.setLatestBookingTimeOfDays(null != response.getBookDays() ? response.getBookDays().shortValue() : 0);
            if (StringUtil.isValidString(response.getBookTime()) && Double.valueOf(response.getBookTime())>0){
                Double bookTime=Double.valueOf(response.getBookTime())/100;
                ctripSellRule.setLatestBookingTimeOfHours((short)bookTime.intValue());
            }else{
                ctripSellRule.setLatestBookingTimeOfHours((short)23);
            }
            ctripSellRule.setCancelType(null != response.getCancelType() ? response.getCancelType().shortValue() : (short)0);
            ctripSellRule.setLatestCancelTimeOfDays(null != response.getCancelDays() ? response.getCancelDays().shortValue() : (short)0);
            if (StringUtil.isValidString(response.getCancelTime())){
                Double cancelTime=Double.valueOf(response.getCancelDays())/100;
                ctripSellRule.setLatestCancelTimeOfHours((short)cancelTime.intValue());
            }else{
                ctripSellRule.setLatestCancelTimeOfHours((short)0);
            }
            ctripSellRule.setLatestconfirmTimeOfDays(null!=response.getLastConfirmDays()?response.getLastConfirmDays().shortValue():(short)0);
            if (StringUtil.isValidString(response.getLastConfirmHours()) && Double.valueOf(response.getLastConfirmHours())>0){
                Double lastConfirmHours=Double.valueOf(response.getLastConfirmHours())/100;
                ctripSellRule.setLatestconfirmTimeOfHours((short)lastConfirmHours.intValue());
            }else{
                ctripSellRule.setLatestconfirmTimeOfHours((short)18);
            }
            saleRuleModel.setCtripSellRule(ctripSellRule);
        } else {
            SellingRule sellingRule = new SellingRule();
            sellingRule.setLatestconfirmTimeOfDays(null!=response.getLastConfirmDays()?response.getLastConfirmDays().shortValue():(short)0);
            if (StringUtil.isValidString(response.getLastConfirmHours()) && Double.valueOf(response.getLastConfirmHours())>0){
                Double lastConfirmHours=Double.valueOf(response.getLastConfirmHours())/100;
                sellingRule.setLatestconfirmTimeOfHours((short)lastConfirmHours.intValue());
            }else{
                sellingRule.setLatestconfirmTimeOfHours((short)18);
            }
            saleRuleModel.setSellingRule(sellingRule);
        }

    }
}
