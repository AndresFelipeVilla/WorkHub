package com.felipevilla.Workhub20.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.felipevilla.Workhub20.dto.BookingDTO;
import com.felipevilla.Workhub20.error.ResourceNotFoundException;
import com.felipevilla.Workhub20.mapper.BookingMapper;
import com.felipevilla.Workhub20.model.BookingModel;
import com.felipevilla.Workhub20.repository.BookingRepository;

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
        BookingModel booking = bookingMapper.bookingDTOToBooking(bookingDTO);
        BookingModel savedBooking = bookingRepository.save(booking);
        return bookingMapper.bookingToBookingDTO(savedBooking);
    }

    @Override
    public BookingDTO findById(Long id) throws ResourceNotFoundException {
        BookingModel booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id: " + id));
        return bookingMapper.bookingToBookingDTO(booking);
    }

    @Override
    public List<BookingDTO> findAll() {
        List<BookingModel> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::bookingToBookingDTO).toList();
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) throws ResourceNotFoundException {
        BookingModel exisBookingModel = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id: " + id));

        exisBookingModel.setStartDate(bookingDTO.getStartDate());
        exisBookingModel.setEndDate(bookingDTO.getEndDate());
        exisBookingModel.setCancelled(bookingDTO.getCancelled());
        exisBookingModel.setUser(bookingDTO.getUser());
        exisBookingModel.setSpace(bookingDTO.getSpace());

        BookingModel updatedBookingModel = bookingRepository.save(exisBookingModel);

        return bookingMapper.bookingToBookingDTO(updatedBookingModel);
    }

    @Override
    public void deleteBooking(Long id) throws ResourceNotFoundException {
        BookingModel bookingModel = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id: " + id));
        bookingRepository.delete(bookingModel);
    }

}
