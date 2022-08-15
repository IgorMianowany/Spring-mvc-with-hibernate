package com.spring.service;

import com.spring.entity.CRMUser;
import com.spring.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(CRMUser user);

    User findByUserName(String username);
}
