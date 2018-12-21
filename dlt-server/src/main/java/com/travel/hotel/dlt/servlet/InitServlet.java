package com.travel.hotel.dlt.servlet;


import com.travel.common.constant.InitData;
import com.travel.hotel.dlt.request.dto.IncrementPushDataRequest;
import com.travel.hotel.dlt.schedule.BatchPushRoomDataSchedule;
import com.travel.hotel.product.entity.po.DictionaryPO;
import com.travel.hotel.product.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.travel.hotel.dlt.schedule.BatchPushRoomDataSchedule.needSyncRoomDataToDlt;

/***
 * 初始化信息
 *
 * @author Administrator
 */
@WebServlet(name = "InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet implements javax.servlet.Servlet {

    private static final long serialVersionUID = 1577428495743539780L;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        try {
            initDictionaryMap(null);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String opType = req.getParameter("opType");
        if (!StringUtils.isEmpty(opType)) {
            if ("setNeedSyncRoomDataToDlt".equals(opType)) {

                Boolean needSyncRoomDataToDlt = Boolean.valueOf(req.getParameter("needSyncRoomDataToDlt"));
                this.setNeedSyncRoomDataToDlt(needSyncRoomDataToDlt);

//                resp.setContentType("application/json");
                resp.getWriter().print(needSyncRoomDataToDlt);

            } else if ("getNeedSyncRoomDataToDlt".equals(opType)) {

                Boolean needSyncRoomDataToDlt = this.getNeedSyncRoomDataToDlt();
//                resp.setContentType("application/json");
                resp.getWriter().print(needSyncRoomDataToDlt);

            }
        } else {
            this.initDictionaryMap(resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    private void setNeedSyncRoomDataToDlt(Boolean needSyncRoomDataToDlt) {
        BatchPushRoomDataSchedule.needSyncRoomDataToDlt = needSyncRoomDataToDlt;
        DltIncrementPushDataServlet.needSyncRoomDataToDlt = needSyncRoomDataToDlt;
    }

    private Boolean getNeedSyncRoomDataToDlt() {
        return BatchPushRoomDataSchedule.needSyncRoomDataToDlt;
    }

    private void initDictionaryMap(HttpServletResponse resp) {

        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        DictionaryService dictionaryService = (DictionaryService) context.getBean("dictionaryService");
        List<DictionaryPO> allList = dictionaryService.queryAll();
        InitData.cityMap = dictionaryService.queryCityMap(allList);
        InitData.starMap = dictionaryService.queryStarMap(allList);
        InitData.bedTypeMap = dictionaryService.queryBedTypeMap(allList);
        InitData.currencyMap = dictionaryService.queryCurrencyMap(allList);
        InitData.quotaTypeMap = dictionaryService.queryQuotaTypeMap(allList);
        InitData.weekendList = dictionaryService.queryWeekendList(allList);
        InitData.chargeMap = dictionaryService.queryChargeTypeMap(allList);
        InitData.merchantMap = dictionaryService.queryMerchantMap(allList);
        InitData.dltInterfaceInfoMap = dictionaryService.queryDltInterfaceInfoMap(allList);
        InitData.channelConfigMap = dictionaryService.queryChannelConfig();

        if (null != resp) {
            try {
                resp.getWriter().println("citMap=" + InitData.cityMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("starMap=" + InitData.starMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("bedTypeMap=" + InitData.bedTypeMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("currencyMap=" + InitData.currencyMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("quotaTypeMap=" + InitData.quotaTypeMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("weekendList=" + InitData.weekendList);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("chargeMap=" + InitData.chargeMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("dltInterfaceInfoMap=" + InitData.dltInterfaceInfoMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("bankMap=" + InitData.bankMap);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("RESERVE_QUOTA_TIME=" + InitData.RESERVE_QUOTA_TIME);
                resp.getWriter().println("<br/>");
                resp.getWriter().println("channelConfigMap=" + InitData.channelConfigMap);
            } catch (IOException e) {
                log.error("print result failure", e);
            }
        }
    }

}
