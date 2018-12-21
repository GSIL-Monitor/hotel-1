package com.fangcang.finance.prepay.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class DebuctOrRetreatPrepayRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -4367489882956646387L;
    /**
     * 预付款合约id
     */
    @NotNull
    private Integer contractId;
    /**
     * 扣还类型， 1扣预付款余额，2还预付款余额
     */
    @NotNull
    private Integer type;
    /**
     * 扣还金额
     */
    @NotNull
    private BigDecimal amount;
    /**
     * 内容
     */
    private String content;
    /**
     * 备注
     */
    private String note;
    /**
     * 对象类型，1账单，2供货单
     */
    @NotNull
    private Integer orderType;
    /**
     * 类型1为账单编码，类型2为供货单编码
     */
    @NotNull
    private String orderCode;
    /**
     * 类型1为账单名称
     */
    private String billName;
    /**
     * 财务工单Id
     */
    @NotNull
    private String financeOrderId;


}
