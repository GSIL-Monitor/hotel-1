package com.fangcang.base.response;

import java.util.HashMap;
import java.util.Map;

public class CountryTreeDTO extends AreaDataDTO {
	private static final long serialVersionUID = -2401312432938071903L;
	/**
	 * 国家下属省份Map
	 * Map<ProvinceCode,ProvinceTreeDTO>
	 */
	private Map<String,ProvinceTreeDTO> provinceMap;
	
	public Map<String, ProvinceTreeDTO> getProvinceMap() {
		if(null == this.provinceMap) this.provinceMap = new HashMap<String,ProvinceTreeDTO>();
		return provinceMap;
	}
	public void setProvinceMap(Map<String, ProvinceTreeDTO> provinceMap) {
		this.provinceMap = provinceMap;
	}
}
