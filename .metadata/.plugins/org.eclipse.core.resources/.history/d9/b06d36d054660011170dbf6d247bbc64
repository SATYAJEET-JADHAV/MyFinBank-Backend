package com.myfinbank.loan.client;

import com.myfinbank.loan.dto.WorkflowTaskRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "workflow-service")
public interface WorkflowClient {

    @PostMapping("/workflow/tasks")
    void createWorkflowTask(WorkflowTaskRequest request);
}