package com.travel.hotel.dlt.utils;

import com.travel.common.utils.DateUtil;

import java.util.Date;

/**
 *   2018/5/15.
 */
public class MainTest {

    public static void main(String[] args) {

        Date d = DateUtil.stringToDate("2018-05-14");
        Integer hours = DateUtil.getHours(new Date(), DateUtil.getDate(d, 1, 0));
        System.out.println(hours);

    }

}
