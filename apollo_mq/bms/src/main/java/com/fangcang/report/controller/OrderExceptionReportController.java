package com.fangcang.report.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.report.request.QueryOrderExceptionDTO;
import com.fangcang.report.response.OrderExceptionDTO;
import com.fangcang.report.service.OrderExceptionReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@Slf4j
@RequestMapping(("/report"))
public class OrderExceptionReportController extends BaseController {

    @Autowired
    private OrderExceptionReportService orderExceptionReportService;

    @RequestMapping(value = "/queryOrderException" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryOrderException(@RequestBody QueryOrderExceptionDTO requestDTO) {
        log.info("queryOrderException param:{}", JSON.toJSONString(requestDTO));
        ResponseDTO responseDTO = null;
        try{
            requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=orderExceptionReportService.queryOrderException(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("orderExceptionReportService.queryOrderException 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/exportOrderException")
    public void exportOrderException(QueryOrderExceptionDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportOrderException param:{}",JSON.toJSONString(requestDTO));
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        PaginationSupportDTO<OrderExceptionDTO> paginationSupportDTO=orderExceptionReportService.queryOrderException(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("异常订单表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),OrderExceptionDTO.class,paginationSupportDTO.getItemList());
    }
}
