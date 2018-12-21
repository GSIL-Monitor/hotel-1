package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/2.
 */
@Data
public class DebuctQuotaQueryDTO extends BaseDTO implements Serializable {

    @NotEmpty(message = "orderCode不能为Null")
    private String orderCode;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;
}
