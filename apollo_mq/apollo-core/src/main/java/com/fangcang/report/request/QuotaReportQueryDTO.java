package com.fangcang.report.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/7/9.
 */
@Data
public class QuotaReportQueryDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = -7047003785184022977L;

    /**
     * 城市编码
     */
    @NotEmpty(message = "城市编码不能为空")
    private String cityCode;

    /**
     * 酒店ID
     */
    @NotNull(message = "hotelId不能为空")
    private Long hotelId;

    /**
     *开始日期
     */
    @NotEmpty(message = "开始日期不能为空")
    private String startDate;

    /**
     * 结束日期
     */
    @NotEmpty(message = "结束日期不能为空")
    private String endDate;

    /**
     * 供应商编码
     */
    @NotEmpty(message = "supplyCode不能为空")
    private String supplyCode;

    /**
     * 供应商名称
     */
    @NotEmpty(message = "supplyName不能为空")
    private String supplyName;

    /**
     * 配额类型  com.fangcang.common.enums.QuotaTypeEnum
     */
    private Integer quotaType;

    /**
     * 商家编码
     */
    private String merchantCode;
}
