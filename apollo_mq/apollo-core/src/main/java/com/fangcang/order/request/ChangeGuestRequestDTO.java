package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ChangeGuestRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 4526945633542840677L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 入住人名称
     */
    @NotEmpty
    private List<String> guestList;


}
