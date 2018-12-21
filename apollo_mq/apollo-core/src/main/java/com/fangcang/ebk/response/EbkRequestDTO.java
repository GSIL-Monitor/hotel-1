package com.fangcang.ebk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbkRequestDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 请求id
     */
    private Long id;

    /**
     * 请求类型：1预订，2修改，3取消
     */
    private Integer requestType;

    /**
     * 请求状态（0未处理，1确认，2拒绝）
     */
    private Integer requestStatus;

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
    private String supplyRemark;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;
}
