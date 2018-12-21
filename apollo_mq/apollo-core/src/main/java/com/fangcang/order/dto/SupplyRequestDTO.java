package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SupplyRequestDTO implements Serializable{
    private static final long serialVersionUID = 7092127444789241406L;
    /**
     * 供货单请求ID
     */
    private Integer supplyRequestId;

    /**
     * 供货单id
     */
    private Integer supplyOrderId;

    /**
     * 供货单类型：1：预定单，2：重发预订单，3：修改单，4：取消单
     */
    private Integer supplyType;

    /**
     * 发送方式：1：EBK
     */
    private Integer sendType;

    /**
     * 发送状态，1：成功，0：失败
     */
    private Integer sendStatus;

    /**
     * 是否显示价格，1：显示，0：不显示
     */
    private Integer isDisplayPrice;

    /**
     * 本次确认类型，0：未处理 1：已确认，2：已拒绝
     */
    private Integer thisConfirmType;

    /**
     * 本次确认号
     */
    private String thisConfirmNo;

    /**
     * 本次确认人
     */
    private String thisConfirmName;

    /**
     * 拒绝原因：变价，满房，其他自定义
     */
    private String refuseReason;

    /**
     * 确认或取消备注
     */
    private String note;

    private String creator;

    private String createTime;

    private String modifier;

    private String modifyTime;

}