package org.nlb.springboot03.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.nlb.springboot03.object.user;
import org.nlb.springboot03.object.userExample;
import org.nlb.springboot03.object.user_password;

public interface userMapper {
    long countByExample(userExample example);

    int deleteByExample(userExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(user record);

    int insertSelective(user record);

    int selectFromTelephone(String telephone);

    user selectFromTelephone2(String telephone);

    List<user> selectByExample(userExample example);

    user selectByPrimaryKey(Integer id);


    int updateByExampleSelective(@Param("record") user record, @Param("example") userExample example);

    int updateByExample(@Param("record") user record, @Param("example") userExample example);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
}