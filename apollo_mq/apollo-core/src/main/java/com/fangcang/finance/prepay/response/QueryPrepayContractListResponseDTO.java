package com.fangcang.finance.prepay.response;

import com.fangcang.finance.prepay.dto.PrepayContractDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/7/4
 */
@Data
public class QueryPrepayContractListResponseDTO extends PrepayContractDTO implements Serializable {

    private static final long serialVersionUID = 7384341532247128135L;

    /**
     * 供应商名称
     */
    private String supplyName;
    /**
     * 押金
     */
    private BigDecimal depositAmount;
    /**
     * 供应商预付款余额
     */
    private BigDecimal prepayBalance;
    /**
     * 预付款合约余额
     */
    private BigDecimal prepayContractBalance;
    /**
     * 合同剩余月
     */
    private Integer remainMonth;
    /**
     * 产品经理姓名
     */
    private String merchantPMName;
    /**
     * 业务经理姓名
     */
    private String merchantBMName;

}
