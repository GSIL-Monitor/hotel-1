package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.product.dto.QuotaStateDTO;
import com.fangcang.product.dto.QuotaStateDailyDTO;
import com.fangcang.product.request.*;
import com.fangcang.product.response.DebuctOrRetreatQuotaResponseDTO;
import com.fangcang.product.response.DebuctQuotaQueryResponseDTO;
import com.fangcang.product.response.QuotaStateResponseDTO;
import com.fangcang.product.service.QuotaStateService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@RestController
@RequestMapping(value = "/product")
public class QuotaStateController extends BaseController{

    @Autowired
    private QuotaStateService quotaStateService;

    /**
     * 查看配额池房态
     * @param quotaStateQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryQuotaStateDailyInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<QuotaStateResponseDTO> queryQuotaStateDailyInfo(@RequestBody @Valid QuotaStateQueryDTO quotaStateQueryDTO){
        return quotaStateService.queryQuotaStateDailyInfo(quotaStateQueryDTO);
    }

    /**
     * 批量调整房态
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/batchModifyQuotaState",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO batchModifyQuotaState(@RequestBody @Valid BatchSaveQuotaStateRequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO userDTO = super.getCacheUser();
            requestDTO.setCreator(super.getFullName());
            requestDTO.setModifier(super.getFullName());
            requestDTO.setMerchantCode(userDTO.getMerchantCode());
            responseDTO = quotaStateService.batchModifyQuotaState(requestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 单日调整房态
     * @param quotaStateDailyRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateQuotaStateDaily",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveOrUpdateQuotaStateDaily(@RequestBody @Valid QuotaStateDailyRequestDTO quotaStateDailyRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO userDTO = super.getCacheUser();
            quotaStateDailyRequestDTO.setMerchantCode(userDTO.getMerchantCode());
            quotaStateDailyRequestDTO.setCreator(super.getFullName());
            quotaStateDailyRequestDTO.setModifier(super.getFullName());
            responseDTO = quotaStateService.saveOrUpdateQuotaStateDaily(quotaStateDailyRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 扣、退配额接口
     * @param debuctOrRetreatQuotaRequestDTO
     * @return
     */
    @RequestMapping(value = "/debuctOrRetreatQuota",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<DebuctOrRetreatQuotaResponseDTO> debuctOrRetreatQuota(@RequestBody @Valid DebuctOrRetreatQuotaRequestDTO debuctOrRetreatQuotaRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        try {
            UserDTO userDTO = super.getCacheUser();
            debuctOrRetreatQuotaRequestDTO.setCreator(super.getFullName());
            debuctOrRetreatQuotaRequestDTO.setModifier(super.getFullName());
            debuctOrRetreatQuotaRequestDTO.setMerchantCode(userDTO.getMerchantCode());
            responseDTO = quotaStateService.debuctOrRetreatQuota(debuctOrRetreatQuotaRequestDTO);
        } catch (Exception e) {
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 查询订单实扣配额
     * @param debuctQuotaQueryDTO
     * @return
     */
    @RequestMapping(value = "/queryOrderDebuctQuotaInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<DebuctQuotaQueryResponseDTO> queryOrderDebuctQuotaInfo(@RequestBody @Valid DebuctQuotaQueryDTO debuctQuotaQueryDTO){
        return quotaStateService.queryOrderDebuctQuotaInfo(debuctQuotaQueryDTO);
    }
}
