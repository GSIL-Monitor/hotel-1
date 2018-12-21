package com.fangcang.merchant.controller;


import com.fangcang.agent.response.BankCardListResponseDTO;
import com.fangcang.base.dto.CommonCityDTO;
import com.fangcang.base.request.QueryMerchantCityDTO;
import com.fangcang.base.service.CommonCityService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.hotelinfo.request.HotelInfoRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeRequestDTO;
import com.fangcang.hotelinfo.response.HotelInfoResponseDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.hotelinfo.service.RoomTypeService;
import com.fangcang.merchant.domain.TempDO;
import com.fangcang.merchant.dto.BankCardDTO;
import com.fangcang.merchant.mapper.TempMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@Slf4j
@RestController
@RequestMapping(value = "/test/merchant")
public class TestMerchantController extends BaseController {

    @RequestMapping(value = "/getMerchantBankList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<BankCardListResponseDTO> getMerchantBankList (){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<BankCardDTO> bankCardDTOList = new ArrayList<>();
        for(int i= 0;i<10;i++){
            BankCardDTO bankCardDTO = new BankCardDTO();
            bankCardDTO.setBankCardId(Long.valueOf(1000 + i));
            bankCardDTO.setOpeningBank("中国工商银行南山支行" + i);
            bankCardDTO.setAccountName("张三" + i);
            bankCardDTO.setAccountNumber("123434307879" + i);
            bankCardDTOList.add(bankCardDTO);
        }
        BankCardListResponseDTO bankCardListResponseDTO = new BankCardListResponseDTO();
        bankCardListResponseDTO.setBankCardList(bankCardDTOList);
        responseDTO.setModel(bankCardListResponseDTO);
        return responseDTO;
    }

    @Autowired
    private TempMapper tempMapper;

    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private CommonCityService commonCityService;

    @RequestMapping(value = "/importHotel",method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public ResponseDTO importHotel(){
        try{
            List<TempDO> tempDOList=tempMapper.selectAll();
            for (TempDO tempDO:tempDOList){
                String[] content=tempDO.getContent().split(",");

                QueryMerchantCityDTO queryMerchantCityDTO=new QueryMerchantCityDTO();
                queryMerchantCityDTO.setMerchantCode(super.getMerchantCode());
                queryMerchantCityDTO.setCityName(content[0]);
                PaginationSupportDTO<CommonCityDTO> cityForPage=commonCityService.queryMerchantCityForPage(queryMerchantCityDTO);

                //新增酒店基本信息
                HotelInfoRequestDTO hotelInfoRequestDTO=new HotelInfoRequestDTO();
                hotelInfoRequestDTO.setCityCode(cityForPage.getItemList().get(0).getCityCode());
                hotelInfoRequestDTO.setCityName(cityForPage.getItemList().get(0).getCityName());
                hotelInfoRequestDTO.setHotelName(content[1]);
                hotelInfoRequestDTO.setCreator(super.getFullName());
                hotelInfoRequestDTO.setMerchantCode(super.getMerchantCode());
                hotelInfoRequestDTO.setAdditionalList(new ArrayList<>());
                hotelInfoRequestDTO.setFacilityList(new ArrayList<>());
                ResponseDTO<HotelInfoResponseDTO> hotelInfoResponseDTO=hotelInfoService.saveOrUpdateHotel(hotelInfoRequestDTO);

                //新增房型
                for (int i=2;i<content.length;i++){
                    RoomTypeRequestDTO roomTypeRequestDTO=new RoomTypeRequestDTO();
                    roomTypeRequestDTO.setHotelId(hotelInfoResponseDTO.getModel().getHotelId());
                    roomTypeRequestDTO.setRoomTypeName(content[i]);
                    roomTypeRequestDTO.setCreator(super.getFullName());
                    roomTypeRequestDTO.setBedTypeList(new ArrayList<>());
                    ResponseDTO roomTypeResponseDTO = roomTypeService.saveOrUpdateRoomType(roomTypeRequestDTO);
                }
                log.info("import :"+content+";hotelid="+hotelInfoResponseDTO.getModel().getHotelId());
            }
            return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        }catch (Exception e){
            log.error("importHotel error:",e);
            return new ResponseDTO(ResultCodeEnum.FAILURE.code);
        }
    }
}
