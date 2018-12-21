package com.fangcang.product;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.CurrencyEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.product.dto.BedTypeDTO;
import com.fangcang.product.dto.PricePlanInfoDTO;
import com.fangcang.product.dto.ProductDailyDTO;
import com.fangcang.product.dto.RoomTypeDTO;
import com.fangcang.product.request.*;
import com.fangcang.product.response.HotelDetailResponseDTO;
import com.fangcang.product.response.HotelListResponseDTO;
import com.fangcang.product.response.PricePlanResponseDTO;
import com.fangcang.product.response.ToAddPricePlanResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ASUS on 2018/5/17.
 */
@RestController
@RequestMapping(value = "/test/product")
public class TestPricePlanController {

    private ConcurrentHashMap<Long,PricePlanRequestDTO> pricePlanMap = new ConcurrentHashMap<>();

    private AtomicLong atomicLong = new AtomicLong();

    @RequestMapping(value = "/queryHotelList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> queryHotelList(@RequestBody ProductHotelListQueryDTO queryDTO){
        List<HotelListResponseDTO> list = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            HotelListResponseDTO hotelListResponseDTO = new HotelListResponseDTO();
            hotelListResponseDTO.setHotelId(Long.valueOf(1000 + i));
            hotelListResponseDTO.setHotelName("深圳喜马拉雅大酒店");
            hotelListResponseDTO.setCityCode("SZX");
            hotelListResponseDTO.setCityName("深圳");
            if(i % 2 == 0){
                hotelListResponseDTO.setIsCommonUsed(1);
            }else{
                hotelListResponseDTO.setIsCommonUsed(0);
            }
            list.add(hotelListResponseDTO);
        }
        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(5);
        paginationSupportDTO.setTotalPage(1);
        paginationSupportDTO.setCurrentPage(queryDTO.getCurrentPage());
        paginationSupportDTO.setPageSize(queryDTO.getPageSize());
        paginationSupportDTO.setItemList(list);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/queryHotelInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<HotelDetailResponseDTO> queryHotelInfo(@RequestBody @Valid ProductRoomTypeListQueryDTO queryDTO){
        HotelDetailResponseDTO hotelDetailResponseDTO = new HotelDetailResponseDTO();
        hotelDetailResponseDTO.setHotelId(1000L);
        hotelDetailResponseDTO.setHotelName("深圳喜马拉雅大酒店");
        //房型
        List<RoomTypeDTO> roomTypeDTOList = new ArrayList<>();
        for(int i=0;i < 3;i++){
            RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
            roomTypeDTO.setRoomTypeId(Long.valueOf(1000 + i));
            roomTypeDTO.setRoomTypeName("标准大床房" + i);
            roomTypeDTO.setBroadBand(1);
            roomTypeDTOList.add(roomTypeDTO);
        }

        List<PricePlanInfoDTO> pricePlanDTOList1 = new ArrayList<>();
        for(int i =0 ;i< 3;i++){
            PricePlanInfoDTO pricePlanDTO = getPricePlanDTO(i);
            pricePlanDTOList1.add(pricePlanDTO);
        }

        List<PricePlanInfoDTO> pricePlanDTOList2 = new ArrayList<>();
        for(int i = 4 ;i< 5;i++){
            PricePlanInfoDTO pricePlanDTO = getPricePlanDTO(i);
            pricePlanDTOList2.add(pricePlanDTO);
        }

        List<PricePlanInfoDTO> pricePlanDTOList3 = new ArrayList<>();
        for(int i = 5 ;i< 6;i++){
            PricePlanInfoDTO pricePlanDTO = getPricePlanDTO(i);
            pricePlanDTOList3.add(pricePlanDTO);
        }
        roomTypeDTOList.get(0).setPricePlanList(pricePlanDTOList1);
        roomTypeDTOList.get(1).setPricePlanList(pricePlanDTOList2);
        roomTypeDTOList.get(2).setPricePlanList(pricePlanDTOList3);

        hotelDetailResponseDTO.setRoomTypeList(roomTypeDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(hotelDetailResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/toAddPricePlan",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<ToAddPricePlanResponseDTO> toAddPricePlan(@RequestBody @Valid ToAddPricePlanQueryDTO queryDTO){
        ToAddPricePlanResponseDTO toAddPricePlanResponseDTO = new ToAddPricePlanResponseDTO();
        toAddPricePlanResponseDTO.setHotelId(Long.valueOf(10000));
        toAddPricePlanResponseDTO.setHotelName("深圳喜马拉雅大酒店");

        List<RoomTypeDTO> roomTypeDTOList = new ArrayList<>();
        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
        roomTypeDTO.setRoomTypeId(10000L);
        roomTypeDTO.setRoomTypeName("豪华房");

        List<BedTypeDTO> bedTypeDTOList = new ArrayList<>();
        for(int i =0 ;i< 3;i++){
            BedTypeDTO bedTypeDTO = new BedTypeDTO();
            bedTypeDTO.setBedTypeId(Long.valueOf(1000 + i));
            bedTypeDTO.setBedTypeName("单床");
            bedTypeDTOList.add(bedTypeDTO);
        }
        roomTypeDTO.setBedTypeList(bedTypeDTOList);

        RoomTypeDTO roomTypeDTO2 = new RoomTypeDTO();
        roomTypeDTO2.setRoomTypeId(10001L);
        roomTypeDTO2.setRoomTypeName("双人房");
        List<BedTypeDTO> bedTypeDTOList2 = new ArrayList<>();
        for(int i =0 ;i< 3;i++){
            BedTypeDTO bedTypeDTO = new BedTypeDTO();
            bedTypeDTO.setBedTypeId(Long.valueOf(1000 + i));
            bedTypeDTO.setBedTypeName("双人床");
            bedTypeDTOList2.add(bedTypeDTO);
        }
        roomTypeDTO2.setBedTypeList(bedTypeDTOList2);

        roomTypeDTOList.add(roomTypeDTO);
        roomTypeDTOList.add(roomTypeDTO2);
        toAddPricePlanResponseDTO.setRoomTypeList(roomTypeDTOList);
        toAddPricePlanResponseDTO.setAdditionalStr("加早30/份,加床40/份");
        toAddPricePlanResponseDTO.setCancelPolicy("一经预定不可取消");

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(toAddPricePlanResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/saveOrUpdatePricePlan",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public  ResponseDTO saveOrUpdatePricePlan(@RequestBody @Valid PricePlanRequestDTO pricePlanRequestDTO){
        long pricePlanId = atomicLong.incrementAndGet();
        pricePlanRequestDTO.setPricePlanId(pricePlanId);
        pricePlanMap.put(pricePlanId,pricePlanRequestDTO);
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/queryPricePlanInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PricePlanResponseDTO> queryPricePlanInfo(@RequestBody @Valid PricePlanQueryDTO pricePlanQueryDTO){
        PricePlanRequestDTO pricePlanRequestDTO = pricePlanMap.get(pricePlanQueryDTO.getPricePlanId());
        PricePlanResponseDTO pricePlanResponseDTO = new PricePlanResponseDTO();
        if(null != pricePlanRequestDTO){
            pricePlanResponseDTO = PropertyCopyUtil.transfer(pricePlanRequestDTO, PricePlanResponseDTO.class);
        }
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(pricePlanResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/deletePricePlan",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deletePricePlan(@RequestBody @Valid PricePlanQueryDTO pricePlanQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/queryHotelDynamicList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public  ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> queryHotelDynamicList(@RequestBody @Valid HotelDynamicPriceQueryDTO hotelDynamicPriceQueryDTO){
        List<HotelListResponseDTO> hotelListResponseDTOList = new ArrayList<>();
        for(int i = 0;i < 14;i ++){
            HotelListResponseDTO hotelListResponseDTO = new HotelListResponseDTO();
            hotelListResponseDTO.setHotelId(Long.valueOf(10000 + 1));
            hotelListResponseDTO.setHotelName("深圳喜马拉雅大酒店" + i);
            hotelListResponseDTOList.add(hotelListResponseDTO);
        }
        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(15);
        paginationSupportDTO.setTotalPage(1);
        paginationSupportDTO.setCurrentPage(hotelDynamicPriceQueryDTO.getCurrentPage());
        paginationSupportDTO.setPageSize(15);
        paginationSupportDTO.setItemList(hotelListResponseDTOList);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }

//    @RequestMapping(value = "/queryHotelAllPricePlanInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
//    public ResponseDTO<HotelDetailResponseDTO> queryHotelAllPricePlanInfo(@RequestBody @Valid HotelDynamicPriceQueryDTO hotelDynamicPriceQueryDTO){
//        HotelDetailResponseDTO hotelDetailResponseDTO = new HotelDetailResponseDTO();
//        hotelDetailResponseDTO.setHotelId(Long.valueOf(10000 ));
//        hotelDetailResponseDTO.setHotelName("深圳喜马拉雅大酒店");
//        //房型
//        List<RoomTypeDTO> roomTypeDTOList = new ArrayList<>();
//        for(int j=0;j < 3;j++){
//            RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
//            roomTypeDTO.setRoomTypeId(Long.valueOf(1000 + j));
//            roomTypeDTO.setRoomTypeName("标准大床房" + j);
//            roomTypeDTO.setBroadBand(1);
//            roomTypeDTOList.add(roomTypeDTO);
//        }
//
//        List<PricePlanInfoDTO> pricePlanDTOList1 = new ArrayList<>();
//        for(int k =0 ;k< 3;k++){
//            PricePlanInfoDTO pricePlanDTO = getPricePlanDTO(k);
//            pricePlanDTOList1.add(pricePlanDTO);
//        }
//
//        List<PricePlanInfoDTO> pricePlanDTOList2 = new ArrayList<>();
//        for(int m = 4 ;m< 5;m++){
//            PricePlanInfoDTO pricePlanDTO = getPricePlanDTO(m);
//            pricePlanDTOList2.add(pricePlanDTO);
//        }
//
//        roomTypeDTOList.get(0).setPricePlanList(pricePlanDTOList1);
//        roomTypeDTOList.get(1).setPricePlanList(pricePlanDTOList2);
//
//        hotelDetailResponseDTO.setRoomTypeList(roomTypeDTOList);
//
//        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
//        responseDTO.setModel(hotelDetailResponseDTO);
//        return responseDTO;
//    }

    private PricePlanInfoDTO getPricePlanDTO(int i) {
        PricePlanInfoDTO pricePlanDTO = new PricePlanInfoDTO();
        pricePlanDTO.setPricePlanId(Long.valueOf(1000 + i));
        pricePlanDTO.setPricePlanName("限时优惠" + i);
        pricePlanDTO.setBedType(String.valueOf(i));
        pricePlanDTO.setBreakFastType(Integer.valueOf(i));
        pricePlanDTO.setSupplyCode("S1000" + i);
        pricePlanDTO.setSupplyName("深圳美丽华旅行社" + i);
        pricePlanDTO.setCurrency(CurrencyEnum.CNY.value);
        pricePlanDTO.setIsShare(1);
        pricePlanDTO.setQuotaAccountId(Long.valueOf(1000 + i));

        List<ProductDailyDTO> productDailyDTOList = new ArrayList<>();
        List<Date> dateList = DateUtil.getDateList(DateUtil.getCurrentDate(), DateUtil.getDate(DateUtil.getCurrentDate(), 6, 0));
        for (Date date : dateList) {
            ProductDailyDTO productDailyDTO = new ProductDailyDTO();
            BigDecimal salePrice = new BigDecimal(20);
            BigDecimal basePrice = new BigDecimal(10);
            productDailyDTO.setSaleDate(DateUtil.dateFormat(date,"yyyy-MM-dd"));
            productDailyDTO.setBasePrice(basePrice);
            productDailyDTO.setGroupBasePrice(basePrice);
            productDailyDTO.setB2bSalePrice(salePrice);
            productDailyDTO.setGroupSalePrice(salePrice);
            productDailyDTO.setCtripSalePrice(salePrice);
            productDailyDTO.setQunarSalePrice(salePrice);
            productDailyDTO.setElongSalePrice(salePrice);
            productDailyDTO.setTongchengSalePrice(salePrice);
            productDailyDTO.setTuniuSalePrice(salePrice);
            productDailyDTO.setXmdSalePrice(salePrice);
            productDailyDTO.setJdSalePrice(salePrice);
            productDailyDTO.setTaobaoSalePrice(salePrice);
            productDailyDTO.setQunarB2BSalePrice(salePrice);
            productDailyDTO.setQunarNgtSalePrice(salePrice);
            productDailyDTO.setQunarUsdSalePrice(salePrice);
            productDailyDTO.setQuotaNum(5);
            productDailyDTO.setAllQuotaNum(5);
            productDailyDTO.setOverDraft(1);
            productDailyDTO.setQuotaState(1);
            productDailyDTOList.add(productDailyDTO);
        }
        pricePlanDTO.setProductDailyList(productDailyDTOList);
        return pricePlanDTO;
    }
}
