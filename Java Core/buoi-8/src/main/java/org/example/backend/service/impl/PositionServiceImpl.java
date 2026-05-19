package org.example.backend.service.impl;

import org.example.backend.repository.IPositionRepository;
import org.example.backend.repository.impl.PositionRepositoryImpl;
import org.example.backend.service.IPositionService;
import org.example.entity.Position;
import org.example.enums.PositionName;

import java.util.List;

public class PositionServiceImpl implements IPositionService {
    // khoi tao positionRepository
    private IPositionRepository positionRepository = new PositionRepositoryImpl();

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public boolean create(PositionName name) {
        return positionRepository.create(name);
    }

    @Override
    public boolean update(int id, PositionName name) {
        return positionRepository.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return positionRepository.delete(id);
    }

    @Override
    public boolean checkExist(Integer id, PositionName name) {
        return positionRepository.checkExist(id, name);
    }

    @Override
    public boolean checkExistID(Integer id) {
        return positionRepository.checkExistID(id);
    }
}
