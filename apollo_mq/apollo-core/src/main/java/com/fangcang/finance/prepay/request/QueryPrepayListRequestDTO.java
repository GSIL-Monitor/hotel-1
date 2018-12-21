package com.fangcang.finance.prepay.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class QueryPrepayListRequestDTO extends BaseQueryConditionDTO implements Serializable {


    private static final long serialVersionUID = 4530657607419037182L;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 酒店id
     */
    private Integer hotelId;
    /**
     * 供应商id
     */
    private Integer supplyId;
    /**
     * 产品经理
     */
    private Integer merchantPM;
    /**
     * 业务经理
     */
    private Integer merchantBM;
    /**
     * 未完成金额类型，1大于等于，2小于等于
     */
    private Integer unDoneAmountType;
    /**
     * 未完成金额，等于合同金额-已结算金额
     */
    private BigDecimal unDoneAmount;
    /**
     * 剩余时间类型，1大于等于，2小于等于
     */
    private Integer remainMonthType;
    /**
     * 剩余时间，单位月
     */
    private Integer remainMonth;
    /**
     * 未完成间夜类型，1大于等于，2小于等于
     */
    private Integer unDoneRoomNightType;
    /**
     * 未完成间夜
     */
    private Integer unDoneRoomNight;
    /**
     * 合约查询类型，传空查所有合约，传1只查有效合约
     */
    private Integer contractQueryType;
    /**
     * 商家编码
     */
    private Long merchantId;


}
