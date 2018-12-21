package com.fangcang.finance.prepay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class PrepayRechargeItemDTO implements Serializable {
    private static final long serialVersionUID = -5620925109852889806L;

    private Integer id;

    /**
     * 合约id
     */
    @NotNull
    private Integer contractId;

    /**
     * 金额
     */
    @NotNull
    private BigDecimal amount;

    /**
     * 币种
     */
    @NotBlank
    private String currency;

    /**
     * 折合商家本币金额
     */
    private BigDecimal exchangeAmount;

    /**
     * 商家本币币种
     */
    private String exchangeCurrency;

    /**
     * 到账时间
     */
    private String arrivalDate;

    /**
     * 凭证单号
     */
    private String serialNo;

    /**
     * 备注
     */
    private String note;

    /**
     * 供应商开户行
     */
    private String orgBankName;

    /**
     * 供应商银行账户
     */
    private String orgBankAccount;

    /**
     * 商家银行卡id
     */
    private Integer merchantBankId;

    /**
     * 商家开户行
     */
    private String merchantBankName;

    /**
     * 商家银行账户
     */
    private String merchantBankAccount;

    private String creator;

    private String createTime;

    private String modifier;

    private Date modifyTime;

}