package com.fangcang.base.request;

import com.fangcang.base.dto.CommonCityDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateCommonCityDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private List<CommonCityDTO> commonCityList;

    private String merchantCode;

    private String operator;
}
