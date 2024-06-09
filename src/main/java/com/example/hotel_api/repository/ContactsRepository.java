package com.example.hotel_api.repository;

import com.example.hotel_api.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts,Long> {
}
