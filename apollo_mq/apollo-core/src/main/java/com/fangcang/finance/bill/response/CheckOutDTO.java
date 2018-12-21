package com.fangcang.finance.bill.response;

import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 可出账订单数
     */
    private Integer orderCount;

    /**
     * 商家本币总金额
     */
    private BigDecimal totalAmount;

    /**
     * 占总欠款的比例
     */
    private String amountOfPercent;

    /**
     * 多币种金额
     */
    private List<MultipleCurrencyAmountDTO> multipleCurrencyList;
}
