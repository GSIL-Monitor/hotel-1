package com.fangcang.finance.bill.response;

import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillFinanceOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer financeId;

    private String financeCode;

    private Integer financeType;

    private Integer financeStatus;

    private String orgCode;

    private String orgName;

    /**
     * 支付通道
     */
    private List<String> passageList;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 多币种金额
     */
    private List<MultipleCurrencyAmountDTO> multipleCurrencyList;
}
