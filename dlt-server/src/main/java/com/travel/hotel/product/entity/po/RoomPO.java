package com.travel.hotel.product.entity.po;

import java.util.Date;

import com.travel.common.dto.GenericQueryDTO;

public class RoomPO extends GenericQueryDTO {

    public RoomPO() {
    }

    public RoomPO(Long hotelId) {
        this.hotelId = hotelId;
    }

    private String hotelName;

    private Long roomTypeId;

    private Long hotelId;

    private String roomName;

    private String bedType;

    /**
     * 床型中文描述
     */
    private  String bedTypeName;

    private Integer brand;

    private Integer isactive;

    private String creator;

    //初始化一个数值，但保存的是不取此数值。就是为了方便数据存储
    private Date createdate;

    private String modifier;

    //初始化一个数值，但保存的是不取此数值。就是为了方便数据存储
    private Date modifydate;

    private String descp;
    
    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType == null ? null : bedType.trim();
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

}