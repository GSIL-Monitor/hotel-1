package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.DictionaryPO;
import com.travel.hotel.product.entity.po.DictionaryPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryPOMapper {
    int countByExample(DictionaryPOExample example);

    int deleteByExample(DictionaryPOExample example);

    int deleteByPrimaryKey(Long idtDictionary);

    int insert(DictionaryPO record);

    int insertSelective(DictionaryPO record);

    List<DictionaryPO> selectByExample(DictionaryPOExample example);

    DictionaryPO selectByPrimaryKey(Long idtDictionary);

    int updateByExampleSelective(@Param("record") DictionaryPO record, @Param("example") DictionaryPOExample example);

    int updateByExample(@Param("record") DictionaryPO record, @Param("example") DictionaryPOExample example);

    int updateByPrimaryKeySelective(DictionaryPO record);

    int updateByPrimaryKey(DictionaryPO record);

    List<DictionaryPO> selectAll();
}