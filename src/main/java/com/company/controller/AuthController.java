package com.company.controller;

import com.company.model.User;
import com.company.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;

public interface AuthController {
    String BASE_PATH = AppConstants.BASE_PATH + "/auth/";
    String REGISTER_PATH = BASE_PATH + "register";
    String VERIFICATION_PATH = BASE_PATH + "verification";

    @GetMapping(REGISTER_PATH)
    String registerPage();

    @PostMapping(REGISTER_PATH)
    String register(@Valid @ModelAttribute User user, Model model) throws MessagingException;

    @PostMapping(VERIFICATION_PATH)
    String verification(@Valid @RequestParam Integer userId, @RequestParam String enterCode, Model model);

    @GetMapping(VERIFICATION_PATH)
    String verificationPage(@Valid @RequestParam Integer userId, Model model);
}
