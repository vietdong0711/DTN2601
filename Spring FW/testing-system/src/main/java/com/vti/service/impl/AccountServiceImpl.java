package com.vti.service.impl;

import com.vti.common.StringCommon;
import com.vti.config.CustomUserDetail;
import com.vti.config.JWTUtils;
import com.vti.dto.AccountDTO;
import com.vti.dto.ImportError;
import com.vti.dto.LoginDTO;
import com.vti.dto.context.AccountContext;
import com.vti.dto.csv.AccountCsv;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.Role;
import com.vti.exception.BusinessException;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.LoginForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IAccountService;
import com.vti.service.ImportFile;
import com.vti.specification.AccountCustomSpecification;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    private AuthenticationManager authenticationManager;// check user+ pass
    @Autowired
    private JWTUtils jwtUtils;// generate token
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private HttpServletResponse response;

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
        Page<AccountDTO> pageDTO = pageAccounts.map(account -> new AccountDTO(account));
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
            throw BusinessException.builder().message("Username da ton tai!")
                    .code("VALIDATION_ERROR").status(500).build();
        }
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setPassword(form.getPassword());
        // chuyen departmentId ở from -> department
        Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            throw BusinessException.builder().message("Department ID not found!").code("VALIDATION_ERROR").status(500).build();
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
        Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
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

    @Override
    public LoginDTO login(LoginForm form) {
        // check username + password
        Authentication authen = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

        // generate token
        String token = jwtUtils.generateToken(form.getUsername());
        return new LoginDTO(token);
    }

    @Override
    public String getProfile() {
        String username =  ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        return username;
    }

    @Override
    public String importCSV(MultipartFile file) {
        Map<String, Account> mapAccountByUsername = accountRepository.findAll()
                .stream().collect(Collectors.toMap(Account::getUsername, acc -> acc));
        Map<String, Account> mapAccountByEmail = accountRepository.findAll()
                .stream().collect(Collectors.toMap(Account::getEmail, acc -> acc));;
        List<Department> departments = departmentRepository.findAll();
        List<Position> positions = positionRepository.findAll();
        AccountContext context = new AccountContext(mapAccountByEmail, mapAccountByEmail, departments, positions);
        return this.importFile(file, context, response);
    }


    @Override
    public List<AccountCsv> readFile(MultipartFile file) {
        List<AccountCsv> csvs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1))) {
            String line = br.readLine();// bo di hang header
            while ((line = br.readLine()) != null) {
                // logic doc file
                String[] fileds = line.split(",", -1);
                String username = fileds[0];
                String email = fileds[1];
                String fullName = fileds[2];
                String departmentId = fileds[3];
                String positionId = fileds[4];
                AccountCsv accountCsv = new AccountCsv(username, fullName, email, departmentId, positionId);
                csvs.add(accountCsv);
            }

        } catch (Exception e) {
        }
        return csvs;
    }

    @Override
    public void validation(AccountCsv accountCsv, AccountContext context, List<ImportError> importErrors, List<Account> entities) {
        List<String> errors = new ArrayList<>();
        // validate username
        String username = accountCsv.getUsername();
        if (Objects.isNull(username) || username.trim().isEmpty()) {
            errors.add("Username không được để trống");
        } else if (context.getMapAccountByUsername().containsKey(username)) {
            errors.add("Username đã tồn tại");
        }

        // validation fullname
        String fullName = accountCsv.getFullName();
        if (Objects.isNull(fullName) || fullName.trim().isEmpty()) {
            errors.add("FullName không được để trống");
        }

        //validation email
        String email = accountCsv.getEmail();
        if (Objects.isNull(email) || email.trim().isEmpty()) {
            errors.add("Email không được để trống");
        } else if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email ko đúng định dạng");
        } else if (context.getMapAccountByEmail().containsKey(email)) {
            errors.add("Email đã tồn tại");
        }

        //validation departmentID
        String departmentId = accountCsv.getDepartmentID();
        if (Objects.isNull(departmentId) || departmentId.trim().isEmpty()) {
            errors.add("DepartmentId không được để trống");
        } else if (!departmentId.matches(StringCommon.NUMBER_REGEX) || Integer.parseInt(departmentId) <= 0) {
            errors.add("DepartmentId phải nhập số và phải lớn hơn 0");
        }
        boolean checkExistDepartment = false;
        Department department = null;// cho vao contructor account
        if (departmentId.matches(StringCommon.NUMBER_REGEX)) {
            for (Department de : context.getDepartments()) {
                if (de.getId() == Integer.parseInt(departmentId)) {
                    checkExistDepartment = true;
                    department = de;
                    break;
                }
            }
            if (!checkExistDepartment) {
                errors.add("DepartmentId không tồn tại");
            }
        }

        //validation positionID
        String positionId = accountCsv.getPositionID();
        if (Objects.isNull(positionId) || positionId.trim().isEmpty()) {
            errors.add("PositionID không được để trống ");
        } else if (!positionId.matches(StringCommon.NUMBER_REGEX) || Integer.parseInt(positionId) <= 0) {
            errors.add("PositionID phải nhập số và phải lớn hơn 0");
        }
        boolean checkExistPosition = false;
        Position position = null;
        if (positionId.matches(StringCommon.NUMBER_REGEX)) {
            for (Position po : context.getPositions()) {
                if (po.getId() == Integer.parseInt(positionId)) {
                    checkExistPosition = true;
                    position = po;
                    break;
                }
            }
            if (!checkExistPosition) {
                errors.add("PositionID không tồn tại");
            }
        }

        if (errors.isEmpty()) {
            Account acc = new Account(department, email, fullName, position, username);
            acc.setRole(Role.EMPLOYEE);
            acc.setPassword(bCryptPasswordEncoder.encode("123456"));
            entities.add(acc);
            context.getMapAccountByUsername().put(username, acc);
            context.getMapAccountByEmail().put(email, acc);
        } else {
            importErrors.add(new ImportError(accountCsv.toString(), String.join(" | ", errors)));
        }
    }

    @Override
    public void saveAll(List<Account> entities) {
        accountRepository.saveAll(entities);
    }

    @Override
    public void exportFileError(List<ImportError> importErrors, HttpServletResponse response) {
        // 1. Cấu hình HTTP Header để trình duyệt hiểu đây là file download
        String fileName = "import_errors.csv";
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
            // 2. Thêm Byte Order Mark (BOM) cho UTF-8 để Excel hiển thị đúng tiếng Việt
            bw.write('\ufeff');
            // 3. Ghi header của file CSV
            bw.write("username,full_name,email,departmentId,positionId,message_error");
            bw.newLine();
            // 4. Duyệt danh sách và ghi từng dòng dữ liệu
            if (importErrors != null) {
                for (ImportError error : importErrors) {
                    // Bạn nên lưu ý: Nếu dữ liệu chứa dấu phẩy (,), hãy escape nó hoặc xử lý nối chuỗi hợp lý.
                    // Giả định error.getLine() trả về chuỗi các field cách nhau bằng dấu phẩy
                    bw.write(error.getLine() + "," + error.getMessage());
                    bw.newLine();
                }
            }
            bw.flush();
        } catch (Exception e) {
            // Log lỗi cụ thể ra thay vì để trống catch block
            System.err.println("Lỗi xuất file CSV: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
