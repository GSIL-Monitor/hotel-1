package com.fangcang.ebk.response;

import com.fangcang.common.PaginationSupportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckInReportSummaryDTO extends PaginationSupportDTO<CheckInReportDTO>{

    /**
     * 总间夜
     */
    private Integer roomNightTotal;

    /**
     * 散房间夜
     */
    private Integer looseRoomNight;

    /**
     * 团房间夜
     */
    private Integer grouponRoomNight;

    /**
     * 总金额
     */
    private BigDecimal amountTotal;
}
