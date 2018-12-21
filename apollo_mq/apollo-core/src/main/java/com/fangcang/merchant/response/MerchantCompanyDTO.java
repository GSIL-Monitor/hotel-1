package com.fangcang.merchant.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MerchantCompanyDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer id;

    private String merchantCode;

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

    /**
     * 适用供应商：0所有、1指定
     */
    private Integer applySupply;

    private List<MerchantCompanyApplyDTO> companyApplyDTOList;

    private String createTime;

    private String creator;

    private String modifyTime;

    private String modifier;
}