package com.fangcang.hotelinfo.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.util.List;

/**
 * Created by ASUS on 2018/6/8.
 */
@Data
public class MeetingRoomDO extends BaseDO{

    /**
     * 会议室ID
     */
    private Long meetingId;

    /**
     * 酒店ID
     */
    private Long hotelId;

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
     * 备注
     */
    private String remark;

    /**
     * 是否有效
     */
    private Integer isActive;

    /**
     * 会议室主图
     */
    private ImageDO imageDO;

    /**
     * 摆放类型
     */
    private List<PutTypeDO> putTypeDOList;

    /**
     * 会议室图片
     */
    private List<ImageDO> imageDOList;
}
