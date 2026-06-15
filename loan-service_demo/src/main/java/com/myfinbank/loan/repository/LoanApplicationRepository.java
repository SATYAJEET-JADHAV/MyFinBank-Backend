package com.myfinbank.loan.repository;

import com.myfinbank.loan.entity.LoanApplication;
import com.myfinbank.loan.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByCustomerId(Long customerId);

    List<LoanApplication> findByStatus(LoanStatus status);
}