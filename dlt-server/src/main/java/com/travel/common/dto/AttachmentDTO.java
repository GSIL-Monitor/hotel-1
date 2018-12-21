package com.travel.common.dto;

import java.util.Date;

import lombok.Data;

/**
 * @Description : 附件信息
 * @author : Zhiping Sun
 * @date : 2018年3月5日上午10:20:21
 */
@Data
public class AttachmentDTO extends ModifyDTO {

	private static final long serialVersionUID = 2102402622448887157L;

	/**
	 * 附件id
	 */
	private String attachmentId;
	
	/**
	 * 业务id（如订单id，客户id，供应商id）
	 */
	private Long businessId;
	
	/**
	 * 附件类型
	 */
	private String attachmentType;
	
	/**
	 * 附件类型描述
	 */
	private String attachmentTypeText;
	
	/**
	 * 上传前附件名称
	 */
	private String originalAttachmentName;
	
	/**
	 * 上传后附件名称
	 */
	private String serverAttachmentName;
	
	/**
	 * 附件访问url
	 */
	private String attachmentUrl;
	
	/**
	 * 附件上传路径
	 */
	private String attachmentPath;
	
	/**
	 * 上传时间
	 */
	private Date uploadDate;
}
