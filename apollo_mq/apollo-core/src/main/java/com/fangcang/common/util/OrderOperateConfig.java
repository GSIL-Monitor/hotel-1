package com.fangcang.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhanwang
 */
@Component
@ConfigurationProperties(prefix = "apollo.dltorder")
@Data
public class OrderOperateConfig {

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

}
