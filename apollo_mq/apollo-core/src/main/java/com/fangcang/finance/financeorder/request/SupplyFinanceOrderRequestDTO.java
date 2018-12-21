package com.fangcang.finance.financeorder.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Vinney on 2018/7/17.
 */
@Data
public class SupplyFinanceOrderRequestDTO extends BaseDTO {
    /**
     * 工单ID
     */
    private Long id ;

    /**
     * 工单编码
     */
    private String financeCode;

    /**
     *工单类型 @see FinanceTypeEnum
     */
    private Integer financeType;

    /**
     *工单状态 @see FinanceStatusEnum
     */
    @NotNull(message = "工单状态不能为空")
    private Integer financeStatus;

    /**
     *业务单号(订单号/账单号)
     */
    @NotNull(message = "业务单号不能为空")
    private String orderCode;

    /**
     * 账单名称
     */
    private String billName;

    /**
     *分销商编码
     */
    private String orgCode;
    /**
     *分销商名称
     */
    private String orgName;
    /**
     *商家编码
     */
    private String merchantCode;
    /**
     *币种
     */
    private String currency;
    /**
     *通知金额
     */
    private BigDecimal notifyAmount;

    /**
     * 如果批量收款，则将每个订单的金额传入
     */
    private List<FinanceOrderItemDTO> financeOrderItemDTOList;

    /**
     * 转账记录
     */
    private List<VoucherRequestDTO> voucherList;

}
