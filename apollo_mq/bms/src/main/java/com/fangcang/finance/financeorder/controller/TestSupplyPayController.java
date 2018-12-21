package com.fangcang.finance.financeorder.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.GetAdvancePaymentRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.GetAdvancePaymentResponseDTO;
import com.fangcang.finance.financeorder.response.SupplyOrderListResponseDTO;
import com.fangcang.supplier.dto.SupplyBankCardDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vinney on 2018/7/7.
 */
@RestController
@RequestMapping("/test/supplyPay")
public class TestSupplyPayController extends BaseController {

    @RequestMapping(value = "/getUnpay",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> getUnreceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        SupplyOrderListResponseDTO responseDTO = new SupplyOrderListResponseDTO();
        responseDTO.setCheckinDate(new Date());
        responseDTO.setCheckoutDate(new Date());
        responseDTO.setGuestNames("张三/李四");
        responseDTO.setHotelName("测试酒店");
        responseDTO.setHasPaid(new BigDecimal("0"));
        responseDTO.setSupplyCode("G2018070912031");
        responseDTO.setOrderFinanceStatus(0);
        responseDTO.setSupplyOrderId(135013L);
        responseDTO.setRoomTypeNames("大床房|双床房");
        responseDTO.setShouldPay(new BigDecimal("900"));
        responseDTO.setSupplyCode("S10000001");

        List<FinanceOrderResponseDTO> financeOrderResponseDTOList = new ArrayList<>();
        for (int i = 0 ; i < 2 ; i++){
            FinanceOrderResponseDTO financeOrderResponseDTO = new FinanceOrderResponseDTO();
            financeOrderResponseDTO.setOrderCode("H2018070912032");
            financeOrderResponseDTO.setCurrency("CNY");
            financeOrderResponseDTO.setFinanceCode("F1203921");
            financeOrderResponseDTO.setFinanceStatus(0);
            financeOrderResponseDTO.setNotifyAmount(new BigDecimal("300"));
            financeOrderResponseDTOList.add(financeOrderResponseDTO);
        }
        responseDTO.setFinanceOrderList(financeOrderResponseDTOList);

        List<SupplyOrderListResponseDTO> list = new ArrayList<>();
        list.add(responseDTO);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(10);
        paginationSupport.setTotalCount(120);
        paginationSupport.setTotalPage(12);
        paginationSupport.setCurrentPage(1);

        ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> responseDTO1 = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        responseDTO1.setModel(paginationSupport);
        return responseDTO1;
    }

    @RequestMapping(value = "/getPaid",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> getReceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        SupplyOrderListResponseDTO responseDTO = new SupplyOrderListResponseDTO();
        responseDTO.setCheckinDate(new Date());
        responseDTO.setCheckoutDate(new Date());
        responseDTO.setGuestNames("张三/李四");
        responseDTO.setHotelName("测试酒店");
        responseDTO.setHasPaid(new BigDecimal("900"));
        responseDTO.setSupplyOrderCode("H2018070912032");
        responseDTO.setOrderFinanceStatus(1);
        responseDTO.setSupplyOrderId(135014L);
        responseDTO.setRoomTypeNames("大床房|双床房");
        responseDTO.setShouldPay(new BigDecimal("900"));
        responseDTO.setSupplyCode("S10000001");

        List<FinanceOrderResponseDTO> financeOrderResponseDTOList = new ArrayList<>();
        for (int i = 0 ; i < 2 ; i++){
            FinanceOrderResponseDTO financeOrderResponseDTO = new FinanceOrderResponseDTO();
            financeOrderResponseDTO.setOrderCode("H2018070912032");
            financeOrderResponseDTO.setCurrency("CNY");
            financeOrderResponseDTO.setFinanceCode("F1203921");
            financeOrderResponseDTO.setFinanceStatus(1);
            financeOrderResponseDTO.setNotifyAmount(new BigDecimal("300"));
            financeOrderResponseDTOList.add(financeOrderResponseDTO);
        }
        responseDTO.setFinanceOrderList(financeOrderResponseDTOList);

        List<SupplyOrderListResponseDTO> list = new ArrayList<>();
        list.add(responseDTO);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(10);
        paginationSupport.setTotalCount(120);
        paginationSupport.setTotalPage(12);
        paginationSupport.setCurrentPage(1);

        ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> responseDTO1 = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        responseDTO1.setModel(paginationSupport);
        return responseDTO1;
    }
    @RequestMapping(value = "/getUnfinished",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> getUnfinished (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        SupplyOrderListResponseDTO supplyOrderListResponseDTO = new SupplyOrderListResponseDTO();
        supplyOrderListResponseDTO.setCheckinDate(new Date());
        supplyOrderListResponseDTO.setCheckoutDate(new Date());
        supplyOrderListResponseDTO.setGuestNames("张三/李四");
        supplyOrderListResponseDTO.setHotelName("测试酒店");
        supplyOrderListResponseDTO.setHasPaid(new BigDecimal("0"));
        supplyOrderListResponseDTO.setSupplyOrderCode("H2018070912032");
        supplyOrderListResponseDTO.setOrderFinanceStatus(1);
        supplyOrderListResponseDTO.setSupplyOrderId(135014L);
        supplyOrderListResponseDTO.setRoomTypeNames("大床房|双床房");
        supplyOrderListResponseDTO.setShouldPay(new BigDecimal("1000"));
        supplyOrderListResponseDTO.setSupplyCode("S10000001");

        List<FinanceOrderResponseDTO> financeOrderResponseDTOList = new ArrayList<>();
        for (int i = 0 ; i < 1 ; i++){
            FinanceOrderResponseDTO financeOrderResponseDTO = new FinanceOrderResponseDTO();
            financeOrderResponseDTO.setOrderCode("H2018070912032");
            financeOrderResponseDTO.setCurrency("CNY");
            financeOrderResponseDTO.setFinanceCode("F1203921");
            financeOrderResponseDTO.setFinanceStatus(1);
            financeOrderResponseDTO.setNotifyAmount(new BigDecimal("300"));
            financeOrderResponseDTO.setFinanceOrderId(Long.valueOf(1000+i));
            financeOrderResponseDTOList.add(financeOrderResponseDTO);
        }
        supplyOrderListResponseDTO.setFinanceOrderList(financeOrderResponseDTOList);

        List<SupplyOrderListResponseDTO> list = new ArrayList<>();
        list.add(supplyOrderListResponseDTO);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(10);
        paginationSupport.setTotalCount(120);
        paginationSupport.setTotalPage(12);
        paginationSupport.setCurrentPage(1);

        ResponseDTO<PaginationSupportDTO<SupplyOrderListResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        responseDTO.setModel(paginationSupport);
        return responseDTO;
    }

    @RequestMapping(value = "/cancelFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO cancelFinanceOrder (@RequestBody @Validated FinanceOrderRequestDTO financeOrderRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        return responseDTO;
    }

    @RequestMapping(value = "/getSupplyBankList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<SupplyBankCardDTO>> getSupplyBankList (){
        ResponseDTO<List<SupplyBankCardDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);

        SupplyBankCardDTO agentBankCardDTO = new SupplyBankCardDTO();
        agentBankCardDTO.setAccountName("Test01");
        agentBankCardDTO.setAccountNumber("111123456789");
        agentBankCardDTO.setBankCardId(1111);
        agentBankCardDTO.setOpeningBank("招商银行");

        SupplyBankCardDTO agentBankCardDTO2 = new SupplyBankCardDTO();
        agentBankCardDTO2.setAccountName("Test02");
        agentBankCardDTO2.setAccountNumber("222223456789");
        agentBankCardDTO2.setBankCardId(2222);
        agentBankCardDTO2.setOpeningBank("建设银行");

        List<SupplyBankCardDTO> supplyBankCardDTOList = new ArrayList<>();
        supplyBankCardDTOList.add(agentBankCardDTO);
        supplyBankCardDTOList.add(agentBankCardDTO2);

        responseDTO.setModel(supplyBankCardDTOList);
        return responseDTO;
    }

    @RequestMapping(value = "/confirmFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO confirmFinanceOrder (@RequestBody @Validated ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        return responseDTO;
    }


    @RequestMapping(value = "/getAdvancePayment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<GetAdvancePaymentResponseDTO>> getAdvancePayment (@RequestBody GetAdvancePaymentRequestDTO getAdvancePaymentRequestDTO){
        ResponseDTO<List<GetAdvancePaymentResponseDTO>> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        List<GetAdvancePaymentResponseDTO> supplyContractList = new ArrayList<>();
        GetAdvancePaymentResponseDTO getAdvancePaymentResponseDTO = new GetAdvancePaymentResponseDTO();
        getAdvancePaymentResponseDTO.setCurrency("CNY");
        getAdvancePaymentResponseDTO.setHotelName("XXXX酒店");
        getAdvancePaymentResponseDTO.setPrepayBalance(new BigDecimal("100000"));
        getAdvancePaymentResponseDTO.setValidBeginDate("2018-01-01");
        getAdvancePaymentResponseDTO.setValidEndDate("2018-06-30");
        supplyContractList.add(getAdvancePaymentResponseDTO);

        GetAdvancePaymentResponseDTO getAdvancePaymentResponseDTO1 = new GetAdvancePaymentResponseDTO();
        getAdvancePaymentResponseDTO1.setCurrency("CNY");
        getAdvancePaymentResponseDTO1.setHotelName("XXXX酒店");
        getAdvancePaymentResponseDTO1.setPrepayBalance(new BigDecimal("100000"));
        getAdvancePaymentResponseDTO1.setValidBeginDate("2018-07-01");
        getAdvancePaymentResponseDTO1.setValidEndDate("2018-12-31");
        supplyContractList.add(getAdvancePaymentResponseDTO1);

        responseDTO.setModel(supplyContractList);
        return responseDTO;
    }

}
