package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ChangeBalanceMethodRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 3941851355357007187L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;

    /**
     * 分销商结算方式
     */
    @NotNull
    private Integer balanceMethod;

}
