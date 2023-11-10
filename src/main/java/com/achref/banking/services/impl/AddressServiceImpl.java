package com.achref.banking.services.impl;

import com.achref.banking.dto.AddressDto;
import com.achref.banking.models.Address;
import com.achref.banking.repositories.AddressRepository;
import com.achref.banking.services.AddressService;
import com.achref.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final ObjectsValidator<AddressDto> validator;
    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);

        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> finAll() {
        return repository.findAll().stream().map(AddressDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id).map(AddressDto::fromEntity).orElseThrow(() ->  new EntityNotFoundException("No Address was found with the Id : " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
