package com.devsu.bank.account_service.mapper;

import com.devsu.bank.account_service.config.CommonSettings;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.model.Transaction;

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
}
