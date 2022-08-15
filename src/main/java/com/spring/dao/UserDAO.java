package com.spring.dao;

import com.spring.entity.User;

public interface UserDAO {
    User findUserByName(String username);
    void save(User user);
}
