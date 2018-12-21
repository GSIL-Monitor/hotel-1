package com.fangcang.agent.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class AddBankCardRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -3915157452518806984L;

    /**
     * 银行卡id
     */
    private Integer bankCardId;

    /**
     * 开户行
     */
    @NotEmpty(message = "openingBank不能为空")
    private String openingBank;

    /**
     * 开户名
     */
    @NotEmpty(message = "accountName不能为空")
    private String accountName;

    /**
     * 账号
     */
    @NotEmpty(message = "accountNumber不能为空")
    private String accountNumber;

    /**
     * 用户ID
     */
    @NotNull(message = "agentId不能为空")
    private Long agentId;
}
