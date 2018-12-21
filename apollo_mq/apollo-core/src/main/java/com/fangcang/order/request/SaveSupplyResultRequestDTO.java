package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class SaveSupplyResultRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1367931872352907377L;

    /**
     * 供货单id
     */
    @NotNull
    private Integer supplyOrderId;
    /**
     * 供货单状态，3：已确认，4：不确认
     */
    @NotNull
    private Integer supplyStatus;
    /**
     * 供应商确认号码
     */
    private String confirmNo;
    /**
     * 确认人名称
     */
    private String confirmName;
    /**
     * 拒绝原因：1变价，2满房，3，其他自定义
     */
    private String refuseReason;
    /**
     * 备注
     */
    private String note;
    /**
     * 商家编码
     */
    private String merchantCode;
}
