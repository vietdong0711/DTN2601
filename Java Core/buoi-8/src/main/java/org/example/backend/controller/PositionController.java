package org.example.backend.controller;

import org.example.backend.service.IPositionService;
import org.example.backend.service.impl.PositionServiceImpl;
import org.example.entity.Position;
import org.example.enums.PositionName;

import java.util.List;

public class PositionController {
    // khoi tao positionService
    private IPositionService positionService = new PositionServiceImpl();

    public List<Position> findAll() {
        return positionService.findAll();
    }

    public boolean create(PositionName name) {
        return positionService.create(name);
    }

    public boolean update(int id, PositionName name) {
        return positionService.update(id, name);
    }

    public boolean delete(int id) {
        return positionService.delete(id);
    }
}
