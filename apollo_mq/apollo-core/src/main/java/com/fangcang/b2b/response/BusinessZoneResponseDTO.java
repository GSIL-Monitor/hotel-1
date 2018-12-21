package com.fangcang.b2b.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 11:08
 * @Description: 商圈ResponseDTO
 */
@Data
public class BusinessZoneResponseDTO implements Serializable {

    private static final long serialVersionUID = 6370786120912107952L;

    /**
     * 商圈区编码
     */
    private String businessCode;

    /**
     * 商圈区名称
     */
    private String businessName;
}
