package com.vti.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ArticlePositionNameConverter implements AttributeConverter<PositionName, String> {
    @Override// chuyen tu enum -> string de luu vao DB
    public String convertToDatabaseColumn(PositionName attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getName();
    }

    @Override// chuyển từ String lay tu DB -> enum (Java)
    public PositionName convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return PositionName.toEnum(dbData);
    }
}
