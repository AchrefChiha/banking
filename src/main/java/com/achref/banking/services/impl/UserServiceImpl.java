package com.achref.banking.services.impl;

import com.achref.banking.dto.AccountDto;
import com.achref.banking.dto.UserDto;
import com.achref.banking.models.User;
import com.achref.banking.repositories.UserRepository;
import com.achref.banking.services.AccountService;
import com.achref.banking.services.UserService;
import com.achref.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;
    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        User savedUser = repository.save(user);
        return savedUser.getId();
    }

    @Override
    public List<UserDto> finAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No User was found with the provided ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(true);
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        repository.save(user);
        return user.getId();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
