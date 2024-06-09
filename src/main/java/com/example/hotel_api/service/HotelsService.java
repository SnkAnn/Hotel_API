package com.example.hotel_api.service;

import com.example.hotel_api.model.Amenities;
import com.example.hotel_api.model.Hotel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface HotelsService {
    List<Map<String, Object>> findAllHotels();

    Map<String, Object> saveHotel(Hotel hotel);

    ResponseEntity<Map<String, Object>> getHotelById(Long id);

    List<Map<String, Object>>searchHotels(String name,String brand,String city,String country,List<String> amenities);

    void addAmenitiesToHotel(Long id, List<Amenities> amenities);

    Map<String, Integer> getHistogram(String param);
}
