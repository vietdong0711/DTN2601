package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<AccountDTO> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable(name = "id") Integer id) {
        AccountDTO dto = accountService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AccountCreateForm form) {
        accountService.create(form);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }

    // update  AccountUpdateForm
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody @Valid AccountCreateForm form) {
        accountService.update(id, form);
        return new ResponseEntity<>("Update successfully", HttpStatus.CREATED);
    }

}
