package com.fangcang.finance.financeorder.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Vinney on 2018/7/10.
 */
@Data
public class ConfirmFinanceOrderRequestDTO extends BaseDTO implements Serializable {

    private String orderCode;

    /**
     * 供应商编码/分销商名称
     */
    private String orgCode;

    private String merchantCode;

    /**
     * 工单ID
     */
    @NotNull(message = "工单Id不能为空")
    private Integer financeOrderId;

    /**
     * 通知金额
     */
    private BigDecimal notifyAmount;

    /**
     * 通知币种
     */
    private String currency;

    /**
     * 工单类型
     */
    private Integer financeType;

    /**
     * 账单名称
     */
    private String billName;


    /**
     * 转账记录
     */
    private List<VoucherRequestDTO> voucherList;

}
