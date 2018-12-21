package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.product.dto.*;
import com.fangcang.product.mapper.PricePlanMapper;
import com.fangcang.product.request.*;
import com.fangcang.product.response.AllSharedPoolResponseDTO;
import com.fangcang.product.response.NeedSharedProductResponseDTO;
import com.fangcang.product.response.SharedPoolResponseDTO;
import com.fangcang.product.service.SharedPoolService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/product")
public class SharedPoolController extends BaseController{

    @Autowired
    private SharedPoolService sharedPoolService;

    /**
     *共享房态
     * @param quotaStateQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryCurrentSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SharedPoolResponseDTO> queryCurrentSharedPool(@RequestBody @Valid QuotaStateQueryDTO quotaStateQueryDTO){
        return sharedPoolService.queryCurrentSharedPool(quotaStateQueryDTO);
    }

    /**
     * 管理共享组
     * @param sharedPoolQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryAllSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AllSharedPoolResponseDTO> queryAllSharedPool(@RequestBody SharedPoolQueryDTO sharedPoolQueryDTO){
        UserDTO cacheUser = super.getCacheUser();
        sharedPoolQueryDTO.setMerchantId(cacheUser.getMerchantId());
        sharedPoolQueryDTO.setMerchantCode(cacheUser.getMerchantCode());
        return sharedPoolService.queryAllSharedPool(sharedPoolQueryDTO);
    }

    /**
     * 新建共享池
     * @param addSharedPoolRequestDTO
     * @return
     */
    @RequestMapping(value = "/createSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO createSharedPool(@RequestBody @Valid AddSharedPoolRequestDTO addSharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserDTO userDTO = super.getCacheUser();
            addSharedPoolRequestDTO.setCreator(getFullName());
            addSharedPoolRequestDTO.setModifier(getFullName());
            addSharedPoolRequestDTO.setMerchantCode(userDTO.getMerchantCode());
            responseDTO = sharedPoolService.createSharedPool(addSharedPoolRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 重命名共享池
     * @param sharedPoolRequestDTO
     * @return
     */
    @RequestMapping(value = "/renameSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO renameSharedPool(@RequestBody SharedPoolRequestDTO sharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            sharedPoolRequestDTO.setCreator(super.getFullName());
            responseDTO = sharedPoolService.renameSharedPool(sharedPoolRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 选择要共享的产品
     * @param sharedPoolRequestDTO
     * @return
     */
    @RequestMapping(value = "/queryNeedSharedProduct",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<NeedSharedProductResponseDTO> queryNeedSharedProduct(@RequestBody SharedPoolRequestDTO sharedPoolRequestDTO){
        return sharedPoolService.queryNeedSharedProduct(sharedPoolRequestDTO);
    }

    /**
     * 添加产品到共享池
     * @param addProductToSharedPoolRequestDTO
     * @return
     */
    @RequestMapping(value = "/addProductToSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO addProductToSharedPool(@RequestBody @Valid AddProductToSharedPoolRequestDTO addProductToSharedPoolRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO userDTO = super.getCacheUser();
            addProductToSharedPoolRequestDTO.setMerchantCode(userDTO.getMerchantCode());
            addProductToSharedPoolRequestDTO.setCreator(super.getFullName());
            responseDTO = sharedPoolService.addProductToSharedPool(addProductToSharedPoolRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 共享池移除产品
     * @param cancelSharingRequestDTO
     * @return
     */
    @RequestMapping(value = "/deleteProductFromSharedPool",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deleteProductFromSharedPool(@RequestBody @Valid CancelSharingRequestDTO cancelSharingRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO userDTO = super.getCacheUser();
            cancelSharingRequestDTO.setMerchantCode(userDTO.getMerchantCode());
            cancelSharingRequestDTO.setCreator(super.getFullName());
            responseDTO = sharedPoolService.deleteProductFromSharedPool(cancelSharingRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
