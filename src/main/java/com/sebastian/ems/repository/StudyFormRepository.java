package com.sebastian.ems.repository;

import com.sebastian.ems.model.StudyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyFormRepository extends JpaRepository<StudyForm, Long> {
}
