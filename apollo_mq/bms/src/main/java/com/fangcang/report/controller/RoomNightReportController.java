package com.fangcang.report.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.report.request.QueryRoomNightReportDTO;
import com.fangcang.report.response.RoomNightDailyReportDTO;
import com.fangcang.report.response.RoomNightReportDTO;
import com.fangcang.report.response.RoomNightSummaryDTO;
import com.fangcang.report.service.RoomNightReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(("/report"))
public class RoomNightReportController extends BaseController {

    @Autowired
    private RoomNightReportService roomNightReportService;

    @RequestMapping(value = "/roomnight" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO roomnight(@RequestBody QueryRoomNightReportDTO requestDTO) {
        log.info("roomnight param:"+requestDTO);
        ResponseDTO responseDTO = null;
        if (!StringUtil.isValidString(requestDTO.getBeginDate())){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null, "开始日期不能为空");
        }
        if (!StringUtil.isValidString(requestDTO.getEndDate())){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null, "结束日期不能为空");
        }
        try{
            requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
            PaginationSupportDTO paginationSupportDTO=roomNightReportService.queryRoomNightReport(requestDTO);
            responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
            responseDTO.setModel(paginationSupportDTO);
        }catch (Exception e){
            log.error("roomNightReportService.queryRoomNightReport 异常",e);
            responseDTO=new ResponseDTO(ResultCodeEnum.FAILURE.code,null, ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
        }
        return responseDTO;
    }

    @RequestMapping(value = "/roomnightexport")
    public void roomnightexport(QueryRoomNightReportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("roomnightexport param:{}" , JSON.toJSONString(requestDTO));
        if (!StringUtil.isValidString(requestDTO.getBeginDate()) || !StringUtil.isValidString(requestDTO.getEndDate())) {
            return;
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
        if (StringUtil.isValidString(requestDTO.getChannelCode()) && requestDTO.getChannelCode().contains(",")){
            requestDTO.setChannelCode(requestDTO.getChannelCode().replace(",",""));
        }

        log.info("roomnightexport param,after set : {}",JSON.toJSONString(requestDTO));

        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        RoomNightSummaryDTO paginationSupportDTO = roomNightReportService.queryRoomNightReport(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("间夜统计报表", "UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream out=response.getOutputStream();

        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("间夜统计报表");
        //设置标题
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("酒店名称");
        Date beginDate = DateUtil.stringToDate(requestDTO.getBeginDate());
        Date endDate = DateUtil.stringToDate(requestDTO.getEndDate());
        Long day = DateUtil.getDay(beginDate, endDate);
        for (int i = 0; i < day; i++) {
            Date curDate = DateUtil.getDate(beginDate, i, 0);
            cell = row.createCell(1 + i);
            cell.setCellValue(DateUtil.dateToString(curDate, "yyyy/MM/dd"));
        }
        cell = row.createCell(1 + day.intValue());
        cell.setCellValue("总间夜");
        cell = row.createCell(2 + day.intValue());
        cell.setCellValue("散房间夜");
        cell = row.createCell(3 + day.intValue());
        cell.setCellValue("团房间夜");

        int rownum = 1;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (RoomNightReportDTO roomNightReportDTO : paginationSupportDTO.getItemList()) {
            row = sheet.createRow(rownum);

            cell = row.createCell(0);
            cell.setCellValue(roomNightReportDTO.getHotelName());
            int i = 0;
            for (RoomNightDailyReportDTO dailyReportDTO : roomNightReportDTO.getDailyList()) {
                cell = row.createCell(1 + i);
                cell.setCellValue(dailyReportDTO.getRoomNight());
                i++;
            }
            cell = row.createCell(1 + day.intValue());
            cell.setCellValue(roomNightReportDTO.getRoomNight());
            cell = row.createCell(2 + day.intValue());
            cell.setCellValue(roomNightReportDTO.getLooseRoomNight());
            cell = row.createCell(3 + day.intValue());
            cell.setCellValue(roomNightReportDTO.getGrouponRoomNight());

            rownum++;
        }

        workbook.write(out);
        out.flush();
        out.close();
    }

    /*@RequestMapping(value = "/roomnightexport1")
    public void roomnightexport1(QueryRoomNightReportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("roomnightexport param:"+requestDTO);
        if (!StringUtil.isValidString(requestDTO.getBeginDate()) || !StringUtil.isValidString(requestDTO.getEndDate())){
            return;
        }
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        RoomNightSummaryDTO paginationSupportDTO=roomNightReportService.queryRoomNightReport(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("订单明细报表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
        ExcelExportEntity colEntity = new ExcelExportEntity("酒店名称", "hotelName");
        colList.add(colEntity);
        Date beginDate=DateUtil.stringToDate(requestDTO.getBeginDate());
        Date endDate=DateUtil.stringToDate(requestDTO.getEndDate());
        Long day=DateUtil.getDay(beginDate,endDate);
        for (int i=0;i<day;i++){
            Date curDate=DateUtil.getDate(beginDate,i,0);
            colEntity = new ExcelExportEntity(DateUtil.dateToString(curDate,"yyyy/MM/dd"), DateUtil.dateToString(curDate,"yyyy-MM-dd"));
            colList.add(colEntity);
        }
        colEntity = new ExcelExportEntity("总间夜", "roomNight");
        colList.add(colEntity);
        colEntity = new ExcelExportEntity("散房间夜", "looseRoomNight");
        colList.add(colEntity);
        colEntity = new ExcelExportEntity("团房间夜", "grouponRoomNight");
        colList.add(colEntity);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (RoomNightReportDTO roomNightReportDTO:paginationSupportDTO.getItemList()) {
            Map<String, Object> valMap = new HashMap<String, Object>();
            valMap.put("hotelName", roomNightReportDTO.getHotelName());
            valMap.put("roomNight", roomNightReportDTO.getRoomNight());
            valMap.put("looseRoomNight", roomNightReportDTO.getLooseRoomNight());
            valMap.put("grouponRoomNight", roomNightReportDTO.getGrouponRoomNight());
            for (RoomNightDailyReportDTO dailyReportDTO:roomNightReportDTO.getDailyList()){
                valMap.put(dailyReportDTO.getSaleDate(), dailyReportDTO.getRoomNight());
            }
            list.add(valMap);
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), colList, list);
        workbook.write(response.getOutputStream());
    }*/

    /*@RequestMapping(value = "/roomnightexport")
    public View roomnightexport(QueryRoomNightReportDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("roomnightexport param:"+requestDTO);
        if (!StringUtil.isValidString(requestDTO.getBeginDate()) || !StringUtil.isValidString(requestDTO.getEndDate())) {
            return new AbstractXlsView() {
                @Override
                protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
                    workbook.createSheet("订单明细报表");
                }
            };
        }
        requestDTO.setCurrentPage(1);
        requestDTO.setPageSize(999999999);
        requestDTO.setMerchantCode(super.getCacheUser().getMerchantCode());
        RoomNightSummaryDTO paginationSupportDTO=roomNightReportService.queryRoomNightReport(requestDTO);

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("订单明细报表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");

        return new AbstractXlsView() {
            @Override
            protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
                Sheet sheet=workbook.createSheet("订单明细报表");
                //设置标题
                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("酒店名称");
                Date beginDate=DateUtil.stringToDate(requestDTO.getBeginDate());
                Date endDate=DateUtil.stringToDate(requestDTO.getEndDate());
                Long day=DateUtil.getDay(beginDate,endDate);
                for (int i=0;i<day;i++){
                    Date curDate=DateUtil.getDate(beginDate,i,0);
                    cell = row.createCell(1+i);
                    cell.setCellValue(DateUtil.dateToString(curDate,"yyyy/MM/dd"));
                }
                cell = row.createCell(1+day.intValue());
                cell.setCellValue("总间夜");
                cell = row.createCell(2+day.intValue());
                cell.setCellValue("散房间夜");
                cell = row.createCell(3+day.intValue());
                cell.setCellValue("团房间夜");

                int rownum =1;
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (RoomNightReportDTO roomNightReportDTO:paginationSupportDTO.getItemList()) {

                    row = sheet.createRow(rownum);

                    cell  = row.createCell(0);
                    cell.setCellValue(roomNightReportDTO.getHotelName());
                    int i=0;
                    for (RoomNightDailyReportDTO dailyReportDTO:roomNightReportDTO.getDailyList()){
                        cell  = row.createCell(1+i);
                        cell.setCellValue(dailyReportDTO.getRoomNight());
                        i++;
                    }
                    cell = row.createCell(1+day.intValue());
                    cell.setCellValue(roomNightReportDTO.getRoomNight());
                    cell = row.createCell(2+day.intValue());
                    cell.setCellValue(roomNightReportDTO.getLooseRoomNight());
                    cell = row.createCell(3+day.intValue());
                    cell.setCellValue(roomNightReportDTO.getGrouponRoomNight());

                    rownum++;
                }
            }
        };
    }*/
}
