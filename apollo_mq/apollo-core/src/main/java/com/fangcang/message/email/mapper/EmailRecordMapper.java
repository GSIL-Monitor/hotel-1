package com.fangcang.message.email.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.message.email.domain.EmailRecordDO;
import com.fangcang.message.remote.response.email.EmailInfoDTO;

import java.util.List;
import java.util.Map;

public interface EmailRecordMapper extends MyMapper<EmailRecordDO> {
	
	/**
	 * 查询邮件记录
	 */
	public List<EmailInfoDTO> queryEmailRecords(Map<String, Object> paramMap);
}
