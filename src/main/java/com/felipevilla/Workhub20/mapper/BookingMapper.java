package com.felipevilla.Workhub20.mapper;

import org.mapstruct.Mapper;

import com.felipevilla.Workhub20.dto.BookingDTO;
import com.felipevilla.Workhub20.model.BookingModel;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO bookingToBookingDTO(BookingModel booking);
    BookingModel bookingDTOToBooking(BookingDTO bookingDTO); 
}
