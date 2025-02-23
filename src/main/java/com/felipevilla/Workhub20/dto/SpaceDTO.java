package com.felipevilla.Workhub20.dto;

import java.util.List;

import com.felipevilla.Workhub20.model.BookingModel;

import com.felipevilla.Workhub20.model.Enum.SpaceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceDTO {
    
    private Long id;
    private String name;
    private Integer capacity;
    private Boolean available; 
    private SpaceType spacetype;
    private List<BookingModel> bookings;

}
