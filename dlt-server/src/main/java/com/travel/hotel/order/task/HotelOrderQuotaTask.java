package com.travel.hotel.order.task;

import com.travel.common.dto.product.request.QuotaDTO;
import com.travel.common.enums.QuotaOperateEnum;
import com.travel.common.utils.SpringContextUtil;
import com.travel.hotel.order.dao.OrderOperateLogPOMapper;
import com.travel.hotel.order.entity.po.OrderOperateLogPO;
import com.travel.hotel.product.service.QuotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 *   2018/3/22.
 */
public class HotelOrderQuotaTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(HotelOrderQuotaTask.class);

    private List<QuotaDTO> quotaDTOList;
    private QuotaOperateEnum quotaOperateEnum;
    private QuotaService quotaServiceImpl;
    private OrderOperateLogPOMapper orderOperateLogPOMapper;

    public HotelOrderQuotaTask(List<QuotaDTO> quotaDTOList, QuotaOperateEnum quotaOperateEnum) {
        this.quotaDTOList = quotaDTOList;
        this.quotaOperateEnum = quotaOperateEnum;
    }

    @Override
    public void run() {

        orderOperateLogPOMapper = (OrderOperateLogPOMapper) SpringContextUtil.getBean("orderOperateLogPOMapper");
        OrderOperateLogPO orderOperateLogPO = new OrderOperateLogPO();

        try {
            quotaServiceImpl = (QuotaService) SpringContextUtil.getBean("quotaServiceImpl");

            // 扣配额
            if (QuotaOperateEnum.DEDUCT.key.equals(quotaOperateEnum.key)) {
                String errorMsg = quotaServiceImpl.batchDeductQuota(quotaDTOList);
                orderOperateLogPO.setContent(errorMsg);
            } else {
                quotaServiceImpl.batchRefundQuota(quotaDTOList);
                orderOperateLogPO.setContent("批量退还配额成功");
            }
        } catch (Exception e) {
            LOG.error("扣配额失败", e);
            orderOperateLogPO.setContent("批量" + quotaOperateEnum.value + "失败");
        }

        orderOperateLogPO.setOrderCode(quotaDTOList.get(0).getOrderCode());
        orderOperateLogPO.setCreator("system");
        orderOperateLogPO.setCreateDate(new Date());
        orderOperateLogPOMapper.insert(orderOperateLogPO);
    }
}
