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
public class HandleOrderReqRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -4986111599240324038L;

    /**
     * 取消订单请求id
     */
    @NotNull
    private Integer orderRequestId;

    /**
     * 处理结果：0:未处理，1同意处理，2拒绝处理
     */
    private Integer handleResult;

    /**
     * 备注
     */
    private String note;

}
