package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.dto.LoginDTO;
import com.vti.dto.context.AccountContext;
import com.vti.dto.csv.AccountCsv;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.LoginForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface IAccountService extends ImportFile<AccountCsv, AccountContext, Account> {
    Page<AccountDTO> findAll(Pageable pageable, AccountSearchForm form);

    AccountDTO findById(Integer id);

    void create(AccountCreateForm form);

    void update(Integer id, @Valid AccountCreateForm form);

    LoginDTO login(LoginForm form);

    String getProfile();

    String importCSV(MultipartFile file);
}
