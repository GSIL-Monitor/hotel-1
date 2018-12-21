package com.fangcang.b2b.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 11:06
 * @Description: 获取商圈RequestDTO
 */
@Data
public class BusinessZoneRequestDTO implements Serializable {

    private static final long serialVersionUID = 5419850189012819517L;

    /**
     * 城市编码
     */
    @NotEmpty(message = "城市编码不能为null")
    private String cityCode;
}
