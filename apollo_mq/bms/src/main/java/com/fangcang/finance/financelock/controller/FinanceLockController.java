package com.fangcang.finance.financelock.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.financelock.request.FinanceLockOrderRequestDTO;
import com.fangcang.finance.financelock.request.QueryLockLogRequestDTO;
import com.fangcang.finance.financelock.request.UnlockOrderListRequestDTO;
import com.fangcang.finance.financelock.response.FinanceLockLogResponseDTO;
import com.fangcang.finance.financelock.response.UnlockOrderListResponseDTO;
import com.fangcang.finance.financelock.service.FinanceLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/04
 */
@RestController
@Slf4j
@RequestMapping("/finance")
public class FinanceLockController extends BaseController {

    @Resource
    private FinanceLockService financeLockService;

    @RequestMapping(value = "/unlockOrderList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<UnlockOrderListResponseDTO>> unlockOrderList(@RequestBody UnlockOrderListRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            PaginationSupportDTO<UnlockOrderListResponseDTO> page = financeLockService.unlockOrderList(requestDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("unlockOrderList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }


    @RequestMapping(value = "/lockOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO lockOrder(@RequestBody @Valid FinanceLockOrderRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setModifier(getFullName());
            requestDTO.setModifyTime(new Date());
            responseDTO = financeLockService.lockOrder(requestDTO);
        } catch (Exception e) {
            log.error("lockOrder error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryLockLog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<FinanceLockLogResponseDTO>> queryLockLog(@RequestBody @Valid QueryLockLogRequestDTO requestDTO) {
        ResponseDTO<List<FinanceLockLogResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = financeLockService.queryLockLog(requestDTO);
        } catch (Exception e) {
            log.error("queryLockLog error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

}
