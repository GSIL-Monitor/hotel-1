package com.fangcang.report.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomNightReportDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 间夜
     */
    private Integer roomNight;

    /**
     * 散房间夜
     */
    private Integer looseRoomNight;

    /**
     * 团房间夜
     */
    private Integer grouponRoomNight;

    /**
     * 金额
     */
    private BigDecimal amount;

    private Integer hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 每日统计
     */
    private List<RoomNightDailyReportDTO> dailyList;
}
