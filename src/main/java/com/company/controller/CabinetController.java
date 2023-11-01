package com.company.controller;

import com.company.utils.AppConstants;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



public interface CabinetController {
    String BASE_PATH = AppConstants.BASE_PATH + "/cabinet";
    @GetMapping(BASE_PATH)
    String cabinetPage(Model model);
}
