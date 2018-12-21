package com.fangcang.hotelinfo.service.impl;

import com.fangcang.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.hotelinfo.domain.CommonHotelDO;
import com.fangcang.hotelinfo.mapper.CommonHotelMapper;
import com.fangcang.hotelinfo.request.CommonUseRequestDTO;
import com.fangcang.hotelinfo.request.CommonUsedHotelRequestDTO;
import com.fangcang.hotelinfo.service.CommonHotelService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonHotelServiceImpl implements CommonHotelService {
	
	 @Autowired
	 private  CommonHotelMapper commonHotelMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO isCommonUseHotel(CommonUseRequestDTO commonUseRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			if(null == commonUseRequestDTO
					|| null == commonUseRequestDTO.getHotelId()
					|| null == commonUseRequestDTO.getIsCommonUsed()){
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
				return responseDTO;
			}
			if (commonUseRequestDTO.getIsCommonUsed() == 1) {
				//新增常用商家设置
				CommonHotelDO commonHotelDO = null;
				commonHotelDO = PropertyCopyUtil.transfer(commonUseRequestDTO, CommonHotelDO.class);
				commonHotelMapper.insertCommonHotel(commonHotelDO);
			} else {
				//取消常用商家设置
				CommonHotelDO commonHotelDO = new CommonHotelDO();
				commonHotelDO.setHotelId(commonUseRequestDTO.getHotelId());
				commonHotelDO.setMerchantCode(commonUseRequestDTO.getMerchantCode());
				commonHotelMapper.deleteCommonHotel(commonHotelDO);
			}
		} catch (Exception e) {
			log.error("isCommonUseHotel", e);
			throw new ServiceException("isCommonUseHotel",e);
		}
		return responseDTO;
	}

}
