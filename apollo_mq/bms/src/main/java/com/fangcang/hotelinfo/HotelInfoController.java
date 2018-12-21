package com.fangcang.hotelinfo;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.request.AddHotelFastRequestDTO;
import com.fangcang.hotelinfo.request.CommonUseRequestDTO;
import com.fangcang.hotelinfo.request.CommonUsedHotelRequestDTO;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelIdRequestDTO;
import com.fangcang.hotelinfo.request.HotelInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;
import com.fangcang.hotelinfo.request.TransferHotelInfoDataRequestDTO;
import com.fangcang.hotelinfo.response.AddHotelFastResponseDTO;
import com.fangcang.hotelinfo.response.CommonUsedHotelResponseDTO;
import com.fangcang.hotelinfo.response.HotelBaseInfoRsponseDTO;
import com.fangcang.hotelinfo.response.HotelInfoResponseDTO;
import com.fangcang.hotelinfo.response.HotelListResponseDTO;
import com.fangcang.hotelinfo.service.CommonHotelService;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.merchant.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 酒店基本信息管理
 * @author 可爱的灿灿
 *
 */
@RestController
@RequestMapping(value = "/hotelinfo")
public class HotelInfoController extends BaseController{
	
	@Autowired
	private CommonHotelService commonHotelService;
	
	@Autowired
	private HotelInfoService hotelInfoService;

	/**
	 * 快速新增酒店
	 */
	@RequestMapping(value = "/addHotelFast", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<AddHotelFastResponseDTO> addHotelFast(@RequestBody AddHotelFastRequestDTO requestDTO){
		ResponseDTO responseDTO = null;
		try {
			UserDTO cacheUser = super.getCacheUser();
			requestDTO.setOperator(super.getFullName());
			requestDTO.setMerchantCode(cacheUser.getMerchantCode());
			responseDTO= hotelInfoService.addHotelFast(requestDTO);
		} catch (Exception e) {
			responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 保存活修改酒店基本信息
	 * @param hotelInfoRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateHotel", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<HotelInfoResponseDTO> saveOrUpdateHotel(@RequestBody HotelInfoRequestDTO hotelInfoRequestDTO) {
		//新增酒店基本信息
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			UserDTO cacheUser = super.getCacheUser();
			hotelInfoRequestDTO.setCreator(super.getFullName());
			hotelInfoRequestDTO.setModifier(super.getFullName());
			hotelInfoRequestDTO.setMerchantCode(cacheUser.getMerchantCode());
			responseDTO = hotelInfoService.saveOrUpdateHotel(hotelInfoRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}
	/**
	 * 查询酒店基本信息
	 */
	@RequestMapping(value = "/queryHotelInfoByHotelId",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<HotelBaseInfoRsponseDTO> queryHotelInfoByHotelId(@RequestBody @Valid HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO){
		UserDTO userDTO = super.getCacheUser();
		hotelBaseInfoRequestDTO.setMerchantCode(userDTO.getMerchantCode());
		return hotelInfoService.queryHotelInfoByHotelId(hotelBaseInfoRequestDTO);
	}
	/**
	 * 删除酒店基本信息
	 * @param hotelIdRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/deleteHotel",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO deleteHotel(@RequestBody @Valid HotelIdRequestDTO hotelIdRequestDTO){
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
            HotelDO hotelDO = new HotelDO();
            hotelDO.setHotelId(hotelIdRequestDTO.getHotelId());
            hotelDO.setIsActive(0);
            hotelDO.setModifier(super.getFullName());
			hotelDO.setMerchantCode(super.getMerchantCode());
            responseDTO = hotelInfoService.deleteHotel(hotelDO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
	}
	
	/**
	 * 查询商家常用酒店
	 * @param queryCommonHotelDTO
	 * @return
	 */
	@RequestMapping(value = "/queryCommonUsedHotel",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<CommonUsedHotelResponseDTO> queryCommonUsedHotel(@RequestBody @Valid CommonUsedHotelRequestDTO queryCommonHotelDTO){
		UserDTO cacheUser = super.getCacheUser();
		queryCommonHotelDTO.setMerchantCode(cacheUser.getMerchantCode());
		return hotelInfoService.queryCommonUsedHotel(queryCommonHotelDTO);
	}
	
	/**
	 * 是否常用商家
	 * @param commonUseDTO
	 * @return
	 */

	@RequestMapping(value = "/isCommonUseHotel", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseDTO isCommonUseHotel(@RequestBody @Valid CommonUseRequestDTO commonUseDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			UserDTO cacheUser = super.getCacheUser();
			commonUseDTO.setMerchantCode(cacheUser.getMerchantCode());
			commonUseDTO.setCreator(super.getFullName());
			commonUseDTO.setModifier(super.getFullName());
			commonUseDTO.setRecommendedLevel(50);
			responseDTO = commonHotelService.isCommonUseHotel(commonUseDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}
	  
	/**
	 * 获取酒店列表
	 * @param hotelInfoListQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/getHotelList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> getHotelList(@RequestBody HotelListQueryDTO hotelInfoListQueryDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			UserDTO userDTO = super.getCacheUser();
			hotelInfoListQueryDTO.setMerchantCode(userDTO.getMerchantCode());
			responseDTO = hotelInfoService.getHotelList(hotelInfoListQueryDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	@RequestMapping(value = "/transferHotelInfoData", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public ResponseDTO transferHotelInfoData(@RequestBody @Valid TransferHotelInfoDataRequestDTO transferHotelInfoDataRequestDTO){
		return hotelInfoService.transferHotelInfoData(transferHotelInfoDataRequestDTO);
	}
}
