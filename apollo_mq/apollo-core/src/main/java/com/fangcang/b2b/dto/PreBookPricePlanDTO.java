package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class PreBookPricePlanDTO implements Serializable {

    private static final long serialVersionUID = 2373022963581366638L;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;
    /**
     * 房型id
     */
    private Integer roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 价格计划id
     */
    private Integer rateplanId;

    /**
     * 价格计划名称
     */
    private String rateplanName;

    /**
     * 采购类型(1合约房,2配额房,3包房一,4包房二)
     */
    private Integer quotaType;

    /**
     * 床型，0:单床，1：大床，2：双床，3：三床，4：四床，多个床型用逗号隔开
     */
    private String bedtype;

    /**
     * 早餐类型，1，无早  2：单早，3：双早，4：人头早
     */
    private Integer breakfastType;

    /**
     * 入住日期
     */
    private String checkinDate;

    /**
     * 离店日期
     */
    private String checkoutDate;

    /**
     * 间数
     */
    private Integer roomNum;

    /**
     * 晚数
     */
    private Long night;

    /**
     * 每日价格
     */
    private List<DailyDTO> dayList;

    /**
     * 展示币种
     */
    private String showCurreny;

    /**
     * 最小配额
     */
    private Integer minQuotaNum;

    /**
     * 是否可超
     */
    private Integer overDraft;

}