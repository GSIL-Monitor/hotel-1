package com.travel.common.dto.product.response;

import java.util.Date;
import java.util.List;

/**
 *   2018/3/14.
 */
public class QuotaDailyReportResponseDTO {

    private Long hotelId;
    private String hotelName;

    /**
     * 汇总那一行的数据
     */
    private List<RoomTypeDTO> totalDTOList;

    /**
     * 按照房型汇总的
     */
    private List<RoomTypeDTO> roomTypeDTOList;

    private List<Date> dateList;

    public List<Date> getDateList() {
        return dateList;
    }

    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }

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

    public List<RoomTypeDTO> getTotalDTOList() {
        return totalDTOList;
    }

    public void setTotalDTOList(List<RoomTypeDTO> totalDTOList) {
        this.totalDTOList = totalDTOList;
    }

    public List<RoomTypeDTO> getRoomTypeDTOList() {
        return roomTypeDTOList;
    }

    public void setRoomTypeDTOList(List<RoomTypeDTO> roomTypeDTOList) {
        this.roomTypeDTOList = roomTypeDTOList;
    }
}
