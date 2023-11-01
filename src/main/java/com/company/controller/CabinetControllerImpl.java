package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class CabinetControllerImpl implements CabinetController{
   private final UserService userService;
    @Override
    public String cabinetPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean admin = userService.isAdmin(user.getEmail()) || user.getEmail().equalsIgnoreCase("doniyrep@gmail.com");
        if(admin){
            return "control/adminPage";
        }else {
            System.out.println(user);
            if(user.isVerified()){
                return "cabinet/cabinet";
            }else {
                model.addAttribute("userId",user.getId());
                return "auth/not-verified";
            }

        }
    }
}
