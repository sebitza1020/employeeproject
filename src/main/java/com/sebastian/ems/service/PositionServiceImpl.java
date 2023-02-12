package com.sebastian.ems.service;

import com.sebastian.ems.model.Position;
import com.sebastian.ems.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements ItemStorageService<Position>{

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAllItems() {
        return positionRepository.findAll();
    }

    @Override
    public void saveItem(Position positions) {
        this.positionRepository.save(positions);
    }

    @Override
    public Position findItemById(long id) {
        Optional<Position> optional = positionRepository.findById(id);
        Position position;
        if (optional.isPresent()) {
            position = optional.get();
        } else {
            throw new RuntimeException(" Position not found for id :: " + id);
        }
        return position;
    }

    @Override
    public void deleteItemById(long id) {
        this.positionRepository.deleteById(id);
    }

    @Override
    public Page<Position> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.positionRepository.findAll(pageable);
    }
}
