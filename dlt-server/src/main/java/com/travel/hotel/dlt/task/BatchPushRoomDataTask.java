package com.travel.hotel.dlt.task;

import com.alibaba.fastjson.JSON;
import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.SpringContextUtil;
import com.travel.hotel.dlt.service.BatchPushRoomDataService;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 *   2018/3/1.
 */
@Data
public class BatchPushRoomDataTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(BatchPushRoomDataTask.class);

    private List<DltMapRoomPO> dltMapRoomPOList;

    private String merchantCode;

    private BatchPushRoomDataService batchPushRoomDataService;

    private Date checkInDate;

    private Date checkOutDate;

    public BatchPushRoomDataTask(List<DltMapRoomPO> dltMapRoomPOList,String merchantCode,
                                 Date checkInDate,Date checkOutDate) {
        this.dltMapRoomPOList = dltMapRoomPOList;
        this.merchantCode = merchantCode;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public void run() {
        if (CollectionUtils.isEmpty(dltMapRoomPOList)) {
            LOG.error("将要同步的已映射房型列表为空， {}" , JSON.toJSONString(dltMapRoomPOList));
            return;
        }
        // 1. 推送售卖详情到代理通
        try {
            batchPushRoomDataService = (BatchPushRoomDataService) SpringContextUtil.getBean("batchPushRoomDataService");
            if (checkInDate==null||checkOutDate==null){
                batchPushRoomDataService.setRoomSaleRule(dltMapRoomPOList,merchantCode);
            }
            checkInDate = (null==checkInDate?DateUtil.dateFormat(new Date()):checkInDate);
            checkOutDate = (null==checkOutDate?DateUtil.dateFormat(DateUtil.getDate(new Date(), 180, 0)):checkOutDate);
            batchPushRoomDataService.pushRoomDataToDltByMapRoomList(dltMapRoomPOList, checkInDate, checkOutDate,merchantCode);
        } catch (Exception e) {
            LOG.error("定时推送房型报价数据失败, dltMapRoomPOList=" + dltMapRoomPOList, e);
        }
    }
}
