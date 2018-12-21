package com.fangcang.finance.prepay.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/7/10
 */
@Data
public class PrepayLogInfoRequestDTO implements Serializable {
    private static final long serialVersionUID = -5672382444847354872L;

    /**
     * 日志类型，1合约日志，2押金
     */
    @NotNull
    private Integer type;
    /**
     * 合约id
     */
    private Integer contractId;

    /**
     * 供应商id
     */
    private Integer supplyId;
}
