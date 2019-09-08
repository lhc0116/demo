package com.flowable.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author li huaichuan
 * @date 19-9-8
 */
public class HelloEvent extends ApplicationEvent{

    private String name;

    public HelloEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
