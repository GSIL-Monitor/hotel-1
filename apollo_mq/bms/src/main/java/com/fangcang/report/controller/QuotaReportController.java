package com.fangcang.report.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.ExcelHelper;
import com.fangcang.common.util.ReportTemplateConfig;
import com.fangcang.merchant.dto.UserDTO;
import com.fangcang.report.dto.HotelQuotaDTO;
import com.fangcang.report.request.QuotaReportQueryDTO;
import com.fangcang.report.service.QuotaReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/7/9.
 */
@Controller
@RequestMapping("/report")
@Slf4j
public class QuotaReportController extends BaseController {

    @Autowired
    private QuotaReportService quotaReportService;

    @Autowired
    private ReportTemplateConfig reportTemplateConfig;

    /**
     * 配额报表
     * @param quotaReportQueryDTO
     * @return
     */
    @RequestMapping(value = "/quotaReportDetail" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO<PaginationSupportDTO<HotelQuotaDTO>> quotaReportDetail(@RequestBody @Valid QuotaReportQueryDTO quotaReportQueryDTO){
        UserDTO cacheUser = super.getCacheUser();
        quotaReportQueryDTO.setMerchantCode(cacheUser.getMerchantCode());
        return quotaReportService.quotaReportDetail(quotaReportQueryDTO);
    }

    /**
     * 配额报表导出
     * @return
     */
    @RequestMapping(value = "/exportQuotaReportDetail")
    public void exportQuotaReportDetail(QuotaReportQueryDTO quotaReportQueryDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        UserDTO cacheUser = super.getCacheUser();
        quotaReportQueryDTO.setMerchantCode(cacheUser.getMerchantCode());
        ResponseDTO<PaginationSupportDTO<HotelQuotaDTO>> responseDTO = quotaReportService.quotaReportDetail(quotaReportQueryDTO);
        if(null != responseDTO && ResultCodeEnum.SUCCESS.code == responseDTO.getResult()){
            PaginationSupportDTO<HotelQuotaDTO> paginationSupportDTO = responseDTO.getModel();
            if(!CollectionUtils.isEmpty(paginationSupportDTO.getItemList())){
                HotelQuotaDTO hotelQuotaDTO = paginationSupportDTO.getItemList().get(0);
                if(null == hotelQuotaDTO){
                    hotelQuotaDTO = new HotelQuotaDTO();
                }
                if(null == hotelQuotaDTO.getHotelQuotaDayList()){
                    hotelQuotaDTO.setHotelQuotaDayList(Collections.EMPTY_LIST);
                }
                if(null == hotelQuotaDTO.getRoomQuotaDTOList()){
                    hotelQuotaDTO.setRoomQuotaDTOList(Collections.EMPTY_LIST);
                }
                List<Date> dateList=DateUtil.getDateList(DateUtil.stringToDate(quotaReportQueryDTO.getStartDate(), DateUtil.defaultFormat), DateUtil.stringToDate(quotaReportQueryDTO.getEndDate(), DateUtil.defaultFormat));
                Map data = new HashMap();
                data.put("hotelQuotaDTO", hotelQuotaDTO);
                data.put("dateList", dateList);
                data.put("supplyName", quotaReportQueryDTO.getSupplyName());
                data.put("hotelName",hotelQuotaDTO.getHotelName());

                OutputStream output = null;
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    ByteArrayInputStream byteArrayInputStream = ExcelHelper.exportFromRemote(data, reportTemplateConfig.getQuotaReportUrl());
                    //告诉浏览器用什么软件可以打开此文件
                    httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
                    // 下载文件的默认名称
                    httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("配额日历报表.xls","UTF-8"));

                    output = httpServletResponse.getOutputStream();
                    bis = new BufferedInputStream(byteArrayInputStream);
                    bos = new BufferedOutputStream(output);
                    byte[] buff = new byte[2048];
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }
                } catch (Exception e) {
                    log.error("exportQuotaReportDetail has error",e);
                }finally {
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
    }
}
