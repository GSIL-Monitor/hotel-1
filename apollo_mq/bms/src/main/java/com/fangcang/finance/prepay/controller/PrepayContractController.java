package com.fangcang.finance.prepay.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.finance.prepay.dto.PrepayContractItemDTO;
import com.fangcang.finance.prepay.dto.PrepayContractLogDTO;
import com.fangcang.finance.prepay.request.AddOrUpdatePrepayContractRequestDTO;
import com.fangcang.finance.prepay.request.PrepayLogInfoRequestDTO;
import com.fangcang.finance.prepay.request.PrepayRechargeRequestDTO;
import com.fangcang.finance.prepay.request.QueryPrepayItemListRequestDTO;
import com.fangcang.finance.prepay.request.QueryPrepayListRequestDTO;
import com.fangcang.finance.prepay.request.QueryRechargeListRequestDTO;
import com.fangcang.finance.prepay.request.UpdateDepositAmountRequestDTO;
import com.fangcang.finance.prepay.response.QueryPrepayContractListResponseDTO;
import com.fangcang.finance.prepay.response.QueryRechargeListResponseDTO;
import com.fangcang.finance.prepay.service.PrepayContractService;
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
 * @date : 2018/7/6
 */
@RestController
@Slf4j
@RequestMapping("/finance/prepay")
public class PrepayContractController extends BaseController {

    @Resource
    private PrepayContractService prepayContractService;

    @RequestMapping(value = "/queryPrepayContractList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<QueryPrepayContractListResponseDTO>> queryPrepayContractList(@RequestBody QueryPrepayListRequestDTO requestDTO) {
        ResponseDTO<PaginationSupportDTO<QueryPrepayContractListResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            requestDTO.setMerchantId(getCacheUser().getMerchantId());
            PaginationSupportDTO<QueryPrepayContractListResponseDTO> page = prepayContractService.queryPrepayContractList(requestDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("queryPrepayContractList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }


    @RequestMapping(value = "/addOrUpdatePrepayContract", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addOrUpdatePrepayContract(@RequestBody @Valid AddOrUpdatePrepayContractRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getCacheUser().getRealName());
            requestDTO.setCreateTime(new Date());
            requestDTO.setModifier(getCacheUser().getRealName());
            requestDTO.setModifyTime(new Date());
            responseDTO = prepayContractService.addOrUpdatePrepayContract(requestDTO);
        } catch (Exception e) {
            log.error("addOrUpdatePrepayContract error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO recharge(@RequestBody @Valid PrepayRechargeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setCreator(getCacheUser().getRealName());
            requestDTO.setCreateTime(DateUtil.dateToStringWithHms(new Date()));
            responseDTO = prepayContractService.recharge(requestDTO);
        } catch (Exception e) {
            log.error("recharge error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryRechargeList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<QueryRechargeListResponseDTO>> queryRechargeList(@RequestBody @Valid QueryRechargeListRequestDTO requestDTO) {
        ResponseDTO<PaginationSupportDTO<QueryRechargeListResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            PaginationSupportDTO<QueryRechargeListResponseDTO> page = prepayContractService.queryRechargeList(requestDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("queryRechargeList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryPrepayItemList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<PrepayContractItemDTO>> queryPrepayItemList(@RequestBody @Valid QueryPrepayItemListRequestDTO requestDTO) {
        ResponseDTO<PaginationSupportDTO<PrepayContractItemDTO>> responseDTO = new ResponseDTO<>();
        try {
            PaginationSupportDTO<PrepayContractItemDTO> page = prepayContractService.queryPrepayItemList(requestDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("queryPrepayItemList error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/updateDepositAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateDepositAmount(@RequestBody @Valid UpdateDepositAmountRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            requestDTO.setModifier(getCacheUser().getRealName());
            requestDTO.setModifyTime(new Date());
            responseDTO = prepayContractService.updateDepositAmount(requestDTO);
        } catch (Exception e) {
            log.error("updateDepositAmount error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/prepayLogInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<PrepayContractLogDTO>> prepayLogInfo(@RequestBody @Valid PrepayLogInfoRequestDTO requestDTO) {
        ResponseDTO<List<PrepayContractLogDTO>> responseDTO = new ResponseDTO<>();
        try {
            responseDTO = prepayContractService.prepayLogInfo(requestDTO);
        } catch (Exception e) {
            log.error("prepayLogInfo error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
