package com.fangcang.base.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CityTreeDTO extends AreaDataDTO {
	private static final long serialVersionUID = -7507249816905596510L;

	/**
	 * 城市所属省份code
	 */
	private String provinceCode;
	
	/**
	 * 城市所包含的城区 Map<城区编码,城区 >
	 */
	private Map<String,DistrictTreeDTO> districtMap;
	
	/**
	 * 城市所包含的商业区 Map<商业区编码,商业区>
	 */
	private Map<String,BusinessTreeDTO> businessMap;

	public Map<String, DistrictTreeDTO> getDistrictMap() {
		if(null == districtMap) districtMap = new HashMap<String,DistrictTreeDTO>();
		return districtMap;
	}

	public Map<String, BusinessTreeDTO> getBusinessMap() {
		if(null == businessMap) businessMap = new HashMap<String,BusinessTreeDTO>();
		return businessMap;
	}
}
