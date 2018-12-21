package com.fangcang.finance.financeorder.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Vinney on 2018/7/10.
 */
@Data
public class ConfirmFinanceOrderResponseDTO implements Serializable {

    /**
     * 供应商编码/分销商名称
     */
    private String orgCode;

    private String orderCode;

    private String merchantCode;

    /**
     * 工单ID
     */
    private Integer financeOrderId;

    private Integer financeType;

    /**
     * 通知金额
     */
    private BigDecimal notifyAmount;

    /**
     * 转账记录
     */
    private List<VoucherResponseDTO> voucherList;

    /**
     * 通知币种
     */
    private String currency;


    /**
     * 账单名称
     */
    private String billName;

    /**
     * 操作日志
     */
    private List<FinanceOrderLogResponseDTO> financeOrderlogList;



}
