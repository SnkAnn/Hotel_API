package com.example.hotel_api.repository;

import com.example.hotel_api.model.Address;
import com.example.hotel_api.model.Amenities;
import com.example.hotel_api.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByNameContainingIgnoreCase(String name);

    List<Hotel> findByBrandContainingIgnoreCase(String brand);

    List<Hotel> findByAddressIn(List<Address> addresses);


    List<Hotel> findByAmenitiesIn(List<Long> amenities);

    @Query("SELECT h.brand, COUNT(h) FROM Hotel h GROUP BY h.brand")
    List<Object[]> countHotelsByBrand();

    @Query("SELECT h.address.city, COUNT(h) FROM Hotel h GROUP BY h.address.city")
    List<Object[]> countHotelsByCity();

    @Query("SELECT h.address.country, COUNT(h) FROM Hotel h GROUP BY h.address.country")
    List<Object[]> countHotelsByCounty();

    @Query("SELECT a.name, COUNT(h) FROM Hotel h JOIN h.amenities a GROUP BY a.name")
    List<Object[]> countHotelsByAmenities();

}
