package org.nlb.springboot03.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.nlb.springboot03.object.user_password;
import org.nlb.springboot03.object.user_passwordExample;

public interface user_passwordMapper {
    long countByExample(user_passwordExample example);

    int deleteByExample(user_passwordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(user_password record);

    int insertSelective(user_password record);

    List<user_password> selectByExample(user_passwordExample example);

    user_password selectByPrimaryKey(Integer id);



    user_password selectById(@Param(value="userId") Integer userId);

    int updateByExampleSelective(@Param("record") user_password record, @Param("example") user_passwordExample example);

    int updateByExample(@Param("record") user_password record, @Param("example") user_passwordExample example);

    int updateByPrimaryKeySelective(user_password record);

    int updateByPrimaryKey(user_password record);
}