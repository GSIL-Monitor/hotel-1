package com.fangcang.hotelinfo;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.PutTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.hotelinfo.dto.MeetingRoomDTO;
import com.fangcang.hotelinfo.dto.PutTypeDTO;
import com.fangcang.hotelinfo.request.AddMeetingRoomRequestDTO;
import com.fangcang.hotelinfo.request.DeleteMeetingRoomRequestDTO;
import com.fangcang.hotelinfo.request.MeetingDetailRequestDTO;
import com.fangcang.hotelinfo.request.MeetingListRequestDTO;
import com.fangcang.hotelinfo.response.MeetingDetailResponseDTO;
import com.fangcang.hotelinfo.response.MeetingListResponseDTO;
import com.fangcang.hotelinfo.service.MeetingRoomService;
import com.fangcang.merchant.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/6/7.
 */
@RestController
@RequestMapping(value = "/hotelinfo")
@Slf4j
public class MeetingRoomController extends BaseController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    /**
     * 新增会议室
     * @param addMeetingRoomRequestDTO
     * @return
     */
    @RequestMapping(value = "/addMeetingRoom", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO addMeetingRoom(@RequestBody @Valid AddMeetingRoomRequestDTO addMeetingRoomRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO userDTO = super.getCacheUser();
            addMeetingRoomRequestDTO.setCreator(super.getFullName());
            addMeetingRoomRequestDTO.setModifier(super.getFullName());
            responseDTO = meetingRoomService.addMeetingRoom(addMeetingRoomRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 删除会议室
     * @param deleteMeetingRoomRequestDTO
     * @return
     */
    @RequestMapping(value = "/deleteMeetingById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deleteMeetingById(@RequestBody @Valid DeleteMeetingRoomRequestDTO deleteMeetingRoomRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO userDTO = super.getCacheUser();
            deleteMeetingRoomRequestDTO.setCreator(super.getFullName());
            deleteMeetingRoomRequestDTO.setModifier(super.getFullName());
            responseDTO = meetingRoomService.deleteMeetingById(deleteMeetingRoomRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询会议室列表
     * @param meetingRoomListRequestDTO
     * @return
     */
    @RequestMapping(value = "/queryMeetingList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<MeetingListResponseDTO> queryMeetingList(@RequestBody @Valid MeetingListRequestDTO meetingRoomListRequestDTO) {
        return meetingRoomService.queryMeetingList(meetingRoomListRequestDTO);
    }

    /**
     * 查询单个会议室详情
     * @param meetingDetailRequestDTO
     * @return
     */
    @RequestMapping(value = "/queryMeetingInfoById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<MeetingDetailResponseDTO> queryMeetingInfoById(@RequestBody @Valid MeetingDetailRequestDTO meetingDetailRequestDTO) {
        return meetingRoomService.queryMeetingInfoById(meetingDetailRequestDTO);
    }
}
