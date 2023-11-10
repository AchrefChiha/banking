package com.achref.banking.services;

import com.achref.banking.dto.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<TransactionSumDetails> findSumTransactionsByDate(LocalDate startDate, LocalDate endDate, Integer userId);

    BigDecimal getAccountBalance(Integer userId);
    BigDecimal highestTransfer(Integer userId);
    BigDecimal highestDeposit(Integer userId);


}
