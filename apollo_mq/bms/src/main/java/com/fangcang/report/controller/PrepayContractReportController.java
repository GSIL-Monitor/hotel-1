package com.fangcang.report.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.ExcelHelper;
import com.fangcang.common.util.ReportTemplateConfig;
import com.fangcang.report.request.PrepayContractReportQueryDTO;
import com.fangcang.report.response.PrepayContractReportResponseDTO;
import com.fangcang.report.service.PrepayContractReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(("/report"))
public class PrepayContractReportController extends BaseController {

    @Autowired
    private PrepayContractReportService prepayContractReportService;

    @Autowired
    private ReportTemplateConfig reportTemplateConfig;

    @RequestMapping(value = "/prepayContractReport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<PrepayContractReportResponseDTO>> prepayContractReport(@RequestBody @Valid PrepayContractReportQueryDTO requestDTO) {
        ResponseDTO<PaginationSupportDTO<PrepayContractReportResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            boolean noCanQuery = (requestDTO.getHotelId() == null || requestDTO.getSupplyId() == null) && StringUtils.isEmpty(requestDTO.getDate());
            if (noCanQuery) {
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("酒店和供应商必选，或者日期必选");
                return responseDTO;
            }
            requestDTO.setMerchantId(getCacheUser().getMerchantId());
            PaginationSupportDTO<PrepayContractReportResponseDTO> page = prepayContractReportService.prepayContractReport(requestDTO);
            responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("prepayContractReport error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/prepayContractReportExport")
    public void prepayContractReportExport(PrepayContractReportQueryDTO requestDTO, HttpServletResponse response) throws Exception {
        log.info("prepayContractReportExport param:" + requestDTO);
        boolean noCanQuery = (requestDTO.getHotelId() == null || requestDTO.getSupplyId() == null) && StringUtils.isEmpty(requestDTO.getDate());
        if (noCanQuery) {
            return;
        }
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantId(getCacheUser().getMerchantId());
        PaginationSupportDTO<PrepayContractReportResponseDTO> page = prepayContractReportService.prepayContractReport(requestDTO);
        if (page == null && CollectionUtils.isEmpty(page.getItemList())) {
            return;
        }

        Map<String, Object> data = new HashMap<>(2);
        data.put("itemList", page.getItemList());

        OutputStream output = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ByteArrayInputStream byteArrayInputStream = ExcelHelper.exportFromRemote(data, reportTemplateConfig.getPrepayContractReportUrl());
            //告诉浏览器用什么软件可以打开此文件
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("包房报表.xls", "UTF-8"));

            output = response.getOutputStream();
            bis = new BufferedInputStream(byteArrayInputStream);
            bos = new BufferedOutputStream(output);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("prepayContractReportExport has error", e);
        } finally {
            if (null != bos) {
                bos.close();
            }
            if (null != bis) {
                bis.close();
            }
            if (null != output) {
                output.close();
            }
        }
    }
}
