package com.fangcang.b2b.response;

import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import com.fangcang.hotelinfo.dto.HotelFacilityDTO;
import com.fangcang.hotelinfo.dto.ImageTypeDTO;
import com.fangcang.hotelinfo.dto.MeetingRoomImageDTO;
import com.fangcang.hotelinfo.dto.RoomTypeImageDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 17:32
 * @Description: B2B移动端酒店基本信息ResponseDTO
 */
@Data
public class HotelBaseInfoWithImagesResponseDTO implements Serializable {

    private static final long serialVersionUID = 3755939007234908532L;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 酒店英文名称
     */
    private String engHotelName;

    /**
     * 酒店星级
     */
    private Integer hotelStar;

    /**
     * 酒店电话
     */
    private String phone;

    /**
     * 酒店主主图URL
     */
    private String imageUrl;

    /**
     * 酒店介绍
     */
    private String introduction;

    /**
     * 是否允许携带宠物
     * 0允许 1 不允许
     */
    private Integer pet;

    /**
     * 接受的信用卡,字符数组(1:国内发行银联卡   2:威士(VISA)  3:万事达(Master) 4运通(AMEX)
     * 5大来(Diners Club) 6 Euro卡  7 Euro 6000卡  8EC借记卡  9 威士电子借记卡 10Maestro卡  11吉士美卡)
     */
    private String[] creditCard;

    /**
     * 酒店地址
     */
    private String hotelAddress;

    /**
     * 酒店英文地址
     */
    private String engHotelAddress;

    /**
     * 入住时间
     */
    private String checkInTime;

    /**
     * 退房时间
     */
    private String checkOutTime;

    /**
     * 酒店设施包装类
     */
    private List<HotelFacilityDTO> facilityList;

    /**
     * 附加项
     */
    private List<HotelAdditionalDTO> additionalList;

    /**
     * 图片列表
     */
    private List<ImageTypeDTO> imageTypeList;

    /**
     * 房型图片列表
     */
    private List<RoomTypeImageDTO> roomTypeImageList;

    /**
     * 会议室图片列表
     */
    private List<MeetingRoomImageDTO> meetingRoomImageList;

}
