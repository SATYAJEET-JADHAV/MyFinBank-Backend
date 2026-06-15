package com.myfinbank.workflow.service;

import com.myfinbank.workflow.client.LoanClient;
import com.myfinbank.workflow.dto.*;
import com.myfinbank.workflow.entity.LoanStatusHistory;
import com.myfinbank.workflow.entity.WorkflowTask;
import com.myfinbank.workflow.exception.InvalidWorkflowActionException;
import com.myfinbank.workflow.exception.WorkflowTaskNotFoundException;
import com.myfinbank.workflow.repository.LoanStatusHistoryRepository;
import com.myfinbank.workflow.repository.WorkflowTaskRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowTaskRepository workflowTaskRepository;
    private final LoanStatusHistoryRepository loanStatusHistoryRepository;
    private final LoanClient loanClient;

    public WorkflowServiceImpl(
            WorkflowTaskRepository workflowTaskRepository,
            LoanStatusHistoryRepository loanStatusHistoryRepository,
            LoanClient loanClient
    ) {
        this.workflowTaskRepository = workflowTaskRepository;
        this.loanStatusHistoryRepository = loanStatusHistoryRepository;
        this.loanClient = loanClient;
    }

    @Override
    public WorkflowTaskResponse createWorkflowTask(WorkflowTaskRequest request) {

        WorkflowTask task = new WorkflowTask();
        task.setLoanId(request.getLoanId());
        task.setCustomerId(request.getCustomerId());
        task.setAssignedRole(request.getAssignedRole().toUpperCase());
        task.setCurrentStage(request.getCurrentStage().toUpperCase());
        task.setStatus(request.getStatus().toUpperCase());

        WorkflowTask savedTask = workflowTaskRepository.save(task);

        saveHistory(
                savedTask.getLoanId(),
                null,
                savedTask.getStatus(),
                savedTask.getCustomerId()
        );

        return mapTaskToResponse(savedTask);
    }

    @Override
    public List<WorkflowTaskResponse> getAllTasks() {
        return workflowTaskRepository.findAll()
                .stream()
                .map(this::mapTaskToResponse)
                .toList();
    }

    @Override
    public WorkflowTaskResponse getTaskById(Long taskId) {

        WorkflowTask task = workflowTaskRepository.findById(taskId)
                .orElseThrow(() -> new WorkflowTaskNotFoundException("Workflow task not found with ID: " + taskId));

        return mapTaskToResponse(task);
    }

    @Override
    public List<WorkflowTaskResponse> getTasksByRole(String role) {
        return workflowTaskRepository.findByAssignedRole(role.toUpperCase())
                .stream()
                .map(this::mapTaskToResponse)
                .toList();
    }

    @Override
    public List<WorkflowTaskResponse> getTasksByStatus(String status) {
        return workflowTaskRepository.findByStatus(status.toUpperCase())
                .stream()
                .map(this::mapTaskToResponse)
                .toList();
    }

    @Override
    public WorkflowTaskResponse reviewLoan(Long loanId, StatusUpdateRequest request) {
        return updateWorkflow(
                loanId,
                request,
                "UNDER_REVIEW",
                "MANAGER",
                "FINAL_APPROVAL"
        );
    }

    @Override
    public WorkflowTaskResponse approveLoan(Long loanId, StatusUpdateRequest request) {
        return updateWorkflow(
                loanId,
                request,
                "APPROVED",
                "MANAGER",
                "COMPLETED"
        );
    }

    @Override
    public WorkflowTaskResponse rejectLoan(Long loanId, StatusUpdateRequest request) {
        return updateWorkflow(
                loanId,
                request,
                "REJECTED",
                "MANAGER",
                "COMPLETED"
        );
    }

    @Override
    public WorkflowTaskResponse holdLoan(Long loanId, StatusUpdateRequest request) {
        return updateWorkflow(
                loanId,
                request,
                "ON_HOLD",
                "MANAGER",
                "ABEYANCE"
        );
    }

    @Override
    public List<StatusHistoryResponse> getLoanHistory(Long loanId) {
        return loanStatusHistoryRepository.findByLoanId(loanId)
                .stream()
                .map(this::mapHistoryToResponse)
                .toList();
    }

    private WorkflowTaskResponse updateWorkflow(
            Long loanId,
            StatusUpdateRequest request,
            String finalStatus,
            String assignedRole,
            String nextStage
    ) {

        WorkflowTask task = workflowTaskRepository.findTopByLoanIdOrderByTaskIdDesc(loanId)
                .orElseThrow(() -> new WorkflowTaskNotFoundException("No workflow task found for loan ID: " + loanId));

        String previousStatus = task.getStatus();

        String requestedStatus = request.getNewStatus();

        if (requestedStatus != null && !requestedStatus.isBlank()) {
            requestedStatus = requestedStatus.toUpperCase();

            if (!requestedStatus.equals(finalStatus)) {
                throw new InvalidWorkflowActionException(
                        "Invalid status for this action. Expected: " + finalStatus
                );
            }
        }

        task.setStatus(finalStatus);
        task.setAssignedRole(assignedRole);
        task.setCurrentStage(nextStage);

        WorkflowTask updatedTask = workflowTaskRepository.save(task);

        updateLoanServiceStatusSafely(loanId, finalStatus);

        saveHistory(
                loanId,
                previousStatus,
                finalStatus,
                request.getChangedBy()
        );

        return mapTaskToResponse(updatedTask);
    }

    private void updateLoanServiceStatusSafely(Long loanId, String status) {
        try {
            loanClient.updateLoanStatus(loanId, new LoanStatusUpdateRequest(status));
        } catch (FeignException exception) {
            System.out.println("Loan service unavailable. Workflow updated but loan status sync failed.");
        } catch (Exception exception) {
            System.out.println("Loan status sync failed: " + exception.getMessage());
        }
    }

    private void saveHistory(
            Long loanId,
            String previousStatus,
            String newStatus,
            Long changedBy
    ) {
        LoanStatusHistory history = new LoanStatusHistory();
        history.setLoanId(loanId);
        history.setPreviousStatus(previousStatus);
        history.setNewStatus(newStatus);
        history.setChangedBy(changedBy);

        loanStatusHistoryRepository.save(history);
    }

    private WorkflowTaskResponse mapTaskToResponse(WorkflowTask task) {
        return new WorkflowTaskResponse(
                task.getTaskId(),
                task.getLoanId(),
                task.getCustomerId(),
                task.getAssignedRole(),
                task.getCurrentStage(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    private StatusHistoryResponse mapHistoryToResponse(LoanStatusHistory history) {
        return new StatusHistoryResponse(
                history.getHistoryId(),
                history.getLoanId(),
                history.getPreviousStatus(),
                history.getNewStatus(),
                history.getChangedBy(),
                history.getChangedAt()
        );
    }
}