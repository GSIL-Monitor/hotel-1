package com.travel.common.dto.product.query;

import com.travel.common.dto.GenericQueryDTO;

/**
 *   2018/3/20.
 */
public class QuotaDailyReportQueryDTO extends GenericQueryDTO {

    private Long hotelId;
    private String hotelName;
    private String beginDate;
    private String endDate;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
