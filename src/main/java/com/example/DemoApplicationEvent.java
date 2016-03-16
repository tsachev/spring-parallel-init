package com.example;

import org.springframework.context.ApplicationEvent;

/**
 * @author tsachev
 */
public class DemoApplicationEvent extends ApplicationEvent {

    private final int index;

    public DemoApplicationEvent(Object source, int index) {
        super(source);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "DemoApplicationEvent{" + "index: " + index + '}';
    }
}
