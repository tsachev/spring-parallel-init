package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author tsachev
 */
@Component
public class Worker {
    private final ApplicationEventPublisher publisher;

    @Autowired
    public Worker(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void doWork(int index) {
        try {
            TimeUnit.SECONDS.sleep(5);
            publisher.publishEvent(new DemoApplicationEvent(this, index));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
