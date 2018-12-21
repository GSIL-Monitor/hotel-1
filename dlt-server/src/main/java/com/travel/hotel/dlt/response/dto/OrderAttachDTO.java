package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单附件
 */
@Data
public class OrderAttachDTO implements Serializable {
    private static final long serialVersionUID = -615698518534885231L;

    /**
     * 主键ID
     */
    private Integer orderAttachId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 附件类型：1:供应商确认预定，2：供应商确认修改，3：供应商确认取消，4：给供应商付款凭证，5:分销商预订单，
     * 6：分销商修改单，7：分销商取消单，8:分销商付款凭证，9:入住名单，10:其他
     */
    private Integer type;

    /**
     * 附件名称
     */
    private String name;

    /**
     * url地址
     */
    private String url;

    /**
     * 实际地址
     */
    private String realpath;

    private String creator;

    private String createTime;

    private String modifier;

    private String modifyTime;


}