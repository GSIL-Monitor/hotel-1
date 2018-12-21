package com.fangcang.report.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.report.request.QueryOrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailSummaryDTO;
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
@RequestMapping(("/report"))
public class OrderDetailReportController extends BaseController {

    @Autowired
    private OrderDetailReportService orderDetailReportService;

    @RequestMapping(value = "/orderdetail" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO orderdetail(@RequestBody QueryOrderDetailReportDTO requestDTO) {
        log.info("orderdetail param:{}",JSON.toJSONString(requestDTO));
        ResponseDTO responseDTO = null;
        try{
            requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=orderDetailReportService.queryOrderDetail(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("orderDetailReportService.queryOrderDetail 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
    @RequestMapping(value = "/orderdetailexport")
    public void orderdetailexport(QueryOrderDetailReportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("orderdetailexport param:{}",JSON.toJSONString(requestDTO));

        if (!StringUtil.isValidString(requestDTO.getAgentCode())){
            requestDTO.setAgentCode(null);
        }
        if (!StringUtil.isValidString(requestDTO.getAgentName())){
            requestDTO.setAgentName(null);
        }

        if (!StringUtil.isValidString(requestDTO.getSupplyCode())){
            requestDTO.setSupplyCode(null);
        }
        if (!StringUtil.isValidString(requestDTO.getSupplyName())){
            requestDTO.setSupplyName(null);
        }

        if (!StringUtil.isValidString(requestDTO.getHotelName())){
            requestDTO.setHotelName(null);
        }
        if (StringUtil.isValidString(requestDTO.getBelongName()) && requestDTO.getBelongName().startsWith(",")){
            requestDTO.setBeginStayDate(requestDTO.getBelongName().substring(1));
            requestDTO.setBelongName(null);
        }
        if (StringUtil.isValidString(requestDTO.getMerchantBm()) && requestDTO.getMerchantBm().startsWith(",")){
            requestDTO.setEndStayDate(requestDTO.getMerchantBm().substring(1));
            requestDTO.setMerchantBm(null);
        }
        log.info("orderdetailexport param after set null:{}",JSON.toJSONString(requestDTO));

        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        PaginationSupportDTO<OrderDetailReportDTO> paginationSupportDTO=orderDetailReportService.queryOrderDetail(requestDTO);
        log.info("orderdetailexport result,totalCount={},totalPage={}", JSON.toJSONString(paginationSupportDTO.getTotalCount()),paginationSupportDTO.getTotalPage());

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("订单明细报表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        ExcelUtils.exportExcel(response.getOutputStream(),OrderDetailReportDTO.class,paginationSupportDTO.getItemList());
    }

    @RequestMapping(value = "/orderdetailsummary" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO orderdetailsummary(@RequestBody QueryOrderDetailReportDTO requestDTO) {
        log.info("orderdetailsummary param:"+requestDTO);
        ResponseDTO responseDTO = null;
        try{
//            requestDTO.setBeginStayDate("2019-09-29");
//            requestDTO.setEndStayDate("2019-09-30");
            requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
            OrderDetailSummaryDTO summaryDTO=orderDetailReportService.queryOrderDetailSummary(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(summaryDTO);
        }catch (Exception e){
            log.error("orderDetailReportService.queryOrderDetailSummary 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }
}
