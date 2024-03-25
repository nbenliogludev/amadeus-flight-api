package com.nikben08.amadeusflightapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="airports")
@Getter
@Setter
public class Airport extends BaseModel {
    @Column(nullable = false)
    private String city;
}
