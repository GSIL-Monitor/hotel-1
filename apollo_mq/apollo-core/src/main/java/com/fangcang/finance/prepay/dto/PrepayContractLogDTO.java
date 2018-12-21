package com.fangcang.finance.prepay.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class PrepayContractLogDTO implements Serializable {
    private static final long serialVersionUID = -1554697152668611049L;
    private Integer id;

    /**
     * 日志类型，1合约日志，2押金
     */
    private Byte type;

    /**
     * 合约id
     */
    private Integer contractId;

    /**
     * 供应商id
     */
    private Integer supplyId;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private String operatorTime;

}