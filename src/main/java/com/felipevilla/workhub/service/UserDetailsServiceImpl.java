package com.felipevilla.workhub.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipevilla.workhub.model.User;
import com.felipevilla.workhub.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);    
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("The email %s does not exist in the system", email));
        }

        User user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles()
        .stream().map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList()); 

        return new org.springframework.security.core.userdetails.User(user.getEmail(), 
        user.getPassword(), 
        true, 
        true, 
        true, 
        true, authorities); 

    }

}
