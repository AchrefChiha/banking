package com.achref.banking.dto;

import com.achref.banking.models.Transaction;
import com.achref.banking.models.TransactionType;
import com.achref.banking.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id;

    @Positive
    private BigDecimal amount;

    private TransactionType type;

    private String destinationIban;

    private LocalDate transactionDate;

    private Integer userId;

    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .transactionDate(transaction.getTransactionDate())
                .userId(transaction.getUser().getId())
                .build();

    }

    public static Transaction toEntity(TransactionDto transaction){
        return Transaction.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(LocalDate.now())
                .destinationIban(transaction.getDestinationIban())
                .user(
                        User.builder()
                                .id(transaction.getUserId())
                                .build()
                )
                .build();

    }
}
