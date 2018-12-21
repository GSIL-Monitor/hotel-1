package com.fangcang.finance.prepay.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class QuerySupplyDoneRoomNightRequestDTO implements Serializable {


    private static final long serialVersionUID = -8059077230090223254L;
    /**
     * 供应商编码
     */
    private String supplyCode;
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;


}
