package com.nisiwa.project3.service;


import com.nisiwa.project3.bean.User;

public interface UserService {
    boolean addUser(User user);

    User selectByName(String username);

    User selectByNamePwd(String username, String this_is_a_salt);

    User selectUserById(Integer userId);
}
