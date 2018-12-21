package com.fangcang.report.controller;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.report.request.QueryOrderDetailReportDTO;
import com.fangcang.report.response.OrderDetailReportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(("/test/report"))
public class TestOrderDetailReportController extends BaseController {

    @RequestMapping(value = "/orderdetail" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseDTO orderdetail(@RequestBody QueryOrderDetailReportDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<OrderDetailReportDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
//            OrderDetailReportDTO reportDTO=new OrderDetailReportDTO("H011416102717265"+i,3232L,"深圳京基100大酒店",
//                    54534L,"豪华双人房",2323L,"无早", DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"),
//                    "2016-10-01","2016-10-04",i%3==0?1:i%3,"韩梅梅",i%4==0?1:i%4,i%5==0?1:i%5,"韩梅梅","李雷","王大明","B2B","215343443232",
//                    "A423233323","新景界布吉万科红部","1",1,
//                    BigDecimal.valueOf(5121),BigDecimal.valueOf(1121),BigDecimal.valueOf(4000),BigDecimal.valueOf(5121),BigDecimal.valueOf(200),
//                    "S3232323234",i%3==0?1:i%3,i%5==0?1:i%5,"23232323","S423223","畅游假期",1,
//                    BigDecimal.valueOf(4000),BigDecimal.valueOf(3232),BigDecimal.valueOf(768),BigDecimal.valueOf(4000));
//            list.add(reportDTO);
//            i++;
//            OrderDetailReportDTO reportDTO1=new OrderDetailReportDTO("H011416102717265"+i,3232L,"深圳喜码拉雅大酒店",
//                    54534L,"普通双人房",2323L,"提前几天预订提前几天预订", DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"),
//                    "2016-10-05","2016-10-07",i%3==0?1:i%3,"李小红",i%4==0?1:i%4,i%5==0?1:i%5,"李小红","李雷","李雷","B2B","215343443232",
//                    "A423233323","东莞青旅（港澳部港币）","1",1,
//                    BigDecimal.valueOf(870),BigDecimal.valueOf(600),BigDecimal.valueOf(270),BigDecimal.valueOf(870),BigDecimal.valueOf(100),
//                    "S3232323234"+i,i%3==0?1:i%3,i%5==0?1:i%5,"23232323","S423223","珠海品位旅游文化传播有限公司",1,
//                    BigDecimal.valueOf(770),BigDecimal.valueOf(400),BigDecimal.valueOf(370),BigDecimal.valueOf(770));
//            list.add(reportDTO1);
        }
        PaginationSupportDTO<OrderDetailReportDTO> paginationSupportDTO=new PaginationSupportDTO<>();
        paginationSupportDTO.setTotalCount(30);
        paginationSupportDTO.setTotalPage(2);
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setPageSize(15);
        responseDTO.setModel(paginationSupportDTO);
        paginationSupportDTO.setItemList(list);
        return responseDTO;
    }

    @RequestMapping(value = "/orderdetailexport")
    public void orderdetailexport(QueryOrderDetailReportDTO requestDTO,HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<OrderDetailReportDTO> list=new ArrayList<>();
        for (int i=1;i<16;i++){
//            OrderDetailReportDTO reportDTO=new OrderDetailReportDTO("H011416102717265"+i,3232L,"深圳京基100大酒店",
//                    54534L,"豪华双人房",2323L,"无早", DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"),
//                    "2016-10-01","2016-10-04",i%3==0?1:i%3,"韩梅梅",i%4==0?1:i%4,i%5==0?1:i%5,"韩梅梅","李雷","王大明","B2B","215343443232",
//                    "A423233323","新景界布吉万科红部","1",1,
//                    BigDecimal.valueOf(5121),BigDecimal.valueOf(1121),BigDecimal.valueOf(4000),BigDecimal.valueOf(5121),BigDecimal.valueOf(200),
//                    "S3232323234",i%3==0?1:i%3,i%5==0?1:i%5,"23232323","S423223","畅游假期",1,
//                    BigDecimal.valueOf(4000),BigDecimal.valueOf(3232),BigDecimal.valueOf(768),BigDecimal.valueOf(4000));
//            list.add(reportDTO);
//            i++;
//            OrderDetailReportDTO reportDTO1=new OrderDetailReportDTO("H011416102717265"+i,3232L,"深圳喜码拉雅大酒店",
//                    54534L,"普通双人房",2323L,"提前几天预订提前几天预订", DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"),
//                    "2016-10-05","2016-10-07",i%3==0?1:i%3,"李小红",i%4==0?1:i%4,i%5==0?1:i%5,"李小红","李雷","李雷","B2B","215343443232",
//                    "A423233323","东莞青旅（港澳部港币）","1",1,
//                    BigDecimal.valueOf(870),BigDecimal.valueOf(600),BigDecimal.valueOf(270),BigDecimal.valueOf(870),BigDecimal.valueOf(100),
//                    "S3232323234"+i,i%3==0?1:i%3,i%5==0?1:i%5,"23232323","S423223","珠海品位旅游文化传播有限公司",1,
//                    BigDecimal.valueOf(770),BigDecimal.valueOf(400),BigDecimal.valueOf(370),BigDecimal.valueOf(770));
//            list.add(reportDTO1);
        }

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("订单明细报表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        ExcelUtils.exportExcel(response.getOutputStream(),OrderDetailReportDTO.class, list);
    }
}
