package com.fangcang.merchant.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class MerchantCompanyApplyDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer id;

    private Integer companyId;

    private Integer supplyId;

    private String supplyName;

    private String createTime;

    private String creator;

    private String modifyTime;

    private String modifier;
}