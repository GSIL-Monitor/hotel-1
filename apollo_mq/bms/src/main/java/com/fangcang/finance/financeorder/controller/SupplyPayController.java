package com.fangcang.finance.financeorder.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.GetAdvancePaymentRequestDTO;
import com.fangcang.finance.financeorder.request.QueryBillRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.request.SupplyBankCardRequestDTO;
import com.fangcang.finance.financeorder.response.ConfirmFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.GetAdvancePaymentResponseDTO;
import com.fangcang.finance.financeorder.response.SupplyOrderListResponseDTO;
import com.fangcang.finance.financeorder.response.UnpayResponseDTO;
import com.fangcang.finance.financeorder.service.SupplyFinanceOrderService;
import com.fangcang.finance.prepay.request.QueryPrepayListRequestDTO;
import com.fangcang.finance.prepay.response.QueryPrepayContractListResponseDTO;
import com.fangcang.finance.prepay.service.PrepayContractService;
import com.fangcang.supplier.dto.SupplyBankCardDTO;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.response.SingleSupplyInfoResponseDTO;
import com.fangcang.supplier.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinney on 2018/7/7.
 */
@Slf4j
@RestController
@RequestMapping("/supplyPay")
public class SupplyPayController extends BaseController {

    @Autowired
    private SupplyFinanceOrderService supplyFinanceOrderService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private PrepayContractService prepayContractService;

    @RequestMapping(value = "/getUnpay",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> getUnreceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){

        log.debug("enter 查询供应商待付款,{}",JSON.toJSONString(singleBalanceQueryDTO));
        ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        try{
            PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupportDTO = supplyFinanceOrderService.getUnpaid(singleBalanceQueryDTO);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("待收款查询异常,请求参数:{}",JSON.toJSONString(singleBalanceQueryDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_GET_UNRECEIVED_ERROR);
        }
        log.debug("exit 查询供应商待付款,{}",JSON.toJSONString(responseDTO));
        return responseDTO;
    }

    /**
     * 导出订单待付款
     */
    @RequestMapping(value = "/exportUnpayOrder")
    public void exportUnpayOrder(SingleBalanceQueryDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportUnpay param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        List<UnpayResponseDTO> unpayResponseDTOList=supplyFinanceOrderService.exportUnpayOrder(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("供应商待付款","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),UnpayResponseDTO.class,unpayResponseDTOList);
    }

    /**
     * 导出账单待付款
     */
    @RequestMapping(value = "/exportUnpayBill")
    public void exportUnpayBill(QueryBillRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportUnpayBill param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getMerchantCode());
        List<UnpayResponseDTO> unpayResponseDTOList=supplyFinanceOrderService.exportUnpayBill(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("供应商待付款","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),UnpayResponseDTO.class,unpayResponseDTOList);
    }

    @RequestMapping(value = "/getPaid",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> getReceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        log.debug("enter 查询供应商已付款,{}",JSON.toJSONString(singleBalanceQueryDTO));
        ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        try{
            PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupportDTO = supplyFinanceOrderService.getPaid(singleBalanceQueryDTO);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("已收款查询异常,请求参数:{}",JSON.toJSONString(singleBalanceQueryDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_GET_UNRECEIVED_ERROR);
        }
        log.debug("exit 查询供应商已付款,{}",JSON.toJSONString(responseDTO));
        return responseDTO;
    }
    @RequestMapping(value = "/getUnfinished",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> getUnfinished (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        log.debug("enter 查询供应商未完成,{}",JSON.toJSONString(singleBalanceQueryDTO));
        ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        try{
            PaginationSupportDTO<SupplyOrderListResponseDTO> paginationSupportDTO = supplyFinanceOrderService.getUnfinished(singleBalanceQueryDTO);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("未完成查询异常,请求参数:{}",JSON.toJSONString(singleBalanceQueryDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_GET_UNRECEIVED_ERROR);
        }
        log.debug("exit 查询供应商未完成,{}",JSON.toJSONString(responseDTO));
        return responseDTO;
    }

    @RequestMapping(value = "/cancelFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO cancelFinanceOrder (@RequestBody @Validated FinanceOrderRequestDTO financeOrderRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        try{
            financeOrderRequestDTO.setCreator(getFullName());
            financeOrderRequestDTO.setModifier(getFullName());
            financeOrderRequestDTO.setOperator(getFullName());
            financeOrderRequestDTO.setModifyTime(DateUtil.getCurrentDate());
            financeOrderRequestDTO.setCreateTime(DateUtil.getCurrentDate());
            supplyFinanceOrderService.cancelFinanceOrder(financeOrderRequestDTO);
        }catch (Exception e){
            log.error("取消作废收款失败,请求参数:{}",JSON.toJSONString(financeOrderRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CANCEL_ERROR);
        }

        return responseDTO;
    }

    @RequestMapping(value = "/getSupplyBankList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<SupplyBankCardDTO>> getAgentBankList (@RequestBody SupplyBankCardRequestDTO supplyBankCardRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<SupplyBankCardDTO> supplyBankCardDTOList = supplyService.querySupplyBankCardList(supplyBankCardRequestDTO.getSupplyCode());
        responseDTO.setModel(supplyBankCardDTOList);
        return responseDTO;
    }

    @RequestMapping(value = "/confirmFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO confirmFinanceOrder (@RequestBody @Validated ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        try{
            confirmFinanceOrderRequestDTO.setCreator(getFullName());
            confirmFinanceOrderRequestDTO.setModifier(getFullName());
            confirmFinanceOrderRequestDTO.setModifyTime(DateUtil.getCurrentDate());
            confirmFinanceOrderRequestDTO.setCreateTime(DateUtil.getCurrentDate());
            supplyFinanceOrderService.confirmFinanceOrder(confirmFinanceOrderRequestDTO);
        }catch (ServiceException e){
            log.error("确认收款失败：请求参数为 {}",JSON.toJSONString(confirmFinanceOrderRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_CONFIRM_ERROR);
            responseDTO.setFailReason(e.getMessage());
        }
        return responseDTO;
    }

    @RequestMapping(value = "/getVoucher",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<ConfirmFinanceOrderResponseDTO> getVoucher (@RequestBody @Validated ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO){

        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        try{
            ConfirmFinanceOrderResponseDTO confirmFinanceOrderResponseDTO = supplyFinanceOrderService.queryFinanceOrderDetail(confirmFinanceOrderRequestDTO);
            responseDTO.setModel(confirmFinanceOrderResponseDTO);
        }catch (Exception e){
            log.error("查询收款凭证失败,{}",JSON.toJSONString(confirmFinanceOrderRequestDTO),e);
            responseDTO.setErrorCode(ErrorCodeEnum.FINANCE_ORDER_QUERY_DETAIL_ERROR);
        }

        return responseDTO;
    }

    @RequestMapping(value="/getAdvancePayment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<GetAdvancePaymentResponseDTO>> getAdvancePayment(@RequestBody GetAdvancePaymentRequestDTO getAdvancePaymentRequestDTO){
        ResponseDTO<List<GetAdvancePaymentResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        try{

            SingleUserRequestDTO singleUserRequestDTO = new SingleUserRequestDTO();
            singleUserRequestDTO.setSupplyCode(getAdvancePaymentRequestDTO.getSupplyCode());
            ResponseDTO<SingleSupplyInfoResponseDTO> supplyResponse = supplyService.getSupplyById(singleUserRequestDTO);



            QueryPrepayListRequestDTO requestDTO = new QueryPrepayListRequestDTO();
            requestDTO.setSupplyId(supplyResponse.getModel().getSupplyId().intValue());
            requestDTO.setMerchantId(getCacheUser().getMerchantId());
            requestDTO.setPageSize(9999);
            log.info("查询预付款合约，请求参数：{}",JSON.toJSONString(requestDTO));
            PaginationSupportDTO<QueryPrepayContractListResponseDTO> prepayContractResponse = prepayContractService.queryPrepayContractList(requestDTO);

            List<GetAdvancePaymentResponseDTO> getAdvancePaymentResponseDTOList = new ArrayList<>();
            GetAdvancePaymentResponseDTO getAdvancePaymentResponseDTO = null;
            for (QueryPrepayContractListResponseDTO tempDTO : prepayContractResponse.getItemList()){
                //没有合约ID，表示还没有新增合约
                if (null == tempDTO.getId()){
                    continue;
                }
                getAdvancePaymentResponseDTO = new GetAdvancePaymentResponseDTO();
                getAdvancePaymentResponseDTO.setValidBeginDate(tempDTO.getValidBeginDate());
                getAdvancePaymentResponseDTO.setValidEndDate(tempDTO.getValidEndDate());
                getAdvancePaymentResponseDTO.setCurrency(tempDTO.getCurrency());
                getAdvancePaymentResponseDTO.setContractId(tempDTO.getId());
                getAdvancePaymentResponseDTO.setPrepayBalance(tempDTO.getPrepayBalance());
                getAdvancePaymentResponseDTO.setHotelName(tempDTO.getHotelName());

                getAdvancePaymentResponseDTOList.add(getAdvancePaymentResponseDTO);
            }

            responseDTO.setModel(getAdvancePaymentResponseDTOList);


        }catch (Exception e){
            log.error("查询预付款余额失败，请求参数：{}",JSON.toJSONString(getAdvancePaymentRequestDTO));
            responseDTO.setErrorCode(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }

        return  responseDTO;
    }

}
