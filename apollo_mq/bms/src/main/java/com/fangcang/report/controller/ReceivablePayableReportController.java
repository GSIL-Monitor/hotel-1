package com.fangcang.report.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.ExcelHelper;
import com.fangcang.common.util.ReportTemplateConfig;
import com.fangcang.report.request.PayableReportQueryDTO;
import com.fangcang.report.request.ReceivableReportQueryDTO;
import com.fangcang.report.response.PayableReportResponseDTO;
import com.fangcang.report.response.PayableSummaryDTO;
import com.fangcang.report.response.ReceivableReportResponseDTO;
import com.fangcang.report.response.ReceivableSummaryDTO;
import com.fangcang.report.service.ReceivablePayableReportService;
import lombok.extern.slf4j.Slf4j;
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

/**
 * @author : zhanwang
 * @date : 2018/7/11
 */
@RestController
@Slf4j
@RequestMapping(("/report"))
public class ReceivablePayableReportController extends BaseController {

    @Autowired
    private ReceivablePayableReportService receivablePayableReportService;

    @Autowired
    private ReportTemplateConfig reportTemplateConfig;

    @RequestMapping(value = "/receivableReport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<ReceivableReportResponseDTO>> receivableReport(@RequestBody @Valid ReceivableReportQueryDTO requestDTO) {
        ResponseDTO<PaginationSupportDTO<ReceivableReportResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            PaginationSupportDTO<ReceivableReportResponseDTO> page = receivablePayableReportService.receivableReport(requestDTO);
            responseDTO.setResult(Constant.YES);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("prepayContractReport error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/receivableReportSum", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<ReceivableSummaryDTO> receivableReportSum(@RequestBody @Valid ReceivableReportQueryDTO requestDTO) {
        ResponseDTO<ReceivableSummaryDTO> responseDTO = new ResponseDTO<>();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            ReceivableSummaryDTO receivableSummaryDTO = receivablePayableReportService.receivableReportSum(requestDTO);
            responseDTO.setResult(Constant.YES);
            responseDTO.setModel(receivableSummaryDTO);
        } catch (Exception e) {
            log.error("receivableReportSum error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/receivableReportExport")
    public void receivableReportExport(ReceivableReportQueryDTO requestDTO, HttpServletResponse response) throws Exception {
        log.info("receivableReportExport param:" + requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
        PaginationSupportDTO<ReceivableReportResponseDTO> page = receivablePayableReportService.receivableReport(requestDTO);
        if (page == null && CollectionUtils.isEmpty(page.getItemList())) {
            return;
        }
        ReceivableSummaryDTO receivableSummaryDTO = receivablePayableReportService.receivableReportSum(requestDTO);

        Map<String, Object> data = new HashMap<>(2);
        data.put("itemList", page.getItemList());
        data.put("summary", receivableSummaryDTO);

        OutputStream output = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ByteArrayInputStream byteArrayInputStream = ExcelHelper.exportFromRemote(data, reportTemplateConfig.getReceivableReportUrl());
            //告诉浏览器用什么软件可以打开此文件
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("应收报表.xls", "UTF-8"));

            output = response.getOutputStream();
            bis = new BufferedInputStream(byteArrayInputStream);
            bos = new BufferedOutputStream(output);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("receivableReportExport has error", e);
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


    @RequestMapping(value = "/payableReport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<PayableReportResponseDTO>> payableReport(@RequestBody @Valid PayableReportQueryDTO requestDTO) {
        ResponseDTO<PaginationSupportDTO<PayableReportResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            PaginationSupportDTO<PayableReportResponseDTO> page = receivablePayableReportService.payableReport(requestDTO);
            responseDTO.setResult(Constant.YES);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("payableReport error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/payableReportSum", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PayableSummaryDTO> payableReportSum(@RequestBody @Valid PayableReportQueryDTO requestDTO) {
        ResponseDTO<PayableSummaryDTO> responseDTO = new ResponseDTO<>();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            PayableSummaryDTO payableSummaryDTO = receivablePayableReportService.payableReportSum(requestDTO);
            responseDTO.setResult(Constant.YES);
            responseDTO.setModel(payableSummaryDTO);
        } catch (Exception e) {
            log.error("payableReportSum error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/payableReportExport")
    public void payableReportExport(PayableReportQueryDTO requestDTO, HttpServletResponse response) throws Exception {
        log.info("payableReportExport param:" + requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
        PaginationSupportDTO<PayableReportResponseDTO> page = receivablePayableReportService.payableReport(requestDTO);
        if (page == null && CollectionUtils.isEmpty(page.getItemList())) {
            return;
        }
        PayableSummaryDTO payableSummaryDTO = receivablePayableReportService.payableReportSum(requestDTO);

        Map<String, Object> data = new HashMap<>(2);
        data.put("itemList", page.getItemList());
        data.put("summary", payableSummaryDTO);

        OutputStream output = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ByteArrayInputStream byteArrayInputStream = ExcelHelper.exportFromRemote(data, reportTemplateConfig.getPayableReportUrl());
            //告诉浏览器用什么软件可以打开此文件
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("应付报表.xls", "UTF-8"));

            output = response.getOutputStream();
            bis = new BufferedInputStream(byteArrayInputStream);
            bos = new BufferedOutputStream(output);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("payableReportExport has error", e);
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
