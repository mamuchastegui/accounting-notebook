package com.agileengine.testtask.daos;

import com.agileengine.testtask.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
