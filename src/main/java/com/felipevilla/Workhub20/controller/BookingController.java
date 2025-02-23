package com.felipevilla.Workhub20.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipevilla.Workhub20.dto.BookingDTO;
import com.felipevilla.Workhub20.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/workhub/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/save")
    public ResponseEntity<BookingDTO> saveBooking (@RequestBody BookingDTO bookingDTO) {
        BookingDTO booking = bookingService.save(bookingDTO);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> findById(@PathVariable Long id){
        BookingDTO booking = bookingService.findById(id);
        return ResponseEntity.ok(booking); 
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BookingDTO>> findAll(){
        List<BookingDTO> bookings = bookingService.findAll();
        return ResponseEntity.ok(bookings);  
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id,@Valid @RequestBody BookingDTO bookingDTO ) {
        BookingDTO booking = bookingService.updateBooking(id, bookingDTO);
        return ResponseEntity.ok(booking);
    }
  
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("The Booking was successfully deleted");
    }
}
