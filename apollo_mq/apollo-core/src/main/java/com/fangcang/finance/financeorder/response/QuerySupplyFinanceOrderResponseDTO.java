package com.fangcang.finance.financeorder.response;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Vinney on 2018/7/19.
 */
@Data
public class QuerySupplyFinanceOrderResponseDTO extends BaseDTO {
    /**
     * 财务工单号
     */
    private String financeCode;
    /**
     * 通知金额
     */
    private BigDecimal notifyAmount;

    /**
     * 币种
     */
    private String currency;

    /**
     *  工单状态
     */
    private Integer financeStatus;

    /**
     * 转账记录
     */
    private List<VoucherResponseDTO> voucherList;

    /**
     * 通知类型  financeTypeEnum
     */
    private Integer financeType;

    private Integer financeOrderId;

    private String orderCode;

}
