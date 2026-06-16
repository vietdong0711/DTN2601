package com.vti.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

public enum PositionName {
    DEV("D", "1"),TEST("T", "1"),SCRUM_MASTER("S", "0"),PM("PM", "0");
    private String name;
    private String status;

    private PositionName(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public static PositionName toEnum(String sqlValue) {// chuyển gtri lưu ở DB thành enum PositionName
//        for (PositionName name : PositionName.values()) {
//            if (name.getName().equals(sqlValue)) {
//                return name;
//            }
//        }
//        return null;
        PositionName positionName = Arrays.stream(PositionName.values())
                .filter(i -> i.getName().equals(sqlValue)).findFirst().orElse(null);
        return positionName;
    }
}
