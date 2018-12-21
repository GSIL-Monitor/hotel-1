package com.travel.channel.dao;

import com.travel.channel.entity.po.ChannelAgentPO;
import com.travel.channel.entity.po.ChannelAgentPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelAgentPOMapper {
    int countByExample(ChannelAgentPOExample example);

    int deleteByExample(ChannelAgentPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChannelAgentPO record);

    int insertSelective(ChannelAgentPO record);

    List<ChannelAgentPO> selectByExample(ChannelAgentPOExample example);

    ChannelAgentPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChannelAgentPO record, @Param("example") ChannelAgentPOExample example);

    int updateByExample(@Param("record") ChannelAgentPO record, @Param("example") ChannelAgentPOExample example);

    int updateByPrimaryKeySelective(ChannelAgentPO record);

    int updateByPrimaryKey(ChannelAgentPO record);
}