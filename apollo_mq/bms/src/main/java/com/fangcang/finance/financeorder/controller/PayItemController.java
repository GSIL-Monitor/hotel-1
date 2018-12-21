package com.fangcang.finance.financeorder.controller;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.dto.PayItemDTO;
import com.fangcang.finance.financeorder.request.QueryPayItemDTO;
import com.fangcang.finance.financeorder.service.PayItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/finance")
public class PayItemController extends BaseController {

    @Autowired
    private PayItemService payItemService;

    /**
     * 查询付款或收款凭证
     */
    @RequestMapping(value = "/queryPayItem", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryPayItem(@RequestBody QueryPayItemDTO requestDTO) {
        log.info("queryPayItem param:"+requestDTO);
        ResponseDTO responseDTO=null;
        try{
            List<PayItemDTO> payItemDTOList=payItemService.queryPayItem(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(payItemDTOList);
        }catch (Exception e){
            log.error("payItemService.queryPayItem 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
