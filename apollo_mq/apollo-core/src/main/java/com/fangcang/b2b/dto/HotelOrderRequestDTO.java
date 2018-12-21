package com.fangcang.b2b.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/7/5.
 */
@Data
public class HotelOrderRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 7334269745947183555L;

    private Integer orderId;

    /**
     * 订单申请类型，1取消单申请，2修改单申请
     */
    private Byte requestType;

    /**
     * 处理结果：0:未处理，1同意处理，2拒绝处理
     */
    private Integer handleResult;

    /**
     * 备注
     */
    private String note;

}
