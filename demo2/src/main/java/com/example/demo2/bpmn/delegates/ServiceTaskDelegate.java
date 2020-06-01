package com.example.demo2.bpmn.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.bpmn.behavior.ServiceTaskJavaDelegateActivityBehavior;

@Slf4j
public class ServiceTaskDelegate extends ServiceTaskJavaDelegateActivityBehavior {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Executing {} in thread {}, process variables:", execution.getCurrentActivityName(),  Thread.currentThread().getName());
        execution.getVariables().forEach((key, value) -> log.info("{} = {}", key, value));
    }
}
