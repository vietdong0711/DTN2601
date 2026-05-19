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
}
