package com.example.demo2;

import com.example.demo2.services.BusinessProcessServicesFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class Demo2Application implements CommandLineRunner {

    private final BusinessProcessServicesFacade businessProcessServicesFacade;

    public Demo2Application(BusinessProcessServicesFacade businessProcessServicesFacade) {
        this.businessProcessServicesFacade = businessProcessServicesFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        startProcessByKey();
        startProcessByMessage();
    }

    private void startProcessByMessage() {
        businessProcessServicesFacade.correlateMessage("Message1");
//        businessProcessServicesFacade.sendSignal("Signal1");
        new Thread(() -> {
            sleep(3000);
            businessProcessServicesFacade.correlateMessage("Message1");
        }).start();
    }

    private void startProcessByKey() {
        businessProcessServicesFacade.startProcessInstanceByProcessKey("task_execution", Map.of("x", 3));
//        new Thread(() -> {
//            sleep(3000);
//            businessProcessServicesFacade.correlateMessage("Message6");
//        }).start();
    }

    private void sleep(int millis) {
        try {
            log.info("Sleeping {} ms", millis);
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
