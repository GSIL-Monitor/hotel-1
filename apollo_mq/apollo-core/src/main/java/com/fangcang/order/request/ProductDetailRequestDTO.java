package com.fangcang.order.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ProductDetailRequestDTO implements Serializable {
    private static final long serialVersionUID = -5807714405222702483L;

    /**
     * 供应产品ID
     */
    @NotNull
    private Integer supplyProductId;
}
