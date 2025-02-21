package com.felipevilla.Workhub20.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userName", "message", "jwt", "status"})
public record AuthResponse(String userName, String message, String jwt, boolean status) {


}


