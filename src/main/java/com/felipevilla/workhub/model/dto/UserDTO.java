package com.felipevilla.workhub.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felipevilla.workhub.model.Booking;
import com.felipevilla.workhub.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    
    private Long id;
    private String name; 
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<Role> roles;
    private List<Booking> bookings; 
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin; 
    
}
