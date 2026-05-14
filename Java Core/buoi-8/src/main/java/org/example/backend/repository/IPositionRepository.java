package org.example.backend.repository;


import org.example.entity.Position;
import org.example.enums.PositionName;

import java.util.List;

public interface IPositionRepository {
    List<Position> findAll();
    boolean create(PositionName name);
    boolean update(int id, PositionName name);
    boolean delete(int id);
}
