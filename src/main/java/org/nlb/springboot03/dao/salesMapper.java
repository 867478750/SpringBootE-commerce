package org.nlb.springboot03.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.nlb.springboot03.object.sales;
import org.nlb.springboot03.object.salesExample;

public interface salesMapper {
    long countByExample(salesExample example);

    int deleteByExample(salesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(sales record);

    int insertSelective(sales record);

    List<sales> selectByExample(salesExample example);

    sales selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") sales record, @Param("example") salesExample example);

    int updateByExample(@Param("record") sales record, @Param("example") salesExample example);

    int updateByPrimaryKeySelective(sales record);

    int updateByPrimaryKey(sales record);
}