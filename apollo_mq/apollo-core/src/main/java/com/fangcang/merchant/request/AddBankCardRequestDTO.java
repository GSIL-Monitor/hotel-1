package com.fangcang.merchant.request;

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
     * 账户类型(1为银行卡 2为支付宝  3微信)
     */
    @NotNull(message = "type不能为空")
    private Integer type;

    /**
     * 开户行
     */
    @NotEmpty(message = "openingBank不能为空")
    private String openingBank;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 账号
     */
    @NotEmpty(message = "accountNumber不能为空")
    private String accountNumber;

    /**
     * 用户ID
     */
    @NotNull(message = "userId不能为空")
    private Long userId;
    
    private Long bankCardId;

    /**
     * 支付宝微信收款码图片真实路径
     */
    private String realPath;
}
