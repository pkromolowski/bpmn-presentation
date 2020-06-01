package com.example.demo2.bpmn.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.bpmn.behavior.ServiceTaskJavaDelegateActivityBehavior;

@Slf4j
public class SendMessageDelegate extends ServiceTaskJavaDelegateActivityBehavior {

    @Override
    public void execute(final DelegateExecution execution) {
        execution.getProcessEngineServices()
                .getRuntimeService()
                .correlateMessage("StartPool2", "testBusinessKey");
    }
}
