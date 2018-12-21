package com.travel.hotel.product.service.impl;

import com.travel.hotel.product.dao.QuotaAccountPOMapper;
import com.travel.hotel.product.entity.po.QuotaAccountPO;
import com.travel.hotel.product.service.QuotaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *   2018/1/24.
 */
@Service("quotaAccountService")
public class QuotaAccountServiceImpl implements QuotaAccountService {

    @Autowired
    private QuotaAccountPOMapper quotaAccountPOMapper;

    @Override
    public Long addQuotaAccount(QuotaAccountPO po) {
        quotaAccountPOMapper.insert(po);
        return po.getAccountId();
    }

    @Override
    public List<QuotaAccountPO> queryQuotaAccountListByRoomId(Long roomTypeId) {
        return quotaAccountPOMapper.selectByRoomTypeId(roomTypeId);
    }
}
