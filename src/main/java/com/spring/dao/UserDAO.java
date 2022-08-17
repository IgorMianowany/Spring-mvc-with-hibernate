package com.spring.dao;

import com.spring.entity.User;

import java.util.List;

public interface UserDAO {
    User findUserByName(String username);
    void save(User user);

    List<User> getUsers();

    User getUser(int id);
}
