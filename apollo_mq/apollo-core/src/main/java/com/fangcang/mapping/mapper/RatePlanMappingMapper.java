package com.fangcang.mapping.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.mapping.domain.RatePlanMappingDO;
import com.fangcang.mapping.dto.Mapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Owen on 2018/6/8.
 */
public interface RatePlanMappingMapper extends MyMapper<RatePlanMappingDO> {

    List<Mapping> queryMapping(@Param("merchantCode")String merchantCode, @Param("list")List<Long> hotelIdList);

}
