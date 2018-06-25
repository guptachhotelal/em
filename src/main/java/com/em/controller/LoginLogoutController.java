package com.em.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

    @GetMapping(value = "login.htm")
    public String login() {
        return "common/login";
    }
}
