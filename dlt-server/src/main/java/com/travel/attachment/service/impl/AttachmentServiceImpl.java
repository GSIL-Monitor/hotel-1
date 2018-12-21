package com.travel.attachment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.attachment.dao.AttachmentMapper;
import com.travel.attachment.service.AttachmentService;
import com.travel.common.dto.AttachmentDTO;

/**
 * @Description : 附件管理业务接口实现
 * @author : Zhiping Sun
 * @date : 2018年3月9日上午9:16:48
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public void saveAttachment(AttachmentDTO attachment) {
		this.attachmentMapper.saveAttachment(attachment);
	}
	
	@Override
	public void removeAttachmentById(String attachmentId) {
		this.attachmentMapper.deleteAttachmentById(attachmentId);
	}
	
	@Override
	public AttachmentDTO getAttachmentById(String attachmentId) {
		return this.attachmentMapper.queryAttachmentById(attachmentId);
	}

	@Override
	public List<AttachmentDTO> listAttachmentList(Long businessId) {
		return this.attachmentMapper.queryAttachmentList(businessId);
	}

}
