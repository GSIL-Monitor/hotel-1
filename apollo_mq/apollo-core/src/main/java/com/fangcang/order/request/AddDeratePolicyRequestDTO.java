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
public class AddDeratePolicyRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 5567370557430055659L;

    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;

    /**
     * 减免政策明细
     */
    @NotEmpty
    private List<DeratePolicyRequestDTO> deratePolicyList;

}
