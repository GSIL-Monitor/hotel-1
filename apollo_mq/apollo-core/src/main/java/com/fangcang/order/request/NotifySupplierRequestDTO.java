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
public class NotifySupplierRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -9034007367847962312L;

    /**
     * 供货单ID
     */
    @NotNull
    private Integer supplyOrderId;

    /**
     * 催单类型：1：预定单，2：重发预订单，3：修改单，4：取消单
     */
    @NotNull
    private Integer supplyType;
}
