package com.fangcang.b2b.service;

import com.fangcang.agent.dto.AgentUserDTO;
import com.fangcang.b2b.dto.HotelSimpleDTO;
import com.fangcang.b2b.request.GetHotelDetailRequestDTO;
import com.fangcang.b2b.request.QueryHotelListRequestDTO;
import com.fangcang.b2b.request.QueryMerchantAllCityDTO;
import com.fangcang.b2b.response.GetHotelDetailResponseDTO;
import com.fangcang.b2b.response.HotelBaseInfoWithImagesResponseDTO;
import com.fangcang.b2b.response.QueryHotelListRsponseDTO;
import com.fangcang.b2b.response.QueryMerchantCommonCityResponseDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;

import java.util.List;

/**
 * Created by ASUS on 2018/7/2.
 */
public interface HotelSearchService {

    /**
     * 酒店列表查询
     *
     * @param queryHotelListRequestDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<QueryHotelListRsponseDTO>> queryHotelList(QueryHotelListRequestDTO queryHotelListRequestDTO, AgentUserDTO agentUserDTO);

    /**
     * 酒店详情
     *
     * @param getHotelDetailRequestDTO
     * @param agentUserDTO
     * @return
     */
    public ResponseDTO<GetHotelDetailResponseDTO> getHotelDetail(GetHotelDetailRequestDTO getHotelDetailRequestDTO, AgentUserDTO agentUserDTO);

    /**
     * 查询酒店基本信息
     *
     * @param hotelBaseInfoRequestDTO
     * @return
     */
    public ResponseDTO<HotelBaseInfoWithImagesResponseDTO> queryHotelBaseInfo(HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO);

    /**
     * 查询商家的常用城市
     *
     * @param merchantCode
     * @return
     */
    public ResponseDTO<QueryMerchantCommonCityResponseDTO> queryCommonCity(String merchantCode);

    /**
     * 模糊查询商家城市
     *
     * @param queryMerchantAllCityDTO
     * @return
     */
    public ResponseDTO<QueryMerchantCommonCityResponseDTO> fuzzyQueryMerchantCity(QueryMerchantAllCityDTO queryMerchantAllCityDTO);

    /**
     * 酒店联想
     * @param hotelListQueryDTO
     * @return
     */
    public ResponseDTO<List<HotelSimpleDTO>> associateHotelInfo(HotelListQueryDTO hotelListQueryDTO);

}
