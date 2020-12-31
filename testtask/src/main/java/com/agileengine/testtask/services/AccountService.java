package com.agileengine.testtask.services;

import com.agileengine.testtask.daos.AccountRepository;
import com.agileengine.testtask.daos.TransactionRepository;
import com.agileengine.testtask.models.Account;
import com.agileengine.testtask.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.RollbackException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.agileengine.testtask.models.Transaction.TransactionType.debit;
import static com.agileengine.testtask.utils.Utils.getEntityManager;

@Service
public class AccountService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity updateAccount(Double amount, Transaction.TransactionType transactionType) {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        Integer userId = 1;
        Account userAccount = entityManager.find(Account.class, userId, LockModeType.OPTIMISTIC);

        if (userAccount == null) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user account doesn't exists.");
        }
        if (debit.equals(transactionType) && userAccount.getBalance() < amount) {
        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Insufficient funds.");
        }

        userAccount.setBalance(transactionType, amount);

        try {
            entityManager.persist(userAccount);
            entityManager.getTransaction().commit();
        } catch (RollbackException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

        Transaction transaction = new Transaction(transactionType, amount);
        transaction.setUserId(userAccount.getUserId());
        transaction.setEffectiveDate(new Date());
        transaction.setId(UUID.randomUUID().toString());

        transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    public Optional<Account> getAccount(Integer id) {
        return accountRepository.findById(id);
    }

}
