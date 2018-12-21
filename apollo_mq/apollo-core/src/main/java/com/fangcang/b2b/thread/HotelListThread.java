package com.fangcang.b2b.thread;

import com.fangcang.b2b.request.QueryHotelListRequestDTO;
import com.fangcang.b2b.response.QueryHotelListRsponseDTO;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.product.domain.PriceInfoDO;
import com.fangcang.product.mapper.PriceInfoMapper;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ASUS on 2018/7/2.
 */
@Slf4j
public class HotelListThread extends Thread {

    private CountDownLatch countDownLatch;

    private QueryHotelListRequestDTO queryHotelListRequestDTO;

    private QueryHotelListRsponseDTO queryHotelListRsponseDTO;

    private HotelDO hotelDO;

    private PriceInfoMapper priceInfoMapper;

    public HotelListThread(CountDownLatch countDownLatch,QueryHotelListRequestDTO queryHotelListRequestDTO,
                           QueryHotelListRsponseDTO queryHotelListRsponseDTO,
                           HotelDO hotelDO, PriceInfoMapper priceInfoMapper) {
        this.countDownLatch = countDownLatch;
        this.queryHotelListRequestDTO = queryHotelListRequestDTO;
        this.queryHotelListRsponseDTO = queryHotelListRsponseDTO;
        this.hotelDO = hotelDO;
        this.priceInfoMapper = priceInfoMapper;
    }

    @Override
    public void run() {
        try {
            Long startTime = System.currentTimeMillis();
            ProductDailyInfoQueryDTO productDailyInfoQueryDTO = new ProductDailyInfoQueryDTO();
            productDailyInfoQueryDTO.setHotelId(hotelDO.getHotelId());
            productDailyInfoQueryDTO.setMerchantCode(queryHotelListRequestDTO.getMerchantCode());
            productDailyInfoQueryDTO.setChannelCode(ChannelTypeEnum.B2B.key);
            productDailyInfoQueryDTO.setStartDate(queryHotelListRequestDTO.getCheckInDate());
            productDailyInfoQueryDTO.setEndDate(DateUtil.getDate(queryHotelListRequestDTO.getCheckOutDate(),-1,0));
            productDailyInfoQueryDTO.setMinPrice(queryHotelListRequestDTO.getMinPrice());
            productDailyInfoQueryDTO.setMaxPrice(queryHotelListRequestDTO.getMaxPrice());

            List<PriceInfoDO> priceInfoDOList = priceInfoMapper.queryPriceInfoByHotelId(productDailyInfoQueryDTO);
            if(!CollectionUtils.isEmpty(priceInfoDOList)){
                //计算起价
                //pricePlanId
                Set<Long> pricePlanSet = new HashSet<>();
                //pricePlanId:saleDate
                Map<String,PriceInfoDO> pricePlanDayMap = new HashMap<>();
                StringBuilder key = new StringBuilder();
                for (PriceInfoDO priceInfoDO : priceInfoDOList) {
                    pricePlanSet.add(priceInfoDO.getPricePlanId());

                    key.append(priceInfoDO.getPricePlanId()).append(":").append(DateUtil.dateToString(priceInfoDO.getSaleDate()));
                    pricePlanDayMap.put(key.toString(),priceInfoDO);
                    key.setLength(0);
                }
                key.setLength(0);
                Set<Long> hasPricePricePlanIds = new HashSet<>();
                List<Date> dateList = DateUtil.getDateList(queryHotelListRequestDTO.getCheckInDate(),DateUtil.getDate(queryHotelListRequestDTO.getCheckOutDate(),-1,0));
                //判断每一天价格都符合的
                for(Long pricePlanId : pricePlanSet){
                    boolean everyDayHasPrice = true;
                    for(Date saleDate : dateList){
                        key.append(pricePlanId).append(":").append(DateUtil.dateToString(saleDate));
                        if(!pricePlanDayMap.containsKey(key.toString())){
                            everyDayHasPrice = false;
                            break;
                        }
                        key.setLength(0);
                    }
                    if(everyDayHasPrice){
                        hasPricePricePlanIds.add(pricePlanId);
                    }
                }
                //起价
                key.setLength(0);
                BigDecimal startPrice = null;
                for(Long pricePlanId : hasPricePricePlanIds){
                    for(Date saleDate : dateList){
                        key.append(pricePlanId).append(":").append(DateUtil.dateToString(saleDate));
                        PriceInfoDO priceInfoDO = pricePlanDayMap.get(key.toString());
                        if(null == startPrice){
                            //第一次进来
                            startPrice = priceInfoDO.getB2bSalePrice();
                        }else if (null != priceInfoDO.getB2bSalePrice()){
                            if(startPrice.compareTo(priceInfoDO.getB2bSalePrice()) > 0){
                                startPrice = priceInfoDO.getB2bSalePrice();
                            }
                        }
                        key.setLength(0);
                    }
                }
                queryHotelListRsponseDTO.setStartPrice(startPrice);
            }
            Long endTime = System.currentTimeMillis();
            log.info(Thread.currentThread().getName() + ": queryPriceInfoByHotelId end,total cost:" + (endTime - startTime));
        } catch (Exception e) {
            log.error("queryPriceInfoByHotelId has error",e);
        }finally {
            countDownLatch.countDown();
        }
    }
}
