package com.travel.hotel.product.entity;

import com.travel.hotel.product.entity.po.HotelPO;
import com.travel.hotel.product.entity.po.RoomPO;

import java.util.List;

/**
 *   2018/1/2.
 */
public class HotelDTO extends HotelPO{

    /**
     * 酒店星级中文名称
     */
    private String starName;

    /**
     * 能否預留配額的名稱
     * 1-是
     * 0-否
     */
    private String preHoldQuotaName;

    private String settlementText;

    public String getSettlementText() {
        return settlementText;
    }

    public void setSettlementText(String settlementText) {
        this.settlementText = settlementText;
    }


    public String getPreHoldQuotaName() {
        return preHoldQuotaName;
    }

    public void setPreHoldQuotaName(String preHoldQuotaName) {
        this.preHoldQuotaName = preHoldQuotaName;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    private List<RoomPO> roomPO;

    public List<RoomPO> getRoomPO() {
        return roomPO;
    }

    public void setRoomPO(List<RoomPO> roomPO) {
        this.roomPO = roomPO;
    }

}
