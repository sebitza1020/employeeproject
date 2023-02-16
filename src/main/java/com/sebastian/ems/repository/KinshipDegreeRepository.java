package com.sebastian.ems.repository;

import com.sebastian.ems.model.KinshipDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KinshipDegreeRepository extends JpaRepository<KinshipDegree, Long> {
}
