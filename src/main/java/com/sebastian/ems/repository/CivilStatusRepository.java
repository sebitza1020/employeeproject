package com.sebastian.ems.repository;

import com.sebastian.ems.model.CivilStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CivilStatusRepository extends JpaRepository<CivilStatus, Long> {
}
