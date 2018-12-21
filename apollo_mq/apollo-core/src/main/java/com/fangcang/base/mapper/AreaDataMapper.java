package com.fangcang.base.mapper;

import com.fangcang.base.domain.AreaDataDO;
import com.fangcang.common.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaDataMapper extends MyMapper<AreaDataDO>{

    public List<AreaDataDO> queryTreeAreaData(@Param("type")Integer type,@Param("dataCode")String dataCode);
}
