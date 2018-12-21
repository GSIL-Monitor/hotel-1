package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/29 13:06
 * @Description: 供应商银行卡信息DTO
 */
@Data
public class SupplyBankCardDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -361914651418611284L;

    /**
     * 供应商id
     */
    private Long supplyId;

    /**
     * 银行卡id
     */
    private Integer bankCardId;

    /**
     * 开户行
     */
    private String openingBank;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 账号
     */
    private String accountNumber;
}
