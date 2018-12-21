package com.travel.hotel.product.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.hotel.product.entity.HotelDTO;
import com.travel.hotel.product.entity.po.HotelPO;

import java.util.List;
import java.util.Map;

/**
 *   2017/12/20.
 */
public interface HotelService {

    public HotelDTO queryByHotelId(Long hotelId,String supplyCode);

    /**
     * 分页查询酒店列表
     * @param queryPO
     * @return
     */
    public PaginationDTO<HotelDTO> queryHotelList(HotelPO queryPO);

    /**
     * 查询全部
     * @return
     */
    public List<HotelPO> queryAll();

    /**
     * 新增酒店
     * @param po
     * @return
     */
    public int addHotel(HotelPO po, Boolean supplyExist);

    /**
     * 组装供应商下拉框Map
     * key=supplyCode
     * value=supplyName
     * @return
     */
    public Map<String,String> querySupplyMap();

    public int updateHotel(HotelPO hotelPO);

    public int delHotel(HotelPO hotelPO);

    /**
     * key=hotelId
     * value=hotelName
     * @return
     */
    public Map<Long,String> queryHotelMap();

}
