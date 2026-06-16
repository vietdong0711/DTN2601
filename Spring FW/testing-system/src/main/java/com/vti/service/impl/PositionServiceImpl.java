package com.vti.service.impl;

import com.vti.entity.Position;
import com.vti.repository.IPositionRepository;
import com.vti.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position create(Position position) {
        positionRepository.save(position);
        return position;
    }
}
