package com.fangcang.finance.report.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.finance.report.request.QueryCheckReportDTO;
import com.fangcang.finance.report.response.OrderCheckDTO;
import com.fangcang.finance.report.response.SupplyOrderCheckDTO;
import com.fangcang.finance.report.service.FinanceReportService;
import com.fangcang.report.service.OrderDetailReportService;
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
@RequestMapping("/finance/report")
public class FinanceReportController extends BaseController{

    @Autowired
    private FinanceReportService financeReportService;

    @Autowired
    private OrderDetailReportService orderDetailReportService;

    /**
     * 分销商对账结算明细
     */
    @RequestMapping(value = "/queryAgentCheckReport" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO queryAgentCheckReport(@RequestBody QueryCheckReportDTO request) {
        log.info("queryAgentCheckReport param:{}", JSON.toJSONString(request));
        ResponseDTO responseDTO = null;
        try{
            request.setMerchantCode(super.getCacheUser().getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=financeReportService.queryAgentCheckReport(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("financeReportService.queryAgentCheckReport 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出分销商对账结算明细
     */
    @RequestMapping(value = "/exportAgentCheckReport")
    public void exportAgentCheckReport(QueryCheckReportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportAgentCheckReport param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        PaginationSupportDTO<OrderCheckDTO> paginationSupportDTO=financeReportService.queryAgentCheckReport(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("分销商对账结算明细","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),OrderCheckDTO.class,paginationSupportDTO.getItemList());
    }

    /**
     * 供应商对账结算明细
     */
    @RequestMapping(value = "/querySupplyCheckReport" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO querySupplyCheckReport(@RequestBody QueryCheckReportDTO request) {
        log.info("querySupplyCheckReport param:{}", JSON.toJSONString(request));
        ResponseDTO responseDTO = null;
        try{
            request.setMerchantCode(super.getCacheUser().getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=financeReportService.querySupplyCheckReport(request);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("financeReportService.querySupplyCheckReport 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    /**
     * 导出供应商对账结算明细
     */
    @RequestMapping(value = "/exportSupplyCheckReport")
    public void exportSupplyCheckReport(QueryCheckReportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("exportSupplyCheckReport param:"+requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        PaginationSupportDTO<SupplyOrderCheckDTO> paginationSupportDTO=financeReportService.querySupplyCheckReport(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("供应商对账结算明细","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),SupplyOrderCheckDTO.class,paginationSupportDTO.getItemList());
    }
}
