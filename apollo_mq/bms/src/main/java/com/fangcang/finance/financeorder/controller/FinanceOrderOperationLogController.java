package com.fangcang.finance.financeorder.controller;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderOperationLogResponseDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vinney on 2018/7/17.
 */
@RestController
@RequestMapping("/financeOrderOperationLog")
public class FinanceOrderOperationLogController extends BaseController {

    @RequestMapping(value = "/getFinanceOrderOperationLog",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<FinanceOrderOperationLogResponseDTO> getFinanceOrderOperationLog (@RequestBody @Validated FinanceOrderRequestDTO financeOrderRequestDTO){
        ResponseDTO<FinanceOrderOperationLogResponseDTO> responseDTO = new ResponseDTO<FinanceOrderOperationLogResponseDTO>(ErrorCodeEnum.SUCCESS);
        FinanceOrderOperationLogResponseDTO financeOrderOperationLogResponseDTO = new FinanceOrderOperationLogResponseDTO();
        financeOrderOperationLogResponseDTO.setContent("确认工单");
        financeOrderOperationLogResponseDTO.setOperateTime("2018-7-17 17:43:36");
        financeOrderOperationLogResponseDTO.setOperator("小强");

        responseDTO.setModel(financeOrderOperationLogResponseDTO);
        return responseDTO;
    }
}
