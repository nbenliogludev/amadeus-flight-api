package com.nikben08.amadeusflightapi.service;

import com.nikben08.amadeusflightapi.dto.airport.AirportCreateRequestDto;
import com.nikben08.amadeusflightapi.dto.airport.AirportResponseDto;
import com.nikben08.amadeusflightapi.model.Airport;
import com.nikben08.amadeusflightapi.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

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

    @Test
    public void testGetAirports(){
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();
        List<Airport> airportsList = List.of(airport1, airport2);

        AirportResponseDto responseDto1 = new AirportResponseDto();
        AirportResponseDto responseDto2 = new AirportResponseDto();

        when(airportRepository.findAll()).thenReturn(airportsList);
        when(modelMapper.map(airport1, AirportResponseDto.class)).thenReturn(responseDto1);
        when(modelMapper.map(airport2, AirportResponseDto.class)).thenReturn(responseDto2);

        List<AirportResponseDto> result = airportService.getAirports();

        assertEquals(2, result.size());
        assertEquals(responseDto1, result.get(0));
        assertEquals(responseDto2, result.get(1));
        verify(airportRepository).findAll();
        verify(modelMapper, times(2)).map(any(Airport.class), eq(AirportResponseDto.class));
    }
}
