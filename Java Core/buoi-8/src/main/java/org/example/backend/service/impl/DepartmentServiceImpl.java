package org.example.backend.service.impl;

import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.dto.ImportError;
import org.example.entity.Department;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    // đọc file   +    lấy ra ds deprtment để gửi xuống repository de lưu lại
    // row nào gặp lỗi xuất ra file lỗi
    // row nào ko loi thì import bình thuwowfn
    @Override
    public String importDepartmentToCSV(String pathName) {//pathName; đường dẫn đén file trong máy
        // FileReader đọc file từ đường dẫn, đọc từng chữ cái
        //BufferedReader đọc theo từng dòng
        List<Department> departments = new ArrayList<>();
        List<ImportError> importErrors = new ArrayList<>();
        boolean firstLine = true;
        boolean checkImport = false;
        String message = "";
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {

//            String line = br.readLine();// đang đọc 1 dòng
            String line;
            while ((line = br.readLine()) != null) {
                List<String> errors = new ArrayList<>();
                // bor qua dòng đầu tien vi day là header
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                String departmentName = fields[0];
                // validate dữ lieu
                if (Objects.isNull(departmentName) || departmentName.trim().isEmpty()) {
                    // Tên phòng ban không được để trống
                    errors.add("Tên phòng ban không được để trống");
                }
                // check xem tên phòng ban có bị trùng hay ko
                if (departmentRepository.checkExistName(departmentName, null)) {
                    // Tên phòng ban đã tồn tại
                    errors.add("Tên phòng ban đã tồn tại");
                }
                if (errors.isEmpty()) {// nếu ko có lỗi thì thêm vào ds để lưu vào DB
                    Department dep = new Department(departmentName);
                    departments.add(dep);
                } else {// có lỗi thì xuất ra
                    importErrors.add(new ImportError(line, String.join(" | ", errors)));
                }
            }
            // xuất ra file lỗi list  importErrors  ra file csv  D:\output_department_error.csv
            String pathError = "D:\\output_department_error.csv";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathError))) {
                bw.write("depatment_name,message_error");
                bw.newLine();
                for (ImportError error : importErrors) {
                    bw.write(error.getLine() + "," + error.getMessage());
                    bw.newLine();
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }


            // insert vào DB
            if (!departments.isEmpty()) {
                checkImport = departmentRepository.createDepartments(departments);
            }
        } catch (Exception e) {
//            message = "Import lỗi " + e.getMessage();
        }

        return checkImport ? "Import thành công" : "Import lỗi, đã xuất file ra D:\\output_department_error.csv";
    }


//    public static void main(String[] args) {
//        List<String> strings = new ArrayList<>();
//        strings.add("1");
//        strings.add("2");
//        strings.add("3");
//        System.out.println(String.join(" | ", strings));
//    }
}
