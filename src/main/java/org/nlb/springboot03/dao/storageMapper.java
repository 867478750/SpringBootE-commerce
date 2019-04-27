package org.nlb.springboot03.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.nlb.springboot03.object.storage;
import org.nlb.springboot03.object.storageExample;

public interface storageMapper {
    long countByExample(storageExample example);

    int deleteByExample(storageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(storage record);

    int insertSelective(storage record);

    List<storage> selectByExample(storageExample example);

    storage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") storage record, @Param("example") storageExample example);

    int updateByExample(@Param("record") storage record, @Param("example") storageExample example);

    int updateByPrimaryKeySelective(storage record);

    int updateByPrimaryKey(storage record);
}