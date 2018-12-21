package com.fangcang.base.controller;

import com.fangcang.base.dto.CommonCityDTO;
import com.fangcang.base.request.QueryMerchantCityDTO;
import com.fangcang.base.request.UpdateCommonCityDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(("/test/base/commoncity"))
public class TestCommonCityController extends BaseController {

    @RequestMapping(value = "/merchantcitylist" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO merchantcitylist(@RequestBody QueryMerchantCityDTO requestDTO) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<CommonCityDTO> list=new ArrayList<>();
        list.add(new CommonCityDTO("SZX","深圳",1));
        list.add(new CommonCityDTO("SZX","广州",1));
        list.add(new CommonCityDTO("SZX","珠海",0));
        list.add(new CommonCityDTO("SZX","香港",0));
        list.add(new CommonCityDTO("SZX","澳门",0));
        list.add(new CommonCityDTO("SZX","惠州",0));
        list.add(new CommonCityDTO("SZX","杭州",0));

        PaginationSupportDTO<CommonCityDTO> paginationSupportDTO=new PaginationSupportDTO<>();
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setPageSize(10);
        paginationSupportDTO.setTotalPage(50);
        paginationSupportDTO.setTotalCount(5);
        paginationSupportDTO.setItemList(list);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/updatecommoncity" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO updatecommoncity(@RequestBody UpdateCommonCityDTO requestDTO){
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }
}
