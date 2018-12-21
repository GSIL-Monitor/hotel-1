package com.fangcang.finance.bill.request;

import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.fangcang.finance.financeorder.request.VoucherRequestDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class CreateBillFinanceOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单ID
     */
    @NotNull
    private Integer billId;

    private String operator;

    /**
     * 多币种金额
     */
    @NotEmpty
    private List<MultipleCurrencyAmountDTO> multipleCurrencyList;

    /**
     * 支付信息
     */
    private List<VoucherRequestDTO> financePayItemDTOList;
}
