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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/workhub/bookings")
@Tag(name = "Booking Management", description = "API for managing workspace bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @Operation(summary = "Create a new booking", 
               description = "Saves a new workspace booking with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking created successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = BookingDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid booking data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/save")
    public ResponseEntity<BookingDTO> saveBooking (@RequestBody BookingDTO bookingDTO) {
        BookingDTO booking = bookingService.save(bookingDTO);
        return ResponseEntity.ok(booking);
    }

    @Operation(summary = "Find booking by ID", 
               description = "Retrieves the details of a specific booking by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking found",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = BookingDTO.class))),
        @ApiResponse(responseCode = "404", description = "Booking not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> findById(@PathVariable Long id){
        BookingDTO booking = bookingService.findById(id);
        return ResponseEntity.ok(booking); 
    }

    @Operation(summary = "List all bookings", 
               description = "Retrieves a list of all available bookings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of bookings retrieved successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<BookingDTO>> findAll(){
        List<BookingDTO> bookings = bookingService.findAll();
        return ResponseEntity.ok(bookings);  
    }
    
    @Operation(summary = "Update a booking", 
               description = "Updates an existing booking with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking updated successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = BookingDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid update data"),
        @ApiResponse(responseCode = "404", description = "Booking not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("update/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id,@Valid @RequestBody BookingDTO bookingDTO ) {
        BookingDTO booking = bookingService.updateBooking(id, bookingDTO);
        return ResponseEntity.ok(booking);
    }
  
    @Operation(summary = "Delete a booking", 
    description = "Deletes an existing booking by its ID")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Booking not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("The Booking was successfully deleted");
    }
}
