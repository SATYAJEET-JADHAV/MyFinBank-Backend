package com.myfinbank.loan.repository;

import com.myfinbank.loan.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanDocumentRepository extends JpaRepository<LoanDocument, Long> {

    List<LoanDocument> findByLoanId(Long loanId);
}