package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.dto.LoginDTO;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.LoginForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

public interface IAccountService extends UserDetailsService {
    Page<AccountDTO> findAll(Pageable pageable, AccountSearchForm form);

    AccountDTO findById(Integer id);

    void create(AccountCreateForm form);

    void update(Integer id, @Valid AccountCreateForm form);

    LoginDTO login(LoginForm form);
}
