package com.example.hotel_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_SEQ")
    @SequenceGenerator(name = "contacts_SEQ", sequenceName = "contacts_SEQ", allocationSize = 1)
    private Long id;
    private String phone;
    private String email;
}
