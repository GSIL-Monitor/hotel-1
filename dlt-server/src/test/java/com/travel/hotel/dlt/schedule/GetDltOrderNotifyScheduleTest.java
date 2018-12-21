package com.travel.hotel.dlt.schedule;

import com.travel.hotel.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   2018/5/14.
 */
public class GetDltOrderNotifyScheduleTest extends BaseTest {


    private GetDltOrderNotifySchedule getDltOrderNotifySchedule;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getDltOrderNotifySchedule = (GetDltOrderNotifySchedule) context.getBean("getDltOrderNotifySchedule");
    }


    @Test
    public void getDltOrderNotify() throws Exception {
        getDltOrderNotifySchedule.getDltOrderNotify();
    }

}