package com.fangcang.hotelinfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.IncrementDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.config.HotelBaseInfoConfig;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.BedTypeEnum;
import com.fangcang.common.enums.BroadBandEnum;
import com.fangcang.common.enums.CreditCardEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.HotelFeatureEnum;
import com.fangcang.common.enums.HotelStarEnum;
import com.fangcang.common.enums.ImageTypeEnum;
import com.fangcang.common.enums.PutTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.ThemeEnum;
import com.fangcang.common.enums.saas.BedTypeSaasEnum;
import com.fangcang.common.enums.saas.BroadBandSassEnum;
import com.fangcang.common.enums.saas.CreditCardSaasEnum;
import com.fangcang.common.enums.saas.HotelSaasStarEnum;
import com.fangcang.common.enums.saas.ImageTypeSaasEnum;
import com.fangcang.common.enums.saas.PetSaasEnum;
import com.fangcang.common.enums.saas.PutTypeSaasEnum;
import com.fangcang.common.enums.saas.ThemeSaasEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.HttpClientUtil;
import com.fangcang.common.util.IncrementConfig;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.URLSplitUtil;
import com.fangcang.hotelinfo.domain.HotelAdditionalDO;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.domain.HotelFacilityDO;
import com.fangcang.hotelinfo.domain.HotelMappingDO;
import com.fangcang.hotelinfo.domain.HotelPolicyDO;
import com.fangcang.hotelinfo.domain.ImageDO;
import com.fangcang.hotelinfo.domain.MeetingRoomDO;
import com.fangcang.hotelinfo.domain.PutTypeDO;
import com.fangcang.hotelinfo.domain.RoomTypeDO;
import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import com.fangcang.hotelinfo.dto.HotelFacilityDTO;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import com.fangcang.hotelinfo.dto.SimpleHotelDTO;
import com.fangcang.hotelinfo.dto.TransferHotelInfoResult;
import com.fangcang.hotelinfo.mapper.HotelAdditionalMapper;
import com.fangcang.hotelinfo.mapper.HotelFacilityMapper;
import com.fangcang.hotelinfo.mapper.HotelInfoMappingMapper;
import com.fangcang.hotelinfo.mapper.HotelMapper;
import com.fangcang.hotelinfo.mapper.HotelPolicyMapper;
import com.fangcang.hotelinfo.mapper.ImageMapper;
import com.fangcang.hotelinfo.mapper.MeetingRoomMapper;
import com.fangcang.hotelinfo.mapper.PutTypeMapper;
import com.fangcang.hotelinfo.mapper.RoomTypeMapper;
import com.fangcang.hotelinfo.request.AddHotelFastRequestDTO;
import com.fangcang.hotelinfo.request.CommonUsedHotelRequestDTO;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;
import com.fangcang.hotelinfo.request.HotelQueryRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.TransferHotelInfoDataRequestDTO;
import com.fangcang.hotelinfo.response.AddHotelFastResponseDTO;
import com.fangcang.hotelinfo.response.CommonUsedHotelResponseDTO;
import com.fangcang.hotelinfo.response.HotelBaseInfoRsponseDTO;
import com.fangcang.hotelinfo.response.HotelInfoResponseDTO;
import com.fangcang.hotelinfo.response.HotelListResponseDTO;
import com.fangcang.hotelinfo.saas.dto.HotelFeatureDTO;
import com.fangcang.hotelinfo.saas.dto.HotelImgDTO;
import com.fangcang.hotelinfo.saas.dto.HotelSaasDTO;
import com.fangcang.hotelinfo.saas.dto.MeetingTypeInfoDTO;
import com.fangcang.hotelinfo.saas.dto.PutTypeSassDTO;
import com.fangcang.hotelinfo.saas.dto.RoomTypeBedTypeRelaDTO;
import com.fangcang.hotelinfo.saas.dto.RoomTypeSaasDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.hotelinfo.service.RoomTypeService;
import com.fangcang.hotelinfo.thread.TransferHotelInfoThread;
import com.fangcang.product.service.IncrementService;
import com.fangcang.product.thread.IncrementThread;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

@Service
@Slf4j
public class HotelInfoServiceImpl implements HotelInfoService {

	@Autowired
	private HotelMapper hotelMapper;

	@Autowired
	private HotelAdditionalMapper hotelAdditionalMapper;

	@Autowired
	private HotelPolicyMapper hotelPolicyMapper;

	@Autowired
	private HotelFacilityMapper hotelFacilityMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private RoomTypeService roomTypeService;

	@Resource(name = "incrementExecutor")
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Autowired
	private IncrementConfig incrementConfig;

	@Autowired
	private IncrementService incrementService;

	@Autowired
	private HotelBaseInfoConfig hotelBaseInfoConfig;

	@Resource(name = "transferHotelInfoExecutor")
	private ThreadPoolTaskExecutor transferHotelInfoExecutor;

	@Autowired
	private HotelInfoMappingMapper hotelInfoMappingMapper;

	@Autowired
	private MeetingRoomMapper meetingRoomMapper;

	@Autowired
	private PutTypeMapper putTypeMapper;

	@Autowired
	private RoomTypeMapper roomTypeMapper;

	@Autowired
	private PlatformTransactionManager platformTransactionManager;

	@Transactional
	@Override
	public ResponseDTO<AddHotelFastResponseDTO> addHotelFast(AddHotelFastRequestDTO requestDTO) {
		ResponseDTO responseDTO = null;
		AddHotelFastResponseDTO addHotelFastResponseDTO=new AddHotelFastResponseDTO();
		addHotelFastResponseDTO.setHotelName(requestDTO.getHotelName());
		addHotelFastResponseDTO.setRoomTypeName(requestDTO.getRoomTypeName());

		//新增酒店基本信息
		HotelInfoRequestDTO hotelInfoRequestDTO=new HotelInfoRequestDTO();
		hotelInfoRequestDTO.setCityCode(requestDTO.getCityCode());
		hotelInfoRequestDTO.setCityName(requestDTO.getCityName());
		hotelInfoRequestDTO.setHotelName(requestDTO.getHotelName());
		hotelInfoRequestDTO.setCreator(requestDTO.getOperator());
		hotelInfoRequestDTO.setMerchantCode(requestDTO.getMerchantCode());
		hotelInfoRequestDTO.setAdditionalList(new ArrayList<>());
		hotelInfoRequestDTO.setFacilityList(new ArrayList<>());
		ResponseDTO<HotelInfoResponseDTO> hotelInfoResponseDTO=saveOrUpdateHotel(hotelInfoRequestDTO);
		if (hotelInfoResponseDTO.getResult()==ResultCodeEnum.SUCCESS.code){
			addHotelFastResponseDTO.setHotelId(hotelInfoResponseDTO.getModel().getHotelId().intValue());
		}else{
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailReason("酒店创建失败");
			return responseDTO;
		}
		//新增房型
		RoomTypeRequestDTO roomTypeRequestDTO=new RoomTypeRequestDTO();
		roomTypeRequestDTO.setHotelId(hotelInfoResponseDTO.getModel().getHotelId());
		roomTypeRequestDTO.setRoomTypeName(requestDTO.getRoomTypeName());
		roomTypeRequestDTO.setCreator(requestDTO.getOperator());
		roomTypeRequestDTO.setBedTypeList(new ArrayList<>());
		ResponseDTO roomTypeResponseDTO = roomTypeService.saveOrUpdateRoomType(roomTypeRequestDTO);
		if (roomTypeResponseDTO.getResult()==ResultCodeEnum.SUCCESS.code){
			addHotelFastResponseDTO.setRoomTypeId(Integer.valueOf(roomTypeResponseDTO.getModel().toString()));
		}else{
			throw new ServiceException("房型创建失败");
		}
		responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		responseDTO.setModel(addHotelFastResponseDTO);
		return responseDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<HotelInfoResponseDTO> saveOrUpdateHotel(HotelInfoRequestDTO hotelInfoRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		HotelInfoResponseDTO hotelInfoResponseDTO = new HotelInfoResponseDTO();
		try {
			// 酒店基础信息
			HotelDO hotelDO = PropertyCopyUtil.transfer(hotelInfoRequestDTO, HotelDO.class);
			// 酒店主题
			if (null != hotelInfoRequestDTO.getTheme()) {
				String[] strTheme = hotelInfoRequestDTO.getTheme();
				StringBuilder themBuffer = new StringBuilder();
				for (int i = 0; i < strTheme.length; i++) {
					if (strTheme.length - 1 == i) {
						themBuffer.append(strTheme[i]);
					} else {
						themBuffer.append(strTheme[i]).append(",");
					}
				}
				hotelDO.setTheme(themBuffer.toString());
			}
			// 免房政策
			if (null != hotelInfoRequestDTO.getFreeRoomPolicy()) {
				String[] intFreeRoomPolicy = hotelInfoRequestDTO.getFreeRoomPolicy();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < intFreeRoomPolicy.length; i++) {
					if (intFreeRoomPolicy.length - 1 == i) {
						sb.append(intFreeRoomPolicy[i]);
					} else {
						sb.append(intFreeRoomPolicy[i]).append(",");
					}
				}
				hotelDO.setFreeRoomPolicy(sb.toString());
			}
			// 酒店主图
			ImageDO imageDO = new ImageDO();
			imageDO.setImageId(hotelInfoRequestDTO.getImageId());
			imageDO.setHotelId(hotelDO.getHotelId());
			imageDO.setImageType(ImageTypeEnum.OUTVIEW.key);
			imageDO.setExtId(hotelDO.getHotelId());
			imageDO.setIsMainImage(1);
			imageDO.setImageUrl(hotelInfoRequestDTO.getImageUrl());
			imageDO.setRealPath(hotelInfoRequestDTO.getRealPath());
			imageDO.setCreator(hotelInfoRequestDTO.getCreator());
			imageDO.setModifier(hotelInfoRequestDTO.getModifier());

			// 酒店附加项
			List<HotelAdditionalDO> hotelAdditionalDOList = new ArrayList<>();
			List<HotelAdditionalDTO> additionalList = hotelInfoRequestDTO.getAdditionalList();
			if (!CollectionUtils.isEmpty(additionalList)) {
				HotelAdditionalDO hotelAdditionalDO = null;
				for (HotelAdditionalDTO additional : additionalList) {
					if (null != additional.getIsSelected() && 1 == additional.getIsSelected()) {
						hotelAdditionalDO = PropertyCopyUtil.transfer(additional, HotelAdditionalDO.class);
						hotelAdditionalDO.setHotelId(hotelDO.getHotelId());
						hotelAdditionalDO.setMerchantCode(hotelInfoRequestDTO.getMerchantCode());
						hotelAdditionalDO.setCreator(hotelInfoRequestDTO.getCreator());
						hotelAdditionalDOList.add(hotelAdditionalDO);
					}
				}
			}

			// 酒店政策
			HotelPolicyDO hotelPolicyDO = PropertyCopyUtil.transfer(hotelInfoRequestDTO, HotelPolicyDO.class);
			if (null != hotelInfoRequestDTO.getCreditCard()) {
				String[] strCreditCard = hotelInfoRequestDTO.getCreditCard();
				StringBuilder creditCardBuffer = new StringBuilder();
				for (int i = 0; i < strCreditCard.length; i++) {
					if (strCreditCard.length - 1 == i) {
						creditCardBuffer.append(strCreditCard[i]);
					} else {
						creditCardBuffer.append(strCreditCard[i] + ",");
					}
				}
				hotelPolicyDO.setCreditCard(creditCardBuffer.toString());
				hotelPolicyDO.setHotelId(hotelDO.getHotelId());
			}

			// 酒店设施
			List<HotelFacilityDTO> facilityList = hotelInfoRequestDTO.getFacilityList();
			List<HotelFacilityDO> hotelFacilityDOList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(facilityList)) {
				HotelFacilityDO hotelFacilityDO = null;
				for (HotelFacilityDTO hotelFacility : facilityList) {
					hotelFacilityDO = PropertyCopyUtil.transfer(hotelFacility, HotelFacilityDO.class);
					hotelFacilityDO.setHotelId(hotelDO.getHotelId());
					hotelFacilityDO.setCreator(hotelInfoRequestDTO.getCreator());
					hotelFacilityDOList.add(hotelFacilityDO);
				}
			}

			if (hotelInfoRequestDTO.getHotelId() == null) {
				// 新增
				hotelDO.setIsActive(1);
				hotelDO.setCreateTime(new Date());
				hotelMapper.insertHotel(hotelDO);
				// 主图
				if (null != imageDO) {
					imageDO.setHotelId(hotelDO.getHotelId());
					imageDO.setExtId(hotelDO.getHotelId());
					imageMapper.insertHotelImage(imageDO);
				}
				// 附加项
				if (!CollectionUtils.isEmpty(hotelAdditionalDOList)) {
					for (HotelAdditionalDO hotelAdditionalDO : hotelAdditionalDOList) {
						hotelAdditionalDO.setHotelId(hotelDO.getHotelId());
					}
					hotelAdditionalMapper.inserHotelAdditional(hotelAdditionalDOList);
				}
				// 酒店政策
				if (null != hotelPolicyDO) {
					hotelPolicyDO.setHotelId(hotelDO.getHotelId());
					hotelPolicyMapper.insertHotelPolicy(hotelPolicyDO);
				}
				// 酒店设施
				if (!CollectionUtils.isEmpty(hotelFacilityDOList)) {
					for (HotelFacilityDO hotelFacilityDO : hotelFacilityDOList) {
						hotelFacilityDO.setHotelId(hotelDO.getHotelId());
					}
					hotelFacilityMapper.inserHotelFacility(hotelFacilityDOList);
				}

			} else if (null != hotelDO.getHotelId()) {
				// 修改酒店基本信息
				hotelMapper.updateHotel(hotelDO);

				// 修改酒店主图
				if (null != imageDO && null != imageDO.getImageId()) {
					imageMapper.updateHotelImage(imageDO);// 根据ID修改
				}else if(null != imageDO && null == imageDO.getImageId()){
					imageMapper.insertHotelImage(imageDO);
				}

				// 修改酒店附加项
				HotelAdditionalDO hotelAdditionalDO = new HotelAdditionalDO();
				hotelAdditionalDO.setHotelId(hotelInfoRequestDTO.getHotelId());
				hotelAdditionalDO.setMerchantCode(hotelInfoRequestDTO.getMerchantCode());
				hotelAdditionalMapper.deleteHotelAdditional(hotelAdditionalDO);
				if (!CollectionUtils.isEmpty(hotelAdditionalDOList)) {
					hotelAdditionalMapper.inserHotelAdditional(hotelAdditionalDOList);
				}
				// 修改酒店政策
				if (null != hotelPolicyDO) {
					hotelPolicyMapper.updateHotelPolicy(hotelPolicyDO);
				}
				// 酒店设施
				hotelFacilityMapper.deleteHotelFacByHotelId(hotelInfoRequestDTO.getHotelId());

				if (!CollectionUtils.isEmpty(facilityList)) {
					hotelFacilityMapper.inserHotelFacility(hotelFacilityDOList);
				}
			}
			hotelInfoResponseDTO.setHotelId(hotelDO.getHotelId());
			responseDTO.setModel(hotelInfoResponseDTO);
			responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
		} catch (Exception e) {
			log.error("saveOrUpdateHotel has error", e);
			throw new ServiceException("saveOrUpdateHotel has error", e);
		}
		return responseDTO;
	}

	/**
	 * 查询酒店基本信息
	 */
	@Override
	public ResponseDTO<HotelBaseInfoRsponseDTO> queryHotelInfoByHotelId(
			HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			HotelDO hotelDO = hotelMapper.queryHotelInfoByHotelId(hotelBaseInfoRequestDTO);
			if (null != hotelDO) {
				// 查询酒店介绍
				HotelBaseInfoRsponseDTO hotelBaseInfoRsponseDTO = new HotelBaseInfoRsponseDTO();
				hotelBaseInfoRsponseDTO.setHotelId(hotelDO.getHotelId());
				hotelBaseInfoRsponseDTO.setHotelName(hotelDO.getHotelName());
				hotelBaseInfoRsponseDTO.setCityCode(hotelDO.getCityCode());
				hotelBaseInfoRsponseDTO.setCityName(hotelDO.getCityName());
				hotelBaseInfoRsponseDTO.setEngHotelName(hotelDO.getEngHotelName());
				hotelBaseInfoRsponseDTO.setBusinessCode(hotelDO.getBusinessCode());
				hotelBaseInfoRsponseDTO.setHotelStar(hotelDO.getHotelStar());
				hotelBaseInfoRsponseDTO.setPhone(hotelDO.getPhone());
				hotelBaseInfoRsponseDTO.setFax(hotelDO.getFax());
				hotelBaseInfoRsponseDTO.setOpeningDate(hotelDO.getOpeningDate());
				hotelBaseInfoRsponseDTO.setDecorationDate(hotelDO.getDecorationDate());
				hotelBaseInfoRsponseDTO.setRoomTotalNum(hotelDO.getRoomTotalNum());
				hotelBaseInfoRsponseDTO.setIntroduction(hotelDO.getIntroduction());
				hotelBaseInfoRsponseDTO.setCancelPolicy(hotelDO.getCancelPolicy());
				hotelBaseInfoRsponseDTO.setHotelAddress(hotelDO.getHotelAddress());
				hotelBaseInfoRsponseDTO.setEngHotelAddress(hotelDO.getEngHotelAddress());
				hotelBaseInfoRsponseDTO.setProductRemark(hotelDO.getProductRemark());
				hotelBaseInfoRsponseDTO.setOrderRemark(hotelDO.getOrderRemark());
				if (StringUtil.isValidString(hotelDO.getTheme())) {
					hotelBaseInfoRsponseDTO.setTheme(hotelDO.getTheme().split(","));
				}
				if (StringUtil.isValidString(hotelDO.getFreeRoomPolicy())) {
					hotelBaseInfoRsponseDTO.setFreeRoomPolicy(hotelDO.getFreeRoomPolicy().split(","));
				}
				if (null != hotelDO.getImagedo()) {
					hotelBaseInfoRsponseDTO.setImageUrl(hotelDO.getImagedo().getImageUrl());
					hotelBaseInfoRsponseDTO.setRealPath(hotelDO.getImagedo().getRealPath());
					hotelBaseInfoRsponseDTO.setImageId(hotelDO.getImagedo().getImageId());
				}
				if (null != hotelDO.getHotelPolicyDO()) {
					hotelBaseInfoRsponseDTO.setCheckInTime(hotelDO.getHotelPolicyDO().getCheckInTime());
					hotelBaseInfoRsponseDTO.setCheckOutTime(hotelDO.getHotelPolicyDO().getCheckOutTime());
					hotelBaseInfoRsponseDTO.setPet(hotelDO.getHotelPolicyDO().getPet());
					hotelBaseInfoRsponseDTO.setOtherPolicy(hotelDO.getHotelPolicyDO().getOtherPolicy());
					String creditCard = hotelDO.getHotelPolicyDO().getCreditCard();
					if (StringUtil.isValidString(creditCard)) {
						hotelBaseInfoRsponseDTO.setCreditCard(creditCard.split(","));
					}
				}
				// 附加项
				List<HotelAdditionalDO> additionalList = hotelDO.getAdditionalList();
				if (!CollectionUtils.isEmpty(additionalList)) {
					List<HotelAdditionalDTO> hotelAdditionalList = new ArrayList<>();
					hotelAdditionalList = PropertyCopyUtil.transferArray(additionalList, HotelAdditionalDTO.class);
					hotelBaseInfoRsponseDTO.setAdditionalList(hotelAdditionalList);
				}
				// 酒店设施
				List<HotelFacilityDO> facilityList = hotelDO.getFacilityList();
				if (!CollectionUtils.isEmpty(facilityList)) {
					List<HotelFacilityDTO> hotelFacilityList = new ArrayList<>();
					hotelFacilityList = PropertyCopyUtil.transferArray(facilityList, HotelFacilityDTO.class);
					hotelBaseInfoRsponseDTO.setFacilityList(hotelFacilityList);
				}
				// 房型
				List<RoomTypeDO> roomTypeList = hotelDO.getRoomTypeList();
				if (!CollectionUtils.isEmpty(roomTypeList)) {
					List<RoomTypeDTO> hotelRoomTypeList = new ArrayList<>();
					RoomTypeDTO roomTypeDTO = null;
					for (RoomTypeDO roomTypeDO : roomTypeList) {
						roomTypeDTO = PropertyCopyUtil.transfer(roomTypeDO, RoomTypeDTO.class);
						if (null != roomTypeDO.getImagedo()) {
							roomTypeDTO.setImageUrl(roomTypeDO.getImagedo().getImageUrl());
							roomTypeDTO.setRealPath(roomTypeDO.getImagedo().getRealPath());
						}
						roomTypeService.assembleBedTypeList(roomTypeDO, roomTypeDTO);
						hotelRoomTypeList.add(roomTypeDTO);
					}
					hotelBaseInfoRsponseDTO.setRoomTypeList(hotelRoomTypeList);
				}
				responseDTO.setModel(hotelBaseInfoRsponseDTO);
			}
			return responseDTO;
		} catch (Exception e) {
			log.error("queryHotelInfoByHotelId", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 删除酒店基本信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO deleteHotel(HotelDO hotelDO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			hotelMapper.updateHotel(hotelDO);
			responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
			// 推送增量
			IncrementDTO incrementDTO = new IncrementDTO();
			incrementDTO.setMerchantCode(hotelDO.getMerchantCode());
			incrementDTO.setMHotelId(hotelDO.getHotelId());
			List<IncrementDTO> incrementDTOList = new ArrayList<>();
			incrementDTOList.add(incrementDTO);

			String url = URLSplitUtil.getUrl(incrementConfig);
			IncrementThread incrementThread = new IncrementThread(incrementDTOList, url, incrementService);
			threadPoolTaskExecutor.execute(incrementThread);
		} catch (Exception e) {
			log.error("deleteHotel has error", e);
			throw new ServiceException("deleteHotel has error", e);
		}
		return responseDTO;
	}

	/**
	 * 查询商家常用酒店
	 */
	@Override
	public ResponseDTO<CommonUsedHotelResponseDTO> queryCommonUsedHotel(CommonUsedHotelRequestDTO queryCommonHotelDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			CommonUsedHotelResponseDTO commonUsedHotelResponseDTO = new CommonUsedHotelResponseDTO();
			// 每页显示记录数
			PageHelper.startPage(queryCommonHotelDTO.getCurrentPage(), queryCommonHotelDTO.getPageSize());
			queryCommonHotelDTO.setIsActive(1);
			List<HotelDO> hotelList = hotelMapper.queryCommonUsedHotel(queryCommonHotelDTO);
			if (!CollectionUtils.isEmpty(hotelList)) {
				List<SimpleHotelDTO> simplhotelList = null;
				simplhotelList = PropertyCopyUtil.transferArray(hotelList, SimpleHotelDTO.class);
				commonUsedHotelResponseDTO.setHotelList(simplhotelList);
			}
			responseDTO.setModel(commonUsedHotelResponseDTO);
		} catch (Exception e) {
			log.error("queryPricePlanInfo", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 获得酒店列表
	 */
	@Override
	public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> getHotelList(HotelListQueryDTO hotelListQueryDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			hotelListQueryDTO.setIsActive(1);
			PageHelper.startPage(hotelListQueryDTO.getCurrentPage(), hotelListQueryDTO.getPageSize());
			List<HotelDO> listHotel = hotelMapper.getHotelList(hotelListQueryDTO);
			List<HotelListResponseDTO> HotelListResponseDTOlist = new ArrayList<HotelListResponseDTO>();
			PageInfo<HotelDO> pageInfo = new PageInfo<>(listHotel);
			HotelListResponseDTOlist = PropertyCopyUtil.transferArray(listHotel, HotelListResponseDTO.class);
			PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
			paginationSupportDTO.setTotalCount(pageInfo.getTotal());
			paginationSupportDTO.setTotalPage(pageInfo.getPages());
			paginationSupportDTO.setCurrentPage(pageInfo.getPageNum());
			paginationSupportDTO.setPageSize(pageInfo.getPageSize());
			paginationSupportDTO.setItemList(HotelListResponseDTOlist);
			responseDTO.setModel(paginationSupportDTO);
		} catch (Exception e) {
			log.error("getHotelList", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	public ResponseDTO transferHotelInfoData(TransferHotelInfoDataRequestDTO transferHotelInfoDataRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		// 查询酒店列表
		if (null == transferHotelInfoDataRequestDTO || (null == transferHotelInfoDataRequestDTO.getHotels()
				&& !StringUtil.isValidString(transferHotelInfoDataRequestDTO.getCityCode())
				&& !StringUtil.isValidString(transferHotelInfoDataRequestDTO.getHotelName()))) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
			return responseDTO;
		}
		String hotelListUrl = URLSplitUtil.getHotelBaseInfoUrl(hotelBaseInfoConfig, "remote/hotelinfo/queryHotelForPage");
		HotelQueryRequestDTO hotelQueryRequestDTO = new HotelQueryRequestDTO();
		hotelQueryRequestDTO.setCountryCode(transferHotelInfoDataRequestDTO.getCountryCode());
		hotelQueryRequestDTO.setCityCode(transferHotelInfoDataRequestDTO.getCityCode());
		hotelQueryRequestDTO.setHotelList(Arrays.asList(transferHotelInfoDataRequestDTO.getHotels()));
		hotelQueryRequestDTO.setHotelName(transferHotelInfoDataRequestDTO.getHotelName());
		String hotelListResult = null;
		try {
			hotelListResult = HttpClientUtil.postJson(hotelListUrl, JSON.toJSONString(hotelQueryRequestDTO));
		} catch (IOException e) {
			log.error("Query hotelList has error", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
			return responseDTO;
		}
		try {
			ResponseDTO hotelListResponse = JSON.parseObject(hotelListResult, ResponseDTO.class);
			if (null == hotelListResponse || ResultCodeEnum.FAILURE.code == hotelListResponse.getResult()) {
				log.error("Query hotellist response is empty.");
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
				return responseDTO;
			}
			PaginationSupportDTO paginationSupportDTO = JSON.parseObject(JSON.toJSONString(hotelListResponse.getModel()), PaginationSupportDTO.class);
			if (null == paginationSupportDTO || CollectionUtils.isEmpty(paginationSupportDTO.getItemList())) {
				log.error("Query hotellist result is empty.");
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
				return responseDTO;
			}
			//酒店列表
			List<HotelSaasDTO> hotelSaasDTOList = JSON.parseArray(JSON.toJSONString(paginationSupportDTO.getItemList()), HotelSaasDTO.class);
			CountDownLatch countDownLatch = new CountDownLatch(hotelSaasDTOList.size());
			//酒店详情URL
			String hotelDetailURL = URLSplitUtil.getHotelBaseInfoUrl(hotelBaseInfoConfig, "remote/hotelinfo/queryHotelDetail/");
			List<Future<TransferHotelInfoResult>> futureList = new ArrayList<>();
			for (HotelSaasDTO hotelSaasDTO : hotelSaasDTOList) {
				//并行查询酒店详情接口
				TransferHotelInfoThread transferHotelInfoThread = new TransferHotelInfoThread(countDownLatch,hotelDetailURL,hotelSaasDTO,this);
				Future<TransferHotelInfoResult> future = transferHotelInfoExecutor.submit(transferHotelInfoThread);
				futureList.add(future);
			}
			//等待子线程执行完成
			countDownLatch.await();
			for(Future<TransferHotelInfoResult> future : futureList){
				log.info("Transfer hotel info result : " + JSON.toJSONString(future.get()));
			}
			//开始第二页查询
			startTransferSecondPage(paginationSupportDTO,hotelBaseInfoConfig,hotelListUrl,hotelQueryRequestDTO,responseDTO,hotelDetailURL);
		} catch (Exception e) {
			log.error("Transfer data has error", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
			return responseDTO;
		}
		return responseDTO;
	}

	/**
	 * 分页查询
	 */
	private ResponseDTO startTransferSecondPage(PaginationSupportDTO paginationSupportDTO,HotelBaseInfoConfig hotelBaseInfoConfig,String hotelListUrl,
								  HotelQueryRequestDTO hotelQueryRequestDTO,ResponseDTO responseDTO, String hotelDetailURL){
		//开始第二页查询
		int totalPage = paginationSupportDTO.getTotalPage();//总页数
		for(int i = 2; i <= totalPage; i++){
			String hotelListResult = null;
			try {
				hotelQueryRequestDTO.setPageNo(i);
				hotelListResult = HttpClientUtil.postJson(hotelListUrl, JSON.toJSONString(hotelQueryRequestDTO));
			} catch (IOException e) {
				log.error("Query hotelList has error", e);
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
				return responseDTO;
			}
			try {
				ResponseDTO hotelListResponse = JSON.parseObject(hotelListResult, ResponseDTO.class);
				if (null == hotelListResponse || ResultCodeEnum.FAILURE.code == hotelListResponse.getResult()) {
					log.error("Query hotellist response is empty.");
					responseDTO.setResult(ResultCodeEnum.FAILURE.code);
					responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
					responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
					return responseDTO;
				}
				PaginationSupportDTO paginationSupport = JSON.parseObject(JSON.toJSONString(hotelListResponse.getModel()), PaginationSupportDTO.class);
				if (null == paginationSupport || CollectionUtils.isEmpty(paginationSupport.getItemList())) {
					log.error("Query hotellist result is empty.");
					responseDTO.setResult(ResultCodeEnum.FAILURE.code);
					responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
					responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
					return responseDTO;
				}
				//酒店列表
				List<HotelSaasDTO> hotelSaasDTOList = JSON.parseArray(JSON.toJSONString(paginationSupport.getItemList()), HotelSaasDTO.class);
				CountDownLatch countDownLatch = new CountDownLatch(hotelSaasDTOList.size());
				//酒店详情URL
				List<Future<TransferHotelInfoResult>> futureList = new ArrayList<>();
				for (HotelSaasDTO hotelSaasDTO : hotelSaasDTOList) {
					//并行查询酒店详情接口
					TransferHotelInfoThread transferHotelInfoThread = new TransferHotelInfoThread(countDownLatch,hotelDetailURL,hotelSaasDTO,this);
					Future<TransferHotelInfoResult> future = transferHotelInfoExecutor.submit(transferHotelInfoThread);
					futureList.add(future);
				}
				//等待子线程执行完成
				countDownLatch.await();
				for(Future<TransferHotelInfoResult> future : futureList){
					log.info("Transfer hotel info result : " + JSON.toJSONString(future.get()));
				}
			} catch (Exception e) {
				log.error("Transfer data has error", e);
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
				return responseDTO;
			}
		}
		return responseDTO;
	}


	//@Transactional(propagation = Propagation.REQUIRED)
	public TransferHotelInfoResult saveTransferResult(HotelSaasDTO hotelSaasDTO){
		TransferHotelInfoResult transferHotelInfoResult = new TransferHotelInfoResult();
		//获取事务定义
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		//设置事务
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		//获得事务状态
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(def);
		try {
			transferHotelInfoResult.setResult(ResultCodeEnum.FAILURE.code);
			transferHotelInfoResult.setOldHotelId(hotelSaasDTO.getHotelId());
			if(null == hotelSaasDTO || null == hotelSaasDTO.getHotelId()){
                log.error("Parameter has error");
                return transferHotelInfoResult;
            }
			//酒店基础信息
			HotelDO hotelDO = new HotelDO();
			hotelDO.setCountry(hotelSaasDTO.getCountryCode());
			hotelDO.setHotelName(hotelSaasDTO.getHotelName());
			hotelDO.setCityCode(hotelSaasDTO.getCityCode());
			hotelDO.setCityName(hotelSaasDTO.getCityName());
			hotelDO.setEngHotelName(hotelSaasDTO.getEngHotelName());
			hotelDO.setBusinessCode(hotelSaasDTO.getBusinessCode());
			//酒店星级
			assembleHotelStar(hotelSaasDTO,hotelDO);
			hotelDO.setPhone(hotelSaasDTO.getTelePhone());
			hotelDO.setFax(hotelSaasDTO.getFax());
			//开业时间
			if(null != hotelSaasDTO.getPraciceDate()){
                hotelDO.setOpeningDate(DateUtil.dateToString(hotelSaasDTO.getPraciceDate()));
            }
			//装修时间
			if(null != hotelSaasDTO.getFitmentDate()){
                hotelDO.setDecorationDate(DateUtil.dateToString(hotelSaasDTO.getFitmentDate()));
            }
			//房间总数
			if(StringUtil.isValidString(hotelSaasDTO.getLayerCount())){
                hotelDO.setRoomTotalNum(Integer.parseInt(hotelSaasDTO.getLayerCount()));
            }
			HotelPolicyDO hotelPolicyDO = new HotelPolicyDO();
			hotelPolicyDO.setCheckInTime(hotelSaasDTO.getCheckInTime());
			hotelPolicyDO.setCheckOutTime(hotelSaasDTO.getCheckOutTime());
			hotelDO.setHotelPolicyDO(hotelPolicyDO);
			//酒店主题 宠物 信用卡 酒店设施
			assembleHotelFeature(hotelSaasDTO,hotelDO);
			hotelDO.setIntroduction(hotelSaasDTO.getIntroduce());
			hotelDO.setHotelAddress(hotelSaasDTO.getHotelAddress());
			hotelDO.setEngHotelAddress(hotelSaasDTO.getEngHotelAddress());

			//是否有效
			if(StringUtil.isValidString(hotelSaasDTO.getIsActive())){
                hotelDO.setIsActive(Integer.parseInt(hotelSaasDTO.getIsActive()));
            }
			hotelDO.setCreateTime(new Date());
			//新增酒店
			hotelMapper.insertHotel(hotelDO);
			//新增主图
			if(StringUtil.isValidString(hotelSaasDTO.getMainImage())){
				ImageDO imageDO = new ImageDO();
				imageDO.setIsMainImage(1);
				imageDO.setHotelId(hotelDO.getHotelId());
				imageDO.setExtId(hotelDO.getHotelId());
				imageDO.setImageUrl(hotelSaasDTO.getMainImage());
				imageDO.setRealPath(hotelSaasDTO.getMainImage());//无法得到realpath
				imageDO.setImageType(ImageTypeEnum.OUTVIEW.key);
				imageMapper.insertHotelImage(imageDO);
			}
			//酒店政策
			hotelDO.getHotelPolicyDO().setHotelId(hotelDO.getHotelId());
			hotelPolicyMapper.insertHotelPolicy(hotelDO.getHotelPolicyDO());
			//酒店设施
			if(!CollectionUtils.isEmpty(hotelDO.getFacilityList())){
				for (HotelFacilityDO hotelFacilityDO : hotelDO.getFacilityList()) {
					hotelFacilityDO.setHotelId(hotelDO.getHotelId());
				}
				hotelFacilityMapper.inserHotelFacility(hotelDO.getFacilityList());
			}
			//保存酒店映射关系
			HotelMappingDO hotelMappingDO = new HotelMappingDO();
			hotelMappingDO.setOldHotelId(hotelSaasDTO.getHotelId());
			hotelMappingDO.setNewHotelId(hotelDO.getHotelId());
			hotelInfoMappingMapper.insertHotelMapping(hotelMappingDO);
			//房型
			assembleRoomType(hotelSaasDTO,hotelDO);
			//批量保存房型
			if(!CollectionUtils.isEmpty(hotelDO.getRoomTypeList())){
				roomTypeMapper.insertRoomTypes(hotelDO.getRoomTypeList());
				//房型图片
				List<ImageDO> imageDOS = new ArrayList<>();
				for(RoomTypeDO roomTypeDO : hotelDO.getRoomTypeList()){
					if(!CollectionUtils.isEmpty(roomTypeDO.getImageDOList())){
						for(ImageDO image : roomTypeDO.getImageDOList()){
							image.setExtId(roomTypeDO.getRoomTypeId());
							imageDOS.add(image);
						}
					}
				}
				if(!CollectionUtils.isEmpty(imageDOS)){
					imageMapper.batchSaveHotelImage(imageDOS);
				}
			}
			//会议室
			assembleMeetingRoom(hotelSaasDTO,hotelDO);
			//批量保存会议室 投放类型 会议室图片
			if(!CollectionUtils.isEmpty(hotelDO.getMeetingRoomDOList())){
				meetingRoomMapper.batchInsertMeetingRoom(hotelDO.getMeetingRoomDOList());
				//投放类型  会议室图片
				List<PutTypeDO> putTypeDOList = new ArrayList<>();
				List<ImageDO> imageDOS = new ArrayList<>();
				List<MeetingRoomDO> meetingRoomDOList = hotelDO.getMeetingRoomDOList();
				for (MeetingRoomDO meetingRoomDO : meetingRoomDOList) {
					if(!CollectionUtils.isEmpty(meetingRoomDO.getPutTypeDOList())){
						List<PutTypeDO> putTypeDOS = meetingRoomDO.getPutTypeDOList();
						for (PutTypeDO putTypeDO : putTypeDOS) {
							putTypeDO.setMeetingId(meetingRoomDO.getMeetingId());
							putTypeDOList.add(putTypeDO);
						}
					}
					if(!CollectionUtils.isEmpty(meetingRoomDO.getImageDOList())){
						for(ImageDO image : meetingRoomDO.getImageDOList()){
							image.setExtId(meetingRoomDO.getMeetingId());
							imageDOS.add(image);
						}
					}
				}
				if(!CollectionUtils.isEmpty(putTypeDOList)){
					putTypeMapper.batchInsertPutType(putTypeDOList);
				}
				if(!CollectionUtils.isEmpty(imageDOS)){
					imageMapper.batchSaveHotelImage(imageDOS);
				}
			}
			//图片列表
			if(!CollectionUtils.isEmpty(hotelSaasDTO.getHotelImgList())){
				List<ImageDO> imageDOS = new ArrayList<>();
				ImageDO image = null;
				for(HotelImgDTO hotelImgDTO : hotelSaasDTO.getHotelImgList()){
					image = null;
					if(String.valueOf(ImageTypeSaasEnum.OUTVIEW.key).equals(hotelImgDTO.getImgType())){//外观图
						image = new ImageDO();
						image.setImageType(ImageTypeEnum.OUTVIEW.key);
					}else if(String.valueOf(ImageTypeSaasEnum.INVIEW.key).equals(hotelImgDTO.getImgType())){//大堂图
						image = new ImageDO();
						image.setImageType(ImageTypeEnum.INVIEW.key);
					}else if(String.valueOf(ImageTypeSaasEnum.OTHERVIEW.key).equals(hotelImgDTO.getImgType())){//其他
						image = new ImageDO();
						image.setImageType(ImageTypeEnum.OTHERVIEW.key);
					}
					if(null != image){
						image.setExtId(hotelDO.getHotelId());
						//image.setImageSmallUrl();
						//image.setImageMiddleUrl();
						//image.setImageLargeUrl();
						image.setIsMainImage(0);
						image.setHotelId(hotelDO.getHotelId());
						image.setImageUrl(hotelImgDTO.getImgurl());
						image.setRealPath(hotelImgDTO.getImgurl());
						imageDOS.add(image);
					}
				}
				if(!CollectionUtils.isEmpty(imageDOS)){
					imageMapper.batchSaveHotelImage(imageDOS);
				}
			}
			transferHotelInfoResult.setResult(ResultCodeEnum.SUCCESS.code);
			transferHotelInfoResult.setNewHotelId(hotelDO.getHotelId());
			//提交事务
			platformTransactionManager.commit(transactionStatus);
		} catch (Exception e) {
			platformTransactionManager.rollback(transactionStatus);
			log.error("saveTransferResult",e);
			throw new ServiceException("saveTransferResult",e);
		}
		return transferHotelInfoResult;
	}

	/**
	 * 房型
	 * @param hotelSaasDTO
	 * @param hotelDO
	 */
	private void assembleRoomType(HotelSaasDTO hotelSaasDTO, HotelDO hotelDO){
		//房型
		if (!CollectionUtils.isEmpty(hotelSaasDTO.getRoomTypeDTOList())) {
			List<RoomTypeSaasDTO> roomTypeDTOList = hotelSaasDTO.getRoomTypeDTOList();
			List<RoomTypeDO> roomTypeList = new ArrayList<RoomTypeDO>();
			for (int i = 0; i< roomTypeDTOList.size(); i++) {
				RoomTypeSaasDTO roomTypeSaasDTO = roomTypeDTOList.get(i);
				RoomTypeDO roomTypeDO = new RoomTypeDO();
				roomTypeDO.setHotelId(hotelDO.getHotelId());
				roomTypeDO.setRoomTypeName(roomTypeSaasDTO.getRoomTypeName());
				roomTypeDO.setFloor(roomTypeSaasDTO.getRoomFloor());
				roomTypeDO.setArea(roomTypeSaasDTO.getRoomArea());
				roomTypeDO.setMaxPerson(roomTypeSaasDTO.getMaxPerson());
				if(null != roomTypeSaasDTO.getRoomNum()){
					roomTypeDO.setRoomNum(String.valueOf(roomTypeSaasDTO.getRoomNum()));
				}
				//窗户
				//roomTypeDO.setIsWindow();
				roomTypeDO.setIsWifi(0);
				//宽带
				if(null != roomTypeSaasDTO.getBroadNet() && BroadBandSassEnum.CHARGE.key == roomTypeSaasDTO.getBroadNet()){
					roomTypeDO.setIsWired(BroadBandEnum.CHARGE.key);
				}else if(null != roomTypeSaasDTO.getBroadNet() && BroadBandSassEnum.FREE.key == roomTypeSaasDTO.getBroadNet()){
					roomTypeDO.setIsWired(BroadBandEnum.FREE.key);
				}else if(null != roomTypeSaasDTO.getBroadNet() && BroadBandSassEnum.NONE.key == roomTypeSaasDTO.getBroadNet()){
					roomTypeDO.setIsWired(BroadBandEnum.NONE.key);
				}
				roomTypeDO.setIsExtraBed(roomTypeSaasDTO.getAddBed());//是否可加床 0.不可加床  1.可加床
				roomTypeDO.setSort(99);
				roomTypeDO.setIsActive(Integer.parseInt(roomTypeSaasDTO.getIsActive()));

				if (!CollectionUtils.isEmpty(roomTypeSaasDTO.getRoomTypeBedTypeRelaDTOList())) {
					StringBuilder bedTypeStringBuilder = new StringBuilder();
					StringBuilder bedTypeDesBuilder = new StringBuilder();//单床(1.2m*2m;1张)
					for (RoomTypeBedTypeRelaDTO roomTypeBedTypeRelaDTO : roomTypeSaasDTO.getRoomTypeBedTypeRelaDTOList()) {
						String width = "";
						String length = "";
						if(StringUtil.isValidString(roomTypeBedTypeRelaDTO.getBedSize())){
							width = roomTypeBedTypeRelaDTO.getBedSize().split("\\*")[0];
							length = roomTypeBedTypeRelaDTO.getBedSize().split("\\*")[1];
						}
						if (BedTypeSaasEnum.SINGLE.key.equals(roomTypeBedTypeRelaDTO.getBedTypecd())) {//单床
							bedTypeStringBuilder.append(BedTypeEnum.SINGLE.key).append(",");
							bedTypeDesBuilder.append(BedTypeEnum.SINGLE.value).append("(").append(width).append("m*").
									append(length).append("m;").append(roomTypeBedTypeRelaDTO.getBedNum()).append("张)").append(",");
						} else if (BedTypeSaasEnum.KING.key.equals(roomTypeBedTypeRelaDTO.getBedTypecd())) {//大床
							bedTypeStringBuilder.append(BedTypeEnum.KING.key).append(",");
							bedTypeDesBuilder.append(BedTypeEnum.KING.value).append("(").append(width).append("m*").
									append(length).append("m;").append(roomTypeBedTypeRelaDTO.getBedNum()).append("张)").append(",");
						} else if (BedTypeSaasEnum.TWIN.key.equals(roomTypeBedTypeRelaDTO.getBedTypecd())) {//双床
							bedTypeStringBuilder.append(BedTypeEnum.TWIN.key).append(",");
							bedTypeDesBuilder.append(BedTypeEnum.TWIN.value).append("(").append(width).append("m*").
									append(length).append("m;").append(roomTypeBedTypeRelaDTO.getBedNum()).append("张)").append(",");
						} else if (BedTypeSaasEnum.THREE.key.equals(roomTypeBedTypeRelaDTO.getBedTypecd())) {//三床
							bedTypeStringBuilder.append(BedTypeEnum.THREE.key).append(",");
							bedTypeDesBuilder.append(BedTypeEnum.THREE.value).append("(").append(width).append("m*").
									append(length).append("m;").append(roomTypeBedTypeRelaDTO.getBedNum()).append("张)").append(",");
						} else if (BedTypeSaasEnum.FOUR.key.equals(roomTypeBedTypeRelaDTO.getBedTypecd())) {//四床
							bedTypeStringBuilder.append(BedTypeEnum.FOUR.key).append(",");
							bedTypeDesBuilder.append(BedTypeEnum.FOUR.value).append("(").append(width).append("m*").
									append(length).append("m;").append(roomTypeBedTypeRelaDTO.getBedNum()).append("张)").append(",");
						}
					}
					if(bedTypeStringBuilder.length() > 0){
						roomTypeDO.setBedType(bedTypeStringBuilder.toString().substring(0,bedTypeStringBuilder.toString().length() - 1));
						roomTypeDO.setBedDescription(bedTypeDesBuilder.toString().substring(0,bedTypeDesBuilder.toString().length() - 1));
					}
				}
				//房型图片
				if(!CollectionUtils.isEmpty(roomTypeSaasDTO.getRoomTypeImgDTOList())){
					List<ImageDO> imageDOS = new ArrayList<>();
					ImageDO roomTypeImage = null;
					for(HotelImgDTO hotelImgDTO : roomTypeSaasDTO.getRoomTypeImgDTOList()){
						roomTypeImage = new ImageDO();
						roomTypeImage.setImageType(ImageTypeEnum.ROOMVIEW.key);
						roomTypeImage.setRealPath(hotelImgDTO.getImgurl());
						roomTypeImage.setImageUrl(hotelImgDTO.getImgurl());
						roomTypeImage.setHotelId(hotelDO.getHotelId());
						roomTypeImage.setIsMainImage(0);
						//roomTypeImage.setImageLargeUrl();
						//roomTypeImage.setImageMiddleUrl();
						//roomTypeImage.setImageSmallUrl();
						imageDOS.add(roomTypeImage);
					}
					roomTypeDO.setImageDOList(imageDOS);
				}
				roomTypeList.add(roomTypeDO);
			}
			hotelDO.setRoomTypeList(roomTypeList);
		}
	}

	/**
	 * 酒店设施 -- > 酒店主题 、信用卡
	 * @param hotelSaasDTO
	 * @param hotelDO
	 */
	private void assembleHotelFeature(HotelSaasDTO hotelSaasDTO, HotelDO hotelDO) {
		if (!CollectionUtils.isEmpty(hotelSaasDTO.getHotelFeatureList())) {
			StringBuilder themeBuilder = new StringBuilder();
			StringBuilder creditCard = new StringBuilder();
			List<HotelFacilityDO> facilityList = new ArrayList<>();
			for (HotelFeatureDTO hotelFeatureDTO : hotelSaasDTO.getHotelFeatureList()) {
				//酒店主题
				if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.VILLA.key) {//度假村或别墅
					themeBuilder.append(ThemeEnum.VILLA.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.FLAT.key) {//公寓
					themeBuilder.append(ThemeEnum.FLAT.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.HOTEL.key) {//酒店
					themeBuilder.append(ThemeEnum.HOTEL.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.HOMESTAY.key) {//民宿或旅社
					themeBuilder.append(ThemeEnum.HOMESTAY.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.BUSINESS_METTING.key) {//商务会议
					themeBuilder.append(ThemeEnum.BUSINESS_METTING.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.HOLIDAY.key) {//温泉度假
					themeBuilder.append(ThemeEnum.HOLIDAY.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.SEASIDE.key) {//海滨休闲
					themeBuilder.append(ThemeEnum.SEASIDE.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.THEMATIC.key) {//主题特色
					themeBuilder.append(ThemeEnum.THEMATIC.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.ENTERTAINMENT.key) {//娱乐休闲
					themeBuilder.append(ThemeEnum.ENTERTAINMENT.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.PARENT_CHILD_TRAVEL.key) {//亲子出游
					themeBuilder.append(ThemeEnum.PARENT_CHILD_TRAVEL.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.INN.key) {//客栈
					themeBuilder.append(ThemeEnum.INN.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.AGRITAINMENT.key) {//农家乐
					themeBuilder.append(ThemeEnum.AGRITAINMENT.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.YOUTH_HOSTEL.key) {//青年旅舍
					themeBuilder.append(ThemeEnum.YOUTH_HOSTEL.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.SCENIC_HOTEL.key) {//景区酒店
					themeBuilder.append(ThemeEnum.SCENIC_HOTEL.key).append(",");
				} else if (hotelFeatureDTO.getFeatureId() == ThemeSaasEnum.OTHER.key) {//其他
					themeBuilder.append(ThemeEnum.OTHER.key).append(",");
				}

				//宠物
				if(hotelFeatureDTO.getFeatureId() == PetSaasEnum.ALLOW.key){
					hotelDO.getHotelPolicyDO().setPet(1);
				}else if(hotelFeatureDTO.getFeatureId() == PetSaasEnum.NOT_ALLOW.key){
					hotelDO.getHotelPolicyDO().setPet(0);
				}
				//信用卡
				if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.UNION_PAY.key){//国内发行银联卡
					creditCard.append(CreditCardEnum.UNION_PAY.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.VISA.key){//威士(VISA)
					creditCard.append(CreditCardEnum.VISA.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.MASTER.key){//万事达(Master)
					creditCard.append(CreditCardEnum.MASTER.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.AMEX.key){//运通(AMEX)
					creditCard.append(CreditCardEnum.AMEX.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.DINERS_CLUB.key){//大来(Diners Club)
					creditCard.append(CreditCardEnum.DINERS_CLUB.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.EURO.key){//Euro卡
					creditCard.append(CreditCardEnum.EURO.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.EURO_6000.key){//Euro 6000卡
					creditCard.append(CreditCardEnum.EURO_6000.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.EC.key){//EC借记卡
					creditCard.append(CreditCardEnum.EC.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.VISA_DEBIT.key){//威士电子借记卡
					creditCard.append(CreditCardEnum.VISA_DEBIT.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.MAESTRO.key){//Maestro卡
					creditCard.append(CreditCardEnum.MAESTRO.key).append(",");
				}else if(hotelFeatureDTO.getFeatureId() == CreditCardSaasEnum.JESSE.key){//吉士美卡
					creditCard.append(CreditCardEnum.JESSE.key).append(",");
				}
				//酒店设施
				if(Constant.networkMap.containsKey(hotelFeatureDTO.getFeatureName())){
					HotelFacilityDO hotelFacilityDO = new HotelFacilityDO();
					hotelFacilityDO.setFacilityType(HotelFeatureEnum.NETWORK.key);
					hotelFacilityDO.setFacilityName(hotelFeatureDTO.getFeatureName());
					facilityList.add(hotelFacilityDO);
				}else if(Constant.stopCarMap.containsKey(hotelFeatureDTO.getFeatureName())){
					HotelFacilityDO hotelFacilityDO = new HotelFacilityDO();
					hotelFacilityDO.setFacilityType(HotelFeatureEnum.PARKING.key);
					hotelFacilityDO.setFacilityName(hotelFeatureDTO.getFeatureName());
					facilityList.add(hotelFacilityDO);
				}else if(Constant.foodMap.containsKey(hotelFeatureDTO.getFeatureName())){
					HotelFacilityDO hotelFacilityDO = new HotelFacilityDO();
					hotelFacilityDO.setFacilityType(HotelFeatureEnum.DINING_FEATURE.key);
					hotelFacilityDO.setFacilityName(hotelFeatureDTO.getFeatureName());
					facilityList.add(hotelFacilityDO);
				}else if(Constant.hotelMap.containsKey(hotelFeatureDTO.getFeatureName())){
					HotelFacilityDO hotelFacilityDO = new HotelFacilityDO();
					hotelFacilityDO.setFacilityType(HotelFeatureEnum.HOTEL_SERVICE.key);
					hotelFacilityDO.setFacilityName(hotelFeatureDTO.getFeatureName());
					facilityList.add(hotelFacilityDO);
				}else if(Constant.roomMap.containsKey(hotelFeatureDTO.getFeatureName())){
					HotelFacilityDO hotelFacilityDO = new HotelFacilityDO();
					hotelFacilityDO.setFacilityType(HotelFeatureEnum.ROOM.key);
					hotelFacilityDO.setFacilityName(hotelFeatureDTO.getFeatureName());
					facilityList.add(hotelFacilityDO);
				}else if(Constant.foodTypeMap.containsKey(hotelFeatureDTO.getFeatureName())){
					HotelFacilityDO hotelFacilityDO = new HotelFacilityDO();
					hotelFacilityDO.setFacilityType(HotelFeatureEnum.MEAL_TYPE.key);
					hotelFacilityDO.setFacilityName(hotelFeatureDTO.getFeatureName());
					facilityList.add(hotelFacilityDO);
				}
			}
			if(themeBuilder.length() > 0){
				hotelDO.setTheme(themeBuilder.toString().substring(0,themeBuilder.toString().length() -  1));
			}
			if(creditCard.length() > 0){
				hotelDO.getHotelPolicyDO().setCreditCard(creditCard.toString().substring(0,creditCard.toString().length() - 1));
			}

			if(!CollectionUtils.isEmpty(facilityList)){
				hotelDO.setFacilityList(facilityList);
			}
		}
	}

	/**
	 * 酒店星级
	 * @param hotelSaasDTO
	 * @param hotelDO
	 */
	private void assembleHotelStar(HotelSaasDTO hotelSaasDTO, HotelDO hotelDO){
		if(StringUtil.isValidString(hotelSaasDTO.getHotelStar())){
			if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.BELOWTWOSTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.BELOWTWOSTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.QUISATWOSTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.QUISATWOSTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.TWOSTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.TWOSTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.QUISATHREESTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.QUISATHREESTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.THREESTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.THREESTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.QUISAFOURSTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.QUISAFOURSTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.FOURSTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.FOURSTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.QUISAFIVESTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.QUISAFIVESTAR.key);
			}else if(Integer.parseInt(hotelSaasDTO.getHotelStar()) == HotelSaasStarEnum.FIVESTAR.key){
				hotelDO.setHotelStar(HotelStarEnum.FIVESTAR.key);
			}
		}
	}

	/**
	 * 会议室
	 * @param hotelSaasDTO
	 * @param hotelDO
	 */
	private void assembleMeetingRoom(HotelSaasDTO hotelSaasDTO, HotelDO hotelDO) {
		// 查出的会议室进行转换
		if (!CollectionUtils.isEmpty(hotelSaasDTO.getMeetingInfoDTOList())) {
			List<MeetingTypeInfoDTO> meetingInfoDTOList = hotelSaasDTO.getMeetingInfoDTOList();
			List<MeetingRoomDO> meetingRoomDOList = new ArrayList<MeetingRoomDO>();
			MeetingRoomDO meetingRoomDO = null;
			for (MeetingTypeInfoDTO meetingTypeInfoDTO : meetingInfoDTOList) {
				meetingRoomDO = new MeetingRoomDO();
				meetingRoomDO.setHotelId(hotelDO.getHotelId());
				meetingRoomDO.setMeetingName(meetingTypeInfoDTO.getMeetingTypeName());
				meetingRoomDO.setPillar(meetingTypeInfoDTO.getFrameIn());
				meetingRoomDO.setCapacity(String.valueOf(meetingTypeInfoDTO.getMinPeoples()) + "-" + String.valueOf(meetingTypeInfoDTO.getMaxPeoples()));
				meetingRoomDO.setArea(meetingTypeInfoDTO.getArea());
				meetingRoomDO.setFloor(String.valueOf(meetingTypeInfoDTO.getLayer()));
				meetingRoomDO.setMeetingRoomLong(String.valueOf(meetingTypeInfoDTO.getMeetingL()));
				meetingRoomDO.setMeetingRoomHigh(String.valueOf(meetingTypeInfoDTO.getMeetingH()));
				meetingRoomDO.setMeetingRoomWidth(String.valueOf(meetingTypeInfoDTO.getMeetingW()));
				// 早上时间截取  0600-1100
				String [] morningSectionArray = assembleTime(meetingTypeInfoDTO.getMorningSection());
				if(null != morningSectionArray && morningSectionArray.length > 0){
					meetingRoomDO.setMorningStartTime(morningSectionArray[0]);
					meetingRoomDO.setMorningEndTime(morningSectionArray[1]);
				}
				// 中午时间截取
				String [] afternoonSectionArray = assembleTime(meetingTypeInfoDTO.getAfternoonSection());
				if(null != afternoonSectionArray && afternoonSectionArray.length > 0){
					meetingRoomDO.setAfternoonStartTime(afternoonSectionArray[0]);
					meetingRoomDO.setAfternoonEndTime(afternoonSectionArray[1]);
				}
				// 晚上时间截取
				String [] nightSectionArray = assembleTime(meetingTypeInfoDTO.getNightSection());
				if(null != nightSectionArray && nightSectionArray.length > 0){
					meetingRoomDO.setNightStartTime(nightSectionArray[0]);
					meetingRoomDO.setNightEndTime(nightSectionArray[1]);
				}
				// 全天时间截取
				String [] allDaySectionArray = assembleTime(meetingTypeInfoDTO.getAllDaySection());
				if(null != allDaySectionArray && allDaySectionArray.length > 0){
					meetingRoomDO.setAllDayStartTime(allDaySectionArray[0]);
					meetingRoomDO.setAllDayEndTime(allDaySectionArray[1]);
				}
				meetingRoomDO.setRemark(meetingTypeInfoDTO.getNotes());
				meetingRoomDO.setIsActive(meetingTypeInfoDTO.getIsActive());

				//摆放类型
				if(StringUtil.isValidString(meetingTypeInfoDTO.getPutTypes())){
					List<PutTypeSassDTO> putTypeSaasDTOList = JSON.parseArray(meetingTypeInfoDTO.getPutTypes(),PutTypeSassDTO.class);
					List<PutTypeDO> putTypeDOList = new ArrayList<>();
					PutTypeDO putTypeDO = null;
					for (PutTypeSassDTO putTypeSassDTO : putTypeSaasDTOList) {
						putTypeDO = new PutTypeDO();
						putTypeDO.setApplicableNumber(putTypeSassDTO.getPeopleNum());
						if (String.valueOf(PutTypeSaasEnum.THEATER.key).equals(putTypeSassDTO.getPutTypeKey())){//剧场式
							putTypeDO.setPutType(PutTypeEnum.THEATRE.key);
						}else if(String.valueOf(PutTypeSaasEnum.DESK.key).equals(putTypeSassDTO.getPutTypeKey())){//课桌式
							putTypeDO.setPutType(PutTypeEnum.CLASS_ROOM.key);
						}else if(String.valueOf(PutTypeSaasEnum.UTYPE.key).equals(putTypeSassDTO.getPutTypeKey())){//U型式
							putTypeDO.setPutType(PutTypeEnum.U_TYPE.key);
						}else if(String.valueOf(PutTypeSaasEnum.RTYPE.key).equals(putTypeSassDTO.getPutTypeKey())){//回型式
							putTypeDO.setPutType(PutTypeEnum.RETURN_TYPE.key);
						} else if (String.valueOf(PutTypeSaasEnum.BANQUET.key).equals(putTypeSassDTO.getPutTypeKey())){//宴会式
							putTypeDO.setPutType(PutTypeEnum.BANQUET.key);
						} else if (String.valueOf(PutTypeSaasEnum.DIRECTOR.key).equals(putTypeSassDTO.getPutTypeKey())){//董会式
							putTypeDO.setPutType(PutTypeEnum.DIRECTORATE.key);
						}
						if(null != putTypeDO.getPutType()){
							putTypeDOList.add(putTypeDO);
						}
					}
					meetingRoomDO.setPutTypeDOList(putTypeDOList);
				}
				//会议室图片  原来不存在主图
				if(!CollectionUtils.isEmpty(meetingTypeInfoDTO.getMeetingImgDTOList())){
					List<ImageDO> imageDOS = new ArrayList<>();
					ImageDO meetingRoomImage = null;
					for(HotelImgDTO hotelImgDTO : meetingTypeInfoDTO.getMeetingImgDTOList()){
						meetingRoomImage = new ImageDO();
						meetingRoomImage.setImageType(ImageTypeEnum.MEET.key);
						meetingRoomImage.setRealPath(hotelImgDTO.getImgurl());
						meetingRoomImage.setImageUrl(hotelImgDTO.getImgurl());
						meetingRoomImage.setHotelId(hotelDO.getHotelId());
						meetingRoomImage.setIsMainImage(0);
						//meetingRoomImage.setImageLargeUrl();
						//meetingRoomImage.setImageMiddleUrl();
						//meetingRoomImage.setImageSmallUrl();
						imageDOS.add(meetingRoomImage);
					}
					meetingRoomDO.setImageDOList(imageDOS);
				}
				meetingRoomDOList.add(meetingRoomDO);
			}
			hotelDO.setMeetingRoomDOList(meetingRoomDOList);
		}
	}

	private String[] assembleTime(String str){
		String [] strArray = str.split("-");
		if(null != strArray && strArray.length > 0){
			for(int j = 0;j < strArray.length; j++){
				strArray[j] = new StringBuilder(strArray[j]).insert(2, ":").toString();
			}
		}
		return strArray;
	}
}
