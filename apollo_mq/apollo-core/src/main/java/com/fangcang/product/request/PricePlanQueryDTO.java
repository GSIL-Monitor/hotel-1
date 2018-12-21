package com.fangcang.product.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class PricePlanQueryDTO implements Serializable{

    private static final long serialVersionUID = -4856095930832736209L;
    @NotNull(message = "pricePlanId不能为Null")
    private Long pricePlanId;

}
