package com.travel.hotel.product.service;

import com.travel.hotel.product.entity.po.QuotaAccountPO;

import java.util.List;

/**
 *   2018/1/24.
 */
public interface QuotaAccountService {
    /**
     *
     * @param po
     * @return 返回主键：配额账号
     */
    public Long addQuotaAccount(QuotaAccountPO po);

    public List<QuotaAccountPO> queryQuotaAccountListByRoomId(Long roomTypeId);

}
