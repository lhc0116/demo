package com.flowable.demo.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author li huaichuan
 * @date 19-8-23
 */
//@Component
public class ProxyConfig {

    @PostConstruct
    public void init() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");
    }
}
