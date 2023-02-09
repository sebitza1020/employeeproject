package com.sebastian.ems.repository;

import com.sebastian.ems.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
