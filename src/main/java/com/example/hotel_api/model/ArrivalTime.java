package com.example.hotel_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Locale;

@Data
@Entity
@Table(name ="arrival_Time")
public class ArrivalTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrival_time_SEQ")
    @SequenceGenerator(name = "arrival_time_SEQ", sequenceName = "arrival_time_SEQ", allocationSize = 1)
    private Long id;
   // @Column(name = "check_In")
    private LocalTime checkIn;
   // @Column(name = "check_Out")
    private LocalTime checkOut;


}
