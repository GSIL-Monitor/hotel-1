package com.fangcang.product.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class ToAddPricePlanQueryDTO implements Serializable{

    private static final long serialVersionUID = -1876077016472187625L;
    /**
     * 酒店ID
     */
    @NotNull(message = "hotelId不能为Null")
    private Long hotelId;

    /**
     * 房型ID
     */
    @NotNull(message = "roomTypeId不能为Null")
    private Long roomTypeId;

    /**
     * 商家编码
     */
    private String merchantCode;
}

