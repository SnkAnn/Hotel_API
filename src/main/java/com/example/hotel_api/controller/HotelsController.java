package com.example.hotel_api.controller;

import com.example.hotel_api.model.Amenities;
import com.example.hotel_api.model.Hotel;
import com.example.hotel_api.service.HotelsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@AllArgsConstructor
public class HotelsController {

    private final HotelsService hotelsService;

    @GetMapping("/hotels")
    public List<Map<String, Object>>  getAllHotels(){

        return hotelsService.findAllHotels();
    }
@GetMapping("/hotels/{id}")
    public ResponseEntity<Map<String, Object>> getHotelById(@PathVariable Long id){

        return hotelsService.getHotelById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>>searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> amenities) {

        List<Map<String, Object>> hotels = hotelsService.searchHotels(name, brand, city, country, amenities);
        return ResponseEntity.ok(hotels);
    }

    @PostMapping("/hotels")
    public Map<String, Object> createHotel(@RequestBody Hotel hotel){
        return  hotelsService.saveHotel(hotel);
    }

    @PostMapping("/hotels/{id}/amenities")
    public void addAmenitiesToHotel(@PathVariable Long id, @RequestBody List <Amenities> amenities){
        hotelsService.addAmenitiesToHotel(id,amenities);
    }
    @GetMapping("/histogram/{param}")
    public Map<String,Integer> getHistogram(@PathVariable String param){//вроде работает
        return hotelsService.getHistogram( param);
    }
}
