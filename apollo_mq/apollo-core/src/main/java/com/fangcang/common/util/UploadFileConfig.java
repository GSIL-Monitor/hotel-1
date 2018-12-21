package com.fangcang.common.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by ASUS on 2018/6/6.
 */
@Component
@ConfigurationProperties(prefix = "ftp")
@Data
public class UploadFileConfig {
    /**
     * 服务地址
     */
    private String addr;

    /**
     * 端口
     */
    private String port;

    /**
     * 服务器登录名
     */
    private String userName;

    /**
     * 用户登录秘密
     */
    private String passWord;

    /**
     * 图片域名
     */
    private String domainName;

    /**
     * 图片真实路径
     */
    private String realPath;
    
    /**
     * 供应商合同文件路径
     */
    private String supplyContractPath;
    
    /**
     * 分销商合同文件路径
     */
    private String agentContractPath;
    
    /**
     * 文件目录前缀
     */
    private String fileDirpRefix;
    
    /**
     * 文件url前缀
     */
    private String fileUrlRefix;
    
}
