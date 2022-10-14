package com.example.api_5.controllers;

import com.example.api_5.entity.Contact;
import com.example.api_5.repository.ContactRepository;
import com.example.api_5.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    ContactService service;


    @GetMapping("/contacts")
    public ResponseEntity< List<Contact>> findAllContacts(){
        try {
            List<Contact> contacts = service.findContacts();
            if (contacts.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }catch (Exception e){
//           что то пошло не так
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findContact/{id}")
    public ResponseEntity<Contact> findContactById(@PathVariable(value = "id") Long id){
        try{
            Contact contact = service.findContactById(id);
            if (contact == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(contact, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addContact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact ){
        try{
            return new ResponseEntity<>(service.createOrUpdateContact(contact), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContactByName(@RequestBody Contact contact){
        try{
            return new ResponseEntity<>(service.createOrUpdateContact(contact), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Contact> deleteContactById(@PathVariable(value = "id") Long id){
        try {
            service.deleteContactByName(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
