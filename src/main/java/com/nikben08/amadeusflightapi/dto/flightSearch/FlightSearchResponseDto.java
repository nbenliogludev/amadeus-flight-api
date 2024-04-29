package com.nikben08.amadeusflightapi.dto.flightSearch;

import com.nikben08.amadeusflightapi.dto.flight.FlightResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FlightSearchResponseDto {
    private List<FlightResponseDto> departureFlights;
    private List<FlightResponseDto> returnFlights;
}
