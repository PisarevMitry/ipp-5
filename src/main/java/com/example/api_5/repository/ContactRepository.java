package com.example.api_5.repository;

import com.example.api_5.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long > {
    public Contact findContactByName(String name);
    public Contact findContactById(Long id);
}
