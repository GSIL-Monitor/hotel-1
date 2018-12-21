package com.fangcang.report.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/7/4
 */
@Data
public class PrepayContractReportResponseDTO implements Serializable {


    private static final long serialVersionUID = -8660162652439508114L;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 日期，格式: YYYY/MM
     */
    private String date;
    /**
     * 目标间夜
     */
    private Integer roomNight;
    /**
     * 已完成间夜数
     */
    private Integer doneRoomNight;

    /**
     * 产品经理姓名
     */
    private String merchantPMName;
    /**
     * 业务经理姓名
     */
    private String merchantBMName;
}
