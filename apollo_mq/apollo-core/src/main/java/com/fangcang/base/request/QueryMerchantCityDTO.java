package com.fangcang.base.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryMerchantCityDTO extends BaseQueryConditionDTO {

    private String cityCode;

    private String cityName;

    private String merchantCode;
}
