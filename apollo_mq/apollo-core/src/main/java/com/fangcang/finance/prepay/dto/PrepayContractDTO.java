package com.fangcang.finance.prepay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class PrepayContractDTO implements Serializable {
    private static final long serialVersionUID = -851689123290035062L;
    private Integer id;

    /**
     * 机构id
     */
    @NotNull
    private Integer supplyId;

    /**
     * 合同有效开始日期
     */
    @NotBlank
    private String validBeginDate;

    /**
     * 合同有效结束日期
     */
    @NotBlank
    private String validEndDate;

    @NotNull
    private Integer hotelId;

    @NotBlank
    private String hotelName;

    /**
     * 目标间夜总数
     */
    @NotNull
    private Integer targetRoomNightSum;

    /**
     * 合同金额
     */
    @NotNull
    private BigDecimal contractAmount;

    /**
     * 预付款
     */
    private BigDecimal prepayAmount;

    /**
     * 已结算金额
     */
    private BigDecimal settlementAmount;

    /**
     * 已完成间夜
     */
    private Integer doneRoomNight;

    /**
     * 未完成间夜
     */
    private Integer unDoneRoomNight;

    /**
     * 币种
     */
    @NotBlank
    private String currency;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

}