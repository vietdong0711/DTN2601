package org.example.dto.context;

import org.example.entity.Department;

import java.util.Map;

public class DepartmentContext {
    private Map<String, Department> mapByName;

    public DepartmentContext(Map<String, Department> mapByName) {
        this.mapByName = mapByName;
    }

    public Map<String, Department> getMapByName() {
        return mapByName;
    }

    public void setMapByName(Map<String, Department> mapByName) {
        this.mapByName = mapByName;
    }
}
