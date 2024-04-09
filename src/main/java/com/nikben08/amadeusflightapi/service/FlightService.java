package com.nikben08.amadeusflightapi.service;

import com.nikben08.amadeusflightapi.dto.flight.FlightCreateRequestDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.model.Flight;
import com.nikben08.amadeusflightapi.repository.AirportRepository;
import com.nikben08.amadeusflightapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.nikben08.amadeusflightapi.dto.flight.FlightResponseDto;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public Iterable<FlightResponseDto> getAllFlights() {
        return flightRepository
                .findAll()
                .stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    public FlightResponseDto findFlightById(Long id) {
        Flight foundFlight = getFlightById(id);
        return modelMapper.map(foundFlight, FlightResponseDto.class);
    }

    private Flight getFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isEmpty()) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }
        return flight.get();
    }

    public FlightResponseDto createFlight(FlightCreateRequestDto flightCreateRequest){
        Flight flight = modelMapper.map(flightCreateRequest, Flight.class);
        Flight createdFlight = flightRepository.save(flight);
        return modelMapper.map(createdFlight, FlightResponseDto.class);
    }

    private Airport getAirportById(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isEmpty()) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }
        return airport.get();
    }

    private void updateFlightFromDto(Flight flight, FlightCreateRequestDto flightCreateDto) {
        if (flightCreateDto.getDepartureAirportId() != null) {
            Airport departureAirport = getAirportById(flightCreateDto.getDepartureAirportId());
            flight.setDepartureAirport(departureAirport);
        }
        if (flightCreateDto.getArrivalAirportId() != null) {
            Airport arrivalAirport = getAirportById(flightCreateDto.getArrivalAirportId());
            flight.setArrivalAirport(arrivalAirport);
        }
        if (flightCreateDto.getDepartureDate() != null) {
            flight.setDepartureDate(flightCreateDto.getDepartureDate());
        }
        if (flightCreateDto.getArrivalDate() != null) {
            flight.setArrivalDate(flightCreateDto.getArrivalDate());
        }
        if (flightCreateDto.getPrice() != null) {
            flight.setPrice(flightCreateDto.getPrice());
        }

        flight.setUpdatedAt(LocalDateTime.now());
    }

    public FlightResponseDto updateFlight(Long id, FlightCreateRequestDto flightCreateRequest) {
        Flight updatedFlight = getFlightById(id);
        updateFlightFromDto(updatedFlight, flightCreateRequest);

        updatedFlight = flightRepository.save(updatedFlight);
        return modelMapper.map(updatedFlight, FlightResponseDto.class);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new ResourceNotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }

}
