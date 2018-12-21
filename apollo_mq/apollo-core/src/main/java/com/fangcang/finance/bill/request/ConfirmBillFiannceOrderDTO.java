package com.fangcang.finance.bill.request;

import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class ConfirmBillFiannceOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    @NotNull
    private String billCode;

    private String operator;

    /**
     * 多币种金额
     */
    @NotEmpty
    private List<MultipleCurrencyAmountDTO> multipleCurrencyList;
}
