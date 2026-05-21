package org.example.dto;

public class ImportError {
    private String line;
    private String message;

    public ImportError(String line, String message) {
        this.line = line;
        this.message = message;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // tại sao ImportError(la class giong department) ko lưu vào entity
    // do chức năng ImportError  lưu lại các dữ liệu lỗi + message lỗi
    // entity : lưu cac đối tượng mapping với database

    // DTO : data transfer object (Đối tượng truyền dữ liệu)
    // khi mà import department, acc, position mà dữ liệu ko hợp lệ
    //      thì mình sẽ đưa thông tin của row đó, message lỗi vào ImportError
        // để xuất ra file
}
