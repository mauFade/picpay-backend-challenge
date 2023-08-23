package com.picpaychallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaychallenge.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {}
