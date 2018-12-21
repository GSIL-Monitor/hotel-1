package com.travel.hotel.dlt.service.impl;

import com.travel.common.utils.DateUtil;
import com.travel.hotel.BaseTest;
import com.travel.hotel.dlt.service.BatchPushRoomDataService;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 *   2018/5/14.
 */
public class BatchPushRoomDataServiceImplTest extends BaseTest {

    private BatchPushRoomDataService batchPushRoomDataService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        batchPushRoomDataService = (BatchPushRoomDataService) context.getBean("batchPushRoomDataService");
    }

    @Test
    public void pushRoomDataToDltByPricePlan() throws Exception {

        Date checkInDate = DateUtil.dateFormat(new Date());
        Date checkOutDate = DateUtil.dateFormat(DateUtil.getDate(new Date(), 7, 0));
        batchPushRoomDataService.pushRoomDataToDltByPricePlan(16L, checkInDate, checkOutDate);

    }

}