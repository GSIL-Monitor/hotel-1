package com.fangcang.base.response;

import lombok.Data;

@Data
public class DistrictTreeDTO extends AreaDataDTO {
	private static final long serialVersionUID = 3957200071338342424L;
	
	/**
	 * 城区所属城市编码
	 */
	private String cityCode;
}
