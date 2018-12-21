package com.fangcang.hotelinfo.saas.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PutTypeSassDTO implements Serializable{

	private static final long serialVersionUID = -8870398403690626839L;
	
	/**
	 * 摆放类型对应的键
	 */
	private String putTypeKey;
	
	/**
	 * 摆放类型
	 */
	private String putTypeValue;
	
	/**
	 * 摆放类型适用人数
	 */
	private String peopleNum;
}
