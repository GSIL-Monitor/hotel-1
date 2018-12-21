package com.travel.common.dto.order.request;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 订单确认
 * @author : Zhiping Sun
 * @date : 2018年2月28日下午6:33:45
 */
@Data
public class OrderConfirmDTO extends ModifyDTO {
	
	private static final long serialVersionUID = -390733242395342778L;

	/**
	 * 订单id
	 */
	private Long orderId;
	
	/**
	 * 订单编码
	 */
	private String orderCode;
	
	/**
	 * 确认类型
	 */
	private String confirmType;
	
	/**
	 * 发件服务器
	 */
	private String emailHost;
	
	/**
	 * 发件端口
	 */
	private String emailPort;
	
	/**
	 * 邮件发送人
	 */
	private String emailFrom;
	
	/**
	 * 邮件接收人
	 */
	private String emailTo;
	
	/**
	 * 邮件主题
	 */
	private String emailSubject;
	
	/**
	 * 发件邮箱密码
	 */
	private String passWord;
	
	/**
	 * logo预览地址
	 */
	private String logoPath;
	
	/**
	 * 电子章地址
	 */
	private String signetPath;
	
	/**
	 * 邮件中显示logo图片的html变量
	 */
	private String logoHtmlTarget;
	
	/**
	 * 邮件中显示电子章图片的html变量
	 */
	private String signetHtmlTarget;

}
