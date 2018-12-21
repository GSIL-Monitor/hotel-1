package com.fangcang.hotelinfo.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.request.AddMeetingRoomRequestDTO;
import com.fangcang.hotelinfo.request.DeleteMeetingRoomRequestDTO;
import com.fangcang.hotelinfo.request.MeetingDetailRequestDTO;
import com.fangcang.hotelinfo.request.MeetingListRequestDTO;
import com.fangcang.hotelinfo.response.MeetingDetailResponseDTO;
import com.fangcang.hotelinfo.response.MeetingListResponseDTO;

/**
 * Created by ASUS on 2018/6/8.
 */
public interface MeetingRoomService {
    /**
     * 新增会议室
     * @param addMeetingRoomRequestDTO
     * @return
     */
    public ResponseDTO addMeetingRoom(AddMeetingRoomRequestDTO addMeetingRoomRequestDTO);

    /**
     * 删除会议室
     * @param deleteMeetingRoomRequestDTO
     * @return
     */
    public ResponseDTO deleteMeetingById(DeleteMeetingRoomRequestDTO deleteMeetingRoomRequestDTO);

    /**
     * 查询会议室列表
     * @param meetingRoomListRequestDTO
     * @return
     */
    public ResponseDTO<MeetingListResponseDTO> queryMeetingList(MeetingListRequestDTO meetingRoomListRequestDTO) ;

    /**
     * 查询单个会议室详情
     * @param meetingDetailRequestDTO
     * @return
     */
    public ResponseDTO<MeetingDetailResponseDTO> queryMeetingInfoById(MeetingDetailRequestDTO meetingDetailRequestDTO);
}
