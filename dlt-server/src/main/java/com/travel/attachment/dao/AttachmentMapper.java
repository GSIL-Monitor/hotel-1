package com.travel.attachment.dao;

import java.util.List;

import com.travel.common.dto.AttachmentDTO;

/**
 * @Description : 附件管理数据操作接口
 * @author : Zhiping Sun
 * @date : 2018年3月9日上午9:18:02
 */
public interface AttachmentMapper {

	/**
	 * 保存附件
	 * @param attachment
	 */
	public void saveAttachment(AttachmentDTO attachment);
	
	/**
	 * 删除附件
	 * @param attachmentId
	 */
	public void deleteAttachmentById(String attachmentId);
	
	/**
	 * 查询附件
	 * @param attachmentId
	 */
	public AttachmentDTO queryAttachmentById(String attachmentId);
	
	/**
	 * 查询附件
	 * @param businessId
	 * @return
	 */
	public List<AttachmentDTO> queryAttachmentList(Long businessId);
}
