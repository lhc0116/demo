package com.flowable.demo.listener;

import com.flowable.demo.event.HelloEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author li huaichuan
 * @date 19-9-8
 */
@Component
public class HelloListener implements ApplicationListener<HelloEvent> {

    @Override
    public void onApplicationEvent(HelloEvent helloEvent) {
        System.out.println("监听到事件发生");
        System.out.println(helloEvent.getName());
    }
}
