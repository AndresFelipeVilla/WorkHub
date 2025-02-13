package com.felipevilla.workhub.model.dto;

import java.time.LocalDate;

import com.felipevilla.workhub.model.Space;
import com.felipevilla.workhub.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    
    private Long id;
    private LocalDate startDate; 
    private LocalDate endDate;
    private Boolean cancelled;
    private User user;
    private Space space;
}
