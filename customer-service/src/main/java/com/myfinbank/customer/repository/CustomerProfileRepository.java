package com.myfinbank.customer.repository;

import com.myfinbank.customer.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {

    Optional<CustomerProfile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByPanNumber(String panNumber);

    boolean existsByAadhaarNumber(String aadhaarNumber);
}