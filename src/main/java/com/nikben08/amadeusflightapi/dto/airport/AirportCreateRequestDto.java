package com.nikben08.amadeusflightapi.dto.airport;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportCreateRequestDto {
    @Column(nullable = false)
    private String city;
}
