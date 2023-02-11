package com.sebastian.ems.service;

import com.sebastian.ems.model.EmployeeType;
import com.sebastian.ems.repository.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTypeServiceImpl implements ItemStorageService<EmployeeType> {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Override
    public List<EmployeeType> getAllItems() {
        return employeeTypeRepository.findAll();
    }

    @Override
    public void saveItem(EmployeeType item) {

    }

    @Override
    public EmployeeType findItemById(long id) {
        return null;
    }

    @Override
    public void deleteItemById(long id) {

    }

    @Override
    public Page<EmployeeType> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        return null;
    }
}
