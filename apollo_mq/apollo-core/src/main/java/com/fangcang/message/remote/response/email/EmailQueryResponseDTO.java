package com.fangcang.message.remote.response.email;

import java.io.Serializable;
import java.util.List;

public class EmailQueryResponseDTO implements Serializable{

	private static final long serialVersionUID = 8564607942723101159L;
	
	List<EmailInfoDTO> emailInfoList;

	public List<EmailInfoDTO> getEmailInfoList() {
		return emailInfoList;
	}

	public void setEmailInfoList(List<EmailInfoDTO> emailInfoList) {
		this.emailInfoList = emailInfoList;
	}
	
	
}
