package com.nikben08.amadeusflightapi.controller;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportResponseDto> createAirport(@RequestBody AirportCreateRequestDto airportCreateRequest){
        AirportResponseDto createdAirport = airportService.createAirport(airportCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirport);
    }
}
