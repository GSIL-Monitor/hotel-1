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
public class ReceivableReportQueryDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = 3828104557420403748L;

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
     * 分销商编码
     */
    private String agentCode;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 业务经理
     */
    private String merchantBm;
    /**
     * 商家编码
     */
    private String merchantCode;

}
