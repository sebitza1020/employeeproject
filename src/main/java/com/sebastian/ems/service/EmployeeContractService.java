package com.sebastian.ems.service;

import com.sebastian.ems.model.EmployeeContract;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeContractService {
    List<EmployeeContract> getAllEmployeeContracts();
    void saveEmployeeContract(EmployeeContract employeeContract);
    EmployeeContract findEmployeeContractById(long id);
    void deleteEmployeeContractById(long id);
    Page<EmployeeContract> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
