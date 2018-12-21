package com.fangcang.agent.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class ModifyAmountRequestDTO extends BaseDTO implements Serializable {

    /**
     * 分销商ID
     */
    @NotNull(message = "agentId不能为Null")
    private Long agentId;

    @NotNull(message = "amount不能为Null")
    private BigDecimal amount;
}
