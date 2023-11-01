package com.company.service;

import com.company.dao.UserDAO;
import com.company.model.User;
import com.company.verification.VerificationByEmail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserDAO userDAO;
    private final VerificationByEmail verificationByEmail;

    public AuthServiceImpl(UserDAO userDAO, VerificationByEmail verificationByEmail) {
        this.userDAO = userDAO;
        this.verificationByEmail = verificationByEmail;
    }

    @Override
    public Integer register(User user) {
        Optional<User> byEmail = userDAO.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            Integer userId = userDAO.save(user);
            verificationByEmail.generatedCodeByEmail(userId,user.getEmail());
            return userId;
        }
        if (byEmail.get().isVerified()) {
            return -1;
        } else {
            return -2;
        }
    }

    @Override
    public void setVerified(Integer userId) {
        userDAO.setVerified(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userDAO.findByEmail(username);
        return byEmail.orElseThrow(() -> new UsernameNotFoundException("User not verified"));
    }
}
