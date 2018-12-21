package com.travel.hotel.dlt.schedule;

import com.alibaba.fastjson.JSON;
import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.common.constant.InitData;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.dlt.dao.DltOrderDetailPOMapper;
import com.travel.hotel.dlt.dao.DltOrderPOMapper;
import com.travel.hotel.dlt.entity.po.DltOrderDetailPO;
import com.travel.hotel.dlt.entity.po.DltOrderDetailPOExample;
import com.travel.hotel.dlt.entity.po.DltOrderPO;
import com.travel.hotel.dlt.request.base.PagingType;
import com.travel.hotel.dlt.request.dto.GetDltOrderNotifyRequest;
import com.travel.hotel.dlt.response.dto.GetDltOrderNotifyOrderListDTO;
import com.travel.hotel.dlt.response.dto.GetDltOrderNotifyResponse;
import com.travel.hotel.dlt.utils.DltInterfaceInvoker;
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
public class GetDltOrderLeakedSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(GetDltOrderLeakedSchedule.class);

    private static final String CRON = "0 0/10 * * * ?";

    @Autowired
    private DltOrderPOMapper dltOrderPOMapper;

    @Autowired
    private DltOrderDetailPOMapper dltOrderDetailPOMapper;

    @Scheduled(cron = CRON)
    public void getDltOrderNotify() {

        List<DltOrderPO> dltOrderPOList = new ArrayList<>();
        try {
            GetDltOrderNotifyRequest request = new GetDltOrderNotifyRequest();
            Date currDate = new Date();
            Date startTime = DateUtil.getDate(currDate, 0, -1, 0);

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
                        //过滤掉已经存在的订单
                        List<GetDltOrderNotifyOrderListDTO> leakedOrderList=new ArrayList<>();
                        List<String> leakedOrderIdList=new ArrayList<>();
                        for (GetDltOrderNotifyOrderListDTO tempDTO : getDltOrderNotifyOrderListDTOList) {
                            DltOrderDetailPOExample example = new DltOrderDetailPOExample();
                            example.createCriteria().andDltOrderIdEqualTo(tempDTO.getDltOrderId());
                            List<DltOrderDetailPO> dltOrderDetailPOList = dltOrderDetailPOMapper.selectByExample(example);
                            if (dltOrderDetailPOList.size()==0){
                                leakedOrderList.add(tempDTO);
                                leakedOrderIdList.add(tempDTO.getDltOrderId());
                            }
                        }

                        LOG.info("**************leakedOrderList:"+leakedOrderIdList);
                        //加入到增量表
                        for (GetDltOrderNotifyOrderListDTO tempDTO : leakedOrderList) {
                            DltOrderPO po = BeanCopy.copyProperties(tempDTO,DltOrderPO.class);
                            po.setDltOrderId(tempDTO.getDltOrderId());
                            po.setMerchantCode(merchantCode);
                            po.setCreateDate(new Date());
                            po.setIsHandled(1);//默认是1
                            po.setMerchantCode(merchantCode);
//                            po.setUpdateTime(new Date());//区别下是补偿任务拉去回来的
                            dltOrderPOList.add(po);
                        }

                        if (!CollectionUtils.isEmpty(dltOrderPOList)){
                            dltOrderPOMapper.insertBatch(dltOrderPOList);
                        }
                    }
                }
            }else {
                LOG.error("渠道配置表信息加载出错，或没有配置渠道对接信息！");
            }
        } catch (Exception e) {
            LOG.error("同步订单提醒失败,{}", JSON.toJSONString(dltOrderPOList), e);
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
