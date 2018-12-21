package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 16:29
 * @Description: 供应商信息
 */
@Data
public class SupplyInfoDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8999495108921302302L;

    private Long supplyId;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 联系人姓名
     */
    private String realName;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 我司业务经理名称
     */
    private String merchantBMName;

    /**
     * 1-常用；0-不常用
     */
    private Integer frequentlyUse;

    /**
     * 1-启用；0-停用
     */
    private Integer isActive;

    /**
     * 客户等级
     */
    private Integer customerLevel;

    /**
     * 押金
     */
    private BigDecimal depositAmount;

    /**
     * 现金余额
     */
    private BigDecimal cashAmount;

    /**
     * 合作预警
     */
    private Integer cooperationStatus;

    /**
     * 备注
     */
    private String note;
}
