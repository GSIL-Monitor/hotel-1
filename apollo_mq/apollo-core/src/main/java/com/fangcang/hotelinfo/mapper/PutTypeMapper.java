package com.fangcang.hotelinfo.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.PutTypeDO;

import java.util.List;

/**
 * Created by ASUS on 2018/6/8.
 */
public interface PutTypeMapper extends MyMapper<PutTypeDO> {

    /**
     * 批量新增摆放类型
     * @param putTypeDOList
     */
    public void batchInsertPutType(List<PutTypeDO> putTypeDOList);

    /**
     * 根据会议室ID删除摆放类型
     * @param putTypeDO
     */
    public void deletePutTypeByMeetingId(PutTypeDO putTypeDO);
}
