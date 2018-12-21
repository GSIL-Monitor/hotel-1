package com.fangcang.finance.bill.response;

import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 账单编码
     */
    private String billCode;

    /**
     * 账单名称
     */
    private String billName;

    /**
     * 账单状态（1对账中，2已对账，3待结算，4已结算，5已取消）
     */
    private Integer billStatus;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 账单开始日期
     */
    private String beginDate;

    /**
     * 账单结束日期
     */
    private String endDate;

    /**
     * 是否自动对账
     */
    private Integer isAuto;

    /**
     * 导入批次号
     */
    private Integer importNo;

    /**
     * 自动对账结果
     */
    private BillAutoMatchResultDTO autoMatchResultDTO;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否通知收款完成(1已完成，0未完成)
     */
    private Integer isFinishedNotice=1;

    /**
     * 多币种金额
     */
    private List<MultipleCurrencyAmountDTO> multipleCurrencyList;

    /**
     * 账单所对应的工单
     */
    private List<BillFinanceOrderDTO> financeOrderDTOList;
}
