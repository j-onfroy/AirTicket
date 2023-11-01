package com.company.service;

import com.company.dao.UserDAO;
import com.company.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    @Override
    public boolean isAdmin(String email) {
        Optional<User> byEmail = userDAO.findByEmail(email);
        return byEmail.get().isAdmin();
    }

    @Override
    public String getEmailById(Integer userId) {
        return userDAO.getEmailById(userId);
    }
}
