package com.example.demo2.bpmn.listeners;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

@Slf4j
public class DemoBpmnParseListener extends AbstractBpmnParseListener {

    public void registerTaskListeners(String taskType, ActivityImpl activity) {
//        activity.addListener(ExecutionListener.EVENTNAME_START,
//                baseDelegateExecution -> log.info("Starting {} task: {}", taskType, activity.getName()));
//        activity.addListener(ExecutionListener.EVENTNAME_END,
//                baseDelegateExecution -> log.info("Completed {} task: {}", taskType, activity.getName()));
    }

    @Override
    public void parseServiceTask(Element serviceTaskElement, ScopeImpl scope, ActivityImpl activity) {
        registerTaskListeners("service", activity);
    }

    @Override
    public void parseManualTask(Element manualTaskElement, ScopeImpl scope, ActivityImpl activity) {
        registerTaskListeners("manual", activity);
    }

    @Override
    public void parseScriptTask(Element scriptTaskElement, ScopeImpl scope, ActivityImpl activity) {
        registerTaskListeners("script", activity);
    }

    @Override
    public void parseIntermediateCatchEvent(Element intermediateEventElement, ScopeImpl scope, ActivityImpl activity) {
        activity.addListener(ExecutionListener.EVENTNAME_START,
                baseDelegateExecution -> log.info("Registered event subscription: {}", activity.getId()));

        activity.addListener(ExecutionListener.EVENTNAME_END,
                baseDelegateExecution -> log.info("Event fired: {}", activity.getId()));
    }
}
