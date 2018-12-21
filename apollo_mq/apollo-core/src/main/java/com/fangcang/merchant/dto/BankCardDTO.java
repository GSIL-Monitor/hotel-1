package com.fangcang.merchant.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;


@Data
public class BankCardDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8374649153207476654L;
    /**
     * 银行卡id
     */
    private Long bankCardId;

    /**
     * 账户类型(1为银行卡 2为支付宝  3微信)
     */
    private Integer type;

    /**
     * 开户行
     */
    private String openingBank;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 真实路径
     */
    private String realPath;
}
