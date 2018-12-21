package com.fangcang.merchant.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class AddMerchantCompanyDTO implements Serializable {
    private static final long serialVersionUID = -3915157452518806984L;

    @NotNull
    private String merchantCode;

    /**
     * 公司名称
     */
    @NotNull
    private String company;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮件主题
     */
    private String emailSubject;

    /**
     * logo
     */
    private String logoUrl;

    /**
     * 印章
     */
    private String seal;

    /**
     * 适用供应商：0所有、1指定
     */
    private Integer applySupply=0;

    private List<Integer> supplyIdList;

    private String operator;
}
