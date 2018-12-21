package com.fangcang.product.service;

import com.fangcang.product.dto.RestrictDTO;

/**
 * Created by Vinney on 2018/10/17.
 */
public interface RestrictService {

    /**
     * 保存条款
     * 先删除，再插入。新增和编辑页都可以用这个。
     * @param restrictDTO
     */
    void saveRestrictByRatePlanId(RestrictDTO restrictDTO);

    /**
     *
     * @param restrictDTO
     * @return
     */
    RestrictDTO queryRestrictByRatePlanId(RestrictDTO restrictDTO);

    /**
     *
     */
    void deleteRestrictByRatePlanId(Long ratePlanId);

}
