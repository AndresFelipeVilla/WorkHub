package com.felipevilla.Workhub20.dto;


import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username, @NotBlank String password) {}  