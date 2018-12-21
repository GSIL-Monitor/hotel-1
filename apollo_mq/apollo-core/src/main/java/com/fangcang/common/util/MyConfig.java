package com.fangcang.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fangcang") 
@PropertySource("classpath:/application.properties")
@Data
public class MyConfig {

	private String addr;

	private String port;

	private String userName;

	private String passWord;

	private String realmName;
	
	private String supplyContractPath;
	
	private String agentContractPath;
}
