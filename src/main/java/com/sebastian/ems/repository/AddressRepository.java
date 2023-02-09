package com.sebastian.ems.repository;

import com.sebastian.ems.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
