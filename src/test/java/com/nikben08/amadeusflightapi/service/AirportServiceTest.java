package com.nikben08.amadeusflightapi.service;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AirportServiceTest {

    private AirportService airportService;
    private AirportRepository airportRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        airportRepository = mock(AirportRepository.class);
        modelMapper = mock(ModelMapper.class);
        airportService = new AirportService(airportRepository, modelMapper);
    }

    @Test
    public void testCreateAirport() {
        AirportCreateRequestDto requestDto = AirportCreateRequestDto.builder()
                .city("Istanbul")
                .build();

        Airport airport = new Airport();
        Airport savedAirport = new Airport();
        AirportResponseDto responseDto = new AirportResponseDto();

        when(modelMapper.map(requestDto, Airport.class)).thenReturn(airport);
        when(airportRepository.save(airport)).thenReturn(savedAirport);
        when(modelMapper.map(savedAirport, AirportResponseDto.class)).thenReturn(responseDto);

        AirportResponseDto expectedResponse = airportService.createAirport(requestDto);

        assertEquals(responseDto, expectedResponse);
        verify(airportRepository).save(airport);
    }
}
