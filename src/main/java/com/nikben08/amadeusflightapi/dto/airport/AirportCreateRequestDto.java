package com.nikben08.amadeusflightapi.dto.airport;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AirportCreateRequestDto {
    @Column(nullable = false)
    private String city;
}
