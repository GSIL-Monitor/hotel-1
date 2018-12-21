package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.hotelinfo.dto.PutTypeDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class AddMeetingRoomRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 3850462354372727789L;
    /**
     * 会议室ID
     */
    private Long meetingId;

    /**
     * 酒店ID
     */
    @NotNull(message = "hotelId不能为Null")
    private Long hotelId;

    /**
     * 会议室名称
     */
    @NotEmpty(message = "meetingName不能为Null")
    private String meetingName;

    /**
     * 立柱(1 有立柱  0无立柱)
     */
    @NotNull(message = "pillar不能为Null")
    private Integer pillar;

    /**
     * 容纳人数
     */
    @NotEmpty(message = "capacity不能为空")
    private String capacity;

    /**
     * 会议室面积
     */
    @NotEmpty(message = "area不能为空")
    private String area;

    /**
     * 所属楼层
     */
    private String floor;

    /**
     * 会议室长度
     */
    @NotEmpty(message = "meetingRoomLong不能为空")
    private String meetingRoomLong;

    /**
     *会议室宽度
     */
    @NotEmpty(message = "meetingRoomWidth不能为空")
    private String meetingRoomWidth;

    /**
     * 会议室高度
     */
    @NotEmpty(message = "meetingRoomHigh不能为空")
    private String meetingRoomHigh;

    /**
     * 上午开始时间
     */
    @NotEmpty(message = "morningStartTime不能为空")
    private String morningStartTime;

    /**
     * 上午结束时间
     */
    @NotEmpty(message = "morningEndTime不能为空")
    private String morningEndTime;

    /**
     * 下午开始时间
     */
    @NotEmpty(message = "afternoonStartTime不能为空")
    private String afternoonStartTime;

    /**
     * 下午结束时间
     */
    @NotEmpty(message = "afternoonEndTime不能为空")
    private String afternoonEndTime;

    /**
     * 晚上开始时间
     */
    @NotEmpty(message = "nightEndTime不能为空")
    private String nightStartTime;

    /**
     * 晚上结束时间
     */
    @NotEmpty(message = "nightEndTime不能为空")
    private String nightEndTime;

    /**
     * 全天开始时间
     */
    @NotEmpty(message = "allDayStartTime不能为空")
    private String allDayStartTime;

    /**
     * 全天结束时间
     */
    @NotEmpty(message = "allDayEndTime不能为空")
    private String allDayEndTime;

    /**
     * 摆放类型
     */
    @NotNull(message = "putTypeList不能为空")
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
