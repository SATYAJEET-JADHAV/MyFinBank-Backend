package com.myfinbank.workflow.service;

import com.myfinbank.workflow.dto.StatusHistoryResponse;
import com.myfinbank.workflow.dto.StatusUpdateRequest;
import com.myfinbank.workflow.dto.WorkflowTaskRequest;
import com.myfinbank.workflow.dto.WorkflowTaskResponse;

import java.util.List;

public interface WorkflowService {

    WorkflowTaskResponse createWorkflowTask(WorkflowTaskRequest request);

    List<WorkflowTaskResponse> getAllTasks();

    WorkflowTaskResponse getTaskById(Long taskId);

    List<WorkflowTaskResponse> getTasksByRole(String role);

    List<WorkflowTaskResponse> getTasksByStatus(String status);

    WorkflowTaskResponse reviewLoan(Long loanId, StatusUpdateRequest request);

    WorkflowTaskResponse approveLoan(Long loanId, StatusUpdateRequest request);

    WorkflowTaskResponse rejectLoan(Long loanId, StatusUpdateRequest request);

    WorkflowTaskResponse holdLoan(Long loanId, StatusUpdateRequest request);

    List<StatusHistoryResponse> getLoanHistory(Long loanId);
}