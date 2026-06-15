package com.myfinbank.workflow.repository;

import com.myfinbank.workflow.entity.LoanStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanStatusHistoryRepository extends JpaRepository<LoanStatusHistory, Long> {

    List<LoanStatusHistory> findByLoanId(Long loanId);
}