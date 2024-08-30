package com.devsu.bank.account_service.adapters.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.bank.account_service.adapters.dto.TransactionDTO;
import com.devsu.bank.account_service.domain.model.Transaction;
import com.devsu.bank.account_service.infraestructure.config.CommonSettings;

public class TransactionMapper {
    public static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setBalance(transaction.getBalance());
        transactionDTO.setTransactionType(transaction.getTransactionType().getName());
        transactionDTO
                .setCreatedDate(transaction.getCreatedAt().atZone(CommonSettings.TIME_ZONE).toLocalDate().toString());
        transactionDTO.setAccountId(transaction.getAccount().getId());
        transactionDTO.setId(transaction.getId());
        return transactionDTO;
    }

    public static List<TransactionDTO> toDTO(List<Transaction> transactions) {
        return transactions.stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }
}
