package com.nikben08.amadeusflightapi.controller;

import com.nikben08.amadeusflightapi.dto.flightSearch.FlightSearchCriteriaDto;
import com.nikben08.amadeusflightapi.dto.flightSearch.FlightSearchResponseDto;
import com.nikben08.amadeusflightapi.service.FlightSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flights/search")
@RequiredArgsConstructor
@Tag(name = "Flight Search", description = "Search for flights")
public class FlightSearchController {
    private final FlightSearchService flightSearchService;

    @Operation(
            description = "Search for flights",
            // longer summary
            summary = "Search for flights, you must provide departure airport, arrival airport and departure date. Optionally you can specify return date for round trip.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved flights"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
            }
    )
    @GetMapping
    public FlightSearchResponseDto searchFlights(@ModelAttribute FlightSearchCriteriaDto searchCriteria) {
        return flightSearchService.searchFlights(searchCriteria);
    }
}
