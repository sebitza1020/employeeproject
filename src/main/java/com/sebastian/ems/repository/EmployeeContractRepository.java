package com.sebastian.ems.repository;

import com.sebastian.ems.model.EmployeeContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeContractRepository extends JpaRepository<EmployeeContract, Long> {
}
