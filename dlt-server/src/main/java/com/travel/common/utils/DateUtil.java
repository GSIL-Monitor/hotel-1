package com.travel.common.utils;


import com.github.pagehelper.util.StringUtil;
import com.travel.common.dto.DateInfoDTO;
import com.travel.common.exception.ParameterException;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 默认格式 yyyy-MM-dd
     */
    public static String defaultFormat = "yyyy-MM-dd";

    /**
     * 将字符串转换为日期 (使用默认格式yyyy-MM-dd)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date) {
        return stringToDate(date, defaultFormat);

    }

    /**
     * 自定义格式将字符串转换为日期
     *
     * @param date   字符串的日期
     * @param format 格式(形如:yyyy-MM-dd)
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date, String format) {
        try {
            if (StringUtils.isEmpty(date)) {
                return null;
            }
            if (StringUtil.isEmpty(format)) {
                format = defaultFormat;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new ParameterException(e);
        }
    }

    /**
     * 将日期转换为字符串 (使用默认格式yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, defaultFormat);
    }

    /**
     * 自定义格式将日期转换为字符串
     *
     * @param date   日期
     * @param format 格式
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (null == date) {
            return null;
        }
        if (StringUtil.isEmpty(format)) {
            format = defaultFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(date);
    }


    /**
     * 格式化日期，默认格式yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date dateFormat(Date date) {
        return dateFormat(date, null);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return
     */
    public static Date dateFormat(Date date, String format) {

        if (null == date) {
            return null;
        }
        if (StringUtil.isEmpty(format)) {
            format = defaultFormat;
        }
        String formatDate = dateToString(date, format);

        return stringToDate(formatDate, format);
    }

    /**
     * 对传入的时间进行加减天数/加减小时数
     *
     * @param date 原始时间
     * @param day  需要增加减少的天数
     * @param hour 需要增加减少的小时数
     * @return
     */
    public static Date getDate(Date date, int day, int hour) {
        if (null != date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, day);
            calendar.add(Calendar.HOUR, hour);
            date = calendar.getTime();
        }
        return date;
    }

    /**
     * 原始时间减少天  小时   分钟数
     *
     * @param date
     * @param day
     * @param hour
     * @return Date
     * @Title getDate
     * @author fengxiaozhen 2015-11-16 下午2:16:51
     */
    public static Date getDate(Date date, int day, int hour, int minute) {
        if (null != date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, day);
            calendar.add(Calendar.HOUR, hour);
            calendar.add(Calendar.MINUTE, minute);
            date = calendar.getTime();
        }
        return date;
    }

    public static Date getDate(Date date, int month) {
        if (null != date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, month);
            date = calendar.getTime();
        }
        return date;
    }

    /**
     * 测试日期是否在某一段日期之间
     *
     * @param date
     * @param start
     * @param end
     * @return
     */
    public static boolean between(Date date, Date start, Date end) {
        return 0 <= getDay(start, date) && 0 >= getDay(end, date);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 得到两个时间点相差的天数(date2-date1)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDay(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return 0;
        }
        return Long.valueOf((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)).intValue();
    }

    /**
     * 得到两个时间点相差的小时数(date2-date1)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getHours(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return 0;
        }
        return Long.valueOf((date2.getTime() - date1.getTime()) / (1000 * 60 * 60)).intValue();
    }

    /**
     * 得到两个时间点相差的分钟数(date2-date1)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getMinute(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return 0;
        }
        return (date2.getTime() - date1.getTime()) / (1000 * 60);
    }

    /**
     * 获取两个时间段的秒数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getSecond(Date beginDate, Date endDate) {
        long time = endDate.getTime() - beginDate.getTime();
        long second = (time / 1000);
        return second;
    }

    /**
     * 比较两个时间点大小(精确到日期) </br> date1 > date2 返回1 </br> date1 = date2 返回0 </br> date1 <
     * date2 返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(Date date1, Date date2) {
        int resultCode = 0;
        long days = getDay(date1, date2);
        if (days < 0) {
            resultCode = 1;
        } else if (days > 0) {
            resultCode = -1;
        }
        return resultCode;
    }

    /**
     * 比较两个时间点大小(精确到毫秒) </br> date1 > date2 返回1 </br> date1 = date2 返回0 </br> date1 <
     * date2 返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareInMS(Date date1, Date date2) {
        int resultCode = 0;
        if (null == date1 || null == date2) {
            return resultCode;
        }
        long days = Long.valueOf((date2.getTime() - date1.getTime())).longValue();
        if (days < 0) {
            resultCode = 1;
        } else if (days > 0) {
            resultCode = -1;
        }
        return resultCode;
    }

    /**
     * 日期的星期
     *
     * @param date Date
     * @return int 1-7
     */
    public static int getWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return 0 == (calendar.get(Calendar.DAY_OF_WEEK) - 1) ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 返回日期星期
     *
     * @param date
     * @return
     */
    public static String getWeekStrOfDate(Date date) {
        int week = getWeekOfDate(date);
        String weekStr = null;
        switch (week) {
            case 1:
                weekStr = "周一";
                break;
            case 2:
                weekStr = "周二";
                break;
            case 3:
                weekStr = "周三";
                break;
            case 4:
                weekStr = "周四";
                break;
            case 5:
                weekStr = "周五";
                break;
            case 6:
                weekStr = "周六";
                break;
            case 7:
                weekStr = "周日";
                break;
        }
        return weekStr;
    }

    /**
     * 返回两个时间段的List
     *
     * @param date1
     * @param date2
     * @return
     */
    public static List<Date> getDateList(Date date1, Date date2) {
        List<Date> dateList = null;
        if (null == date1 || null == date2)
            dateList = Collections.emptyList();
        int days = getDay(date1, date2);
        if (days >= 0) {
            dateList = new ArrayList<Date>(days);
            for (int i = 0; i <= days; i++) {
                dateList.add(getDate(date1, i, 0));
            }
        } else {
            dateList = Collections.emptyList();
        }

        return dateList;
    }

    /**
     * 返回两个时间段的Map
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Map<Date, Date> getDateMap(Date date1, Date date2) {
        Map<Date, Date> dateMap = null;
        if (null == date1 || null == date2)
            dateMap = Collections.emptyMap();
        int days = getDay(date1, date2);
        if (days >= 0) {
            dateMap = new HashMap<Date, Date>(days);
            for (int i = 0; i <= days; i++) {
                dateMap.put(dateFormat(getDate(date1, i, 0), "yyyy-MM-dd"), dateFormat(getDate(date1, i, 0), "yyyy-MM-dd"));
            }
        } else {
            dateMap = Collections.emptyMap();
            ;
        }

        return dateMap;
    }

    /**
     * 获取两个时间段的分钟数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getMinsBetweenDate(Date beginDate, Date endDate) {
        long time = endDate.getTime() - beginDate.getTime();
        long min = (time / (60 * 1000));
        return min;
    }

    /**
     * 根据某个日期得到前一天日期
     *
     * @param d
     * @return
     */
    public static Date getBeforeDate(Date d) {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 得到某个日期的后一天日期
     *
     * @param d
     * @return
     */
    public static Date getAfterDate(Date d) {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 日期是否符合某一星期
     *
     * @param date
     * @param week
     * @return
     */
    public static boolean isMatchWeek(Date date, int week) {
        return getWeekOfDate(date) == week;
    }

    /**
     * 返回beginDateStr和endDateStr之间的日期列表
     * @param beginDateStr
     * @param endDateStr
     * @return List<DateInfoDTO> 日期和星期几
     */
    public static List<DateInfoDTO> getDateList(String beginDateStr,String endDateStr) throws ParseException {

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dateList = getDateList(simpleDateFormat.parse(beginDateStr),simpleDateFormat.parse(endDateStr));
        List<DateInfoDTO> dateInfoList = new LinkedList<DateInfoDTO>();
        DateInfoDTO dateInfoDTO = null;
        for (Date date : dateList){
            dateInfoDTO = new DateInfoDTO();
            dateInfoDTO.setSaleDate(date);
            dateInfoDTO.setWeekDay(getWeekStrOfDate(date));
            dateInfoList.add(dateInfoDTO);
        }
        return dateInfoList;
    }

    /**
     * 判断date是否是周末
     * 周末由商家自己定义，目前是写在字典表里面的
     * @param weekendDefinition  周五  周六
     * @param weekday  周一
     * @return
     */
    public static boolean isWeekend(List<String> weekendDefinition, String weekday){
        return weekendDefinition.contains(weekday);
    }
    
    /**
     * 根据日期返回月和天
     * 格式 月/天 如05/08
     * @param date
     * @return
     */
    public static String getMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String dt = "";
		String d = "";
		if (month < 10) {
			dt = "0" + month;
		} else {
			dt = month + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		return dt + "/" + d;
	}

}
