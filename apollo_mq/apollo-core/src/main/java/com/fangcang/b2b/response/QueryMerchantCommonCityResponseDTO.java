package com.fangcang.b2b.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 17:04
 * @Description: 根据商家code查询常用城市ResponseDTO
 */
@Data
public class QueryMerchantCommonCityResponseDTO implements Serializable {

    private static final long serialVersionUID = 5029510885357342763L;

    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;
}
