package com.felipevilla.workhub.service;

import java.util.List;

import com.felipevilla.workhub.error.BookingNotFoundException;
import com.felipevilla.workhub.model.dto.BookingDTO;

public interface IBookingService {

    public BookingDTO save(BookingDTO bookingDTO);

    public BookingDTO findById(Long id) throws BookingNotFoundException;

    public List<BookingDTO> findAll(); 

    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) throws BookingNotFoundException;

    public void deleteBooking(Long id) throws BookingNotFoundException;

}
