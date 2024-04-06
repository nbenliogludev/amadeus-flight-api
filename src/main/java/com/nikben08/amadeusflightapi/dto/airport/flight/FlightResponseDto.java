package com.nikben08.amadeusflightapi.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightResponseDto {
    private Long id;

    private Long departureAirportId;
    private Long arrivalAirportId;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Double price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
