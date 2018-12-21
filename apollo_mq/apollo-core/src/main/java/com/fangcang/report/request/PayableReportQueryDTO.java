package com.fangcang.report.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author zhanwang
 */
@Data
public class PayableReportQueryDTO extends BaseQueryConditionDTO implements Serializable {


    private static final long serialVersionUID = -1453806245953991587L;
    /**
     * 查询类型： 1下单日期，2入住日期，3离店日期
     */
    @NotNull
    private Integer queryType;
    /**
     * 开始日期
     */
    @NotBlank
    private String startDate;

    /**
     * 结束日期
     */
    @NotBlank
    private String endDate;
    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 产品经理id
     */
    private String merchantPmId;
    /**
     * 商家编码
     */
    private String merchantCode;

}
