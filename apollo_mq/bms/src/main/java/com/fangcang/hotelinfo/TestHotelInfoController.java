package com.fangcang.hotelinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fangcang.hotelinfo.dto.BedTypeDTO;
import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import com.fangcang.hotelinfo.dto.SimpleHotelDTO;
import com.fangcang.hotelinfo.request.*;
import com.fangcang.hotelinfo.response.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;

@RestController
@RequestMapping(value = "/test/hotelinfo")
public class TestHotelInfoController {
	 
	@RequestMapping(value = "/saveOrUpdateHotel",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<HotelInfoResponseDTO> saveOrUpdatehotel(@RequestBody @Valid HotelInfoRequestDTO hotelInfo){
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		return responseDTO;
	}
	

	@RequestMapping(value = "/getHotelList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> queryHotelInfoList(@RequestBody @Valid HotelListQueryDTO hotelInfoListQueryDTO){
		List<HotelListResponseDTO> list = new ArrayList<>();
		HotelListResponseDTO hotelInfoListResponseDTO = new HotelListResponseDTO();
		for(int i = 0;i< 5;i++){
			hotelInfoListResponseDTO.setHotelId(Long.valueOf(1000 + i));
			hotelInfoListResponseDTO.setHotelName("橘子酒店" + i);
			hotelInfoListResponseDTO.setCityName("JZJD");
			hotelInfoListResponseDTO.setCountryCode("CN");
			hotelInfoListResponseDTO.setCountryName("中国");
			hotelInfoListResponseDTO.setIsCommonUsed(1);
			list.add(hotelInfoListResponseDTO);
		}

		PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
		paginationSupportDTO.setTotalCount(5);
		paginationSupportDTO.setTotalPage(1);
		paginationSupportDTO.setCurrentPage(hotelInfoListQueryDTO.getCurrentPage());
		paginationSupportDTO.setPageSize(hotelInfoListQueryDTO.getPageSize());
		paginationSupportDTO.setItemList(list);
        	
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }
	
	@RequestMapping(value = "/isCommonUseHotel",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO isCommonUseHotel(@RequestBody @Valid CommonUseRequestDTO commonUseDTo){
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		return responseDTO;
	}
		
	@RequestMapping(value = "/queryCommonUsedHotel",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<CommonUsedHotelResponseDTO> queryCommonUsedHotel(@RequestBody @Valid CommonUsedHotelRequestDTO queryCommonHotelDTO){
		CommonUsedHotelResponseDTO hotelCommonUseResponseDTO = new CommonUsedHotelResponseDTO();
		List<SimpleHotelDTO> hotelDTOList = new ArrayList<>();
		for(int i=0;i < 6;i++){
			SimpleHotelDTO simpleHotelDTO = new SimpleHotelDTO();
			simpleHotelDTO.setHotelId(Long.valueOf(1000 + i));
			simpleHotelDTO.setHotelName("深圳喜马拉雅大酒店" + i);
			hotelDTOList.add(simpleHotelDTO);
		}
		hotelCommonUseResponseDTO.setHotelList(hotelDTOList);
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		responseDTO.setModel(hotelCommonUseResponseDTO);
		return responseDTO;
	}

	@RequestMapping(value = "/queryHotelInfoByHotelId",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<HotelBaseInfoRsponseDTO> queryHotelInfoByHotelId(@RequestBody @Valid HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO){

		BedTypeDTO bedTypeRsponseDTO= new BedTypeDTO();
		bedTypeRsponseDTO.setBedType(1);
		bedTypeRsponseDTO.setLength("5");
		bedTypeRsponseDTO.setNum(10);
		bedTypeRsponseDTO.setWide("3");

		List<BedTypeDTO>  bedTypelist=new ArrayList<BedTypeDTO>();
		bedTypelist.add(bedTypeRsponseDTO);
		RoomTypeDTO hotelRoomTypeRsponseDTO= new RoomTypeDTO();
		hotelRoomTypeRsponseDTO.setRoomTypeId(Long.valueOf(10000));
		hotelRoomTypeRsponseDTO.setRoomTypeName("黄冈酒店");
		hotelRoomTypeRsponseDTO.setEngRoomTypeName("huangganghotel");
		hotelRoomTypeRsponseDTO.setFloor("3");
		hotelRoomTypeRsponseDTO.setArea("200");
		hotelRoomTypeRsponseDTO.setMaxPerson(6);
		hotelRoomTypeRsponseDTO.setRoomNum("10");
		hotelRoomTypeRsponseDTO.setIsWindow(1);
		hotelRoomTypeRsponseDTO.setIsWifi(0);
		hotelRoomTypeRsponseDTO.setIsWired(0);
		hotelRoomTypeRsponseDTO.setIsSmokeless(0);
		hotelRoomTypeRsponseDTO.setIsExtraBed(1);
		hotelRoomTypeRsponseDTO.setIsActive(0);
		hotelRoomTypeRsponseDTO.setImageUrl("http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg");
		hotelRoomTypeRsponseDTO.setRealPath("spring");
		hotelRoomTypeRsponseDTO.setBedTypeList(bedTypelist);
		List<RoomTypeDTO> roomTypeList= new ArrayList<RoomTypeDTO>();
		roomTypeList.add(hotelRoomTypeRsponseDTO);
		List<HotelAdditionalDTO> additionalList=new ArrayList<HotelAdditionalDTO>();
		HotelAdditionalDTO additionalRsponseDTO=new HotelAdditionalDTO();
		additionalRsponseDTO.setAdditionalId(Long.valueOf(1000+1));
		additionalRsponseDTO.setAdditionalName("早餐");
		additionalRsponseDTO.setAdditionalType(1);
		additionalRsponseDTO.setAdditionalPrice(new BigDecimal(20));
		additionalList.add(additionalRsponseDTO);

		HotelBaseInfoRsponseDTO hotelBaseInfoRsponseDTO= new HotelBaseInfoRsponseDTO();
		hotelBaseInfoRsponseDTO.setAdditionalList(additionalList);
		hotelBaseInfoRsponseDTO.setRoomTypeList(roomTypeList);
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

		hotelBaseInfoRsponseDTO.setHotelId(1000L);
		hotelBaseInfoRsponseDTO.setHotelName("测试酒店");
		hotelBaseInfoRsponseDTO.setCityCode("SZX");
		hotelBaseInfoRsponseDTO.setCityName("深圳");
		hotelBaseInfoRsponseDTO.setBusinessCode("");
		hotelBaseInfoRsponseDTO.setHotelStar(1);
		hotelBaseInfoRsponseDTO.setPhone("027-1227777");
		hotelBaseInfoRsponseDTO.setFax("11212121");
		hotelBaseInfoRsponseDTO.setOpeningDate("2018");
		hotelBaseInfoRsponseDTO.setDecorationDate("2018");
		hotelBaseInfoRsponseDTO.setRoomTotalNum(100);
		
		hotelBaseInfoRsponseDTO.setTheme(new String[]{"1","2"});
		hotelBaseInfoRsponseDTO.setImageUrl("http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg");
		hotelBaseInfoRsponseDTO.setRealPath("test02images/hotels");
		hotelBaseInfoRsponseDTO.setIntroduction("酒店介绍");
		hotelBaseInfoRsponseDTO.setCancelPolicy("取消策略");
		hotelBaseInfoRsponseDTO.setCheckInTime("16:00");
		hotelBaseInfoRsponseDTO.setCheckOutTime("12:00");
		hotelBaseInfoRsponseDTO.setPet(1);
		
		hotelBaseInfoRsponseDTO.setCreditCard(new String[]{"1","2"});
		hotelBaseInfoRsponseDTO.setHotelAddress("中国广东省深圳市");

		responseDTO.setModel(hotelBaseInfoRsponseDTO);
		return responseDTO;
	}

	@RequestMapping(value = "/deleteHotel",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO deleteHotel(@RequestBody @Valid HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO){
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		return responseDTO;
	}
}














