package com.agileengine.testtask.daos;

import com.agileengine.testtask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
