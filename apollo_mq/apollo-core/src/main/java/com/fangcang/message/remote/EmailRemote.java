package com.fangcang.message.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.request.email.AddEmailConfigDTO;
import com.fangcang.message.remote.request.email.EmailQueryRequestDTO;
import com.fangcang.message.remote.request.email.EmailSendRequestDTO;
import com.fangcang.message.remote.request.email.QueryEmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailQueryResponseDTO;

import java.util.List;

public interface EmailRemote {

	/**
	 * 发送邮件
	 * @param request 邮件发送请求对象
	 * @return BaseResponseDTO
	 */
	public ResponseDTO sendEmail(EmailSendRequestDTO request);
	
	/**
	 * 查询邮件信息
	 * @param request 邮件信息查询请求对象
	 * @return EmailQueryResponseDTO
	 */
	public ResponseDTO<EmailQueryResponseDTO> queryEmailInfo(EmailQueryRequestDTO request);

	/**
	 * 保存邮件配置
	 * @param requestDTO
	 */
	public ResponseDTO addEmailConfig(AddEmailConfigDTO requestDTO);

	/**
	 * 查询邮件配置
	 * @param requestDTO
	 */
	public ResponseDTO<List<EmailConfigDTO>> queryEmailConfig(QueryEmailConfigDTO requestDTO);
}
