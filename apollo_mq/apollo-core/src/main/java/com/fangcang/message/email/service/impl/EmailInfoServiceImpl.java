package com.fangcang.message.email.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.message.email.domain.EmailConfigDO;
import com.fangcang.message.email.domain.EmailRecordDO;
import com.fangcang.message.email.mapper.EmailConfigMapper;
import com.fangcang.message.email.mapper.EmailRecordMapper;
import com.fangcang.message.email.service.EmailInfoService;
import com.fangcang.message.email.util.EmailUtils;
import com.fangcang.message.remote.request.email.AddEmailConfigDTO;
import com.fangcang.message.remote.request.email.EmailQueryRequestDTO;
import com.fangcang.message.remote.request.email.EmailSendRequestDTO;
import com.fangcang.message.remote.request.email.QueryEmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailConfigDTO;
import com.fangcang.message.remote.response.email.EmailInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("emailInfoService")
public class EmailInfoServiceImpl implements EmailInfoService {

	@Autowired
	private EmailRecordMapper emailRecordMapper;

	@Autowired
	private EmailConfigMapper emailConfigMapper;

	@Override
	public ResponseDTO sendEmail(EmailSendRequestDTO request) {
		log.info("begin to EmailInfoServiceImpl.sendEmail send");
		ResponseDTO response = new ResponseDTO();
		try{
			log.info("send template......");
			boolean result = EmailUtils.send(request);
			response.setResult(result ? ResultCodeEnum.SUCCESS.code:ResultCodeEnum.FAILURE.code);
		}catch(Exception e){
			log.error("调用发邮件消息服务异常",e);
			response.setResult(ResultCodeEnum.FAILURE.code);
			response.setFailReason("调用发邮件消息服务异常");
		}
		EmailRecordDO emailInfo = new EmailRecordDO();
		BeanUtils.copyProperties(request,emailInfo);
		emailInfo.setSendResult(response.getResult()==ResultCodeEnum.SUCCESS.code);
		emailInfo.setSendTime(new Date());
		emailRecordMapper.insert(emailInfo);

		log.info("end to EmailInfoServiceImpl.sendEmail send");
		return response;
	}

	@Override
	public List<EmailInfoDTO> queryEmailInfos(EmailQueryRequestDTO requestDTO) {
		log.info("begin to EmailInfoServiceImpl.queryEmailInfos send");
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("from", requestDTO.getFrom());
		paramMap.put("toArray", requestDTO.getToArray());
		paramMap.put("emailType", requestDTO.getEmailType());
		paramMap.put("sendResult", requestDTO.isSendResult());
		paramMap.put("beginTime", requestDTO.getBeginTime());
		paramMap.put("endTime", requestDTO.getEndTime());
		
		List<EmailInfoDTO> emails = emailRecordMapper.queryEmailRecords(paramMap);
		
		log.info("end to EmailInfoServiceImpl.queryEmailInfos send");
		return emails;
	}

	@Override
	public ResponseDTO addEmailConfig(AddEmailConfigDTO requestDTO) {
		EmailConfigDO emailConfigDO=new EmailConfigDO();
		BeanUtils.copyProperties(requestDTO,emailConfigDO);
		emailConfigMapper.insert(emailConfigDO);
		return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
	}

	@Override
	public List<EmailConfigDTO> queryEmailConfig(QueryEmailConfigDTO requestDTO) {
		EmailConfigDO emailConfigParam=new EmailConfigDO();
		emailConfigParam.setUserName(requestDTO.getUserName());
		List<EmailConfigDO> emailConfigDOList=emailConfigMapper.select(emailConfigParam);
		List<EmailConfigDTO> emailConfigDTOList=new ArrayList<>();
		for (EmailConfigDO emailConfigDO:emailConfigDOList){
			EmailConfigDTO emailConfigDTO=new EmailConfigDTO();
			emailConfigDTO.setEmailServerHost(emailConfigDO.getEmailServerHost());
			emailConfigDTO.setEmailServerPort(emailConfigDO.getEmailServerPort());
			emailConfigDTO.setUserName(emailConfigDO.getUserName());
			emailConfigDTO.setPassword(emailConfigDO.getPassword());
			emailConfigDTOList.add(emailConfigDTO);
		}
		return emailConfigDTOList;
	}
}
