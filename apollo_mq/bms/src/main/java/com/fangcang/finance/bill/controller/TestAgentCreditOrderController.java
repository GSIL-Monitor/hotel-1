package com.fangcang.finance.bill.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.finance.bill.request.QueryCheckOutDTO;
import com.fangcang.finance.bill.response.CheckOutDTO;
import com.fangcang.finance.bill.response.CheckOutOrderDTO;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(("/test/finance/creditOrder"))
public class TestAgentCreditOrderController extends BaseController {

    /**
     * 查询分销商可出账信息
     */
    @RequestMapping(value = "/queryAgentCheckOut", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentCheckOut(@RequestBody QueryCheckOutDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<CheckOutDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
            CheckOutDTO checkOutDTO=new CheckOutDTO("A321321"+i,"春秋旅游商",32, BigDecimal.valueOf(34223L),"18%",
                    Arrays.asList(new MultipleCurrencyAmountDTO("CNY",BigDecimal.valueOf(34223L),null,null)));
            list.add(checkOutDTO);
            i++;
            CheckOutDTO checkOutDTO1=new CheckOutDTO("A321321"+i,"环球旅游行家",18, BigDecimal.valueOf(453L),"18%",
                    Arrays.asList(new MultipleCurrencyAmountDTO("CNY",BigDecimal.valueOf(453L),null,null)));
            list.add(checkOutDTO1);
        }
        PaginationSupportDTO<CheckOutDTO> paginationSupportDTO=new PaginationSupportDTO<>();
        paginationSupportDTO.setTotalCount(30);
        paginationSupportDTO.setTotalPage(2);
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setPageSize(15);
        responseDTO.setModel(paginationSupportDTO);
        paginationSupportDTO.setItemList(list);
        return responseDTO;
    }

    /**
     * 查询可出账的订单
     */
    @RequestMapping(value = "/queryCheckOutOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryCheckOutOrder(@RequestBody QueryCheckOutDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<CheckOutOrderDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
//            CheckOutOrderDTO checkOutOrderDTO=new CheckOutOrderDTO(1,321,"D323123","深圳喜马拉雅大酒店","大床房","无早限时优惠","李雷、韩梅梅","2016/03/06","2016/03/08",
//                    5,"CNY","BA3223332",BigDecimal.valueOf(288),"2017-03-09 14:50:20",1);
//            list.add(checkOutOrderDTO);
//            i++;
//            CheckOutOrderDTO checkOutOrderDTO1=new CheckOutOrderDTO(1,322,"D767773","桔子酒店精选","大床房","无早","韩梅梅","2016/03/06","2016/03/08",
//                    2,"CNY","BA3276767",BigDecimal.valueOf(460),"2017-03-09 14:50:20",1);
//            list.add(checkOutOrderDTO1);
        }
        PaginationSupportDTO<CheckOutOrderDTO> paginationSupportDTO=new PaginationSupportDTO<>();
        paginationSupportDTO.setTotalCount(30);
        paginationSupportDTO.setTotalPage(2);
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setPageSize(15);
        responseDTO.setModel(paginationSupportDTO);
        paginationSupportDTO.setItemList(list);
        return responseDTO;
    }
}
