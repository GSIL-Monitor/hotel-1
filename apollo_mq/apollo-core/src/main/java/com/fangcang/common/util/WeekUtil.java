package com.fangcang.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/24.
 */
public class WeekUtil {

    public static List<Date> getSaleDate(Date startDate,Date endDate,String weeks){
        List<Date> dates = new ArrayList<>();
        if(null != startDate && null != endDate && StringUtil.isValidString(weeks)){

            List<Date> dateList = DateUtil.getDateList(startDate, endDate);
            for (Date date : dateList) {
                String week = String.valueOf(DateUtil.getWeekOfDate(date));
                if(weeks.contains(week)){
                    dates.add(date);
                }
            }
        }
        return dates;
    }
}
