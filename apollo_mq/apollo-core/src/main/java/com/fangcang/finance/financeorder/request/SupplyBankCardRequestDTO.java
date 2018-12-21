package com.fangcang.finance.financeorder.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Vinney on 2018/7/25.
 */
@Data
public class SupplyBankCardRequestDTO implements Serializable {

    @NotNull(message = "供应商编码不能为空")
    private String supplyCode;

}
