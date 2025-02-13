package com.felipevilla.workhub.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_name")
    @NotNull(message = "The date cannot be empty")
    private LocalDate startDate; 

    @Column(name = "end_date")
    @NotNull(message = "The date cannot be empty")
    private LocalDate endDate;
    
    private Boolean cancelled;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Space.class)
    private Space space;


    
    
}
