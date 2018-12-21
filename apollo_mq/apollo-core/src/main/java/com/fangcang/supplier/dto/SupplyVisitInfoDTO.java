package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 16:59
 * @Description: 供应商拜访记录信息
 */
@Data
public class SupplyVisitInfoDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8149191683837995112L;

    /**
     * 拜访人
     */
    private String visitor;

    /**
     * 拜访时间
     */
    private Date visitTime;

    /**
     * 拜访内容
     */
    private String content;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 拜访记录id
     */
    private Long supplyVisitId;

    /**
     * 所属供应商id
     */
    private Long supplyId;


}
