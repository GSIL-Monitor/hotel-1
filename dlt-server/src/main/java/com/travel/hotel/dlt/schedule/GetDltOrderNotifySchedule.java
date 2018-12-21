package com.travel.hotel.dlt.schedule;

import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.common.constant.InitData;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.dlt.dao.DltOrderPOMapper;
import com.travel.hotel.dlt.entity.po.DltOrderPO;
import com.travel.hotel.dlt.request.base.PagingType;
import com.travel.hotel.dlt.request.dto.GetDltOrderNotifyRequest;
import com.travel.hotel.dlt.response.dto.GetDltOrderNotifyOrderListDTO;
import com.travel.hotel.dlt.response.dto.GetDltOrderNotifyResponse;
import com.travel.hotel.dlt.utils.DltInterfaceInvoker;
import com.travel.hotel.product.service.DictionaryService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 定时查询代理通订单变化通知
 *   2018/4/11.
 */
@Component
@Lazy(false)
@EnableScheduling
public class GetDltOrderNotifySchedule {

    private static final Logger LOG = LoggerFactory.getLogger(GetDltOrderNotifySchedule.class);

    private static final String CRON = "0/30 * * * * ?";

    @Autowired
    private DltOrderPOMapper dltOrderPOMapper;

    @Autowired
    private DictionaryService dictionaryService;

    @Scheduled(cron = CRON)
    public void getDltOrderNotify() {
        try {
            GetDltOrderNotifyRequest request = new GetDltOrderNotifyRequest();
            Date currDate = new Date();
            Date lastEndTime=dictionaryService.queryLastEndTime();
            dictionaryService.saveLastEndTime(currDate);
            Date startTime = (null == lastEndTime) ? DateUtil.getDate(currDate, 0, 0, -30) : lastEndTime;

            //重叠拉取30秒内的增量
            /*Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            calendar.add(Calendar.SECOND, -30);
            startTime = calendar.getTime();*/

            request.setStartTime(this.dateFormat(startTime));
            request.setEndTime(this.dateFormat(currDate));

            PagingType pagingType = new PagingType();
            pagingType.setPageSize(100);
            pagingType.setPageIndex(1);
            request.setPagingType(pagingType);

            //将映射关系按商家分组，分开拉取
            Map<String,List<DltMapRoomPO>> mappingMap = new HashMap<String,List<DltMapRoomPO>>();
            if(null!= InitData.channelConfigMap.keySet()) {
                for(String merchantCode : InitData.channelConfigMap.keySet()) {
//                    List<String> orderIdList = this.queryOrderIdsFromDlt(request,merchantCode);
                    List<GetDltOrderNotifyOrderListDTO> getDltOrderNotifyOrderListDTOList = this.queryOrderListFromDlt(request,merchantCode);
                    if (CollectionUtils.isNotEmpty(getDltOrderNotifyOrderListDTOList)) {
                        List<DltOrderPO> dltOrderPOList = new ArrayList<>();
                        for (GetDltOrderNotifyOrderListDTO tempDTO : getDltOrderNotifyOrderListDTOList) {
                            DltOrderPO po = BeanCopy.copyProperties(tempDTO,DltOrderPO.class);
                            po.setDltOrderId(tempDTO.getDltOrderId());
                            po.setMerchantCode(merchantCode);
                            po.setCreateDate(new Date());
                            po.setIsHandled(1);//默认是1
                            po.setMerchantCode(merchantCode);
                            dltOrderPOList.add(po);
                        }
                        dltOrderPOMapper.insertBatch(dltOrderPOList);
                    }
                }
            }else {
                LOG.error("渠道配置表信息加载出错，或没有配置渠道对接信息！");
            }
        } catch (Exception e) {
            LOG.error("同步订单提醒失败", e);
        }
    }

    private List<String> queryOrderIdsFromDlt(GetDltOrderNotifyRequest request,String merchantCode) {

        List<String> orderIdList = new ArrayList<>();
        GetDltOrderNotifyResponse response = DltInterfaceInvoker.invoke(request,merchantCode);
        if (null != response && null != response.getResultStatus() && 0 == response.getResultStatus().getResultCode()) {
            orderIdList.addAll(response.getDltOrderIds());

            // 递归分页获取数据
            PagingType pt = response.getPagingType();
            if (null != pt && pt.getTotalPages() > pt.getPageIndex()) {
                pt.setPageIndex(pt.getPageIndex() + 1);
                pt.setTotalPages(null);
                pt.setTotalRecords(null);
                request.setPagingType(pt);
                orderIdList.addAll(this.queryOrderIdsFromDlt(request,merchantCode));
            }
        }

        return orderIdList;
    }

    private List<GetDltOrderNotifyOrderListDTO> queryOrderListFromDlt(GetDltOrderNotifyRequest request, String merchantCode) {

        List<GetDltOrderNotifyOrderListDTO> orderIdList = new ArrayList<>();
        GetDltOrderNotifyResponse response = DltInterfaceInvoker.invoke(request,merchantCode);
        if (null != response && null != response.getResultStatus() && 0 == response.getResultStatus().getResultCode()) {
            orderIdList.addAll(response.getDltOrderList());

            // 递归分页获取数据
            PagingType pt = response.getPagingType();
            if (null != pt && pt.getTotalPages() > pt.getPageIndex()) {
                pt.setPageIndex(pt.getPageIndex() + 1);
                pt.setTotalPages(null);
                pt.setTotalRecords(null);
                request.setPagingType(pt);
                orderIdList.addAll(this.queryOrderListFromDlt(request,merchantCode));
            }
        }

        return orderIdList;
    }

    private String dateFormat(Date date) {
        return "/Date(" + date.getTime() + "+0800)/";
    }
}
