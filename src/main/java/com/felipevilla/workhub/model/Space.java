package com.felipevilla.workhub.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "spaces")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Space {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @NotEmpty
    private String name;
    private Integer capacity;
    private Boolean available; 
    
    @Enumerated(EnumType.STRING) 
    private Spacetype spacetype;

    @OneToMany(targetEntity = Booking.class, mappedBy = "space")
    private List<Booking> bookings;



}
