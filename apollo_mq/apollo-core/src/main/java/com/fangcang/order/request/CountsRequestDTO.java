package com.fangcang.order.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class CountsRequestDTO implements Serializable {


    private static final long serialVersionUID = -8666987935458038355L;
    /**
     * 订单列表类型： 1，B2B订单列表， 2：OTA订单列表
     */
    @NotNull
    private Integer listType;

    /**
     * 商家编码
     */
    private String merchantCode;


}
