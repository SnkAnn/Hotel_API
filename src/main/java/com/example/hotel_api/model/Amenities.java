package com.example.hotel_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name ="amenities")
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "amenities_SEQ")
    @SequenceGenerator(name = "amenities_SEQ", sequenceName = "amenities_SEQ", allocationSize = 1)
    private Long id;
    private String name;


}
