package com.fangcang.supplier.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.util.Date;

/**
 * Created by Vinney on 2018/10/25.
 */
@Data
public class QueryOtherCostRequestDTO extends BaseQueryConditionDTO {

    private Date createTimeBegin;

    private Date createTimeEnd;

    /**
     * 0-没启用
     * 1-启用
     * -1-全部
     */
    private Integer isActive;

    /**
     * 其他成本表的主键
     */
    private Integer id;

    /**
     * 供应商
     */
    private Integer supplyId;

}
