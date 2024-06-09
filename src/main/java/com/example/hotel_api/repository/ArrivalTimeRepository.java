package com.example.hotel_api.repository;

import com.example.hotel_api.model.ArrivalTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalTimeRepository extends JpaRepository<ArrivalTime,Long> {
}
