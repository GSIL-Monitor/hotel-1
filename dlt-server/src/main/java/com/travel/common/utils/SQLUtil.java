package com.travel.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 *   2018/1/26.
 */
public class SQLUtil {


    /**
     * 组装SQL语句中的IN:   'buyout1','buyout2','buyout3'
     * @return
     */
    public static String getStringInSQL(List<String> list){
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        StringBuffer in = new StringBuffer();

        for (String str : list){
            in.append("'").append(str).append("'");
            in.append(",");
        }

        return in.substring(0,in.length()-1);
    }

    /**
     * 组装SQL语句中的IN:   'buyout1','buyout2','buyout3'
     * @return
     */
    public static String getLongInSQL(List<Long> list){
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        StringBuffer in = new StringBuffer();

        for (Long str : list){
            in.append("'").append(str).append("'");
            in.append(",");
        }

        return in.substring(0,in.length()-1);
    }
}
