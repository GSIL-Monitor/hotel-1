package com.fangcang.base.controller;

import com.fangcang.base.dto.CommonCityDTO;
import com.fangcang.base.request.QueryMerchantCityDTO;
import com.fangcang.base.request.UpdateCommonCityDTO;
import com.fangcang.base.service.CommonCityService;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(("/base/commoncity"))
public class CommonCityController extends BaseController {

    @Autowired
    private CommonCityService commonCityService;

    @RequestMapping(value = "/merchantcitylist" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO merchantcitylist(@RequestBody QueryMerchantCityDTO requestDTO) {
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=commonCityService.queryMerchantCityForPage(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("commonCityService.queryMerchantCityForPage 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/updatecommoncity" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updatecommoncity(@RequestBody UpdateCommonCityDTO requestDTO){
        ResponseDTO responseDTO=null;
        try{
            requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
            requestDTO.setOperator(super.getCacheUser().getUsername());
            responseDTO=commonCityService.updateCommonCity(requestDTO);
        }catch (Exception e){
            log.error("commonCityService.updateCommonCity 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/querycommoncity" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querycommoncity() {
        ResponseDTO responseDTO=null;
        try{
            List<CommonCityDTO> list=commonCityService.queryCommonCity(super.getCacheUser().getMerchantCode());
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(list);
        }catch (Exception e){
            log.error("commonCityService.queryCommonCity 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
