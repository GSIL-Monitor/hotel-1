package com.fangcang.mapping;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.StringUtil;
import com.fangcang.mapping.request.HotelMappingRequest;
import com.fangcang.mapping.request.MappingQueryRequest;
import com.fangcang.mapping.request.RatePlanMappingRequest;
import com.fangcang.mapping.service.ElongMappingService;
import com.fangcang.mapping.service.HotelMappingService;
import com.fangcang.merchant.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Owen on 2018/6/8.
 */
@RestController
@Slf4j
@RequestMapping(value = "/mapping")
public class MappingController extends BaseController {

    @Autowired
    private HotelMappingService hotelMappingService;

    @Autowired
    private ElongMappingService elongMappingService;

    /**
     * 查询映射
     * @param mappingQueryRequest
     * @return
     */
    @RequestMapping(value = "/queryMapping", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO queryMapping(@RequestBody MappingQueryRequest mappingQueryRequest) {

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO cacheUser = super.getCacheUser();

            if(null==mappingQueryRequest) {
                mappingQueryRequest = new MappingQueryRequest();
            }

            //设置默认页码和每页显示条数
            if(null==mappingQueryRequest.getPageSize() || null==mappingQueryRequest.getCurrentPage()) {
                mappingQueryRequest.setCurrentPage(1);
                mappingQueryRequest.setPageSize(10);
            }


            if (!StringUtil.isValidString(mappingQueryRequest.getCityCode())){
                mappingQueryRequest.setCityCode(null);
            }

            if (!StringUtil.isValidString(mappingQueryRequest.getHotelId())){
                mappingQueryRequest.setHotelId(null);
            }

            //设置商家编码
            mappingQueryRequest.setMerchantCode(cacheUser.getMerchantCode());
//            mappingQueryRequest.setMerchantCode("M10000001");
            responseDTO = hotelMappingService.queryMappingNew(mappingQueryRequest);

        } catch (Exception e) {
            log.error("queryMapping异常：",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 新增或者修改酒店映射
     * @param hotelMappingRequest
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateHotelMapping", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveOrUpdateHotelMapping(@RequestBody HotelMappingRequest hotelMappingRequest) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        if(null==hotelMappingRequest || null==hotelMappingRequest.getHotelId() || hotelMappingRequest.getHotelId() <=0 ) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            return responseDTO;
        }
        log.info("新增或者修改酒店映射，dlthotelid=" + hotelMappingRequest.getDltHotelId());
        try {
            UserDTO cacheUser = super.getCacheUser();
            hotelMappingRequest.setMerchantCode(cacheUser.getMerchantCode());
            hotelMappingRequest.setCreator(cacheUser.getUsername());
            hotelMappingRequest.setCreateTime(new Date());
            hotelMappingRequest.setModifier(cacheUser.getUsername());
            hotelMappingRequest.setModifyTime(new Date());


            responseDTO = hotelMappingService.saveOrUpdateHotelMapping(hotelMappingRequest);

        } catch (Exception e) {
            log.error("查询映射失败:{}", JSON.toJSONString(hotelMappingRequest),e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 新增或者修改酒店映射
     * @param ratePlanMappingRequest
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateRatePlanMapping", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveOrUpdateRatePlanMapping(@RequestBody RatePlanMappingRequest ratePlanMappingRequest) {
        //新增酒店基本信息
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        if(null==ratePlanMappingRequest || null==ratePlanMappingRequest.getRatePlanId() || ratePlanMappingRequest.getRatePlanId() <=0 ) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
            responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
            return responseDTO;
        }
        try {
            UserDTO cacheUser = super.getCacheUser();
            ratePlanMappingRequest.setMerchantCode(cacheUser.getMerchantCode());
            ratePlanMappingRequest.setCreator(cacheUser.getUsername());
            ratePlanMappingRequest.setCreateTime(new Date());
            ratePlanMappingRequest.setModifier(cacheUser.getUsername());
            ratePlanMappingRequest.setCreateTime(new Date());


            responseDTO = hotelMappingService.saveOrUpdateRatePlanMapping(ratePlanMappingRequest);

        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询艺龙映射
     * @param mappingQueryRequest
     * @return
     */
    @RequestMapping(value = "/queryElongMapping", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO queryElongMapping(@RequestBody MappingQueryRequest mappingQueryRequest) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO cacheUser = super.getCacheUser();
            if(null==mappingQueryRequest) {
                mappingQueryRequest = new MappingQueryRequest();
            }
            if (!StringUtil.isValidString(mappingQueryRequest.getCityCode())){
                mappingQueryRequest.setCityCode(null);
            }
            if (!StringUtil.isValidString(mappingQueryRequest.getHotelId())){
                mappingQueryRequest.setHotelId(null);
            }
            //设置商家编码
            mappingQueryRequest.setMerchantCode(cacheUser.getMerchantCode());
            responseDTO = elongMappingService.queryElongMapping(mappingQueryRequest);
        } catch (Exception e) {
            log.error("queryMapping异常：",e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
