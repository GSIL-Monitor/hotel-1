package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.DateDTO;
import com.fangcang.product.dto.QuotaStateDailyDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/29.
 */
@Data
public class QuotaStateDailyRequestDTO extends BaseDTO implements Serializable {

    /**
     * 配额账号
     */
    @NotNull(message = "quotaAccountId不能为Null")
    private Long quotaAccountId;

    /**
     * 售卖日期
     */
    @NotNull(message = "saleDateList不能为Null")
    private List<DateDTO> saleDateList;

    /**
     * 房态设置(-1不变  0关房  1开房)
     */
    @NotNull(message = "quotaState不能为Null")
    private Integer quotaState;

    /**
     * 配额的操作类型(1 增加 , 2减少, 3设置为)
     */
    @NotNull(message = "operateType不能为Null")
    private Integer operateType;

    /**
     * 配额数量
     */
    private Integer quotaNum;

    /**
     * 是否可超(1可超  0不可超  -1 不变)
     */
    @NotNull(message = "overDraft不能为Null")
    private Integer overDraft;

    /**
     * 商家编码
     */
    private String merchantCode;
}
