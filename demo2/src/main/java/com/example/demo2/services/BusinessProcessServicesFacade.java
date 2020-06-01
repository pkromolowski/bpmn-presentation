package com.example.demo2.services;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusinessProcessServicesFacade {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public BusinessProcessServicesFacade(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    public void startProcessInstanceByProcessKey(String processKey) {
        startProcessInstanceByProcessKey(processKey, Map.of());
    }

    public void startProcessInstanceByProcessKey(String processKey, Map<String, Object> processVariables) {
        runtimeService.startProcessInstanceByKey(processKey, "testBusinessKey", processVariables);
    }

    public void correlateMessage(String message) {
        correlateMessage(message, Map.of());
    }

    public void correlateMessage(String message, Map<String, Object> processVariables) {
        runtimeService.correlateMessage(message, processVariables);
    }

    public void sendSignal(String signalName) {
        runtimeService.signalEventReceived(signalName);
    }

}
