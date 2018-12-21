package com.fangcang.supplier.domain;

import lombok.Data;

import java.util.Date;

/**
* @Description:    供应商银行卡信息
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/29 13:04
*/
@Data
public class SupplyBankCardDO {
    
    private Long bankCardId;

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

    private Long supplyId;

    private Date createTime;

    private String creator;

    private String modifier;

    private Date modifyTime;

}