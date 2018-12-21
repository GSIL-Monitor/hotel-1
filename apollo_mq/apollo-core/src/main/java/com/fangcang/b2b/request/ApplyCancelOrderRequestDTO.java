package com.fangcang.b2b.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class ApplyCancelOrderRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 7737024370721357907L;

    @NotNull(message = "orderId不能为Null")
    private Long orderId;
}
