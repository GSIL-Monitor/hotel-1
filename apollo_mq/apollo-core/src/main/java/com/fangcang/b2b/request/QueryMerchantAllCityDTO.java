package com.fangcang.b2b.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/17 09:47
 * @Description: B2B模糊查询商家所有城市
 */
@Data
public class QueryMerchantAllCityDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = 8443925739177306296L;

    /**
     * 商家编码(Session获取)
     */
    private String merchantCode;

    /**
     * 城市名(模糊匹配用)
     */
    private String cityName;

    /**
     * 国家编码,默认中国
     */
    private String countryCode = "CN";
}
