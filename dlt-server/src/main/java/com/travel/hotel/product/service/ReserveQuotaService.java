package com.travel.hotel.product.service;

import com.travel.hotel.product.entity.po.ReserveQuotaPO;

/**
 *   2018/3/31.
 */
public interface ReserveQuotaService {

    /**
     * 回收预留配额
     * @param reserveQuotaPO
     */
    void recoverReserveQuota(ReserveQuotaPO reserveQuotaPO);

}
