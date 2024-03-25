package com.nikben08.amadeusflightapi.dto.airport;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AirportResponseDto {
    private Long id;
    private String city;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
