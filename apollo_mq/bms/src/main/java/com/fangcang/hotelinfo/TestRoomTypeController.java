package com.fangcang.hotelinfo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fangcang.hotelinfo.request.DeleteRoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.ImageRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeIdQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeRequestDTO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import com.fangcang.hotelinfo.dto.BedTypeDTO;
import com.fangcang.hotelinfo.response.RoomTypeIdRsponseDTO;
import com.fangcang.hotelinfo.response.RoomTypeRsponseDTO;

@RestController
@RequestMapping("/test/hotelinfo")
public class TestRoomTypeController {

	@RequestMapping(value = "/saveOrUpdateRoomType",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO saveOrUpdateRoomType(@RequestBody @Valid RoomTypeRequestDTO roomTypeRequestDTO){
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		return responseDTO;
	}

	@RequestMapping(value = "/queryRoomTypeInfoByHotelId",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO<RoomTypeDTO> queryRoomTypeInfoByHotelId( @RequestBody @Valid RoomTypeQueryDTO roomTypeQueryDTO){

		BedTypeDTO bedTypeRsponseDTO= new BedTypeDTO();
		
		List<BedTypeDTO>  bedTypelist=new ArrayList<BedTypeDTO>();
		bedTypeRsponseDTO.setLength("5");
		bedTypeRsponseDTO.setNum(10);
		bedTypeRsponseDTO.setWide("3");
		bedTypeRsponseDTO.setBedType(1);
		bedTypelist.add(bedTypeRsponseDTO);
		
		List<RoomTypeDTO> roomTypeList= new ArrayList<RoomTypeDTO>();
		RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
		roomTypeDTO.setHotelId(1000L);
		roomTypeDTO.setRoomTypeId(Long.valueOf(10000));
		roomTypeDTO.setRoomTypeName("黄冈酒店");
		roomTypeDTO.setEngRoomTypeName("huangganghotel");
		roomTypeDTO.setFloor("3");
		roomTypeDTO.setArea("200");
		roomTypeDTO.setMaxPerson(6);
		roomTypeDTO.setRoomNum("10");
		roomTypeDTO.setIsWindow(1);
		roomTypeDTO.setIsWifi(0);
		roomTypeDTO.setIsWired(0);
		roomTypeDTO.setIsSmokeless(0);
		roomTypeDTO.setIsExtraBed(1);
		roomTypeDTO.setIsActive(1);
		roomTypeDTO.setRoomTypeDescribe("房型描述。。。。。。。");
		roomTypeDTO.setImageUrl("http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg");
		roomTypeDTO.setRealPath("spring");


		roomTypeDTO.setBedTypeList(bedTypelist);
		roomTypeList.add(roomTypeDTO);

		RoomTypeRsponseDTO roomTypeRsponseDTO = new RoomTypeRsponseDTO();
		roomTypeRsponseDTO.setRoomTypeList(roomTypeList);
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
	    responseDTO.setModel( roomTypeRsponseDTO);
		return responseDTO;
		
	}

	@RequestMapping(value = "/deleteRoomType",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ResponseDTO deleteRoomType(@RequestBody DeleteRoomTypeRequestDTO deleteRoomTypeRequestDTO){
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		return  responseDTO;
	}
	
	@RequestMapping(value = "/queryRoomTypeInfoById",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public  ResponseDTO<RoomTypeIdRsponseDTO> queryRoomTypeInfoById(@RequestBody @Valid RoomTypeIdQueryDTO roomTypeIdQueryDTO ){
           BedTypeDTO bedTypeRsponseDTO= new BedTypeDTO();
		
		  List<BedTypeDTO>  bedTypelist=new ArrayList<BedTypeDTO>();
		  bedTypeRsponseDTO.setLength("5");
		  bedTypeRsponseDTO.setNum(10);
		  bedTypeRsponseDTO.setWide("3");
		  bedTypeRsponseDTO.setBedType(1);
		  bedTypelist.add(bedTypeRsponseDTO);
		    
		  RoomTypeIdRsponseDTO roomTypeIdRsponseDTO=new  RoomTypeIdRsponseDTO();
		  roomTypeIdRsponseDTO.setHotelId(1000L);
		  roomTypeIdRsponseDTO.setRoomTypeId(10000L);
		  roomTypeIdRsponseDTO.setRoomTypeName("黄冈酒店");
		  roomTypeIdRsponseDTO.setEngRoomTypeName("huangganghotel");
		  roomTypeIdRsponseDTO.setFloor("3");
		  roomTypeIdRsponseDTO.setArea("200");
		  roomTypeIdRsponseDTO.setMaxPerson(6);
		  roomTypeIdRsponseDTO.setRoomNum("10");
		  roomTypeIdRsponseDTO.setIsWindow(1);
		  roomTypeIdRsponseDTO.setIsWifi(0);
		  roomTypeIdRsponseDTO.setIsWired(0);
		  roomTypeIdRsponseDTO.setIsSmokeless(0);
		  roomTypeIdRsponseDTO.setIsExtraBed(1);
		  roomTypeIdRsponseDTO.setIsActive(1);
		  roomTypeIdRsponseDTO.setRoomTypeDescribe("房型描述。。。。。。。");
		  roomTypeIdRsponseDTO.setImageUrl("http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg");
		  roomTypeIdRsponseDTO.setRealPath("spring");
          roomTypeIdRsponseDTO.setBedTypeList(bedTypelist);
		
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		responseDTO.setModel( roomTypeIdRsponseDTO);
		return  responseDTO;	
	}
             
}
