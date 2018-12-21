package com.fangcang.common.util;

import com.fangcang.common.config.HotelBaseInfoConfig;

/**
 * Created by ASUS on 2018/6/20.
 */
public class URLSplitUtil {

    public static String getUrl(IncrementConfig incrementConfig){
        StringBuilder stringBuilder = new StringBuilder();
        if(StringUtil.isValidString(incrementConfig.getDomain())){
            stringBuilder.append("http://").append(incrementConfig.getDomain())
                    .append("/").append(incrementConfig.getServer())
                    .append("/").append(incrementConfig.getInterfaceName());
        }else{
            stringBuilder.append("http://").append(incrementConfig.getIp())
                    .append(":").append(incrementConfig.getPort())
                    .append("/").append(incrementConfig.getServer())
                    .append("/").append(incrementConfig.getInterfaceName());
        }
        return stringBuilder.toString();
    }

    public static String getHotelBaseInfoUrl(HotelBaseInfoConfig hotelBaseInfoConfig,String suffix){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://").append(hotelBaseInfoConfig.getIp())
                .append(":").append(hotelBaseInfoConfig.getPort())
                .append("/").append(hotelBaseInfoConfig.getServer())
                .append("/").append(suffix);
        return stringBuilder.toString();
    }

    public static String getOrderOperateUrl(OrderOperateConfig orderOperateConfig){
        StringBuilder stringBuilder = new StringBuilder();
        if(StringUtil.isValidString(orderOperateConfig.getDomain())){
            stringBuilder.append("http://").append(orderOperateConfig.getDomain())
                         .append("/").append(orderOperateConfig.getServer())
                         .append("/").append(orderOperateConfig.getInterfaceName());
        }else{
            stringBuilder.append("http://").append(orderOperateConfig.getIp())
                         .append(":").append(orderOperateConfig.getPort())
                         .append("/").append(orderOperateConfig.getServer())
                         .append("/").append(orderOperateConfig.getInterfaceName());
        }
        return stringBuilder.toString();
    }
}
