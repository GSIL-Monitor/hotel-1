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
public class ProfitReportQueryDTO extends BaseQueryConditionDTO implements Serializable {


    private static final long serialVersionUID = -3368748842668193736L;
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
     * 城市编码
     */
    private String cityCode;
    /**
     * 酒店id
     */
    private String hotelId;
    /**
     * 分销商编码
     */
    private String agentCode;
    /**
     * 产品经理名称
     */
    private String merchantPm;
    /**
     * 业务经理名称
     */
    private String merchantBm;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 分组类型, 1城市，2分销商，3酒店，4渠道，5产品经理，6业务经理，7酒店+分销商，8酒店+渠道，9产品经理+酒店，10业务经理+酒店
     */
    @NotNull
    private Integer groupType;

}
