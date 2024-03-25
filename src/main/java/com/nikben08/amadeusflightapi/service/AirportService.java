package com.nikben08.amadeusflightapi.service;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public AirportResponseDto createAirport(AirportCreateRequestDto airportCreateRequest){
        Airport airport = modelMapper.map(airportCreateRequest, Airport.class);
        airport = airportRepository.save(airport);
        return modelMapper.map(airport, AirportResponseDto.class);
    }
}
