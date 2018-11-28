package com.em.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EMController extends BaseController {

    //https://github.com/hellokoding/registration-login-spring-xml-maven-jsp-mysql
    private static final Logger LOGGER = LogManager.getLogger(EMController.class.getName());

    @GetMapping(value = "login.htm")
    public String login(HttpServletRequest request, Model model) {
        return "common/login";
    }

    @GetMapping(value = "logout.htm")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login.htm?logout";
    }
}
