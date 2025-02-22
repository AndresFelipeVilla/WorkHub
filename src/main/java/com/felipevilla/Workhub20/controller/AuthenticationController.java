package com.felipevilla.Workhub20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipevilla.Workhub20.dto.AuthCreateUserRequest;
import com.felipevilla.Workhub20.dto.AuthLoginRequest;
import com.felipevilla.Workhub20.dto.AuthResponse;
import com.felipevilla.Workhub20.dto.AuthResponseUpdate;
import com.felipevilla.Workhub20.dto.AuthUpdateUserRolesRequest;
import com.felipevilla.Workhub20.service.UserDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/workhub/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthCreateUserRequest authCreateUser){
        return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK); 
    }

    @PatchMapping("/updateRoles")
    public ResponseEntity<AuthResponseUpdate> updateUserRoles(@RequestBody AuthUpdateUserRolesRequest authUpdateUserRolesRequest){ 
        return new ResponseEntity<>(this.userDetailsService.updateUserRoles(authUpdateUserRolesRequest), HttpStatus.OK);
    
    }   
 
}
