package com.vti.validation.account;

import com.vti.repository.IAccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsernameNotExistValidator implements ConstraintValidator<UsernameNotExist, String> {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
//        if (username.trim().length() == 0)
        if (StringUtils.isEmpty(username)) {
            return true;
        }
        return !accountRepository.existsByUsername(username);
    }
}
