package com.travel.hotel.dlt.schedule;


import com.travel.common.utils.DateUtil;
import com.travel.hotel.dlt.dao.DltOrderPOMapper;
import com.travel.hotel.dlt.entity.po.DltOrderPO;
import com.travel.hotel.dlt.entity.po.DltOrderPOExample;
import com.travel.hotel.dlt.request.dto.GetDltOrderInfoRequest;
import com.travel.hotel.dlt.response.dto.DltOrderInfo;
import com.travel.hotel.dlt.response.dto.GetDltOrderInfoResponse;
import com.travel.hotel.dlt.service.DltHotelOrderService;
import com.travel.hotel.dlt.utils.DltInterfaceInvoker;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 定时查询代理通订单变化通知
 *   2018/4/11.
 */
@Component
@Lazy(false)
@EnableScheduling
public class GetDltOrderInfoSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(GetDltOrderInfoSchedule.class);

    //0/30 * * * * ?
    private static final String CRON = "0 0/1 * * * ?";

    private static volatile Set<String> inProcessingOrderSet = new HashSet<>();
    @Autowired
    private DltOrderPOMapper dltOrderPOMapper;

    @Autowired
    private DltHotelOrderService dltHotelOrderService;

    @Scheduled(cron = CRON)
    public void getDltOrderInfo() {

        // 从t_dlt_order表中取出所有未处理的订单号
        DltOrderPOExample example = new DltOrderPOExample();
        // 0 是处理成功，其他都是处理的次数，每处理一次累加1，超过3次不处理了
        example.createCriteria().andIsHandledBetween(1, 3);
        example.setOrderByClause("create_date");
        List<DltOrderPO> dltOrderPOList = dltOrderPOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(dltOrderPOList)) {
            LOG.error("没有需要同步的订单信息");
            return;
        }

        // 合并订单号
        Map<String, DltOrderPO> orderMap = new HashMap<>();
        dltOrderPOList.forEach(po -> {
            if (!orderMap.keySet().contains(po.getDltOrderId())) {
                orderMap.put(po.getDltOrderId(), po);
            }
//            else {
//                // 选一个处理次数小的作为实际处理次数，尽可能多处理计次直至成功
//                DltOrderPO temp = orderMap.get(po.getDltOrderId());
//                temp.setIsHandled(temp.getIsHandled() > po.getIsHandled() ? po.getIsHandled() : temp.getIsHandled());
//            }
        });

        // 循环查询dlt接口，获取订单详情
        for (Map.Entry<String, DltOrderPO> entry : orderMap.entrySet()) {
            if (!inProcessingOrderSet.contains(entry.getKey())) {
                synchronized (this) {
                    if (!inProcessingOrderSet.contains(entry.getKey())) {
                        LOG.info("开始同步订单，DltOrderId=" + entry.getKey());
                        inProcessingOrderSet.add(entry.getKey());
                        LOG.info("开始同步订单，订单号被加入到去重集合,DltOrderId=" + entry.getKey());
                    } else {
                        continue;
                    }
                }
            } else {
                // 如果正在处理，则跳过，避免订单被多个定时任务重复处理
                continue;
            }
            this.getDltOrderInfo(entry.getKey(),entry.getValue().getMerchantCode());
            inProcessingOrderSet.remove(entry.getKey());
            LOG.info("同步订单结束：订单号从去重集合中删除,DltOrderId=" + entry.getKey());
        }

    }

    public void getDltOrderInfo(String dltOrderId,String merchantCode) {
        GetDltOrderInfoRequest request = new GetDltOrderInfoRequest();
        request.setDltOrderId(dltOrderId);
        GetDltOrderInfoResponse response = DltInterfaceInvoker.invoke(request,merchantCode);
        if (null == response || null == response.getResultStatus() || 0 != response.getResultStatus().getResultCode()
                || null == response.getDltOrderInfo()) {
            dltHotelOrderService.updateOrderHandleResult(dltOrderId, false, "调用代理通接口返回空");
            return;
        }

        try {
            DltOrderInfo orderInfo = response.getDltOrderInfo();

            /**
             * 代理通有时候会更新全部数据的时间戳。导致历史数据被拉回来了。此处做一次过滤
             * 离店日期为1天前的订单不同步了
             */
            if (DateUtil.getDay(DateUtil.getBeforeDate(DateUtil.getCurrentDate()),orderInfo.getCheckoutDate()) < 0){
                //此订单不同步，从t_dlt_order表中删除？
                LOG.info("已过离店日期：checkoutDate="+orderInfo.getCheckoutDate());
                dltHotelOrderService.updateOrderHandleResult(dltOrderId, true, "已过离店日期，无需同步");
            } else{
                dltHotelOrderService.createOrder(orderInfo,merchantCode);
            }

        } catch (Exception e) {
            LOG.error("同步代理通订单到订单系统出错", e);
        }
    }
}
