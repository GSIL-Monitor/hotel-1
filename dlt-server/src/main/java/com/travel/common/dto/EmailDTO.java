package com.travel.common.dto;

import java.io.Serializable;
import java.util.Properties;

import lombok.Data;

/**
 * @Description : 发邮件信息
 * @author : Zhiping Sun
 * @date : 2018年2月28日上午9:55:46
 */
@Data
public class EmailDTO implements Serializable {
	
	private static final long serialVersionUID = -6818207444930358544L;

	/**
	 * 发件人地址
	 */
	private String from;
	
	/**
	 * 邮件接收人地址
	 */
	private String to;
	
	/**
	 * 抄送地址
	 */
	private String cc;
	
	/**
	 * 邮件主题
	 */
	private String subject;
	
	/**
	 * 邮件内容
	 */
	private String content;
	
	/**
	 * 发件服务器ip
	 */
	private String host;
	
	/**
	 * 发件服务器端口
	 */
	private String port;
	
	/**
	 * 发件人邮箱登陆名
	 */
	private String userName;
	
	/**
	 * 发件人邮箱登陆密码
	 */
	private String password;
	
	/**
     * 获得邮件会话属性
     */
	public Properties getProperties(){
		Properties prop = new Properties();
		prop.put("mail.smtp.host", this.host);
		prop.put("mail.smtp.port", this.port);
		//读写超时时间
		prop.put("mail.smtp.timeout", 20000);
		//连接超时时间
		prop.put("mail.smtp.connectiontimeout", 20000);
		prop.put("mail.smtp.auth", "true");
		return prop;
    }

}
