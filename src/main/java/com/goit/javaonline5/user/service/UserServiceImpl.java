package com.goit.javaonline5.user.service;

import com.goit.javaonline5.user.repository.UserRepository;
import com.goit.javaonline5.user.model.UserModel;
import com.goit.javaonline5.user.model.UserRegistrationDto;
import com.goit.javaonline5.user.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserModel save(UserRegistrationDto registrationDto) {
        UserModel user = new UserModel() {{
            setFirstName(registrationDto.getFirstName());
            setLastName(registrationDto.getLastName());
            setEmail(registrationDto.getEmail());
            setPassword(passwordEncoder.encode(
                    registrationDto.getPassword()
            ));
        }};
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), EmptyAuthorities());
    }

    //todo. ЩО ТУТ ТВОРИТЬСЯ?
    private Collection<? extends GrantedAuthority> EmptyAuthorities() {
        Collection<String> emptyList = Collections.emptyList();
        return emptyList.stream().map(empty -> new SimpleGrantedAuthority(empty.toString())).collect(Collectors.toList());
    }
}
