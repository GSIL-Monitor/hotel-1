package com.fangcang.hotelinfo.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ImageTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.hotelinfo.domain.ImageDO;
import com.fangcang.hotelinfo.domain.MeetingRoomDO;
import com.fangcang.hotelinfo.domain.PutTypeDO;
import com.fangcang.hotelinfo.dto.MeetingRoomDTO;
import com.fangcang.hotelinfo.dto.PutTypeDTO;
import com.fangcang.hotelinfo.mapper.ImageMapper;
import com.fangcang.hotelinfo.mapper.MeetingRoomMapper;
import com.fangcang.hotelinfo.mapper.PutTypeMapper;
import com.fangcang.hotelinfo.request.AddMeetingRoomRequestDTO;
import com.fangcang.hotelinfo.request.DeleteMeetingRoomRequestDTO;
import com.fangcang.hotelinfo.request.MeetingDetailRequestDTO;
import com.fangcang.hotelinfo.request.MeetingListRequestDTO;
import com.fangcang.hotelinfo.request.MeetingRoomListQueryDTO;
import com.fangcang.hotelinfo.response.MeetingDetailResponseDTO;
import com.fangcang.hotelinfo.response.MeetingListResponseDTO;
import com.fangcang.hotelinfo.service.MeetingRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/6/8.
 */
@Service
@Slf4j
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    @Autowired
    private PutTypeMapper putTypeMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO addMeetingRoom(AddMeetingRoomRequestDTO addMeetingRoomRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            //会议室信息
            MeetingRoomDO meetingRoomDO = PropertyCopyUtil.transfer(addMeetingRoomRequestDTO,MeetingRoomDO.class);
            //摆放类型
            List<PutTypeDO> putTypeDOList = null;
            if(!CollectionUtils.isEmpty(addMeetingRoomRequestDTO.getPutTypeList())){
                putTypeDOList = PropertyCopyUtil.transferArray(addMeetingRoomRequestDTO.getPutTypeList(),PutTypeDO.class);
            }
            //主图
            ImageDO imageDO = null;
            if(StringUtil.isValidString(addMeetingRoomRequestDTO.getImageUrl())
                    && StringUtil.isValidString(addMeetingRoomRequestDTO.getRealPath())){
                imageDO = new ImageDO();
                imageDO.setImageId(addMeetingRoomRequestDTO.getImageId());
                imageDO.setHotelId(addMeetingRoomRequestDTO.getHotelId());
                imageDO.setImageType(ImageTypeEnum.MEET.key);
                imageDO.setIsMainImage(1);
                imageDO.setImageUrl(addMeetingRoomRequestDTO.getImageUrl());
                imageDO.setRealPath(addMeetingRoomRequestDTO.getRealPath());
                imageDO.setCreator(addMeetingRoomRequestDTO.getCreator());
                imageDO.setModifier(addMeetingRoomRequestDTO.getModifier());
            }
            if(null == addMeetingRoomRequestDTO.getMeetingId()){
                //新增会议室
                meetingRoomDO.setIsActive(1);
                meetingRoomMapper.insertMeetingRoom(meetingRoomDO);
                //新增摆放类型
                if(!CollectionUtils.isEmpty(putTypeDOList)){
                    for (PutTypeDO putTypeDO : putTypeDOList) {
                        putTypeDO.setMeetingId(meetingRoomDO.getMeetingId());
                        putTypeDO.setCreator(addMeetingRoomRequestDTO.getCreator());
                    }
                    putTypeMapper.batchInsertPutType(putTypeDOList);
                }
            }else{
                //修改会议室
                meetingRoomMapper.updateMeetingRoom(meetingRoomDO);
                //删除摆放类型并新增
                if(!CollectionUtils.isEmpty(putTypeDOList) && null != addMeetingRoomRequestDTO.getMeetingId()){
                    PutTypeDO deleteDo = new PutTypeDO();
                    deleteDo.setMeetingId(addMeetingRoomRequestDTO.getMeetingId());
                    putTypeMapper.deletePutTypeByMeetingId(deleteDo);
                    for (PutTypeDO putTypeDO : putTypeDOList) {
                        putTypeDO.setMeetingId(meetingRoomDO.getMeetingId());
                        putTypeDO.setCreator(addMeetingRoomRequestDTO.getCreator());
                    }
                    putTypeMapper.batchInsertPutType(putTypeDOList);
                }
            }
            //主图
            if(null != imageDO && null == imageDO.getImageId()){
                //新增
                imageDO.setExtId(meetingRoomDO.getMeetingId());
                imageMapper.insertHotelImage(imageDO);
            }else if(null != imageDO && null != imageDO.getImageId()){
                imageMapper.updateHotelImage(imageDO);
            }
        } catch (Exception e) {
            log.error("addMeetingRoom has error.",e);
            throw new ServiceException("addMeetingRoom",e);
        }
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseDTO deleteMeetingById(DeleteMeetingRoomRequestDTO deleteMeetingRoomRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            MeetingRoomDO meetingRoomDO = new MeetingRoomDO();
            meetingRoomDO.setMeetingId(deleteMeetingRoomRequestDTO.getMeetingId());
            meetingRoomDO.setModifier(deleteMeetingRoomRequestDTO.getModifier());
            meetingRoomDO.setIsActive(0);
            meetingRoomMapper.updateMeetingRoom(meetingRoomDO);
        } catch (Exception e) {
            log.error("deleteMeetingById has error.",e);
            throw new ServiceException("deleteMeetingById has error",e);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<MeetingListResponseDTO> queryMeetingList(MeetingListRequestDTO meetingRoomListRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            MeetingRoomListQueryDTO meetingRoomListQueryDTO = new MeetingRoomListQueryDTO();
            meetingRoomListQueryDTO.setHotelId(meetingRoomListRequestDTO.getHotelId());
            List<MeetingRoomDO> meetingRoomDOS = meetingRoomMapper.queryMeetingRoomList(meetingRoomListQueryDTO);
            if(!CollectionUtils.isEmpty(meetingRoomDOS)){
                MeetingListResponseDTO meetingListResponseDTO = new MeetingListResponseDTO();
                List<MeetingRoomDTO> meetingRoomDTOList = new ArrayList<>();
                MeetingRoomDTO meetingRoomDTO = null;
                for (MeetingRoomDO meetingRoomDO : meetingRoomDOS) {
                    meetingRoomDTO = PropertyCopyUtil.transfer(meetingRoomDO,MeetingRoomDTO.class);
                    if(null != meetingRoomDO.getImageDO()){
                        meetingRoomDTO.setImageId(meetingRoomDO.getImageDO().getImageId());
                        meetingRoomDTO.setImageUrl(meetingRoomDO.getImageDO().getImageUrl());
                        meetingRoomDTO.setRealPath(meetingRoomDO.getImageDO().getRealPath());
                    }
                    if(!CollectionUtils.isEmpty(meetingRoomDO.getPutTypeDOList())){
                        List<PutTypeDTO> putTypeList = PropertyCopyUtil.transferArray(meetingRoomDO.getPutTypeDOList(),PutTypeDTO.class);
                        meetingRoomDTO.setPutTypeList(putTypeList);
                    }
                    meetingRoomDTOList.add(meetingRoomDTO);
                }
                meetingListResponseDTO.setMeetingList(meetingRoomDTOList);
                responseDTO.setModel(meetingListResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryMeetingList has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<MeetingDetailResponseDTO> queryMeetingInfoById(MeetingDetailRequestDTO meetingDetailRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            MeetingRoomListQueryDTO meetingRoomListQueryDTO = new MeetingRoomListQueryDTO();
            meetingRoomListQueryDTO.setMeetingId(meetingDetailRequestDTO.getMeetingId());
            List<MeetingRoomDO> meetingRoomDOS = meetingRoomMapper.queryMeetingRoomList(meetingRoomListQueryDTO);
            if(!CollectionUtils.isEmpty(meetingRoomDOS)){
                MeetingRoomDO meetingRoomDO = meetingRoomDOS.get(0);
                MeetingDetailResponseDTO meetingDetailResponseDTO = PropertyCopyUtil.transfer(meetingRoomDO,MeetingDetailResponseDTO.class);
                if(null != meetingRoomDO.getImageDO()){
                    meetingDetailResponseDTO.setImageId(meetingRoomDO.getImageDO().getImageId());
                    meetingDetailResponseDTO.setImageUrl(meetingRoomDO.getImageDO().getImageUrl());
                    meetingDetailResponseDTO.setRealPath(meetingRoomDO.getImageDO().getRealPath());
                }
                if(!CollectionUtils.isEmpty(meetingRoomDO.getPutTypeDOList())){
                    List<PutTypeDTO> putTypeList = PropertyCopyUtil.transferArray(meetingRoomDO.getPutTypeDOList(),PutTypeDTO.class);
                    meetingDetailResponseDTO.setPutTypeList(putTypeList);
                }
                responseDTO.setModel(meetingDetailResponseDTO);
            }
        } catch (Exception e) {
            log.error("queryMeetingInfoById has error",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
