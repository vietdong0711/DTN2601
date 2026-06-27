package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.service.IAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Page<AccountDTO>> findAll(Pageable pageable , AccountSearchForm form) {
        Page<AccountDTO> accounts = accountService.findAll(pageable, form);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<AccountDTO> findById(@PathVariable(name = "id") Integer id) {
        AccountDTO dto = accountService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity<?> create(@RequestBody @Valid AccountCreateForm form) {
        accountService.create(form);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }

    // update  AccountUpdateForm
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id,
                                    @RequestBody @Valid AccountCreateForm form) {
        accountService.update(id, form);
        return new ResponseEntity<>("Update successfully", HttpStatus.CREATED);
    }

    // lấy ra thông tin đăng nhập hiện tại  username  ,lấy từ token
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return new ResponseEntity<>(accountService.getProfile(), HttpStatus.OK);
    }

    @PostMapping("/importCSV")
    public ResponseEntity<?> importCSV(@RequestParam(name = "file") MultipartFile file) {
        return new ResponseEntity<>(accountService.importCSV(file), HttpStatus.CREATED);
    }

}
