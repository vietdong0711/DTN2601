package com.vti.repository;

import com.vti.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    boolean existByUsernameAndIdNot(String username, Integer id);
    boolean existByEmailAndIdNot(String email, Integer id);
}
