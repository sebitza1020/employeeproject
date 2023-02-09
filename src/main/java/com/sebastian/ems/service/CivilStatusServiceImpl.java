package com.sebastian.ems.service;

import com.sebastian.ems.model.CivilStatus;
import com.sebastian.ems.repository.CivilStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public class CivilStatusServiceImpl implements ItemStorageService<CivilStatus> {

    @Autowired
    private CivilStatusRepository civilStatusRepository;

    @Override
    public List<CivilStatus> getAllItems() {
        return civilStatusRepository.findAll();
    }

    @Override
    public void saveItem(CivilStatus item) {

    }

    @Override
    public CivilStatus findItemById(long id) {
        return null;
    }

    @Override
    public void deleteItemById(long id) {

    }

    @Override
    public Page<CivilStatus> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        return null;
    }
}
