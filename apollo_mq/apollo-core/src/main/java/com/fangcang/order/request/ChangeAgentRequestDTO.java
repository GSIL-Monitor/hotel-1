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
public class ChangeAgentRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 9057234743124748545L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;
    /**
     * 联系人电话
     */
    private String contractPhone;

    /**
     * 联系人名称
     */
    private String contractName;

    /**
     * 结算方式
     */
    private Integer balanceMethod;
}
