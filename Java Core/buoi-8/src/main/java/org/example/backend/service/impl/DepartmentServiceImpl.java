package org.example.backend.service.impl;

import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.dto.ImportError;
import org.example.dto.context.DepartmentContext;
import org.example.dto.csv.DepartmentCsv;
import org.example.entity.Department;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DepartmentServiceImpl implements IDepartmentService {
    // khởi tạo repository
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();

    @Override
    public List<Department> findAll() {
        // che dấu đi thông tin (ẩn đi thông tin)
        return departmentRepository.findAll();// lấy dc ds từ repository
    }

    @Override
    public boolean create(String name) {
        return departmentRepository.create(name);
    }

    @Override
    public boolean update(int id, String name) {
        return departmentRepository.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return departmentRepository.delete(id);
    }

    @Override
    public boolean checkExistName(String name, Integer id) {
        return departmentRepository.checkExistName(name, id);
    }

    @Override
    public boolean checkExistId(Integer id) {
        return departmentRepository.checkExistId(id);
    }

    @Override
    public String importDepartmentFromCSV(String pathName) {
        Map<String, Department> mapDepartmentByName = departmentRepository.mapDepartmentByName();
        DepartmentContext context = new DepartmentContext(mapDepartmentByName);
        return this.importFile(pathName, context, "C:\\Users\\FPT\\Desktop\\DTN2601\\csv\\output_department.csv");
    }

    @Override
    public List<DepartmentCsv> readFile(String pathName) {
        List<DepartmentCsv> csvs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            String line = br.readLine();// bo di hang header
            while ((line = br.readLine()) != null) {
                // logic doc file
                String[] fileds = line.split(",", -1);
                DepartmentCsv departmentCsv = new DepartmentCsv(fileds[0]);
                csvs.add(departmentCsv);
            }

        } catch (Exception e) {
        }
        return csvs;
    }

    @Override
    public void validation(DepartmentCsv departmentCsv, DepartmentContext context, List<ImportError> importErrors, List<Department> entities) {
        List<String> errors = new ArrayList<>();// luu lai ds loi cua line này
        String departmentName = departmentCsv.getName();

        if (Objects.isNull(departmentName) || departmentName.trim().isEmpty()) {
            errors.add("Tên phòng ban không được để trống");
        } else if (context.getMapByName().containsKey(departmentName)) {
            errors.add("Tên phòng ban đã tồn tại");
        }

        if (errors.isEmpty()) {// nếu ko có lỗi thì thêm vào ds để lưu vào DB
            Department dep = new Department(departmentName);
            entities.add(dep);
            // moi khi có department hop le thi se them luôn vào map để check trùng các row sau
            Map<String, Department> map = context.getMapByName();
            map.put(departmentName, dep);
            context.setMapByName(map);
        } else {// có lỗi thì xuất ra
            importErrors.add(new ImportError(departmentName, String.join(" | ", errors)));
        }
    }

    @Override
    public void saveAll(List<Department> entities){
        departmentRepository.createDepartments(entities);
    }

    @Override
    public void exportFileError(List<ImportError> importErrors, String pathError) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathError))) {
            bw.write("depatment_name,message_error");
            bw.newLine();
            for (ImportError error : importErrors) {
                bw.write(error.getLine() + "," + error.getMessage());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //D:\input_department.csv
    // đọc file   +    lấy ra ds deprtment để gửi xuống repository de lưu lại
    // row nào gặp lỗi xuất ra file lỗi
    // row nào ko loi thì import bình thuwowfn
//    @Override
//    public String importDepartmentFromCSV(String pathName) {//pathName; đường dẫn đén file trong máy
//        File file = new File(pathName);
//        if (!file.exists()) {
//            return "File này ko ko tồn tại";
//        }
//
//        if (!pathName.endsWith(".csv")) {
//            return "File này ko đúng định";
//        }
//        List<Department> departments = new ArrayList<>();
//        List<ImportError> importErrors = new ArrayList<>();
//        Map<String, Department> mapDepartmentByName = departmentRepository.mapDepartmentByName();
//        boolean checkImport = false;
//        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
//            String line = br.readLine();// bo di hang header
//            while ((line = br.readLine()) != null) {
//                this.validation(line, departments, importErrors, mapDepartmentByName);
//            }
//
//            if (!departments.isEmpty()) {
//                checkImport = departmentRepository.createDepartments(departments);
//            }
//
//            if (!importErrors.isEmpty()) {
//                this.exportFileCSV(importErrors);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return checkImport && importErrors.isEmpty() ? "Import thành công" : "Import lỗi, đã xuất file ra D:\\output_department_error.csv";
//    }
//

    /// /    Set<String> departmentNames = new HashSet<>();//
//    // validate, thêm vào ds departments nếu ko có lỗi, thêm vào ds importErrors nếu có lỗi
//    public void validation(String line, List<Department> departments, List<ImportError> importErrors, Map<String, Department> mapDepartmentByName) {
//        String[] fields = line.split(",");
//        String departmentName = fields[0];
//        List<String> errors = new ArrayList<>();// luu lai ds loi cua line này
//        if (Objects.isNull(departmentName) || departmentName.trim().isEmpty()) {
//            errors.add("Tên phòng ban không được để trống");
//        }
//        if (mapDepartmentByName.containsKey(departmentName)) {
//            errors.add("Tên phòng ban đã tồn tại");
//        }
//
//        if (errors.isEmpty()) {// nếu ko có lỗi thì thêm vào ds để lưu vào DB
//            Department dep = new Department(departmentName);
//            departments.add(dep);
//            // moi khi có department hop le thi se them luôn vào map để check trùng các row sau
//            mapDepartmentByName.put(departmentName, dep);
//        } else {// có lỗi thì xuất ra
//            importErrors.add(new ImportError(line, String.join(" | ", errors)));
//        }
//    }
//
//    // xuat file
//    public void exportFileCSV(List<ImportError> importErrors) {
//        // xuất ra file lỗi list  importErrors  ra file csv  D:\output_department_error.csv
//        String pathError = "D:\\output_department_error.csv";
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathError))) {
//            bw.write("depatment_name,message_error");
//            bw.newLine();
//            for (ImportError error : importErrors) {
//                bw.write(error.getLine() + "," + error.getMessage());
//                bw.newLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        List<DepartmentCsv> departmentCsvs = departmentService.readFile("C:\\Users\\FPT\\Desktop\\DTN2601\\csv\\input_department.csv");
        System.out.println("");
    }
}
