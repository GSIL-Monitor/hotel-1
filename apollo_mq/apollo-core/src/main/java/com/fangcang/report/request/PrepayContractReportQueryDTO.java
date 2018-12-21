package com.fangcang.report.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/7/6
 */
@Data
public class PrepayContractReportQueryDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = -1660968323083001872L;
    /**
     * 供应商id，日期和酒店/供应商必选二选一
     */
    private Integer supplyId;
    /**
     * 酒店Id
     */
    private Integer hotelId;
    /**
     * 日期，格式: YYYY/MM
     */
    private String date;
    /**
     * 产品经理id
     */
    private Integer merchantPM;
    /**
     * 业务经理id
     */
    private Integer merchantBM;

    /**
     * 商家编码
     */
    private Long merchantId;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

}
