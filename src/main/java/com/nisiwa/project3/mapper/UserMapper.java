package com.nisiwa.project3.mapper;

import com.nisiwa.project3.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByName(String name);

    User selectByNamePwd(@Param("username") String username, @Param("password") String this_is_a_salt);
}