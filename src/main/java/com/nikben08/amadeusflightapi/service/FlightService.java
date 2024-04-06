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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public Flight createFlight(FlightCreateRequestDto flightCreateRequest){
        Flight flight = modelMapper.map(flightCreateRequest, Flight.class);
        Flight createdFlight = flightRepository.save(flight);
        return createdFlight;
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

    public Flight updateFlight(Long id, FlightCreateRequestDto flightCreateRequest) {
        Flight updatedFlight = getFlightById(id);
        updateFlightFromDto(updatedFlight, flightCreateRequest);

        updatedFlight = flightRepository.save(updatedFlight);
        return updatedFlight;
    }

    public Iterable<Flight> getFlights() {
        return flightRepository
                .findAll()
                .stream()
                .toList();
    }

    public Flight getFlightById(Long id) {
        Optional<Flight> foundFlight = flightRepository.findById(id);

        if (foundFlight.isEmpty()) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }

        Flight flight = foundFlight.get();
        return flight;
    }


}
