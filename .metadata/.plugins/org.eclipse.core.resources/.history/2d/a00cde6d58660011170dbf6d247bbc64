package com.myfinbank.workflow.controller;

import com.myfinbank.workflow.dto.StatusUpdateRequest;
import com.myfinbank.workflow.dto.WorkflowTaskRequest;
import com.myfinbank.workflow.service.WorkflowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createWorkflowTask(@Valid @RequestBody WorkflowTaskRequest request) {
        return new ResponseEntity<>(workflowService.createWorkflowTask(request), HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(workflowService.getAllTasks());
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskId) {
        return ResponseEntity.ok(workflowService.getTaskById(taskId));
    }

    @GetMapping("/tasks/role/{role}")
    public ResponseEntity<?> getTasksByRole(@PathVariable String role) {
        return ResponseEntity.ok(workflowService.getTasksByRole(role));
    }

    @GetMapping("/tasks/status/{status}")
    public ResponseEntity<?> getTasksByStatus(@PathVariable String status) {
        return ResponseEntity.ok(workflowService.getTasksByStatus(status));
    }

    @PutMapping("/review/{loanId}")
    public ResponseEntity<?> reviewLoan(
            @PathVariable Long loanId,
            @Valid @RequestBody StatusUpdateRequest request
    ) {
        return ResponseEntity.ok(workflowService.reviewLoan(loanId, request));
    }

    @PutMapping("/approve/{loanId}")
    public ResponseEntity<?> approveLoan(
            @PathVariable Long loanId,
            @Valid @RequestBody StatusUpdateRequest request
    ) {
        return ResponseEntity.ok(workflowService.approveLoan(loanId, request));
    }

    @PutMapping("/reject/{loanId}")
    public ResponseEntity<?> rejectLoan(
            @PathVariable Long loanId,
            @Valid @RequestBody StatusUpdateRequest request
    ) {
        return ResponseEntity.ok(workflowService.rejectLoan(loanId, request));
    }

    @PutMapping("/hold/{loanId}")
    public ResponseEntity<?> holdLoan(
            @PathVariable Long loanId,
            @Valid @RequestBody StatusUpdateRequest request
    ) {
        return ResponseEntity.ok(workflowService.holdLoan(loanId, request));
    }

    @GetMapping("/history/{loanId}")
    public ResponseEntity<?> getLoanHistory(@PathVariable Long loanId) {
        return ResponseEntity.ok(workflowService.getLoanHistory(loanId));
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Workflow Service is running");
    }
}