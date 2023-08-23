package com.picpaychallenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaychallenge.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
  Optional<Transaction> findTransactionById(String id);

  Optional<Transaction> findTransactionBySender(String senderId);

  Optional<Transaction> findTransactionByRecceiver(String receiverId);
}
