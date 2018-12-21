package com.fangcang.base.response;

import lombok.Data;

@Data
public class BusinessTreeDTO extends AreaDataDTO {
	private static final long serialVersionUID = 1600456906888482602L;
	
	/**
	 * 商业区所属城市编码
	 */
	private String cityCode;
}
