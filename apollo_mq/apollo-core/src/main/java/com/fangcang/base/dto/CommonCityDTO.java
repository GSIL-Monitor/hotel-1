package com.fangcang.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonCityDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private String cityCode;

    private String cityName;

    /**
     * 是否常用：1常用，0不常用
     */
    private Integer isCommon;
}
