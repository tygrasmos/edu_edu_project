package ru.mos.tygras.eve.planned_assistance.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.User;
import ru.mos.tygras.eve.planned_assistance.service.repository.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserService userService;

    public UserDetailServiceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByLogin(username);

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        org.springframework.security.core.userdetails.User userSecurity =
                new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        encoder.encode(user.getPassword()),
                        user.getEnabled(),
                        user.getAccountNonExpired(),
                        user.getCredentialsNonExpired(),
                        user.getAccountNonLocked(),
                        getGrantedAuthorities(user.getRoles()));

        return userSecurity;
    }

    private List<SimpleGrantedAuthority> getGrantedAuthorities(String roles){
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
