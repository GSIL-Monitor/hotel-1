package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateConfirmResultDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 请求id
     */
    private Long requestId;

    /**
     * 确认结果（1确认，2拒绝）
     */
    private Integer confirmResult;

    /**
     * 确认号
     */
    private String confirmNo;

    /**
     * 拒绝原因
     */
    private String refuseReason;

    /**
     * 备注
     */
    private String remark;

    private String operator;

    private String operatorName;
}
