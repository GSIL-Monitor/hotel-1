package com.fangcang.order.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class SupplyDetailRequestDTO implements Serializable {
    private static final long serialVersionUID = 8975102263411132924L;

    /**
     * 供货单id
     */
    @NotNull
    private Integer supplyOrderId;
}
