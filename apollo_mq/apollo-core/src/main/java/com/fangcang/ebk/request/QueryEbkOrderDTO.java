package com.fangcang.ebk.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.util.List;

@Data
public class QueryEbkOrderDTO extends BaseQueryConditionDTO{

    /**
     * 页签类型（-1全部，1待处理，2今日入住，3取消修改申请）
     */
    private Integer tagType;

    /**
     * 搜索关键字(订单号、入住人)
     */
    private String keyWord;

    /**
     * 是否团房（-1全部，0散房，1团房）
     */
    private Integer isGroupon;

    /**
     * 供货单号
     */
    private String supplyOrderCode;

    /**
     * 日期类型（1下单日期，2入住日期，3离店日期）
     */
    private Integer dateType;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 团号
     */
    private String grouponCode;

    /**
     * 订单状态（-1全部，1新单，2修改申请，3取消申请，4已确认，5已取消）
     */
    private List<Integer> orderStatusList;

    /**
     * 导游
     */
    private String guide;

    /**
     * 客人
     */
    private String guest;

    /**
     * 确认号
     */
    private String confirmNo;

    /**
     * 供应商编码
     */
    public String supplyCode;
}
