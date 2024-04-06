package com.nikben08.amadeusflightapi.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.nikben08.amadeusflightapi.repository.AirportRepository;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(AirportRepository airportRepository) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper;
    }
}
