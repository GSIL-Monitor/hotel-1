package com.travel.hotel.product.service.impl;

import com.travel.common.constant.InitData;
import com.travel.common.dto.DateInfoDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.request.DayRoomControlDTO;
import com.travel.common.dto.product.request.RoomControlRequestDTO;
import com.travel.common.dto.product.response.RoomControlResponseDTO;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.product.entity.PricePlanDTO;
import com.travel.hotel.product.entity.po.HotelPO;
import com.travel.hotel.product.entity.po.PricePO;
import com.travel.hotel.product.entity.po.PricePlanPO;
import com.travel.hotel.product.entity.po.QuotaAccountPO;
import com.travel.hotel.product.entity.po.QuotaPO;
import com.travel.hotel.product.entity.po.ReserveQuotaPO;
import com.travel.hotel.product.service.HotelService;
import com.travel.hotel.product.service.PricePlanService;
import com.travel.hotel.product.service.PriceService;
import com.travel.hotel.product.service.QuotaAccountService;
import com.travel.hotel.product.service.QuotaService;
import com.travel.hotel.product.service.RoomControlService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/26.
 */
@Service("roomControlService")
public class RoomControlServiceImpl implements RoomControlService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PricePlanService pricePlanService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private QuotaService quotaService;

    @Autowired
    private QuotaAccountService quotaAccountService;

    @Autowired
    private HotelService hotelService;

    @Override
    public PaginationDTO<RoomControlResponseDTO> queryRoomControlList(RoomControlRequestDTO requestDTO) {

        //1-根据条件查询有哪些价格计划
        PricePlanPO pricePlanQueryPO = new PricePlanPO();
        pricePlanQueryPO.setHotelId(requestDTO.getHotelId());
        pricePlanQueryPO.setPriceplanId(requestDTO.getPricePlanId());
        pricePlanQueryPO.setQuotaTypeList(requestDTO.getQuotaTypeList());
        pricePlanQueryPO.setIsActive(requestDTO.getIsActive());
        pricePlanQueryPO.setCurrentPage(requestDTO.getCurrentPage());
        pricePlanQueryPO.setPageSize(requestDTO.getPageSize());
        pricePlanQueryPO.setCityType(requestDTO.getCityType());
        if (!StringUtils.isEmpty(requestDTO.getSupplyCode())){
            pricePlanQueryPO.setSupplyCode(requestDTO.getSupplyCode());
        }

        if (!StringUtils.isEmpty(requestDTO.getCityCode())) {
            pricePlanQueryPO.setCityCode(requestDTO.getCityCode());
        }

        //酒店-房型-供应商-价格计划名称-包房类型-全部信息返回了
        PaginationDTO<PricePlanDTO> pricePlanPaginationDTO = pricePlanService.queryPricePlanList(pricePlanQueryPO);


        if (null == pricePlanPaginationDTO || CollectionUtils.isEmpty(pricePlanPaginationDTO.getRecordList())){
            return null;
        }

        List<PricePlanDTO> pricePlanDTOList = pricePlanPaginationDTO.getRecordList();

        List<Long> pricePlanIdList = getPricePlanIdList(pricePlanDTOList);

        //查询价格 key=价格计划，value=List<日期，价格>
        Map<Long,Map<String,PricePO>> priceMap = queryPriceList(getPriceQueryParam(pricePlanIdList,requestDTO));

        //查询房态 key=配额账号，value=Map<日期，配额>
        Map<Long,Map<String,QuotaPO>> quotaMap = queryQuotaList(getQuotaQueryParam(pricePlanDTOList,requestDTO));

        //查询预留配额  key=价格计划，value=List<日期，预留配额>
        Map<Long,Map<String,ReserveQuotaPO>> reserveQuotaMap = queryReserveQuotaList(getPriceQueryParam(pricePlanIdList,requestDTO));

        //将所有日期获取出来
        List<DateInfoDTO> dateInfoDTOList = null;
        try {
            dateInfoDTOList = DateUtil.getDateList(requestDTO.getBeginDate(),requestDTO.getEndDate());
        } catch (ParseException e) {
            logger.error("查询控房时日期格式无法解析,{}",requestDTO);
            throw new ServiceException("查询控房时日期格式无法解析");
        }
        if (CollectionUtils.isEmpty(dateInfoDTOList)){
            logger.error("查询控房时日期为空,{}",requestDTO);
            throw new ServiceException("查询控房时日期为空");
        }

        //一个日期一个对象
        RoomControlResponseDTO responseDTO = null;
        List<RoomControlResponseDTO> responseDTOList = new LinkedList<RoomControlResponseDTO>();
        for (PricePlanDTO pricePlanDTO : pricePlanDTOList){

            //价格信息
            Map<String,PricePO> onePricePlanPricePOMap = priceMap.get(pricePlanDTO.getPriceplanId());

            //房型信息
            Map<String,QuotaPO> onePricePlanQuotaMap = quotaMap.get(pricePlanDTO.getAccountId());

            //预留配额
            Map<String,ReserveQuotaPO> onePricePlanReserveQuotaMap = reserveQuotaMap.get(pricePlanDTO.getPriceplanId());


            for (DateInfoDTO dateInfo :dateInfoDTOList){

                responseDTO = new RoomControlResponseDTO();
                //如果此条日期比今天还小，则不能修改
                if (DateUtil.getDay(DateUtil.getCurrentDate(),dateInfo.getSaleDate())< 0){
                    responseDTO.setCanEdit(0);
                }
                responseDTO.setPricePlanId(pricePlanDTO.getPriceplanId());
                responseDTO.setPricePlanName(pricePlanDTO.getPriceplanName());
                responseDTO.setHotelId(pricePlanDTO.getHotelId());
                responseDTO.setHotelName(pricePlanDTO.getHotelName());
                responseDTO.setRoomTypeId(pricePlanDTO.getRoomTypeId());
                responseDTO.setRoomTypeName(pricePlanDTO.getRoomTypeName());
                responseDTO.setQuotaType(pricePlanDTO.getQuotaType());
                responseDTO.setQuotaTypeName(pricePlanDTO.getQuotaTypeName());
                responseDTO.setIsActive(pricePlanDTO.getIsActive());
                responseDTO.setIsActiveName(pricePlanDTO.getIsActiveName());
                responseDTO.setSaleDate(dateInfo.getSaleDate());
                responseDTO.setSaleDateStr(DateUtil.dateToString(dateInfo.getSaleDate(),"yyyy-MM-dd"));
                responseDTO.setAccouontId(pricePlanDTO.getAccountId());
                responseDTO.setPreHoldQuota(pricePlanDTO.getPreHoldQuota());
                responseDTO.setReserveMinute(InitData.RESERVE_QUOTA_TIME);//暂时写死15分钟

                //价格
                String formateDate = DateUtil.dateToString(dateInfo.getSaleDate(),"yyyy-MM-dd");
                if (null != onePricePlanPricePOMap && onePricePlanPricePOMap.containsKey(formateDate)){
                    PricePO tempPrice = onePricePlanPricePOMap.get(formateDate);
                    if (null != tempPrice){
                        responseDTO.setPriceId(tempPrice.getPriceId());
                        responseDTO.setBasePrice(tempPrice.getBasePrice());
                        responseDTO.setBaseCurrency(tempPrice.getBaseCurrency());
                        responseDTO.setSaleBPrice(tempPrice.getSaleBPrice());
                        responseDTO.setSaleBCurrency(tempPrice.getSaleChannelCurrency());
                        responseDTO.setSaleCPrice(tempPrice.getSaleCPrice());
                        responseDTO.setSaleCCurrency(tempPrice.getSaleCCurrency());
                        responseDTO.setSaleCtripPrice(tempPrice.getCtripPrice());
                        responseDTO.setSaleCtripCurrency(tempPrice.getSaleChannelCurrency());
                        responseDTO.setSaleTaobaoPrice(tempPrice.getTmPrice());
                        responseDTO.setSaleTaobaoCurrency(tempPrice.getSaleChannelCurrency());
                    }
                }

                //配额房态
                if (null != onePricePlanQuotaMap && onePricePlanQuotaMap.containsKey(formateDate)){
                    QuotaPO tempQuotaPO = onePricePlanQuotaMap.get(formateDate);
                    if (null != tempQuotaPO){
                        responseDTO.setQuotaId(tempQuotaPO.getQuotaId());
                        responseDTO.setQuotaNum(tempQuotaPO.getQuotaNum());
                        responseDTO.setUsedQuotaNum(tempQuotaPO.getUsedQuotaNum());
                        responseDTO.setAllQuotaNum(tempQuotaPO.getAllQuotaNum());
                        responseDTO.setDetQuotaNum(tempQuotaPO.getDetQuotaNum());
//                        responseDTO.setReserveQuotaNum(tempQuotaPO.getReserveQuotaNum());
                    }
                }

                //预留配额
                if (null != onePricePlanReserveQuotaMap && onePricePlanReserveQuotaMap.containsKey(formateDate)){
                    ReserveQuotaPO tempReserveQuotaPO = onePricePlanReserveQuotaMap.get(formateDate);
                    if (null != tempReserveQuotaPO){
                        responseDTO.setReserveQuotaNum(tempReserveQuotaPO.getReserveQuotaNum());
                    }
                }

                responseDTOList.add(responseDTO);
            }
        }

        //分页
        PaginationDTO<RoomControlResponseDTO> pageResult = new PaginationDTO<RoomControlResponseDTO>();
        pageResult.setPageSize(pricePlanPaginationDTO.getPageSize());
        pageResult.setCurrentPage(pricePlanPaginationDTO.getCurrentPage());
        pageResult.setTotalPages(pricePlanPaginationDTO.getTotalPages());
        pageResult.setTotalCount(pricePlanPaginationDTO.getTotalCount());
        pageResult.setRecordList(responseDTOList);

        return pageResult;
    }

    /***
     * 控房模块，单独保存某一天的数据（价格和配额），可能是新增，也可能是修改。
     */
    @Override
    public int saveDayPriceAndQuota(DayRoomControlDTO dayRoomControlDTO) {

        /*
         * 1-priceId为空，表示要新增价格；否则修改价格-------价格表
         * 2-quotaId为空，表示要新增配额。否则修改配额。-----房态配额表
         *      2.1 accountId也为空则表示要，新增配额账号，然后新增配额。
         * 3-有效不为空，表示要修改价格计划的有效性       -----价格计划表
         * 4-是否预留配额不为空，表示要修改酒店的是否预留配额的设置------酒店表
         *
         */

        savePrice(dayRoomControlDTO);
        saveQuota(dayRoomControlDTO);
        setActive(dayRoomControlDTO);
        setPreQuota(dayRoomControlDTO);

        return 0;
    }

    private void setPreQuota(DayRoomControlDTO dayRoomControlDTO) {
        if (null != dayRoomControlDTO.getPreHoldQuota()){
            HotelPO hotelPO = new HotelPO();
            hotelPO.setHotelId(dayRoomControlDTO.getHotelId());
            hotelPO.setPreHoldQuota(dayRoomControlDTO.getPreHoldQuota());
            hotelService.updateHotel(hotelPO);
        }
    }

    private void setActive(DayRoomControlDTO dayRoomControlDTO) {
        if (null != dayRoomControlDTO.getIsActive()){
            pricePlanService.updateAcvtive(dayRoomControlDTO.getPricePlanId(),dayRoomControlDTO.getIsActive(),dayRoomControlDTO.getModifier());
        }
    }

    private void saveQuota(DayRoomControlDTO dayRoomControlDTO) {
        QuotaPO quotaPO = new QuotaPO();
        Long accountId = getAccountId(dayRoomControlDTO);
        quotaPO.setAccountId(accountId);
        quotaPO.setAllQuotaNum(dayRoomControlDTO.getAllQuotaNum());
        quotaPO.setQuotaNum(dayRoomControlDTO.getQuotaNum());
        quotaPO.setDetQuotaNum(dayRoomControlDTO.getDetQuotaNum());
        quotaPO.setReserveQuotaNum(dayRoomControlDTO.getReserveQuotaNum());
        quotaPO.setModifier(dayRoomControlDTO.getModifier());
        quotaPO.setModifydate(dayRoomControlDTO.getModifyDate());
        quotaPO.setSaleDate(dayRoomControlDTO.getSaleDate());

        //FIXME 此处可以与addPriceQuotaRestrict方法进行提取公共方法
        if (null == dayRoomControlDTO.getQuotaId()){
            //新增房态
            List<QuotaPO> quotaPOList = new ArrayList<QuotaPO>();
            quotaPO.setCreatedate(DateUtil.getCurrentDate());
            quotaPO.setCreator(dayRoomControlDTO.getCreator());
            quotaService.batchAddQuota(quotaPOList);

            //更新价格计划表的配额账号字段
            PricePlanPO pricePlanPO = new PricePlanPO();
            pricePlanPO.setModifier(dayRoomControlDTO.getModifier());
            pricePlanPO.setModifydate(DateUtil.getCurrentDate());
            pricePlanPO.setAccountId(accountId);
            pricePlanPO.setPriceplanId(dayRoomControlDTO.getPricePlanId());
            pricePlanService.updateByCondition(pricePlanPO);
        }else{
            quotaService.updateByAccountIdSelective(quotaPO);
        }
    }

    private void savePrice(DayRoomControlDTO dayRoomControlDTO) {
        PricePO pricePO = new PricePO();
        pricePO.setPriceplanId(dayRoomControlDTO.getPricePlanId());
        pricePO.setSaleBPrice(dayRoomControlDTO.getSaleBPrice());
        pricePO.setSaleCPrice(dayRoomControlDTO.getSaleCPrice());
        pricePO.setBasePrice(dayRoomControlDTO.getBasePrice());
        pricePO.setBaseCurrency(dayRoomControlDTO.getBaseCurrency());
        pricePO.setSaleChannelCurrency(dayRoomControlDTO.getSaleBCurrency());
        pricePO.setSaleCCurrency(dayRoomControlDTO.getSaleCCurrency());
        pricePO.setSaleDate(dayRoomControlDTO.getSaleDate());
        pricePO.setModifier(dayRoomControlDTO.getModifier());
        pricePO.setModifydate(DateUtil.getCurrentDate());

        if (null == dayRoomControlDTO.getPriceId()){
            //新增价格
            List<PricePO> pricePOList = new ArrayList<PricePO>();
            pricePO.setCreatedate(DateUtil.getCurrentDate());
            pricePO.setCreator(dayRoomControlDTO.getCreator());
            pricePOList.add(pricePO);
            priceService.batchAddPrice(pricePOList);

        } else{
            //修改价格
            pricePO.setPriceId(dayRoomControlDTO.getPriceId());
            priceService.updatePriceByCondition(pricePO);
        }
    }

    /**
     *
     * @param param
     * @return key=配额账号ID，value=配额列表  Map<价格计划ID，Map<日期，配额>>
     */
    private Map<Long,Map<String,QuotaPO>> queryQuotaList(Map<String,Object> param){
        Map<Long,Map<String,QuotaPO>> quotaMap = new HashMap<Long,Map<String,QuotaPO>>();
        //没有配额账号都不用查询
        if (null == param.get("InAccountId") || CollectionUtils.isEmpty((List<Long>)param.get("InAccountId"))){
            return quotaMap;
        }
        List<QuotaPO> allQuotaList = quotaService.queryByCondition(param);

        if (CollectionUtils.isEmpty(allQuotaList)){
            return quotaMap;
        }



        Map<String,QuotaPO> dayQuotaMap = null;

        //按照价格计划分组
        for (QuotaPO po : allQuotaList){
            if (quotaMap.containsKey(po.getAccountId())){
                quotaMap.get(po.getAccountId()).put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po);
            } else {
                dayQuotaMap = new LinkedHashMap<String,QuotaPO>();
                dayQuotaMap.put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po);

                quotaMap.put(po.getAccountId(),dayQuotaMap);
            }
        }

        return quotaMap;
    }

    private Map<String,Object> getQuotaQueryParam(List<PricePlanDTO> pricePlanDTOList,RoomControlRequestDTO requestDTO){
        List<Long> accountIdList = new ArrayList<Long>();
        for (PricePlanDTO pricePlanDTO : pricePlanDTOList){
            if (null != pricePlanDTO.getAccountId() && !accountIdList.contains(pricePlanDTO.getAccountId().intValue())){
                accountIdList.add(pricePlanDTO.getAccountId());
            }
        }

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("InAccountId",accountIdList);
        param.put("checkInDate",DateUtil.stringToDate(requestDTO.getBeginDate(),"yyyy-MM-dd"));
        //SQL中不包括结束日期，所以这里默认添加1天查询
        param.put("checkOutDate",DateUtil.getAfterDate(DateUtil.stringToDate(requestDTO.getEndDate(),"yyyy-MM-dd")));

        return param;
    }


    /**
     *
     * @param param
     * @return key=价格计划ID，value=价格列表  Map<价格计划ID，List<价格>>
     */
    private Map<Long,Map<String,ReserveQuotaPO>> queryReserveQuotaList(Map<String,Object> param){

        Map<Long,Map<String,ReserveQuotaPO>> priceMap = new HashMap<Long,Map<String,ReserveQuotaPO>>();

        List<ReserveQuotaPO> allPriceList = pricePlanService.queryDaySumByPricePlan(param);
        if (CollectionUtils.isEmpty(allPriceList)){
            return priceMap;
        }

        Map<String,ReserveQuotaPO> dayPriceList = null;
        //按照价格计划分组
        for (ReserveQuotaPO po : allPriceList){
            if (priceMap.containsKey(po.getPriceplanId())){
                priceMap.get(po.getPriceplanId()).put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po);
            } else {
                dayPriceList = new LinkedHashMap<String,ReserveQuotaPO>();
                dayPriceList.put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po);

                priceMap.put(po.getPriceplanId(),dayPriceList);
            }
        }

        return priceMap;
    }

    /**
     *
     * @param param
     * @return key=价格计划ID，value=价格列表  Map<价格计划ID，List<价格>>
     */
    private Map<Long,Map<String,PricePO>> queryPriceList(Map<String,Object> param){

        Map<Long,Map<String,PricePO>> priceMap = new HashMap<Long,Map<String,PricePO>>();

        List<PricePO> allPriceList = priceService.queryPriceByCondition(param);
        if (CollectionUtils.isEmpty(allPriceList)){
            return priceMap;
        }

        Map<String,PricePO> dayPriceList = null;
        //按照价格计划分组
        for (PricePO po : allPriceList){
            if (priceMap.containsKey(po.getPriceplanId())){
                priceMap.get(po.getPriceplanId()).put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po);
            } else {
                dayPriceList = new LinkedHashMap<String,PricePO>();
                dayPriceList.put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po);

                priceMap.put(po.getPriceplanId(),dayPriceList);
            }
        }

        return priceMap;
    }

    private Map<String,Object> getPriceQueryParam(List<Long> pricePlanIdList ,RoomControlRequestDTO requestDTO){

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("InPriceplanId",pricePlanIdList);
        param.put("checkInDate",DateUtil.stringToDate(requestDTO.getBeginDate(),"yyyy-MM-dd"));
        //SQL语句中是不包含结束日期的，所以这里加1天查询
        param.put("checkOutDate",DateUtil.getAfterDate(DateUtil.stringToDate(requestDTO.getEndDate(),"yyyy-MM-dd")));

        return param;
    }

    private List<Long> getPricePlanIdList(List<PricePlanDTO> pricePlanDTOList) {
        List<Long> pricePlanIdList = new ArrayList<Long>();
        for (PricePlanDTO pricePlanDTO : pricePlanDTOList){
            pricePlanIdList.add(pricePlanDTO.getPriceplanId());
        }
        return pricePlanIdList;
    }

    private Long getAccountId(DayRoomControlDTO dayRoomControlDTO){
        Long accountId = dayRoomControlDTO.getAccouontId();
        if (null == accountId){
            //查询该房型下是否已存在了配额账号,m没有就新增一个，有就拿来用
            List<QuotaAccountPO> accountList = quotaAccountService.queryQuotaAccountListByRoomId(dayRoomControlDTO.getRoomTypeId());
            if (CollectionUtils.isEmpty(accountList)){
                //新增配额账号
                QuotaAccountPO addAccountPO = new QuotaAccountPO();
                addAccountPO.setRoomTypeId(dayRoomControlDTO.getRoomTypeId());
                addAccountPO.setAccountName(dayRoomControlDTO.getRoomTypeName()+"-"+dayRoomControlDTO.getPricePlanName());
                accountId = quotaAccountService.addQuotaAccount(addAccountPO);
            }else{
                accountId = accountList.get(0).getAccountId();
            }
        }
        return accountId;
    }
}
