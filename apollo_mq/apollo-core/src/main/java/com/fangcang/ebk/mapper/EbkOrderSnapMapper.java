package com.fangcang.ebk.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.ebk.domain.EbkOrderSnapDO;
import com.fangcang.ebk.response.EbkOrderSnapDTO;

public interface EbkOrderSnapMapper extends MyMapper<EbkOrderSnapDO>{

    public EbkOrderSnapDTO queryModifyRequestDetail(Long requestId);
}
