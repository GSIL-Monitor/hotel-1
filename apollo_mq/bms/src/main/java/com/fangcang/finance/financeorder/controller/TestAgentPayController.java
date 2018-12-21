package com.fangcang.finance.financeorder.controller;

import com.fangcang.agent.dto.AgentBankCardDTO;
import com.fangcang.agent.request.AgentBankCardRequestDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.financeorder.request.ConfirmFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SingleBalanceQueryDTO;
import com.fangcang.finance.financeorder.response.AgentOrderListResponseDTO;
import com.fangcang.finance.financeorder.response.ConfirmFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderLogResponseDTO;
import com.fangcang.finance.financeorder.response.FinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.FinancePayItemAttchResponseDTO;
import com.fangcang.finance.financeorder.response.VoucherResponseDTO;
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
@RequestMapping("/test/agentPay")
public class TestAgentPayController extends BaseController {

    @RequestMapping(value = "/getUnreceived",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> getUnreceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        AgentOrderListResponseDTO responseDTO = new AgentOrderListResponseDTO();
        responseDTO.setCheckinDate(new Date());
        responseDTO.setCheckoutDate(new Date());
        responseDTO.setGuestNames("张三/李四");
        responseDTO.setHotelName("测试酒店");
        responseDTO.setHasReveived(new BigDecimal("0"));
        responseDTO.setOrderCode("H2018070912031");
        responseDTO.setOrderFinanceStatus(0);
        responseDTO.setOrderId(135013);
        responseDTO.setRoomTypeNames("大床房|双床房");
        responseDTO.setShouldReceive(new BigDecimal("900"));
        responseDTO.setAgentCode("D123456");
        responseDTO.setCurrency("CNY");
        responseDTO.setRateplanName("双早");
        responseDTO.setAgentName("分销商名称1111");

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

        List<AgentOrderListResponseDTO> list = new ArrayList<>();
        list.add(responseDTO);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(10);
        paginationSupport.setTotalCount(120);
        paginationSupport.setTotalPage(12);
        paginationSupport.setCurrentPage(1);

        ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> responseDTO1 = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        responseDTO1.setModel(paginationSupport);
        return responseDTO1;
    }

    @RequestMapping(value = "/getReceived",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> getReceived (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        AgentOrderListResponseDTO responseDTO = new AgentOrderListResponseDTO();
        responseDTO.setCheckinDate(new Date());
        responseDTO.setCheckoutDate(new Date());
        responseDTO.setGuestNames("张三/李四");
        responseDTO.setHotelName("测试酒店");
        responseDTO.setHasReveived(new BigDecimal("900"));
        responseDTO.setOrderCode("H2018070912032");
        responseDTO.setOrderFinanceStatus(1);
        responseDTO.setOrderId(135014);
        responseDTO.setRoomTypeNames("大床房|双床房");
        responseDTO.setShouldReceive(new BigDecimal("900"));
        responseDTO.setAgentCode("D123456");
        responseDTO.setCurrency("CNY");
        responseDTO.setRateplanName("双早");
        responseDTO.setAgentName("分销商名称1111");


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

        List<AgentOrderListResponseDTO> list = new ArrayList<>();
        list.add(responseDTO);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(10);
        paginationSupport.setTotalCount(120);
        paginationSupport.setTotalPage(12);
        paginationSupport.setCurrentPage(1);

        ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> responseDTO1 = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        responseDTO1.setModel(paginationSupport);
        return responseDTO1;
    }
    @RequestMapping(value = "/getUnfinished",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> getUnfinished (@RequestBody SingleBalanceQueryDTO singleBalanceQueryDTO){
        AgentOrderListResponseDTO responseDTO = new AgentOrderListResponseDTO();
        responseDTO.setCheckinDate(new Date());
        responseDTO.setCheckoutDate(new Date());
        responseDTO.setGuestNames("张三/李四");
        responseDTO.setHotelName("测试酒店");
        responseDTO.setHasReveived(new BigDecimal("0"));
        responseDTO.setOrderCode("H2018070912032");
        responseDTO.setOrderFinanceStatus(1);
        responseDTO.setOrderId(135014);
        responseDTO.setRoomTypeNames("大床房|双床房");
        responseDTO.setShouldReceive(new BigDecimal("1000"));
        responseDTO.setAgentCode("D123456");
        responseDTO.setCurrency("CNY");
        responseDTO.setRateplanName("双早");
        responseDTO.setAgentName("分销商名称1111");


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
        responseDTO.setFinanceOrderList(financeOrderResponseDTOList);

        List<AgentOrderListResponseDTO> list = new ArrayList<>();
        list.add(responseDTO);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(10);
        paginationSupport.setTotalCount(120);
        paginationSupport.setTotalPage(12);
        paginationSupport.setCurrentPage(1);

        ResponseDTO<PaginationSupportDTO<AgentOrderListResponseDTO>> responseDTO1 = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        responseDTO1.setModel(paginationSupport);
        return responseDTO1;
    }

    @RequestMapping(value = "/cancelFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO cancelFinanceOrder (@RequestBody @Validated FinanceOrderRequestDTO financeOrderRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        return responseDTO;
    }

    @RequestMapping(value = "/getAgentBankList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<List<AgentBankCardDTO>> getAgentBankList (@RequestBody AgentBankCardRequestDTO agentBankCardRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        AgentBankCardDTO agentBankCardDTO1 = new AgentBankCardDTO();
        agentBankCardDTO1.setAccountName("Test01");
        agentBankCardDTO1.setAccountNumber("111123456789");
        agentBankCardDTO1.setBankCardId(1111);
        agentBankCardDTO1.setOpeningBank("招商银行");

        AgentBankCardDTO agentBankCardDTO2 = new AgentBankCardDTO();
        agentBankCardDTO2.setAccountName("Test02");
        agentBankCardDTO2.setAccountNumber("222223456789");
        agentBankCardDTO2.setBankCardId(2222);
        agentBankCardDTO2.setOpeningBank("建设银行");

        List<AgentBankCardDTO> agentBankCardDTOList = new ArrayList<>();
        agentBankCardDTOList.add(agentBankCardDTO1);
        agentBankCardDTOList.add(agentBankCardDTO2);

        responseDTO.setModel(agentBankCardDTOList);
        return responseDTO;
    }

    @RequestMapping(value = "/confirmFinanceOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO confirmFinanceOrder (@RequestBody @Validated ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ErrorCodeEnum.SUCCESS);
        return responseDTO;
    }

    @RequestMapping(value = "/getVoucher",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<ConfirmFinanceOrderResponseDTO> getVoucher (@RequestBody @Validated ConfirmFinanceOrderRequestDTO confirmFinanceOrderRequestDTO){
        ResponseDTO<ConfirmFinanceOrderResponseDTO> responseDTO = new ResponseDTO<>(ErrorCodeEnum.SUCCESS);
        ConfirmFinanceOrderResponseDTO confirmFinanceOrderResponseDTO = new ConfirmFinanceOrderResponseDTO();
        confirmFinanceOrderResponseDTO.setNotifyAmount(new BigDecimal("1000"));
        confirmFinanceOrderResponseDTO.setCurrency("CNY");
        confirmFinanceOrderResponseDTO.setFinanceOrderId(111);
        confirmFinanceOrderResponseDTO.setMerchantCode("M10000002");
        confirmFinanceOrderResponseDTO.setOrgCode("A10000001");

        List<VoucherResponseDTO> voucherList = new ArrayList<>();
        VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
        voucherResponseDTO.setId(1);
        voucherResponseDTO.setCurrency("CNY");
        voucherResponseDTO.setAmount(new BigDecimal("1000"));
        voucherResponseDTO.setFinanceOrderId(111);

        List<FinancePayItemAttchResponseDTO> financePayItemAttchList = new ArrayList<>();
        FinancePayItemAttchResponseDTO financePayItemAttchResponseDTO = new FinancePayItemAttchResponseDTO();
        financePayItemAttchResponseDTO.setRealpath("/data/image/finance");
        financePayItemAttchResponseDTO.setUrl("http://apollo.file.com/finance/12312312.png");
        financePayItemAttchResponseDTO.setName("工行水单20180711010101001");
        financePayItemAttchList.add(financePayItemAttchResponseDTO);
        voucherResponseDTO.setFinancePayItemAttchList(financePayItemAttchList);

        voucherResponseDTO.setMerchantBankName("工行");
        voucherResponseDTO.setOrgBankName("建行");
        voucherResponseDTO.setNote("团房销账订单");
        voucherResponseDTO.setPassage(4);
        voucherResponseDTO.setSerialNo("12321321656");
        voucherList.add(voucherResponseDTO);

        List<FinanceOrderLogResponseDTO> financeOrderlogList = new ArrayList<>();
        FinanceOrderLogResponseDTO agentFinanceOrderLogDO1 = new FinanceOrderLogResponseDTO();
        agentFinanceOrderLogDO1.setFinanceOrderId(111);
        agentFinanceOrderLogDO1.setOperateTime("2018-7-31 17:02:37");
        agentFinanceOrderLogDO1.setOperator("张三");
        agentFinanceOrderLogDO1.setContent("创建工单");

        FinanceOrderLogResponseDTO agentFinanceOrderLogDO2 = new FinanceOrderLogResponseDTO();
        agentFinanceOrderLogDO2.setFinanceOrderId(111);
        agentFinanceOrderLogDO2.setOperateTime("2018-7-31 17:02:46");
        agentFinanceOrderLogDO2.setOperator("李四");
        agentFinanceOrderLogDO2.setContent("确认工单");

        financeOrderlogList.add(agentFinanceOrderLogDO1);
        financeOrderlogList.add(agentFinanceOrderLogDO2);

        confirmFinanceOrderResponseDTO.setVoucherList(voucherList);
        confirmFinanceOrderResponseDTO.setFinanceOrderlogList(financeOrderlogList);

        responseDTO.setModel(confirmFinanceOrderResponseDTO);
        return responseDTO;
    }



}
