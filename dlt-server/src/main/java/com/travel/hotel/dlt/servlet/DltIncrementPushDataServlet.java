package com.travel.hotel.dlt.servlet;

import com.alibaba.fastjson.JSON;
import com.travel.channel.dao.DltMapRoomPOMapper;
import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.channel.entity.po.DltMapRoomPOExample;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.dlt.enums.ResultCodeEnum;
import com.travel.hotel.dlt.request.dto.IncrementPushDataRequest;
import com.travel.hotel.dlt.response.ResponseDTO;
import com.travel.hotel.dlt.task.BatchPushRoomDataTask;
import com.travel.hotel.dlt.utils.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 *  m_ 2018/6/18.
 */
public class DltIncrementPushDataServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DltIncrementPushDataServlet.class);
    private static final long serialVersionUID = -2554612691837243322L;
    public static volatile Boolean needSyncRoomDataToDlt = true;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.FAILURE.code);
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader br = request.getReader();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            List<IncrementPushDataRequest> requestList = JSON.parseArray(sb.toString(), IncrementPushDataRequest.class);

            if (!needSyncRoomDataToDlt) {
                LOG.info("被设置为不需要同步报价到代理通.{}",sb.toString());
                return;
            }

            LOG.info("incrementPushRoomData execute... " + sb.toString());

            if(CollectionUtils.isNotEmpty(requestList)) {
                for(IncrementPushDataRequest requestDTO : requestList) {
                    if(!StringUtil.isValidString(requestDTO.getMerchantCode()) || null == requestDTO.getMHotelId()) {
                        responseDTO.setFailCode("INVALID_INPUTPARAM");
                        responseDTO.setFailReason("参数不合法");
                    }else {
                        // 查询房型映射表，查询所有已经映射的房型
                        DltMapRoomPOExample example = new DltMapRoomPOExample();
                        DltMapRoomPOExample.Criteria criteria = example.createCriteria();
                        criteria.andZhHotelIdEqualTo(requestDTO.getMHotelId());
                        criteria.andMerchantCodeEqualTo(requestDTO.getMerchantCode());
                        if(null!=requestDTO.getMRoomTypeId()) {
                            criteria.andZhRoomIdEqualTo(requestDTO.getMRoomTypeId());
                        }
                        if(null!=requestDTO.getMRatePlanId()) {
                            criteria.andZhRpIdEqualTo(requestDTO.getMRatePlanId());
                        }

                        criteria.andDltRoomIdIsNotNull();
                        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
                        DltMapRoomPOMapper dltMapRoomPOMapper = (DltMapRoomPOMapper) context.getBean("dltMapRoomPOMapper");
                        List<DltMapRoomPO> dltMapRoomPOList = dltMapRoomPOMapper.selectByExample(example);
                        if (CollectionUtils.isEmpty(dltMapRoomPOList)) {
                            LOG.error("未查询到任何有映射关系的房型，增量推送结束.{}",sb.toString());
                            responseDTO.setFailCode("NO_MAPPING_DATA");
                            responseDTO.setFailReason("未查询到映射关系！");
                        }else {
                            //如果传入开始日期大于当前日期加180天，直接结束推送
                            //如果传入结束日期大于当前日期加180天，则默认结束日期为
                            if(StringUtil.isValidString(requestDTO.getStartDate()) &&
                                    DateUtil.getDay(DateUtil.getCurrentDate(),DateUtil.stringToDate(requestDTO.getStartDate()))>=180) {
                                responseDTO.setFailCode("DATE ERROR");
                                responseDTO.setFailReason("日期范围过大！");
                            }else {
                                Date endDate = null;
                                if(StringUtil.isValidString(requestDTO.getEndDate())) {
                                    if(DateUtil.getDay(DateUtil.getCurrentDate(),DateUtil.stringToDate(requestDTO.getEndDate()))>=180) {
                                        endDate = DateUtil.getDate(DateUtil.getCurrentDate(),181,0);
                                    }else {
                                        endDate = DateUtil.getDate(DateUtil.stringToDate(requestDTO.getEndDate()),1,0);
                                    }
                                }

                                BatchPushRoomDataTask task = new BatchPushRoomDataTask(dltMapRoomPOList,requestDTO.getMerchantCode(),
                                        null==requestDTO.getStartDate()?null: DateUtil.stringToDate(requestDTO.getStartDate()),
                                        endDate);
                                ThreadPoolTaskExecutor batchPushRoomDataTaskTaskExecutor = (ThreadPoolTaskExecutor) context.getBean("batchPushRoomDataTaskTaskExecutor");
                                batchPushRoomDataTaskTaskExecutor.execute(task);

                                //休眠1秒执行下一个
                                Thread.sleep(1000);

//                                BatchPushRoomDataService batchPushRoomDataService = (BatchPushRoomDataService) context.getBean("batchPushRoomDataService");
//                                batchPushRoomDataService.pushRoomDataToDltByMapRoomList(dltMapRoomPOList,
//                                    null==requestDTO.getStartDate()?null: DateUtil.stringToDate(requestDTO.getStartDate()),
//                                    endDate,requestDTO.getMerchantCode());
                                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("增量推送出错, {}",sb.toString() , e);
            responseDTO.setFailCode("SYSTEM_EXCEPTION");
            responseDTO.setFailReason("系统异常");
        }

        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(responseDTO));
        pw.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.write("get method is not enable, please invoke post method.");
        pw.flush();
    }

}
