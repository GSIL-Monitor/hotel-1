package com.fangcang.hotelinfo;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
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
@RequestMapping(value = "/test/hotelinfo")
public class TestMeetingRoomController extends BaseController {

    @RequestMapping(value = "/addMeetingRoom", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO addMeetingRoom(@RequestBody @Valid AddMeetingRoomRequestDTO addMeetingRoomRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/deleteMeetingById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deleteMeetingById(@RequestBody @Valid DeleteMeetingRoomRequestDTO deleteMeetingRoomRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/queryMeetingList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<MeetingListResponseDTO> queryMeetingList(@RequestBody @Valid MeetingListRequestDTO meetingRoomListRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        MeetingListResponseDTO meetingListResponseDTO = new MeetingListResponseDTO();
        List<MeetingRoomDTO> meetingList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MeetingRoomDTO meetingRoomDTO = new MeetingRoomDTO();
            meetingRoomDTO.setMeetingId(Long.valueOf(1000 + i));
            meetingRoomDTO.setMeetingName("花间堂会议室" + i);
            if (i % 2 == 0) {
                meetingRoomDTO.setPillar(1);
            } else {
                meetingRoomDTO.setPillar(0);
            }
            meetingRoomDTO.setArea("30");
            meetingRoomDTO.setCapacity("容纳40~100人");
            meetingRoomDTO.setFloor("3");
            meetingList.add(meetingRoomDTO);
        }
        meetingListResponseDTO.setMeetingList(meetingList);
        responseDTO.setModel(meetingListResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/queryMeetingInfoById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<MeetingDetailResponseDTO> queryMeetingInfoById(MeetingDetailRequestDTO meetingDetailRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        MeetingDetailResponseDTO meetingDetailResponseDTO = new MeetingDetailResponseDTO();
        meetingDetailResponseDTO.setMeetingId(100000L);
        meetingDetailResponseDTO.setMeetingName("花间堂会议室");
        meetingDetailResponseDTO.setPillar(1);
        meetingDetailResponseDTO.setCapacity("容纳30~100人");
        meetingDetailResponseDTO.setArea("30");
        meetingDetailResponseDTO.setFloor("5");
        meetingDetailResponseDTO.setMeetingRoomLong("10");
        meetingDetailResponseDTO.setMeetingRoomWidth("5");
        meetingDetailResponseDTO.setMeetingRoomHigh("3");
        meetingDetailResponseDTO.setMorningStartTime("09:00");
        meetingDetailResponseDTO.setMorningEndTime("12:00");
        meetingDetailResponseDTO.setAfternoonStartTime("14:00");
        meetingDetailResponseDTO.setAfternoonEndTime("18:00");
        meetingDetailResponseDTO.setNightStartTime("19:00");
        meetingDetailResponseDTO.setNightEndTime("22:00");
        meetingDetailResponseDTO.setAllDayStartTime("09:00");
        meetingDetailResponseDTO.setAllDayEndTime("22:00");
        meetingDetailResponseDTO.setRemark("备注~~~~~~~");
        meetingDetailResponseDTO.setImageId(3000L);
        meetingDetailResponseDTO.setImageUrl("http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg");
        meetingDetailResponseDTO.setRealPath("/test02images/hotels/873/195873");

        List<PutTypeDTO> putTypeDTOList = new ArrayList<>();
        PutTypeDTO putTypeDTO = new PutTypeDTO();
        putTypeDTO.setPutType(PutTypeEnum.THEATRE.key);
        putTypeDTO.setApplicableNumber("30");

        PutTypeDTO putTypeDTO2 = new PutTypeDTO();
        putTypeDTO2.setPutType(PutTypeEnum.DIRECTORATE.key);
        putTypeDTO2.setApplicableNumber("50");

        PutTypeDTO putTypeDTO3 = new PutTypeDTO();
        putTypeDTO3.setPutType(PutTypeEnum.BANQUET.key);
        putTypeDTO3.setApplicableNumber("30");
        putTypeDTOList.add(putTypeDTO);
        putTypeDTOList.add(putTypeDTO2);
        putTypeDTOList.add(putTypeDTO3);

        meetingDetailResponseDTO.setPutTypeList(putTypeDTOList);
        responseDTO.setModel(meetingDetailResponseDTO);

        return responseDTO;
    }
}
