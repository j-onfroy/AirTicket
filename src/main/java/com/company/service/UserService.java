package com.company.service;

public interface UserService {
    boolean isAdmin(String email);
    String getEmailById(Integer userId);
}
