package com.fangcang.finance.prepay.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanwang
 */
@Data
public class PrepayRechargeItemAttchDTO implements Serializable {
    private static final long serialVersionUID = 7048015969122189845L;
    private Integer id;

    /**
     * 充值明细id
     */
    private Integer rechargeItemId;

    /**
     * 附件名称
     */
    private String name;

    /**
     * url地址
     */
    private String url;

    /**
     * 实际地址
     */
    private String realpath;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

}