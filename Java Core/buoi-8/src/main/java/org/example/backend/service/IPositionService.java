package org.example.backend.service;

import org.example.entity.Position;
import org.example.enums.PositionName;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();
    boolean create(PositionName name);
    boolean update(int id, PositionName name);
    boolean delete(int id);
}
