package com.sebastian.ems.service;

import com.sebastian.ems.model.EmployeeContract;
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
public class EmployeeContractServiceImpl implements EmployeeContractService {

    @Autowired
    private EmployeeContractRepository employeeContractRepository;

    @Override
    public List<EmployeeContract> getAllEmployeeContracts() {
        return this.employeeContractRepository.findAll();
    }

    @Override
    public void saveEmployeeContract(EmployeeContract employeeContract) {
        this.employeeContractRepository.save(employeeContract);
    }

    @Override
    public EmployeeContract findEmployeeContractById(long id) {
        Optional<EmployeeContract> optional = employeeContractRepository.findById(id);
        EmployeeContract employeeContract;
        if (optional.isPresent()) {
            employeeContract = optional.get();
        } else {
            throw new RuntimeException(" EmployeeContract not found for id :: " + id);
        }
        return employeeContract;
    }

    @Override
    public void deleteEmployeeContractById(long id) {
        this.employeeContractRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeContract> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeContractRepository.findAll(pageable);
    }
}
