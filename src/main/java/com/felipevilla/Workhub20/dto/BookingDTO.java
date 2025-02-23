package com.felipevilla.Workhub20.dto;

import java.time.LocalDate;

import com.felipevilla.Workhub20.model.SpaceModel;
import com.felipevilla.Workhub20.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean cancelled;
    private UserModel user;
    private SpaceModel space;
}
