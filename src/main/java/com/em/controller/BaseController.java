package com.em.controller;

import com.em.entity.User;
import com.em.repository.UserRepository;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public abstract class BaseController {

    private static final Logger LOGGER = LogManager.getLogger(BaseController.class.getName());

    @Resource
    private UserRepository userRepository;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;

    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(upat);
        if (upat.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(upat);
            LOGGER.info(String.format("Auto login %s successfully!", username));
        }
    }

    public UserDetails getUserDetail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                return (UserDetails) auth.getPrincipal();
            }
        }
        return null;
    }

    public String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return String.valueOf(principal);
        }
    }

    public User getUser() {
        return userRepository.findByUserName(getUserName()).get();
    }
}
