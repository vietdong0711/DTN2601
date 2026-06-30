package com.vti.service;

import com.vti.dto.ImportError;
import com.vti.dto.csv.AccountCsv;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface ImportFile<T, E, K> {
    List<T> readFile(MultipartFile file);// trả ra 1 list csv
    void validation(T t, E context, List<AccountCsv> errorImports, List<K> entities);// E là context, đưa vao map, list de check validation
    void saveAll(List<K> entities);
//    void exportFileError(List<ImportError> importErrors, HttpServletResponse response);

    //path: đường dẫn: D:\input_department.csv
    default  String importFile(MultipartFile file, E context, List<AccountCsv> errorImports){
        String fileName = file.getOriginalFilename();

        if (!fileName.endsWith(".csv") || !file.getContentType().equals("text/csv")) {
            return "File này ko đúng định";
        }
        List<T> csvs = readFile(file);// ds csv lấy ra từ file csv
        List<K> entities = new ArrayList<>();// list lưu vao DB
        for (T t : csvs) {
            validation(t, context, errorImports, entities);
        }
        // luu ds ko loi vào DB
        saveAll(entities);

        String message = "Đã import thành công " + entities.size() + ", thất bại " + errorImports.size();
        return message;
    }
}
