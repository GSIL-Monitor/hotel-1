package com.fangcang.message.weixin.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件工具类
 */
@Slf4j
public class ConfigUtils {

	private static Properties props = new Properties();

	static {
		try {
			props.load(ConfigUtils.class.getResourceAsStream("/config/weixin/weixin_service_no_error.properties"));
		} catch (FileNotFoundException e) {
			log.error("weixin_service_no_error配置文件不存在！", e);
		} catch (IOException e) {
			log.error("读取weixin_service_no_error配置文件IO异常！", e);
		} catch (Exception e){
			log.error("读取weixin_service_no_error配置文件失败！", e);
		}
	}

	public static String getConfigValue(String key) {
		return props.getProperty(key);
	}

    public static void setProps(Properties p){
        props = p;
    }
    
}
