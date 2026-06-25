package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.dto.LoginDTO;
import com.vti.form.LoginForm;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm form) {
        LoginDTO dto = accountService.login(form);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
