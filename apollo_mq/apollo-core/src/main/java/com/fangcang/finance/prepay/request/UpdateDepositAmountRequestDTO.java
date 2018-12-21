package com.fangcang.finance.prepay.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class UpdateDepositAmountRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 898899742942016824L;
    /**
     * 供应商id
     */
    @NotNull
    private Integer supplyId;
    /**
     * 押金
     */
    @NotNull
    private BigDecimal depositAmount;


}
