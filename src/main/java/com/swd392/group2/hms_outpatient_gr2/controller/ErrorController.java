package com.swd392.group2.hms_outpatient_gr2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error-403")
    public String showError403() {
        return "error/403";
    }


    @GetMapping("/error-404")
    public String showError404() {
        return "error/404";
    }
}
