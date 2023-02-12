package com.sebastian.ems.service;

import com.sebastian.ems.model.EmployeeContracts;
import com.sebastian.ems.repository.EmployeeContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeContractServiceImpl implements ItemStorageService<EmployeeContracts> {

    @Autowired
    private EmployeeContractRepository employeeContractRepository;

    @Override
    public List<EmployeeContracts> getAllItems() {
        return this.employeeContractRepository.findAll();
    }

    @Override
    public void saveItem(EmployeeContracts employeeContract) {
        this.employeeContractRepository.save(employeeContract);
    }

    @Override
    public EmployeeContracts findItemById(long id) {
        Optional<EmployeeContracts> optional = employeeContractRepository.findById(id);
        EmployeeContracts employeeContract;
        if (optional.isPresent()) {
            employeeContract = optional.get();
        } else {
            throw new RuntimeException(" EmployeeContract not found for id :: " + id);
        }
        return employeeContract;
    }

    @Override
    public void deleteItemById(long id) {
        this.employeeContractRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeContracts> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeContractRepository.findAll(pageable);
    }
}
