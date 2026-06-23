package com.vti.service.impl;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IAccountService;
import com.vti.specification.AccountCustomSpecification;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private IPositionRepository positionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AccountDTO> findAll(Pageable pageable, AccountSearchForm form) {
        Specification<Account> where = Specification.unrestricted();// where 1=1
        if (StringUtils.isNotEmpty(form.getUsername())) {
            AccountCustomSpecification username
                    = new AccountCustomSpecification("username", form.getUsername());
            where = where.and(username);
        }
        if (StringUtils.isNotEmpty(form.getEmail())) {
            AccountCustomSpecification email
                    = new AccountCustomSpecification("email", form.getEmail());
            where = where.and(email);
        }
        if (StringUtils.isNotEmpty(form.getFullName())) {
            AccountCustomSpecification fullName
                    = new AccountCustomSpecification("fullName", form.getFullName());
            where = where.and(fullName);
        }
        if (StringUtils.isNotEmpty(form.getDepartmentName())) {
            AccountCustomSpecification departmentName
                    = new AccountCustomSpecification("departmentName", form.getDepartmentName());
            where = where.and(departmentName);
        }
        if (StringUtils.isNotEmpty(form.getPositionName())) {
            AccountCustomSpecification positionName
                    = new AccountCustomSpecification("positionName", form.getPositionName());
            where = where.and(positionName);
        }
        Page<Account> pageAccounts = accountRepository.findAll(where, pageable);
        // page : content +

//        List<AccountDTO> dtos = new ArrayList<>();
//        for (Account acc: accounts) {
//            AccountDTO dto = modelMapper.map(acc, AccountDTO.class);
//            dtos.add(dto);
//        }
        Page<AccountDTO> pageDTO = pageAccounts.map( account -> new AccountDTO(account));
        return pageDTO;
    }

    @Override
    public AccountDTO findById(Integer id) {
        // tìm account theo id
        Account account = accountRepository.findById(id).orElse(null);
        if (Objects.isNull(account)) {
            throw new RuntimeException("ID not found");
        }
        // chuyen tu account -> accountDTO
        return new AccountDTO(account);
    }

    @Override
    @Transactional
    public void create(AccountCreateForm form) {
        // Form   ->   entity
        Account account = new Account();
        // validation email, username, .....
        account.setEmail(form.getEmail());
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), null)) {
            throw new RuntimeException("Username đã tồn tại");
        }
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setPassword(form.getPassword());
        // chuyen departmentId ở from -> department
        Department department= departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            throw new RuntimeException("Department ID not found!");
        }
        account.setDepartment(department);
        // chuyen positionId ở from -> position
        Position position = positionRepository.findById(form.getPositionId()).orElse(null);
        if (Objects.isNull(position)) {
            throw new RuntimeException("Position ID not found!");
        }
        account.setPosition(position);

        // lưu vào DB
        accountRepository.save(account);
    }

    @Override
    public void update(Integer id, AccountCreateForm form) {
        // tim account theo id
        Account account = accountRepository.findById(id).orElse(null);
        if (Objects.isNull(account)) {
            throw new RuntimeException("ID not found");
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), id)) {
            throw new RuntimeException("Email exist!");
        }
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), id)) {
            throw new RuntimeException("Username exist!");
        }

        // chuyen departmentId ở from -> department
        Department department= departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            throw new RuntimeException("Department ID not found!");
        }
        // chuyen positionId ở from -> position
        Position position = positionRepository.findById(form.getPositionId()).orElse(null);
        if (Objects.isNull(position)) {
            throw new RuntimeException("Position ID not found!");
        }
        account.setEmail(form.getEmail());
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setPassword(form.getPassword());
        account.setDepartment(department);
        account.setPosition(position);

        accountRepository.save(account);
    }
}
