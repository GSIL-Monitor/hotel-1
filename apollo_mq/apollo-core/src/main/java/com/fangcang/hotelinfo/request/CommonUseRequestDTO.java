package com.fangcang.hotelinfo.request;

import java.io.Serializable;

import com.fangcang.common.BaseDTO;
import lombok.Data;

@Data
public class CommonUseRequestDTO extends BaseDTO implements Serializable{
	 
	private static final long serialVersionUID = 5193125615119903448L;
	/**
	 * 酒店Id
	 */
	private Long hotelId;
	/**
	 * 是否常用(1 常用 0 不常用)
	 */
	private Integer isCommonUsed;
	/**
	 * 是否常用ID
	 */
	private Long id;
	/**
	 * 商家编码
	 */
	private String merchantCode;
	
	/**
	 * 推荐等级
	 */
    private  Integer recommendedLevel;
}
