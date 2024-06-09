package com.example.hotel_api.service.Impl;

import com.example.hotel_api.model.*;
import com.example.hotel_api.repository.AddressRepository;
import com.example.hotel_api.repository.AmenitiesRepository;
import com.example.hotel_api.repository.HotelRepository;
import com.example.hotel_api.service.HotelsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class HotelsServiceImpl implements HotelsService {

    private final HotelRepository repository;
    private final AmenitiesRepository amenitiesRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Map<String, Object>> findAllHotels() {
        return convertHotelToBriefInf(repository.findAll());
    }


    @Override
    public Map<String, Object> saveHotel(Hotel hotel) {
        List<Hotel> arr=new ArrayList<>();
        arr.add(repository.save(hotel));
        return convertHotelToBriefInf(arr).get(0);
    }


    @Override
    public ResponseEntity<Map<String, Object>> getHotelById(Long id) {
        return repository.findById(id)
                .map(hotel -> {
                    Map<String, Object> response = new LinkedHashMap<>();
                    response.put("id", hotel.getId());
                    response.put("name", hotel.getName());
                    response.put("brand", hotel.getBrand());

                    Map<String, Object> addressMap = new LinkedHashMap<>();
                    Address address = hotel.getAddress();
                    addressMap.put("houseNumber", address.getHouseNumber());
                    addressMap.put("street", address.getStreet());
                    addressMap.put("city", address.getCity());
                    addressMap.put("country", address.getCountry());
                    addressMap.put("postCode", address.getPostCode());
                    response.put("address", addressMap);

                    Map<String, Object> contactsMap = new LinkedHashMap<>();
                    Contacts contacts = hotel.getContacts();
                    contactsMap.put("phone", contacts.getPhone());
                    contactsMap.put("email", contacts.getEmail());
                    response.put("contacts", contactsMap);

                    Map<String, Object> arrivalTimeMap = new LinkedHashMap<>();
                    ArrivalTime arrivalTime = hotel.getArrivalTime();
                    arrivalTimeMap.put("checkIn", arrivalTime.getCheckIn());
                    arrivalTimeMap.put("checkOut", arrivalTime.getCheckOut());
                    response.put("arrivalTime", arrivalTimeMap);

                    List<String> amenitiesList = hotel.getAmenities().stream()
                            .map(Amenities::getName)
                            .collect(Collectors.toList());
                    response.put("amenities", amenitiesList);

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @Override
    public List<Map<String, Object>> searchHotels(String name, String brand, String city, String country, List<String> amenities) {
        if (name != null) {
            return convertHotelToBriefInf(repository.findByNameContainingIgnoreCase(name));
        }
        if (brand != null) {
            return convertHotelToBriefInf(repository.findByBrandContainingIgnoreCase(brand));
        }
        if (city != null) {

            return convertHotelToBriefInf(repository.findByAddressIn(addressRepository.findByCity(city)));
        }
        if (country != null) {

            return convertHotelToBriefInf(repository.findByAddressIn(addressRepository.findByCountry(country)));
        }
        if (amenities != null && !amenities.isEmpty()) {
            return convertHotelToBriefInf(repository.findByAmenitiesIn(amenitiesRepository.getIdByName(amenities)));
        }
        return convertHotelToBriefInf(repository.findAll());

    }

    private static List<Map<String, Object>> convertHotelToBriefInf(List<Hotel> hotels) {
        List<Map<String, Object>> mapHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            Map<String, Object> hotelMap = new LinkedHashMap<>();
            hotelMap.put("id", hotel.getId());
            hotelMap.put("name", hotel.getName());
            hotelMap.put("description", hotel.getDescription());

            String address = String.format("%s, %s, %s, %s, %s",
                    hotel.getAddress().getHouseNumber(),
                    hotel.getAddress().getStreet(),
                    hotel.getAddress().getCity(),
                    hotel.getAddress().getPostCode(),
                    hotel.getAddress().getCountry());
            hotelMap.put("address", address);
            hotelMap.put("phone", hotel.getContacts().getPhone());
            mapHotels.add(hotelMap);
        }
        return mapHotels;
    }

    @Override
    public void addAmenitiesToHotel(Long id, List<Amenities> amenities) {
        Optional<Hotel> optionalHotel = repository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            List<Amenities> existingAmenities = hotel.getAmenities();

            for (Amenities amenity : amenities) {
                if (amenity.getId() == null) {
                    amenitiesRepository.save(amenity);
                }
            }
            existingAmenities.addAll(amenities);
            hotel.setAmenities(existingAmenities);
            repository.save(hotel);
        } else {
            throw new IllegalArgumentException("Hotel with id " + id + " not found.");
        }
    }


    @Override
    public Map<String, Integer> getHistogram(String param) {
        List<Object[]> results;
        switch (param) {
            case "brand":
                results = repository.countHotelsByBrand();
                break;
            case "city":
                results = repository.countHotelsByCity();
                break;
            case "country":
                results = repository.countHotelsByCounty();
                break;
            case "amenities":
                results = repository.countHotelsByAmenities();
                break;
            default:
                throw new IllegalArgumentException("Invalid parameter: " + param);
        }

        Map<String, Integer> histogram = new HashMap<>();
        for (Object[] result : results) {
            histogram.put((String) result[0], ((Number) result[1]).intValue());
        }
        return histogram;
    }


}
