package com.example.Boards.Models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


import java.util.HashMap;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<Integer, String>, String> {

    private static final String SEPARATOR = ";";

    @Override
    public String convertToDatabaseColumn(Map<Integer, String> attribute) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : attribute.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(SEPARATOR);
        }
        return sb.toString();
    }

    @Override
    public Map<Integer, String> convertToEntityAttribute(String dbData) {
        Map<Integer, String> attribute = new HashMap<>();
        if (dbData != null && !dbData.isEmpty()) {
            String[] entries = dbData.split(SEPARATOR);
            for (String entry : entries) {
                String[] parts = entry.split(":");
                if (parts.length == 2) {
                    try {
                        int key = Integer.parseInt(parts[0]);
                        attribute.put(key, parts[1]);
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }
        return attribute;
    }
}
