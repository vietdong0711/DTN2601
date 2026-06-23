package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.dto.LoginDTO;
import com.vti.form.LoginForm;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("/login")
    public ResponseEntity<?> login(LoginForm form) {
        LoginDTO dto = accountService.login(form);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
