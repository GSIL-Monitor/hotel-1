package com.travel.hotel.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.dto.AutoCompleteDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.enums.BedTypeEnum;
import com.travel.common.utils.PageConvert;
import com.travel.hotel.product.dao.RoomPOMapper;
import com.travel.hotel.product.entity.po.RoomPO;
import com.travel.hotel.product.service.HotelService;
import com.travel.hotel.product.service.RoomService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/11.
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomPOMapper roomPOMapper;

    @Autowired
    private HotelService hotelService;

    @Override
    public PaginationDTO<RoomPO> queryRoomListByCondition(RoomPO queryPO) {

        PageHelper.startPage(queryPO.getCurrentPage(), queryPO.getPageSize());
        List<RoomPO> roomPOList = roomPOMapper.selectByCondition(queryPO);

        //将床型转换成中文描述
        this.getBedTypeName(roomPOList);
        PageInfo<RoomPO> pageInfo = new PageInfo<RoomPO>(roomPOList);
        return PageConvert.getPaginationSupport(pageInfo);
    }

    @Override
    public List<RoomPO> queryRoomByHotelId(Long hotelId) {
        RoomPO queryPO = new RoomPO(hotelId);
        queryPO.setIsactive(1);
        return roomPOMapper.selectByCondition(queryPO);
    }

    @Override
    public Map<Long, String> queryHotelMap() {
        return hotelService.queryHotelMap();
    }

    @Override
    public int addRoom(RoomPO po) {
        po.setCreatedate(new Date());
        po.setModifydate(new Date());
        po.setIsactive(1);
        return roomPOMapper.insertSelective(po);
    }

    @Override
    public RoomPO queryRoomById(Long roomId) {
        RoomPO po = new RoomPO();
        po.setRoomTypeId(roomId);
        List<RoomPO> roomPOList = roomPOMapper.selectByCondition(po);

        if (CollectionUtils.isEmpty(roomPOList) || roomPOList.size() != 1){
            return null;
        }
        return roomPOList.get(0);
    }

    @Override
    public int updateRoom(RoomPO po) {
        po.setModifydate(new Date());
        return roomPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public int delRoom(RoomPO po) {
        po.setIsactive(0);
        po.setModifydate(new Date());
        return roomPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public String autoComplete(Long hotelId) {
        List<RoomPO> roomPOList = this.queryRoomByHotelId(hotelId);
        List<AutoCompleteDTO> autoCompleteDTOList = new ArrayList<AutoCompleteDTO>();
        AutoCompleteDTO dto = null;
        for (RoomPO po : roomPOList){
            dto = new AutoCompleteDTO();
            dto.setLabel(po.getRoomName());
            dto.setValue(String.valueOf(po.getRoomTypeId()));
            autoCompleteDTOList.add(dto);
        }
        return JSON.toJSONString(autoCompleteDTOList);
    }

    private void getBedTypeName(List<RoomPO> roomPOList){
        for (RoomPO po : roomPOList){
            if (StringUtils.isEmpty(po.getBedType())){
                continue;
            }
            po.setBedTypeName(this.getBedTypeName(po.getBedType()));
        }
    }

    /**
     * 將10;20;30转换成 大床、双床、三床
     * @param bedTypes  10;20;30
     * @return 大床、双床、三床
     */
    private String getBedTypeName(String bedTypes){
        if (StringUtils.isEmpty(bedTypes)){
            return null;
        }

        String[] bedTypeArrays = bedTypes.split(",");
        StringBuffer bedTypeName =  new StringBuffer();
        for (String bedType : bedTypeArrays){
            bedTypeName.append(BedTypeEnum.getValueByKey(bedType)).append("、");
        }

        return bedTypeName.substring(0,bedTypeName.toString().length()-1);
    }
}
