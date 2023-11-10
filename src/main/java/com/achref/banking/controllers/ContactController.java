package com.achref.banking.controllers;


import com.achref.banking.dto.AddressDto;
import com.achref.banking.dto.ContactDto;
import com.achref.banking.services.AddressService;
import com.achref.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody ContactDto contactDto
    ){
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.finAll());
    }

    @GetMapping("/users/{user-id}")
    public  ResponseEntity<List<ContactDto>> findAllByUserId(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }


    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDto> findById(
            @PathVariable("/{contact-id") Integer contactId
    ){
        return ResponseEntity.ok(service.findById(contactId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("contact-id") Integer contactId
    ){
        service.delete(contactId);
        return  ResponseEntity.accepted().build();
    }
}
