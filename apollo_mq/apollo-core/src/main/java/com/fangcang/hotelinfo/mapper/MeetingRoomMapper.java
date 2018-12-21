package com.fangcang.hotelinfo.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.MeetingRoomDO;
import com.fangcang.hotelinfo.request.MeetingRoomListQueryDTO;

import java.util.List;

/**
 * Created by ASUS on 2018/6/8.
 */
public interface MeetingRoomMapper extends MyMapper<MeetingRoomDO> {

    /**
     * 新增会议室
     * @param meetingRoomDO
     */
    public void insertMeetingRoom(MeetingRoomDO meetingRoomDO);

    /**
     * 修改会议室
     * @param meetingRoomDO
     */
    public void updateMeetingRoom(MeetingRoomDO meetingRoomDO);

    /**
     * 会议室查询
     * @param meetingRoomListQueryDTO
     * @return
     */
    public List<MeetingRoomDO> queryMeetingRoomList(MeetingRoomListQueryDTO meetingRoomListQueryDTO);

    /**
     * 批量新增会议室
     * @param meetingRoomDOList
     */
    public void batchInsertMeetingRoom(List<MeetingRoomDO> meetingRoomDOList);
}
