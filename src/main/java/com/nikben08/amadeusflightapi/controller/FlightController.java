package com.nikben08.amadeusflightapi.controller;

import com.nikben08.amadeusflightapi.dto.flight.FlightCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.flight.FlightResponseDto;
import com.nikben08.amadeusflightapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponseDto> createFlight(@RequestBody FlightCreateRequestDto flightCreateRequest){
        FlightResponseDto createdFlight = flightService.createFlight(flightCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDto> updateFlight(@PathVariable Long id, @RequestBody FlightCreateRequestDto flightCreateRequest) {
        FlightResponseDto updatedFlight = flightService.updateFlight(id, flightCreateRequest);
        return ResponseEntity.ok(updatedFlight);
    }

    @GetMapping
    public ResponseEntity<Iterable<FlightResponseDto>> getAllFlights() {
        Iterable<FlightResponseDto> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable Long id) {
        FlightResponseDto foundFlight = flightService.findFlightById(id);
        return ResponseEntity.ok(foundFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

}
