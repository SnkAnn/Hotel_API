package com.example.hotel_api.repository;

import com.example.hotel_api.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCity(String city);

    List<Address> findByCountry(String country);
}
