package com.fangcang.hotelinfo.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.dto.TransferHotelInfoResult;
import com.fangcang.hotelinfo.request.AddHotelFastRequestDTO;
import com.fangcang.hotelinfo.request.CommonUsedHotelRequestDTO;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;
import com.fangcang.hotelinfo.request.TransferHotelInfoDataRequestDTO;
import com.fangcang.hotelinfo.response.AddHotelFastResponseDTO;
import com.fangcang.hotelinfo.response.CommonUsedHotelResponseDTO;
import com.fangcang.hotelinfo.response.HotelBaseInfoRsponseDTO;
import com.fangcang.hotelinfo.response.HotelInfoResponseDTO;
import com.fangcang.hotelinfo.response.HotelListResponseDTO;
import com.fangcang.hotelinfo.saas.dto.HotelSaasDTO;

/**
 * 
 * @author ASUS
 *
 */
public interface HotelInfoService {

	/**
	 * 快速新增酒店
	 */
	public ResponseDTO<AddHotelFastResponseDTO> addHotelFast(AddHotelFastRequestDTO requestDTO);
	
	/**
	 * 保存修改酒店基本信息	 
	 */
	public ResponseDTO<HotelInfoResponseDTO> saveOrUpdateHotel(HotelInfoRequestDTO HotelInfoRequestDTO);
	/**
	 * 查询酒店基本信息
	 */
	public ResponseDTO<HotelBaseInfoRsponseDTO> queryHotelInfoByHotelId(HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO);
	/**
	 * 删除酒店基本信息
	 */
	public ResponseDTO deleteHotel(HotelDO hotelDO);
	/**
	 * 查询商家常用酒店
	 */
	public ResponseDTO<CommonUsedHotelResponseDTO> queryCommonUsedHotel(CommonUsedHotelRequestDTO queryCommonHotelDTO);
	/**
	 * 获得酒店列表
	 * @param hotelListQueryDTO
	 * @return
	 */
    public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> getHotelList(HotelListQueryDTO hotelListQueryDTO);

	/**
	 *迁移生产数据到apollo数据库
	 * @param transferHotelInfoDataRequestDTO
	 * @return
	 */
	public ResponseDTO transferHotelInfoData(TransferHotelInfoDataRequestDTO transferHotelInfoDataRequestDTO);

	/**
	 * 转换数据并保存到数据中
	 * @param hotelSaasDTO
	 * @return
	 */
	public TransferHotelInfoResult saveTransferResult(HotelSaasDTO hotelSaasDTO);
}
