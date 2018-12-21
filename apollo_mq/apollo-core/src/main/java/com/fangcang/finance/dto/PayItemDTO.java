package com.fangcang.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayItemDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer id;

    /**
     * 类型：1分销商现金,2分销商押金,3供应商现金,4供应商押金
     */
    private Integer payItemType;

    /**
     * 关联id
     */
    private Integer relationId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 折合金额
     */
    private BigDecimal exchangeAmount;

    /**
     * 折合币种
     */
    private Integer exchangeCurrency;

    /**
     * 到账日期
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
     * 分销商或供应商银行名称
     */
    private String orgBankName;

    /**
     * 分销商或供应商银行账户
     */
    private String orgBankAccount;

    /**
     * 商家银行id
     */
    private Integer merchantBankId;

    /**
     * 商家银行名称
     */
    private String merchantBankName;

    /**
     * 商家银行账户
     */
    private String merchantBankAccount;

    /**
     * 凭证附件
     */
    private List<PayItemAttchDTO> payItemAttchDTOList;
}
