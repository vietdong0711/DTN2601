package org.example.backend.service;

import org.example.dto.ImportError;
import org.example.entity.Department;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ImportFile<T, E, K> {
    List<T> readFile(String path);// trả ra 1 list csv
    void validation(T t, E context, List<ImportError> importErrors, List<K> entities);// E là context, đưa vao map, list de check validation
    void saveAll(List<K> entities);
    void exportFileError(List<ImportError> importErrors, String pathError);

    //path: đường dẫn: D:\input_department.csv
    default  String importFile(String path, E context, String pathError){
        File file = new File(path);
        if (!file.exists()) {
            return "File này ko ko tồn tại";
        }
        if (!path.endsWith(".csv")) {
            return "File này ko đúng định";
        }
        List<T> csvs = readFile(path);// ds csv lấy ra từ file csv
        List<ImportError> importErrors = new ArrayList<>();// list bắt lỗi
        List<K> entities = new ArrayList<>();// list lưu vao DB
        for (T t : csvs) {
            validation(t, context, importErrors, entities);
        }
        // luu ds ko loi vào DB
        saveAll(entities);

        // xuat ra file loi
        exportFileError(importErrors, pathError);
        String message = "Đã import thành công " + entities.size() + ", thất bại " + importErrors.size();
        return message;
    }
}
