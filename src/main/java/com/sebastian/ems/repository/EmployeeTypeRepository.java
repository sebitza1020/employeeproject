package com.sebastian.ems.repository;

import com.sebastian.ems.model.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {
}
