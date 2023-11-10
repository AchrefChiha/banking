package com.achref.banking.services.impl;

import com.achref.banking.dto.AccountDto;
import com.achref.banking.exceptions.OperationNonPermittedException;
import com.achref.banking.models.Account;
import com.achref.banking.repositories.AccountRepository;
import com.achref.banking.services.AccountService;
import com.achref.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;
    @Override
    public Integer save(AccountDto dto) {

        if(dto.getId() != null){
            throw new OperationNonPermittedException(
              "Account cannot be updated",
              "save account",
              "Account",
              "Update not permitted"
            );
        }
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = repository.findByUserId((account.getUser().getId())).isPresent();
        if(userHasAlreadyAnAccount && account.getUser().isActive()){
            throw new OperationNonPermittedException(
                    "the selected user has already an active account",
                    "create account",
                    "Account service",
                    "Account creation"
            );
        }
        if (dto.getId() == null){
            account.setIban(generateRandomIban());
        }
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> finAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList()
                );
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id).map(AccountDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("No Account was found with the Id : " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private String generateRandomIban(){

        String iban = Iban.random(CountryCode.DE).toFormattedString();

        boolean ibanExists = repository.findByIban(iban).isPresent();

        if (ibanExists){
            iban = generateRandomIban();
        }
        return iban;
    }
}
