package com.nisiwa.project3.service.impl;

import com.nisiwa.project3.bean.User;
import com.nisiwa.project3.mapper.UserMapper;
import com.nisiwa.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: nisiwa
 * @date: 2019-04-10 20:54
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        int insert = userMapper.insert(user);
        return insert == 1;
    }

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public User selectByNamePwd(String username, String this_is_a_salt) {
        return userMapper.selectByNamePwd(username, this_is_a_salt);
    }

    @Override
    public User selectUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
