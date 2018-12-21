package com.travel.hotel.dlt.schedule;


import com.travel.channel.dao.DltMapRoomPOMapper;
import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.channel.entity.po.DltMapRoomPOExample;
import com.travel.common.constant.InitData;
import com.travel.hotel.dlt.task.BatchPushRoomDataTask;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 定时推送房态、房价、房量信息到代理通
 *   2018/4/11.
 */
@Component
@Lazy(false)
@EnableScheduling
public class BatchPushRoomDataSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(BatchPushRoomDataSchedule.class);
    private static final String CRON = "0 0 0/5 * * ?";
    public static volatile Boolean needSyncRoomDataToDlt = true;

    @Autowired
    private DltMapRoomPOMapper dltMapRoomPOMapper;

    @Autowired
    private ThreadPoolTaskExecutor batchPushRoomDataTaskTaskExecutor;

    @Scheduled(cron = CRON)
    public void batchPushRoomData() {
        LOG.info("batchPushRoomData execute... " + CRON);
        if (!needSyncRoomDataToDlt) {
            LOG.error("被设置为不需要同步报价到代理通，将直接在代理通后台录入报价");
            return;
        }

        // 查询房型映射表，查询所有已经映射的房型
        DltMapRoomPOExample example = new DltMapRoomPOExample();
        example.createCriteria().andDltRoomIdIsNotNull();
        List<DltMapRoomPO> dltMapRoomPOList = dltMapRoomPOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(dltMapRoomPOList)) {
            LOG.error("未查询到任何有映射关系的房型，推送结束");
            return;
        }

        //将映射关系按商家分组，分开推送
        Map<String,List<DltMapRoomPO>> mappingMap = new HashMap<String,List<DltMapRoomPO>>();
        if(null!=InitData.channelConfigMap && null!=InitData.channelConfigMap.keySet()) {
            for(String merchantCode : InitData.channelConfigMap.keySet()) {
                List<DltMapRoomPO> mapRoomPOList = new ArrayList<>();
                mappingMap.put(merchantCode,mapRoomPOList);
            }

            for(DltMapRoomPO dltMapRoomPO:dltMapRoomPOList) {
                if(null!=mappingMap.get(dltMapRoomPO.getMerchantCode())) {
                    mappingMap.get(dltMapRoomPO.getMerchantCode()).add(dltMapRoomPO);
                }
            }
        }else {
            LOG.error("渠道配置表信息加载出错，或没有配置渠道对接信息！");
        }


        if(mappingMap.size() > 0) {
            for(String merchantCode : mappingMap.keySet()) {
                BatchPushRoomDataTask task = new BatchPushRoomDataTask(mappingMap.get(merchantCode),merchantCode,null,null);
                batchPushRoomDataTaskTaskExecutor.execute(task);
            }
        }
    }
}
