package com.example.hotel_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_SEQ", allocationSize = 1)

    private Long id;
   // @Column(name = "house_number")
    private String houseNumber;
    private String street;
    private String city;
    private String country;
   // @Column(name = "post_code")
    private int postCode;
}
