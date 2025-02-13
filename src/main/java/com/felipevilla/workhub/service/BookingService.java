package com.felipevilla.workhub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.felipevilla.workhub.error.BookingNotFoundException;
import com.felipevilla.workhub.mapper.BookingMapper;
import com.felipevilla.workhub.model.Booking;
import com.felipevilla.workhub.model.dto.BookingDTO;
import com.felipevilla.workhub.repository.BookingRepository;

@Service
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.bookingDTOToBooking(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.bookingToBookingDTO(savedBooking);
    }

    @Override
    public BookingDTO findById(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id)
                        .orElseThrow(()-> new BookingNotFoundException("Booking not found with id: " + id)); 
        return bookingMapper.bookingToBookingDTO(booking);
    }

    @Override
    public List<BookingDTO> findAll() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
        .map(bookingMapper::bookingToBookingDTO)
        .toList(); 
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) throws BookingNotFoundException {
        Booking existingBooking = bookingRepository.findById(id)
                    .orElseThrow(()-> new BookingNotFoundException("Booking not found with id: " + id));

        existingBooking.setStartDate(bookingDTO.getStartDate());
        existingBooking.setEndDate(bookingDTO.getEndDate());
        existingBooking.setCancelled(bookingDTO.getCancelled());
        existingBooking.setUser(bookingDTO.getUser());
        existingBooking.setSpace(bookingDTO.getSpace());

        Booking updateBooking = bookingRepository.save(existingBooking);

        return bookingMapper.bookingToBookingDTO(updateBooking);
    }

    @Override   
    public void deleteBooking(Long id) throws BookingNotFoundException {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }

}
