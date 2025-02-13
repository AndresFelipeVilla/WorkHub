package com.felipevilla.workhub.mapper;

import org.mapstruct.Mapper;


import com.felipevilla.workhub.model.Booking;
import com.felipevilla.workhub.model.dto.BookingDTO;

@Mapper(componentModel = "spring")  
public interface BookingMapper { 
    BookingDTO bookingToBookingDTO(Booking booking); 
    Booking bookingDTOToBooking(BookingDTO bookingDTO);
}




