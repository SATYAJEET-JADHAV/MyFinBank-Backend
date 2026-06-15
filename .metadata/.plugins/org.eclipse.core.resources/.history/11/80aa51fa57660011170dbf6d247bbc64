package com.myfinbank.workflow.repository;

import com.myfinbank.workflow.entity.WorkflowTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkflowTaskRepository extends JpaRepository<WorkflowTask, Long> {

    List<WorkflowTask> findByAssignedRole(String assignedRole);

    List<WorkflowTask> findByStatus(String status);

    List<WorkflowTask> findByLoanId(Long loanId);

    Optional<WorkflowTask> findTopByLoanIdOrderByTaskIdDesc(Long loanId);
}