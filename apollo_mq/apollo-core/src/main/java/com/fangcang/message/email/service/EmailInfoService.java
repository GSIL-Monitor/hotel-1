package com.fangcang.message.email.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.message.remote.request.email.AddEmailConfigDTO;
import com.fangcang.message.remote.request.email.EmailQueryRequestDTO;
import com.fangcang.message.remote.request.email.EmailSendRequestDTO;
import com.fangcang.message.remote.request.email.QueryEmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailInfoDTO;

import java.util.List;

public interface EmailInfoService {
	
	/**
	 * 发送邮件
	 * @param request
	 * @return
	 */
	public ResponseDTO sendEmail(EmailSendRequestDTO request);
	
	/**
	 * 查询邮件记录
	 * @param requestDTO
	 */
	public List<EmailInfoDTO> queryEmailInfos(EmailQueryRequestDTO requestDTO);

	/**
	 * 保存邮件配置
	 * @param requestDTO
     */
	public ResponseDTO addEmailConfig(AddEmailConfigDTO requestDTO);

	/**
	 * 查询邮件配置
	 * @param requestDTO
     */
	public List<EmailConfigDTO> queryEmailConfig(QueryEmailConfigDTO requestDTO);
}
