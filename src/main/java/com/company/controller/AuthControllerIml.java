package com.company.controller;

import com.company.model.User;
import com.company.service.AuthService;
import com.company.verification.VerificationByEmail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;

@Controller
@RequiredArgsConstructor
public class AuthControllerIml implements AuthController {
    private final AuthService authService;
    private final VerificationByEmail verificationByEmail;
    @Override
    public String registerPage() {
        return "auth/register";
    }

    @Override
    public String register(@Valid @ModelAttribute User user, Model model) throws MessagingException {
        Integer userId = authService.register(user);
        if(userId==-1){
            model.addAttribute("error","This account already exists");
            return "auth/register";
        }else if (userId==-2){
            model.addAttribute("error","Your account is not verified");
            return "auth/register";
        }
        model.addAttribute("userId", userId);
        return "auth/sendcode";
    }

    @Override
    public String verification(@Valid @RequestParam Integer userId, @Valid @RequestParam String enterCode, Model model) {
        boolean isValid = verificationByEmail.verification(userId, enterCode);
        if (isValid) {
            authService.setVerified(userId);
            return "auth/succes";
        } else {
            model.addAttribute("error","Code is wrong. Enter again");
            model.addAttribute("userId",userId);
            return "auth/sendcode";
        }
    }

    @Override
    public String verificationPage(@Valid @RequestParam Integer userId, Model model) {
        verificationByEmail.generatedCodeByUserId(userId);
        model.addAttribute("userId",userId);
        return "auth/sendcode";
    }
}
