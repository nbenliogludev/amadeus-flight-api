package com.nikben08.amadeusflightapi.controller;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<AirportResponseDto> getAllAirports() {
        Iterable<Airport> airports = airportService.getAirports();
        List<AirportResponseDto> airportResponseDtos = new ArrayList<>();
        for (Airport airport : airports) {
            AirportResponseDto dto = modelMapper.map(airport, AirportResponseDto.class);
            airportResponseDtos.add(dto);
        }
        return airportResponseDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponseDto> getAirport(@PathVariable Long id) {
        Airport foundAirport = airportService.getAirportById(id);
        AirportResponseDto response = modelMapper.map(foundAirport, AirportResponseDto.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AirportResponseDto> createAirport(@RequestBody AirportCreateRequestDto airportCreateRequest){
        Airport createdAirport = airportService.createAirport(airportCreateRequest);
        AirportResponseDto response = modelMapper.map(createdAirport, AirportResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponseDto> updateAirport(@PathVariable Long id, @RequestBody AirportCreateRequestDto airportCreateDto) {
        Airport updatedAirport = airportService.updateAirport(id, airportCreateDto);
        AirportResponseDto response = modelMapper.map(updatedAirport, AirportResponseDto.class);
        return ResponseEntity.ok(response);
    }

}
