package com.felipevilla.Workhub20.dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;

@Validated
public record AuthCreateRoleRequest(@Size (max = 2, message = "The uiser cannot have more than 3 roles") List<String> roleListName) {}
