package com.achref.banking.services.impl;

import com.achref.banking.dto.ContactDto;
import com.achref.banking.models.Contact;
import com.achref.banking.repositories.ContactRepository;
import com.achref.banking.services.ContactService;
import com.achref.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;
    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> finAll() {
        return repository.findAll().stream().map(ContactDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id).map(ContactDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("No Contact was found with the Id : " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId).stream().map(ContactDto::fromEntity).collect(Collectors.toList());
    }
}
