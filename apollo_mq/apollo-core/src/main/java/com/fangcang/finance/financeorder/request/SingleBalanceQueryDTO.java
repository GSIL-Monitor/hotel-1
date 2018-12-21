package com.fangcang.finance.financeorder.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vinney on 2018/7/8.
 */
@Data
public class SingleBalanceQueryDTO extends BaseQueryConditionDTO implements Serializable {


    private String orderCode;

    /**
     * 日期查询类型：1-入住，2-离店，3-下单
     */
    private Integer dateQueryType;
    private String startDate;
    private String endDate;

    private String hotelName;
    private Integer hotelId;

    /**
     *如果是分销商单结：分销商名称<br/>
     * 如果是供应商单结：供应商名称
     */
    private String orgName;

    /**
     *如果是分销商单结：分销商编码<br/>
     * 如果是供应商单结：供应商编码
     */
    private String orgCode;

    private String guestName;

    /**
     * 单结订单-结算状态
     * 1-待收款
     * 2-已收款
     * 3-未完成
     */
    private int financeOrderStatus;


}
