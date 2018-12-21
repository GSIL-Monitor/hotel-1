package com.fangcang.product;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.systemlog.SystemLog;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.request.DynamicPricePlanQueryDTO;
import com.fangcang.product.request.HotelDynamicPriceQueryDTO;
import com.fangcang.product.request.PricePlanQueryDTO;
import com.fangcang.product.request.PricePlanRequestDTO;
import com.fangcang.product.request.ProductHotelListQueryDTO;
import com.fangcang.product.request.ProductPageQueryDTO;
import com.fangcang.product.request.ProductRoomTypeListQueryDTO;
import com.fangcang.product.request.ToAddPricePlanQueryDTO;
import com.fangcang.product.response.HotelDetailResponseDTO;
import com.fangcang.product.response.HotelListResponseDTO;
import com.fangcang.product.response.PricePlanListResponseDTO;
import com.fangcang.product.response.PricePlanResponseDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import com.fangcang.product.response.ToAddPricePlanResponseDTO;
import com.fangcang.product.service.PricePlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ASUS on 2018/5/17.
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class PricePlanController extends BaseController{

    @Autowired
    private PricePlanService pricePlanService;

    /**
     * 查询酒店列表
     * @param queryDTO
     * @return
     */
    @RequestMapping(value = "/queryHotelList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> queryHotelList(@RequestBody ProductHotelListQueryDTO queryDTO){
        UserDTO cacheUser = super.getCacheUser();
        queryDTO.setMerchantCode(cacheUser.getMerchantCode());
        queryDTO.setMerchantId(cacheUser.getMerchantId());
        queryDTO.setHasQueryProduct(false);
        return pricePlanService.queryHotelList(queryDTO);
    }

    /**
     * 新增或者修改价格计划
     * @param pricePlanRequestDTO
     * @return
     */
    @SystemLog
    @RequestMapping(value = "/saveOrUpdatePricePlan",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO saveOrUpdatePricePlan(@RequestBody @Valid PricePlanRequestDTO pricePlanRequestDTO){
        //TODO  商家编码和创建者
        ResponseDTO responseDTO = new ResponseDTO();
        log.info("saveOrUpdatePricePlan param:"+pricePlanRequestDTO);
        try {
            UserDTO cacheUser = super.getCacheUser();
            pricePlanRequestDTO.setMerchantCode(cacheUser.getMerchantCode());
            pricePlanRequestDTO.setCreator(super.getFullName());
            pricePlanRequestDTO.setModifier(super.getFullName());
            responseDTO = pricePlanService.saveOrUpdatePricePlan(pricePlanRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 编辑查询价格计划
     * @param pricePlanQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryPricePlanInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PricePlanResponseDTO> queryPricePlanInfo(@RequestBody @Valid PricePlanQueryDTO pricePlanQueryDTO){
        return pricePlanService.queryPricePlanInfo(pricePlanQueryDTO);
    }

    /**
     * 删除价格计划
     * @param pricePlanQueryDTO
     * @return
     */
    @RequestMapping(value = "/deletePricePlan",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO deletePricePlan(@RequestBody @Valid PricePlanQueryDTO pricePlanQueryDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            PricePlanDO pricePlanDO = new PricePlanDO();
            pricePlanDO.setPricePlanId(pricePlanQueryDTO.getPricePlanId());
            pricePlanDO.setIsActive(0);
            pricePlanDO.setModifier(super.getFullName());
            UserDTO userDTO = super.getCacheUser();
            pricePlanDO.setMerchantCode(userDTO.getMerchantCode());
            responseDTO = pricePlanService.deletePricePlan(pricePlanDO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 动态查询价格计划列表
     * @param dynamicPricePlanQueryDTO
     * @return
     */
    @RequestMapping(value = "/dynamicPricePlanList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PricePlanListResponseDTO> dynamicPricePlanList(@RequestBody DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO){
        UserDTO cacheUser = super.getCacheUser();
        dynamicPricePlanQueryDTO.setMerchantCode(cacheUser.getMerchantCode());
        dynamicPricePlanQueryDTO.setIsActive(1);
        return pricePlanService.dynamicPricePlanList(dynamicPricePlanQueryDTO);
    }

    /**
     * 根据酒店查询所有产品信息
     * @param queryDTO
     * @return
     */
    @RequestMapping(value = "/queryHotelInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<HotelDetailResponseDTO> queryHotelInfo(@RequestBody @Valid ProductRoomTypeListQueryDTO queryDTO){
        UserDTO cacheUser = super.getCacheUser();
        queryDTO.setMerchantCode(cacheUser.getMerchantCode());
        return pricePlanService.queryHotelInfo(queryDTO);
    }

    /**
     * 分页查询产品信息
     * @param productPageQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryProductInfoByPage",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<ProductDailyInfoResponseDTO>> queryProductInfoByPage(@RequestBody ProductPageQueryDTO productPageQueryDTO){
        UserDTO userDTO = super.getCacheUser();
        productPageQueryDTO.setMerchantCode(userDTO.getMerchantCode());
        return pricePlanService.queryProductInfoByPage(productPageQueryDTO);
    }

    /**
     * 新增价格计划所需要的数据
     * @param queryDTO
     * @return
     */
    @RequestMapping(value = "/toAddPricePlan",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<ToAddPricePlanResponseDTO> toAddPricePlan(@RequestBody @Valid ToAddPricePlanQueryDTO queryDTO){
        UserDTO userDTO = super.getCacheUser();
        queryDTO.setMerchantCode(userDTO.getMerchantCode());
        return pricePlanService.toAddPricePlan(queryDTO);
    }

    /**
     * 动态报价
     * @param hotelDynamicPriceQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryHotelDynamicList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public  ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> queryHotelDynamicList(@RequestBody @Valid HotelDynamicPriceQueryDTO hotelDynamicPriceQueryDTO){
        UserDTO cacheUser = super.getCacheUser();
        ProductHotelListQueryDTO productHotelListQueryDTO = null;
        productHotelListQueryDTO = PropertyCopyUtil.transfer(hotelDynamicPriceQueryDTO,ProductHotelListQueryDTO.class);
        productHotelListQueryDTO.setMerchantCode(cacheUser.getMerchantCode());
        productHotelListQueryDTO.setMerchantId(cacheUser.getMerchantId());
        productHotelListQueryDTO.setHasQueryProduct(true);
        return pricePlanService.queryHotelList(productHotelListQueryDTO);
    }
}
