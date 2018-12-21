package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

/**
 * Created by Vinney on 2018/9/13.
 */
@Data
public class UnlockRequestDTO extends BaseDTO {

    private Integer orderId;

    private String lockUser;

}
