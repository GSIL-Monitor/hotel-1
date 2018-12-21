package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.product.dto.*;
import com.fangcang.product.request.*;
import com.fangcang.product.response.AllSharedPoolResponseDTO;
import com.fangcang.product.response.NeedSharedProductResponseDTO;
import com.fangcang.product.response.SharedPoolResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@RestController
@RequestMapping(value = "/test/product")
public class TestSharedPoolController {

    @RequestMapping(value = "/queryCurrentSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SharedPoolResponseDTO> queryCurrentSharedPool(@RequestBody @Valid QuotaStateQueryDTO quotaStateQueryDTO){
        SharedPoolResponseDTO sharedPoolResponseDTO = new SharedPoolResponseDTO();
        sharedPoolResponseDTO.setQuotaAccountId(quotaStateQueryDTO.getQuotaAccountId());
        sharedPoolResponseDTO.setQuotaAccountName("大床房配额池丨每天50间");

        List<SharedPoolPricePlanDTO> sharedPoolPricePlanDTOList = new ArrayList<>();
        for(int i = 0;i< 5;i++){
            SharedPoolPricePlanDTO sharedPoolPricePlanDTO = new SharedPoolPricePlanDTO();
            if(i == 3){
                sharedPoolPricePlanDTO.setIsCurrentPricePlan(1);
            }else{
                sharedPoolPricePlanDTO.setIsCurrentPricePlan(0);
            }
            sharedPoolPricePlanDTO.setPricePlanId(Long.valueOf(1000 + i));
            sharedPoolPricePlanDTO.setPricePlanName("限时优惠+门票" + i);
            sharedPoolPricePlanDTO.setRoomTypeId(Long.valueOf(1000 + i));
            sharedPoolPricePlanDTO.setRoomTypeName("标准双床房" + i);
            sharedPoolPricePlanDTOList.add(sharedPoolPricePlanDTO);
        }
        sharedPoolResponseDTO.setSharedPoolPricePlanList(sharedPoolPricePlanDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(sharedPoolResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/queryAllSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AllSharedPoolResponseDTO> queryAllSharedPool(@RequestBody SharedPoolQueryDTO sharedPoolQueryDTO){
        AllSharedPoolResponseDTO allSharedPoolResponseDTO = new AllSharedPoolResponseDTO();
        allSharedPoolResponseDTO.setHotelId(sharedPoolQueryDTO.getHotelId());

        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        for (int i = 0;i< 5;i ++ ){
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setSupplyCode("S1000" + i);
            supplierDTO.setSupplyName("深圳环球旅行供应商" + i);
            supplierDTOList.add(supplierDTO);
        }

        List<SharedPoolDTO> sharedPoolDTOList = new ArrayList<>();
        for (int i = 0;i < 3;i++){
            SharedPoolDTO sharedPoolDTO = new SharedPoolDTO();
            sharedPoolDTO.setQuotaAccountId(Long.valueOf(1000 + i));
            sharedPoolDTO.setQuotaAccountName("大床配额池" + i);

            List<SharedPoolPricePlanDTO> sharedPoolPricePlanDTOList = new ArrayList<>();
            for(int j = 0;j< 5;j++){
                SharedPoolPricePlanDTO sharedPoolPricePlanDTO = new SharedPoolPricePlanDTO();
                if(i == 2){
                    sharedPoolPricePlanDTO.setIsCurrentPricePlan(1);
                }else{
                    sharedPoolPricePlanDTO.setIsCurrentPricePlan(0);
                }
                sharedPoolPricePlanDTO.setPricePlanId(Long.valueOf(1000 + i));
                sharedPoolPricePlanDTO.setPricePlanName("限时优惠+门票" + i);
                sharedPoolPricePlanDTO.setRoomTypeId(Long.valueOf(1000 + i));
                sharedPoolPricePlanDTO.setRoomTypeName("标准双床房" + i);
                sharedPoolPricePlanDTOList.add(sharedPoolPricePlanDTO);
            }
            sharedPoolDTO.setSharedPoolPricePlanList(sharedPoolPricePlanDTOList);
            sharedPoolDTOList.add(sharedPoolDTO);
        }
        supplierDTOList.get(0).setSharedPoolList(sharedPoolDTOList);
        supplierDTOList.get(1).setSharedPoolList(sharedPoolDTOList);
        supplierDTOList.get(2).setSharedPoolList(sharedPoolDTOList);
        allSharedPoolResponseDTO.setSupplierList(supplierDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(allSharedPoolResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/createSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO createSharedPool(@RequestBody @Valid AddSharedPoolRequestDTO addSharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/renameSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO renameSharedPool(@RequestBody SharedPoolRequestDTO sharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/queryNeedSharedProduct",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<NeedSharedProductResponseDTO> queryNeedSharedProduct(@RequestBody SharedPoolRequestDTO sharedPoolRequestDTO){
        NeedSharedProductResponseDTO needSharedProductResponseDTO = new NeedSharedProductResponseDTO();
        List<NeedSharedPoolRoomTypeDTO> roomTypeDTOList = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            NeedSharedPoolRoomTypeDTO roomTypeDTO = new NeedSharedPoolRoomTypeDTO();
            roomTypeDTO.setRoomTypeId(Long.valueOf(1000 + i));
            roomTypeDTO.setRoomTypeName("大床房" + i);
            List<PricePlanDTO> pricePlanDTOList = new ArrayList<>();
            for(int j= 0 ; j< 3;j++){
                PricePlanDTO pricePlanDTO = new PricePlanDTO();
                pricePlanDTO.setPricePlanId(Long.valueOf(1000 + i));
                pricePlanDTO.setPricePlanName("限时优惠" + i);
                pricePlanDTOList.add(pricePlanDTO);
            }
            roomTypeDTO.setPricePlanList(pricePlanDTOList);
            roomTypeDTOList.add(roomTypeDTO);
        }
        needSharedProductResponseDTO.setRoomTypeList(roomTypeDTOList);
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(needSharedProductResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/addProductToSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO addProductToSharedPool(@RequestBody @Valid AddProductToSharedPoolRequestDTO addProductToSharedPoolRequestDTOddProductToSharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/deleteProductFromSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deleteProductFromSharedPool(@RequestBody @Valid CancelSharingRequestDTO cancelSharingRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }
}
