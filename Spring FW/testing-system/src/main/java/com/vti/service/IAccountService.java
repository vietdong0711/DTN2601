package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    Page<AccountDTO> findAll(Pageable pageable, AccountSearchForm form);

    AccountDTO findById(Integer id);

    void create(AccountCreateForm form);

    void update(Integer id, @Valid AccountCreateForm form);
}
