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
        String[] roles = user.getRoles().stream().map(a -> a.getName()).toArray(String[]::new);
        builder.authorities(roles);
        return builder.build();
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(0);
//        user.getRoles().forEach(role -> {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        });
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findUserbyUername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found.");
//        }
//
//        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
//        builder.password(passwordEncoder.encode(user.getPassWord()));
//        String[] roles = user.getRoles().stream().map(a -> a.getRole()).toArray(String[]::new);
//        builder.authorities(roles);
//        return builder.build();
//    }
//
//    private User findUserbyUername(String username) {
//        if (username.equalsIgnoreCase("admin")) {
//            User user = new User();
//            user.setUserName(username);
//            user.setPassWord("admin123");
//            final Role userRole = new Role();
//            userRole.setId(1);
//            userRole.setRole("ADMIN");
//            userRole.setUser(user);
//            user.setRoles(new HashSet<UserRole>() {
//                {
//                    add(userRole);
//                }
//            });
//            return user;
//        }
//        return null;
//    }
}
