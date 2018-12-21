package com.fangcang.base.service;

import com.fangcang.base.response.CityTreeDTO;
import com.fangcang.base.response.CountryTreeDTO;

import java.util.List;
import java.util.Map;

public interface AreaDataService {

    /**
     * 查询所有国家区域信息
     * @return
     */
    public Map<String,CountryTreeDTO> queryAllContryData();

    /**
     * 查询所有区域信息(只包含中国)
     * @return
     */
    public CountryTreeDTO queryAllAreaData();

    /**
     * 查询所有城市信息 List<CityTreeDTO>
     */
    public List<CityTreeDTO> queryAllCityData();

    /**
     * 根据省份编码查询该省下所有城市信息
     */
    public List<CityTreeDTO> queryCityDataByProvinceCode(String provinceCode);

    /**
     * 根据城市编码查询城市信息
     * @param cityCode
     * @return
     */
    public CityTreeDTO queryCityDataByCityCode(String cityCode);
}
