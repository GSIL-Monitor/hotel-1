package com.fangcang.finance.prepay.schedule;

import com.fangcang.finance.prepay.service.PrepayContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zhanwang
 */
@Component
@Slf4j
public class StatisSupplyDoneRoomNightSchedule {

    @Autowired
    private PrepayContractService prepayContractService;

    /**
     * 每个半个小时统计供应商成交间夜数
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void statisSupplyDoneRoomNight() {
        try {
            prepayContractService.statisSupplyDoneRoomNight();
        } catch (Exception e) {
            log.error("statisSupplyDoneRoomNight error.", e);
        }
    }
}
