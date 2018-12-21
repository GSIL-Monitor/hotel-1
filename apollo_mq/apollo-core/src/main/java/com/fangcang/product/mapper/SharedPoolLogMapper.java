package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.product.domain.SharedPoolLogDO;

import java.util.List;

/**
 * Created by ASUS on 2018/6/6.
 */
public interface SharedPoolLogMapper extends MyMapper<SharedPoolLogDO> {

    /**
     * 批量保存共享池操作日志
     * @param sharedPoolLogDOList
     */
    public void batchSaveSharedPoolLog(List<SharedPoolLogDO> sharedPoolLogDOList);
}
