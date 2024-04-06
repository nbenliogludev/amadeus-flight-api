package com.nikben08.amadeusflightapi.service;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public Airport createAirport(AirportCreateRequestDto airportCreateRequest){
        Airport airport = modelMapper.map(airportCreateRequest, Airport.class);
        airport = airportRepository.save(airport);
        return airport;
    }

    public Airport updateAirport(Long id, AirportCreateRequestDto airportCreateDto) {
        Optional<Airport> foundAirport = airportRepository.findById(id);

        if (foundAirport.isEmpty()) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }

        Airport airport = foundAirport.get();
        airport.setCity(airportCreateDto.getCity());
        airport = airportRepository.save(airport);
        return airport;
    }

    public Iterable<Airport> getAirports() {
        return airportRepository
                .findAll()
                .stream()
                .map(airport -> modelMapper.map(airport, Airport.class))
                .toList();
    }

    public Airport getAirportById(Long id) {
        Optional<Airport> foundAirport = airportRepository.findById(id);

        if (foundAirport.isEmpty()) {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }

        Airport airport = foundAirport.get();
        return airport;
    }

}
