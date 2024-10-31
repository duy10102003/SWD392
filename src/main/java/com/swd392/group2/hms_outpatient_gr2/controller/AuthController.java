package com.swd392.group2.hms_outpatient_gr2.controller;

import com.swd392.group2.hms_outpatient_gr2.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {
    private final AccountService accountService;
    private final HttpSession session;

    @Autowired
    public AuthController(AccountService accountService,
                          HttpSession session) {
        this.accountService = accountService;
        this.session = session;
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping(value = {"/", "/home"})
    public String homePage(Model model) {
        return "home-page";
    }
}
