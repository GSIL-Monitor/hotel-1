package com.fangcang.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/6/20.
 */
@Component
@ConfigurationProperties(prefix = "apollo.increment")
@Data
public class IncrementConfig {

    /**
     * 域名
     */
    private String domain;

    /**
     * 增量推送Ip
     */
    private String ip;

    /**
     * 增量推送端口
     */
    private String port;

    /**
     * 增量推送服务
     */
    private String server;

    /**
     * 接口名称
     */
    private String interfaceName;

    public static final Long DELAY_TIME = 1000L;//1s

    public static final Integer RETRY_NUM = 1;//重试次数
}
