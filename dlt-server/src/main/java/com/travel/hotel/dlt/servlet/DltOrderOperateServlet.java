package com.travel.hotel.dlt.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.travel.common.utils.SpringContextUtil;
import com.travel.hotel.dlt.service.DltHotelOrderOperateService;
import com.travel.channel.dto.request.HotelOrderOperateRequestDTO;
import com.travel.channel.dto.response.HotelOrderOperateResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *   2018/4/26.
 */
public class DltOrderOperateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DltOrderOperateServlet.class);
    private static final long serialVersionUID = -7937228873465336258L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HotelOrderOperateResponseDTO hotelOrderOperateResponseDTO;
        try {
            BufferedReader br = request.getReader();
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            HotelOrderOperateRequestDTO requestDTO = JSON.parseObject(sb.toString(), new TypeReference<HotelOrderOperateRequestDTO>(){});
            LOG.info("operateOrder,param:"+sb.toString());
            DltHotelOrderOperateService dltHotelOrderOperateService =
                    (DltHotelOrderOperateService) SpringContextUtil.getBean("dltHotelOrderOperateService");
            hotelOrderOperateResponseDTO = dltHotelOrderOperateService.operateOrder(requestDTO);
        } catch (Exception e) {
            LOG.error("操作代理通订单出错, 错误信息：" + e.getMessage(), e);
            hotelOrderOperateResponseDTO = new HotelOrderOperateResponseDTO();
            hotelOrderOperateResponseDTO.setIsSuccess(0);
            hotelOrderOperateResponseDTO.setFailureReason("操作代理通订单出错, 错误信息：" + e.getMessage());
        }

        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(hotelOrderOperateResponseDTO));
        pw.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.write("get method is not enable, please invoke post method.");
        pw.flush();
    }

}
