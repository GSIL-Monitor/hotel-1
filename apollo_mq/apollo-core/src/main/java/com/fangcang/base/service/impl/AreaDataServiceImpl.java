package com.fangcang.base.service.impl;

import com.fangcang.base.constant.AreaDataType;
import com.fangcang.base.domain.AreaDataDO;
import com.fangcang.base.mapper.AreaDataMapper;
import com.fangcang.base.response.BusinessTreeDTO;
import com.fangcang.base.response.CityTreeDTO;
import com.fangcang.base.response.CountryTreeDTO;
import com.fangcang.base.response.DistrictTreeDTO;
import com.fangcang.base.response.ProvinceTreeDTO;
import com.fangcang.base.service.AreaDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AreaDataServiceImpl implements AreaDataService {

    @Autowired
    private AreaDataMapper areaDataMapper;

    @Override
    public CountryTreeDTO queryAllAreaData() {
        CountryTreeDTO country = new CountryTreeDTO();
        List<AreaDataDO> areaDataDOList = areaDataMapper.queryTreeAreaData(AreaDataType.COUNTRY,AreaDataType.CN);

        for (AreaDataDO baseData : areaDataDOList) {
            int dataType = baseData.getType();
            switch (dataType) {
                case AreaDataType.COUNTRY: {
                    BeanUtils.copyProperties(baseData,country);
                    break;
                }
                case AreaDataType.PROVINCE: {
                    ProvinceTreeDTO province = new ProvinceTreeDTO();
                    BeanUtils.copyProperties(baseData,province);

                    province.setCountryCode(country.getDataCode());

                    Map<String, ProvinceTreeDTO> provinceMap = country.getProvinceMap();
                    provinceMap.put(province.getDataCode(), province);
                    country.setProvinceMap(provinceMap);
                    break;
                }
                case AreaDataType.CITY: {
                    CityTreeDTO city = new CityTreeDTO();
                    long parentId = baseData.getParentId();
                    Map<String, ProvinceTreeDTO> provinceMap = country.getProvinceMap();

                    for (Map.Entry<String, ProvinceTreeDTO> entry : provinceMap.entrySet()) {
                        ProvinceTreeDTO province = entry.getValue();
                        if (parentId == province.getId()) {
                            BeanUtils.copyProperties(baseData,city);
                            city.setProvinceCode(entry.getKey());
                            province.getCityMap().put(city.getDataCode(), city);
                            break;
                        }
                    }
                    break;
                }
                case AreaDataType.DISTRICT: {
                    DistrictTreeDTO district = new DistrictTreeDTO();
                    long parentId = baseData.getParentId();
                    Map<String, ProvinceTreeDTO> provinceMap = country.getProvinceMap();
                    boolean flag = false;

                    for (Map.Entry<String, ProvinceTreeDTO> provinceEntry : provinceMap.entrySet()) {
                        ProvinceTreeDTO province = provinceEntry.getValue();
                        for (Map.Entry<String, CityTreeDTO> cityEntry : province.getCityMap().entrySet()) {
                            CityTreeDTO city = cityEntry.getValue();
                            if (parentId == city.getId()) {
                                BeanUtils.copyProperties(baseData,district);
                                district.setCityCode(cityEntry.getKey());
                                city.getDistrictMap().put(district.getDataCode(), district);
                                flag = true;
                                break;
                            }
                        }
                        if (flag){
                            break;
                        }
                    }
                    break;
                }
                case AreaDataType.BUSINESS: {
                    BusinessTreeDTO business = new BusinessTreeDTO();
                    long parentId = baseData.getParentId();
                    Map<String, ProvinceTreeDTO> provinceMap = country.getProvinceMap();
                    boolean flag = false;

                    for (Map.Entry<String, ProvinceTreeDTO> provinceEntry : provinceMap.entrySet()) {
                        ProvinceTreeDTO province = provinceEntry.getValue();
                        for (Map.Entry<String, CityTreeDTO> cityEntry : province.getCityMap().entrySet()) {
                            CityTreeDTO city = cityEntry.getValue();
                            if (parentId == city.getId()) {
                                BeanUtils.copyProperties(baseData,business);
                                business.setCityCode(cityEntry.getKey());
                                city.getBusinessMap().put(business.getDataCode(), business);
                                flag = true;
                                break;
                            }
                        }
                        if (flag){
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return country;
    }

    @Override
    public Map<String, CountryTreeDTO> queryAllContryData() {
        Map<String, CountryTreeDTO> countryMap = new HashMap<String, CountryTreeDTO>();

        List<AreaDataDO> areaDataDOList = areaDataMapper.selectAll();
        for (AreaDataDO baseData : areaDataDOList) {
            int dataType = baseData.getType();
            switch (dataType) {
                case AreaDataType.COUNTRY: {
                    CountryTreeDTO country = new CountryTreeDTO();
                    BeanUtils.copyProperties(baseData,country);
                    countryMap.put(country.getDataCode(), country);
                    break;
                }
                case AreaDataType.PROVINCE: {
                    ProvinceTreeDTO province = new ProvinceTreeDTO();
                    long parentId = baseData.getParentId();
                    for (Map.Entry<String, CountryTreeDTO> entry : countryMap.entrySet()) {
                        CountryTreeDTO country = entry.getValue();
                        if (parentId == country.getId()) {
                            BeanUtils.copyProperties(baseData,province);
                            province.setCountryCode(country.getDataCode());
                            Map<String, ProvinceTreeDTO> provinceMap = country.getProvinceMap();
                            provinceMap.put(province.getDataCode(), province);
                            country.setProvinceMap(provinceMap);
                            break;
                        }
                    }
                    break;
                }
                case AreaDataType.CITY: {
                    CityTreeDTO city = new CityTreeDTO();
                    long parentId = baseData.getParentId();
                    boolean flag = false;

                    for (Map.Entry<String, CountryTreeDTO> entry : countryMap.entrySet()) {
                        CountryTreeDTO country = entry.getValue();
                        for (Map.Entry<String, ProvinceTreeDTO> entryProvince : country.getProvinceMap().entrySet()) {
                            ProvinceTreeDTO province = entryProvince.getValue();
                            if (parentId == province.getId()) {
                                BeanUtils.copyProperties(baseData,city);
                                city.setProvinceCode(entryProvince.getKey());
                                province.getCityMap().put(city.getDataCode(), city);
                                flag = true;
                                break;
                            }
                        }
                        if (flag){
                            break;
                        }
                    }
                    break;
                }
                case AreaDataType.DISTRICT: {
                    DistrictTreeDTO district = new DistrictTreeDTO();
                    long parentId = baseData.getParentId();
                    boolean flag = false;
                    for (Map.Entry<String, CountryTreeDTO> entry : countryMap.entrySet()) {
                        for (Map.Entry<String, ProvinceTreeDTO> provinceEntry : entry.getValue().getProvinceMap().entrySet()) {
                            ProvinceTreeDTO province = provinceEntry.getValue();
                            for (Map.Entry<String, CityTreeDTO> cityEntry : province.getCityMap().entrySet()) {
                                CityTreeDTO city = cityEntry.getValue();
                                if (parentId == city.getId()) {
                                    BeanUtils.copyProperties(baseData,district);
                                    district.setCityCode(cityEntry.getKey());
                                    city.getDistrictMap().put(district.getDataCode(), district);
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag){
                                break;
                            }
                        }
                        if (flag){
                            break;
                        }
                    }
                    break;
                }
                case AreaDataType.BUSINESS: {
                    BusinessTreeDTO business = new BusinessTreeDTO();
                    long parentId = baseData.getParentId();
                    boolean flag = false;
                    for (Map.Entry<String, CountryTreeDTO> entry : countryMap.entrySet()) {
                        for (Map.Entry<String, ProvinceTreeDTO> provinceEntry : entry.getValue().getProvinceMap().entrySet()) {
                            ProvinceTreeDTO province = provinceEntry.getValue();
                            for (Map.Entry<String, CityTreeDTO> cityEntry : province.getCityMap().entrySet()) {
                                CityTreeDTO city = cityEntry.getValue();
                                if (parentId == city.getId()) {
                                    BeanUtils.copyProperties(baseData,business);
                                    business.setCityCode(cityEntry.getKey());

                                    city.getBusinessMap().put(business.getDataCode(), business);

                                    flag = true;
                                    break;
                                }
                            }
                            if (flag){
                                break;
                            }
                        }
                        if (flag){
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return countryMap;
    }

    @Override
    public List<CityTreeDTO> queryAllCityData() {
        List<CityTreeDTO> cityList = new ArrayList<>();

        List<AreaDataDO> areaDataDOList = areaDataMapper.queryTreeAreaData(AreaDataType.CITY,null);

        for (AreaDataDO baseData : areaDataDOList) {
            int dataType = baseData.getType();

            switch (dataType) {
                case AreaDataType.CITY: {
                    CityTreeDTO city = new CityTreeDTO();
                    BeanUtils.copyProperties(baseData,city);
                    cityList.add(city);
                    break;
                }
                case AreaDataType.DISTRICT: {
                    DistrictTreeDTO district = new DistrictTreeDTO();
                    long parentId = baseData.getParentId();
                    for (CityTreeDTO city : cityList) {
                        if (parentId == city.getId()) {
                            BeanUtils.copyProperties(baseData,district);
                            district.setCityCode(city.getDataCode());
                            city.getDistrictMap().put(district.getDataCode(), district);
                            break;
                        }
                    }
                    break;
                }
                case AreaDataType.BUSINESS: {
                    BusinessTreeDTO business = new BusinessTreeDTO();
                    long parentId = baseData.getParentId();
                    for (CityTreeDTO city : cityList) {
                        if (parentId == city.getId()) {
                            BeanUtils.copyProperties(baseData,business);
                            business.setCityCode(city.getDataCode());
                            city.getBusinessMap().put(business.getDataCode(), business);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return cityList;
    }

    @Override
    public List<CityTreeDTO> queryCityDataByProvinceCode(String provinceCode) {
        List<CityTreeDTO> cityList = new ArrayList<CityTreeDTO>();

        List<AreaDataDO> areaDataDOList = areaDataMapper.queryTreeAreaData(AreaDataType.PROVINCE,provinceCode);

        for (AreaDataDO baseData : areaDataDOList) {
            int dataType = baseData.getType();
            switch (dataType) {
                case AreaDataType.CITY: {
                    CityTreeDTO city = new CityTreeDTO();
                    BeanUtils.copyProperties(baseData,city);
                    cityList.add(city);
                    break;
                }
                case AreaDataType.DISTRICT: {
                    long parentId = baseData.getParentId();
                    for (CityTreeDTO city : cityList) {
                        if (parentId == city.getId()) {
                            DistrictTreeDTO district = new DistrictTreeDTO();
                            BeanUtils.copyProperties(baseData,district);
                            district.setCityCode(city.getDataCode());
                            city.getDistrictMap().put(district.getDataCode(), district);
                            break;
                        }
                    }
                    break;
                }
                case AreaDataType.BUSINESS: {
                    long parentId = baseData.getParentId();
                    for (CityTreeDTO city : cityList) {
                        if (parentId == city.getId()) {
                            BusinessTreeDTO business = new BusinessTreeDTO();
                            BeanUtils.copyProperties(baseData,business);
                            business.setCityCode(city.getDataCode());
                            city.getBusinessMap().put(business.getDataCode(), business);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return cityList;
    }

    @Override
    public CityTreeDTO queryCityDataByCityCode(String cityCode) {
        CityTreeDTO city = new CityTreeDTO();

        List<AreaDataDO> areaDataDOList = areaDataMapper.queryTreeAreaData(AreaDataType.CITY,cityCode);

        for (AreaDataDO baseData : areaDataDOList) {
            int dataType = baseData.getType();
            switch (dataType) {
                case AreaDataType.CITY: {
                    BeanUtils.copyProperties(baseData,city);
                    break;
                }
                case AreaDataType.DISTRICT: {
                    DistrictTreeDTO district = new DistrictTreeDTO();
                    BeanUtils.copyProperties(baseData,district);
                    district.setCityCode(city.getDataCode());
                    city.getDistrictMap().put(district.getDataCode(), district);
                    break;
                }
                case AreaDataType.BUSINESS: {
                    BusinessTreeDTO business = new BusinessTreeDTO();
                    BeanUtils.copyProperties(baseData,business);
                    business.setCityCode(city.getDataCode());
                    city.getBusinessMap().put(business.getDataCode(), business);
                    break;
                }
            }
        }
        return city;
    }
}
