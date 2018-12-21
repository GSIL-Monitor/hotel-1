package com.fangcang.report.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.ExcelHelper;
import com.fangcang.common.util.ReportTemplateConfig;
import com.fangcang.report.request.ProfitReportQueryDTO;
import com.fangcang.report.response.ProfitReportResponseDTO;
import com.fangcang.report.response.ProfitSummaryDTO;
import com.fangcang.report.service.ProfitReportService;
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
public class ProfitReportController extends BaseController {

    @Autowired
    private ProfitReportService profitReportService;

    @Autowired
    private ReportTemplateConfig reportTemplateConfig;

    @RequestMapping(value = "/profitReport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<ProfitReportResponseDTO>> profitReport(@RequestBody @Valid ProfitReportQueryDTO requestDTO) {
        ResponseDTO<PaginationSupportDTO<ProfitReportResponseDTO>> responseDTO = new ResponseDTO<>();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            PaginationSupportDTO<ProfitReportResponseDTO> page = profitReportService.profitReport(requestDTO);
            responseDTO.setResult(Constant.YES);
            responseDTO.setModel(page);
        } catch (Exception e) {
            log.error("profitReport error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/profitReportSum", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<ProfitSummaryDTO> profitReportSum(@RequestBody @Valid ProfitReportQueryDTO requestDTO) {
        ResponseDTO<ProfitSummaryDTO> responseDTO = new ResponseDTO<>();
        try {
            requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
            ProfitSummaryDTO profitSummaryDTO = profitReportService.profitReportSum(requestDTO);
            responseDTO.setResult(Constant.YES);
            responseDTO.setModel(profitSummaryDTO);
        } catch (Exception e) {
            log.error("profitReportSum error:", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
            responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/profitReportExport")
    public void profitReportExport(ProfitReportQueryDTO requestDTO, HttpServletResponse response) throws Exception {
        log.info("profitReportExport param:" + requestDTO);
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(getCacheUser().getMerchantCode());
        PaginationSupportDTO<ProfitReportResponseDTO> page = profitReportService.profitReport(requestDTO);
        if (page == null && CollectionUtils.isEmpty(page.getItemList())) {
            return;
        }
        ProfitSummaryDTO profitSummaryDTO = profitReportService.profitReportSum(requestDTO);

        Map<String, Object> data = new HashMap<>(2);
        data.put("itemList", page.getItemList());
        data.put("summary", profitSummaryDTO);
        data.put("groupType", requestDTO.getGroupType());

        OutputStream output = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ByteArrayInputStream byteArrayInputStream = ExcelHelper.exportFromRemote(data, reportTemplateConfig.getProfitReportUrl());
            //告诉浏览器用什么软件可以打开此文件
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("利润报表.xls", "UTF-8"));

            output = response.getOutputStream();
            bis = new BufferedInputStream(byteArrayInputStream);
            bos = new BufferedOutputStream(output);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("profitReportExport has error", e);
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
