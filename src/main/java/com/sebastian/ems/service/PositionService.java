package com.sebastian.ems.service;

import com.sebastian.ems.model.Position;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PositionService {
    List<Position> getAllPositions();

    void savePosition(Position positions);

    Position findPositionById(long id);

    void deletePositionById(long id);

    Page<Position> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
