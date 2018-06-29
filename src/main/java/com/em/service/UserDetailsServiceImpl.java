package com.em.service;

import com.em.entity.User;
import com.em.repository.UserRepository;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findByUserName(username);
        if (!oUser.isPresent()) {
            throw new UsernameNotFoundException(username + " not found.");
        }
        User user = oUser.get();
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.disabled(!user.isActive());
        builder.password(user.getPassWord());
        String[] roles = user.getRoles().stream().map(a -> a.getRole()).toArray(String[]::new);
        builder.authorities(roles);
        return builder.build();
    }
}
