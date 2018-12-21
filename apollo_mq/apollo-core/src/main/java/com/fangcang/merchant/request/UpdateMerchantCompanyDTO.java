package com.fangcang.merchant.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateMerchantCompanyDTO implements Serializable {
    private static final long serialVersionUID = -9179160103816200551L;

    @NotNull
    private Integer id;

    /**
     * 公司名称
     */
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

    private String operator;
}
