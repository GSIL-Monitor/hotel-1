package com.travel.attachment.service;

import java.util.List;

import com.travel.common.dto.AttachmentDTO;

/**
 * @Description : 附件管理业务接口
 * @author : Zhiping Sun
 * @date : 2018年3月9日上午9:13:33
 */
public interface AttachmentService {

	/**
	 * 保存附件
	 * @param attachment
	 */
	public void saveAttachment(AttachmentDTO attachment);
	
	/**
	 * 删除附件
	 * @param attachmentId
	 */
	public void removeAttachmentById(String attachmentId);
	
	/**
	 * 查询附件
	 * @param attachmentId
	 */
	public AttachmentDTO getAttachmentById(String attachmentId);
	
	/**
	 * 查询附件
	 * @param businessId
	 * @return
	 */
	public List<AttachmentDTO> listAttachmentList(Long businessId);
}
