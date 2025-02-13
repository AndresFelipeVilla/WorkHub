package com.felipevilla.workhub.model.dto;

import java.util.List;

import com.felipevilla.workhub.model.Booking;
import com.felipevilla.workhub.model.Spacetype;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpaceDTO {
    
    private Long id;
    private String name;
    private Integer capacity;
    private Boolean available; 
    private Spacetype spacetype;
    private List<Booking> bookings;
    
}
