package com.felipevilla.Workhub20.service;

import java.util.List;

import com.felipevilla.Workhub20.dto.BookingDTO;
import com.felipevilla.Workhub20.error.ResourceNotFoundException;

public interface IBookingService {

    public BookingDTO save(BookingDTO bookingDTO);

    public BookingDTO findById(Long id) throws ResourceNotFoundException;

    public List<BookingDTO> findAll(); 

    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) throws ResourceNotFoundException;

    public void deleteBooking(Long id) throws ResourceNotFoundException; ;

}
