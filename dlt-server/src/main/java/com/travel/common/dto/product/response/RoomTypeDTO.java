package com.travel.common.dto.product.response;

import java.util.List;

/**
 *   2018/3/14.
 */
public class RoomTypeDTO {
    private Long roomTypeId;
    private String roomTypeName;
    private List<DailyQuotaDTO> totalDailyQuotaList;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public List<DailyQuotaDTO> getTotalDailyQuotaList() {
        return totalDailyQuotaList;
    }

    public void setTotalDailyQuotaList(List<DailyQuotaDTO> totalDailyQuotaList) {
        this.totalDailyQuotaList = totalDailyQuotaList;
    }
}
