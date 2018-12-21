package com.fangcang.product.thread;

import com.fangcang.common.util.DateUtil;
import com.fangcang.product.domain.QuotaStateDO;
import com.fangcang.product.mapper.QuotaStateMapper;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.request.QuotaStateQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ASUS on 2018/6/1.
 */

@Slf4j
public class QuotaStateThread extends Thread{

    private QuotaStateMapper quotaStateMapper;

    private Map<String,QuotaStateDO> quotaStateDOMap;

    private CountDownLatch countDownLatch;

    private ProductDailyInfoQueryDTO productDailyInfoQueryDTO;

    public QuotaStateThread(QuotaStateMapper quotaStateMapper, Map<String, QuotaStateDO> quotaStateDOMap
            ,CountDownLatch countDownLatch,ProductDailyInfoQueryDTO productDailyInfoQueryDTO) {
        this.quotaStateMapper = quotaStateMapper;
        this.quotaStateDOMap = quotaStateDOMap;
        this.countDownLatch = countDownLatch;
        this.productDailyInfoQueryDTO = productDailyInfoQueryDTO;
    }

    @Override
    public void run() {
        try {
            QuotaStateQueryDTO quotaStateQueryDTO = new QuotaStateQueryDTO();
            quotaStateQueryDTO.setPricePlanId(productDailyInfoQueryDTO.getPricePlanId());
            quotaStateQueryDTO.setStartDate(productDailyInfoQueryDTO.getStartDate());
            quotaStateQueryDTO.setEndDate(productDailyInfoQueryDTO.getEndDate());
            quotaStateQueryDTO.setPricePlanIds(productDailyInfoQueryDTO.getPricePlanIds());
            List<QuotaStateDO> quotaStateDOList = quotaStateMapper.dynamicQueryQuotaStateDailyInfo(quotaStateQueryDTO);
            if(!CollectionUtils.isEmpty(quotaStateDOList)){
                StringBuilder key = new StringBuilder();
                for (QuotaStateDO quotaStateDO : quotaStateDOList) {
                    key.append(quotaStateDO.getPricePlanId()).append(":").append(DateUtil.dateToString(quotaStateDO.getSaleDate()));
                    quotaStateDOMap.put(key.toString(),quotaStateDO);
                    key.setLength(0);
                }
            }
        } catch (Exception e) {
            log.error("dynamicQueryQuotaStateDailyInfo has error",e);
        } finally {
            countDownLatch.countDown();
        }
    }
}
