package com.flowable.demo.controller;

import com.flowable.demo.event.HelloEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author li huaichuan
 * @date 19-8-4
 */
@RestController
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/index")
    public ResponseEntity<HttpStatus> index() {
        String sql = "select count(1) from pass_user where id > ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, 300);
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", count);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }










    @PatchMapping("/event")
    public ResponseEntity<HttpStatus> event() {
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] 开始发布事件");
        applicationContext.publishEvent(new HelloEvent(this, "陆小凤"));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
