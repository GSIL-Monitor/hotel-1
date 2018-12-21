package com.fangcang.report.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.util.List;

@Data
public class QueryOrderDetailReportDTO extends BaseQueryConditionDTO {

    /**
     * 日期类型（1下单日期，2入住日期，3离店日期）
     */
    private Integer dateType;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 酒店id
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 订单状态，1：新建，2：处理中，3：已确认，4：已取消
     */
    private Integer orderStatus;

    private List<Integer> orderStatusList;

    /**
     * 订单处理人
     */
    private String belongName;

    /**
     * 业务经理
     */
    private String merchantBm;

    /**
    * 产品经理
     */
    private String merchantPm;

    /**
     * 运营员
     */
    private String merchantOp;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 在住日期：开始日期
     */
    private String beginStayDate;

    /**
     * 在住日期：结束日期
     */
    private String endStayDate;
}
