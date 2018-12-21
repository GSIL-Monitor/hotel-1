package com.fangcang.hotelinfo.response;

import com.fangcang.common.BaseDTO;
import com.fangcang.hotelinfo.dto.PutTypeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class MeetingDetailResponseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -2080542468325560802L;

    /**
     * 会议室ID
     */
    private Long meetingId;

    /**
     * 会议室名称
     */
    private String meetingName;

    /**
     * 立柱(1 有立柱  0无立柱)
     */
    private Integer pillar;

    /**
     * 容纳人数
     */
    private String capacity;

    /**
     * 会议室面积
     */
    private String area;

    /**
     * 所属楼层
     */
    private String floor;

    /**
     * 会议室长度
     */
    private String meetingRoomLong;

    /**
     *会议室宽度
     */
    private String meetingRoomWidth;

    /**
     * 会议室高度
     */
    private String meetingRoomHigh;

    /**
     * 上午开始时间
     */
    private String morningStartTime;

    /**
     * 上午结束时间
     */
    private String morningEndTime;

    /**
     * 下午开始时间
     */
    private String afternoonStartTime;

    /**
     * 下午结束时间
     */
    private String afternoonEndTime;

    /**
     * 晚上开始时间
     */
    private String nightStartTime;

    /**
     * 晚上结束时间
     */
    private String nightEndTime;

    /**
     * 全天开始时间
     */
    private String allDayStartTime;

    /**
     * 全天结束时间
     */
    private String allDayEndTime;

    /**
     * 摆放类型
     */
    private List<PutTypeDTO> putTypeList;

    /**
     * 备注
     */
    private String remark;

    /**
     * 主图ID
     */
    private Long imageId;

    /**
     * 主图URL
     */
    private String imageUrl;

    /**
     * 真实路径
     */
    private String realPath;
}
