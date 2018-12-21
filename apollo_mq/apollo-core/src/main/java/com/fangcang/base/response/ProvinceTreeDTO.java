package com.fangcang.base.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProvinceTreeDTO extends AreaDataDTO {
	private static final long serialVersionUID = -6950436177621615257L;

	/**
	 * 省份所属国家编码
	 */
	private String countryCode;
	
	/**
	 * 省份包含的城市信息
	 */
	private Map<String,CityTreeDTO> cityMap;

	public Map<String, CityTreeDTO> getCityMap() {
		if(null == cityMap) cityMap = new HashMap<String,CityTreeDTO>();
		return cityMap;
	}
}
