package com.agileengine.testtask.daos;

import com.agileengine.testtask.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
