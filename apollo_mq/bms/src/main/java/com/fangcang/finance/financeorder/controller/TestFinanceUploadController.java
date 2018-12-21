package com.fangcang.finance.financeorder.controller;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.finance.financeorder.response.FinancePayItemAttchResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vinney on 2018/7/11.
 */
@RestController
@RequestMapping("/test/finance")
public class TestFinanceUploadController extends BaseController {

    @RequestMapping(value = "/uploadFinanceItemAttch",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<FinancePayItemAttchResponseDTO> uploadFinanceItemAttch(@RequestParam(value = "file", required = false) MultipartFile file) {
        ResponseDTO<FinancePayItemAttchResponseDTO> responseDTOResponseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        FinancePayItemAttchResponseDTO financePayItemAttchResponseDTO = new FinancePayItemAttchResponseDTO();
        financePayItemAttchResponseDTO.setName("凭证附件1");
        financePayItemAttchResponseDTO.setUrl("http://image.apollo.com/112312.png");
        financePayItemAttchResponseDTO.setRealpath("/data/image/finance");

        responseDTOResponseDTO.setModel(financePayItemAttchResponseDTO);
        return responseDTOResponseDTO;
    }
}
