package com.example.hotel_api.repository;

import com.example.hotel_api.model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmenitiesRepository extends JpaRepository<Amenities,Long> {
    @Query("SELECT a.id FROM Amenities a WHERE a.name IN :name")
    List<Long> getIdByName(@Param("name") List<String> names);

}
