package com.fangcang.product.thread;

import com.fangcang.common.util.DateUtil;
import com.fangcang.product.domain.PriceInfoDO;
import com.fangcang.product.mapper.PriceInfoMapper;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ASUS on 2018/6/1.
 */
@Slf4j
public class PriceInfoThread extends Thread {

    private PriceInfoMapper priceInfoMapper;

    private Map<String,PriceInfoDO> priceInfoDOMap;

    private CountDownLatch countDownLatch;

    private ProductDailyInfoQueryDTO productDailyInfoQueryDTO;

    public PriceInfoThread(PriceInfoMapper priceInfoMapper, Map<String, PriceInfoDO> priceInfoDOMap,
                           CountDownLatch countDownLatch, ProductDailyInfoQueryDTO productDailyInfoQueryDTO) {
        this.priceInfoMapper = priceInfoMapper;
        this.priceInfoDOMap = priceInfoDOMap;
        this.countDownLatch = countDownLatch;
        this.productDailyInfoQueryDTO = productDailyInfoQueryDTO;
    }

    @Override
    public void run() {
        try {
            List<PriceInfoDO> priceInfoDOList = priceInfoMapper.queryDailyPriceInfo(productDailyInfoQueryDTO);
            if(!CollectionUtils.isEmpty(priceInfoDOList)){
                StringBuilder key = new StringBuilder();
                for (PriceInfoDO priceInfoDO : priceInfoDOList) {
                    key.append(priceInfoDO.getPricePlanId()).append(":").append(DateUtil.dateToString(priceInfoDO.getSaleDate()));
                    priceInfoDOMap.put(key.toString(),priceInfoDO);
                    key.setLength(0);
                }
            }
        } catch (Exception e) {
            log.error("queryDailyPriceInfo has error",e);
        } finally {
            countDownLatch.countDown();
        }
    }
}
