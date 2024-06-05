package com.nikben08.amadeusflightapi.service;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AmadeusFlightApiServiceTests {

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAirport() {
        AirportCreateRequestDto requestDto = new AirportCreateRequestDto();
        requestDto.setCity("New York");

        Airport airport = new Airport();
        AirportResponseDto responseDto = new AirportResponseDto();

        when(modelMapper.map(requestDto, Airport.class)).thenReturn(airport);
        when(modelMapper.map(airport, AirportResponseDto.class)).thenReturn(responseDto);
        when(airportRepository.save(airport)).thenReturn(airport);

        AirportResponseDto createdAirport = airportService.createAirport(requestDto);

        assertNotNull(createdAirport);
        verify(modelMapper, times(1)).map(requestDto, Airport.class);
        verify(modelMapper, times(1)).map(airport, AirportResponseDto.class);
        verify(airportRepository, times(1)).save(airport);
    }

}
