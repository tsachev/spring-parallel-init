package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @author tsachev
 */
@Component
public class Initializer {
    private final Worker worker;

    @Autowired
    public Initializer(Worker worker) {
        this.worker = worker;
    }

    @PostConstruct
    public void init() throws Exception {

        try {
            CompletableFuture.allOf(IntStream.range(1, Runtime.getRuntime().availableProcessors() + 1)
                                             .mapToObj((index) -> CompletableFuture.runAsync(() -> worker.doWork(index)))
                                             .toArray(CompletableFuture[]::new)).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }
}
