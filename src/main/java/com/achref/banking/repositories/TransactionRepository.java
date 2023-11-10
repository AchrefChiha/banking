package com.achref.banking.repositories;
import com.achref.banking.dto.TransactionSumDetails;
import com.achref.banking.models.Transaction;
import com.achref.banking.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(t.amount) from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.transactionDate as transactionDate, sum(t.amount) from Transaction t where t.user.id = :userId and (t.creationDate between :start and :end) group by t.transactionDate")
    List<TransactionSumDetails> findSumTransactionByDate(LocalDateTime start, LocalDateTime end, Integer userId);
}

