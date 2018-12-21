package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/31 20:38
 * @Description:
 */
@Data
public class SupplyVisitDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -687800214907229619L;

    private Integer supplyVisitId;

    private Integer supplyId;

    /**
     * 拜访者
     */
    private String visitor;

    /**
     * 拜访时间
     */
    private Date visitTime;

    /**
     * 拜访内容
     */
    private String visitContent;
}
