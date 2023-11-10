package com.achref.banking.services;

import com.achref.banking.dto.TransactionDto;
import com.achref.banking.models.Transaction;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {
    List<TransactionDto> findAllByUserId(Integer userId);
}
