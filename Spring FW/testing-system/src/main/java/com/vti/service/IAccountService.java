package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.form.AccountCreateForm;
import jakarta.validation.Valid;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();

    AccountDTO findById(Integer id);

    void create(AccountCreateForm form);

    void update(Integer id, @Valid AccountCreateForm form);
}
