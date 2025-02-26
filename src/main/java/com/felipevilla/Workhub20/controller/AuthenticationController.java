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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/workhub/auth")
@Tag(name = "Authentication", description = "API for user authentication and authorization management")
public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Operation(summary = "Register a new user", 
               description = "Creates a new user account with the provided credentials")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = AuthResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid registration data"),
        @ApiResponse(responseCode = "409", description = "Username already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthCreateUserRequest authCreateUser){
        return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @Operation(summary = "User login", 
               description = "Authenticates a user and returns a token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Authentication successful",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = AuthResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid login data"),
        @ApiResponse(responseCode = "401", description = "Authentication failed"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK); 
    }

    @Operation(summary = "Update user roles", 
               description = "Updates the roles assigned to a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User roles updated successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = AuthResponseUpdate.class))),
        @ApiResponse(responseCode = "400", description = "Invalid role update data"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "403", description = "Permission denied"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/updateRoles")
    public ResponseEntity<AuthResponseUpdate> updateUserRoles(@RequestBody AuthUpdateUserRolesRequest authUpdateUserRolesRequest){ 
        return new ResponseEntity<>(this.userDetailsService.updateUserRoles(authUpdateUserRolesRequest), HttpStatus.OK);
    
    }   
 
}
