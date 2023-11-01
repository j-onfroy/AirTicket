package com.company.service;

import com.company.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    Integer register(User user);
    void setVerified(Integer userId);
}
