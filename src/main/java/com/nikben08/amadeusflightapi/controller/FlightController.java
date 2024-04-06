package com.nikben08.amadeusflightapi.controller;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.dto.flight.FlightCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.flight.FlightResponseDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.model.Flight;
import com.nikben08.amadeusflightapi.repository.FlightRepository;
import com.nikben08.amadeusflightapi.service.AirportService;
import com.nikben08.amadeusflightapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final ModelMapper modelMapper;
    private final FlightRepository flightRepository;

    @PostMapping
    public ResponseEntity<FlightResponseDto> createFlight(@RequestBody FlightCreateRequestDto flightCreateRequest){
        Flight createdFlight = flightService.createFlight(flightCreateRequest);
        FlightResponseDto response = modelMapper.map(createdFlight, FlightResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<FlightResponseDto> getAllFlights() {
        Iterable<Flight> flights = flightService.getFlights();
        List<FlightResponseDto> flightResponseDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightResponseDto dto = modelMapper.map(flight, FlightResponseDto.class);
            flightResponseDtos.add(dto);
        }
        return flightResponseDtos;
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDto> updateFlight(@PathVariable Long id, @RequestBody FlightCreateRequestDto flightCreateRequest) {
        Flight updatedFlight = flightService.updateFlight(id, flightCreateRequest);
        FlightResponseDto response = modelMapper.map(updatedFlight, FlightResponseDto.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable Long id) {
        Flight foundFlight = flightService.getFlightById(id);
        FlightResponseDto response = modelMapper.map(foundFlight, FlightResponseDto.class);
        return ResponseEntity.ok(response);
    }



}
