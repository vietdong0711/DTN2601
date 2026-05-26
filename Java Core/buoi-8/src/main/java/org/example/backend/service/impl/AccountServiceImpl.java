package org.example.backend.service.impl;

import org.example.backend.repository.IAccountRepository;
import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.IPositionRepository;
import org.example.backend.repository.impl.AccountRepositoryImpl;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.repository.impl.PositionRepositoryImpl;
import org.example.backend.service.IAccountService;
import org.example.backend.service.IDepartmentService;
import org.example.common.StringCommon;
import org.example.dto.ImportError;
import org.example.entity.Account;
import org.example.entity.Department;
import org.example.entity.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccountServiceImpl implements IAccountService {
    // khoi tao accountRepository
    private IAccountRepository accountRepository = new AccountRepositoryImpl();
    private IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private IPositionRepository positionRepository = new PositionRepositoryImpl();
    private static final String PATH_ERROR = "D:\\output_account_error.csv";


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(String email, String username, String fullName, int departmentID, int positionID) {
        // logic validation
        return accountRepository.create(email, username, fullName, departmentID, positionID);
    }

    @Override
    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId) {
        return accountRepository.update(id, updateName, email, username, departmentId, positionId);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public Map<String, Account> mapAccountByUsername() {
        return accountRepository.mapAccountByUsername();
    }

    @Override
    public boolean checkUsernameAndIdNot(String username, Integer id) {
        return accountRepository.checkUsernameAndIdNot(username, id);
    }

    @Override
    public boolean checkEmail(String email) {
        return accountRepository.checkEmail(email);
    }

    @Override
    public boolean checkId(Integer id) {
        return accountRepository.checkId(id);
    }

    @Override
    public boolean update(Integer id, String username) {
        return accountRepository.update(id, username);
    }

    @Override
    public String importAccountFromCSV(String pathName) {
        File file = new File(pathName);
        if (!file.exists()) {
            return "File này ko ko tồn tại";
        }

        if (!pathName.endsWith(".csv")) {
            return "File ko đúng định dạng!";
        }
        List<Account> accounts = new ArrayList<>();
        List<ImportError> importErrors = new ArrayList<>();
        boolean firstLine = true;
        boolean checkImport = false;
        String header = "";
        Map<String, Account> mapAccountByUsername = accountRepository.mapAccountByUsername();
        Map<String, Account> mapAccountByEmail = accountRepository.mapAccountByEmail();
        List<Department> departments = departmentRepository.findAll();// kiem tra xem departmentID import vao co ton tai hay ko
        List<Position> positions = positionRepository.findAll();
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            header = br.readLine();// bo di dong header
            String line;
            while ((line = br.readLine()) != null) {
                // validation + them vao ds accounts neu du lieu hop le, them vao importErrors nếu du lieu ko hop le
                this.validate(line, importErrors, accounts, mapAccountByUsername, mapAccountByEmail, departments, positions);
            }
            // insert vào DB
            if (!accounts.isEmpty()) {
                checkImport = accountRepository.createAccounts(accounts);
            }
            // xuất ra file lỗi list  importErrors  ra file csv  D:\output_department_error.csv
            this.exportAccountErrorToCSV(header, PATH_ERROR, importErrors);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkImport && importErrors.isEmpty() ? "Import thành công" : "Import lỗi, đã xuất file ra " + PATH_ERROR;
    }

    public void validate(String line, List<ImportError> importErrors, List<Account> accounts, Map<String, Account> mapAccountByUsername, Map<String, Account> mapAccountByEmail, List<Department> departments, List<Position> positions) {
        String[] fields = line.split(",", -1);
        List<String> errors = new ArrayList<>();
        String username = fields[0];
        String fullName = fields[1];
        String email = fields[2];
        String departmentId = fields[3];
        String positionId = fields[4];

        // validate username
        if (Objects.isNull(username) || username.trim().isEmpty()) {
            errors.add("Username không được để trống");
        } else if (mapAccountByUsername.containsKey(username)) {
            errors.add("Username đã tồn tại");
        }

        // validation fullname
        if (Objects.isNull(fullName) || fullName.trim().isEmpty()) {
            errors.add("FullName không được để trống");
        }

        //validation email
        if (Objects.isNull(email) || email.trim().isEmpty()) {
            errors.add("Email không được để trống");
        } else if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email ko đúng định dạng");
        } else if (mapAccountByEmail.containsKey(email)) {
            errors.add("Email đã tồn tại");
        }

        //validation departmentID
        if (Objects.isNull(departmentId) || departmentId.trim().isEmpty()) {
            errors.add("DepartmentId không được để trống");
        } else if (!departmentId.matches(StringCommon.NUMBER_REGEX) || Integer.parseInt(departmentId) <= 0) {
            errors.add("DepartmentId phải nhập số và phải lớn hơn 0");
        }
        boolean checkExistDepartment = false;
        Department department = null;// cho vao contructor account
        if (departmentId.matches(StringCommon.NUMBER_REGEX)) {
            for (Department de : departments) {
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
        if (Objects.isNull(positionId) || positionId.trim().isEmpty()) {
            errors.add("PositionID không được để trống ");
        } else if (!positionId.matches(StringCommon.NUMBER_REGEX) || Integer.parseInt(positionId) <= 0) {
            errors.add("PositionID phải nhập số và phải lớn hơn 0");
        }
        boolean checkExistPosition = false;
        Position position = null;
        if (positionId.matches(StringCommon.NUMBER_REGEX)) {
            for (Position po : positions) {
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
            Account acc = new Account(username, fullName, email, department, position);
            accounts.add(acc);
            mapAccountByUsername.put(username, acc);
            mapAccountByEmail.put(email, acc);
        } else {
            importErrors.add(new ImportError(line, String.join(" | ", errors)));
        }
    }

    public void exportAccountErrorToCSV(String header, String pathName, List<ImportError> importErrors) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName))) {
            bw.write(header + ",message_error");
            bw.newLine();
            for (ImportError error : importErrors) {
                bw.write(error.getLine() + "," + error.getMessage());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //D:\input_account.csv
}
