package com.nikben08.amadeusflightapi.controller;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @GetMapping
    public List<AirportResponseDto> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponseDto> getAirportById(@PathVariable Long id) {
        AirportResponseDto foundAirport = airportService.getAirportById(id);
        return ResponseEntity.ok(foundAirport);
    }

    @PostMapping
    public ResponseEntity<AirportResponseDto> createAirport(@RequestBody AirportCreateRequestDto airportCreateRequest){
        AirportResponseDto createdAirport = airportService.createAirport(airportCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponseDto> updateAirport(@PathVariable Long id, @RequestBody AirportCreateRequestDto airportCreateDto) {
        AirportResponseDto updatedAirport = airportService.updateAirport(id, airportCreateDto);
        return ResponseEntity.ok(updatedAirport);
    }
}
