package com.fangcang.finance.financelock.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class UnlockOrderListRequestDTO extends BaseQueryConditionDTO implements Serializable {


    private static final long serialVersionUID = 6834616237303555511L;
    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 供货单编码
     */
    private String supplyOrderCode;

    /**
     * 客人名称
     */
    private String guestName;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 财务锁单状态，1：已锁定，2：未锁定
     */
    private Integer financeLockStatus;


}
