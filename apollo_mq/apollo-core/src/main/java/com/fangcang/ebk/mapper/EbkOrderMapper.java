package com.fangcang.ebk.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.ebk.domain.EbkOrderDO;
import com.fangcang.ebk.request.LockRequestDTO;
import com.fangcang.ebk.request.QueryEbkOrderDTO;
import com.fangcang.ebk.response.EbkOrderDTO;
import com.fangcang.ebk.response.EbkOrderSimpleDTO;

import java.util.List;

public interface EbkOrderMapper extends MyMapper<EbkOrderDO>{

    public List<EbkOrderSimpleDTO> queryOrderList(QueryEbkOrderDTO requestDTO);

    public EbkOrderDTO queryOrderDetail(String supplyOrderCode);

    public int lock(LockRequestDTO requestDTO);

    public int unlock(LockRequestDTO requestDTO);

    public int updateNotifyCount(List idList);
}
