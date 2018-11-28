package com.em.service;

import com.em.entity.User;
import com.em.repository.UserRepository;
import java.util.Optional;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class.getName());

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findByUserName(username);
        if (!oUser.isPresent()) {
            System.out.println("user not found");
            throw new UsernameNotFoundException(username + " not found.");
        }
        User user = oUser.get();
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.disabled(!user.isActive());
        builder.password(user.getPassWord());
        String[] roles = new String[]{user.getRole()};
        builder.authorities(roles);
        return builder.build();
    }
}
