package com.fangcang.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/6/25.
 */
@Component
@ConfigurationProperties(prefix = "apollo.hotelbaseinfo")
@Data
public class HotelBaseInfoConfig {

    /**
     * IP
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    /**
     * 服务名称
     */
    private String server;
}
