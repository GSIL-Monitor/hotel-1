package com.fangcang.finance.financeorder.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Vinney on 2018/7/11.
 */
@Data
public class VoucherRequestDTO implements Serializable {

    /**
     * payItemId
     */
    private Integer id;

    /**
     * 工单ID
     */
    private Integer financeOrderId;

    /**
     * 支付通道
     */
    private Integer passage;

    /**
     * 如果passage=5 预付款，必须传入合约ID
     */
    private Integer contractId;

    /**
     * 打款金额
     */
    private BigDecimal amount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 凭证号
     */
    private String serialNo;

    /**
     * 备注
     */
    private String note;

    /**
     * 分销商/供应商银行名称
     */
    private String orgBankName;

    /**
     * 分销商/供应商银行账号
     */
    private String orgBankAccount;

    private Integer merchantBankId;

    private String merchantBankName;

    /**
     * 到账时间
     */
    private String arrivalDate;

    private String merchantBankAccount;

    /**
     * 凭证附件
     */
    private List<FinancePayItemAttchRequestDTO> financePayItemAttchList;
}
