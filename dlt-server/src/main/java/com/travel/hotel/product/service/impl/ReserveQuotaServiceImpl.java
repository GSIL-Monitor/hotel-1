package com.travel.hotel.product.service.impl;

import com.travel.hotel.product.dao.ReserveQuotaPOMapper;
import com.travel.hotel.product.entity.po.ReserveQuotaPO;
import com.travel.hotel.product.service.PricePlanService;
import com.travel.hotel.product.service.ReserveQuotaService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *   2018/3/31.
 */
@Service("reserveQuotaService")
public class ReserveQuotaServiceImpl implements ReserveQuotaService {


    @Autowired
    private ReserveQuotaPOMapper reserveQuotaPOMapper;

    @Autowired
    private PricePlanService pricePlanService;


    @Override
    public void recoverReserveQuota(ReserveQuotaPO reserveQuotaPO) {

        //1-查询需要回收的配额数据（正在回收的不查询出来）
        List<ReserveQuotaPO> reserveQuotaPOList = reserveQuotaPOMapper.selectByCondition(reserveQuotaPO);

        //2-将这些数据标记为正在回收
        reserveQuotaPOList.forEach(po -> po.setStatus(2));

        if (CollectionUtils.isEmpty(reserveQuotaPOList)){
            return;
        }

        reserveQuotaPOMapper.updateStatusToReserving(reserveQuotaPOList);

        //3-回收数据
        for (ReserveQuotaPO po : reserveQuotaPOList){
            try {
                po.setAmount(0-po.getReserveQuotaNum());
                po.setReserveQuotaNum(0);
                po.setStatus(1);
                pricePlanService.updateDistributeReserveQuota(po);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }
}
